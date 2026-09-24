# F3 — Sư phạm & phù hợp tuổi 18–36 tháng

> Persona: Expert F3 · App: **Hôm nay chơi gì?** · Parent-led toddler play  
> Corpus: `FOLK_CORPUS_FOR_REVIEW.md` (48 mục) · **Không** sửa `catalog.json`

---

```
STATUS: OK
SCOPE: Duyệt mọi mục category ∈ {dong_dao, hat} (+ tip liên quan) về phù hợp tuổi 18–36 tháng, parent-led; cờ bạo lực/chết/hù dọa/xấu hổ/hóc/bài giảng dài/chế giễu giới–giai cấp.
DONE: Đã đọc charter + toàn bộ 48 mục; bảng verdict theo id; EDITS_REQUIRED (P0/P1) cho Applier/F5.
FILES: qa/experts/folk/03_su_pham_tuoi.md
DEVIATIONS: Tip `tip_*` (category sach) ghi chú ngắn — không áp AGE_GATE nội dung lời bài hát (không có lời đầy đủ).
BLOCKERS: Không.
ERRORS: Không.
NEXT_FOR_PARENT: F5 hợp nhất P0 (chi_chi / keo_cua / ca_con_co_dem / thang_bom) trước khi applier đụng catalog; ưu tiên AGE_GATE hoặc cắt body, không sáng tác lời mới.
```

---

## Tiêu chí F3 (tóm tắt)

| Cờ | Hành động ưu tiên |
|---|---|
| Chết / xác / nấu–xáo sinh vật | `AGE_GATE` nâng tuổi **hoặc** cắt câu **hoặc** `REMOVE` nếu giữ nguyên cho <36m |
| Gãy xương / đau thể chất (hù dọa nhẹ) | Cắt kết hoặc `AGE_GATE` ≥36 tháng nghe có PH |
| Hù dọa / đuối nước / bị nhốt–bắt | `AGE_GATE` + ghi chú PH; tránh chơi “sợ” với 18–24m |
| Xấu hổ / bỏ rơi / chế giễu giai cấp–giới | Cắt hoặc chỉ nghe ≥30–36m kèm PH giải thích ngắn |
| Bài dài / ca dao “đạo lý” trừu tượng | Giữ nghe ngắn; không bắt thuộc; cân nhắc nâng `ageHint` |
| Rủi ro hóc (không có trong corpus này) | — |

**Nguyên tắc app:** parent-led · không hù dọa để bé “ngoan” · ưu tiên cắt / AGE_GATE hơn sáng tác lời mới.

---

## Bảng duyệt theo `id`

| id | category | ageHint hiện tại | Verdict F3 | Ghi chú ngắn |
|---|---|---|---|---|
| `dd_nu_na` | dong_dao | 18–36 | AUTHENTIC | Ru nhịp ổn; không nội dung nhạy. |
| `dd_chi_chi` | dong_dao | 24–36 | **AGE_GATE** / FIX_LYRICS | **P0:** «Con ngựa chết trương» — chết + hình ảnh xác. Cắt 2 câu chết/trống **hoặc** nâng ≥48 tháng / nghe PH. |
| `dd_thang_bom` | dong_dao | 30–36 (nghe) | **AGE_GATE** | **P0:** Quá dài + đối thoại giai–nghèo (phú ông) khó với <36m. Giữ nghe **≥36 tháng**; rút bản 4 dòng (quạt mo → nắm xôi) nếu muốn giữ trong band toddler. |
| `dd_dung_dang` | dong_dao | 18–36 | AUTHENTIC | Nhịp chơi ổn; «cửa trời» trừu tượng nhưng không sợ. |
| `dd_keo_cua` | dong_dao | 18–36 | **AGE_GATE** / FIX_LYRICS | **P0:** «Sổ gãy chân» + bỏ rơi chị em không chỗ nằm. Cắt từ «Còn chị em mình…» **hoặc** ageHint ≥36 tháng. |
| `dd_hoa_buoi` | dong_dao | 24–36 | VARIANT_OK | Lời “đêm / tim” hơi trưởng thành; không hại — OK nghe. |
| `dd_con_co` | dong_dao | 24–36 | AUTHENTIC | Địa danh; không bạo lực. |
| `dd_bau_ai` | dong_dao | 30–36 (nghe) | AUTHENTIC | Ngắn, ẩn dụ anh chị em — nghe OK. |
| `dd_hom_na` | dong_dao | 18–30 | AUTHENTIC | Ru ngắn phù hợp. |
| `dd_chim_chim` | dong_dao | 24–36 | AUTHENTIC | Trò chọn người + «Ú òa!» — PH tránh hù mạnh 18–24m. |
| `hat_ru_a_oi` | hat | 0–36 | VARIANT_OK | Ru ổn; «trường đời» trừu tượng — không cần cắt. |
| `hat_ru_vi_dao` | hat | 18–36 | VARIANT_OK | Giống trên; ngắn hơn — OK. |
| `hat_ly_cay_bong` | hat | 30–36 | AUTHENTIC | Chỉ điệp khúc — phù hợp nhịp. |
| `hat_dan_ga` | hat | 18–36 | AUTHENTIC | Nhà trẻ; ấm, không sợ. |
| `dd_ba_cong` | dong_dao | 24–36 | VARIANT_OK | «Ao sâu» — nhắc nước, không hù; PH đứng xa ao thật. |
| `dd_rong_ran` | dong_dao | 24–36 | AGE_GATE (nhẹ) | Trò dây / đuổi bắt nhóm: **≥30 tháng** + PH dẫn; 18–24m dễ sợ/ngã. |
| `dd_con_kien` | dong_dao | 18–36 | AUTHENTIC | Nhịp leo — rất phù hợp. |
| `dd_cai_bong` | dong_dao | 18–30 | AUTHENTIC | Hang / ú òa nhẹ — OK nếu PH không hù. |
| `dd_thang_cuoi` | dong_dao | 24–36 | AUTHENTIC | Trăng / Hằng Nga — êm. |
| `dd_ong_trang` | dong_dao | 18–36 | AUTHENTIC | Ru–trăng ngắn — lý tưởng. |
| `dd_mot_hai_ba` | dong_dao | 18–36 | AUTHENTIC | Đếm ngón — phù hợp. |
| `dd_bit_mat` | dong_dao | 30–36 | AGE_GATE (nhẹ) | Bịt mắt: giữ **≥30–36**; cấm ép 18–24m; PH quan sát sợ. |
| `dd_co_chan_chi` | dong_dao | 24–36 | AUTHENTIC | Chợ / bánh / ông bà — ấm. |
| `dd_chim_da_da` | dong_dao | 18–36 | AUTHENTIC | Chim về nhà — OK. |
| `dd_ong_troi` | dong_dao | 24–36 | AUTHENTIC | Mời chơi — vui. |
| `dd_nha_co_ai` | dong_dao | 18–36 | AUTHENTIC | Gọi người thân — lý tưởng. |
| `dd_di_cho_me` | dong_dao | 18–36 | AUTHENTIC | Chợ / ăn — OK (không chủ đề hóc). |
| `dd_ba_ke` | dong_dao | 18–36 | AUTHENTIC | Gọi bà — ấm. |
| `dd_ong_bao` | dong_dao | 18–36 | AUTHENTIC | Gọi ông — ấm. |
| `dd_em_be` | dong_dao | 18–30 | AUTHENTIC | Tập đi — phù hợp. |
| `ca_cong_cha` | hat | 30–36 (nghe) | AGE_GATE (nhẹ) | Ca dao “hiếu” dài / trừu tượng — giữ **nghe ≥36** hoặc chỉ 2 câu đầu. |
| `hat_cai_ngu` | hat | 0–36 | AUTHENTIC | Ru cổ điển; không hù. |
| `hat_me_ganh_nuoc` | hat | 0–36 | AUTHENTIC | Ru ấm. |
| `hat_gio_dua` | hat | 18–36 | AUTHENTIC | Nhịp ru; bản rút — OK. |
| `hat_au_o_bac` | hat | 0–36 | VARIANT_OK | Như ví dầu — OK ru. |
| `ca_anh_em` | hat | 30–36 (nghe) | AUTHENTIC | Ngắn — nghe OK. |
| `ca_chi_nga` | hat | 30–36 (nghe) | AUTHENTIC | Ngắn — nghe OK. |
| `ca_ga_mot_me` | hat | 30–36 (nghe) | VARIANT_OK | «Đá nhau» = đá gà ẩn dụ; PH nói “anh em đừng cãi” — không diễn bạo lực. |
| `ca_con_co_dem` | hat | 24–36 | **REMOVE_OR_REPLACE** / **AGE_GATE** | **P0:** Đuối nước + «xáo măng» (nấu cò). **Không** phát đầy đủ cho <36m. Chỉ 2 câu đầu **hoặc** ageHint ≥48 / tip-only. |
| `ca_me_cha_nuoi` | hat | 30–36 (nghe) | AUTHENTIC | Ngắn đạo lý — nghe OK. |
| `hat_ru_me_yeu` | hat | 0–36 | AUTHENTIC | Ru hiện đại-truyền miệng ngắn — lý tưởng. |
| `hat_ru_ba_ke` | hat | 0–36 | AUTHENTIC | Ru bà — ấm; «chuyện xưa» không kể kinh dị. |
| `ca_doi_ta` | hat | 30–36 (nghe) | AUTHENTIC | Ngắn — nghe OK. |
| `hat_ly_cay_da` | hat | 24–36 | AUTHENTIC | Chim / cây — vui. |
| `hat_dan_vit` | hat | 18–36 | AUTHENTIC | Nhà trẻ — phù hợp. |
| `hat_chim_sau` | hat | 18–36 | AUTHENTIC | Ngắn vui — phù hợp. |
| `tip_bai_ba_me` | sach | 18–36 | AUTHENTIC | Tip tìm bài — không lời nhạy; OK. |
| `tip_hat_ru_bac` | sach | 0–36 | AUTHENTIC | Tip sưu tầm ru — OK; nhắc PH tránh bản hù dọa nếu ông bà có câu “ma/yêu”. |

---

## P0 — Vấn đề tuổi (ưu tiên Applier / F5)

| # | id | Vấn đề | Khuyến nghị F3 |
|---|---|---|---|
| 1 | `ca_con_co_dem` | «Lộn cổ xuống ao» + «xáo măng» = đuối nước + nấu sinh vật; note corpus đã gợi dừng sớm nhưng `ageHint` 24–36 vẫn quá thấp nếu body giữ đủ. | **Ưu tiên:** body chỉ **2 câu đầu**; **hoặc** `AGE_GATE` ≥48 tháng / chuyển tip “hỏi ông bà bản ngắn”. **Không** phát câu xáo cho dưới 36m. |
| 2 | `dd_chi_chi` | «Con ngựa chết trương» — chết + trương xác; dễ sợ / hỏi “chết là gì?”. | Cắt «Cái đanh nấu mỡ / Con ngựa chết trương» (hoặc cả khối chết–trống–dép rách) **hoặc** `ageHint` ≥48 tháng. Giữ nhịp «Chi chi… Úi chà!» nếu cắt. |
| 3 | `dd_keo_cua` | Kết «Sổ gãy chân» + đoạn chị em không chỗ nằm (xấu hổ / thương tích). | Cắt từ «Còn chị em mình» đến hết **hoặc** `ageHint` ≥36 tháng (nghe có PH). Giữ phần kéo cưa / xây nhà cho 18–36. Hiện age 18–36 **không ổn** nếu giữ nguyên kết. |
| 4 | `dd_thang_bom` | Dài (8 dòng đối thoại); lớp phú ông / của cải — tải nhận thức + dễ hiểu nhầm “ai thắng”. | Giữ **chỉ nghe ≥36 tháng**; rút còn quạt mo → nắm xôi (4 dòng) nếu muốn trong band toddler; không bắt thuộc. |

---

## ## EDITS_REQUIRED

> Applier **không** sáng tác lời mới thay thế; chỉ cắt / nâng age / ghi chú PH / chuyển tip theo F5 CONSENSUS.

### P0 (bắt buộc trước khi dùng dưới 36 tháng)

1. **`ca_con_co_dem`**
   - Action: `AGE_GATE` **và/hoặc** shorten body  
   - Body đề xuất giữ:  
     `Con cò mà đi ăn đêm / Đậu phải cành mềm lộn cổ xuống ao`  
     → **Xóa** «Ông ơi ông vớt… xáo măng…» khỏi bản toddler  
   - Alternatif: `ageHint` → `48+ tháng (nghe PH)` hoặc `REMOVE` khỏi band 18–36 / tip-only  
   - UI note: «Bản đầy đủ có câu xáo — không hát với bé dưới 3 tuổi.»

2. **`dd_chi_chi`**
   - Action: `FIX_LYRICS` (cắt) **hoặc** `AGE_GATE`  
   - Cắt tối thiểu: «Cái đanh nấu mỡ / Con ngựa chết trương»  
   - Nếu giữ nguyên lời: `ageHint` → `≥48 tháng` (không còn 24–36)

3. **`dd_keo_cua`**
   - Action: `FIX_LYRICS` (cắt kết) **hoặc** `AGE_GATE`  
   - Cắt: từ «Còn chị em mình» … «Sổ gãy chân.»  
   - Nếu giữ nguyên: `ageHint` → `≥36 tháng (nghe)`; **không** để 18–36 với câu gãy chân

4. **`dd_thang_bom`**
   - Action: `AGE_GATE` (+ optional shorten)  
   - `ageHint` → `≥36 tháng (nghe)` hoặc `36–60 tháng (nghe)`  
   - Optional: body rút 4 dòng (quạt mo → nắm xôi); bỏ chuỗi đổi bò/trâu/ao/lim nếu giữ trong toddler catalog

### P1 (nên làm)

5. **`dd_rong_ran`** — `ageHint` → `30–36 tháng`; note PH: trò dây/đội, tránh ép bé sợ.  
6. **`dd_bit_mat`** — giữ ≥30–36; note: không bịt mắt cưỡng bức 18–24m.  
7. **`ca_cong_cha`** — chỉ nghe ≥36 **hoặc** cắt còn 2 câu đầu (núi Thái Sơn / nước trong nguồn).  
8. **`ca_ga_mot_me`** — note PH: giải thích “đừng cãi nhau”, không diễn đá/đánh.  
9. **`dd_chim_chim` / `dd_cai_bong`** — note UI: «Ú òa» nhẹ, không hù dọa.  
10. **`tip_hat_ru_bac`** — thêm 1 dòng gợi ý: tránh bản ru có ma/yêu/hù nếu ông bà có biến thể đó.

### Không yêu cầu sửa (OK toddler parent-led)

`dd_nu_na`, `dd_dung_dang`, `dd_con_co`, `dd_bau_ai`, `dd_hom_na`, `hat_ly_cay_bong`, `hat_dan_ga`, `dd_con_kien`, `dd_thang_cuoi`, `dd_ong_trang`, `dd_mot_hai_ba`, `dd_co_chan_chi`, `dd_chim_da_da`, `dd_ong_troi`, `dd_nha_co_ai`, `dd_di_cho_me`, `dd_ba_ke`, `dd_ong_bao`, `dd_em_be`, `hat_cai_ngu`, `hat_me_ganh_nuoc`, `hat_gio_dua`, `ca_anh_em`, `ca_chi_nga`, `ca_me_cha_nuoi`, `hat_ru_me_yeu`, `hat_ru_ba_ke`, `ca_doi_ta`, `hat_ly_cay_da`, `hat_dan_vit`, `hat_chim_sau`, `tip_bai_ba_me` (+ các hát ru ví dầu / ầu ơ như VARIANT_OK ở bảng trên).

---

## Tóm tắt cho F5

- **P0 age issues (4):** `ca_con_co_dem` (xáo măng), `dd_chi_chi` (chết trương), `dd_keo_cua` (sổ gãy chân), `dd_thang_bom` (dài + giai–nghèo).  
- Không phát hiện chủ đề **hóc** trong corpus.  
- Hù dọa: chủ yếu ở kết bài / trò bịt mắt–dây — xử lý bằng AGE_GATE + note PH, không cần REMOVE hàng loạt.  
- `STATUS: OK` — đủ để F5 CONSENSUS; catalog chưa đụng.
