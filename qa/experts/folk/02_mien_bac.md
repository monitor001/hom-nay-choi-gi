# F2 — Miền Bắc & ngữ cảnh địa phương

> Persona AI nội bộ · App **Hôm nay chơi gì?** · Không thay chuyên gia người thật.  
> Phạm vi: tag `region` + tính phù hợp miền Bắc; **không** sửa `catalog.json`.

```
STATUS: OK
SCOPE: 48 mục folk/tip trong FOLK_CORPUS_FOR_REVIEW.md — kiểm tra region Miền Bắc vs Nam Bộ/Trung Bộ/Cả nước; ghi chú biến thể Bắc cần thêm vào howToUse/body.
DONE: Bảng verdict đủ 48 id; tách RECLASSIFY (đổi region); đặc biệt xác nhận hat_ly_cay_bong = Nam Bộ (không bán là Bắc); flag hat_ly_cay_da / hat_gio_dua lệch miền.
FILES: qa/experts/folk/02_mien_bac.md
DEVIATIONS: Không duyệt license/tuổi sâu (F3/F4); không phán TEXT authenticity chi tiết ngoài dấu hiệu địa phương (F1).
BLOCKERS: Không.
ERRORS: Không.
NEXT_FOR_PARENT: F5 hợp nhất EDITS_REQUIRED region; applier chỉ đổi tag sau CONSENSUS. Danh sách RECLASSIFY xem cuối báo cáo.
```

## Tiêu chí F2 (tóm tắt)

| Tag đề xuất | Khi nào |
|---|---|
| **Miền Bắc** | Địa danh / từ vựng / thể loại gắn Bắc rõ (Đồng Đăng, bánh đa, ầu ơ, cấy đồng sâu, trò Bắc quen) |
| **Cả nước** | Ca dao / đồng dao / nhà trẻ lưu hành rộng, không dấu miền |
| **Nam Bộ (tham khảo)** | Dân ca lý / Nam Bộ — **không** gắn Bắc |
| **Trung / biến thể** | Lời gắn Huế–Trung (vd. Kỳ đài) khi corpus đang gắn Bắc |

---

## Bảng duyệt đủ id

| id | region hiện tại | Verdict F2 | Ghi chú ngắn |
|---|---|---|---|
| `dd_nu_na` | Miền Bắc | AUTHENTIC | Nu na nu nống — ru/đồng dao Bắc quen. |
| `dd_chi_chi` | Miền Bắc | AUTHENTIC | Bản «ngựa chết trương…» lưu hành mạnh Bắc; biết cả nước nhưng giữ Bắc OK. |
| `dd_thang_bom` | Miền Bắc | RECLASSIFY | Thằng Bờm — ca dao/thơ dân gian **cả nước**, không riêng Bắc. → `Cả nước` |
| `dd_dung_dang` | Miền Bắc | AUTHENTIC | Dung dăng dung dẻ — trò miệng Bắc điển hình. |
| `dd_keo_cua` | Miền Bắc | AUTHENTIC | Kéo cưa lừa xẻ — trò Bắc quen. |
| `dd_hoa_buoi` | Cả nước | AUTHENTIC | Tag region đúng hướng pan-VN (tính 「thật lời」 để F1). |
| `dd_con_co` | Miền Bắc | AUTHENTIC | Đồng Đăng / Kỳ Lừa / Tam Thanh = **Lạng Sơn** — Bắc rõ. |
| `dd_bau_ai` | Miền Bắc | RECLASSIFY | Bầu–bí — ca dao **cả nước**. → `Cả nước` |
| `dd_hom_na` | Miền Bắc | VARIANT_OK | Biến thể Hom na / Nu na / Ù a — Bắc OK; **cần note** howToUse (xem EDITS). |
| `dd_chim_chim` | Miền Bắc | RECLASSIFY | Chi chi xì xà — sân trường **cả nước**. → `Cả nước` |
| `hat_ru_a_oi` | Miền Bắc | AUTHENTIC | À ơi + mẹ gánh nước/ruộng — khung ru Bắc. Note: phân biệt «à ơi» / «ầu ơ». |
| `hat_ru_vi_dao` | Miền Bắc | RECLASSIFY | Ví dầu… — ca dao **cả nước** (hay được ru Bắc nhưng tag pan đúng hơn). → `Cả nước` |
| `hat_ly_cay_bong` | Nam Bộ (tham khảo) | AUTHENTIC | **Nam Bộ** — đúng tag. **Cấm** bán / gắn Miền Bắc. Giữ tham khảo, không đổi thành Bắc. |
| `hat_dan_ga` | Cả nước / nhà trẻ | AUTHENTIC | Tag đúng. |
| `dd_ba_cong` | Miền Bắc | AUTHENTIC | Bà còng… áo the / cậu ấm — từ vựng Bắc. |
| `dd_rong_ran` | Miền Bắc | AUTHENTIC | Rồng rắn lên mây — trò dây Bắc kinh điển. Note nhẹ: «nhà sàn» có thể ghi là hình ảnh trò, không bắt buộc vùng cao. |
| `dd_con_kien` | Miền Bắc | AUTHENTIC | Phổ biến Bắc (cũng nghe nơi khác) — giữ Bắc OK. |
| `dd_cai_bong` | Miền Bắc | VARIANT_OK | Trò miệng ngắn lưu hành Bắc; ghi «bản nhà» nếu khác. |
| `dd_thang_cuoi` | Miền Bắc | RECLASSIFY | Cuội–Hằng — truyền thuyết **cả nước**. → `Cả nước` (+ F1 xem lời hiện đại). |
| `dd_ong_trang` | Miền Bắc | RECLASSIFY | Ông trăng generic — không dấu Bắc. → `Cả nước` |
| `dd_mot_hai_ba` | Miền Bắc | RECLASSIFY | Đếm ngón — **cả nước**. → `Cả nước` |
| `dd_bit_mat` | Miền Bắc | RECLASSIFY | Tên trò cả nước; lời app generic. → `Cả nước` |
| `dd_co_chan_chi` | Miền Bắc | AUTHENTIC | **Bánh đa** = dấu Bắc rõ. |
| `dd_chim_da_da` | Miền Bắc | VARIANT_OK | Đồng dao ngắn; giữ Bắc chấp nhận được. |
| `dd_ong_troi` | Miền Bắc | RECLASSIFY | Ông trời generic — không miền. → `Cả nước` |
| `dd_nha_co_ai` | Miền Bắc | RECLASSIFY | Gọi tên người thân — pan. → `Cả nước` |
| `dd_di_cho_me` | Miền Bắc | RECLASSIFY | Mẹ đi chợ generic. → `Cả nước` |
| `dd_ba_ke` | Miền Bắc | RECLASSIFY | Lời gọi hiện đại / truyền miệng rộng — không riêng Bắc. → `Cả nước` |
| `dd_ong_bao` | Miền Bắc | RECLASSIFY | Cặp với bà — pan. → `Cả nước` |
| `dd_em_be` | Miền Bắc | RECLASSIFY | Đồng dao ngắn generic. → `Cả nước` |
| `ca_cong_cha` | Miền Bắc | RECLASSIFY | Núi Thái Sơn… — ca dao **cả nước**. → `Cả nước` |
| `hat_cai_ngu` | Miền Bắc | AUTHENTIC | Cái ngủ… mẹ đi **cấy đồng sâu** — ru Bắc chuẩn. |
| `hat_me_ganh_nuoc` | Miền Bắc | AUTHENTIC | Ru mẹ gánh nước / ruộng sâu — Bắc. |
| `hat_gio_dua` | Miền Bắc | RECLASSIFY | Lời **Kỳ đài** gắn Huế/Trung; bản Bắc quen là **Trấn Vũ / Thọ Xương**. Không bán Kỳ đài như «miền Bắc thuần». → `Cả nước` *hoặc* giữ Bắc + FIX lời (xem EDITS). |
| `hat_au_o_bac` | Miền Bắc | AUTHENTIC | «Ầu ơ» + ví dầu — đúng khung **hát ru Bắc**. |
| `ca_anh_em` | Miền Bắc | RECLASSIFY | Anh em chân tay — **cả nước**. → `Cả nước` |
| `ca_chi_nga` | Miền Bắc | RECLASSIFY | Chị ngã em nâng — **cả nước**. → `Cả nước` |
| `ca_ga_mot_me` | Miền Bắc | RECLASSIFY | Gà cùng một mẹ — **cả nước**. → `Cả nước` |
| `ca_con_co_dem` | Miền Bắc | AUTHENTIC | Con cò ăn đêm — gắn mạnh Bắc (cũng biết cả nước); giữ Bắc OK. AGE_GATE do F3 (xáo măng). |
| `ca_me_cha_nuoi` | Miền Bắc | RECLASSIFY | Công mẹ / nghĩa cha — **cả nước**. → `Cả nước` |
| `hat_ru_me_yeu` | Miền Bắc | RECLASSIFY | Ru «mẹ yêu con» rút gọn hiện đại — không dấu Bắc. → `Cả nước` |
| `hat_ru_ba_ke` | Miền Bắc | RECLASSIFY | Ru bà kể chuyện generic. → `Cả nước` (hoặc giữ tip-find nếu F4 chuyển). |
| `ca_doi_ta` | Miền Bắc | RECLASSIFY | Ao ta — tục ngữ/ca dao **cả nước**. → `Cả nước` |
| `hat_ly_cay_da` | Miền Bắc | REMOVE_OR_REPLACE | Thể **lý** là truyền thống **Nam Bộ**; «Lý cây đa = biến thể Bắc» dễ hiểu nhầm / bịa khung Bắc. **Không** bán là dân ca Bắc. Thay bằng đồng dao/hát Bắc thật hoặc chuyển tip / bỏ. |
| `hat_dan_vit` | Miền Bắc | RECLASSIFY | Nhà trẻ **cả nước** (đối xứng `hat_dan_ga`). → `Cả nước / nhà trẻ` |
| `hat_chim_sau` | Miền Bắc | RECLASSIFY | Sân chơi generic. → `Cả nước` |
| `tip_bai_ba_me` | Miền Bắc | VARIANT_OK | Tip tìm bài — hướng Bắc OK; liệt kê có bài cả nước/thương mại — giữ tip, đừng gắn region cứng cho từng bài trong list. |
| `tip_hat_ru_bac` | Miền Bắc | AUTHENTIC | Đúng brief: sưu tầm **ầ ơi / ầu ơ** ông bà miền Bắc. |

---

## Đặc biệt: `hat_ly_cay_bong`

| Kiểm tra | Kết luận |
|---|---|
| Region hiện tại | `Nam Bộ (tham khảo)` — **đúng** |
| Có được gắn Miền Bắc? | **Không.** Dân ca lý Nam Bộ; app chỉ tham khảo điệp khúc nhịp. |
| Verdict F2 | `AUTHENTIC` (tag miền đúng) — **không** RECLASSIFY sang Bắc |
| UI / copy | Card phải hiện Nam Bộ; không gộp filter «chỉ Bắc» như bài ru Bắc |

---

## Ghi chú biến thể Bắc cần thêm (howToUse / body)

Áp dụng khi applier sửa copy (không sáng tác lời mới):

1. **`dd_hom_na`** — Ghi: bản nhà có thể *Nu na nu nống* / *Hom na* / *Ù a ù à*; dùng đúng bản ông bà miền Bắc.
2. **`hat_ru_a_oi` + `hat_au_o_bac`** — Ghi phân biệt: nhiều nhà Bắc ru **à ơi** hoặc **ầu ơ**; không bắt buộc một kiểu.
3. **`hat_gio_dua`** — Nếu giữ `Miền Bắc`: thay/ bổ sung câu Bắc quen *«Tiếng chuông Trấn Vũ canh gà Thọ Xương»*; ghi *Kỳ đài* là biến thể **Huế/Trung**, không phải chuẩn Bắc.
4. **`dd_rong_ran`** — Có thể note: lời trò Bắc; hình «nhà sàn» là trong bài hát trò, không đổi region.
5. **`hat_cai_ngu` / `hat_me_ganh_nuoc`** — Optional: «cấy / gánh nước / đồng sâu» = khung nghề nông Bắc quen trong hát ru.
6. **`tip_hat_ru_bac`** — Giữ 3 câu hỏi à ơi / ầu ơ (đã đủ F2).

---

## EDITS_REQUIRED (region reclassifications)

Chỉ đề xuất đổi `region` (và note body khi ghi). **Chưa** sửa catalog.

| id | region hiện tại | region đề xuất | Lý do |
|---|---|---|---|
| `dd_thang_bom` | Miền Bắc | Cả nước | Ca dao/thơ dân gian toàn quốc |
| `dd_bau_ai` | Miền Bắc | Cả nước | Ca dao bầu–bí toàn quốc |
| `dd_chim_chim` | Miền Bắc | Cả nước | Trò sân trường pan-VN |
| `hat_ru_vi_dao` | Miền Bắc | Cả nước | Ca dao ví dầu pan; ru Bắc là cách dùng, không phải nguồn miền |
| `dd_thang_cuoi` | Miền Bắc | Cả nước | Truyền thuyết Cuội toàn quốc |
| `dd_ong_trang` | Miền Bắc | Cả nước | Không dấu địa phương Bắc |
| `dd_mot_hai_ba` | Miền Bắc | Cả nước | Đếm ngón pan |
| `dd_bit_mat` | Miền Bắc | Cả nước | Trò + lời generic |
| `dd_ong_troi` | Miền Bắc | Cả nước | Generic |
| `dd_nha_co_ai` | Miền Bắc | Cả nước | Gọi người thân pan |
| `dd_di_cho_me` | Miền Bắc | Cả nước | Generic |
| `dd_ba_ke` | Miền Bắc | Cả nước | Không riêng Bắc |
| `dd_ong_bao` | Miền Bắc | Cả nước | Không riêng Bắc |
| `dd_em_be` | Miền Bắc | Cả nước | Generic |
| `ca_cong_cha` | Miền Bắc | Cả nước | Ca dao hiếu pan |
| `ca_anh_em` | Miền Bắc | Cả nước | Ca dao anh em pan |
| `ca_chi_nga` | Miền Bắc | Cả nước | Ca dao chị–em pan |
| `ca_ga_mot_me` | Miền Bắc | Cả nước | Ca dao pan |
| `ca_me_cha_nuoi` | Miền Bắc | Cả nước | Ca dao pan |
| `ca_doi_ta` | Miền Bắc | Cả nước | Tục ngữ/ca dao pan |
| `hat_ru_me_yeu` | Miền Bắc | Cả nước | Ru rút gọn không dấu Bắc |
| `hat_ru_ba_ke` | Miền Bắc | Cả nước | Ru generic |
| `hat_dan_vit` | Miền Bắc | Cả nước / nhà trẻ | Đồng bộ `hat_dan_ga` |
| `hat_chim_sau` | Miền Bắc | Cả nước | Sân chơi pan |
| `hat_gio_dua` | Miền Bắc | **Cả nước** *hoặc* giữ Bắc + đổi lời Trấn Vũ | Lời Kỳ đài = Trung/Huế, không phải Bắc chuẩn |
| `hat_ly_cay_da` | Miền Bắc | *(không gắn Bắc)* — REMOVE hoặc thay bài Bắc thật / tip | Thể lý = Nam Bộ; không «biến thể Bắc» |

**Không đổi region (xác nhận đúng):**

| id | Giữ |
|---|---|
| `hat_ly_cay_bong` | `Nam Bộ (tham khảo)` |
| `hat_dan_ga` | `Cả nước / nhà trẻ` |
| `dd_hoa_buoi` | `Cả nước` |
| Các AUTHENTIC Bắc trong bảng trên | `Miền Bắc` |

---

## Tóm tắt đếm (F2)

| Verdict | Số (khoảng) |
|---|---|
| AUTHENTIC | 16 |
| VARIANT_OK | 5 |
| RECLASSIFY | 25 |
| REMOVE_OR_REPLACE | 1 (`hat_ly_cay_da`) |
| FIX_LYRICS | 0 riêng F2 (gộp vào `hat_gio_dua` nếu chọn giữ Bắc) |
| AGE_GATE | 0 (nhường F3; `ca_con_co_dem` chỉ ghi chú) |

---

## Danh sách RECLASSIFY ids (trả parent)

```
dd_thang_bom
dd_bau_ai
dd_chim_chim
hat_ru_vi_dao
dd_thang_cuoi
dd_ong_trang
dd_mot_hai_ba
dd_bit_mat
dd_ong_troi
dd_nha_co_ai
dd_di_cho_me
dd_ba_ke
dd_ong_bao
dd_em_be
ca_cong_cha
ca_anh_em
ca_chi_nga
ca_ga_mot_me
ca_me_cha_nuoi
ca_doi_ta
hat_ru_me_yeu
hat_ru_ba_ke
hat_dan_vit
hat_chim_sau
hat_gio_dua
```

**Ngoài RECLASSIFY — hành động miền:** `hat_ly_cay_da` → **REMOVE_OR_REPLACE** (không gắn Bắc).  
**Giữ Nam Bộ:** `hat_ly_cay_bong` (không RECLASSIFY sang Bắc).
