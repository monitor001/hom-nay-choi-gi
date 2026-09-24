# C2 — Ngôn ngữ & kể chuyện sớm (18–36 tháng)

> Persona: Child Expert **C2** · App: **Hôm nay chơi gì?** · Thư viện tranh kể chuyện  
> Nguồn: `00_CHARTER.md`, `STORY_ART_100.json` (100 mục)  
> **Không** gen ảnh · **Không** sửa `catalog.json` · Vẫn `draft_unreviewed`  
> Ngày: 2026-09-24

---

```
STATUS: OK
SCOPE: Thẩm định 100 chủ đề tranh về khả năng kích hoạt Ai? / Làm gì? / Rồi sao? với toddler 18–36m; cờ REPROMPT khi trừu tượng / nhiều chủ thể cạnh tranh / yếu từ vựng chỉ–hỏi; gợi ý storyPrompt 1 dòng (VI) cho 20 mục gia đình–văn hóa tốt nhất.
DONE: Đọc charter + toàn bộ STORY_ART_100; bảng verdict; EDITS_REQUIRED (P0/P1); 20 storyPrompt gợi ý.
FILES: qa/experts/art/02_C2_ke_chuyen.md
DEVIATIONS: Không.
BLOCKERS: Không blocker cho báo cáo. Gen hàng loạt nên chờ Art sửa P0 REPROMPT (cảnh nhiều chủ thể / trừu tượng).
ERRORS: Không.
NEXT_FOR_PARENT: A1/A2 sửa scene P0 trong STORY_ART_100 trước gen; C3 có thể dùng bảng này + C1 để chốt SHIP/REPROMPT/DROP; Applier chưa đụng catalog.json.
```

---

## 0. Kết luận ngắn

Khung **Ai? → Làm gì? → Rồi sao?** hoạt động tốt nhất với tranh **có người/thú làm chủ thể + hành động rõ** (mẹ bế, bà kể, chào bố, rửa tay…). Nhóm đồ vật quen (chuối, dép, cốc, nón lá…) ổn cho **chỉ–gọi tên**, nhưng “Rồi sao?” cần PH dẫn thêm 1 bước tưởng tượng.

**Yếu nhất:** cảnh phong cảnh / thời tiết / trời đêm (ruộng, sông xa, mây, sao, cầu vồng trên mái làng) — khó chỉ một chủ thể, “Ai?” trống. **Cạnh tranh chủ thể:** cả nhà ăn cơm, đi chợ, parade Trung thu, bus xa + phố, vịt/chim sẻ số nhiều.

**Trước gen hàng loạt:** sửa ~22 mục **REPROMPT P0**; ~15 mục **AGE_NOTE** (PH dẫn câu hỏi, không bắt “Rồi sao?” phức tạp).

---

## 1. Tiêu chí C2 (tóm tắt)

| Câu hỏi PH | Cần thấy trong tranh |
|---|---|
| **Ai?** | 1 người / thú / đồ vật chính **rõ, lớn, gần** — toddler chỉ được |
| **Làm gì?** | Hành động hoặc trạng thái **đọc được ngay** (bế, ngồi, uống, treo…) |
| **Rồi sao?** | Gợi ý bước tiếp theo (ôm → hôn; mưa → vào nhà; đọc → ngủ) — không bắt buộc 3 nhân vật |

| Cờ | Verdict |
|---|---|
| Trừu tượng / không chỉ được 1 vật | `REPROMPT` hoặc `AGE_NOTE` |
| ≥2 chủ thể ngang hàng / bối cảnh đông | `REPROMPT` (gom 1 tiêu điểm) |
| Vật quen, chỉ–gọi tốt, “Rồi sao?” yếu | `SHIP` + ghi chú PH |
| Không có agent + không có hành động kể | `REPROMPT` / cân nhắc `DROP` nếu không cứu được |

---

## 2. Bảng duyệt theo `id`

**Chú thích verdict:** `SHIP` = dùng được cho chỉ–hỏi–kể · `REPROMPT` = sửa scene trước gen · `AGE_NOTE` = giữ nhưng PH dẫn / ageHint · `DROP` = bỏ chủ đề (hiếm).

### 2.1 Existing (10)

| id | Verdict C2 | Ai? / Làm gì? / Rồi sao? | Ghi chú |
|---|---|---|---|
| `to_chuoi` | SHIP | Quả / nằm trên mẹt / ăn–bóc | Chỉ–gọi mạnh; PH hỏi “Ăn không?” |
| `to_ga` | SHIP | Gà / đứng sân / gáy–đi | Tốt |
| `to_meo` | SHIP | Mèo / nằm ghế / ngủ | Tốt |
| `to_ca` | SHIP | Cá / trong bát / bơi | Tốt |
| `to_mua` | **REPROMPT** | Mưa + chuối + chum — **3 tiêu điểm** | Gom: sân + giọt mưa **hoặc** chum + mưa; bỏ lá chuối nếu làm lệch |
| `to_mat_troi` | **REPROMPT** | Trời + đồi + mái — phong cảnh | Mặt trời **lớn giữa khung**; mái/đồi tối giản nền |
| `to_dep` | SHIP | Dép / để cửa / đi vào | Rồi sao? “Đi chơi!” |
| `to_coc` | SHIP | Cốc / trên bàn / uống | Tốt |
| `to_xe_dap` | AGE_NOTE | Xe + mũ bảo hiểm cạnh | Hai vật; ưu tiên xe, mũ nhỏ hơn **hoặc** bỏ mũ khỏi prompt |
| `to_la_cay` | **REPROMPT** | “Held gently” — **Ai cầm?** mờ | Tay bé cầm **1** lá lớn **hoặc** lá đơn không tay |

### 2.2 Gia đình & quan hệ

| id | Verdict C2 | Ai? / Làm gì? / Rồi sao? | Ghi chú |
|---|---|---|---|
| `to_me_bong_con` | SHIP | Mẹ+bé / bế / ôm–hôn | **Mẫu vàng** C2 |
| `to_ba_ke_chuyen` | SHIP | Bà+bé / kể trên chiếu / nghe–cười | Mẫu vàng |
| `to_ong_dan_choi` | SHIP | Ông+bé / dắt tay sân / đi tiếp | Tốt |
| `to_ca_nha_com` | **REPROMPT** | Nhiều người + bát + đũa | **1** bé + **1** người lớn, 1 bát cơm gần; nền gia đình mờ |
| `to_om_ba` | SHIP | Bé+bà / ôm / cười | Tốt |
| `to_chao_bo` | SHIP | Bé+bố / vẫy cửa / bố vào | Tốt |
| `to_anh_em_choi` | SHIP | 2 anh em / xếp gỗ / chia đồ | OK nếu **2** mặt rõ, khối gỗ không át |
| `to_me_hat_ru` | SHIP | Mẹ+bé / ru / ngủ | Phòng tối nhẹ OK nếu mặt còn đọc được |

### 2.3 Nhà cửa & đồ dùng

| id | Verdict C2 | Ghi chú |
|---|---|---|
| `to_cua_so` | **REPROMPT** | Cửa sổ + cây + trời = cạnh tranh → khung cửa + **1** cây xanh ngoài |
| `to_san_gach` | AGE_NOTE / REPROMPT nhẹ | Sân trống khó “Ai?” → chậu cây **lớn giữa** làm chủ thể chỉ |
| `to_giuong_ngu` | SHIP | Giường / nằm / ngủ — OK |
| `to_den_ngu` | SHIP | Đèn / sáng / ngủ — OK |
| `to_ban_chai` | AGE_NOTE | Bàn chải + cốc: ưu tiên bàn chải lớn |
| `to_khan_mat` | SHIP | Khăn / treo / lau mặt — OK |
| `to_ghe_go` | SHIP | Ghế / ngồi — OK |
| `to_tu_lanh` | **REPROMPT** | Tủ + tô quả ngang hàng → cửa tủ **hoặc** 1 quả ngoài, không cả hai bằng nhau |
| `to_noi_com` | SHIP | Nồi / hơi / ăn cơm — OK (hơi mềm, không “nóng sợ”) |
| `to_chieu_coi` | SHIP | Chiếu / trải / ngồi–nghe chuyện — văn hóa tốt |

### 2.4 Động vật

| id | Verdict C2 | Ghi chú |
|---|---|---|
| `to_cho_con` | SHIP | Chó / ngồi sân — tốt |
| `to_vit_ao` | **REPROMPT** | “Ducklings” số nhiều → **1** vịt con rõ; nước nền |
| `to_heo_con` | SHIP | Heo con — tốt |
| `to_chim_se` | **REPROMPT** | Nhiều sẻ + “power line / branch” mơ hồ → **1** sẻ trên **cành**; **không** dây điện |
| `to_bo_sua` | SHIP | Bò / đứng đồng — nhà xa nhỏ OK |
| `to_ech_xanh` | SHIP | Ếch / trên lá sen — tốt |
| `to_buom_vang` | AGE_NOTE | Bướm + hoa: bướm lớn hơn hoa |
| `to_tom_song` | SHIP | Tôm / nước — OK nếu hình dạng rõ (không trừu tượng) |
| `to_cua_bien` | SHIP | Cua / cát — OK |
| `to_tho_trang` | SHIP | Thỏ / vườn — OK |

### 2.5 Thiên nhiên & thời tiết

| id | Verdict C2 | Ghi chú |
|---|---|---|
| `to_cay_dua` | AGE_NOTE | Cây cao — chỉ được; “Rồi sao?” yếu (PH: “Hái dừa!” tưởng tượng) |
| `to_hoa_sen` | SHIP | Sen / nở — tốt |
| `to_hoa_mai` | SHIP | Mai Tết — tốt chỉ–gọi văn hóa |
| `to_ruong_lua` | **REPROMPT** | Cánh đồng phẳng → **cận cảnh 1 khóm lúa** hoặc bé đứng bờ (xa nhẹ) |
| `to_song_nuoc` | **REPROMPT** | Thuyền “far away” → thuyền **gần–vừa**, sông làm nền |
| `to_cau_vong` | **REPROMPT** | Cầu vồng + mái làng → cầu vồng chiếm khung; mái tối giản |
| `to_ong_trang` | SHIP | Ông trăng / trên trời / ngủ — nhân hóa giúp “Ai?” |
| `to_sao_dem` | **REPROMPT** | Sao trừu tượng, không agent → **1–2 sao lớn** + mép cửa sổ/nhà; hoặc bé chỉ tay |
| `to_may_trang` | **REPROMPT** | Mây thuần túy → **1** đám mây rõ; có thể thêm chim nhỏ phụ |
| `to_con_mua_xuan` | **REPROMPT** | Mưa + lá chuối → **1** lá chuối + vài giọt rõ |
| `to_soi_da` | **REPROMPT** | Nhiều viên đá → **1** viên nhẵn + tay bé (tuỳ chọn) |
| `to_hoa_phuong` | AGE_NOTE | Cụm hoa: chọn **1** chùm gần |

### 2.6 Đồ ăn

| id | Verdict C2 | Ghi chú |
|---|---|---|
| `to_bat_com` | SHIP | Bát cơm — chỉ–gọi + “Ăn nào!” |
| `to_qua_xoai` | SHIP | Xoài — tốt |
| `to_dua_hau` | SHIP | Dưa hấu — tốt |
| `to_cam_ngot` | SHIP | Cam bổ — tốt (không lưỡi dao lộ) |
| `to_hop_sua` | AGE_NOTE | Hộp + ly: ưu tiên hộp **hoặc** ly đang uống |
| `to_banh_mi` | SHIP | Bánh mì VN — tốt |
| `to_trung_luoc` | SHIP | Trứng — tốt |
| `to_rau_muong` | AGE_NOTE | Đĩa rau — gọi tên được; kém “Ai?” |
| `to_banh_chung` | SHIP | Bánh chưng Tết — văn hóa mạnh |
| `to_kem_que` | SHIP | Kem — tốt |

### 2.7 Thói quen & chơi

| id | Verdict C2 | Ghi chú |
|---|---|---|
| `to_rua_tay` | SHIP | Tay / rửa / sạch — chuỗi tốt |
| `to_danh_rang` | **REPROMPT** | “Parent silhouette” làm rối **Ai?** → bé đánh răng rõ; PH mặt nhẹ **hoặc** không có |
| `to_mac_ao` | SHIP | Bé / mặc áo / xong — tốt |
| `to_xep_khoi` | SHIP | Khối / xếp / đổ vui — tốt nếu tháp 1 tiêu điểm |
| `to_doc_sach` | SHIP | PH+bé / xem sách / nói chuyện tranh — **mẫu vàng ngôn ngữ** |
| `to_da_bong` | AGE_NOTE | Bóng + chân: bóng lớn hơn |
| `to_ve_sap` | SHIP | Sáp + giấy — OK (không chữ trên giấy) |
| `to_di_cho` | **REPROMPT** | Mẹ + sạp + quả đông → mẹ + **giỏ** + **1** loại quả gần |
| `to_tuoi_cay` | SHIP | Bé / tưới / cây lớn — tốt |
| `to_quet_nha` | SHIP | Chổi / quét — OK (có thể thêm tay bé nếu muốn “Ai?”) |
| `to_uong_nuoc` | SHIP | Bé / uống — tốt |
| `to_ngu_ngon` | SHIP | Bé / ngủ / gấu bông — tốt |

### 2.8 Văn hóa VN & đồ chơi

| id | Verdict C2 | Ghi chú |
|---|---|---|
| `to_non_la` | SHIP | Nón / treo / đội — chỉ–gọi văn hóa tốt |
| `to_ao_dai_me` | SHIP | Mẹ / mặc áo dài / đứng đẹp — tốt |
| `to_den_long` | SHIP | Đèn lồng / sáng / Trung thu — tốt |
| `to_trong_tre_em` | SHIP | Trống / đánh — tốt |
| `to_sao_tre` | **REPROMPT** | “Parade soft” = đông người → **bé + sao giấy**; không đám đông |
| `to_mere_tre` | AGE_NOTE | Mẹt trống — yếu hấp dẫn; cân nhắc 1 quả trên mẹt (vẫn 1 chủ đề mẹt) |
| `to_quat_nan` | SHIP | Quạt / để ghế / quạt — OK |
| `to_am_tra` | AGE_NOTE | Ấm + tách → ấm trà lớn làm chủ |
| `to_tranh_dong_ho` | **REPROMPT** | Giờ / số = trừu tượng + rủi ro **chữ/số** → mặt đồng hồ **không số chữ**; kim đơn giản **hoặc** đổi cảnh “chỉ đồng hồ treo tường” không “morning time” |
| `to_bong_mem` | SHIP | Bóng — tốt |
| `to_xe_do_choi` | SHIP | Xe gỗ — tốt |
| `to_gau_bong` | SHIP | Gấu bông (đồ chơi) — tốt |
| `to_bup_be` | SHIP | Búp bê — tốt |
| `to_thuyen_giay` | SHIP | Thuyền / nổi chậu — tốt chuỗi |
| `to_may_bay_giay` | SHIP | Máy bay giấy / bệ cửa sổ — OK |
| `to_o_to_bus` | SHIP | Xe buýt đồ chơi — tốt |
| `to_den_pin` | **REPROMPT** | “Beam” trừu tượng → **đèn pin cầm rõ** + vệt sáng nhỏ phụ |
| `to_chuong_xe` | AGE_NOTE | Close-up chuông — chỉ được; “Rồi sao?” = “Ring ring!” |
| `to_gio_xach` | SHIP | Giỏ + rau — OK nếu giỏ là khối chính |

### 2.9 Ngoài trời / địa điểm

| id | Verdict C2 | Ghi chú |
|---|---|---|
| `to_bus_that` | **REPROMPT** | Bus “far” + phố → xe buýt **gần vừa**, vỉa hè sạch, 1 tiêu điểm |
| `to_truong_mam_non` | AGE_NOTE | Cổng trường = địa điểm; PH hỏi “Đi học?” — không bắt “Ai?” nếu không có người |
| `to_cong_vien` | **REPROMPT** | Ghế + cây ngang → **1** ghế **hoặc** bé trên ghế + cây nền |
| `to_cau_truot` | SHIP | Cầu trượt thấp — tốt |
| `to_xich_du` | **REPROMPT** | Silhouette PH → mặt PH nhẹ hoặc chỉ bé trên xích đu |
| `to_ho_ca` | **REPROMPT** | Hồ + cá + hàng rào → **1–2 cá cam lớn**; hàng rào nền |
| `to_vuon_rau` | **REPROMPT** | Nhiều luống → **1** luống / **1** loại rau gần |
| `to_gian_hoa` | AGE_NOTE | Giàn hoa lan man → **1** chùm hoa rõ trên giàn |
| `to_o_khoa` | **DROP** hoặc **REPROMPT mạnh** | Ổ khóa close-up: kém từ vựng toddler + không chuỗi kể → đổi thành **bé/PH mở cửa** (nắm đấm cửa lớn) **hoặc** DROP |

---

## 3. Thống kê nhanh

| Verdict | Số lượng (ước) | Ghi chú |
|---|---|---|
| SHIP | ~58 | Chỉ–hỏi–kể ổn |
| REPROMPT | ~28 | Sửa scene trước gen |
| AGE_NOTE | ~13 | Giữ; PH dẫn câu hỏi |
| DROP (đề xuất) | 1 (`to_o_khoa`) hoặc REPROMPT thành “mở cửa” | |

---

## EDITS_REQUIRED

### P0 — Sửa scene trước gen (REPROMPT)

| # | id | Vấn đề C2 | Sửa scene (1 dòng cho Art) |
|---|---|---|---|
| 1 | `to_ca_nha_com` | Nhiều người cạnh tranh | Bé + 1 người lớn, 1 bát cơm gần; nền mờ |
| 2 | `to_di_cho` | Mẹ + sạp + quả đông | Mẹ cầm giỏ, **1** loại quả gần mặt |
| 3 | `to_mua` | Mưa + chuối + chum | Chọn **chum+mưa** hoặc **sân+giọt mưa**; 1 phụ kiện nền |
| 4 | `to_mat_troi` | Đồi + mái + trời | Mặt trời lớn giữa khung; nền tối giản |
| 5 | `to_la_cay` | Agent cầm lá mờ | Tay bé cầm 1 lá **hoặc** lá đơn |
| 6 | `to_chim_se` | Nhiều sẻ + dây điện | 1 sẻ trên cành; cấm power line |
| 7 | `to_vit_ao` | Số nhiều ducklings | 1 vịt con rõ |
| 8 | `to_sao_dem` | Sao trừu tượng | 1–2 sao lớn + mép nhà/cửa sổ |
| 9 | `to_may_trang` | Mây thuần | 1 đám mây rõ dạng |
| 10 | `to_ruong_lua` | Phong cảnh phẳng | Cận 1 khóm lúa xanh |
| 11 | `to_song_nuoc` | Thuyền quá xa | Thuyền vừa–gần, sông nền |
| 12 | `to_cau_vong` | Cầu vồng + mái làng | Cầu vồng chủ; mái tối giản |
| 13 | `to_sao_tre` | Parade đông | Chỉ bé + sao giấy |
| 14 | `to_bus_that` | Bus xa + phố | Bus gần vừa, 1 tiêu điểm |
| 15 | `to_cong_vien` | Ghế + cây ngang | 1 ghế (có/không bé) làm chủ |
| 16 | `to_ho_ca` | Hồ + cá + rào | 1–2 cá cam lớn |
| 17 | `to_vuon_rau` | Nhiều luống | 1 luống / 1 loại rau |
| 18 | `to_cua_so` | Cửa + cây + trời | Khung cửa + 1 cây ngoài |
| 19 | `to_tu_lanh` | Tủ + tô quả ngang | Chỉ tủ **hoặc** 1 quả làm phụ nhỏ |
| 20 | `to_danh_rang` | Silhouette PH | Bé rõ; PH mặt nhẹ hoặc bỏ |
| 21 | `to_xich_du` | Silhouette PH | Như trên |
| 22 | `to_tranh_dong_ho` | Số/giờ + trừu tượng | Không chữ/số trên mặt; kim đơn giản |
| 23 | `to_den_pin` | Beam trừu tượng | Thân đèn pin rõ + vệt sáng phụ |
| 24 | `to_con_mua_xuan` | Mưa + lá | 1 lá chuối + giọt mưa |
| 25 | `to_soi_da` | Nhiều đá | 1 viên đá nhẵn (có thể + tay) |
| 26 | `to_o_khoa` | Yếu chỉ–kể | **DROP** hoặc đổi: nắm cửa / mở cửa chào |

### P1 — AGE_NOTE / tinh chỉnh nhẹ (không chặn gen nếu Art bận)

| id | Gợi ý |
|---|---|
| `to_xe_dap` | Thu nhỏ hoặc bỏ mũ bảo hiểm cạnh |
| `to_san_gach` | Chậu cây lớn làm điểm chỉ |
| `to_ban_chai`, `to_hop_sua`, `to_am_tra` | 1 vật chính lớn hơn vật phụ |
| `to_buom_vang`, `to_hoa_phuong`, `to_gian_hoa` | 1 bướm/chùm hoa chiếm khung |
| `to_cay_dua`, `to_rau_muong`, `to_chuong_xe`, `to_truong_mam_non`, `to_mere_tre` | howToUse: PH hỏi “Cái gì?” / “Làm gì với…?”; không ép cốt truyện dài |
| `to_da_bong` | Bóng lớn hơn chân |

---

## 4. Gợi ý `storyPrompt` (1 dòng VI) — 20 mục gia đình / văn hóa tốt nhất

> Dùng làm gợi ý PH trên app (chỉ–hỏi–kể). Art **không** vẽ chữ này lên tranh.

| # | id | storyPrompt (gợi ý C2) |
|---|---|---|
| 1 | `to_me_bong_con` | Ai đây? Mẹ đang bế con — rồi mẹ hôn nhẹ lên má con. |
| 2 | `to_ba_ke_chuyen` | Ai ngồi trên chiếu? Bà kể chuyện — bé lắng nghe rồi cười. |
| 3 | `to_ong_dan_choi` | Ai dắt tay? Ông dẫn chơi ngoài sân — rồi cùng đi thêm một vòng. |
| 4 | `to_om_ba` | Ai ôm ai? Con ôm bà — bà cười, ôm chặt lại. |
| 5 | `to_chao_bo` | Ai ở cửa? Con vẫy chào bố — bố vào nhà, ôm con. |
| 6 | `to_me_hat_ru` | Ai hát? Mẹ ru con — rồi con lim dim ngủ. |
| 7 | `to_anh_em_choi` | Ai chơi với ai? Anh em xếp gỗ — rồi cùng xây thêm một nhà. |
| 8 | `to_doc_sach` | Ai xem sách? Mẹ/bố chỉ tranh — con nói “Đây!” rồi lật trang. |
| 9 | `to_di_cho` | Ai đi chợ? Mẹ xách giỏ — chọn quả ngon rồi về nhà. |
| 10 | `to_ao_dai_me` | Ai đẹp thế? Mẹ mặc áo dài — con chỉ “Áo mẹ!” rồi ôm mẹ. |
| 11 | `to_non_la` | Cái gì đây? Nón lá — đội lên đầu rồi ra nắng. |
| 12 | `to_den_long` | Cái gì sáng? Đèn lồng Trung thu — cầm lên rồi đi quanh sân. |
| 13 | `to_sao_tre` | Ai cầm sao? Con cầm sao giấy — giơ cao rồi bước nhẹ. |
| 14 | `to_banh_chung` | Cái gì vuông? Bánh chưng Tết — mở lá rồi ăn cùng nhà. |
| 15 | `to_hoa_mai` | Hoa gì vàng? Hoa mai ngày Tết — ngắm hoa rồi chúc nhau. |
| 16 | `to_chieu_coi` | Cái gì trải sàn? Chiếu cói — ngồi xuống rồi nghe bà kể. |
| 17 | `to_bat_com` | Cái gì đây? Bát cơm — xới cơm rồi ăn ngon. |
| 18 | `to_ngu_ngon` | Ai ngủ? Con ngủ với gấu bông — đèn ngủ sáng, ngủ ngon. |
| 19 | `to_tuoi_cay` | Ai tưới? Con tưới cây — cây uống nước rồi lớn thêm. |
| 20 | `to_trong_tre_em` | Cái gì đây? Trống trẻ em — đập nhẹ “tùng!” rồi cười. |

**Gợi ý thêm (văn hóa, không trong top 20):** `to_quat_nan` — “Quạt nan — quạt mát rồi ngồi nghỉ.” · `to_mere_tre` — “Mẹt tre — để quả lên rồi mang vào bếp.” · `to_hoa_sen` — “Hoa sen trên ao — sen nở rồi thơm nhẹ.”

---

## 5. Hướng dẫn PH ngắn (howToUse — cho C3 / copy)

1. Chỉ vào chủ thể: **“Đây là gì / ai?”**  
2. Hỏi hành động: **“Đang làm gì?”**  
3. Một bước tiếp: **“Rồi sao?”** (PH nói mẫu trước, bé nhại được thì dừng).  
4. Với tranh AGE_NOTE (mây, ruộng, cổng trường): dừng ở bước 1–2; không ép cốt truyện.

---

## 6. Top issues (cho Parent / C3)

1. **P0 đa chủ thể:** `ca_nha_com`, `di_cho`, `sao_tre`, `mua`, `bus_that`, `cong_vien`, `ho_ca` — dễ phá chỉ–hỏi.  
2. **P0 trừu tượng trời/nước:** `sao_dem`, `may_trang`, `ruong_lua`, `song_nuoc`, `cau_vong`, `mat_troi` — thiếu “Ai?”.  
3. **P0 agent mờ:** silhouette PH (`danh_rang`, `xich_du`), lá “held” (`la_cay`), beam đèn (`den_pin`).  
4. **Đề xuất DROP/đổi:** `o_khoa` — kém từ vựng & chuỗi kể.  
5. **Điểm mạnh giữ nguyên:** cụm gia đình ấm (`me_bong_con`, `ba_ke_chuyen`, `om_ba`, `chao_bo`, `doc_sach`) + đồ văn hóa rõ (`non_la`, `banh_chung`, `den_long`).

---

*Persona C2 · draft_unreviewed · không thay chuyên gia người thật.*
