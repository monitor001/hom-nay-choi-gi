# Q4 — Nội dung & seed parity (Android)

> Persona: nội dung / seed · đội APK (`00_CHARTER.md`)  
> Phạm vi: `gaucon/content-seed/**`, `assets/content_seed.json` ↔ `web/content/activities.json` (+ canonical `content/seed/activities_mvp_draft.json`)  
> **Không** sửa seed/code trong lượt này  
> Ngày: 2026-09-24

---

## STATUS

**WARN**

Parity web ↔ Android **đạt** (byte-identical, 97 HĐ, `version: 1`, `content_status: draft_unreviewed`). Disclaimer nháp hiện đúng trên Welcome / Today / Activity detail.  

**Rủi ro seed:** `ContentSeedLoader` không tự xử lý JSON lỗi; `GauConApp` nuốt exception bằng `runCatching` không log; cold start có race Room trống → Today trống với copy “không phù hợp tuổi” (sai nguyên nhân). Upsert JSON “hợp lệ nhưng rỗng” có thể khóa `content_version` = 1 và chặn seed lại.

---

## 1. Sample compare — counts / status / fields

| Kiểm tra | `content_seed.json` (Android) | `web/content/activities.json` | Kết quả |
|---|---|---|---|
| SHA-256 (file) | `f8cc8be1ba27487d…` | identical | **PASS** byte-identical (107 954 bytes) |
| Canonical `content/seed/activities_mvp_draft.json` | same object | same | **PASS** ba bản đồng nhất |
| `version` | `1` | `1` | OK |
| `content_status` (file) | `draft_unreviewed` | `draft_unreviewed` | OK |
| `activities.length` | **97** | **97** | OK (docs cũ ~90; post–Wave5 = 97) |
| Unique `id` | 97 / 0 null / 0 only-Android / 0 only-web | same | OK |
| Per-activity `content_status` | không có field | không có | OK — status chỉ ở file-level; loader hardcode `DRAFT_UNREVIEWED` |
| Title / age / domains / steps (common ids) | 0 diff | 0 diff | OK |
| Domain enum | PHYSICAL, COGNITIVE, LANGUAGE, SOCIAL_EMOTIONAL, AESTHETIC, SELF_CARE | same counts | OK — khớp `Domain` Kotlin |
| Age bands 18–36m | ≥37 candidates mỗi tháng | same | OK cho `DailyPicker` (≥3) |
| `reviewed_by` | 97/97 `[]` | same | Expected draft |
| `isPremium` | 97/97 `false` | same | DTO có; **không** map vào `Activity` domain |

Sync path: `scripts/wave5_apply_seed.mjs` ghi đồng thời canonical + web + Android — giải thích parity hiện tại.

---

## 2. Disclaimer / draft surface (Android)

| Màn | Copy | Đánh giá |
|---|---|---|
| Welcome (slide 3) | “draft_unreviewed — chưa được chuyên gia duyệt. Không phải chẩn đoán y tế.” | Đúng trạng thái; jargon `draft_unreviewed` lộ PH |
| Today | “Nội dung nháp (draft_unreviewed) — chưa chuyên gia duyệt.” | OK an toàn pháp lý nội bộ; jargon P2 |
| Activity detail | “Bản nháp draft_unreviewed — chưa chuyên gia duyệt.” | OK |
| Loader → domain | `contentStatus = ContentStatus.DRAFT_UNREVIEWED` (bỏ qua file string) | **Tốt** — không thể “lỡ” REVIEWED từ JSON |

Web banner tương đương (“chưa chuyên gia duyệt”) — parity ý nghĩa OK.

---

## 3. `ContentSeedLoader` — JSON fail / error handling

```32:45:gaucon/content-seed/src/main/java/com/gaucon/contentseed/ContentSeedLoader.kt
    suspend fun upsertIfNewer() {
        val seed = readSeed()
        val local = prefs.contentVersion()
        if (seed.version > local) {
            activityRepository.upsertAll(seed.activities.map { it.toDomain(seed.version) })
            prefs.setContentVersion(seed.version)
        }
    }

    private fun readSeed(): SeedFile {
        context.assets.open(ASSET_NAME).bufferedReader().use { reader ->
            return json.decodeFromString(SeedFile.serializer(), reader.readText())
        }
    }
```

```26:28:gaucon/app/src/main/java/com/gaucon/app/GauConApp.kt
        appScope.launch {
            runCatching { contentSeedLoader.upsertIfNewer() }
        }
```

| Kịch bản | Hành vi hiện tại | Rủi ro PH |
|---|---|---|
| Asset thiếu / IO lỗi | Exception → `runCatching` nuốt, **không log** | Room trống; Today “chưa có gợi ý…” |
| JSON parse fail (`SerializationException`) | Nuốt im | Như trên — **UI trống, không crash** (WARN, khó debug) |
| JSON `{}` / thiếu `activities` | `SeedFile` default `version=1`, `activities=[]` → upsert rỗng + `setContentVersion(1)` | Seed hỏng **bị khóa** đến khi bump `version` |
| Domain string lạ | `mapNotNull` + `Domain.valueOf` → drop im | HĐ mất domain / picker lệch (hiện seed sạch 0 invalid) |
| Cold start race | Seed async IO; `TodayViewModel.load()` không chờ | Lần mở đầu có thể picks rỗng dù seed OK |

`Json { ignoreUnknownKeys; isLenient }` — an toàn khi thêm field (vd. `source_refs` từ applier); không bảo vệ empty/corrupt payload.

---

## 4. Picker / age (tóm tắt)

- Filter: `ageMonths in ageMin..ageMax`, không retired — coverage 18–36m dày (≥37 HĐ).  
- Empty Today copy hiện tại **không phân biệt** “seed chưa nạp / lỗi” vs “không khớp tuổi”.

---

## EDITS_REQUIRED

### P1 — seed resilience (Applier / Android)

1. **`GauConApp`:** không nuốt im — log `Result.exceptionOrNull()` (Timber/Log); tùy chọn surface non-fatal flag cho Today.  
2. **`upsertIfNewer`:** chỉ `setContentVersion` khi `seed.activities.isNotEmpty()` (và ideally count ≥ ngưỡng tối thiểu, vd. 1 hoặc = expected).  
3. **First-launch race:** await seed trước khi navigate Today, hoặc `TodayViewModel` retry khi `contentVersion==0` / activity table empty.  
4. **Empty-state copy:** tách “Chưa tải được nội dung — thử mở lại app” vs “Chưa có gợi ý phù hợp tuổi tháng”.

### P2 — polish / drift

5. Disclaimer PH: bỏ jargon `draft_unreviewed` trên UI (giữ “nháp / chưa chuyên gia duyệt”) — align X3.  
6. CI/script assert hash parity: `content/seed/activities_mvp_draft.json` ≡ `web/content/activities.json` ≡ `gaucon/.../content_seed.json`.  
7. (Optional) map hoặc bỏ `isPremium` khỏi DTO nếu không dùng.

### Không yêu cầu (đã OK)

- Sync lại 97 HĐ web↔Android (đã identical).  
- Đổi `content_status` file-level hay hardcode `DRAFT_UNREVIEWED`.

---

## Seed risks (tóm tắt cho Q5)

| Risk | Mức | Ghi chú |
|---|---|---|
| Silent seed failure → UI trống | **P1** | `runCatching` không log |
| Empty/minimal JSON khóa `content_version` | **P1** | default `version=1` + `activities=[]` |
| Cold-start race seed vs Today | **P1** | async upsert |
| Drift web↔Android nếu sửa tay 1 bên | **P2** | hiện OK nhờ `wave5_apply_seed.mjs`; thiếu CI |
| Domain invalid bị drop im | **P2** | seed hiện sạch |
| Jargon `draft_unreviewed` trên PH | **P2** | không sai pháp lý; khó đọc |

---

## Verdict cho Q5

`STATUS: WARN` — nội dung parity **PASS**; vận hành seed load **cần P1** trước khi coi APK “dùng ổn” trên cold start / JSON lỗi.
