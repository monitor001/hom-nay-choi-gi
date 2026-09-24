# F1 — Văn hóa dân gian / văn bản học Việt Nam

> App: **Hôm nay chơi gì?** · Persona AI nội bộ — **không** thay chuyên gia người thật.  
> Corpus: `FOLK_CORPUS_FOR_REVIEW.md` (48 mục) · Không sửa `catalog.json`.

```
STATUS: OK
SCOPE: Duyệt toàn bộ id category ∈ {dong_dao, hat} + tip_bai_ba_me, tip_hat_ru_bac — khớp lời truyền miệng quen thuộc; phát hiện xuyên tạc / bịa lời / trộn bản; ghi biến thể miền.
DONE: 48/48 mục có verdict; bảng tổng hợp + ## EDITS_REQUIRED với hướng sửa cụ thể (ưu tiên cắt / bản đã ghi nhận / tip «hỏi ông bà»; không bịa thơ cổ thay thế). FIX=8, REMOVE=15.
FILES: qa/experts/folk/01_van_ban_hoc.md (file này)
DEVIATIONS: Không đối chiếu bản in học thuật đầy đủ (Kho tàng đồng dao / Tuyển tập ca dao in giấy); dựa văn bản học phổ biến + dị bản đã công bố trên web giáo dục / sưu tầm. Mục «Ông Thượng» (dd_keo_cua) và dị bản dài «nấu mỡ» (dd_chi_chi) ít được chỉ mục online → xếp FIX về bản phổ biến đã ghi nhận.
BLOCKERS: Không
ERRORS: Không
NEXT_FOR_PARENT: Chờ F2–F4; F5 hợp nhất. Ưu tiên P0: REMOVE các bài bịa + FIX lời đã xuyên tạc (nu_na, keo_cua, thang_cuoi, cai_ngu, gio_dua). Applier chỉ sửa catalog sau CONSENSUS.
```

**Tóm tắt đếm (F1):**
| Verdict | Số |
|---|---|
| AUTHENTIC | 16 |
| VARIANT_OK | 6 |
| FIX_LYRICS | 8 |
| RECLASSIFY | 3 |
| REMOVE_OR_REPLACE | 15 |
| AGE_GATE | 0 *(F1 ghi chú nhạy cảm → giao F3; không dùng AGE_GATE làm verdict chính)* |

**Đếm hành động cho parent:** **FIX_LYRICS = 8** · **REMOVE_OR_REPLACE = 15**  
*(RECLASSIFY = 3 không tính vào FIX/REMOVE. 16+6+8+3+15 = 48.)*

---

## Bảng duyệt mọi id

| id | verdict | Ghi chú ngắn (văn bản học) |
|---|---|---|
| `dd_nu_na` | **FIX_LYRICS** | Mở đúng «Nu na nu nống / Cái trống nằm trong» nhưng **«Cái kèn nằm ngoài» + «Thầy nói thầy cười / Con ơi con ngủ»** không khớp dị bản phổ biến (thường *Con ong nằm ngoài* → củ khoai / Phật / cóc / gà…). Trộn hát ru vào đồng dao chơi. |
| `dd_chi_chi` | **FIX_LYRICS** | Bản app (*nấu mỡ / trống bỏi / vườn cà / xếp hạt nam lâu*) **không** trùng bản phổ biến đã ghi nhận (*Cái đanh thổi lửa / Con ngựa chết trương / Ba vương… / Bắt dế… / Ù à ù ập*). Giữ tên trò OK; sửa về bản ngắn đã lưu hành rộng hoặc ghi rõ «biến thể sân chơi chưa kiểm chứng». |
| `dd_thang_bom` | AUTHENTIC | Lục bát «Thằng Bờm / quạt mo / phú ông» khớp ca dao quen. |
| `dd_dung_dang` | AUTHENTIC | «Dung dăng dung dẻ / Dắt trẻ đi chơi…» khớp đồng dao phổ biến. |
| `dd_keo_cua` | **FIX_LYRICS** | Bản app (*Ông Thượng bà Thượng… sổ gãy chân*) **không** khớp bản Bắc phổ biến đã ghi nhận: *Ông thợ nào khỏe / Về ăn cơm vua / Ông thợ nào thua / Về bú tí mẹ*. Rủi ro xuyên tạc / nhầm dị bản. |
| `dd_hoa_buoi` | **REMOVE_OR_REPLACE** | Không tìm thấy đồng dao cổ «Hoa thơm ai ướp cho đượm… đêm trăng rằm». Giọng trữ tình hiện đại; **không** gắn folk_traditional. |
| `dd_con_co` | AUTHENTIC | «Bay lả bay la… Đồng Đăng / Kỳ Lừa / Tô Thị / Tam Thanh» — ca dao Lạng Sơn chuẩn. |
| `dd_bau_ai` | AUTHENTIC | «Bầu ơi thương lấy bí cùng…» — ca dao chuẩn. |
| `dd_hom_na` | **FIX_LYRICS** | «Hom na» là biến thể mở của Nu na; thân bài app (*trống thình thình / mai dậy chơi sân*) mang dấu nhà trẻ/sáng tác. Giữ tip «Ù a ù à»; cắt thân hoặc chỉ giữ mở + tip. |
| `dd_chim_chim` | VARIANT_OK | Vè chọn người sân trường («xì xà xì xồ») — truyền miệng phổ biến, không cổ điển như ca dao lục bát; chấp nhận nếu ghi «sân chơi / nhà trẻ». |
| `hat_ru_a_oi` | VARIANT_OK | Ghép «À ơi» + «Ví dầu…» + câu mẹ gánh nước — các mảnh đều dân gian; dạng rút/ghép hát ru chấp nhận được nếu ghi «rút gọn / lắp câu». |
| `hat_ru_vi_dao` | AUTHENTIC | Bốn câu «Ví dầu cầu ván đóng đinh… trường đời» — ca dao chuẩn. |
| `hat_ly_cay_bong` | VARIANT_OK | Nam Bộ; stub điệp «Lý cây bông» + tip ông bà — **đúng cách** (không bịa lời đầy đủ). Giữ region Nam Bộ. |
| `hat_dan_ga` | RECLASSIFY | Lời nhà trẻ lưu hành; **không** đồng dao/hát ru cổ. License `folk_or_widely_circulated` đã đúng hướng — giữ/khẳng định; đừng gắn `folk_traditional`. |
| `dd_ba_cong` | AUTHENTIC | «Bà còng đi chợ trời mưa…» — đồng dao Bắc quen. |
| `dd_rong_ran` | AUTHENTIC | Lời trò «Rồng rắn lên mây…» khớp truyền miệng phổ biến. |
| `dd_con_kien` | AUTHENTIC | «Con kiến mà leo cành đa / đào…» — chuẩn. |
| `dd_cai_bong` | VARIANT_OK | Rút từ «Cái bống là cái bống bang»; bản cực ngắn + «Ú òa» chấp nhận cho 18–30 tháng nếu ghi biến thể rút. |
| `dd_thang_cuoi` | **FIX_LYRICS** | **Xuyên tạc nặng.** Đồng dao đã ghi nhận: *Để trâu ăn lúa gọi cha ời ời / Cha còn cắt cỏ trên trời / Mẹ còn cưỡi ngựa… / cầm nghiên… chuộc lá đa*. Bản app (Hằng Nga / chó trắng / «trần thế mênh mông») lẫn truyền thuyết + giọng hiện đại / gần nhạc sĩ — **không** phải lời đồng dao cổ. |
| `dd_ong_trang` | **REMOVE_OR_REPLACE** | Lời nhà trẻ mới; không có bản đồng dao cổ «Ông trăng ơi… sáng cho bé ngủ» đã kiểm chứng. |
| `dd_mot_hai_ba` | **FIX_LYRICS** | Đếm 1–10 là thực hành dân gian; hai câu cuối «Mười ngón tay xinh / Của bé nhà mình» mang dấu sáng tác nhà trẻ — cắt hoặc tip. |
| `dd_bit_mat` | **REMOVE_OR_REPLACE** | Tên trò dân gian đúng; **lời thơ** trong app là mô tả luật được viết mới, không phải đồng dao cố định đã lưu hành. |
| `dd_co_chan_chi` | **REMOVE_OR_REPLACE** | Không khớp văn bản đồng dao Bắc đã kiểm chứng; nghi sáng tác cho app. |
| `dd_chim_da_da` | **REMOVE_OR_REPLACE** | Không phải ca dao/đồng dao cổ quen; giọng nhà trẻ mới. |
| `dd_ong_troi` | **REMOVE_OR_REPLACE** | Sáng tác nhà trẻ; không có bản cổ «Ông trời ơi… xuống chơi với chúng em». |
| `dd_nha_co_ai` | **REMOVE_OR_REPLACE** | Danh sách gọi tên người thân mới; không folk_traditional. Chuyển tip / hỏi ông bà. |
| `dd_di_cho_me` | **REMOVE_OR_REPLACE** | Lời mới dạng nhà trẻ; không đồng dao đã ghi nhận. |
| `dd_ba_ke` | **REMOVE_OR_REPLACE** | Lời mới; trùng chủ đề tip thương mại «Bà ơi bà» — chuyển `tip_bai_ba_me`, **không** gắn folk. |
| `dd_ong_bao` | **REMOVE_OR_REPLACE** | Cặp sáng tác với `dd_ba_ke`; không truyền miệng cổ đã kiểm chứng. |
| `dd_em_be` | **REMOVE_OR_REPLACE** | Thơ ngắn mới; không đồng dao cổ. |
| `ca_cong_cha` | AUTHENTIC | «Công cha như núi Thái Sơn…» — ca dao chuẩn. |
| `hat_cai_ngu` | **FIX_LYRICS** | Mở đúng «Cái ngủ mày ngủ cho lâu» nhưng app đổi thành «…đồng sâu **mẹ mày về**» và cặp «gánh nước non» — **lệch** dị bản phổ biến (*…đồng sâu **chưa về** / bắt… trê… mang về cho cái ngủ ăn*). Trộn / rút sai. |
| `hat_me_ganh_nuoc` | VARIANT_OK | «Con ơi con ngủ… gánh nước… tháng mười… chuyện cười» — mảnh hát ru/ca dao đã lưu hành; dạng ghép rút chấp nhận nếu ghi biến thể. |
| `hat_gio_dua` | **FIX_LYRICS** | Câu 1 chuẩn; **«Kỳ đài tắm mát» không** thuộc dị bản đã ghi nhận. Bản Bắc phổ biến: *Tiếng chuông Trấn Vũ, canh gà Thọ Xương* (+ Yên Thái / Tây Hồ); dị bản Huế: *Thiên Mụ*. Sửa hoặc chỉ giữ câu 1 + tip. |
| `hat_au_o_bac` | AUTHENTIC | «Ầu ơ» + Ví dầu — hát ru Bắc chuẩn (rút). |
| `ca_anh_em` | AUTHENTIC | «Anh em như thể chân tay / Rách lành đùm bọc…» — chuẩn. |
| `ca_chi_nga` | VARIANT_OK | «Chị ngã em nâng» thường đi kèm cụm anh-em; bản ngắn + «Rách lành đùm bọc lấy nhau» chấp nhận nếu ghi rút. |
| `ca_ga_mot_me` | AUTHENTIC | «Gà cùng một mẹ / Chớ hoài đá nhau» — chuẩn. |
| `ca_con_co_dem` | AUTHENTIC | Lời «ăn đêm… xáo măng» **đúng** ca dao cổ. Tip dừng sớm đúng. → F3 xem AGE_GATE / cắt «xáo măng»; F1 **không** bảo xóa vì «bạo lực» nếu còn muốn giữ di sản nguyên văn. |
| `ca_me_cha_nuoi` | RECLASSIFY | Khẩu hiệu / diễn giải hiếu đạo kiểu sách giáo khoa; **không** phải lục bát ca dao cổ như `ca_cong_cha`. Đổi nguồn/license hoặc gộp tip hiếu đạo; đừng gắn folk_traditional tuyệt đối. |
| `hat_ru_me_yeu` | **REMOVE_OR_REPLACE** | Hát ru mới («mẹ yêu con lắm»); không truyền miệng cổ đã kiểm chứng. |
| `hat_ru_ba_ke` | **REMOVE_OR_REPLACE** | Lời ru mới; chuyển tip `tip_hat_ru_bac`. |
| `ca_doi_ta` | AUTHENTIC | «Ta về ta tắm ao ta…» — ca dao chuẩn. |
| `hat_ly_cay_da` | **REMOVE_OR_REPLACE** | Lời app (*Cành cao cành thấp / Chim về đậu…*) **không** khớp Quan họ Bắc Ninh (*Trèo lên quán dốc ngồi gốc cây đa…*) lẫn Lý Nam Bộ đã công bố. Nguy cơ bịa / nhầm tên làn điệu. Stub + tip như `hat_ly_cay_bong` nếu vẫn muốn giữ id. |
| `hat_dan_vit` | RECLASSIFY | Nhà trẻ / biến thể cô giáo; license widely_circulated OK — không gọi folk_traditional. |
| `hat_chim_sau` | **REMOVE_OR_REPLACE** | Lời ngắn nghi sáng tác; chưa chứng minh lưu hành dân gian ổn định. Không đủ để gắn folk. |
| `tip_bai_ba_me` | AUTHENTIC | Tip đúng nguyên tắc: không chép lời thương mại; hướng ông bà / cô giáo. |
| `tip_hat_ru_bac` | AUTHENTIC | Tip thực hành gia đình đúng văn hóa truyền miệng. |

---

## EDITS_REQUIRED

### P0 — REMOVE_OR_REPLACE (15)

1. **`dd_hoa_buoi`** — Xóa khỏi corpus folk_traditional. Không thay bằng thơ «cổ» bịa. Tip «hỏi ông bà câu hát về hoa bưởi» hoặc bỏ id.  
2. **`dd_ong_trang`** — Xóa. Tip: hỏi ông bà câu gọi trăng / Trung thu nhà mình.  
3. **`dd_bit_mat`** — Xóa lời thơ; nếu giữ trò: chỉ mô tả luật ngắn + «hát vần nhà mình», không gắn lyric folk.  
4. **`dd_co_chan_chi`** — Xóa / thay tip gia đình.  
5. **`dd_chim_da_da`** — Xóa.  
6. **`dd_ong_troi`** — Xóa.  
7. **`dd_nha_co_ai`** — Xóa; gộp hướng vào `tip_bai_ba_me`.  
8. **`dd_di_cho_me`** — Xóa.  
9. **`dd_ba_ke`** — Xóa; dùng `tip_bai_ba_me` (cảnh báo bài nhạc sĩ).  
10. **`dd_ong_bao`** — Xóa; cặp với trên.  
11. **`dd_em_be`** — Xóa.  
12. **`hat_ru_me_yeu`** — Xóa; chuyển `tip_hat_ru_bac`.  
13. **`hat_ru_ba_ke`** — Xóa; chuyển `tip_hat_ru_bac`.  
14. **`hat_ly_cay_da`** — Xóa lời bịa; nếu giữ id: stub điệp + tip ông bà (như `hat_ly_cay_bong`), **không** gắn lyric Quan họ giả.  
15. **`hat_chim_sau`** — Xóa.

### P0 — FIX_LYRICS (8) — lời đề xuất (chỉ dòng đã có nguồn phổ biến)

**1. `dd_nu_na`** — thay bằng dị bản phổ biến (rút ngắn OK):

```
Nu na nu nống
Cái trống nằm trong
Con ong nằm ngoài
Củ khoai chấm mật
Phật ngồi Phật khóc
Con cóc nhảy ra
Con gà ú ụ
```

(Có thể cắt sau 4–6 dòng cho tuổi nhỏ. **Không** giữ «Cái kèn» / «Thầy nói thầy cười».)

**2. `dd_chi_chi`** — ưu tiên bản phổ biến trò chọn ngón:

```
Chi chi chành chành
Cái đanh thổi lửa
Con ngựa chết trương
Ba vương bú tí
Bắt dế đi tìm
Ù à ù ập
```

Ghi chú nguồn: biến thể sân chơi phổ biến. Nếu giữ bản «nấu mỡ…»: phải đổi source thành «biến thể sân chơi chưa đối chiếu» — F1 **không khuyến nghị**.

**3. `dd_keo_cua`** — bản Bắc phổ biến:

```
Kéo cưa lừa xẻ
Ông thợ nào khỏe
Về ăn cơm vua
Ông thợ nào thua
Về bú tí mẹ
```

**4. `dd_thang_cuoi`** — khôi phục đồng dao đã ghi nhận (không Hằng Nga / chó trắng):

```
Thằng Cuội ngồi gốc cây đa
Để trâu ăn lúa gọi cha ời ời
Cha còn cắt cỏ trên trời
Mẹ còn cưỡi ngựa đi mời quan viên
Ông thì cầm bút cầm nghiên
Ông thì cầm tiền đi chuộc lá đa
```

(Tuổi nhỏ: có thể chỉ 2–4 câu đầu. Tip Trung thu / chị Hằng = chuyện kể riêng, **không** nhét vào lyric đồng dao này.)

**5. `dd_hom_na`** — cắt thân sáng tác; ví dụ:

```
Hom na hom nống
Cái trống nằm trong
Con ong nằm ngoài

(Nhà bạn có thể hát «Ù a ù à» — dùng đúng bản nhà.)
```

Hoặc MERGE vào `dd_nu_na` + tip.

**6. `hat_cai_ngu`** — về gần dị bản phổ biến (rút):

```
Cái ngủ mày ngủ cho lâu
Mẹ mày đi cấy đồng sâu chưa về
Bắt được con trắm con trê
Cắm cổ mang về cho cái ngủ ăn
```

**Không** viết «mẹ mày về» thay «chưa về» nếu muốn giữ nghĩa cổ. Cặp «gánh nước» để ở `hat_me_ganh_nuoc`.

**7. `hat_gio_dua`** — sửa câu 2 (bản Hà Nội phổ biến) hoặc stub:

```
Gió đưa cành trúc la đà
Tiếng chuông Trấn Vũ, canh gà Thọ Xương

(Biến thể Huế: «Tiếng chuông Thiên Mụ…» — hỏi ông bà bản quen.)
```

**Xóa** «Kỳ đài tắm mát».

**8. `dd_mot_hai_ba`** — giữ đếm 1–10; cắt hai câu cuối «Mười ngón tay xinh / Của bé nhà mình» (dấu nhà trẻ) hoặc thay tip «nhà mình kết bài thế nào».

### P1 — RECLASSIFY (3)

| id | Việc làm |
|---|---|
| `hat_dan_ga` | Giữ license `folk_or_widely_circulated`; source/tag rõ «nhà trẻ»; không `folk_traditional`. |
| `hat_dan_vit` | Như trên. |
| `ca_me_cha_nuoi` | Đổi sang diễn giải giáo dục / tip hiếu đạo; hoặc xóa nếu trùng chức năng `ca_cong_cha`. |

### Ghi chú cho F3 (không đổi verdict F1)

- `ca_con_co_dem`: lời **đúng** cổ; hình ảnh xáo măng → AGE_GATE / chỉ 2 câu đầu.  
- `dd_chi_chi` (sau FIX): vẫn có «chết trương» — F3 cân nhắc.  
- `dd_thang_bom`, `ca_cong_cha`: nghe hiểu 30–36 tháng — OK văn bản.

### Không bịa thay thế

Với mọi REMOVE: **cấm** viết bài thơ mới rồi gắn «đồng dao cổ». Ưu tiên xóa, stub + tip, hoặc chỉ các dòng đã có trong bảng FIX ở trên.

---

## Đếm chốt

| Hành động | Số id |
|---|---|
| **FIX_LYRICS** | **8** (`dd_nu_na`, `dd_chi_chi`, `dd_keo_cua`, `dd_thang_cuoi`, `dd_hom_na`, `dd_mot_hai_ba`, `hat_cai_ngu`, `hat_gio_dua`) |
| **REMOVE_OR_REPLACE** | **15** |
| RECLASSIFY | 3 |
| AUTHENTIC | 16 |
| VARIANT_OK | 6 |

---

## Rủi ro đã kiểm (charter)

| Id | Kết luận F1 |
|---|---|
| `dd_hoa_buoi` | REMOVE — hiện đại / không chứng thực |
| `dd_thang_cuoi` | FIX — khôi phục bản «trâu ăn lúa…» |
| `dd_ong_trang`, `dd_nha_co_ai`, `dd_ba_ke`, `dd_ong_bao`, `dd_em_be`, `dd_di_cho_me` | REMOVE — sáng tác app |
| `hat_ly_cay_bong` | VARIANT_OK — stub đúng |
| `hat_dan_ga`, `hat_dan_vit`, `hat_chim_sau` | RECLASSIFY / REMOVE (`chim_sau`) |
| `ca_con_co_dem` | AUTHENTIC + chuyển F3 AGE_GATE |
| `dd_chi_chi`, `dd_keo_cua` | FIX về bản phổ biến đã ghi nhận |
