# Hôm nay chơi gì? — Web pilot (kiểm thử trước app)

Prototype **web dành cho phụ huynh**: gợi ý 2–3 hoạt động/ngày từ seed ~90 HĐ (18–36 tháng), localStorage, không cloud.

> Nội dung `draft_unreviewed` — **chưa chuyên gia duyệt**. Không chẩn đoán y tế.

Thử nghiệm phụ trong `ideas/BeGau`. **Không đổi hướng active** AI Kiem Tien. Android scaffold vẫn ở `../gaucon/` để chuyển đổi sau.

## Chạy

```bat
cd /d "D:\AI Kiem Tien\ideas\BeGau\web"
python serve.py
```

Mở http://127.0.0.1:5179/

LAN: `python serve.py 0.0.0.0 5179`

## Kiểm thử nhanh

1. Bắt đầu → nhập tên gọi + ngày sinh (bé ~18–36 tháng).
2. Hôm nay: 2–3 thẻ → Chi tiết → Hoàn thành (😊/😐/😕) → nhận **Gấu Xu**.
3. Tab **Cửa hàng**: lưới quà, đổi GX (đồ ăn ẩn mặc định).
4. **Tài liệu**: chạm tranh → xem **toàn màn hình** cho bé.
5. Đổi gợi ý; Thêm → cỡ chữ / xóa dữ liệu.

## Kiểm thử

```bat
node tests/core.test.mjs
node tests/content.test.mjs
node tests/gx.test.mjs
```

Báo cáo gần nhất: `qa/CONTENT_QA_20260924.md`.

## Map sang Android sau này

| Web | Android (`gaucon/`) |
|---|---|
| `src/age.mjs` | `domain/.../AgeMonths.kt` |
| `src/picker.mjs` | `domain/.../DailyPicker.kt` |
| `content/activities.json` | `content-seed/.../content_seed.json` |
| localStorage | Room + DataStore |
| Web notifications (chưa) | WorkManager R1–R4 |

Port mặc định: **5179** (5175 Nút Giao · 5176 ATGT · 5177 An Toàn · 5178 Lời Hay).
