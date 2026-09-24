# Hôm nay chơi gì?

App **dành cho phụ huynh**: mỗi ngày gợi ý 2–3 hoạt động ngắn (5–15 phút) để chơi cùng con ngoài đời thật (18–36 tháng MVP).

> Nội dung seed = **`draft_unreviewed`** — chưa chuyên gia người thật / Bộ GD&ĐT duyệt. Không chẩn đoán y tế. Không thu dữ liệu lên máy chủ (web pilot: localStorage).

**Repo:** https://github.com/monitor001/hom-nay-choi-gi  
**Web (GitHub Pages):** https://monitor001.github.io/hom-nay-choi-gi/

Thử nghiệm phụ trong `ideas/BeGau` (cùng hệ sinh thái *Bé Gấu An Toàn* nhưng sản phẩm riêng). Không thay hướng sản phẩm khác của monorepo cha.

## Cấu trúc

| Thư mục | Mô tả |
|---|---|
| `web/` | **Pilot kiểm thử** — HTTP tĩnh, port **5179** |
| `content/seed/` | JSON ~97 hoạt động (MVP) |
| `gaucon/` | Scaffold Android (Kotlin/Compose) — cần JDK 17 + SDK |
| `plans/` | CONSENSUS, kiến trúc, pipeline |
| `research/` | Nghiên cứu 6 lĩnh vực GDMN + SELF_CARE |
| `qa/` | QA kỹ thuật + báo cáo chuyên gia nội bộ (persona AI) |

## Chạy web (nhanh)

```bat
cd web
python serve.py
```

Mở http://127.0.0.1:5179/

```bat
cd web
node tests/core.test.mjs
node tests/content.test.mjs
```

## Android

Xem `gaucon/README.md`. Chưa `assembleDebug` trên máy thiếu JDK/SDK.

## Triển khai

Xem **[docs/DEPLOYMENT_PLAN.md](docs/DEPLOYMENT_PLAN.md)**.

## Giấy phép / thương hiệu

Prototype nội bộ. Brand *Hôm nay chơi gì?* — chưa đăng ký công bố store. (Thư mục kỹ thuật `gaucon/` / `BeGau` giữ tên cũ.)
