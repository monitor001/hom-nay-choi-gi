# Hôm nay chơi gì? — Android scaffold (Wave 3)

App phụ huynh Android (local-only): gợi ý 2–3 hoạt động ngắn/ngày cho bé 18–36 tháng.

> **Nội dung seed = `draft_unreviewed` — chưa chuyên gia duyệt.** Không claim “chuẩn Bộ” / đã duyệt trên UI.

Thử nghiệm phụ trong `ideas/BeGau`. **Không** đổi hướng active của AI Kiem Tien.

## Yêu cầu

| Công cụ | Phiên bản |
|---|---|
| JDK | **17** (Temurin / Microsoft Build of OpenJDK) |
| Android SDK | compileSdk / targetSdk **36**, minSdk **26** |
| Android Studio | Ladybug+ khuyến nghị |
| Gradle | Wrapper 8.11.1 (trong repo) |

## Mở project

1. Cài JDK 17 và Android Studio (hoặc SDK Command-line Tools).
2. Tạo `local.properties` từ mẫu:

```properties
sdk.dir=C\:\\Users\\<YOU>\\AppData\\Local\\Android\\Sdk
```

3. Mở thư mục `gaucon/` trong Android Studio (**File → Open**).
4. Sync Gradle.

## Chạy trên emulator

```bat
cd /d "D:\AI Kiem Tien\ideas\BeGau\gaucon"
gradlew.bat :app:assembleDebug
gradlew.bat :app:installDebug
```

Hoặc Run `app` từ Android Studio với AVD API 26+.

Unit tests:

```bat
gradlew.bat :domain:test
gradlew.bat :core:notifications:testDebugUnitTest
```

## Module map

```
app                    Application, NavHost, Hilt
core/common            Clock, ageMonths re-export
core/designsystem      M3 theme + font scale
core/database          Room entities/DAOs/repos
core/datastore         Preferences keys
core/notifications     Channels + DAILY_ACTIVITY Worker (R1–R4)
core/network           ContentApi / CloudBackupGateway no-op stubs
domain                 Models, DailyPicker, use cases
content-seed           assets/content_seed.json + ContentSeedLoader
feature/onboarding     S01 Welcome, S02 Child profile
feature/today          S04 Today + picker
feature/activity       S05 text-first detail
feature/reminder       Partial — schedule 19:30
```

## Phạm vi Wave 3 (đã / chưa)

**Có:** multi-module, seed 90 HĐ → Room, S01/S02/S04/S05, bottom nav chỉ Hôm nay active, notification channels, WorkManager inexact 19:30, disclaimer UI, unit tests ageMonths / DailyPicker / R1–R4.

**Chưa (ngoài scope):** S03 full UX, S06–S14, Billing, cloud SDK, ExoPlayer, Play upload, exact alarm.

## BLOCKERS trên máy scaffold

Máy agent Wave 3 **không có** JDK / `ANDROID_HOME` / Gradle runtime tại thời điểm tạo source. Vì vậy `:app:assembleDebug` **chưa chạy được tại đây**.

Sau khi cài JDK 17 + SDK:

1. Điền `local.properties`
2. Nếu thiếu `gradle/wrapper/gradle-wrapper.jar`, chạy Android Studio sync (tự tải) hoặc `gradle wrapper` với Gradle đã cài.
3. `gradlew.bat :app:assembleDebug`

## Privacy / an toàn sản phẩm

- Local-only (Room + DataStore); không ads / Ad ID / Billing.
- Không xin `POST_NOTIFICATIONS` ở S01; “Để sau” vẫn dùng app; reminder app-side enabled 19:30.
- Manifest **không** khai `SCHEDULE_EXACT_ALARM` / `USE_EXACT_ALARM`.
