# CONSENSUS APK QA — Hôm nay chơi gì?

> Q5 tổng hợp · 2026-09-24  
> Nguồn: Q1–Q4 · Applier đã áp P0/P1 code trong cùng phiên

---

```
STATUS: OK (static + rebuild) · runtime device BLOCKED
SCOPE: Hợp nhất Q1–Q4; xếp P0/P1; áp patch; clean rebuild dist APK; verify icon PNG trong package.
DONE: Consensus; Applier P0/P1 code; assembleDebug OK; dist APK versionCode=3 / 0.1.0-play; ic_launcher + ic_logo magic PNG_OK.
FILES: qa/experts/apk/00_CONSENSUS_APK.md (+ Q1–Q4); gaucon MainActivity/GauConApp/ContentSeedLoader/Today*; dist/hom-nay-choi-gi-0.1.0-dev-debug.apk
DEVIATIONS: Không có thiết bị adb → chưa verify logcat / INSTALL trên máy thật.
BLOCKERS: Device lab trống; AAB Play còn bước bundleRelease + upload thủ công.
ERRORS: (đã hết) artifact cũ JPEG-as-PNG — đã thay.
NEXT_FOR_PARENT: Gỡ app cũ → cài dist mới; nếu còn lỗi gửi logcat tag GauConApp / AndroidRuntime.
```

---

## Verdict hợp nhất

| Expert | Verdict | P0 chính |
|---|---|---|
| **Q1** Install | **FAIL** (artifact) | APK dist stale: `ic_*` trong package vẫn JPEG dù source đã PNG |
| **Q2** Crash | **FAIL** | Compose state ghi trên `Dispatchers.IO` |
| **Q3** UX | **FAIL**/WARN | Today trống + copy «không phù hợp tuổi» gây hiểu nhầm |
| **Q4** Content | **FAIL**/WARN | Race seed fire-and-forget vs Today picks; empty DB khi version đã set |

**Ship gate:** chưa PASS cho PH sideload cho đến khi (1) clean rebuild APK, (2) magic bytes icon trong APK = PNG, (3) cold start Today có gợi ý sau onboarding.

---

## P0 — đã / phải làm

| ID | Issue | Action | Status |
|---|---|---|---|
| **P0-C1** | `MainActivity` gán `fontScale`/`onboardingDone` trong IO | Đọc DataStore trên IO; gán state trên Main | **APPLIED** |
| **P0-I1** | APK packaged icon JPEG-as-PNG | `clean` + `assembleDebug`; verify magic `89 50 4E 47`; thay `dist/` | **APPLIED** (rebuild 2026-09-24) |
| **P0-S1** | Seed race → Today trống | `ContentSeedLoader` upsert nếu DB trống; `TodayViewModel.load` gọi seed + 1 retry; emptyHint đúng nguyên nhân | **APPLIED** |

## P1

| ID | Issue | Action | Status |
|---|---|---|---|
| **P1-C2** | Seed lỗi nuốt im | `Log.e` trong `GauConApp` | **APPLIED** |
| **P1-C3** | `workerFactory` early access | `check(isInitialized)` trong `workManagerConfiguration` | **APPLIED** |
| **P1-U1** | Copy empty Today sai | Bỏ «không phù hợp tuổi»; hint theo child/seed | **APPLIED** |
| **P1-I2** | Adaptive icon / multi-density | Thêm later | OPEN |
| **P1-V1** | versionName dist lệch source | Rebuild với `0.1.0-play` / code 3 | Via rebuild |

---

## Checklist PH sau rebuild

1. Gỡ app debug cũ (tránh conflict key nếu chuyển Play).
2. Cài `dist/hom-nay-choi-gi-0.1.0-dev-debug.apk` (bật nguồn không xác định nếu cần).
3. Onboarding → Today: phải thấy ≥1 gợi ý (hoặc hint rõ «chưa hồ sơ» / «thử đổi gợi ý»).
4. Không crash ngay khi mở; font scale hệ thống lớn vẫn vào được.
5. Nếu fail: `adb logcat --pid=$(adb shell pidof -s com.gaucon.app)` gửi tag `GauConApp` / AndroidRuntime.

---

## Không làm trong consensus

- Không claim «đã duyệt chuyên gia nội dung trẻ em».
- Không hạ `minSdk` (26 by design).
- Không commit keystore / secrets.
