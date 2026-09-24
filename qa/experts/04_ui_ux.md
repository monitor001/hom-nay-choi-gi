# X4 — UI/UX phụ huynh · Gấu Con Web pilot

> Persona: thiết kế sản phẩm phụ huynh (Lan / Bà Hạnh — PRODUCT_DESIGN)  
> Ngày: 2026-09-24 · Workspace `ideas/BeGau`  
> Nguồn: `plans/WAVE4_EXPERT_QA.md`, `PRODUCT_DESIGN.md`, `CONSENSUS.md`, `web/index.html`, `styles.css`, `src/app.mjs`, `web/qa/CONTENT_QA_20260924.md`  
> Kiểm thử: server `http://127.0.0.1:5179/` **đang chạy** (HTTP 200); walkthrough chính bằng **code review** (browser MCP không gắn được tab trong phiên này).  
> **Không** sửa code — chỉ đề xuất. **Không** claim duyệt chuyên gia chính thức.

---

## 1. Luồng onboarding → today → detail → complete

### Quan sát (có bằng chứng trong code)

| Bước | Spec (PRODUCT_DESIGN / CONSENSUS) | Web hiện tại | Khớp? |
|---|---|---|---|
| S01 Chào mừng | 3 slide + chỉ báo trang + Bắt đầu | 1 panel hero, CTA **Bắt đầu** | Partial — đủ giá trị, thiếu carousel |
| S02 Hồ sơ | Tên + ngày sinh (+ ảnh/giới tính tuỳ chọn) | Tên + `type=date`; localStorage disclaimer | OK cho pilot |
| S03 Nhắc / quyền | Giải thích → giờ → Bật / **Để sau** | **Không có** — form submit → `onboardingDone` → S04 | Gap lớn vs IA |
| S04 Hôm nay | Chào + 2–3 thẻ + Đổi gợi ý + lối tắt nhật ký | Chào + thẻ + **Đổi gợi ý** per card; không lối tắt nhật ký | Core OK |
| S05 Chi tiết | Goal / đồ / bước / dễ–khó / an toàn / CTA sticky | Đủ text-first; CTA trong card, **không sticky** | Partial |
| S06 Phản hồi | 😊😐😕 + nhãn chữ → Xong → S04 | Có nhãn + `aria-pressed`; **Lưu** disabled đến khi chọn; về Today | OK |

**Luồng hạnh phúc (happy path):** Welcome → Profile → Today (3 thẻ) → Chi tiết → Hoàn thành → Lưu → Today (đếm tăng) — khớp CONTENT_QA smoke.

**Ma sát UX phụ huynh bận (Lan):**
- Sau hồ sơ nhảy thẳng Hôm nay là tốt (≤30s tới gợi ý) — **nhưng** mất cơ hội giải thích nhắc nhẹ (S03) mà CONSENSUS vẫn muốn app-side `DAILY_ACTIVITY` mặc định.
- Thẻ Today không tap-both-card: phải bấm **Xem chi tiết** (thêm 1 mục tiêu đụng; SPEC muốn thẻ = button).
- Sau hoàn thành: không feedback toast / đánh dấu thẻ đã làm — chỉ tăng “Đã hoàn thành N”; thẻ cũ vẫn hiện (picker không ẩn id đã complete trong ngày trừ khi rơi vào history14 — cùng ngày vẫn trong list nếu chưa exclude completed-today). *Giả định cần xác nhận picker:* `history14` có `completedDate` hôm nay → id bị loại khỏi *swap/re-pick*, nhưng `ensureTodayPicks` không force re-pick sau complete nên thẻ đã làm **vẫn nằm trên S04**.

### Đề xuất
1. Sau complete: đánh dấu thẻ “Đã chơi hôm nay” hoặc ẩn khỏi stack “cần làm” (P0 cảm nhận giá trị).
2. Thêm S03 tối giản web: 1 câu + **Để sau** (không xin Notification API nếu browser hạn chế) (P1).
3. Cả card Today là `button` / clickable region (P1).

---

## 2. A11y: cỡ chữ, contrast, TalkBack/keyboard, touch target

### Cỡ chữ
- Setting S14-lite trong **Thêm**: Vừa (1) / Lớn (1.15) / Rất lớn (1.3); `--font-scale` trên `:root` — **ổn**.
- CONSENSUS: mặc định **Vừa**; auto Lớn nếu system ≥ 1.3 → web **chưa** đọc `prefers` / devicePixel / OS text zoom.
- PRODUCT_DESIGN còn mức **Nhỏ** — web không có (chấp nhận được cho pilot ông bà; ưu tiên Lớn).

### Contrast (tính WCAG relative luminance)

| Cặp | Tỷ lệ | AA normal text |
|---|---:|---|
| ink `#1a2e28` / bg0 `#f3efe6` | 12.49:1 | Pass |
| ink-soft `#3d524a` / card | 8.19:1 | Pass |
| brand `#1f5c4a` / bg0 | 6.81:1 | Pass |
| nút primary `#f7fff9` / brand | 7.67:1 | Pass |
| warn `#9a5b1a` / banner `#fff7e8` | 5.08:1 | Pass (~AA) |
| danger / safety bg | 6.4:1 | Pass |

**Kết luận contrast:** nền sáng + chữ đậm đạt AA cho text chính. Không phát hiện purple/low-contrast AI default. Cần kiểm tay trên máy thật (OLED + True Tone) — giả định.

### TalkBack / keyboard
**Tốt:**
- `lang="vi"`, `aria-label` nav / filter toolbar / nhóm cảm xúc.
- `aria-current="page"`, `aria-pressed` filter & feedback.
- Feedback có **nhãn chữ** kèm emoji (khớp a11y PRODUCT_DESIGN).
- Icon nav `aria-hidden="true"`.

**Yếu / rủi ro pilot ông bà + TalkBack:**
- Mỗi tương tác `innerHTML` full tree → **mất focus** và đọc lại từ đầu (rất khó keyboard / screen reader).
- **Không** có `:focus-visible` / outline tùy chỉnh trong `styles.css`.
- Thẻ hoạt động Today không phải một control duy nhất; SR phải tìm nút “Xem chi tiết”.
- Nút **Lưu** `disabled` không có `aria-describedby` (“Chọn mức độ hợp trước”).
- Không skip-link; không `h1` ổn định theo route cho landmark (Today/Library dùng `h1` inline — OK một phần).

### Touch target (ước lượng @16px root, scale 1)
| Control | ~chiều cao | ≥48px? |
|---|---:|---|
| `.btn` primary | ~50px | Đạt |
| `.nav button` | ~43px | **Dưới** 48px |
| `.filters button` | ~30px | **Thấp** — khó cho Bà Hạnh |
| Feedback 3 cột | padding 0.85rem | Biên; chữ dài có thể wrap chật |

CTA chính không luôn ở nửa dưới màn trên S05 (phải cuộn hết bước) — trái a11y “một tay”.

---

## 3. Empty / error states · Đổi gợi ý · Thư viện 60/90

### Empty / error
| Tình huống | Hiện trạng | Đánh giá |
|---|---|---|
| Không pick được HĐ theo tuổi | Copy empty trên Today + gợi ý Thư viện | OK; hơi “seed/dev” |
| Filter Thư viện = 0 kết quả | `stack` rỗng, **không** empty copy | **P0 bug UX** |
| Fetch `activities.json` lỗi | Card “Không tải được nội dung” + hướng dẫn `serve.py` | OK pilot |
| Profile thiếu trường | HTML `required` | OK; thiếu copy “Chọn ngày sinh để gợi ý đúng tuổi” |
| Đổi gợi ý hết ứng viên | Picker có thể trả list ngắn / trùng hành vi phụ thuộc seed | Cần empty “Hôm nay chưa còn gợi ý mới…” (PRODUCT_DESIGN) |

### Đổi gợi ý
- Per-card **Đổi gợi ý** → `forceSwap` + `excludeExtraIds` gồm id đang hiện — **đúng hướng CONSENSUS** (không giới hạn cứng, tránh trùng id đã hiện).
- Không có “ghim 1 HĐ từ Thư viện thay 1 thẻ” (CONSENSUS §4.11) — chấp nhận P1.
- Không toast xác nhận đổi → phụ huynh có thể không chắc đã đổi (P2 polish).

### Thư viện 60/90
- CONTENT_QA xác nhận: **90** gợi ý, UI **slice(0, 60)** + dòng “Hiển thị 60 / N. Thu hẹp bộ lọc để xem thêm.”
- Với filter **Tất cả**, 30 HĐ **không bao giờ** tới được nếu không lọc lĩnh vực — copy “thu hẹp bộ lọc” **sai hướng** (cần mở rộng / “Xem thêm”, không thu hẹp).
- Không search; không chip thời lượng/đồ dùng (S07 spec) — pilot chấp nhận, ghi P1.

**P0 đề xuất copy/UI:** nút **Xem thêm** hoặc bỏ cap 60; empty filter: “Không thấy hoạt động khớp. Thử Tất cả hoặc lĩnh vực khác.”

---

## 4. Bottom nav & thiếu tab Nhật ký

### Hiện trạng
Bottom nav **4 cột:** Hôm nay · Thư viện · Phát triển · Thêm (`navHtml` trong `app.mjs`).

### Spec / CONSENSUS IA
**5 tab:** Hôm nay · Thư viện · Phát triển · **Nhật ký** · Thêm (S09 timeline ảnh / từ mới / khoảnh khắc).

### Hệ quả UX
- Sau S06 có ghi chú nhưng **không** đường vào nhật ký / ảnh — value F6 biến mất trên web.
- Tab **Phát triển** chỉ đếm tuần + placeholder mốc — đúng stub, nhưng cạnh “thiếu Nhật ký” làm IA lệch mental model phụ huynh (“chơi xong để đâu?”).
- Tab **Thêm** chỉ cỡ chữ + reset — thiếu hub Cẩm nang / Nhắc / Sức khỏe (OUT web pilot OK nếu ghi rõ).

**Khuyến nghị pilot phụ huynh thật:**  
- **Tối thiểu P0 messaging:** trong Progress hoặc Complete: “Nhật ký đầy đủ sẽ có trên app Android” (đã gần giống Progress).  
- **P1 IA:** thêm tab **Nhật ký** stub (empty state đúng PRODUCT_DESIGN copy) để không train user sai 4-tab trước khi Android 5-tab.

---

## 5. Visual / brand vs guideline frontend

Guideline Cursor frontend (tránh cliché): (1) purple gradient, (2) **warm cream + serif display + terracotta**, (3) broadsheet.

| Tiêu chí | Hiện trạng Gấu Con Web |
|---|---|
| Purple / indigo AI | **Không** — brand forest `#1f5c4a` |
| Cream + serif + terracotta | **Có dấu hiệu cliché (2):** bg `#f3efe6` ≈ cream, display **Fraunces**, accent `#c46b2c` terracotta |
| Font expressive | Be Vietnam Pro + Fraunces — tốt, không Inter/Roboto |
| Atmosphere | Radial green + warm gradient — có chiều sâu, không flat 1 màu |
| Brand hero | Welcome: headline mạnh; brand “Gấu Con” chủ yếu topbar nhỏ — trên Today brand **không** thắng headline (chấp nhận app shell) |
| Cards | Dùng nhiều card cho list/CTA — hợp interaction; hero Welcome **không** card — ổn |
| Emoji nav | ☀️📚🌱⋯ — tiện nhận diện pilot; guideline “tránh emoji” → P2 thay glyph/SVG |
| Motion | Chỉ `transform` nút + `prefers-reduced-motion` — tối thiểu, chưa 2–3 motion có chủ đích |
| Dark mode | Không (CONSENSUS = P1) — OK |

**Verdict brand:** An toàn, ấm, “mẹ & thiên nhiên” — **không** rơi purple-cliché; **có** lean cream/terracotta/serif gần mẫu AI phổ biến. Trước pilot marketing: cân nhắc đẩy nền greener (`#e7f0ea` làm base) hoặc accent berry/ink thay terracotta nếu muốn tách biệt visual — **P2**, không chặn dùng thử.

Disclaimer banner vàng + “chưa chuyên gia duyệt” — **đúng** WAVE4 / CONSENSUS (giữ).

Welcome dòng “Bám 5 lĩnh vực Chương trình GDMN…” dễ hiểu nhầm “chuẩn Bộ đã duyệt” trong khi seed `draft_unreviewed` → **P0 copy** làm mềm (“Tham khảo hướng 5 lĩnh vực GDMN + tự lập — nội dung nháp”).

---

## 6. P0 / P1 / P2 UI trước pilot phụ huynh thật

### P0 — làm trước khi đưa PH/ông bà thử tay (web)
1. **Thư viện:** bỏ/điều chỉnh cap 60 hoặc **Xem thêm**; sửa copy; empty state khi filter = 0.
2. **After-complete trên Today:** đánh dấu / tách “đã chơi hôm nay” vs còn lại (tránh cảm giác “làm xong chẳng đổi gì”).
3. **Touch:** tăng hit area nav + chip filter ≥ 44–48px; **CTA sticky** (hoặc fixed bottom) trên Chi tiết.
4. **Focus:** `:focus-visible` rõ; tránh mất ngữ cảnh SR nếu có thể (ít nhất không `outline: none`).
5. **Copy Welcome GDMN:** tránh overclaim duyệt / chuẩn Bộ.
6. **Ghi chú IA trên UI:** Nhật ký & S03 chưa có trên web pilot (1 dòng trong Thêm hoặc Progress) — quản lý kỳ vọng.

### P1 — trước Closed testing gắn Android IA
1. Tab **Nhật ký** stub + empty đúng tone.
2. S03 **Để sau** tối giản.
3. Thẻ Today = một control; ghim HĐ từ Thư viện.
4. Search + empty S07 chuẩn; font auto theo system scale.
5. S05 đọc ≤30s layout: goal → đồ → bước above-the-fold hơn (rút hierarchy).
6. `aria-describedby` khi Lưu disabled; thông báo live region khi đổi gợi ý / lưu xong.

### P2 — polish / brand
1. Onboarding 3 slide; motion nhẹ 2–3 chỗ.
2. Giảm cream/terracotta cliché nếu parent muốn brand riêng.
3. Icon nav không emoji; dark mode; ảnh nhật ký; Cẩm nang hub trong Thêm.

### Không chặn pilot
- Thiếu milestone checklist đầy đủ S08 (đã stub trung thực).
- Thiếu video (text-first CONSENSUS).
- 4-tab vs 5-tab nếu đã disclaimer rõ.

---

## Bảng đối chiếu nghiệm thu PRODUCT_DESIGN §7 (UI)

| # | Tiêu chí | Web pilot |
|---|---|---|
| 1 | Hiểu S05 ≤30s | Phụ thuộc độ dài bước seed — layout đủ; CTA cuối trang làm chậm “làm ngay” |
| 3 | Ông bà cỡ Lớn | Có setting; mặc định Vừa — nhắc PH bật Lớn khi thử ông bà |
| 4 | TalkBack thẻ S04 + 3 nút S06 | S06 OK; S04 thẻ chưa tối ưu |
| 5 | Từ chối notif vẫn dùng | N/A web (không S03) — Today/Library vẫn dùng |
| 6 | S08 không % so sánh | Pass (stub + copy đúng tone) |

---

## Báo cáo agent

```
STATUS: OK
SCOPE: X4 UI/UX review Gấu Con Web (IA, a11y, empty/error, nav, brand, P0–P2); không sửa code/seed
DONE:
  - Đọc WAVE4_EXPERT_QA, PRODUCT_DESIGN, CONSENSUS, CONTENT_QA, index.html, styles.css, app.mjs
  - Xác nhận server 5179 HTTP 200; walkthrough logic qua code (browser MCP không attach được)
  - Đối chiếu luồng S01–S06, a11y, 60/90, thiếu Nhật ký, brand vs cliché, xếp P0/P1/P2
  - Ghi file qa/experts/04_ui_ux.md
FILES:
  - qa/experts/04_ui_ux.md (created)
DEVIATIONS:
  - Không hoàn thành browser snapshot/click smoke trong phiên; bằng chứng UI lấy từ source + CONTENT_QA smoke đã ghi + HTTP 200
BLOCKERS: none
ERRORS: none
NEXT_FOR_PARENT:
  - Chờ X1–X3 rồi X5 gộp P0
  - Wave 5 ưu tiên: library 60/90 + empty filter, complete→Today feedback, touch/CTA sticky, copy GDMN
  - Quyết định: stub tab Nhật ký trên web hay chỉ disclaimer đến Android
```
