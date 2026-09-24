# Đội chuyên gia Đồng dao & Hát dân gian VN — Hiến chương

> Workspace: `ideas/BeGau` · App: **Hôm nay chơi gì?**  
> Mục tiêu: kiểm tra **chính thống** corpus đồng dao / hát ru / dân ca trong `content/resources/catalog.json`  
> Trạng thái: **persona AI nội bộ** — **không** thay chuyên gia văn hóa dân gian / nhà nghiên cứu người thật. Seed vẫn `draft_unreviewed`.

## Phạm vi

- File: `qa/experts/folk/FOLK_CORPUS_FOR_REVIEW.md` (+ `.json`)
- Chỉ mục `category` ∈ {`dong_dao`, `hat`} và tip tìm bài liên quan (`tip_bai_ba_me`, `tip_hat_ru_bac`)
- Không sửa `AGENTS.md` / `PROJECT_STATE.md` / hướng sản phẩm cha

## Vai trò (4 chuyên gia + 1 tổng hợp)

| ID | Vai trò | Trọng tâm |
|---|---|---|
| **F1** | Văn hóa dân gian / văn bản học | Khớp lời truyền miệng quen thuộc; phát hiện **xuyên tạc / bịa lời / trộn bản**; ghi biến thể miền |
| **F2** | Miền Bắc & ngữ cảnh địa phương | Đúng “miền Bắc” vs Nam Bộ/Trung; tag `region` có chính xác không |
| **F3** | Sư phạm & phù hợp tuổi 18–36 tháng | Nội dung đáng sợ / bạo lực / hóc / không phù hợp; rút ngắn lời khi cần |
| **F4** | Bản quyền & phân loại nguồn | `folk_traditional` vs bài nhạc sĩ / thương mại; cấm chép lời bản quyền; tip-only đúng chỗ |
| **F5** | Tổng biên tập folk | Hợp nhất P0/P1; bảng EDITS_REQUIRED cho Applier |

## Thang verdict từng mục

- `AUTHENTIC` — lời quen, ghi nhận biến thể OK  
- `VARIANT_OK` — biến thể chấp nhận được nếu ghi chú nguồn/biến thể  
- `FIX_LYRICS` — sửa lời / cắt câu sai  
- `RECLASSIFY` — đổi license/region/category hoặc chuyển tip  
- `REMOVE_OR_REPLACE` — bịa / xuyên tạc / không phù hợp / rủi ro bản quyền  
- `AGE_GATE` — giữ nhưng nâng ageHint / cảnh báo PH  

## Deliverables (mỗi F1–F4)

Ghi file riêng dưới `qa/experts/folk/`:

1. `01_van_ban_hoc.md` (F1)  
2. `02_mien_bac.md` (F2)  
3. `03_su_pham_tuoi.md` (F3)  
4. `04_ban_quyen_nguon.md` (F4)  
5. `00_CONSENSUS_FOLK.md` (F5 — sau khi có F1–F4)

Mỗi báo cáo F1–F4 gồm:

```
STATUS: OK|PARTIAL|FAIL
SCOPE / DONE / FILES / DEVIATIONS / BLOCKERS / ERRORS / NEXT_FOR_PARENT
```

+ bảng mọi `id` đã duyệt với verdict + ghi chú ngắn + **EDITS_REQUIRED** (nếu có).

## Nguyên tắc

1. **Không sáng tác** lời mới thay thế nếu chưa chắc — ưu tiên cắt / ghi “hỏi ông bà” / chuyển tip.  
2. Giữ *Bé Gấu An Toàn* ngoài phạm vi.  
3. Ưu tiên miền Bắc đúng yêu cầu PH; đánh dấu rõ mục Nam Bộ.  
4. Nội dung nhạy (xáo măng, chết, hù dọa): F3 quyết AGE_GATE hoặc cắt.  
5. Sau F5 CONSENSUS: parent/applier mới sửa `catalog.json` + sync web.
