# Thư viện tài liệu phụ huynh — Hôm nay chơi gì?

## 0. Chính sách curate (chốt)

**Không sáng tác** thơ–truyện–bài hát mới thay nội dung chưa kiểm định.  
**Có:** đồng dao truyền thống (lời ngắn), hướng tìm sách/hát đã xuất bản, thư viện, link WHO/UNICEF/CDC/VBHN, ảnh gia đình, câu nói thực hành chăm sóc.

Xem `content/resources/README.md` · catalog **v5** (đồng dao/hát đã rà F1–F5; bài nhạc sĩ = tip).



---

## 1. Kết luận

**Có — cần bổ sung thư viện tài liệu kèm app**, không chỉ liệt kê “đồ dùng trong nhà”.

| Nhu cầu trong HĐ | Số HĐ liên quan (ước lượng quét) | Đã đóng gói sẵn trong app? |
|---|---:|---|
| Hát / nhạc / đồng dao | ~16 | **Chưa** — HĐ ghi “bài quen của gia đình” |
| Sách / tranh / ảnh | ~20 | **Chưa** — giả định PH có sách sẵn |
| Thơ / vè ngắn | vài HĐ LANGUAGE | **Chưa** — “thơ đã thuộc” |
| Truyện kể ngắn | ~0 rõ | **Thiếu** hẳn |
| Ăn vạ / kỹ năng xã hội (cẩm nang) | P1 | Chưa ship |

Nhiều PH (và ông bà) **không biết tìm ở đâu** bản quyền an toàn → dễ bỏ cuộc hoặc tải nội dung không phù hợp tuổi / có quảng cáo.

---

## 2. Nguyên tắc nguồn (bắt buộc)

| Được | Không được |
|---|---|
| Đồng dao / dân gian truyền miệng (ghi “truyền thống”) | Scan / PDF sách thiếu nhi đang bán |
| Lời bài do đội Hôm nay chơi gì? soạn | Paste lời bài hát đang bản quyền thương mại |
| Ảnh gia đình / đồ thật / tranh bé vẽ | Ảnh stock trẻ em nhạy cảm / chưa có phép |
| Link nguồn **miễn phí hợp pháp** (Wikimedia, thư viện số công, YouTube kênh giáo dục có license rõ) | Deep-link video trẻ em có quảng cáo cá nhân hóa (Play Families) |
| Prompt “tự làm tranh A4” | Claim “đầy đủ thư viện NXB X” |

UI: mỗi mục ghi **Nguồn / bản quyền**; vẫn `draft_unreviewed`.

---

## 3. Cấu trúc thư viện đề xuất

```
content/resources/
  catalog.json          # thơ, đồng dao, bài hát, gợi ý tranh, truyện siêu ngắn
  activity_links.json   # activityId → [resourceId]
```

Trong app (web → sau Android):

| Tab / mục | Nội dung |
|---|---|
| **Tài liệu** | Duyệt theo loại: Đồng dao · Hát ru/chơi · Thơ · Tranh · Truyện 1 phút |
| Trong **Chi tiết HĐ** | Khối “Tài liệu gợi ý” nếu có link |
| Offline | Lời + hướng dẫn in/PDF nhẹ; audio tùy chọn P1 |

---

## 4. Gói MVP tài liệu (Wave Resource-1)

| Loại | Số lượng MVP | Ví dụ |
|---|---:|---|
| Đồng dao truyền thống (lời đầy đủ) | 6–8 | Nu na nu nống, Chi chi chành chành, Hom na… (bản gia đình) |
| Hát ru / hát chơi (lời + điệu gợi ý) | 4–6 | Ru con truyền thống; bài “cất đồ” 8 câu do Hôm nay chơi gì? soạn |
| Thơ/vè 4–6 câu (Hôm nay chơi gì? soạn) | 6 | Cơ thể, màu, mưa, chào ông bà |
| Gợi ý tranh / sách | 8–10 | Checklist chọn sách dày trang; bộ “in tranh A4” mô tả; Wikimedia search tip |
| Truyện 60–90 giây | 4 | Ông mặt trời, Đôi dép, Mưa trên sân (soạn mới) |

**Không** thay *Bé Gấu An Toàn* (cháy/điện/nước).

---

## 5. Map nhanh HĐ → loại tài liệu

- Hát / nhạc: `AES` hát ru, nhún nhạc, điệu bộ; `SOC` cất đồ cùng nhạc; `LANG` đồng dao  
- Tranh/sách: `LANG` sách tranh, hỏi–đáp tranh; `AES` trưng bày; `phy` lật sách  
- Thơ: HĐ có “thơ/vè đã thuộc”  
- XH: chia sẻ / đến lượt — tài liệu = **câu nói mẫu** (đã có) + tranh tình huống P1  

Chi tiết id: `qa/resource_library_scan.json`, `content/resources/activity_links.json`.

---

## 6. Lộ trình

| Bước | Việc |
|---|---|
| **R0 (ngay)** | `catalog.json` gói lõi + mục **Tài liệu** trên web + gắn vài HĐ |
| **R1** | Audio TTS / thu âm tự nguyện; PDF in 1 trang |
| **R2** | CMS Strapi đăng tài liệu đã duyệt; lọc tuổi |
| **R3** | Bản quyền thương mại (nếu có NXB hợp tác) — tách gói Premium |

---

## 7. Việc KHÔNG làm

- Không nhúng Spotify/YouTube playlist mặc định có ads  
- Không lưu ảnh khuôn mặt trẻ lên server ở MVP  
- Không gọi là “thư viện chuẩn Bộ”  

---

*Sau khi có catalog: cập nhật Chi tiết HĐ + tab Tài liệu trên `web/`.*
