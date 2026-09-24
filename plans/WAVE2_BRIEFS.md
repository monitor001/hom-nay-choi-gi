# Wave 2 — Briefs tổ hợp tổng hợp (G1–G3)

Kích hoạt khi cổng Wave 1 mở (đủ `research/01–04` + `PRODUCT_DESIGN` + `TECH_ARCHITECTURE`).

## G1 — Consensus editor → `plans/CONSENSUS.md`
- Đọc cả 6 output Wave 1.
- Gộp: value prop, 6 domain, ageMonths, tone, IA, stack, reminder R1–R7.
- Mục **XUNG ĐỘT & CÁCH XỬ** (nếu B/C/D lệch phân bổ A).
- Mục **QUYẾT ĐỊNH CHỐT** (bullet parent-ready): backend tạm local-only; video optional; font scale; analytics.
- Mục **LOẠI BỎ / HOÃN** (P1/P2).
- Không code; không sửa Wave 1.

## G2 — Content curator → `content/seed/activities_mvp_draft.json` + `content/CURATION_LOG.md`
- Parse YAML hoạt động từ 02/03/04 (+ khung 01).
- Chuẩn hóa schema JSON thống nhất; id ổn định; domains enum.
- Cân ~90 theo 16/14/16/14/12/18; cắt trùng; sửa safety thiếu.
- `reviewed_by: []`, `content_status: "draft_unreviewed"`.
- CURATION_LOG: số giữ/bỏ/sửa + lý do.

## G3 — Build readiness → `plans/BUILD_READY.md`
- GO | GO-WITH-RISKS | NO-GO.
- Phạm vi scaffold Sprint 1–2 cụ thể (module + màn).
- Quyết định kỹ thuật mặc định nếu parent im lặng  (local-only, no cloud auth MVP).
- Checklist DoD trước khi merge scaffold.

Sau G1–G3 OK → Wave 3 scaffold Android theo PIPELINE_AUTO.md.
