# Gấu Con — Build readiness (Wave 2 / G3)

> Phiên bản: 1.2 · Ngày: 2026-09-24 · Agent: G3  
> Nguồn vận hành: `plans/CONSENSUS.md` **§5** (G1 OK) + G2 seed + E/F/PIPELINE/SPEC/research/01  
> Seed: `content/seed/activities_mvp_draft.json` (**90 HĐ**, parse OK) · `content/CURATION_LOG.md`  
> Thử nghiệm phụ `ideas/BeGau` · **Không code** · **Không sửa** `AGENTS.md` / `PROJECT_STATE.md`

---

## 1. Verdict

### **GO**

**Cổng Wave 2→3 đạt:**
| # | Điều kiện (CONSENSUS §7) | Trạng thái |
|---|---|---|
| 1 | CONSENSUS §5 QUYẾT ĐỊNH CHỐT | ✅ |
| 2 | G2 `activities_mvp_draft.json` parse được, ~90 HĐ | ✅ 90 HĐ |
| 3 | BUILD_READY = GO \| GO-WITH-RISKS | ✅ **GO** |
| 4 | Parent im lặng = chấp nhận §5 | ✅ (mặc định) |

**Lý do GO:**
- CONSENSUS §5 chốt local-only, text-first, font Vừa (+ auto Lớn nếu scale ≥ 1.3), không exact alarm / Billing / cloud auth MVP.
- Seed G2 sẵn sàng nạp vào `:content-seed` (xem `CURATION_LOG.md`).
- Phạm vi Wave 3 hẹp (PIPELINE) — đủ để scaffold ngay.

**Rủi ro còn lại (không hạ verdict):** nội dung `reviewed_by: []` / OEM Worker / picker / SPEC disk rút gọn — xử lý bằng DoD + TOP 5 §4; **không** phải blocker cổng. Không claim chuyên gia duyệt trên UI/store.

**NO-GO chỉ khi:** parent cấm code; bắt buộc cloud/Billing trước Closed testing; hoặc đổi hướng active — hiện **không** áp dụng.

**Hành động:** Parent có thể launch Wave 3 scaffold theo §2–§6.

---

## 2. Phạm vi scaffold Wave 3 (cụ thể)

Ánh xạ Sprint 1–2 của F + PIPELINE Wave 3 + CONSENSUS §7 điều kiện #5; **không** làm đủ S07–S14 / R5–R7 / ExoPlayer / Play upload.

### 2.1. Module Gradle (bật ngay)

| Module | Bắt buộc Wave 3? | Việc tối thiểu |
|---|---|---|
| `:app` | Có | `Application` + Hilt, `MainActivity`, NavHost stub, theme |
| `:core:common` | Có | `Clock`, `ageMonths(birth, today)`, Result |
| `:core:designsystem` | Có | Color/Typography M3 stub; cỡ chữ theo DataStore (mặc định **Vừa**) |
| `:core:database` | Có | Room DB + entities §2.3 + DAOs tối thiểu |
| `:core:datastore` | Có | Keys: `onboarding_done`, `active_child_id`, `content_version`, `quiet_*`, `max_suggested_per_day`, `font_scale`, `notif_*` |
| `:core:notifications` | Có | 4 channels; `DAILY_ACTIVITY` Worker + R1–R4 |
| `:core:network` | **Stub rỗng / no-op** | Interface `ContentApi` / `CloudBackupGateway` — **không bind** SDK |
| `:domain` | Có | Models + UseCase interfaces (picker, seed, child) |
| `:content-seed` | Có | Assets JSON + `ContentSeedLoader` |
| `:feature:onboarding` | Có | S01, S02 |
| `:feature:today` | Có | S04 + gọi picker |
| `:feature:activity` | Có | S05 text-first (+ S06 stub optional) |
| `:feature:reminder` | Partial | Schedule `DAILY_ACTIVITY` 19:30; S03 full UX có thể stub (banner quyền trên S04) |
| Các feature còn lại | **Module rỗng hoặc chưa tạo** | Không màn P0 Wave 3 |

**Dependency rules:** giữ D1–D5 của F — feature không import DAO trực tiếp.

### 2.2. Màn hình Wave 3

| Mã | Phạm vi scaffold | Ghi chú |
|---|---|---|
| **S01** | 3 slide + CTA Bắt đầu | Copy CONSENSUS tone; **không** xin notif |
| **S02** | Tên gọi + ngày sinh → `ageMonths`; giới tính/ảnh optional | Lưu `Child` + `active_child_id` |
| **S03** | Optional stub / skippable | “Để sau” → S04; `DAILY_ACTIVITY` vẫn **enabled app-side** 19:30 (CONSENSUS §4.12 / §5.7) |
| **S04** | Chào “bạn” + tên bé + tuổi tháng; 2–3 thẻ; empty state | Đổi gợi ý: không trùng id đã hiện hôm nay nếu còn ứng viên (CONSENSUS §4.11) |
| **S05** | Title, phút, domain, materials, steps 3–5, safety, CTA Hoàn thành | **Text-first**; video placeholder/ẩn |
| S06–S14 | Ngoài Wave 3 (CTA Hoàn thành có thể ghi `ActivityLog` tối giản) | Ghim từ Thư viện = P0 nhẹ sau khi có S07 — không block scaffold |

**Nav tối thiểu:** `!onboarding_done` → S01→S02→S04; else S04. Bottom nav: chỉ **Hôm nay** active.

### 2.3. Room entities tối thiểu

| Entity | Wave 3 |
|---|---|
| `ChildEntity` | id, displayName, birthDate, gender?, photoUri? |
| `ActivityEntity` | id, ageMinMonths, ageMaxMonths, domains, title, goal, materials, steps, safety, easier?, harder?, durationMinutes, isRetired, contentVersion, reviewedBy, contentStatus |
| `ActivityLogEntity` | id, childId, activityId, completedAt, feedback? |
| `DailyPickEntity` | childId + dateIso PK; activityIds; generatedAt |
| `ReminderEntity` | id, childId, type=`DAILY_ACTIVITY`, enabled, time, isUserDefined |
| `ReminderLogEntity` | id, reminderId, firedAt, action |
| Milestone* / Journal* / Health* | **Chưa** bắt buộc Wave 3 |

**`ageMonths` (CONSENSUS §2.2 / §5.3 — unit test bắt buộc):**

```
ageMonths = (y2-y1)*12 + (m2-m1)
if (day2 < day1) ageMonths -= 1
if (ageMonths < 0) ageMonths = 0
```

**Filter HĐ:** `age_min_months ≤ ageMonths ≤ age_max_months`.

**Giảm trọng số sau 😕 (MVP):** cùng `domains[0]` (+ cùng materials tag nếu G2 có) — CONSENSUS §4.8.

### 2.4. Reminder `DAILY_ACTIVITY` — R1–R4

| Rule | Wave 3 |
|---|---|
| Channel `ch_activity` | `Application.onCreate` |
| Schedule | WorkManager delay; **không** exact alarm |
| Default | 19:30 local; routine/health mặc định **tắt** |
| **R1** Quiet hours | Default 21:30–07:00 |
| **R2** Max 2 suggested/day | Default max=2 |
| **R3** Auto-degrade | Soft-prompt stub OK |
| **R4** Already done | Skip nếu ≥1 log hôm nay |
| R5–R7, ROUTINE_*, HEALTH_* | Hoãn Sprint 6+ |

Thứ tự: permission → R1 → R4 → R2 → SHOW → luôn reschedule.

### 2.5. Nạp seed JSON

1. Nguồn: `content/seed/activities_mvp_draft.json` (90 HĐ, G2 OK) → copy/sync vào `content-seed/src/main/assets/content_seed.json` (hoặc đọc trực tiếp path build đã chép).
2. Cold start: upsert nếu seed `contentVersion` > DataStore.
3. Schema: `age_min_months`, `age_max_months`, `domains[]`, `duration_minutes`, `reviewed_by`, `content_status`; id `act_{domainShort}_{nnn}` — chi tiết trong `content/CURATION_LOG.md`.
4. UI **không** claim chuyên gia duyệt (`reviewed_by: []`, `draft_unreviewed`).
5. Parse + upsert idempotent theo `id`; thiếu video URL không crash (text-first).

### 2.6. Daily picker (S04)

Thuật toán CONSENSUS §3.1 / F §3.1: seed `childId|date`, persist `DailyPick`, exclude 14 ngày, ≥2 domain nếu đủ, tổng ≤30 phút. Unit: cùng seed → cùng ids; age ngoài band → loại.

---

## 3. Mặc định kỹ thuật (neo CONSENSUS §5)

> Parent im lặng = chấp nhận CONSENSUS §5. Bảng dưới là ánh xạ vận hành Wave 3 — **không còn pending G1**.

| Hạng mục | Chốt | CONSENSUS |
|---|---|---|
| Dữ liệu | **Local-only** (Room + DataStore + app storage) | §5.6 |
| Auth / cloud | **No cloud auth**; abstract API, không bind Supabase/Firebase | §5.6 |
| Thanh toán | **No Play Billing** | §6 P1 |
| Video S05 | **Optional / text-first**; không ExoPlayer bắt buộc Wave 3 | §5.9 |
| Sync remote | No-op; chỉ seed APK | §5.6 / §3.2 |
| Analytics | KPI từ Room; Crashlytics optional (scrub); **không** ads/AD_ID | §5.11 |
| Exact alarm | **Cấm** | §5.7 |
| Quảng cáo / bán dữ liệu | **Cấm** | §6 loại |
| Font lần đầu | Mặc định **Vừa**; nếu font scale hệ thống ≥ 1.3 → auto/đề xuất **Lớn** | §5.10 |
| S03 “Để sau” | `DAILY_ACTIVITY` **enabled app-side** 19:30; không system notif đến khi có quyền | §5.7 / §4.12 |
| Đổi gợi ý | Không giới hạn cứng; không trùng id đã hiện nếu còn ứng viên | §5.12 / §4.11 |
| 😕 “tương tự” | Cùng `domains[0]` (+ materials tag nếu có) | §4.8 |
| Xưng hô strings | **“bạn”** + tên gọi bé | §5.13 |
| `reviewed_by` | `[]` / draft OK nội bộ; không claim duyệt / “chuẩn Bộ” | §5.8 / §8 |
| Dark mode | **P1** — không chặn Wave 3 | §4.13 |
| Audience | Phụ huynh / người lớn | §5.1 |
| Thư mục app | **`gaucon/`** dưới `ideas/BeGau` | F + §5.15 |

---

## 4. Rủi ro TOP 5 + mitigation

| # | Rủi ro | Tác động | Mitigation |
|---|---|---|---|
| 1 | **OEM giết WorkManager** → `DAILY_ACTIVITY` không tới | KPI HĐ giảm | Copy không hứa đúng phút; hướng dẫn pin; đo máy thật; không exact alarm |
| 2 | **Schema / `content_version` lệch** khi chỉnh seed sau này | Upsert fail hoặc activity “ma” | Soft-delete `isRetired`; version bump có kiểm tra; unit parse theo CURATION_LOG |
| 3 | **Picker không ổn định trong ngày** | PH thấy đổi món loạn | Persist `DailyPick`; unit test seed; fallback thiếu candidate |
| 4 | **Nội dung chưa `reviewed_by`** bị hiểu là đã duyệt | Uy tín / cảm nhận pháp lý | `draft_unreviewed`; disclaimer CONSENSUS §8; chặn production claim |
| 5 | **Scope creep Wave 3** (Billing, cloud, đủ tab, video, S08…) | Trễ scaffold | Cắt cứng §2; reject PR ngoài list; network stub |

---

## 5. DoD checklist — coi scaffold “xong”

### 5.1. Build & cấu trúc
- [ ] Multi-module Gradle + Version Catalog; `./gradlew :app:assembleDebug` OK
- [ ] Hilt + nav S01→S02→S04→S05
- [ ] Module map §2.1; feature ↛ feature
- [ ] README: JDK 17, `gaucon/`, lệnh emulator

### 5.2. Dữ liệu & seed
- [ ] Room + entities §2.3
- [ ] `ContentSeedLoader` → Activity; `content_version` cập nhật
- [ ] Nạp `activities_mvp_draft.json` (90 HĐ) vào Room; thiếu video URL không crash
- [ ] `ageMonths` unit test (vd. 15/03/2024 → 24/09/2026 = 30)
- [ ] Filter `age_min_months ≤ age ≤ age_max_months`

### 5.3. UX lõi
- [ ] S01 không xin notif
- [ ] S02 → `Child` + `active_child_id`
- [ ] S04: 2–3 thẻ ổn định trong ngày; font mặc định Vừa (auto Lớn nếu scale ≥ 1.3)
- [ ] S05 text-first; strings giọng “bạn” + tên bé
- [ ] Offline S04/S05
- [ ] “Để sau” / chưa quyền: app dùng được; reminder app-side vẫn enabled

### 5.4. Reminder
- [ ] Channel `ch_activity`
- [ ] `DAILY_ACTIVITY` 19:30 enqueue
- [ ] Unit test R1–R4 / `NextFire*`
- [ ] Không `SCHEDULE_EXACT_ALARM` / `USE_EXACT_ALARM`

### 5.5. Chất lượng / an toàn sản phẩm
- [ ] Không secret / Ad SDK / Billing / cloud auth bắt buộc
- [ ] Không claim chuyên gia duyệt / chuẩn Bộ trên UI
- [ ] Không % so sánh trẻ khác; không guilt-trip
- [ ] `lintDebug` + unit picker/reminder xanh
- [ ] Không sửa `AGENTS.md` / `PROJECT_STATE.md` / hướng active

**Ngoài DoD scaffold:** Play Closed testing, OEM matrix đủ, S06 đầy đủ, Library/Milestone/Journal, R5–R7, cloud.

---

## 6. Cấu trúc thư mục gợi ý

Root module **`gaucon/`** (alias `android/` cùng nội dung nếu parent muốn).

```
ideas/BeGau/
├── docs/SPEC_MVP.md
├── plans/CONSENSUS.md         # §5 neo quyết định
├── plans/BUILD_READY.md       # file này
├── research/…
├── content/
│   └── seed/
│       └── activities_mvp_draft.json   # G2 ✅ 90 HĐ
└── gaucon/
    ├── settings.gradle.kts
    ├── build.gradle.kts
    ├── gradle/libs.versions.toml
    ├── app/
    ├── core/{common,designsystem,database,datastore,network,notifications}/
    ├── domain/
    ├── feature/{onboarding,today,activity,reminder}/
    ├── content-seed/src/main/assets/content_seed.json
    └── README.md
```

```bash
cd "D:\AI Kiem Tien\ideas\BeGau\gaucon"
./gradlew :app:assembleDebug
./gradlew :domain:testDebugUnitTest :core:notifications:testDebugUnitTest
./gradlew :app:installDebug
```

(Windows: `gradlew.bat`.)

---

## 7. Cổng đạt — việc Wave 3

1. Copy/sync `content/seed/activities_mvp_draft.json` → `gaucon/content-seed/.../assets/`.
2. Scaffold theo §2; DoD §5 trước khi coi xong.
3. Giữ phân bổ / schema theo CURATION_LOG + CONSENSUS (16/14/16/14/12/18; `age_min_months` / `age_max_months`).
4. Parent chỉ override CONSENSUS §5 nếu đổi hướng sản phẩm.

---

## Phụ lục — Giới hạn

- Chỉ sửa file này trong task cập nhật; không code.  
- Không bịa số liệu dùng thử / doanh thu.  
- Nội dung = nháp đến khi `reviewed_by` có chuyên gia.  
- Closed testing / production = quyết định parent.

---

*Hết BUILD_READY.md · G3 v1.2 · Verdict: GO*
