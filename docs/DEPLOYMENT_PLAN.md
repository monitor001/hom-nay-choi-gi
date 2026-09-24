# Kế hoạch triển khai — Hôm nay chơi gì?

> Phiên bản: 1.0 · 2026-09-24 · Repo GitHub (web-first → Android)  
> Trạng thái nội dung: `draft_unreviewed` · X6 nội bộ đã APPROVE_WITH_EDITS (persona AI, **không** thay duyệt người thật)

---

## 1. Mục tiêu triển khai

| Giai đoạn | Sản phẩm | Mục tiêu |
|---|---|---|
| **D0** | GitHub + web Pages/static | Lưu phiên bản, demo phụ huynh nội bộ |
| **D1** | Web pilot công khai (static) | 20–50 PH thử 2–4 tuần; đo ≥3 HĐ/tuần |
| **D2** | Android Closed testing | 50–100 gia đình; cùng seed |
| **D3** | Soft launch Play (nếu KPI đạt) | Local-only hoặc cloud opt-in (P1) |

**Không** dùng thời gian trong app làm KPI. Thành công = hoạt động hoàn thành ngoài đời thật.

---

## 2. D0 — Repo GitHub (tuần này)

- [x] Tách `ideas/BeGau` thành repo độc lập
- [x] Push `main` lên `github.com/monitor001/hom-nay-choi-gi`
- [x] GitHub Pages từ nhánh `gh-pages` (nội dung `/web`) → https://monitor001.github.io/hom-nay-choi-gi/
- [ ] CI nhẹ: `node web/tests/*.mjs` trên push (cần OAuth scope `workflow`)
- [x] README + disclaimer draft_unreviewed

**Phạm vi commit:** `web/`, `content/`, `plans/`, `research/`, `qa/`, `gaucon/` (scaffold), `docs/`, `scripts/`. Không commit `local.properties`, keystore, `.env`.

---

## 3. D1 — Web public (1–2 tuần)

### 3.1. Hosting đề xuất

| Lựa chọn | Ưu | Nhược |
|---|---|---|
| **GitHub Pages** (`/web` → root Pages) | Miễn phí, gắn repo | HTTPS + custom domain cấu hình thêm |
| Cloudflare Pages | CDN nhanh VN/Asia | Thêm tài khoản |
| Netlify / Vercel static | Preview PR | Ít cần nếu đã có GH |

**Khuyến nghị D1:** GitHub Pages từ nhánh `main` / folder `web`, URL dạng `https://<user>.github.io/gau-con/`.

### 3.2. Checklist trước khi public

- [ ] Banner disclaimer cố định (đã có; không tắt được banner mốc)
- [ ] Không claim “chuẩn Bộ / đã chuyên gia duyệt”
- [ ] Privacy 1 trang: chỉ localStorage, không analytics bán dữ liệu
- [ ] `content_status: draft_unreviewed` trong seed
- [ ] Form feedback phụ huynh (Google Form / Typeform) — ngoài app
- [ ] Soft gate: link private trước 1 tuần, rồi public

### 3.3. Đo lường (thủ công D1)

| Chỉ số | Cách |
|---|---|
| Hoàn thành ≥3 HĐ/tuần | PH tự báo / phỏng vấn |
| Lần đầu hoàn thành &lt;24h | Câu hỏi pilot |
| Tắt app vì phiền | Phỏng vấn |

Analytics SDK: **tắt** ở D1 hoặc chỉ đếm pageview ẩn danh tối giản (không PII trẻ).

---

## 4. D2 — Android Closed testing (song song / sau D1 2–4 tuần)

### 4.1. Điều kiện vào D2

- Pilot web có tín hiệu KPI hoặc ≥10 PH phỏng vấn hữu ích
- JDK 17 + Android Studio trên máy build
- Seed đồng bộ từ `content/seed/` (script sync đã có trong Wave 5)

### 4.2. Việc kỹ thuật

| Sprint | Việc |
|---|---|
| A | Mở `gaucon/` trong AS; `assembleDebug`; sửa lỗi compile |
| B | Nạp seed 97 HĐ; parity màn S01–S05 với web |
| C | Reminder `DAILY_ACTIVITY` R1–R4; xin quyền đúng luồng |
| D | Internal / Closed testing track trên Play Console |
| E | Data safety form; privacy URL (GitHub Pages) |

### 4.3. Play policy

- Target audience: **phụ huynh** (không Families kid-primary ở MVP)
- Không exact alarm; không quảng cáo
- Khi thêm thư viện cho bé (P1): rà Families Policy lại

---

## 5. D3 — Soft launch (sau beta 6–8 tuần)

- Chỉ khi ≥3 HĐ/tuần đạt ngưỡng CONSENSUS và có **duyệt người thật** một phần seed
- Quyết định cloud (Supabase vs Firebase) — mặc định vẫn local-only nếu chưa cần
- Premium / Billing: sau khi retention ổn

---

## 6. Lộ trình lịch (gợi ý)

| Tuần | Việc |
|---:|---|
| 0 | Repo GitHub + Pages staging |
| 1 | Soft link 10 PH nội bộ |
| 2–3 | Sửa P1 từ feedback (cẩm nang, Nhật ký stub, phủ 18–24 nếu cần) |
| 4 | Pages public / form feedback |
| 5–8 | Android assemble + Closed testing |
| 9–16 | Beta 50–100 hộ; đánh giá KPI |
| 17+ | Soft launch hoặc pivot nội dung |

---

## 7. Rủi ro & giảm thiểu

| Rủi ro | Giảm thiểu |
|---|---|
| Hiểu nhầm “đã duyệt Bộ” | Copy + banner; không SEO overclaim |
| Hóc / an toàn | Đã P0 Wave 5; giữ ranh giới *Bé Gấu An Toàn* |
| OEM chặn notification | Màn hướng dẫn hãng (D2) |
| Scope creep Android | Web đo nhu cầu trước khi đủ 8 sprint SPEC |

---

## 8. Việc ngay sau khi có URL GitHub

1. Bật Actions: chạy `node web/tests/core.test.mjs` + `content.test.mjs`
2. Settings → Pages → Deploy from `/web`
3. Thêm `web/privacy.html` ngắn (1 trang)
4. Chia sẻ link pilot + form feedback
5. Không tự nâng `reviewed_by` trên store listing

---

## 9. Liên kết nội bộ

- `plans/CONSENSUS.md` — quyết định sản phẩm  
- `plans/BUILD_READY.md` / `gaucon/README.md` — Android  
- `qa/experts/05_SENIOR_EDU_SIGN_OFF.md` — ký nội bộ AI  
- `qa/experts/06_APPLY_LOG.md` — Wave 5 đã áp
