# X2 — Phát triển trẻ & an toàn sinh hoạt (Gấu Con)

> Persona: chuyên gia phát triển trẻ + an toàn sinh hoạt (kiểm thử)  
> Workspace: `ideas/BeGau` · Wave 4 · **Không** sửa seed/code/la bàn  
> Nguồn: `plans/WAVE4_EXPERT_QA.md`, `plans/CONSENSUS.md`, `research/02–04`, `content/seed/activities_mvp_draft.json` (90 HĐ), `web/qa/CONTENT_QA_20260924.md`, stub web `web/src/app.mjs`  
> **Không** chẩn đoán y tế · **Không** claim “đã chuyên gia duyệt”

---

## 0. Kết luận ngắn

Seed 90 HĐ **có trường `safety` trên mọi mục** (PASS kỹ thuật). Phần lớn HĐ vận động/leo/cầu thang đã có cảnh báo hợp lý và **không** xâm phạm module nguy hiểm của *Bé Gấu An Toàn*. Rủi ro còn lại tập trung ở: (1) HĐ có **đồ nhỏ / hạt / lục lạc DIY** — hóc; (2) vài `safety` **quá ngắn hoặc lệch sang “tone”** thay vì rủi ro vật lý; (3) web **chưa có checklist mốc thật** + **chưa có cẩm nang**; (4) band tuổi vài HĐ rộng 18–36m hoặc thói quen an toàn miệng **bắt đầu muộn (24m)**.

---

## 1. Rà safety — HĐ thiếu/yếu & nguy cơ hóc / té / bỏng / đồ nhỏ

### 1.1. Tổng quan (bằng chứng)

| Chỉ số | Kết quả |
|---|---|
| Số HĐ | 90 · mọi mục có `safety` không rỗng |
| `safety` ngắn (&lt; ~40 ký tự) | ~16 mục — chủ yếu COG/SOC/AES; một số vẫn đủ nghĩa, một số **thiếu rủi ro vật lý** |
| Primary quota | 16/14/16/14/12/18 OK (CONTENT_QA) |

Phân loại bên dưới: **bằng chứng** = đọc trực tiếp seed; **đề xuất** = khuyến nghị Wave 5; không phải chẩn đoán lâm sàng.

### 1.2. P0 — nguy cơ hóc / đồ nhỏ (ưu tiên sửa copy `safety` + materials)

| ID | Tuổi | Vấn đề | Đề xuất (không sửa trong Wave 4) |
|---|---|---|---|
| `AES-08` Lục lạc chai nhựa | 18–30 | Materials: gạo/đậu trong chai; 18m vẫn hay bỏ miệng; nắp bung = **hóc hàng loạt** | Ưu tiên *easier* = lục lạc mua sẵn; DIY chỉ ≥24m + dán nắp + kiểm tra trước mỗi lần; cấm hạt nhỏ tuyệt đối; thêm “cất ngay sau chơi” |
| `phy_14_chuyen_nuoc_coc` | 24–36 | Materials cho phép **gạo/đậu**; safety có nhắc hóc nhưng vẫn mở cửa dùng hạt | Mặc định **chỉ nước**; hạt chỉ ≥30m + giám sát 100% + cất ngay; hoặc tách 2 HĐ |
| `phy_11_xau_nui_ong` | 30–36 | Đúng band CDC string ~3y; vẫn **rủi ro hóc cao** | Giữ; bổ sung kích thước tối thiểu (vd. không lọt ống nghiệm ~3 cm) + cấm làm vòng đeo cổ thật |
| `AES-06` Đất nặn bột mì | 24–36 | Nuốt bột/muối; công thức DIY | Giữ “không nuốt”; thêm không dùng nếu bé đang miệng; lượng muối thấp / hoặc đánh dấu *optional high-supervision* |
| `cog_13_nhom_theo_mau` | 26–36 | Kẹp áo có thể nhỏ | Safety hiện “kẹp không quá nhỏ” — siết: kẹp to / khối màu thay kẹp nếu &lt;30m |
| `act_sc_eat_024_03` Bữa ăn có khay | 24–36 | Safety chống hóc **ổn** (hạt cứng, quả tròn) | Giữ; nhắc cắt dọc quả tròn (nho/cà chua) nếu có trong nhà |

### 1.3. P0/P1 — té / leo / cầu thang

| ID | Tuổi | Nhận xét |
|---|---|---|
| `phy_02_leo_ghe_thap` | 18–26 | Khớp CDC leo ghế ~18m; safety ghế xoay/cao OK. **Thiếu**: chiều cao ghế tối đa, sàn không trơn, người lớn **giữ** khi xuống (steps có giữ hông — tốt). Đề xuất: 1 câu “không đứng trên ghế để với đồ cao” (tránh trượt sang `cog_09`). |
| `phy_07_bac_thang_tay_vin` | 24–36 | Safety mạnh + ghi “không thay module an toàn chuyên sâu” — **mẫu tốt**. |
| `cog_09_ghe_thap_voi_do` | 28–36 | Cấm ghế cao/bàn + trỏ *Bé Gấu An Toàn* — **mẫu tốt**. Rủi ro: PH hiểu nhầm “được leo” → giữ ageMin ≥28, không hạ tuổi. |
| `phy_08_nhay_hai_chan` | 28–36 | Band hợp CDC ~30m; safety không nhảy giường/sofa — OK. Bổ sung: không nhảy gần cạnh bàn kính. |
| `phy_06_chay_san_ngan` | 24–32 | Tránh bếp/WC ướt — OK. |
| `phy_16_bo_ham_goi` | 18–30 | Hầm gối: cần “không đè gối lên mặt / không bịt kín” nếu chưa có trong safety (kiểm tra Wave 5). |
| `phy_01_di_theo_duong_goi` | 18–24 | Safety ngắn nhưng đúng trọng tâm té/leo gối. |

### 1.4. Bỏng / nóng / bếp

| ID | Nhận xét |
|---|---|
| `act_sc_safe_024_16` Nóng — dừng tay | Đúng mức **sinh hoạt**: không dùng đồ sôi; khoảng cách bếp/gas. **Không** dạy chữa cháy/gas — đúng ranh giới. |
| `act_sc_eat_024_04` / `act_sc_eat_030_05` | Có WC/bếp + nước ấm / gần bếp — OK. Bổ sung P1: tắt vòi nước nóng trước khi bé với. |
| `act_sc_safe_030_18` Một quy tắc trước bữa | **`safety` hiện chỉ về giọng/tone** (“không kiểm tra điểm”) — **yếu đối với HĐ SC_SAFE**. Cần thêm: ngồi khi ăn, không chạy khi miệng còn đồ, thổi nguội — dù goal đã nhắc quy tắc. |

### 1.5. Nước / trượt / WC

| ID | Nhận xét |
|---|---|
| `act_sc_potty_024_14` Rửa tay sau bô | Safety vệ sinh tay người lớn + nước vừa — thiếu **ghế đẩu chống trượt / không để bé một mình WC** (đã có ở `act_sc_eat_024_04` — nên đồng bộ). |
| `act_sc_potty_024_12` | Safety = tone cảm xúc (đúng cho HĐ ngôn ngữ tín hiệu); rủi ro vật lý thấp — chấp nhận. |
| Không thấy HĐ tắm bể / chơi nước sâu | **Tốt** — tránh đuối nước (thuộc *An Toàn*). |

### 1.6. Dây / quấn cổ

Ổn ở `cog_02_day_xe_do_choi`, `phy_12_mac_quan_rong`, `phy_17_vac_tui_nhe`. Đề xuất P1: chuẩn hóa câu “dây ≤ chiều dài không vòng qua cổ + người lớn giữ đầu dây”.

### 1.7. `safety` lệch loại (tone / cảm xúc thay vì vật lý)

Hợp lệ khi HĐ thuần xã hội (`SOC-06`, `SOC-12`, `SOC-14`, `LANG-12`). **Không hợp lệ** khi domain/mục tiêu là an toàn thao tác: đặc biệt `act_sc_safe_030_18`.

---

## 2. Phù hợp tuổi `ageMinMonths` / `ageMaxMonths` — HĐ lệch band

### 2.1. Nguyên tắc đối chiếu (tham chiếu, không chẩn đoán)

- Band app: 18–24 / 24–30 / 30–36; filter inclusive theo CONSENSUS.  
- neo quan sát: CDC *Learn the Signs* (18m / 2y / 30m / 3y) + mục tiêu `research/02`, `04` — **gợi ý**, không percentile UI.

### 2.2. Khớp tốt (giữ)

| Kỹ năng | HĐ | Band seed | Ghi chú |
|---|---|---|---|
| Leo ghế thấp | `phy_02` | 18–26 | Khớp CDC 18m |
| Đá bóng | `phy_05` | 22–30 | Gần CDC 2y |
| Bậc thang | `phy_07` | 24–36 | Khớp CDC 2y+ |
| Nhảy hai chân | `phy_08` | 28–36 | Khớp CDC 30m |
| Xâu nui lớn | `phy_11` | 30–36 | Khớp CDC 3y |
| Mặc/kéo quần | `phy_12`, `act_sc_potty_030_13` | 30–36 | Khớp |
| Làm quen bô (không ép sạch tã) | `act_sc_potty_018_10` | 18–24 | **Phù hợp** làm quen sớm; không phải mốc “sạch tã” |

### 2.3. Lệch / cần chỉnh (đề xuất)

| ID | Hiện tại | Vấn đề | Đề xuất |
|---|---|---|---|
| `act_sc_safe_024_17` Đồ nhỏ không vào miệng | 24–36 | Thói quen chống hóc **cần hơn ở 18–24** (oral exploration) | Hạ `ageMin` → **18** hoặc thêm bản 18–24 |
| `AES-08` | 18–30 | DIY hạt quá sớm | `ageMin` DIY ≥ **24**; 18–23 chỉ lục lạc sẵn |
| `phy_14` + hạt | 24–36 | Hạt sớm | Nước từ 24; hạt ≥30 |
| Nhiều HĐ span **18–36** (`LANG-03/14`, `SOC-02/04`, `AES-02/03/05/07`, `act_sc_sleep_018_07`…) | span=18 | Không “sai” nhưng `harder` có thể quá khó với 18m | P1: gắn điều kiện tuổi trong `harder` (đã có vài chỗ 24m+/30m+ — nhân rộng) |
| `cog_09` với đồ | 28–36 | Đúng là muộn | **Không** hạ tuổi |
| `AES-05` Nhún nhảy theo nhạc | 18–36 | Không phải nhảy hai chân rời đất — **OK** nếu copy không yêu cầu nhảy thật | Giữ; tránh đồng nhất với `phy_08` |

### 2.4. Phủ picker theo tuổi (bằng chứng)

| ageMonths | Tổng HĐ khớp | SELF_CARE primary |
|---|---:|---:|
| 18 | 29 | 6 |
| 24 | 69 | 14 |
| 30 | 79 | 16 |
| 36 | 65 | 13 |

**18m mỏng hơn** (đúng kỳ vọng MVP) — ưu tiên không rút SC_SAFE khỏi band này.

---

## 3. Checklist mốc (web stub) — nên thêm gì & disclaimer

### 3.1. Hiện trạng web (bằng chứng)

`renderProgress()` (`web/src/app.mjs`): chỉ đếm HĐ tuần + đoạn *“Checklist đầy đủ sẽ có trên app…”* + “Nếu lo lắng, nên hỏi bác sĩ.”  
Banner disclaimer toàn cục **có thể dismiss** (`disclaimerDismissed`) — với tab Phát triển, **không đủ** cho checklist.

### 3.2. Nên thêm (stub web / S08 app) — chỉ mốc đã neo

Theo CONSENSUS §4 mục 5: **không** hiện `*_hyp_*` trên UI MVP.

**A. Banner cố định (không dismiss) trên khối mốc**

> “Mỗi bé một nhịp riêng. Đây là gợi ý theo dõi — **không** chẩn đoán, **không** so sánh với trẻ khác, **không** thay khám bác sĩ. Nếu lo lắng hoặc mất kỹ năng đã có, nên hỏi bác sĩ / cơ sở y tế.”

**B. Bộ stub tối thiểu theo tuổi bé (3 trạng thái: Đã làm được / Đang tập / Chưa)**

| Nhóm | Nguồn đề xuất | Ví dụ id (từ research) |
|---|---|---|
| Thể chất | CDC 18/24/30/36 đã liệt kê `research/02` | `phy_cdc_18_walk`, `phy_cdc_24_run`, `phy_cdc_30_jump`, … |
| Tự lập | `ms_sc_*` trong `research/04` | ăn / ngủ / bô / ngồi khi ăn / nghe “nóng” |
| Ngôn ngữ + XH | checklist C trong `research/03` (rút 4–6 mục/band) | làm theo 1 bước; cụm 2 từ; chỉ tranh; luân phiên nhẹ |

**C. Không thêm trên stub**

- Percentile, “đạt chuẩn”, so sánh bạn bè  
- Tự kết luận chậm phát triển / tự kỷ / ADHD  
- Mốc giả định `phy_hyp_stack_*` (nội bộ Picker thôi)

**D. CTA nhẹ**

- “Chưa” nhiều quanh tuổi checklist → nhắc hỏi bác sĩ (trung tính)  
- Deep-link 1 HĐ liên quan (vd. `ms_sc_eat_02` → `act_sc_eat_018_01`) khi có

---

## 4. Ranh giới với *Bé Gấu An Toàn*

### 4.1. Đã đúng hướng (giữ)

- CONSENSUS: Gấu Con = `safety` 1–2 câu + `SC_SAFE_DAILY`; **không** module cháy / điện / nước / đường / cấp cứu.  
- Seed có cross-link mẫu: `cog_09`, `phy_07` — nhắc không thay module chuyên sâu.  
- Không có HĐ diễn tập thoát hiểm, đuối nước, giao thông, gọi 114, gas cháy.

### 4.2. Ranh giới nội dung (để Wave 5 / X5 không tràn)

| Thuộc **Gấu Con** | Thuộc ***Bé Gấu An Toàn*** (không nhét vào seed BeGau) |
|---|---|
| Ngồi mới ăn; nóng—dừng tay (đồ ấm mẫu); đồ nhỏ không vào miệng; rửa tay; ghế thấp có người lớn | Cháy/gas/thoát hiểm; ổ điện/dây điện chuyên sâu; đuối nước / bể bơi / xô nước; ATGT đường; cấp cứu |
| Đi bậc thang **có tay vịn + người lớn** như vận động | Module cầu thang / té cao như kịch bản nguy hiểm |
| Thói quen bếp mức sinh hoạt (`guide_an_toan_bep` outline) | Bài cháy–gas–điện đầy đủ |

### 4.3. Cảnh báo trùng nhẹ (không phải trùng module nguy hiểm)

- `cog_09` “với đồ bằng ghế” gần biên — **giữ ageMin cao + cấm tủ/bàn**.  
- Không mở rộng “leo tủ”, “mở bếp một mình”, “chơi nước trong xô”.

---

## 5. Cẩm nang / SELF_CARE còn thiếu

### 5.1. Seed SELF_CARE primary (18) — phủ nhánh

| Nhánh | Có trong seed | Thiếu / yếu |
|---|---|---|
| Ăn | thìa, cốc, khay, rửa tay trước ăn, dọn thìa | HĐ “thổi nguội” tách riêng; cầm ăn tay mềm 18–24 (một phần nằm PHYSICAL) |
| Ngủ | nghi thức 4 bước, hát ru, tắt đèn, lấy gối | Ít về **môi trường ngủ** (ánh sáng/tiếng ồn) dạng HĐ ngắn |
| Bô | làm quen, nhắc ngồi, tín hiệu, kéo quần, rửa tay sau | OK cho MVP; giữ tone không ép deadline |
| SC_SAFE | ngồi mới ăn, nóng, đồ nhỏ, quy tắc trước bữa | `act_sc_safe_024_17` muộn tuổi; `030_18` safety lệch; thiếu bản 18–24 chống hóc |

Dual: `phy_12_mac_quan_rong` (PHYSICAL + SELF_CARE) — chấp nhận theo CONSENSUS owner vật lý.

### 5.2. Cẩm nang

| Trạng thái | Chi tiết |
|---|---|
| Research D | Outline **9** bài `guide_*` (ăn vạ, biếng ăn, khó ngủ, ngủ trưa, tập bô, tè đêm, rửa tay, ngồi ăn, an toàn bếp mức SH) |
| CONSENSUS P0 | Ship **≥ 4**: ăn vạ, biếng ăn, khó ngủ, tập bô |
| Web / seed JSON | **0 bài** cẩm nang · không có tab S10 trên web pilot |

**Thiếu so với SPEC ~20 bài (toàn app):** toàn bộ còn lại (cảm xúc XH, ngôn ngữ…) — ngoài scope D nhưng X5 cần backlog.

**Cấm trong cẩm nang (nhắc lại):** chẩn đoán; Melatonin; deadline sạch tã; thực đơn trị liệu; thay module cháy/điện/nước.

---

## 6. Ưu tiên P0 / P1 / P2

### P0 — trước khi coi nội dung “an toàn đủ cho pilot phụ huynh rộng”

1. Siết hóc: `AES-08`, `phy_14` (mặc định nước), củng cố `phy_11` kích thước + không vòng cổ.  
2. Viết lại `safety` của `act_sc_safe_030_18` (thêm rủi ro ăn uống / nóng / ngồi).  
3. Checklist stub web: banner disclaimer **không dismiss** + 6–12 mốc neo CDC/`ms_sc_*` theo tuổi (3 trạng thái).  
4. Hạ tuổi hoặc thêm bản 18–24 cho thói quen “đồ nhỏ không vào miệng”.  
5. Không thêm module nguy hiểm trùng *An Toàn*; giữ cross-link mẫu `phy_07` / `cog_09`.

### P1 — Wave 5 nội dung

1. Ship ≥ 4 cẩm nang CONSENSUS + CTA sang HĐ.  
2. Đồng bộ safety WC/trượt cho mọi HĐ rửa tay / bô.  
3. Nhân điều kiện tuổi trong `harder` cho HĐ span 18–36.  
4. Bổ sung HĐ/câu safety “thổi nguội” / cắt quả tròn nếu materials có trái cây thật (`LANG-07`).  
5. Rà `phy_16` hầm gối (thở/mặt).  
6. `reviewed_by` vẫn rỗng — UI tiếp tục không claim duyệt chuyên gia.

### P2 — sau MVP

1. Đủ ~20 cẩm nang; `guide_te_dem`, `guide_ngu_trua`, …  
2. Mốc `hyp_*` chỉ nội bộ Picker.  
3. Deep-link sản phẩm sang *Bé Gấu An Toàn* (ngoài app) khi PH cần an toàn chuyên sâu.  
4. Mở rộng 0–6 tuổi + rà lại band.

---

## 7. Phân loại phát biểu (minh bạch)

| Loại | Ví dụ trong báo cáo |
|---|---|
| Quan sát có bằng chứng | 90/90 có `safety`; web Progress không có checklist; 0 cẩm nang ship; tuổi/materials đọc từ JSON |
| Giả định | PH có thể hiểu `cog_09` là được leo tủ nếu thiếu copy; 18m “mỏng” ảnh hưởng trải nghiệm |
| Đề xuất | P0–P2 ở §6; chỉnh age/materials Wave 5 |

---

## Báo cáo phối hợp (child → parent)

```
STATUS: OK
SCOPE: X2 — rà safety HĐ, ageMin/Max, checklist stub web, ranh giới Bé Gấu An Toàn, SELF_CARE/cẩm nang, xếp P0/P1/P2; chỉ ghi qa/experts/02_phat_trien_an_toan.md
DONE: Đã đọc WAVE4/CONSENSUS/CONTENT_QA + research 02–04 + seed 90 HĐ + stub Phát triển web; hoàn tất báo cáo X2
FILES: qa/experts/02_phat_trien_an_toan.md
DEVIATIONS: Skill tam-ly-tre-em không tìm thấy trên máy — không dùng; không đọc toàn bộ research 03 (chỉ phần checklist/nguyên tắc)
BLOCKERS: Không (chờ X1/X3/X4 rồi X5 gộp)
ERRORS: Không
NEXT_FOR_PARENT: Cho X5 gộp P0 hóc + checklist disclaimer; không sửa seed đến Wave 5; giữ ranh giới An Toàn
```
