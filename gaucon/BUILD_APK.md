# Android APK — bản phát triển (song song web)

## Artifact vừa build

| Mục | Giá trị |
|---|---|
| File | `dist/hom-nay-choi-gi-0.1.0-dev-debug.apk` (máy local; `*.apk` gitignore) |
| applicationId | `com.gaucon.app` |
| versionName | `0.1.0-dev` |
| versionCode | `2` |
| Build | `:app:assembleDebug` |
| Brand UI | **Hôm nay chơi gì?** |

## Xây lại trên máy này

```bat
cd /d "D:\AI Kiem Tien\ideas\BeGau\gaucon"
set JAVA_HOME=C:\Program Files\Microsoft\jdk-17.0.20.101-hotspot
set ANDROID_HOME=%LOCALAPPDATA%\Android\Sdk
gradlew.bat :app:assembleDebug
```

APK: `gaucon\app\build\outputs\apk\debug\app-debug.apk`

## Cài thử

1. Bật **Cài đặt từ nguồn không xác định** / USB debugging.
2. `adb install -r dist\hom-nay-choi-gi-0.1.0-dev-debug.apk`
3. Hoặc copy APK vào điện thoại và mở.

## Song song với web

| Kênh | URL / artifact |
|---|---|
| Web Pages | https://monitor001.github.io/hom-nay-choi-gi/ |
| Android debug | APK local `dist/` (debug-signed) |

Cùng seed `draft_unreviewed`. Reminder / một số màn Android vẫn scaffold (xem `gaucon/README.md`).
