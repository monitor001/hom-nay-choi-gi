# Kế hoạch phân tích & thiết kế — Gấu Con (Android)

## Bối cảnh
App **dành cho phụ huynh**, gợi ý hoạt động offline ngoài đời thật. MVP: trẻ **18–36 tháng**, ~90 hoạt động.
Thử nghiệm phụ trong `ideas/BeGau`. **Không đổi hướng active** (`traffic-count-analyzer`).
Chưa code Android cho đến khi parent chốt CONSENSUS + PRODUCT_DESIGN.

## Khung giáo dục (bắt buộc)
Bám 5 lĩnh vực Chương trình GDMN Bộ GD&ĐT + lĩnh vực bổ sung:

| Mã | Lĩnh vực |
|---|---|
| `PHYSICAL` | Thể chất (vận động thô + tinh) |
| `COGNITIVE` | Nhận thức |
| `LANGUAGE` | Ngôn ngữ |
| `SOCIAL_EMOTIONAL` | Tình cảm – kỹ năng xã hội |
| `AESTHETIC` | Thẩm mỹ |
| `SELF_CARE` | Tự lập & sinh hoạt (ăn, ngủ, vệ sinh, an toàn) |

Mốc phát triển: tham chiếu thêm WHO Child Growth / CDC Learn the Signs — **không chẩn đoán y tế**.

## Luồng subagent

| Agent | Phạm vi | File output | Trạng thái |
|---|---|---|---|
| A — Khung chương trình | Map Bộ GD&ĐT ↔ 6 mã; nhóm tuổi; nguyên tắc “không phán xét” | `research/01_khung_chuong_trinh.md` | **OK** |
| B — Thể chất + Nhận thức | Mục tiêu 18–36m; mẫu hoạt động; mốc checklist | `research/02_the_chat_nhan_thuc.md` | **OK** |
| C — Ngôn ngữ + XH-TC + Thẩm mỹ | Mục tiêu 18–36m; mẫu hoạt động; mốc checklist | `research/03_ngon_ngu_xa_hoi_tham_my.md` | **OK** |
| D — Tự lập & sinh hoạt | Ăn/ngủ/vệ sinh/an toàn; gắn reminder ROUTINE_* | `research/04_tu_lap_sinh_hoat.md` | **OK** |
| E — Thiết kế sản phẩm UX | IA màn hình S01–S14; tone phụ huynh; DoD nội dung | `plans/PRODUCT_DESIGN.md` | **OK** |
| F — Kiến trúc kỹ thuật | Module Kotlin; Reminder; Room; privacy Play | `plans/TECH_ARCHITECTURE.md` | **OK** |

**Neo từ A (B/C/D bám):** phân bổ 90 HĐ MVP `16/14/16/14/12/18` (PHYS/COG/LANG/SE/AES/SELF); `ageMonths` §2.2; publish chỉ khi `reviewed_by` ≠ ∅; nhà trẻ CT gộp TC–XH+thẩm mĩ — app vẫn tách mã.

**Pipeline tự động:** xem `plans/PIPELINE_AUTO.md` + `plans/WAVE2_BRIEFS.md`.  
Wave 1 đủ cổng → Wave 2 (G1 Consensus / G2 Curator / G3 Build-ready) → Wave 3 scaffold phần mềm.

## Quy tắc chung cho mọi child
- Chỉ ghi **đúng 1 file** được giao; không sửa `AGENTS.md` / `PROJECT_STATE.md` / file sibling.
- **Không code** app Android; không bịa số liệu doanh thu / giá.
- Nội dung hoạt động = **nháp cần chuyên gia duyệt**; ghi rõ `reviewed_by: []`.
- Phân biệt: nguồn chính thức / tham khảo / giả định chưa xác minh.
- Báo cáo cuối bắt buộc: `STATUS / SCOPE / DONE / FILES / DEVIATIONS / BLOCKERS / ERRORS / NEXT_FOR_PARENT`.

## Đặc tả nguồn
`docs/SPEC_MVP.md` (bản 1.0 do parent cung cấp).
