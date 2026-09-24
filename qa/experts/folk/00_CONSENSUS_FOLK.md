# F5 — CONSENSUS FOLK (Đồng dao & hát dân gian)

> App: **Hôm nay chơi gì?** · Ngày: 2026-09-24  
> Nguồn: F1 `01_van_ban_hoc.md` · F2 `02_mien_bac.md` · F3 `03_su_pham_tuoi.md` · F4 `04_ban_quyen_nguon.md`  
> Persona AI nội bộ — **không** claim duyệt nhà nghiên cứu người thật. Catalog sau apply vẫn `draft_unreviewed`.

```
STATUS: OK
SCOPE: Hợp nhất P0/P1 từ F1–F4; bảng EDITS cho Applier
DONE: CONSENSUS + ủy quyền Applier chạy apply_folk_consensus.py
FILES: qa/experts/folk/00_CONSENSUS_FOLK.md (file này)
DEVIATIONS: Khi F1 AUTHENTIC vs F3 AGE_GATE xung đột (chi_chi chết trương; ca_con_co_dem xáo măng) → ưu tiên an toàn tuổi toddler: cắt / AGE_GATE, giữ ghi chú di sản.
BLOCKERS: Không
ERRORS: Không
NEXT_FOR_PARENT: Chạy applier; sync web catalog; mở lại F1 spot-check nếu cần.
```

## Nguyên tắc ưu tiên

1. **F4 bản quyền** thắng khi bài có nhạc sĩ → tip-only, **xoá lời**.  
2. **F1 chính thống** thắng khi lời bịa / xuyên tạc → REMOVE hoặc FIX về dị bản đã ghi nhận.  
3. **F3 tuổi** thắng trên bản đầy đủ có chết/xáo/gãy → cắt hoặc AGE_GATE trong band 18–36m.  
4. **F2 miền** → sửa `region` (nhiều mục «Miền Bắc» → «Cả nước»); `hat_ly_cay_bong` giữ Nam Bộ.

## EDITS_REQUIRED (Applier)

### P0 — Xóa mục bịa / rủi ro (không thay bằng thơ «cổ» mới)

`dd_hoa_buoi`, `dd_ong_trang`, `dd_bit_mat`, `dd_co_chan_chi`, `dd_chim_da_da`, `dd_ong_troi`, `dd_nha_co_ai`, `dd_di_cho_me`, `dd_ba_ke`, `dd_ong_bao`, `dd_em_be`, `hat_ru_me_yeu`, `hat_ru_ba_ke`, `hat_ly_cay_da`, `hat_chim_sau`, `ca_me_cha_nuoi`

### P0 — Bản quyền: chuyển tip, xoá lyrics

- `hat_dan_ga` → tip_find (Filippenko / Việt Anh — hỏi cô giáo)  
- `hat_dan_vit` → tip_find (Mộng Lân — hỏi cô giáo)

### P0 — FIX_LYRICS (theo F1)

`dd_nu_na`, `dd_chi_chi`, `dd_keo_cua`, `dd_thang_cuoi`, `dd_hom_na`, `dd_mot_hai_ba`, `hat_cai_ngu`, `hat_gio_dua`

### P0 — AGE / cắt (F3)

- `ca_con_co_dem`: chỉ 2 câu đầu; ageHint ≥36 tháng (nghe PH); ghi chú dừng trước «xáo măng»  
- `dd_thang_bom`: rút 4 câu mở; ageHint 30–36 (nghe)  
- `dd_chi_chi`: sau FIX F1 vẫn có «chết trương» → ageHint 30–36 (nghe PH) + howToUse cảnh báo hình ảnh  
- `dd_keo_cua`: bản F1 (ông thợ) đã hết «gãy chân» → ageHint có thể 18–36

### P1 — Region (F2)

Đổi nhiều id từ «Miền Bắc» → «Cả nước» theo bảng F2; giữ Bắc thật: Nu na, Chi chi, Kéo cưa, Bà còng, Rồng rắn, Con kiến, hát ru À ơi / Cái ngủ / Ầu ơ, Con cò Đồng Đăng, v.v.

### Giữ nguyên (OK)

Ca dao chuẩn: `ca_cong_cha`, `ca_anh_em`, `ca_ga_mot_me`, `ca_doi_ta`, `dd_bau_ai`, `dd_con_co`, `dd_dung_dang`, `dd_ba_cong`, `dd_rong_ran`, `dd_con_kien`, `hat_ru_vi_dao`, `hat_au_o_bac`, tips, `hat_ly_cay_bong` (Nam Bộ stub).

## Verdict Applier

**APPROVE_WITH_EDITS** — chạy `scripts/apply_folk_consensus.py` rồi sync `web/content/resources-catalog.json`.
