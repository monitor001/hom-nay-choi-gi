# R1 — Economy / bảng giá Gấu Xu

> Expert R1 · Virtual economy · App **Hôm nay chơi gì?** (PH companion, 18–36 tháng)  
> Ngày: 2026-09-24 · Chỉ kế hoạch — **không** sửa code production  
> Nguồn: `00_CHARTER.md` · `plans/CONSENSUS.md`

---

## STATUS

```
STATUS: OK
SCOPE: Thiết kế đơn vị tiền ảo + earn rates + daily cap + streak mềm + catalog quy đổi (Experience / Play / FoodTreat / SharedMoment) với giá GX và gợi ý VND khoảng
DONE:
  - Chốt tên tiền: Gấu Xu (GX)
  - Bảng earn ngày + weekly budget 1 bé
  - Catalog ~20 mục theo tier + nhãn FoodTreat
  - Conversion math (kẹo mút vs đồ chơi)
FILES: qa/experts/rewards/01_R1_economy.md
DEVIATIONS:
  - CONSENSUS §6 loại «coin» khỏi MVP roadmap; bản này thiết kế economy mềm (PH-facing, cap cứng, không slot) → đề xuất **P1 opt-in** hoặc «Gợi ý đổi thưởng» offline, không ship như máy slot. R4 chốt phase.
  - Streak chỉ bonus mềm (+GX), không guilt / không «đừng phá chuỗi» (khớp tone CONSENSUS)
BLOCKERS: Không (chờ R2 guardrail nuôi dưỡng + R3 anti-abuse chi tiết + R4 chốt số)
ERRORS: Không
NEXT_FOR_PARENT:
  - Xác nhận GX vs đổi tên; xác nhận FoodTreat có hiện catalog mặc định hay ẩn sau toggle PH
  - R4: PASS số liệu + phase MVP (khuyến nghị: catalog + earn log P1; không IAP)
VERDICT: PASS (khớp charter; lệch nhẹ CONSENSUS «loại coin MVP» → ghi rõ phase, không gây nghiện)
```

---

## 1. Đơn vị tiền — Gấu Xu (GX)

**Giữ tên Gấu Xu (GX).** Lý do:

| Tiêu chí | Đánh giá |
|---|---|
| Brand | «Gấu» gắn app; gần gũi PH VN |
| Softness | «Xu» = điểm mềm / phiếu đổi, không «coin crypto / vàng» |
| UI | 2 ký tự `GX`, dễ chip nhỏ |
| Tone | Không cạnh tranh leaderboard; không cảm giác cược |

**Không đổi** sang «Sao», «Tim», «Kim cương» — dễ gợi collection nghiện hoặc gamify trẻ. GX chỉ hiện trên màn PH (Đổi thưởng / Nhật ký thưởng), **không** badge nổi trên màn chơi của bé (app vốn không phải màn trẻ).

**Bản chất kinh tế:**

- GX = **phiếu ghi nhận** PH đã chơi–dạy thật với con (complete activity ± nhật ký nhẹ).
- **Đổi thưởng** = PH tự mua/chuẩn bị ngoài đời theo bảng gợi ý. App **không** ship hàng, **không** IAP bắt buộc, **không** ads.
- 1 GX **không** map cố định ra VND trong ledger; cột VND catalog chỉ là **hint khoảng** giúp PH hình dung chi phí thật.

---

## 2. Giả định cân bằng (baseline)

| Giả định | Giá trị | Ghi chú |
|---|---|---|
| KPI SPEC | ≥ 3 HĐ hoàn thành / gia đình / tuần | Không tối đa thời gian app |
| Target thiết kế | **3–5 HĐ / tuần** | «Healthy band» |
| Độ dài HĐ | 5–15 phút offline | Earn gắn complete, không gắn phút mở app |
| Máy 1 bé | 1 hồ sơ | Budget ví dụ §6 |
| Mục tiêu UX | Offline play > grind app | Daily cap cứng |
| Streak | Soft | Nghỉ không mất số dư; không copy «bạn đã phá chuỗi» |

---

## 3. Earn rates & daily cap

### 3.1. Nguồn kiếm GX

| Hành động | GX | Điều kiện | Ghi chú anti-addiction |
|---|---:|---|---|
| Hoàn thành 1 hoạt động (S06) | **+18** | 1 lần / activityId / ngày lịch | Core earn |
| Nhật ký nhẹ sau HĐ (emoji / 1 dòng) | **+6** | Chỉ nếu đã complete HĐ đó trong ngày | Không spam journal trống |
| Bonus «ngày có chơi» (soft day-mark) | **+8** | ≥ 1 HĐ complete trong ngày | Không yêu cầu liên tục |
| Bonus chuỗi mềm (optional) | **+4 / +8 / +12** | Ngày chơi liên tiếp 3 / 5 / 7 (rolling) | **Không** reset số dư; **không** guilt khi đứt; ngày nghỉ = chuỗi về 0, vẫn giữ GX |
| Mở app / scroll thư viện / đổi gợi ý | **0** | — | Chống grind UI |
| Xem video / ở lâu trên màn | **0** | — | Khớp KPI ≠ time-in-app |

**Công thức ngày (trần trước cap):**

```
raw_day =
  18 × n_complete
  + 6 × n_journal_linked   (n_journal ≤ n_complete)
  + 8 nếu n_complete ≥ 1
  + streak_soft_bonus      (0 | 4 | 8 | 12)
```

### 3.2. Daily cap (bắt buộc)

| Tham số | Giá trị |
|---|---|
| **Daily cap** | **48 GX / ngày / trẻ** |
| Soft warn UI | Khi đạt ~40 GX: «Đủ rồi hôm nay — phần còn lại là chơi thật, không cần mở app thêm.» |
| Overflow | Phần vượt **bỏ**, không chuyển ngày sau |

**Vì sao 48?**

- 1 HĐ + journal + day-mark = 18+6+8 = **32 GX** (dưới cap).
- 2 HĐ + 2 journal + day-mark = 36+12+8 = **56 → cắt còn 48** → ngày 2 HĐ «đủ no», không khuyến khích 3–4 HĐ chỉ để farm.
- Daily picker vốn gợi ý 2–3 HĐ nhưng KPI tuần ≥ 3; cap khiến **farm trong ngày vô nghĩa**.

### 3.3. Bảng earn ngày (ước lượng)

| Kịch bản ngày | Tính raw | Sau cap | Nhận xét |
|---|---:|---:|---|
| 0 HĐ | 0 | **0** | Nghỉ OK — không phạt |
| 1 HĐ, không journal | 18+8 = 26 | **26** | Tối thiểu «có chơi» |
| 1 HĐ + journal | 18+6+8 = 32 | **32** | Ngày chuẩn nhẹ |
| 1 HĐ + journal + streak 3d | 32+4 = 36 | **36** | Soft boost |
| 2 HĐ + 2 journal | 36+12+8 = 56 | **48** | Chạm cap |
| 2 HĐ + journal + streak 7d | 36+6+8+12 = 62 | **48** | Cap nuốt streak — đúng ý «đừng grind» |
| 3 HĐ cố farm | raw ≫ 48 | **48** | Vô ích |

### 3.4. Trần tuần mềm (gợi ý R3, không bắt buộc ledger)

| Tham số | Giá trị | Mục đích |
|---|---|---|
| Soft weekly ceiling (UI hint) | ~ **200 GX / tuần** | ~4 ngày chơi đầy + 1–2 ngày nhẹ; không hard-block nếu R3 muốn đơn giản hơn |
| Hard rule MVP đề xuất | Chỉ **daily cap 48** | Đủ anti-grind; weekly soft = copy |

---

## 4. Conversion math (bao lâu đủ đổi?)

### 4.1. Nhịp kiếm «healthy»

| Nhịp PH | HĐ/tuần | Ước GX/tuần* | Ghi chú |
|---|---:|---:|---|
| Dưới KPI | 2 | ~52–64 | Vẫn có tiến; không shame |
| **KPI floor** | **3** | **~78–96** | Target thiết kế chính |
| Healthy | 4–5 | ~104–160 | Nhiều ngày 1 HĐ hơn là 1 ngày farm |
| Cap lý thuyết | 7×48 | 336 | Không đạt được nếu sống thật (cap + mệt) |

\*Giả sử phần lớn ngày = 1 HĐ + journal (± streak mềm rải); không giả sử chạm cap mỗi ngày.

**Neo tính toán dưới đây:** **~90 GX / tuần** (≈ 3–4 HĐ + journal, streak nhẹ).

### 4.2. Thời gian tới vật phẩm neo

| Vật phẩm | Giá GX | @90 GX/tuần | @1 HĐ+journal/ngày (~32 GX/ngày) | VND hint (khoảng) |
|---|---:|---|---|---|
| Kẹo mút (1 cây) | **35** | **~3 ngày** (≈ 0,4 tuần) | **~1,1 ngày** | 5–15 nghìn |
| Bim bim nhỏ | **45** | **~3–4 ngày** | **~1,5 ngày** | 10–25 nghìn |
| Nước mía ly | **55** | **~4–5 ngày** | **~2 ngày** | 10–20 nghìn |
| Ăn ốc (suất nhỏ share) | **90** | **~1 tuần** | **~3 ngày** | 40–80 nghìn |
| Đồ chơi nhỏ | **150** | **~1,5–2 tuần** | **~5 ngày** | 30–80 nghìn |
| Đồ chơi trung | **320** | **~3,5–4 tuần** | **~10 ngày chơi** | 100–250 nghìn |
| Công viên (vé/xe + nước) | **200** | **~2–2,5 tuần** | **~6–7 ngày** | 50–150 nghìn / lần |

**Ý đồ cân bằng:**

- Treat nhỏ (kẹo/nước) = **nhanh** (vài ngày) → PH thấy vòng feedback, nhưng **FoodTreat có disclaimer** (R2 siết copy).
- Đồ chơi / trải nghiệm = **chậm hơn** (1–4 tuần) → khớp nhịp mua sắm gia đình, không «mỗi ngày 1 đồ chơi».
- Không vật phẩm «huyền thoại 2000 GX» — tránh grind dài gây nghiện ledger.

### 4.3. Tỷ lệ gợi ý (không đưa vào UI số cứng)

```
1 ngày healthy (32 GX)  ≈  kẹo mút gần đủ (35)
1 tuần KPI (~90 GX)     ≈  1 treat đường phố nhỏ / góp quỹ đồ chơi
1 tháng healthy (~360)  ≈  1 đồ chơi trung hoặc 1–2 outing
```

---

## 5. Catalog quy đổi

**Quy ước cột**

- **GX:** giá gợi ý đổi trong app (PH «đổi» = tự thực hiện ngoài đời).
- **VND hint:** khoảng tham khảo VN 2025–2026, **không** giá store cố định; PH vùng khác được chỉnh tay (P1).
- **FoodTreat:** luôn kèm nhãn cố định dưới đây.

> **Nhãn FoodTreat (bắt buộc trên UI):**  
> «PH quyết định — không khuyến khích ép ăn / dùng đồ ăn để điều khiển hành vi. Ưu tiên lựa chọn lành mạnh; đồ ngọt & ăn vặt chỉ thỉnh thoảng.»

### 5.1. Tier `Experience` — trải nghiệm / ra ngoài

| ID | Mục | GX | VND hint (khoảng) | Ghi chú |
|---|---|---:|---|---|
| exp_park | Công viên / sân chơi gần nhà (1 buổi) | **200** | 50–150 nghìn (xe + nước + vé nếu có) | Ưu tiên catalog |
| exp_zoo_corner | Góc thú / bảo tàng thiếu nhi (vé trẻ + PH) | **280** | 80–200 nghìn | Theo địa phương |
| exp_swim_paddle | Bể bơi / padle nông (1 buổi, nếu gia đình quen) | **260** | 70–180 nghìn | An toàn do PH |
| exp_bus_ride | Đi xe buýt / tàu điện «chuyến vui» (khứ hồi ngắn) | **120** | 20–60 nghìn | Trải nghiệm > mua đồ |

### 5.2. Tier `Play` — đồ chơi / vật dụng chơi

| ID | Mục | GX | VND hint (khoảng) | Ghi chú |
|---|---|---:|---|---|
| play_small | **Đồ chơi nhỏ** (bóng mềm, xúc xắc vải, xe nhỏ, sticker pad…) | **150** | **30–80 nghìn** | User-requested |
| play_mid | **Đồ chơi trung** (bộ xếp hình, nhạc cụ gỗ đơn giản, búp bê vải…) | **320** | **100–250 nghìn** | User-requested |
| play_book | Sách tranh mới (1 cuốn) | **180** | 40–120 nghìn | Khớp LANGUAGE |
| play_art | Bộ sáp / màu ngón tay an toàn | **140** | 30–90 nghìn | AESTHETIC |
| play_reuse | «Đồ chơi từ nhà» — PH chuẩn bị hộp carton/chai sạch chơi giả vờ | **40** | 0–20 nghìn | Rẻ + đúng value prop app |

### 5.3. Tier `FoodTreat` — đồ ăn / uống (cảnh báo nuôi dưỡng)

| ID | Mục | GX | VND hint (khoảng) | Phân loại | Ghi chú |
|---|---|---:|---|---|---|
| food_fruit_cup | Trái cây cắt sẵn / sữa chua không đường thêm | **50** | 15–40 nghìn | **Healthy option** | Ưu tiên hiện trên |
| food_yogurt | Sữa chua uống / hộp nhỏ | **45** | 10–30 nghìn | **Healthy option** | |
| food_sugarcane | **Nước mía** (ly) | **55** | **10–20 nghìn** | Caution (đường) | User-requested |
| food_watermelon | **Nước dưa hấu** / dưa cắt | **50** | **15–35 nghìn** | Healthy-leaning nếu ít đường thêm | User-requested |
| food_lollipop | **Kẹo mút** (1 cây) | **35** | **5–15 nghìn** | **Caution** | User-requested |
| food_snack | **Bim bim** gói nhỏ | **45** | **10–25 nghìn** | **Caution** (muối/dầu) | User-requested |
| food_oc | **Ăn ốc** (suất nhỏ / share gia đình) | **90** | **40–80 nghìn** | Street-food treat; cay/vệ sinh do PH | User-requested |
| food_banhmi_mini | Bánh mì mini / bánh bao nhỏ | **70** | 15–40 nghìn | Trung tính | Thỉnh thoảng |
| food_che_beans | Chè đậu / tàu hủ nước đường ít | **65** | 15–35 nghìn | Caution đường | |

**Copy UI nhóm Caution:** chip «Thỉnh thoảng · PH chọn».  
**Không** xếp FoodTreat cao hơn Experience/Play trên default sort — default sort: SharedMoment → Experience → Play → FoodTreat.

### 5.4. Tier `SharedMoment` — khoảnh khắc gia đình (ưu tiên cao, GX thấp–trung)

| ID | Mục | GX | VND hint (khoảng) | Ghi chú |
|---|---|---:|---|---|---|
| mom_story_10 | **Thêm 10 phút chuyện / đọc sách** trước ngủ | **30** | 0 | User-style; zero-cost |
| mom_song_pick | **Bé chọn bài hát** / nhảy cùng 1 bài | **25** | 0 | User-style |
| mom_sticker | **Sticker** khen cụ thể (1–3 tem) | **40** | 10–30 nghìn / tờ | User-style |
| mom_choose_game | Bé chọn trò tiếp theo (quyền chọn) | **28** | 0 | Autonomy |
| mom_picnic_blanket | Picnic thảm tại nhà / ban công | **60** | 0–40 nghìn (đồ ăn sẵn có) | |
| mom_bath_toys | Đêm tắm «trò chơi nước» thêm 5 phút | **35** | 0 | Trong nếp sẵn có |
| mom_photo_print | In 1 ảnh chơi cùng nhau | **80** | 5–25 nghìn | Kỷ niệm |
| mom_grandparents | Gọi ông bà / video call «khoe trò hôm nay» | **45** | 0 | Gắn SOCIAL |

### 5.5. Tóm tắt số lượng catalog MVP gợi ý

| Tier | Số mục | Ghi chú ship |
|---|---:|---|
| SharedMoment | 8 | Ship sớm — đúng «thưởng = tương tác thật» |
| Experience | 4 | |
| Play | 5 | |
| FoodTreat | 9 | Có disclaimer; có thể **ẩn mặc định** sau toggle «Hiện gợi ý đồ ăn» (R2/R4 chốt) |
| **Tổng** | **26** | Rút còn ~16 nếu UI chật: giữ đủ user-requested + 6 SharedMoment |

---

## 6. Weekly budget example — gia đình 1 trẻ

**Persona:** 1 bé 24–30 tháng · PH chơi theo KPI · không cố chạm daily cap.

### Tuần mẫu (healthy KPI+)

| Ngày | HĐ | Journal | Streak soft | GX ngày | Ghi chú |
|---|---:|---:|---|---:|---|
| T2 | 1 | có | — | 32 | |
| T3 | 1 | có | — | 32 | |
| T4 | 0 | — | — | 0 | Nghỉ — OK |
| T5 | 1 | có | +4 (ngày chơi thứ 3 trong chuỗi gần) | 36 | |
| T6 | 1 | không | — | 26 | Vội |
| T7 | 1 | có | +4 | 36 | |
| CN | 0 | — | đứt mềm | 0 | |
| **Tổng tuần** | **5** | | | **~162** | Trong band 3–5 HĐ; dưới soft ceiling 200 |

**Phân bổ đổi thưởng gợi ý (cùng tuần hoặc gối tuần sau):**

| Ưu tiên | Đổi | GX | Còn lại |
|---|---|---:|---:|
| 1 | SharedMoment: thêm 10 phút chuyện + chọn bài hát | 30+25 = 55 | 107 |
| 2 | (Tuỳ chọn) trái cây / sữa chua **hoặc** giữ quỹ | 50 | 57 |
| 3 | Góp quỹ đồ chơi nhỏ (cần 150) | +57 → quỹ 57/150 | — |

**Tuần chỉ KPI = 3 HĐ** (T2/T5/T7, có journal): ≈ **3×32 = 96 GX**  
→ đủ **kẹo mút (35)** + **sticker (40)** + dư **21**; hoặc góp ~⅔ đồ chơi nhỏ; hoặc gần đủ **ăn ốc (90)**.

**Số dư:** không hết hạn; không phạt nghỉ. UI có thể gợi ý «Quỹ đang X GX — gần tới đồ chơi nhỏ» **không** countdown guilt.

---

## 7. Anti-patterns (R1 cấm trong số liệu)

| Cấm | Lý do |
|---|---|
| Earn theo phút mở app / session length | Trái KPI |
| Nhân GX khi mua IAP | Không IAP bắt buộc; tránh pay-to-skip nuôi dưỡng |
| Loss aversion («còn 2h mất chuỗi») | Trái CONSENSUS tone |
| Catalog chỉ đồ ngọt | Ép FoodTreat; lệch charter |
| Giá GX trung bình ngày > daily cap dễ dàng bằng 1 thao tác | Phá cap |
| Đổi GX lấy nội dung khoá trong app | Biến thành soft paywall / slot |

---

## 8. Đề xuất phase cho R4

| Phase | Phạm vi economy |
|---|---|
| **MVP (P0)** | Có thể **không** hiện GX — chỉ hoàn thành HĐ + khen tương tác thật (khớp CONSENSUS «loại coin»). |
| **P1 khuyến nghị** | Bật **Gấu Xu + catalog** opt-in trong Cài đặt: «Gợi ý đổi thưởng gia đình». Daily cap 48 · bảng giá §5 · FoodTreat ẩn mặc định. |
| **P2** | Chỉnh giá theo vùng / custom item PH tự thêm; nhiều trẻ = ví tách theo `childId`. |

**Verdict R1:** `PASS` — số liệu nhất quán với KPI ≥ 3/tuần, cap chống grind, streak mềm, FoodTreat có nhãn bắt buộc; ghi nhận lệch «coin MVP» bằng phase P1 opt-in.

---

## 9. Handoff

| Sang | Việc cần |
|---|---|
| **R2** | Copy disclaimer FoodTreat; danh sách thưởng cấm (ép ăn, so sánh anh chị, threat rút GX) |
| **R3** | Mission ngày / anti-abuse trùng activityId; có giữ streak_soft như §3 không |
| **R4** | Chốt P0 vs P1; khóa bảng giá v1; rút catalog nếu cần |

---

*Hết 01_R1_economy.md · Expert R1 · Không chỉnh code Android.*
