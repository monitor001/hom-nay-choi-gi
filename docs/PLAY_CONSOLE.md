# Xuất bản Google Play — Hôm nay chơi gì?

> Artifact agent chuẩn bị: **AAB đã ký** + checklist.  
> **Bạn** đăng nhập [Play Console](https://play.google.com/console) để tạo app / upload (agent không đăng nhập hộ).

## Artifact hiện tại

| Mục | Giá trị |
|---|---|
| File | `dist/hom-nay-choi-gi-0.1.9-play.aab` |
| applicationId | `com.gaucon.app` |
| versionName | `0.1.9` |
| versionCode | `12` |
| Signing | upload-keystore (máy local, gitignore) |
| Khuyến nghị track | **Internal testing** trước — chưa Production |

## Build lại AAB

```bat
cd /d "D:\AI Kiem Tien\ideas\BeGau\gaucon"
set JAVA_HOME=C:\Program Files\Microsoft\jdk-17.0.20.101-hotspot
set ANDROID_HOME=%LOCALAPPDATA\Android\Sdk
gradlew.bat :app:bundleRelease
copy /Y app\build\outputs\bundle\release\app-release.aab "..\dist\hom-nay-choi-gi-0.1.9-play.aab"
```

## Bước trên Play Console (làm tuần tự)

### A. Tạo app (một lần)

1. [play.google.com/console](https://play.google.com/console) → **Create app**.
2. Tên: **Hôm nay chơi gì?** · App · Free · Declarations theo form.
3. **Dashboard** hoàn thành mục bắt buộc (Policy, App access, Ads, Content rating, Target audience, Data safety, Store listing).

### B. Store listing (nháp gợi ý)

- **App name:** Hôm nay chơi gì?
- **Short description (≤80):** Gợi ý chơi cùng con mỗi ngày — nhật ký, Gấu Xu, nhắc phụ huynh.
- **Full description:**  
  Ứng dụng đồng hành phụ huynh: gợi ý hoạt động theo độ tuổi, thư viện tài liệu, phát triển/mốc, nhật ký, sổ thưởng Gấu Xu (đổi quà do PH chuẩn bị). Nội dung prototype giáo dục, chưa chuyên gia duyệt đầy đủ — không thay lời khuyên y tế. Dữ liệu lưu trên máy, không bán, không quảng cáo.
- **Privacy policy:** https://monitor001.github.io/hom-nay-choi-gi/privacy.html
- **Category:** Parenting / Education
- **Contact email:** email Play của bạn
- **Graphics:** icon 512×512 (từ `ic_launcher` / logo), ≥2 screenshot điện thoại (chụp từ máy hoặc emulator)

### C. Data safety / Ads / Audience

| Câu hỏi | Trả lời gợi ý |
|---|---|
| Ads | **No** |
| Data collected | Không thu thập / không chia sẻ (local-only Room + DataStore) |
| Target age | Phụ huynh / 18+ (không Families kid-primary ở MVP) |
| Content rating | Questionnaire Parenting / Education |

### D. Upload Internal testing

1. **Testing → Internal testing → Create release**.
2. Upload `dist/hom-nay-choi-gi-0.1.9-play.aab`.
3. Release name: `0.1.9` · Notes: Internal — shop grid, nhắc nền AlarmClock, Gấu Xu.
4. **Review release → Start rollout to Internal testing**.
5. **Testers** → thêm Gmail → copy **join link** → gửi PH.
6. Họ mở link → Accept → Cài từ Play (không cần nguồn không xác định).

### E. Closed / Production (sau khi Internal ổn)

- Closed: thêm form / nhiều tester.  
- Production: chỉ khi listing + policy xanh + bạn chốt công khai.

## Policy checklist (MVP)

- [x] Không quảng cáo / không Ad ID  
- [x] Nhắc bằng `setAlarmClock` (không `SCHEDULE_EXACT_ALARM`)  
- [x] Disclaimer nội dung nháp trên UI  
- [x] Privacy URL Pages  
- [ ] Screenshot + icon 512 trên Console (bạn chụp/upload)  
- [ ] Content rating + Data safety form trên Console  

## Sao lưu keystore

`gaucon/upload-keystore.jks` + mật khẩu trong `keystore.properties` — **bắt buộc backup offline**. Mất = không cập nhật cùng `com.gaucon.app`.

## Không làm được từ agent

Đăng nhập Console, thanh toán phí tài khoản (~25 USD một lần), upload AAB qua UI, điền form policy/listing, mời tester.
