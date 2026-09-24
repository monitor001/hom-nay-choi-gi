# Pipeline tự động — Hôm nay chơi gì? (`ideas/BeGau`)

> Thử nghiệm phụ · **Không đổi hướng active** (`traffic-count-analyzer`)  
> Mục tiêu: Wave 1 (6 agent) → Wave 2 (tổng hợp/chọn lọc) → Wave 3 (scaffold phần mềm) chạy nối tiếp khi đủ điều kiện cổng.

---

## Wave 1 — Phân tích / thiết kế (đã giao)

| ID | Output | Cổng |
|---|---|---|
| A | `research/01_khung_chuong_trinh.md` | OK |
| B | `research/02_the_chat_nhan_thuc.md` | chờ |
| C | `research/03_ngon_ngu_xa_hoi_tham_my.md` | chờ |
| D | `research/04_tu_lap_sinh_hoat.md` | chờ |
| E | `plans/PRODUCT_DESIGN.md` | OK |
| F | `plans/TECH_ARCHITECTURE.md` | OK |

**Cổng Wave 1→2:** đủ 6 file trên và mỗi agent báo `STATUS: OK|PARTIAL` (FAIL → parent xử lý trước).

---

## Wave 2 — Tổ hợp tổng hợp & chọn lọc (3 agent)

Chỉ chạy khi cổng Wave 1 đạt. Mỗi agent **một file**, không sửa Wave 1.

| ID | Vai trò | Output |
|---|---|---|
| G1 — Consensus editor | Gộp xung đột A–F; chốt quyết định sản phẩm; danh sách mở còn lại | `plans/CONSENSUS.md` |
| G2 — Content curator | Chọn lọc / chuẩn hóa hoạt động YAML → seed JSON MVP (~90); loại trùng; cân phân bổ 16/14/16/14/12/18 | `content/seed/activities_mvp_draft.json` + `content/CURATION_LOG.md` |
| G3 — Build readiness | Checklist kỹ thuật + UX từ CONSENSUS; phạm vi Sprint 1–2 scaffold; rủi ro | `plans/BUILD_READY.md` |

**Tiêu chí chọn lọc nội dung (G2):**
1. Đủ trường khuôn SPEC; `reviewed_by: []` giữ nháp — **không** claim đã duyệt chuyên gia.
2. Đọc ≤30 giây; materials nhà VN; có `safety` khi rủi ro.
3. Tone không phán xét; không chẩn đoán y tế.
4. Không trùng mục tiêu với *Bé Gấu An Toàn* (an toàn chuyên sâu) — chỉ an toàn gắn hoạt động.
5. Dual-domain OK; tổng ~90; tối thiểu 2 lĩnh vực có thể pick/ngày.

**Cổng Wave 2→3:** `CONSENSUS.md` có mục **QUYẾT ĐỊNH CHỐT** (parent có thể override); `activities_mvp_draft.json` parse được; `BUILD_READY.md` = GO hoặc GO-WITH-RISKS.

---

## Wave 3 — Triển khai phần mềm (tự động sau cổng 2)

Phạm vi **scaffold MVP tối thiểu** (không đợi đủ 8 sprint SPEC):

1. Gradle multi-module skeleton theo `TECH_ARCHITECTURE.md`
2. `content-seed` nạp `activities_mvp_draft.json`
3. Onboarding S01–S02 + Today S04 (picker stub) + Activity detail S05 (text-first)
4. Room Child + Activity + ActivityLog; DataStore settings
5. Notification channels + `DAILY_ACTIVITY` WorkManager (R1–R4 tối thiểu)
6. README chạy local; unit test `NextFireCalculator` / `DailyPicker` nếu đã có code

**Cấm Wave 3:** đổi `AGENTS.md` / `PROJECT_STATE.md` hướng active; thu thập PII thừa; exact alarm; SDK quảng cáo; claim nội dung đã chuyên gia duyệt trên UI store.

**Sau Wave 3:** Closed testing / beta = quyết định parent (không tự đẩy Play Store production).

---

## Điều phối parent

1. Wave 1 xong → tự launch Wave 2 (3 agent song song).
2. Wave 2 xong → parent đọc `CONSENSUS.md` §QUYẾT ĐỊNH (2 phút) **hoặc** để pipeline GO-WITH-RISKS nếu không có blocker CRITICAL.
3. Wave 3: 1–2 agent code (scaffold) theo `BUILD_READY.md`.

File trạng thái sống: cập nhật bảng dưới mỗi lần chuyển wave.

| Wave | Trạng thái | Ghi chú |
|---|---|---|
| 1 | **OK** | Đủ 01–04 + PRODUCT_DESIGN + TECH_ARCHITECTURE |
| 2 | **OK** | G1 CONSENSUS · G2 seed 90 HĐ · G3 BUILD_READY |
| 3 | **PARTIAL** | Android `gaucon/` scaffold; chưa assembleDebug (thiếu JDK/SDK) |
| **Web pilot** | **OK** | `web/` port **5179** — kiểm thử trước app; logic age/picker map sang Android sau |
| **Wave 4 Expert QA** | **OK** | X1–X4 → X5 CONSENSUS_REVIEW → X6 APPROVE_WITH_EDITS |
| **Wave 5 Applier** | **OK** | P0 seed+UI áp xong; 97 HĐ; tests PASS; `06_APPLY_LOG.md` · vẫn `draft_unreviewed` |
