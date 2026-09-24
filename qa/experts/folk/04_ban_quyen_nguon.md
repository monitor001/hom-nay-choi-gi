# F4 — Bản quyền & phân loại nguồn

> Persona: Expert F4 · App **Hôm nay chơi gì?** · Corpus `FOLK_CORPUS_FOR_REVIEW`  
> Policy: **folk traditional** = full lyrics OK · **bài nhạc sĩ / thương mại** = **tip only, không chép lời** · **không bịa** rồi gắn `folk_traditional`

```
STATUS: PARTIAL
SCOPE: Audit license (`folk_traditional` | `folk_or_widely_circulated` | `tip_find`) cho 48 mục dong_dao/hat/tip; ưu tiên hat_dan_ga, hat_dan_vit, hat_chim_sau, dd_ba_ke, tip_bai_ba_me, nhóm «Cháu yêu bà».
DONE: Duyệt đủ 48 id; đối chiếu nguồn công khai (SGK/giáo án nhà trẻ) với lời trong corpus; lập bảng verdict + EDITS_REQUIRED.
FILES: qa/experts/folk/04_ban_quyen_nguon.md (file này). Không sửa catalog.json.
DEVIATIONS: Không thẩm định pháp lý formal (không thay luật sư / chủ quyền); attribution dựa trên nguồn giáo dục công khai VN.
BLOCKERS: Không có.
ERRORS: Không có.
NEXT_FOR_PARENT: F5 hợp nhất; Applier thực hiện EDITS_REQUIRED (reclassify → tip_find / xoá lời / gỡ mục bịa). Ưu tiên P0 high-risk.
```

## Policy nhanh (áp dụng)

| License đúng | Được làm gì |
|---|---|
| `folk_traditional` | Chép lời truyền miệng/ca dao/hát ru dân gian quen thuộc |
| `folk_or_widely_circulated` | Chỉ khi **không** xác định được nhạc sĩ **và** không phải bản thương mại quen; vẫn ưu tiên rút gọn + ghi biến thể |
| `tip_find` | Chỉ tên bài / hướng tìm; **cấm** khổ thơ bản quyền |

**Cấm:** (1) bài nhà trẻ có nhạc sĩ mà vẫn full lyrics; (2) app viết lời mới rồi gắn `folk_traditional`; (3) tip dán nguyên khổ «Cháu yêu bà» / tương đương.

---

## High-risk copyright / misrepresentation (P0)

| id | Vấn đề | Verdict |
|---|---|---|
| **hat_dan_ga** | Bài thiếu nhi **có tác giả**: nhạc Phi-líp-pen-cô (Arkady Filippenko), lời Việt **Việt Anh** (SGK Âm nhạc lớp 1). Corpus gắn `folk_or_widely_circulated` + full lyrics (biến thể gần bản SGK: «Đàn gà con lông vàng…»). **Vi phạm policy tip-only.** | `REMOVE_OR_REPLACE` |
| **hat_dan_vit** | Bài nhà trẻ gắn nhạc sĩ **Mộng Lân** («Đàn vịt con»). Corpus full lyrics + `folk_or_widely_circulated`. Dù lời rút/biến thể vẫn là vùng bài có tác giả. | `REMOVE_OR_REPLACE` |
| **dd_ba_ke** | Lời «Bà ơi bà / Cháu yêu bà…» **bịa dạng song / paraphrase** chủ đề bài **Xuân Giao — «Cháu yêu bà»** (thương mại/nhà trẻ), nhưng gắn `folk_traditional` + ghi «không phải bài nhạc thương mại» → **misrepresentation + rủi ro sát bản quyền**. | `REMOVE_OR_REPLACE` |
| **hat_chim_sau** | Lời ngắn **không khớp** đồng dao/dân ca quen; vẻ app-invented gắn `folk_or_widely_circulated` (đòi «phổ biến»). Không đủ bằng chứng folk → không được full lyrics như nguồn dân gian. | `REMOVE_OR_REPLACE` |

**tip_bai_ba_me:** license `tip_find` **đúng**; body **chỉ liệt kê tên** («Cháu yêu bà», «Cả nhà thương nhau», «Mẹ yêu không nào»…) — **không** chép khổ copyrighted. Giữ. Đồng bộ: sau khi gỡ `dd_ba_ke`, tip này là kênh duy nhất cho nhóm bà/mẹ thương mại.

---

## Bảng duyệt mọi id

| id | license hiện tại | verdict | ghi chú ngắn |
|---|---|---|---|
| dd_nu_na | folk_traditional | AUTHENTIC | Đồng dao truyền thống quen. |
| dd_chi_chi | folk_traditional | AUTHENTIC | Đồng dao truyền thống quen. |
| dd_thang_bom | folk_traditional | AUTHENTIC | Thơ/đồng dao dân gian. |
| dd_dung_dang | folk_traditional | AUTHENTIC | Đồng dao truyền thống. |
| dd_keo_cua | folk_traditional | AUTHENTIC | Đồng dao truyền thống. |
| dd_hoa_buoi | folk_traditional | RECLASSIFY | Lời tứ tuyệt hiện đại/văn học; **không** khớp đồng dao truyền miệng quen «hoa thơm». Không invent-as-folk. Cắt lời → tip hỏi ông bà **hoặc** REMOVE nếu không có bản dân gian xác thực. |
| dd_con_co | folk_traditional | AUTHENTIC | Ca dao quen. |
| dd_bau_ai | folk_traditional | AUTHENTIC | Ca dao quen. |
| dd_hom_na | folk_traditional | VARIANT_OK | Biến thể ngắn + nhắc «Ù a»; chấp nhận folk. |
| dd_chim_chim | folk_traditional | VARIANT_OK | Trò chơi sân trường; folk miệng OK (có thể ghi `folk_or_widely_circulated` nếu muốn chặt hơn — không P0). |
| hat_ru_a_oi | folk_traditional | AUTHENTIC | Hát ru/ca dao rút gọn quen. |
| hat_ru_vi_dao | folk_traditional | AUTHENTIC | Ca dao/hát ru. |
| hat_ly_cay_bong | folk_traditional | AUTHENTIC | Dân ca Nam Bộ; chỉ điệp khúc + hướng học thêm — OK. |
| **hat_dan_ga** | folk_or_widely_circulated | **REMOVE_OR_REPLACE** | **P0** — bài có nhạc sĩ; chuyển tip, **xoá toàn bộ lyrics**. |
| dd_ba_cong | folk_traditional | AUTHENTIC | Đồng dao miền Bắc quen. |
| dd_rong_ran | folk_traditional | AUTHENTIC | Đồng dao/trò chơi dây. |
| dd_con_kien | folk_traditional | AUTHENTIC | Đồng dao quen. |
| dd_cai_bong | folk_traditional | VARIANT_OK | Dạng chơi miệng ngắn; chấp nhận. |
| dd_thang_cuoi | folk_traditional | RECLASSIFY | Mở đầu dân gian OK; 2 câu cuối («chú chó trắng… mênh mông đêm dài») **văn học/bịa**. Cắt phần bịa hoặc tip «hỏi ông bà bản quen»; không gắn full như folk chuẩn. |
| dd_ong_trang | folk_traditional | RECLASSIFY | Bản rút hiện đại/app-like; thiếu chứng truyền miệng cố định. Tip hoặc cắt còn khung gọi ông trăng + ghi biến thể nhà. |
| dd_mot_hai_ba | folk_traditional | RECLASSIFY | Đếm 1–10 dân gian OK; «Mười ngón tay xinh / Của bé nhà mình» kiểu sáng tác app. Cắt 2 câu cuối hoặc reclass. |
| dd_bit_mat | folk_traditional | VARIANT_OK | Tên trò + lời khung; chấp nhận rút gọn. |
| dd_co_chan_chi | folk_traditional | VARIANT_OK | Biến thể đồng dao; OK với ghi biến thể. |
| dd_chim_da_da | folk_traditional | RECLASSIFY | Lời quá ngắn, nghi invent; không đủ để claim folk chuẩn — tip/cắt hoặc REMOVE. |
| dd_ong_troi | folk_traditional | RECLASSIFY | Giọng app-invented; không phải đồng dao Bắc cố định quen. |
| dd_nha_co_ai | folk_traditional | RECLASSIFY | Lời gọi tên kiểu app; **không** folk confirmed. Gần chủ đề «Cả nhà thương nhau» (thường có nhạc sĩ) → nguy cơ nhầm. Tip hoặc REMOVE. |
| dd_di_cho_me | folk_traditional | RECLASSIFY | Biến thể nghi invent; ưu tiên tip «hỏi ông bà» hoặc cắt còn khung 2–3 câu nếu F1 xác nhận. |
| **dd_ba_ke** | folk_traditional | **REMOVE_OR_REPLACE** | **P0** — dạng «Cháu yêu bà»; xoá mục hoặc chuyển tip (đã có `tip_bai_ba_me`), **xoá lyrics**. |
| dd_ong_bao | folk_traditional | REMOVE_OR_REPLACE | Cặp bịa với `dd_ba_ke`; cùng xử lý (không folk). |
| dd_em_be | folk_traditional | RECLASSIFY | Lời app-invented gắn folk → misrepresentation nhẹ. Tip/REMOVE. |
| ca_cong_cha | folk_traditional | AUTHENTIC | Ca dao chuẩn. |
| hat_cai_ngu | folk_traditional | AUTHENTIC | Hát ru dân gian. |
| hat_me_ganh_nuoc | folk_traditional | AUTHENTIC | Hát ru/ca dao. |
| hat_gio_dua | folk_traditional | AUTHENTIC | Ca dao/hát ru (+ ghi biến thể). |
| hat_au_o_bac | folk_traditional | AUTHENTIC | Hát ru Bắc. |
| ca_anh_em | folk_traditional | AUTHENTIC | Ca dao. |
| ca_chi_nga | folk_traditional | AUTHENTIC | Ca dao. |
| ca_ga_mot_me | folk_traditional | AUTHENTIC | Ca dao. |
| ca_con_co_dem | folk_traditional | AUTHENTIC | Ca dao (F3 có thể AGE_GATE — ngoài F4). |
| ca_me_cha_nuoi | folk_traditional | VARIANT_OK | Dạng ca dao rút; chấp nhận folk. |
| hat_ru_me_yeu | folk_traditional | RECLASSIFY | Ru hiện đại/app («mẹ yêu con lắm») gắn folk_traditional → misrepresentation. Đổi tip hoặc original-app label nếu product có; **không** folk. |
| hat_ru_ba_ke | folk_traditional | RECLASSIFY | Ru app về bà kể chuyện; không folk confirmed. |
| ca_doi_ta | folk_traditional | AUTHENTIC | Ca dao chuẩn. |
| hat_ly_cay_da | folk_traditional | RECLASSIFY | Điệp «Lý cây đa» có thể dân ca; khổ chim/hót sân nhà nghi dựng. Giữ điệp + tip học thêm **hoặc** xác minh F1 trước khi full lyrics. |
| **hat_dan_vit** | folk_or_widely_circulated | **REMOVE_OR_REPLACE** | **P0** — bài Mộng Lân; tip + xoá lyrics. |
| **hat_chim_sau** | folk_or_widely_circulated | **REMOVE_OR_REPLACE** | **P0** — lời nghi invent; không được «widely circulated» full. |
| tip_bai_ba_me | tip_find | AUTHENTIC | Tip đúng policy; **không** full lyrics thương mại. Giữ; có thể thêm dòng «không nhầm với đồng dao app». |
| tip_hat_ru_bac | tip_find | AUTHENTIC | Tip thực hành; không lời bản quyền. |

**Tóm tắt số lượng:** AUTHENTIC/VARIANT_OK ≈ 30 · RECLASSIFY ≈ 12 · REMOVE_OR_REPLACE (P0 + cặp) = 5 (`hat_dan_ga`, `hat_dan_vit`, `hat_chim_sau`, `dd_ba_ke`, `dd_ong_bao`).

---

## EDITS_REQUIRED

> Cho Applier / F5 — **không** sửa `catalog.json` trong turn F4.

### P0 — bản quyền / misrepresentation nghiêm trọng

1. **`hat_dan_ga`**
   - `license` → `tip_find`
   - **Xoá toàn bộ** body lyrics (kể cả biến thể «lông vàng vàng…»).
   - Body tip mẫu: *«Đàn gà con — bài nhà trẻ có nhạc sĩ (Phi-líp-pen-cô / lời Việt Anh). Học miệng từ cô giáo; app không chép lời.»*
   - Cập nhật `source` cho khớp tip (bỏ «lưu truyền rộng» như folk).

2. **`hat_dan_vit`**
   - `license` → `tip_find`
   - **Xoá** lyrics.
   - Tip: *«Đàn vịt con — bài nhà trẻ (thường ghi nhạc sĩ Mộng Lân). Hỏi cô lớp bé; không chép lời.»*

3. **`dd_ba_ke`**
   - **REMOVE** mục khỏi catalog **hoặc** thay bằng stub tip trỏ `tip_bai_ba_me`.
   - **Cấm** giữ «Bà ơi bà / Cháu yêu bà…» dưới bất kỳ license folk nào.
   - Không được ghi «không phải bài nhạc thương mại» khi lời trùng chủ đề Xuân Giao.

4. **`hat_chim_sau`**
   - **REMOVE** hoặc `license` → `tip_find` với body *«Hỏi ông bà/cô bài chim quen nhà mình»* — **không** gắn lời app như folk/widely circulated.

5. **`dd_ong_bao`**
   - **REMOVE** cùng `dd_ba_ke` (cặp invent-as-folk).

### P1 — invent claiming folk (reclassify / cắt lời)

6. **`dd_hoa_buoi`** — không full lyrics như folk; tip hoặc REMOVE đến khi F1 xác nhận bản dân gian thật.  
7. **`dd_thang_cuoi`** — cắt 2 câu cuối bịa; giữ mở «Thằng Cuội ngồi gốc cây đa» + note biến thể.  
8. **`dd_ong_trang`**, **`dd_chim_da_da`**, **`dd_ong_troi`**, **`dd_nha_co_ai`**, **`dd_di_cho_me`**, **`dd_em_be`** — bỏ `folk_traditional` full; tip / cắt / REMOVE theo F1+F5.  
9. **`dd_mot_hai_ba`** — cắt «Mười ngón tay xinh / Của bé nhà mình» hoặc reclass.  
10. **`hat_ru_me_yeu`**, **`hat_ru_ba_ke`** — không folk_traditional; tip hát ru ông bà **hoặc** nhãn original-app nếu product cho phép (không giả dân gian).  
11. **`hat_ly_cay_da`** — chỉ giữ điệp xác thực sau F1; phần chim/hót sân nhà cắt nếu không chứng minh được.

### P2 — tip (giữ / tinh chỉnh)

12. **`tip_bai_ba_me`** — **GIỮ** `tip_find`; không thêm khổ lời. Tuỳ chọn: ghi rõ *«Cháu yêu bà (Xuân Giao) — chỉ học miệng, app không chép»*.  
13. **`tip_hat_ru_bac`** — GIỮ nguyên.

### Không đụng (F4)

- Các mục AUTHENTIC/VARIANT_OK trong bảng trên: giữ `folk_traditional` + lyrics (F1/F2/F3 có thể sửa văn bản/tuổi riêng).

---

## High-risk copyright ids (cho parent)

```
hat_dan_ga
hat_dan_vit
dd_ba_ke
hat_chim_sau
```

(+ `dd_ong_bao` — misrepresentation cặp, ưu tiên gỡ cùng `dd_ba_ke`)

`tip_bai_ba_me`: **không** high-risk (tip đúng, không full lyrics).
