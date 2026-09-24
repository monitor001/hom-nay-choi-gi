# Hôm nay chơi gì? – App đồng hành giáo dục trẻ 0–6 tuổi (Android)

> Phiên bản tài liệu: 1.0 · Nền tảng: Android · Ngôn ngữ: Kotlin  
> Trạng thái: Đặc tả cho MVP (trẻ 18–36 tháng) và định hướng mở rộng lên 0–6 tuổi  
> Lưu tại: `ideas/BeGau/docs/SPEC_MVP.md` · Thử nghiệm phụ · **Không đổi hướng active**

---

## 1. Tổng quan sản phẩm

### 1.1. Mô tả
**Hôm nay chơi gì?** là app dành cho **phụ huynh**. Mỗi ngày app gợi ý 2–3 hoạt động ngắn (5–15 phút) để bố mẹ chơi và dạy con ngoài đời thật, dùng đồ có sẵn trong nhà. App cũng giúp theo dõi mốc phát triển, ghi nhật ký và nhắc nhở nhẹ nhàng về nếp sinh hoạt của bé.

### 1.2. Người dùng
| Nhóm | Mô tả | Nhu cầu chính |
|---|---|---|
| Bố mẹ (chính) | 25–40 tuổi, bận đi làm | Biết hôm nay chơi gì với con, nhanh gọn |
| Ông bà / người trông trẻ | Chăm bé ban ngày | Hướng dẫn dễ hiểu, chữ to |
| Giáo viên mầm non (giai đoạn 3) | Dùng ở lớp | Giáo án theo chủ đề |

### 1.3. Mục tiêu MVP
- Nhóm tuổi 18–36 tháng, khoảng 90 hoạt động
- 50–100 gia đình dùng thử trong 6–8 tuần
- Chỉ số chính: **≥ 3 hoạt động hoàn thành/tuần/gia đình**

---

## 2. Nguyên tắc thiết kế

1. **Màn hình cho bố mẹ, không phải cho bé.** Bé học qua tương tác thật. Phần nội dung cho bé xem (nếu có) luôn có hẹn giờ, không tự phát tiếp, không phần thưởng gây nghiện.
2. **30 giây để hiểu.** Mỗi hoạt động đọc xong trong 30 giây và dùng được bằng một tay.
3. **Không phán xét.** Không dùng câu kiểu "Bạn đã bỏ lỡ…" hay "Bé đang chậm hơn…". Luôn nhắc "mỗi bé có nhịp riêng".
4. **Offline-first.** Hoạt động, cẩm nang và nhắc nhở chạy được khi không có mạng.
5. **Không quảng cáo, không bán dữ liệu.**
6. **Không chẩn đoán y tế.** App chỉ gợi ý "nên hỏi bác sĩ" khi phù hợp.

---

## 3. Khung chương trình giáo dục

### 3.1. Lĩnh vực phát triển
Bám theo 5 lĩnh vực của Chương trình Giáo dục mầm non (Bộ GD&ĐT) và bổ sung 1 lĩnh vực:

| Mã | Lĩnh vực | Ví dụ mục tiêu (24–36 tháng) |
|---|---|---|
| `PHYSICAL` | Thể chất (vận động thô + tinh) | Nhảy bằng 2 chân, xếp chồng 6 khối |
| `COGNITIVE` | Nhận thức | Phân loại theo màu, nhận biết to/nhỏ |
| `LANGUAGE` | Ngôn ngữ | Nói câu 3–4 từ, kể lại việc đơn giản |
| `SOCIAL_EMOTIONAL` | Tình cảm – kỹ năng xã hội | Gọi tên cảm xúc, chơi luân phiên |
| `AESTHETIC` | Thẩm mỹ | Vẽ nguệch ngoạc, hát theo nhịp |
| `SELF_CARE` | Tự lập & sinh hoạt | Tự xúc ăn, tập bô, cất đồ chơi |

### 3.2. Nhóm tuổi
`0–3m`, `3–6m`, `6–9m`, `9–12m`, `12–18m`, `18–24m`, `24–30m`, `30–36m`, `3–4y`, `4–5y`, `5–6y`

### 3.3. Khuôn mẫu hoạt động
Xem đặc tả đầy đủ trong chat parent / phụ lục YAML (`id`, `domains`, `steps`, `safety`, `reviewed_by` bắt buộc không rỗng trước khi xuất bản).

---

## 4–14. Tính năng, nhắc nhở, màn hình, kiến trúc, dữ liệu, privacy, test, lộ trình, KPI

Chi tiết đầy đủ theo bản parent paste 1.0 (F1–F9 MVP; Reminder R1–R7; S01–S14; Kotlin/Compose/Hilt/Room/WorkManager; offline seed; không exact alarm; Data safety Play).

**Định nghĩa thành công MVP:** ≥ 3 hoạt động hoàn thành/gia đình/tuần. **Không** dùng thời gian trong app làm KPI.
