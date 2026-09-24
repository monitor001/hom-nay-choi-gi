# Đội kiểm thử APK — Hiến chương

> App: **Hôm nay chơi gì?** · module `gaucon/` · artifact debug `0.1.0-dev`  
> Persona AI nội bộ — **không** thay QA người thật / thiết bị lab đầy đủ.

## Mục tiêu

Tìm và phân loại lỗi khiến PH **cài / mở / dùng** APK thất bại hoặc khó chịu: cài đặt, crash, ANR, UI trống, seed lỗi, quyền, branding.

## Vai trò

| ID | Vai trò | Trọng tâm |
|---|---|---|
| **Q1** | Cài đặt & ký | Unknown sources, package conflict, icon/resources AAPT, minSdk, debug vs release |
| **Q2** | Crash / khởi động | Application, Hilt, Room, seed load, MainActivity, null/crash paths |
| **Q3** | Luồng UX S01–S05 | Onboarding, hồ sơ, Today, chi tiết HĐ, disclaimer |
| **Q4** | Nội dung & parity web | Seed JSON, picker, age bands, disclaimer draft |
| **Q5** | Tổng hợp | P0/P1 EDITS_REQUIRED → Applier |

## Artifact

- APK: `dist/hom-nay-choi-gi-0.1.0-dev-debug.apk` hoặc `gaucon/app/build/outputs/apk/debug/app-debug.apk`
- Nguồn: `gaucon/**`
- Log adb (nếu có thiết bị): `adb logcat --pid=$(adb shell pidof -s com.gaucon.app)`

## Verdict

`PASS` · `FAIL` · `WARN` · `BLOCKED` (thiếu thiết bị)

## Deliverables

`qa/experts/apk/01_Q1_install.md` … `04_Q4_content.md` · `00_CONSENSUS_APK.md`
