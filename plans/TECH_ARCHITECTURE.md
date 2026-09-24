# Gấu Con — Kiến trúc kỹ thuật Android (MVP)

> Tài liệu subagent F · Nguồn: `docs/SPEC_MVP.md` (bản paste 1.0 §5, §7–12) + `plans/PLAN_PHAN_TICH.md`  
> Thử nghiệm phụ `ideas/BeGau` · **Không đổi hướng active** · **Không code production**  
> Pseudo/Kotlin dưới đây là đặc tả tinh chỉnh từ SPEC — chưa scaffold Gradle.

---

## 0. Tóm tắt quyết định kỹ thuật (MVP)

| Hạng mục | Chọn | Ghi chú |
|---|---|---|
| Stack | Kotlin 2.x, Compose + M3, Hilt, Room, DataStore, WorkManager | MVVM + Clean, đa module |
| Offline | Seed JSON trong `content-seed` → Room; sync tùy chọn | App dùng được không mạng |
| Nhắc nhở | WorkManager `OneTimeWorkRequest` + delay | **Không** `SCHEDULE_EXACT_ALARM` / `USE_EXACT_ALARM` |
| Backend | **Chưa chốt** — Supabase vs Firebase | Xem §9 |
| Analytics | Crashlytics + analytics tối giản / tự host | Không SDK quảng cáo |
| minSdk / targetSdk | 26 / 36 | Channels từ API 26; Play target 36 từ 31/08/2026 |

---

## 1. Module map & dependency rules

### 1.1. Cây module (theo SPEC §7.3)

```
gaucon/
├── app/                         # Application, MainActivity, NavHost, Hilt entry
├── core/
│   ├── designsystem/            # Theme, typography, shared Compose components
│   ├── database/                # Room DB, entities, DAOs, TypeConverters
│   ├── datastore/               # Preferences DataStore wrappers
│   ├── network/                 # Retrofit, OkHttp, serializers (P1/cloud)
│   ├── notifications/           # Channels, Scheduler, Worker, Notifier, Receivers
│   └── common/                  # Clock, Result, date helpers, dispatchers
├── domain/                      # Pure Kotlin models + UseCase interfaces
├── feature/
│   ├── onboarding/
│   ├── today/
│   ├── activity/
│   ├── library/
│   ├── milestone/
│   ├── journal/
│   ├── guide/
│   ├── reminder/
│   ├── health/
│   └── settings/
└── content-seed/                # assets JSON + ContentSeedLoader (no Android UI)
```

### 1.2. Dependency rules (bắt buộc)

```
app  →  feature:* , domain , core:* , content-seed
feature:*  →  domain , core:designsystem , core:common
             (+ core:database / datastore / notifications chỉ qua repository interface ở domain
                hoặc facade trong core — feature KHÔNG import DAO trực tiếp)
domain  →  (không phụ thuộc Android / Room / Compose / Hilt)
core:database  →  domain (map Entity ↔ model) , core:common
core:notifications  →  domain , core:common , (Room qua ReminderRepository impl ở data layer)
content-seed  →  domain (DTO/parse) ; KHÔNG → feature , KHÔNG → network
core:network  →  domain ; chỉ được gọi từ sync / auth workers (P1+)
```

**Quy tắc bổ sung**

| Rule | Chi tiết |
|---|---|
| D1 | `feature` A không phụ thuộc `feature` B — giao tiếp qua `domain` UseCase hoặc shared Nav args |
| D2 | Một implementation data layer: đặt `data` package trong `core:database` + `core:network`, hoặc module `data` riêng nếu Sprint 1 cần tách test — **khuyến nghị Sprint 1:** giữ thin `data` trong core để giảm boilerplate |
| D3 | `content-seed` chỉ chứa asset + loader; version bump = thay JSON + `contentVersion` |
| D4 | WorkManager Worker chỉ sống trong `core:notifications` (hoặc `core:sync` nếu tách sau) |
| D5 | Không đưa key/secret vào repo; backend URL qua `local.properties` / CI secrets |

### 1.3. Luồng UI (MVVM)

```
Compose Screen → ViewModel → UseCase (domain) → Repository (interface)
                                      ↓
                         Room / DataStore / WorkManager / (P1 Network)
```

---

## 2. Offline-first content sync

### 2.1. Luồng

```
CMS (Strapi, P1) ──export──► content_vN.json
                                    │
                    ┌───────────────┴───────────────┐
                    ▼                               ▼
         assets/ trong APK                  CDN / Storage (có mạng)
         (content-seed)                            │
                    │                               ▼
                    └────────► ContentSyncWorker ──► upsert Room
                                      │
                                      ▼
                              ActivityDao / MilestoneDao / …
```

1. **Cold start lần đầu:** `ContentSeedLoader` đọc `assets/content_seed.json` → upsert Room nếu `meta.contentVersion` local < seed.
2. **Định kỳ:** `ContentSyncWorker` — `PeriodicWorkRequest` 24h, constraint: `NetworkType.UNMETERED` (Wi‑Fi / không tính cước) **hoặc** cho phép metered nếu phụ huynh bật trong Settings (DataStore flag).
3. **So version:** header/file `contentVersion: Int`; chỉ tải khi remote > local.
4. **Upsert:** theo `activity.id` (và milestone id); không xóa log người dùng.
5. **Video:** stream khi xem; tùy chọn “Tải trước hoạt động tuần này” (Media3 cache) — P1 nếu bandwidth.

### 2.2. Pseudo — ContentSyncWorker

```kotlin
@HiltWorker
class ContentSyncWorker @AssistedInject constructor(
    @Assisted ctx: Context,
    @Assisted params: WorkerParameters,
    private val seed: ContentSeedLoader,
    private val api: ContentApi,          // no-op / absent ở MVP local-only
    private val activityRepo: ActivityRepository,
    private val meta: ContentMetaStore    // DataStore: content_version
) : CoroutineWorker(ctx, params) {

    override suspend fun doWork(): Result {
        val local = meta.version()
        // MVP: có thể chỉ re-seed từ assets khi APK mới hơn
        val bundled = seed.readVersion()
        if (bundled > local) {
            activityRepo.upsertAll(seed.readActivities())
            meta.setVersion(bundled)
        }
        // P1: if (api.latestVersion() > meta.version()) download + upsert
        return Result.success()
    }
}
```

### 2.3. Lỗ hổng SPEC đã tinh chỉnh

| Lỗ hổng | Cách xử lý trong kiến trúc |
|---|---|
| SPEC không có entity `ContentMeta` | DataStore `content_version` (+ optional Room `content_meta` nếu cần audit) |
| Sync vs seed xung đột | Luôn lấy **max(version)**; seed chỉ thắng khi APK mới mang version cao hơn remote đã lưu |
| Activity bị gỡ khỏi gói mới | Soft-delete flag `isRetired` — giữ FK cho `activity_log` cũ |

---

## 3. Daily activity picker + test plan

### 3.1. Thuật toán (SPEC §7.5, tinh chỉnh)

**Đầu vào:** `childId`, `ageMonths`, `today: LocalDate`, lịch sử 14 ngày, feedback `NOT_FIT`, milestone “Đang tập”, `Clock`.

```
1. Candidate = activities where ageMin ≤ age ≤ ageMax AND NOT isRetired
2. Exclude ids completed in last 14 days (by completedAt date)
3. Score:
   - +W1 * (days since domain last used)     // ưu tiên lĩnh vực ít làm 7 ngày
   - +W2 nếu domain ∈ milestones status PRACTICING
   - −W3 nếu similar-to activities with feedback NOT_FIT (14 ngày)
4. Greedy pick 3 items:
   - ≥ 2 domains khác nhau
   - sum(durationMinutes) ≤ 30
5. Stabilize: rng = SeededRandom(hash(childId + "|" + todayISO))
   Sort by score desc, tie-break bằng rng — CÙNG seed → CÙNG tập trong ngày
6. Persist DailyPick(childId, date, activityIds[3]) vào Room
   Lần mở lại trong ngày: đọc DailyPick, không chạy lại random
```

**Pseudo seed ổn định**

```kotlin
fun dailySeed(childId: String, day: LocalDate): Long =
    (childId + "|" + day).hashCode().toLong()  // hoặc Murmur/SHA truncated — document trong test
```

### 3.2. Entity bổ sung (không có trong SPEC §8 — cần thêm)

```kotlin
@Entity(
    tableName = "daily_pick",
    primaryKeys = ["childId", "dateIso"],
    indices = [Index("childId")]
)
data class DailyPickEntity(
    val childId: String,
    val dateIso: String,                 // yyyy-MM-dd local
    val activityIds: List<String>,       // TypeConverter JSON
    val generatedAt: Instant
)
```

### 3.3. Test plan — `DailyPickerTest` (+ liên quan)

| Case | Kỳ vọng |
|---|---|
| Cùng `childId` + `day` + DB snapshot | 2 lần `pick()` → cùng 3 ids, cùng thứ tự |
| Đổi `day` | Có thể khác (không bắt buộc khác, nhưng seed khác) |
| Đã làm A trong 14 ngày | A ∉ kết quả |
| Chỉ còn 1 domain hợp lệ | Vẫn trả ≤ 3; ghi log “domain diversity relaxed” (không crash) |
| Tổng duration | ≤ 30; nếu không đủ candidate thì trả ít hơn 3 |
| Feedback `NOT_FIT` gần đây | Giảm xác suất / điểm hoạt động tương tự (cùng domain + keyword tag nếu có) |
| Age ngoài band | Không vào candidate |
| `Clock` cố định | Dùng `FakeClock` — bắt buộc |

**Instrumented:** Room in-memory + insert 90 fake activities → pick ổn định qua process death (đọc `daily_pick`).

---

## 4. Reminder subsystem (R1–R7)

### 4.1. Channels (SPEC §5.5 / §9.3)

| Channel ID | Tên | Importance | Types |
|---|---|---|---|
| `ch_activity` | Gợi ý hoạt động | DEFAULT | `DAILY_ACTIVITY` |
| `ch_routine` | Nếp sinh hoạt | DEFAULT | `ROUTINE_*`, `READING` |
| `ch_health` | Sức khỏe & tiêm chủng | HIGH | `HEALTH_CHECKUP` |
| `ch_growth` | Mốc & nhật ký | LOW | `MILESTONE_CHECK`, `JOURNAL_WEEKLY` |

`SCREEN_TIME` = thông báo **trong app** (không WorkManager / không system notif bắt buộc).

### 4.2. Thành phần

| Class | Vai trò |
|---|---|
| `NotificationChannels` | Tạo 4 channel lúc `Application.onCreate` |
| `NextFireCalculator` | `next(reminder, now): ZonedDateTime?` |
| `ReminderScheduler` | `enqueueUniqueWork("reminder_"+id)`, snooze, cancel, `rescheduleAll` |
| `ReminderWorker` | `evaluateRules` → show / skip → luôn `schedule` lần sau |
| `ReminderNotifier` | Builder + action PendingIntents |
| `ReminderActionReceiver` | Đã xong / Hoãn / Tắt loại / Deep link |
| `TimeChangeReceiver` | `TIMEZONE_CHANGED` / `TIME_SET` → `rescheduleAll` |

**Manifest:** `POST_NOTIFICATIONS` only — **không** khai báo exact alarm. Tắt WorkManager default initializer → `HiltWorkerFactory` (SPEC §9.1–9.2).

### 4.3. Worker rules R1–R7

| Rule | Implementation |
|---|---|
| **R1** Quiet hours | Nếu `!isUserDefined` và `now ∈ [quiet_start, quiet_end)` (hỗ trợ qua đêm) → `SKIPPED_RULE`; vẫn reschedule. Ngoại lệ: `ROUTINE_*` / `HEALTH_CHECKUP` do user đặt (`isUserDefined=true`) |
| **R2** Max 2 suggested/day | Đếm `ReminderLog` type ∈ {DAILY_ACTIVITY, MILESTONE_CHECK, JOURNAL_WEEKLY} với action `SHOWN` hôm nay; nếu ≥ `max_suggested_per_day` → skip. User-defined không đếm |
| **R3** Auto-degrade | `DAILY_ACTIVITY`: nếu 3 lần liên tiếp `SHOWN` mà không OPENED/DONE trong 24h → tăng `consecutiveIgnored`; lần mở app hiện soft-prompt đổi giờ / thưa hơn / tắt |
| **R4** Already done | `DAILY_ACTIVITY` + `completedCountToday(childId) ≥ 1` → skip |
| **R5** Batch 10 phút | Trước `show`: quét các reminder `fireAt` trong ±10 phút cùng child → gộp 1 notif (“Có 2 nhắc…”) hoặc trì hoãn cái sau; log từng id `SKIPPED_RULE` / `SHOWN` rõ ràng |
| **R6** Copy | Chỉ lấy từ `ReminderMessageProvider` (nội dung đã biên tập); không hard-code câu trách |
| **R7** One-tap off | Action “Tắt loại này” → `enabled=false` + `cancel` + deep link S12 |

**Thứ tự evaluate trong Worker (đề xuất):** permission → R1 → R4 → R2 → R5 → SHOW.

### 4.4. Vì sao không exact alarm

Nhắc nuôi dạy lệch vài phút là chấp nhận được. `USE_EXACT_ALARM` dành báo thức/lịch; `SCHEDULE_EXACT_ALARM` bị hạn chế từ Android 14. WorkManager tự persist qua reboot.

**Dự phòng (SPEC §7.1):** AlarmManager *inexact* chỉ nếu đo được WorkManager thất bại có hệ thống trên OEM cụ thể — không phải đường chính MVP.

### 4.5. OEM battery caveats (Xiaomi / Oppo / Vivo / Samsung…)

| Rủi ro | Mitigation |
|---|---|
| Autostart / battery saver giết Worker | Màn hướng dẫn trong Settings + deep link hãng (giữ matrix QA §11.3) |
| Doze trì hoãn dài | Chấp nhận với daily 19:30; không hứa “đúng phút” trong copy UX |
| Không xin notif lần đầu | Luồng §5.7: giải thích cuối onboarding → rồi `POST_NOTIFICATIONS`; từ chối → banner ≤ 1 lần/tuần |
| Reboot / đổi giờ | `TimeChangeReceiver` + `rescheduleAll`; smoke test bắt buộc Sprint 5–6 |

**Không** dùng exact alarm để “bypass” OEM — trái Play policy và SPEC.

---

## 5. Room entities chính + DataStore keys

### 5.1. ER (SPEC §8.1)

```
Child 1───n ActivityLog n───1 Activity
Child 1───n MilestoneStatus n───1 Milestone
Child 1───n JournalEntry
Child 1───n Reminder
Child 1───n HealthEvent
Child 1───n DailyPick          // bổ sung kiến trúc
Reminder 1───n ReminderLog
```

### 5.2. Entities (rút gọn + chỉnh)

Giữ nguyên từ SPEC: `ChildEntity`, `ActivityEntity`, `ActivityLogEntity`, `ReminderEntity`, `ReminderLogEntity`, `ReminderType`.

**Bổ sung bắt buộc (lỗ hổng SPEC):**

```kotlin
@Entity(tableName = "milestone")
data class MilestoneEntity(
    @PrimaryKey val id: String,
    val domain: Domain,
    val ageBand: String,              // e.g. "24-30m"
    val title: String,
    val description: String,
    val contentVersion: Int
)

@Entity(
    tableName = "milestone_status",
    primaryKeys = ["childId", "milestoneId"]
)
data class MilestoneStatusEntity(
    val childId: String,
    val milestoneId: String,
    val status: MilestoneProgress,    // DONE, PRACTICING, NOT_YET
    val updatedAt: Instant
)

@Entity(tableName = "journal_entry", indices = [Index("childId")])
data class JournalEntryEntity(
    @PrimaryKey val id: String,
    val childId: String,
    val createdAt: Instant,
    val text: String?,
    val photoUri: String?,            // app-private storage
    val linkedActivityId: String?
)

@Entity(tableName = "health_event", indices = [Index("childId")])
data class HealthEventEntity(
    @PrimaryKey val id: String,
    val childId: String,
    val title: String,
    val scheduledAt: LocalDate,       // user-entered; app KHÔNG tự tạo lịch tiêm
    val notes: String?,
    val doneAt: Instant?
)

// DailyPickEntity — xem §3.2
```

**Enums:** `Domain` = 6 mã SPEC; `Feedback` = LIKED / NEUTRAL / NOT_FIT; `ReminderAction` = SHOWN / OPENED / SNOOZED / DONE / DISMISSED / SKIPPED_RULE.

**TypeConverters:** `List`/`Set`/`LocalDate`/`LocalTime`/`Instant` via ISO strings hoặc JSON kotlinx.serialization.

### 5.3. DataStore keys

| Key | Type | Default | Ghi chú |
|---|---|---|---|
| `quiet_start` | String HH:mm | `21:30` | R1 |
| `quiet_end` | String HH:mm | `07:00` | R1 |
| `max_suggested_per_day` | Int | `2` | R2 |
| `font_scale` | Float | `1.0` | A11y ông bà |
| `notif_permission_asked_at` | Long epoch | `0` | |
| `active_child_id` | String | – | |
| `content_version` | Int | `0` | **bổ sung** sync |
| `onboarding_done` | Boolean | false | **bổ sung** |
| `notif_banner_last_shown_at` | Long | `0` | **bổ sung** ≤1/tuần |
| `allow_metered_content_sync` | Boolean | false | **bổ sung** |
| `export_backup_photos` | Boolean | false | ảnh nhật ký vs `dataExtractionRules` |

---

## 6. Privacy — checklist (không thay luật sư)

> Checklist vận hành / kỹ thuật. **Không** phải tư vấn pháp lý. Cần luật sư rà trước khi store public, nhất là khi dữ liệu trẻ + máy chủ nước ngoài.

### 6.1. Local-first MVP

- [ ] Không bắt buộc họ tên thật bé; nickname đủ dùng
- [ ] Không thu thập location, contacts, AD_ID
- [ ] Toàn bộ Child / Log / Journal / Reminder trên Room + app storage
- [ ] Cloud sync **tắt mặc định**; chỉ P1 khi user opt-in
- [ ] Xuất ZIP (JSON + ảnh) + xóa toàn bộ local (và cloud nếu đã bật)
- [ ] `dataExtractionRules`: loại ảnh nhạy cảm khỏi auto-backup trừ khi user đồng ý
- [ ] TLS bắt buộc mọi API P1; không log PII trong Crashlytics (scrub nickname/photo URI)

### 6.2. Google Play — Data safety

- [ ] Form Data safety khớp thực tế (local-only vs có account)
- [ ] Privacy policy URL công khai
- [ ] Target audience = **phụ huynh / người lớn** (MVP)
- [ ] Không exact alarm permissions
- [ ] Premium (nếu có) qua Play Billing — không sidecar payment

### 6.3. Families Policy — note P1 kid library

MVP không hướng UI chính cho trẻ. Nếu P1 thêm **thư viện nội dung cho bé xem**:

- [ ] Review lại declaration Families / Designed for Families
- [ ] Chỉ SDK trong allowlist (nếu áp dụng)
- [ ] Không quảng cáo cá nhân hóa; `SCREEN_TIME` hẹn giờ, không autoplay tiếp, không reward gây nghiện (nguyên tắc sản phẩm)
- [ ] Tách rõ “app phụ huynh” vs “trải nghiệm trẻ” trong Play listing

### 6.4. NĐ 13/2023/NĐ-CP (VN) — checklist kỹ thuật/sản phẩm

- [ ] Thông báo xử lý dữ liệu rõ (in-app + policy): loại dữ liệu, mục đích, thời gian giữ
- [ ] Đồng ý từ cha mẹ / người giám hộ trước khi thu thập dữ liệu trẻ (khi có cloud / account)
- [ ] Cơ chế rút lại đồng ý + xóa
- [ ] Làm rõ nơi lưu máy chủ nếu sync xuyên biên giới → **luật sư review**
- [ ] Không dùng dữ liệu để quảng cáo / bán lại (khớp nguyên tắc sản phẩm)

COPPA/GDPR chỉ khi phát hành ngoài VN — đánh dấu P2 + counsel.

---

## 7. CI/CD skeleton

### 7.1. GitHub Actions (đề xuất)

```yaml
# .github/workflows/android-ci.yml  (skeleton — chưa commit code)
name: android-ci
on:
  pull_request:
  push:
    branches: [main]
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-java@v4
        with: { distribution: temurin, java-version: 17 }
      - name: Gradle cache
        uses: gradle/actions/setup-gradle@v4
      - run: ./gradlew lintDebug
      - run: ./gradlew testDebugUnitTest
      - run: ./gradlew :app:bundleRelease
        # signing via CI secrets (KEYSTORE_*, PLAY_*)
      # Optional: upload AAB to Play Internal testing via gradle-play-publisher
```

### 7.2. Cổng chất lượng

| Gate | Bắt buộc trước |
|---|---|
| `lintDebug` | merge |
| Unit: `NextFire*`, `QuietHours*`, `ReminderRules*`, `DailyPicker*` | merge Sprint 3+ / 5+ |
| `bundleRelease` AAB | Internal testing |
| Manual OEM matrix (§11.3) | Closed testing (Sprint 8) |

### 7.3. Secrets (không vào git)

`KEYSTORE_FILE` (base64), `KEYSTORE_PASSWORD`, `KEY_ALIAS`, `PLAY_SERVICE_ACCOUNT_JSON`.

---

## 8. Sprint 1–8 mapping kỹ thuật + TOP 5 rủi ro

Nguồn: SPEC §12 Giai đoạn 1 (sprint 2 tuần).

| Sprint | Kỹ thuật deliverable | Module / layer |
|---|---|---|
| **1** | Gradle multi-module, Version Catalog, Hilt app, Room empty DB, DesignSystem stub, CI lint/test/AAB | `app`, `core:*`, CI |
| **2** | Onboarding S01–S02, Child CRUD, seed load → Room, DataStore `active_child_id` | `feature/onboarding`, `content-seed` |
| **3** | Today S04, `DailyActivityPicker`, DailyPick persist, Activity detail S05 | `feature/today`, `feature/activity`, `domain` |
| **4** | Complete + feedback S06, Library S07, ExoPlayer basic | `feature/library`, ActivityLog |
| **5** | Channels, Scheduler, Worker, S03 permission UX, `DAILY_ACTIVITY`, R1–R4 unit tests | `core/notifications`, `feature/reminder` |
| **6** | `ROUTINE_*`, MILESTONE/JOURNAL reminders, actions, S11–S12, R3+R5, OEM guide draft | notifications + settings |
| **7** | Milestone S08, Journal S09, Guide S10, Health S13 (+ HealthEvent) | `feature/milestone|journal|guide|health` |
| **8** | Settings S14, export/delete, device QA, Closed testing track | `feature/settings`, Play Console |

### TOP 5 rủi ro kỹ thuật

| # | Rủi ro | Tác động | Mitigation |
|---|---|---|---|
| 1 | **OEM giết nền** (Xiaomi/Oppo/…) → nhắc không tới | KPI hoàn thành hoạt động giảm | Hướng dẫn pin theo hãng; chấp nhận inexact; đo trên máy thật Sprint 5–8 |
| 2 | **Picker không ổn định / lệch domain** | Phụ huynh thấy “đổi món” trong ngày; ít đa dạng lĩnh vực | `DailyPick` persist + unit tests seed; fallback rõ khi thiếu candidate |
| 3 | **Nội dung seed / version sync lệch** | Crash FK hoặc activity “ma” | Soft-delete; `content_version`; không xóa log |
| 4 | **Play policy** (exact alarm, Data safety, nhầm Families) | Reject / delist | Không exact alarm; audience = parents; counsel trước kid library |
| 5 | **Backend chốt muộn** (Supabase vs Firebase) | Đôi công sync/auth Sprint sau | MVP **local-first**; abstract `AuthRepository` / `ContentApi`; chốt trước khi làm cloud |

---

## 9. Quyết định cần parent chốt

### 9.1. Supabase vs Firebase

| Tiêu chí | Supabase | Firebase |
|---|---|---|
| DB | Postgres SQL — gần CMS/export | Firestore NoSQL |
| Auth | Email/magic link/OAuth | Mature Android SDK |
| Storage | Built-in | Cloud Storage |
| Crash/Analytics | Tách (cần thêm Sentry/Crashlytics) | Crashlytics + Analytics liền |
| VN latency / cost | Phụ thuộc region | Phụ thuộc region |
| Fit MVP local-first | Cả hai **không chặn** MVP | Cả hai **không chặn** MVP |

**Đề xuất kiến trúc:** giữ interface `ContentApi` + `CloudBackupGateway`; **không** bind SDK cho đến khi parent chốt. Ưu tiên ship offline Sprint 1–8.

**Câu hỏi cho parent:** (1) Có cần đăng nhập trước Closed testing không? (2) Ưu tiên SQL/CMS Postgres hay tốc độ SDK Google? (3) Region dữ liệu có ràng buộc NĐ 13 không?

### 9.2. Analytics tối giản

| Cho phép MVP | Không |
|---|---|
| Firebase Crashlytics (scrub PII) | Ad SDK / attribution quảng cáo |
| Event tối giản **local** hoặc tự host: `activity_completed`, `reminder_shown`, `reminder_skipped_rule` — không gắn tên bé | Fingerprint / advertising ID |
| Đếm KPI ≥3 hoạt động/tuần từ **Room** (primary) | Vanity “time in app” làm KPI (cấm theo sản phẩm) |

**Câu hỏi cho parent:** Crashlytics-only đủ Closed testing, hay cần event pipeline từ tuần beta?

---

## Phụ lục A — Ánh xạ màn hình ↔ feature module (tham chiếu)

| Screen | Module |
|---|---|
| S01–S02 Onboarding | `feature/onboarding` |
| S03 Permission / giờ nhắc | `feature/reminder` |
| S04 Today | `feature/today` |
| S05–S06 Activity | `feature/activity` |
| S07 Library | `feature/library` |
| S08 Milestone | `feature/milestone` |
| S09 Journal | `feature/journal` |
| S10 Guide | `feature/guide` |
| S11–S12 Reminder settings | `feature/reminder` + `settings` |
| S13 Health | `feature/health` |
| S14 Settings | `feature/settings` |

## Phụ lục B — Nguồn & giới hạn

- Chi tiết §7–12 lấy từ bản paste SPEC 1.0 trong chat parent (file `docs/SPEC_MVP.md` trên disk đang **rút gọn** mục 4–14).  
- Tài liệu này tinh chỉnh lỗ hổng: `DailyPick`, milestone/journal/health entities, DataStore keys sync/onboarding, thứ tự R1–R7, soft-delete content.  
- Nội dung hoạt động / copy nhắc = nháp cần chuyên gia duyệt (`reviewed_by`) — ngoài phạm vi kiến trúc.

---

*Hết TECH_ARCHITECTURE.md · Subagent F*
