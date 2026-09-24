# C1 — An toàn cảm xúc & phát triển 18–36 tháng

> App: **Hôm nay chơi gì?** · Persona: Child Expert **C1**  
> Nguồn: `00_CHARTER.md` + `STORY_ART_100.json` (100 subject)  
> Phạm vi: thẩm định **slug / title / scene (prompt)** — **không** gen ảnh, **không** sửa `catalog.json`.

```
STATUS: OK
SCOPE: Review all 100 STORY_ART subjects for toddler emotional safety & developmental fit (18–36m)
DONE: Full pass; SHIP / AGE_NOTE / DROP assigned; EDITS_REQUIRED listed for A1–A2 / C3
FILES: qa/experts/art/01_C1_an_toan_cam_xuc.md
DEVIATIONS: none
BLOCKERS: none
ERRORS: none
NEXT_FOR_PARENT: Apply DROP replacements + AGE_NOTE ageHint/howToUse before batch image gen; C2 language pass; C3 P0 gate
COUNTS: reviewed=100 | SHIP=84 | AGE_NOTE=15 | DROP=1 | REPROMPT_notes=0 (catalog-level; see EDITS)
```

## Tiêu chí C1 (tóm tắt)

| Ưu tiên SHIP | Cờ DROP / AGE_NOTE |
|---|---|
| Gia đình ấm, đồ nhà quen | Đêm đáng sợ, bão tối |
| Động vật dịu, không nhe răng / cắn | Cô lập / bị khóa / xa caregiver |
| Thức ăn = thức ăn | Lửa / pháo / pháo hoa |
| Chơi mềm, sân nhà | Giao thông nguy hiểm |
| | Đồ ăn trình bày như đồ chơi (nghẹn) |
| | Xấu hổ / trách móc |
| | Vũ khí |
| | Động vật tấn công / càng–răng đe dọa |

Verdict theo charter: `SHIP` · `AGE_NOTE` · `DROP` (không gen trong bước này).

---

## Bảng non-SHIP (16 mục)

| # | slug | title | Verdict | Lý do (18–36m) |
|---|---|---|---|---|
| 1 | `bus_that` | Xe buýt đường phố | **DROP** | Scene đường phố thật + xe buýt = **traffic danger**; đã có `o_to_bus` (xe đồ chơi) đủ từ vựng an toàn. |
| 2 | `sao_dem` | Sao đêm | **AGE_NOTE** | Bầu trời đêm — một số trẻ sợ tối; prompt “not dark-scary” tốt nhưng cần ageHint + dùng cùng caregiver lúc đi ngủ. |
| 3 | `ong_trang` | Ông trăng | **AGE_NOTE** | Chủ đề đêm; mặt trăng “friendly” OK — ghi howToUse: kể mềm, ánh sáng ấm trong phòng, không phòng tối đen. |
| 4 | `den_pin` | Đèn pin | **AGE_NOTE** | Chùm sáng trên sàn gợi chơi trong bóng tối → dễ kích hoạt sợ đêm; giữ phòng sáng dịu, caregiver cùng chơi. |
| 5 | `ngu_ngon` | Ngủ ngon | **AGE_NOTE** | Em ngủ một mình + đêm — có thể gắn tách biệt; nhấn night light + thú bông, không kể “bỏ một mình trong tối”. |
| 6 | `vit_ao` | Con vịt | **AGE_NOTE** | Bờ ao / nước — không phải bão, nhưng nước mở với toddler cần howToUse: chỉ nhìn tranh / luôn có người lớn gần nước thật. |
| 7 | `song_nuoc` | Sông nước | **AGE_NOTE** | Sông + thuyền xa — nước mở; ageHint: quan sát từ xa, không mô phỏng “tự xuống sông”. |
| 8 | `ho_ca` | Hồ cá | **AGE_NOTE** | Hồ / ao nhà — nước + tò mò với toddler; giữ “safe fence feel”, howToUse không khuyến khích với tay xuống nước không giám sát. |
| 9 | `cua_bien` | Cua | **AGE_NOTE** | Cua gắn càng / kẹp — gần **động vật cắn–kẹp**; gen phải mềm, càng cụp, không tư thế đe dọa. |
| 10 | `truong_mam_non` | Cổng trường mầm non | **AGE_NOTE** | Cổng trường kích hoạt **tách biệt / xa mẹ** mạnh ở 18–36m; dùng khi đã quen gửi trẻ, kể “mẹ/bố ở gần”, không ép “phải vào một mình”. |
| 11 | `danh_rang` | Đánh răng | **AGE_NOTE** | “Parent nearby silhouette” dễ đọc thành bóng mờ / vắng mặt — đổi thành bố/mẹ rõ nét, ấm (xem EDITS). |
| 12 | `xich_du` | Xích đu | **AGE_NOTE** | Cùng vấn đề silhouette + cao hơn mặt đất nhẹ; caregiver hiện rõ, xích đu thấp. |
| 13 | `sao_tre` | Sao giấy Trung thu | **AGE_NOTE** | Trung thu thường buổi tối / đám đông; que cầm có thể đâm; giữ ban ngày hoặc ánh sáng lễ hội dịu, que ngắn, caregiver cạnh. |
| 14 | `soi_da` | Hòn đá | **AGE_NOTE** | Sỏi nhỏ gần **nghẹn nếu coi là đồ chơi bỏ miệng**; howToUse: chỉ chỉ–kể, không khuyến khích cho vào miệng. |
| 15 | `kem_que` | Kem que | **AGE_NOTE** | Que kem = nguy cơ nghẹn / đâm với <36m; food-as-food OK nhưng ageHint: giám sát, hoặc ưu tiên kem ly không que khi kể thực tế. |
| 16 | `o_khoa` | Ổ khóa cửa | **AGE_NOTE** | Ổ khóa gần **cô lập / bị nhốt / không mở được tới caregiver**; scene phải “then cài mở / cửa mở”, không khóa chặt một mình. |

---

## Tổng hợp SHIP (84) — không liệt kê chi tiết

Các slug còn lại **SHIP** theo C1: gia đình ấm (`me_bong_con`, `ba_ke_chuyen`, `om_ba`, `ca_nha_com`…), đồ nhà (`dep`, `coc`, `chieu_coi`, `non_la`…), động vật dịu (`ga`, `meo`, `cho_con` no teeth bare, `heo_con`, `tho_trang`…), thức ăn đúng vai trò thức ăn (`chuoi`, `bat_com`, `banh_chung`…), chơi mềm (`xep_khoi`, `bong_mem`, `gau_bong`…), thời tiết nhẹ (`mua`, `con_mua_xuan`, `cau_vong`), lễ hội **đã loại pháo** (`hoa_mai` no firecrackers, `den_long` no fire).

Ghi nhận tích cực trong prompt: `cho_con` cấm nhe răng; `hoa_mai` / `den_long` chủ động tránh lửa–pháo; mưa là drizzle/gentle, không bão tối.

---

## EDITS_REQUIRED

### DROP (1) — thay chủ đề trước gen

| slug | Hành động | Gợi ý thay (cùng “giao thông quen” an toàn) |
|---|---|---|
| `bus_that` | **DROP** khỏi lô 100 / không gen | Đã có `o_to_bus`. Thay bằng ví dụ: `xe_dap_san` (xe đạp nhỏ trong sân nhà, đã gần `xe_dap`), `cho_ngoi_xe_day` (xe đẩy em bé trong sân), hoặc `bien_bao_den_xanh` chỉ màu đèn giao thông đơn giản nhìn từ vỉa hè với tay mẹ — **không** đường phố đông xe. |

### AGE_NOTE (15) — giữ slug, bổ sung `ageHint` / `howToUse` (C3 / applier)

| slug | ageHint / howToUse (gợi ý ngắn) | Reprompt scene nếu cần |
|---|---|---|
| `sao_dem` | Đi ngủ cùng người lớn; phòng có đèn ngủ | Giữ trời xanh–tím dịu, vài sao, **không** đen đặc / mây đe dọa |
| `ong_trang` | Kể “ông trăng cười”, có đèn phòng | Mặt trăng tròn mềm, mây bông, không bóng ma / mắt đỏ |
| `den_pin` | Chơi đèn pin ban ngày hoặc phòng sáng | Chùm sáng vui, sàn sáng, **không** bóng dài đáng sợ |
| `ngu_ngon` | Thói quen ngủ; nhấn “mẹ/bố ở gần” | Đèn ngủ rõ, thú bông, mặt ngủ yên, không phòng tối trống |
| `vit_ao` | Chỉ tranh; nước thật luôn có NL | Vịt con gần mép nông, nước trong, không sóng lớn |
| `song_nuoc` | Quan sát từ bờ / tranh | Sông phẳng, thuyền xa nhỏ, không mưa bão |
| `ho_ca` | Không thọc tay không giám sát | Hàng rào / thành hồ rõ, cá cam dịu |
| `cua_bien` | “Cua đi ngang”, không chạm càng thật | Càng cụp, mắt vui, không giơ càng tấn công |
| `truong_mam_non` | Chỉ khi đã quen gửi; kể người lớn đưa đón | Cổng sáng buổi sáng, cây xanh, **có** bóng mẹ/bố gần (không chỉ cổng trống) |
| `danh_rang` | Thói quen vệ sinh cùng bố/mẹ | **Bỏ silhouette** → phụ huynh đầy đủ, ấm, đứng cạnh |
| `xich_du` | Xích đu thấp, NL đẩy | **Bỏ silhouette** → phụ huynh rõ; ghế xích đu thấp |
| `sao_tre` | Trung thu có NL cầm tay; ban ngày hoặc ánh sáng lễ hội | Sao giấy mềm, que ngắn, không lửa nến lộ |
| `soi_da` | Chỉ–kể, không cho miệng | Đá lớn mịn (không sỏi hạt), cạnh nước nông |
| `kem_que` | Giám sát khi ăn thật; <24m cân nhắc kem không que | Que ngắn, kem không nhỏ vụn dễ nghẹt trong tranh |
| `o_khoa` | “Mở cửa chào bố/mẹ”, không khóa một mình | Then **mở** / cửa hé, ánh sáng ấm phía sau — không ổ khóa đóng chặt |

### Reprompt nhẹ (vẫn SHIP nếu sửa prompt — không đổi verdict bảng trên)

| slug | Ghi chú A1 |
|---|---|
| `banh_mi` | Scene “cutting board” — **cấm dao** trong khung hình (vũ khí / sắc). Chỉ ổ bánh mì + thớt trống. |
| `noi_com` / `am_tra` | Hơi nước / ấm trà: giữ “safe, calm”; không lửa trần, không tay trẻ chạm nồi. (Không AGE_NOTE catalog bắt buộc; howToUse caregiver tùy chọn.) |
| `chim_se` | “Power line” OK văn hóa VN; ưu tiên nhánh cây nếu muốn tránh gợi ý điện. |
| `tranh_dong_ho` | C1 cảm xúc: SHIP. (A3: số trên đồng hồ = chữ — ngoài scope C1.) |

### Không yêu cầu

- Không gen PNG trong bước C1.  
- Không sửa `catalog.json` tại đây.  
- Không DROP hàng loạt chủ đề đêm đã có đèn / mẹ (`den_ngu`, `me_hat_ru`) — **SHIP** (đồng điều tiết cảm xúc).

---

## Kết luận gửi Parent / C3

| Metric | Giá trị |
|---|---|
| Đã duyệt | **100** |
| **SHIP** | **84** |
| **AGE_NOTE** | **15** |
| **DROP** | **1** (`bus_that`) |
| An toàn cảm xúc tổng thể | Danh mục **ổn để tiếp tục** sau khi DROP 1 + gắn AGE_NOTE 15 trước gen hàng loạt |

**STATUS: OK** · DROP=**1** · AGE_NOTE=**15**
