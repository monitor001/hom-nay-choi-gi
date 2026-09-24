# Q1 — Cài đặt & ký APK

> Persona: Expert Q1 · App: **Hôm nay chơi gì?** · `com.gaucon.app` · Module `gaucon/`  
> Charter: `qa/experts/apk/00_CHARTER.md`  
> Ngày: 2026-09-24 · Review tĩnh (không rebuild; không thiết bị adb)

---

```
STATUS: FAIL
SCOPE: Cài đặt & ký — unknown sources, corrupt resources (JPEG-as-PNG), minSdk 26, debug vs release signing, Play Internal path.
DONE: Đọc charter + build.gradle.kts + AndroidManifest + BUILD_APK.md + PLAY_CONSOLE.md; magic-byte check source res; extract + magic-byte check icon trong APK dist; aapt dump badging; xác nhận keystore local (không đọc secret).
FILES: qa/experts/apk/01_Q1_install.md
DEVIATIONS: Không chạy assemble lại (charter: chỉ rebuild nếu cần verify icon — đã verify bằng extract APK). Không test adb install trên máy thật.
BLOCKERS: Không có thiết bị adb (`adb devices` trống) → không chứng minh INSTALL_* trên OEM; đủ bằng chứng artifact/source cho P0 icon.
ERRORS: Artifact debug hiện hành chứa JPEG giả PNG (FF D8 FF) trong res icon — khớp known corrupt-resources failure mode.
NEXT_FOR_PARENT: Applier P0 — clean rebuild debug (+ bundleRelease nếu đi Play); thay dist APK; Q5 hợp nhất.
```

---

## Verdict (charter)

**FAIL** — nguồn icon đã sửa PNG thật, nhưng **APK đang phát** (`dist/hom-nay-choi-gi-0.1.0-dev-debug.apk` = `app/build/outputs/apk/debug/app-debug.apk`, 2026-09-24 14:33) vẫn đóng gói JPEG gắn đuôi `.png` → rủi ro cài/resource corrupt; sideload vẫn đòi «nguồn không xác định»; Play path chưa có `.aab` sẵn.

---

## Checklist Q1

| Hạng mục | Verdict | Chi tiết |
|---|---|---|
| Unknown sources / sideload | **WARN (expected)** | `BUILD_APK.md` đúng: PH phải bật nguồn không xác định hoặc USB debug. Play Internal (`docs/PLAY_CONSOLE.md`) là đường tránh — chưa có AAB trong `outputs/`. |
| Corrupt resources / AAPT icon | **FAIL (P0)** | Source `mipmap-xxhdpi/ic_launcher.png` + `drawable/ic_logo.png`: **PNG thật** (`89 50 4E 47…`, 70 781 B, cùng SHA-256). APK packaged `res/drawable/ic_logo.png`: **JPEG** (`FF D8 FF E0…JFIF`, 247 127 B). Intermediates `packaged_res/{debug,release}` vẫn JPEG — build cache/artifact **stale** sau khi sửa source. |
| minSdk 26 | **PASS (by design)** | `minSdk=26` khớp `plans/TECH_ARCHITECTURE.md` / README. Máy API &lt; 26 → `INSTALL_FAILED_OLDER_SDK` — ghi chú PH, không đổi trừ khi product đổi phạm vi. |
| Debug signing | **WARN** | Artifact hiện tại = **debug-signed**, `versionCode=2` / `versionName=0.1.0-dev` (aapt). Source `build.gradle.kts` đã bump `versionCode=3` / `0.1.0-play` — **docs + APK lệch source**. Debug **không** upload Play. |
| Release / Play path | **WARN → blocker vận hành** | `signingConfigs.release` + `keystore.properties` + `upload-keystore.jks` **có trên máy** (gitignore OK). Chưa thấy `app-release.aab`. Manifest release intermediates trỏ `@mipmap/ic_launcher` nhưng packaged mipmap vẫn JPEG stale. |
| Package / branding install surface | **PASS (static)** | `applicationId=com.gaucon.app`; label `Hôm nay chơi gì?`; LAUNCHER activity exported. Chỉ 1 mật độ mipmap (xxhdpi) — scale OK, thiếu adaptive = P1. |
| Thiết bị lab | **BLOCKED** | Không device gắn adb trong phiên review. |

---

## Bằng chứng icon (magic bytes)

| File | Magic | Size | Ghi chú |
|---|---|---|---|
| `res/mipmap-xxhdpi/ic_launcher.png` (source) | PNG `89 50 4E 47` | 70 781 | OK |
| `res/drawable/ic_logo.png` (source) | PNG `89 50 4E 47` | 70 781 | Cùng hash launcher |
| APK `res/drawable/ic_logo.png` | **JPEG** `FF D8 FF E0` | 247 127 | **P0 — artifact cũ** |
| `packaged_res/.../mipmap-xxhdpi-v4/ic_launcher.png` | **JPEG** | 247 127 | Stale intermediate |

Manifest **source** hiện: `android:icon` / `roundIcon` = `@mipmap/ic_launcher`.  
Merged **debug** (build cũ): vẫn `@drawable/ic_logo` — khớp APK đang ship.

---

## Debug vs Play (tóm tắt)

| Kênh | Ký | PH cần gì | Trạng thái |
|---|---|---|---|
| Sideload debug APK | Debug keystore | Unknown sources / adb | Artifact có nhưng **icon corrupt trong package** |
| Play Internal/Closed | Upload key → `.aab` | Link tester Play | Docs sẵn; keystore local sẵn; **chưa có AAB build mới sau fix icon** |

Cảnh báo: cài debug rồi sau đó cài bản Play cùng `applicationId` với **key khác** → `INSTALL_FAILED_UPDATE_INCOMPATIBLE` — gỡ app debug trước khi nhận bản Play.

---

## EDITS_REQUIRED

### P0

1. **Clean rebuild artifact sau fix PNG** — `gradlew :app:clean :app:assembleDebug` (và `:app:bundleRelease` nếu đi Play). Xác nhận lại bằng extract/magic: mọi `ic_launcher` / `ic_logo` trong APK/AAB phải `89 50 4E 47`, không `FF D8 FF`. Thay `dist/hom-nay-choi-gi-0.1.0-dev-debug.apk` (và AAB play nếu có). **Không** phát lại bản 14:33 đang chứa JPEG giả PNG.
2. **Đồng bộ version trên artifact ship** — source đã `versionCode=3` / `versionName=0.1.0-play`; APK dist còn `2` / `0.1.0-dev`. Rebuild để tránh nhầm bản khi PH báo lỗi; cập nhật `BUILD_APK.md` cho khớp (hoặc giữ tên file `-dev` chỉ cho debug, ghi rõ versionName thật).

### P1

1. **Mipmap densitiess + adaptive icon** — hiện chỉ `mipmap-xxhdpi`; thêm mdpi/hdpi/xhdpi/xxxhdpi hoặc `mipmap-anydpi-v26` adaptive (foreground/background) cho launcher Play ổn định.
2. **Loại trùng / làm rõ `drawable/ic_logo.png`** — cùng bytes với launcher; giữ 1 nguồn hoặc document dùng cho in-app vs launcher.
3. **Hướng dẫn PH sideload** — giữ bước unknown sources trong `BUILD_APK.md`; ưu tiên link Internal testing khi AAB đã upload (tránh OEM chặn sideload).
4. **Ghi chú minSdk 26** trong note gửi PH (máy Android 8.0+); không hạ SDK trừ quyết định product.
5. **Sao lưu upload keystore** ngoài máy build (đã nhắc trong `PLAY_CONSOLE.md`) trước Closed testing.

---

## Top P0 (1 dòng)

**APK/dist đang ship vẫn đóng gói JPEG giả PNG trong icon — clean rebuild + thay artifact trước khi gửi PH / upload Play.**
