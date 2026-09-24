# Content QA — 2026-09-24

## Automated

- `core.test.mjs`: PASS
- `content.test.mjs`: PASS (sau khi sửa `act_sc_safe_030_18` duration 3→5 phút)
- activities: **90**
- primary quota 16/14/16/14/12/18: **OK**
- schema bắt buộc + picker 18/24/30/36m: **OK**

## Browser smoke (127.0.0.1:5179)

- Hôm nay: 3 thẻ (bé 23 tháng)
- Chi tiết → hoàn thành 😊 → đếm **1** hoạt động hôm nay
- Thư viện: **90 gợi ý**, lọc lĩnh vực, hiển thị 60/90
- Phát triển: tuần này **1** hoạt động

## Findings

- Đã sửa `act_sc_safe_030_18`: duration 3→**5** phút (web + seed + android assets).
- Đã đổi tiêu đề trùng `AES-01` → “Nguệch ngoạc sáp – nghe âm thanh” (tránh trùng `phy_03_nguech_sap_lon`).
- Không còn trùng tiêu đề.

## Kết luận

**PASS cho pilot web.** Nội dung vẫn `draft_unreviewed` — chưa chuyên gia duyệt.
