# SELF_CARE — Tự lập & sinh hoạt (Gấu Con MVP)

> Agent D · Nháp nghiên cứu · **Chưa chuyên gia duyệt** · Không chẩn đoán y tế  
> Phạm vi: trẻ **18–36 tháng** · App phụ huynh · Offline ngoài đời thật  
> Nguồn neo: `docs/SPEC_MVP.md` (mục 3, 5 Reminder), `plans/PLAN_PHAN_TICH.md`  
> Phân loại nguồn: **[C]** chính thức / khung app · **[T]** tham khảo phát triển · **[G]** giả định nội dung nháp

---

## 0. Disclaimer (bắt buộc mọi checklist & cẩm nang)

- Mốc và mục tiêu dưới đây là **gợi ý theo dõi**, không phải chuẩn “đạt/không đạt”, không thay khám bác sĩ / chuyên gia. **[T]** tham chiếu tinh thần WHO Child Growth / CDC *Learn the Signs* + Chương trình GDMN — **không** dùng app để chẩn đoán chậm phát triển. **[C]** SPEC nguyên tắc 3 & 6.
- Mỗi bé có nhịp riêng. UI checklist chỉ 3 trạng thái: **Đã làm được / Đang tập / Chưa** — không so sánh với bé khác, không câu “Bé đang chậm…”.
- Mọi hoạt động YAML: `reviewed_by: []` — **chỉ xuất bản khi có ≥ 1 chuyên gia duyệt** (SPEC 3.3).
- Nhánh **an toàn sinh hoạt** = thói quen hàng ngày tại nhà (rửa tay, không đưa vật nhỏ vào miệng, ngồi ghế ăn…). **Không** thay game an toàn chuyên sâu (*Bé Gấu An Toàn* / ATGT). **[G]** ranh giới sản phẩm.

---

## 1. Phân nhánh SELF_CARE

| Nhánh | Mã nội bộ (nháp) | Trọng tâm 18–36m | Gắn ReminderType (SPEC 5.2) | Không làm gì |
|---|---|---|---|---|
| **Ăn** | `SC_EAT` | Tự xúc / cầm ăn, ngồi ghế, thử món mới, lịch bữa | `ROUTINE_MEAL` | Không kê thực đơn y tế; không “ép cân” |
| **Ngủ** | `SC_SLEEP` | Ngủ trưa, nghi thức trước ngủ, môi trường ngủ | `ROUTINE_NAP`, `ROUTINE_BEDTIME` (+ `READING` hỗ trợ) | Không chẩn đoán rối loạn giấc ngủ |
| **Vệ sinh / tập bô** | `SC_POTTY` | Nhận tín hiệu, ngồi bô, rửa tay sau vệ sinh | `ROUTINE_POTTY` | Không ép bỏ tã theo deadline; không xấu hổ |
| **An toàn sinh hoạt hàng ngày** | `SC_SAFE_DAILY` | Thói quen an toàn lúc ăn/chơi trong nhà | (không kênh riêng; có thể gắn hoạt động hôm nay) | Không module cháy/điện/nước/đường chuyên sâu |

**Nguyên tắc sư phạm nhánh này:** kỹ năng gắn **nếp lặp hàng ngày** (ăn–ngủ–vệ sinh), bố mẹ dẫn dắt bằng lời mời và nghi thức ngắn; ưu tiên đồ nhà VN.

---

## 2. Mục tiêu theo band tuổi (18–36 tháng)

> Band theo SPEC nhóm tuổi MVP: `18–24m`, `24–30m`, `30–36m`. Mục tiêu = định hướng gợi ý hoạt động, không KPI ép buộc.

### 2.1. Ăn (`SC_EAT`)

| Band | Mục tiêu gợi ý (bé làm được với hỗ trợ phù hợp) |
|---|---|
| 18–24m | Cầm đồ ăn mềm bằng tay; tập dùng thìa (vương vãi OK); ngồi ghế ăn trong phần lớn bữa; uống từ cốc có hỗ trợ |
| 24–30m | Xúc thìa ổn định hơn; tự lấy vài món trong khay; biết nói “thêm / thôi / ngon”; tham gia dọn khăn/muỗng đơn giản |
| 30–36m | Tự xúc phần lớn bữa mềm; dùng nĩa trẻ (tùy gia đình); rửa tay trước ăn với nhắc; biết chờ món nóng nguội một chút |

### 2.2. Ngủ (`SC_SLEEP`)

| Band | Mục tiêu gợi ý |
|---|---|
| 18–24m | Có **nghi thức ngắn** trước ngủ (cùng 2–4 bước lặp); ngủ trưa theo nhịp nhà; giảm kích thích mạnh gần giờ ngủ |
| 24–30m | Tham gia bước nghi thức (mang sách, tắt đèn cùng bố mẹ); nhận biết “sắp ngủ”; nằm giường/chiếu quen |
| 30–36m | Tự lấy gối/gấu bông; nói được “đi ngủ”; ngủ trưa có thể rút ngắn dần tùy bé — **không ép một khuôn** |

### 2.3. Vệ sinh / tập bô (`SC_POTTY`)

| Band | Mục tiêu gợi ý |
|---|---|
| 18–24m | Làm quen bô/toilet trẻ (ngồi chơi mặc quần); nhận vài tín hiệu ướt/khó chịu; rửa tay sau thay tã (bố mẹ làm, bé bắt chước) |
| 24–30m | Ngồi bô theo lịch nhẹ (sau ăn, trước ngủ) **không ép lâu**; báo “ị / tè” bằng từ/cử chỉ; kéo quần đơn giản có hỗ trợ |
| 30–36m | Một số bé tự đi bô ban ngày; vẫn có tai nạn — bình thường hóa; tự kéo quần phần lớn; xả nước / lấy giấy có hỗ trợ |

> **[G]** Nhiều bé VN bỏ tã ban ngày quanh 24–36m; đêm thường muộn hơn. App **không** đặt mốc “phải sạch tã lúc X tháng”.

### 2.4. An toàn sinh hoạt hàng ngày (`SC_SAFE_DAILY`)

| Band | Mục tiêu gợi ý |
|---|---|
| 18–24m | Không chạy khi miệng có đồ ăn; ngồi khi uống; “hết rồi” khi đồ nóng; không đưa vật nhỏ vào miệng (bố mẹ giám sát) |
| 24–30m | Rửa tay trước ăn / sau vệ sinh với bài hát ngắn; biết “nóng / sắc” theo lời nhắc; dừng lại khi bố mẹ nói “dừng” trong bữa |
| 30–36m | Nhắc lại quy tắc 1 câu trước bữa/chơi; tự lấy khăn lau tay; biết nhờ người lớn với đồ cao/nóng |

---

## 3. Checklist mốc (UI F5)

**Trạng thái:** `DONE` = Đã làm được · `PRACTICING` = Đang tập · `NOT_YET` = Chưa  
**Disclaimer UI (một dòng, luôn hiện gần checklist):** *“Mỗi bé có nhịp riêng. Đây chỉ là gợi ý theo dõi, không phải chẩn đoán.”*

### 3.1. Ăn

| id mốc | Nội dung checklist | Band gợi ý hiện |
|---|---|---|
| `ms_sc_eat_01` | Cầm đồ ăn mềm bằng tay khi ngồi ghế | 18–24 |
| `ms_sc_eat_02` | Tập xúc thìa (vương vãi được chấp nhận) | 18–24 |
| `ms_sc_eat_03` | Uống từ cốc có hỗ trợ / cốc tập | 18–30 |
| `ms_sc_eat_04` | Ngồi suốt phần lớn bữa (không bắt buộc im lặng) | 24–30 |
| `ms_sc_eat_05` | Tự xúc phần lớn bữa mềm | 30–36 |
| `ms_sc_eat_06` | Rửa tay trước ăn khi được nhắc | 24–36 |

### 3.2. Ngủ

| id mốc | Nội dung checklist | Band gợi ý hiện |
|---|---|---|
| `ms_sc_sleep_01` | Có nghi thức 2–4 bước trước ngủ | 18–24 |
| `ms_sc_sleep_02` | Tham gia bước nghi thức (mang sách, tắt đèn…) | 24–30 |
| `ms_sc_sleep_03` | Tự lấy gối / thú quen thuộc khi sắp ngủ | 30–36 |
| `ms_sc_sleep_04` | Có khung ngủ trưa tương đối ổn định theo nhà | 18–30 |

### 3.3. Vệ sinh / bô

| id mốc | Nội dung checklist | Band gợi ý hiện |
|---|---|---|
| `ms_sc_potty_01` | Làm quen ngồi bô (không ép) | 18–24 |
| `ms_sc_potty_02` | Báo tín hiệu tè/ị bằng từ hoặc cử chỉ | 24–30 |
| `ms_sc_potty_03` | Ngồi bô theo vài khung giờ quen | 24–36 |
| `ms_sc_potty_04` | Kéo quần lên/xuống có hỗ trợ | 24–36 |
| `ms_sc_potty_05` | Rửa tay sau vệ sinh / thay tã (cùng bố mẹ) | 18–36 |

### 3.4. An toàn sinh hoạt

| id mốc | Nội dung checklist | Band gợi ý hiện |
|---|---|---|
| `ms_sc_safe_01` | Ngồi khi ăn / uống (nhắc + làm mẫu) | 18–24 |
| `ms_sc_safe_02` | Nghe “dừng / nóng” và dừng thử trong bữa | 24–30 |
| `ms_sc_safe_03` | Rửa tay theo bài hát ngắn trước ăn | 24–36 |

---

## 4. Hoạt động nháp YAML (~18)

> Khuôn SPEC 3.3 · `domains` ưu tiên `[SELF_CARE]` · có thể gắn phụ LANGUAGE khi lời nói là công cụ nếp sống · **materials nhà VN** · `reviewed_by: []`.

### 4.1. Ăn (5)

```yaml
id: act_sc_eat_018_01
title: "Thìa của Gấu"
age_min_months: 18
age_max_months: 24
domains: [SELF_CARE]
duration_minutes: 10
materials: ["1 thìa nhựa mềm", "cháo/cơm mềm nguội", "yếm hoặc khăn"]
goal: "Bé tập cầm thìa đưa miệng, bố mẹ chấp nhận vương vãi"
steps:
  - "Cho bé ngồi ghế ăn, mặc yếm"
  - "Đưa thìa vào tay bé, mẹ cầm tay hướng dẫn 1–2 thìa đầu"
  - "Khen nỗ lực: 'Gấu đang tập xúc đó'"
  - "Mẹ xúc xen kẽ nếu bé mệt — không ép hết tô"
parent_phrases: ["Gấu cầm thìa nào", "Đưa lên miệng nhé", "Giỏi quá, Gấu đang tập"]
easier: "Bé cầm thìa trống, mẹ xúc bằng thìa khác"
harder: "Bé xúc 3–5 thìa liên tiếp trước khi mẹ hỗ trợ"
safety: "Thức ăn nguội vừa ăn; ghế chắc, có người lớn cạnh; không chạy khi miệng còn đồ"
video_url: ""
reviewed_by: []
reviewed_at: null
```

```yaml
id: act_sc_eat_018_02
title: "Cốc tập uống"
age_min_months: 18
age_max_months: 30
domains: [SELF_CARE]
duration_minutes: 5
materials: ["cốc tập / cốc nhỏ có quai", "nước lọc", "khăn lau"]
goal: "Bé nâng cốc uống vài ngụm với hỗ trợ"
steps:
  - "Đổ ít nước (1–2 cm) vào cốc"
  - "Hai tay mẹ kèm tay bé nâng cốc"
  - "Uống xong: 'Hết rồi, đặt cốc xuống'"
  - "Lau miệng cùng bé"
parent_phrases: ["Hai tay nâng cốc nào", "Từ từ nhé", "Đặt cốc xuống bàn"]
easier: "Dùng bình tập có vòi mềm"
harder: "Bé tự nâng 2 tay, mẹ chỉ giữ đáy cốc"
safety: "Ngồi vững; nước thường (không nóng); không để bé đi lại khi uống"
video_url: ""
reviewed_by: []
reviewed_at: null
```

```yaml
id: act_sc_eat_024_03
title: "Bữa ăn có khay"
age_min_months: 24
age_max_months: 36
domains: [SELF_CARE]
duration_minutes: 15
materials: ["khay/đĩa chia ngăn hoặc 2–3 chén nhỏ", "cơm mềm", "rau/đậu mềm", "thìa"]
goal: "Bé tự chọn xúc từng ngăn, luyện ngồi và tự lập nhẹ"
steps:
  - "Bày 2–3 món mềm đã nguội trên khay"
  - "Chỉ: 'Gấu lấy cơm trước nhé'"
  - "Để bé xúc/cầm; mẹ ngồi cạnh, không soi từng thìa"
  - "Kết thúc: cùng bỏ thìa vào bát, cảm ơn Gấu"
parent_phrases: ["Gấu chọn món nào?", "Thêm một thìa nữa nhé?", "Bữa ăn xong rồi"]
easier: "Chỉ 1 món + đồ cầm tay"
harder: "Bé tự nói 'thêm / thôi' trước khi mẹ lấy thêm"
safety: "Không hạt cứng, không quả tròn nhỏ; cắt mềm; luôn có người lớn"
video_url: ""
reviewed_by: []
reviewed_at: null
```

```yaml
id: act_sc_eat_024_04
title: "Rửa tay trước ăn"
age_min_months: 24
age_max_months: 36
domains: [SELF_CARE]
duration_minutes: 5
materials: ["xà phòng dịu", "ghế đẩu thấp chắc", "khăn lau tay"]
goal: "Bé làm theo 3 bước rửa tay trước bữa"
steps:
  - "Kéo ghế đẩu, vặn nước (người lớn kiểm soát nhiệt)"
  - "Hát đếm 1–2–3 khi xát xà phòng"
  - "Xả nước, lau khô"
  - "Nói: 'Sạch rồi, vào bàn ăn nào'"
parent_phrases: ["Xát xà phòng nào", "Đếm 1-2-3", "Lau khô tay"]
easier: "Mẹ cầm tay bé xát, bé chỉ quan sát đếm"
harder: "Bé tự lấy xà phòng và nhắc mẹ 'rửa tay'"
safety: "Nước ấm vừa; ghế chống trượt; không để bé một mình trong WC/bếp"
video_url: ""
reviewed_by: []
reviewed_at: null
```

```yaml
id: act_sc_eat_030_05
title: "Gấu dọn thìa"
age_min_months: 30
age_max_months: 36
domains: [SELF_CARE]
duration_minutes: 5
materials: ["thìa/bát nhẹ", "chậu nhỏ hoặc bồn đã tắt nước nóng"]
goal: "Sau bữa, bé mang thìa vào chậu — tập kết thúc nếp ăn"
steps:
  - "Báo hiệu: 'Bữa xong, mình dọn nào'"
  - "Nhờ bé cầm thìa/bát nhựa nhẹ"
  - "Bỏ vào chậu; vỗ tay nhẹ"
  - "Lau bàn cùng mẹ (bé cầm khăn)"
parent_phrases: ["Cảm ơn Gấu đã dọn", "Thìa vào đây nhé", "Bàn sạch rồi"]
easier: "Chỉ mang thìa, mẹ mang bát"
harder: "Bé phân loại thìa / khăn vào đúng chỗ"
safety: "Không đồ thủy tinh/nặng/nóng; sàn khô; giám sát gần bếp"
video_url: ""
reviewed_by: []
reviewed_at: null
```

### 4.2. Ngủ (4)

```yaml
id: act_sc_sleep_018_06
title: "Nghi thức 4 bước ngủ"
age_min_months: 18
age_max_months: 30
domains: [SELF_CARE]
duration_minutes: 10
materials: ["đèn dịu", "1 cuốn sách ảnh", "gấu bông/gối quen"]
goal: "Lặp cùng thứ tự mỗi tối để tín hiệu 'sắp ngủ'"
steps:
  - "Bước 1: thu đồ chơi vào rổ (mẹ làm chính, bé bỏ 1–2 món)"
  - "Bước 2: đánh răng / lau răng theo tuổi (người lớn thực hiện)"
  - "Bước 3: đọc 1 cuốn sách ngắn"
  - "Bước 4: tắt đèn lớn, ôm, nói câu kết cố định"
parent_phrases: ["Sắp đến giờ ngủ của Gấu", "Mình đọc một cuốn thôi nhé", "Chúc Gấu ngủ ngon"]
easier: "Rút còn 2 bước: sách + ôm"
harder: "Bé tự lấy sách và gối theo thứ tự"
safety: "Không màn hình sáng trong nghi thức; giường/chiếu an toàn, không vật nhỏ gần gối"
video_url: ""
reviewed_by: []
reviewed_at: null
```

```yaml
id: act_sc_sleep_018_07
title: "Hát ru quen"
age_min_months: 18
age_max_months: 36
domains: [SELF_CARE]
duration_minutes: 5
materials: ["giọng hát bố mẹ hoặc 1 bài hát không lời êm"]
goal: "Dùng cùng một bài hát làm tín hiệu ngủ trưa/tối"
steps:
  - "Làm dịu phòng (ánh sáng, giảm TV)"
  - "Bế/ngồi cạnh, hát cùng 1 bài mỗi lần"
  - "Giảm dần âm lượng"
  - "Đặt bé xuống khi mắt chậm"
parent_phrases: ["Nghe bài hát ngủ nào", "Mắt Gấu nặng rồi", "Mẹ/bố ở đây"]
easier: "Chỉ nestle + ngân nga không lời"
harder: "Bé yêu cầu 'hát nữa' rồi chấp nhận dừng sau câu hẹn"
safety: "Tư thế ngủ an toàn theo hướng dẫn y tế địa phương; không đệm thừa che mặt"
video_url: ""
reviewed_by: []
reviewed_at: null
```

```yaml
id: act_sc_sleep_024_08
title: "Tắt đèn cùng Gấu"
age_min_months: 24
age_max_months: 36
domains: [SELF_CARE]
duration_minutes: 5
materials: ["công tắc đèn trong tầm với có hỗ trợ", "đèn ngủ dịu (tuỳ)"]
goal: "Bé tham gia bước 'tắt đèn' trong nghi thức"
steps:
  - "Báo: 'Đến bước tắt đèn rồi'"
  - "Bế hoặc ghế đẩu chắc, bé ấn công tắc (mẹ giữ)"
  - "Bật đèn ngủ nếu cần"
  - "Nói câu kết: 'Phòng tối, Gấu ngủ nào'"
parent_phrases: ["Gấu tắt đèn giúp mẹ", "Đèn ngủ nhỏ thôi", "Ngủ ngon"]
easier: "Mẹ đặt tay lên tay bé ấn cùng"
harder: "Bé tự đi tới công tắc (có đèn đủ sáng) rồi gọi mẹ"
safety: "Không để bé trèo tủ; giám sát điện; không bóng tối đột ngột gây sợ — đèn ngủ OK"
video_url: ""
reviewed_by: []
reviewed_at: null
```

```yaml
id: act_sc_sleep_030_09
title: "Gấu lấy gối"
age_min_months: 30
age_max_months: 36
domains: [SELF_CARE]
duration_minutes: 5
materials: ["gối nhỏ / thú quen", "chỗ ngủ cố định"]
goal: "Bé tự lấy đồ ngủ quen và đến chỗ ngủ"
steps:
  - "Nhắc: 'Đi lấy gối của Gấu nào'"
  - "Để bé tìm và cầm"
  - "Cùng đặt lên chỗ ngủ"
  - "Nằm xuống, đọc/ hát ngắn"
parent_phrases: ["Gối Gấu đâu rồi?", "Đặt gối đây nhé", "Gấu giỏi quá"]
easier: "Mẹ chỉ tay vào gối"
harder: "Bé lấy gối + sách + thú theo checklist 3 món"
safety: "Không dây/túi nilon gần chỗ ngủ; giám sát tư thế"
video_url: ""
reviewed_by: []
reviewed_at: null
```

### 4.3. Vệ sinh / tập bô (5)

```yaml
id: act_sc_potty_018_10
title: "Làm quen với bô"
age_min_months: 18
age_max_months: 24
domains: [SELF_CARE]
duration_minutes: 5
materials: ["bô trẻ hoặc bệ lót toilet trẻ", "sách ảnh nhỏ"]
goal: "Bé ngồi bô mặc quần vài phút vui vẻ — không ép ị"
steps:
  - "Đặt bô nơi cố định, dễ với"
  - "Mời: 'Gấu ngồi chơi trên bô một chút nhé?'"
  - "Đọc sách / hát 1–2 phút"
  - "Kết thúc vui, không hỏi 'đã ị chưa?'"
parent_phrases: ["Ngồi chơi nào", "Bô của Gấu đây", "Xong rồi, mình chơi tiếp"]
easier: "Chỉ nhìn / chạm bô, chưa ngồi"
harder: "Ngồi sau bữa ăn theo khung giờ quen"
safety: "Bô sạch, sàn chống trượt; không để bé một mình trên toilet cao"
video_url: ""
reviewed_by: []
reviewed_at: null
```

```yaml
id: act_sc_potty_024_11
title: "Nhắc ngồi bô nhẹ"
age_min_months: 24
age_max_months: 36
domains: [SELF_CARE]
duration_minutes: 5
materials: ["bô", "giấy vệ sinh", "ghế đẩu nếu cần"]
goal: "Thử ngồi bô theo khung (sau ăn / trước ngủ) không ép lâu"
steps:
  - "Chọn lúc bé đang vui, không đang chơi say"
  - "Mời ngồi 2–3 phút tối đa"
  - "Nếu không có gì: 'Mai mình thử lại' — giọng bình thường"
  - "Rửa tay cùng nhau"
parent_phrases: ["Thử ngồi bô nhé?", "Không sao nếu chưa sẵn sàng", "Rửa tay nào"]
easier: "Chỉ kéo quần giả / ngồi mặc quần"
harder: "Bé tự nói 'bô' khi cần"
safety: "Không phạt, không so sánh; vệ sinh tay; hỗ trợ thăng bằng"
video_url: ""
reviewed_by: []
reviewed_at: null
```

```yaml
id: act_sc_potty_024_12
title: "Từ tín hiệu của Gấu"
age_min_months: 24
age_max_months: 36
domains: [SELF_CARE, LANGUAGE]
duration_minutes: 5
materials: ["không bắt buộc — có thể ảnh bô đơn giản"]
goal: "Gắn từ/cử chỉ 'tè / ị / bô' với cảm giác"
steps:
  - "Khi thay tã, gọi tên: 'Tã ướt rồi'"
  - "Hỏi: 'Gấu muốn ngồi bô không?'"
  - "Lặp từ ngắn mỗi lần"
  - "Ăn mừng mọi lần báo (kể cả báo muộn)"
parent_phrases: ["Tã ướt", "Gấu tè rồi", "Bô nào"]
easier: "Chỉ bố mẹ gọi tên, chưa hỏi"
harder: "Bé nói từ hoặc dẫn tay mẹ tới bô"
safety: "Giọng trung tính; không xấu hổ / trêu"
video_url: ""
reviewed_by: []
reviewed_at: null
```

```yaml
id: act_sc_potty_030_13
title: "Kéo quần tập"
age_min_months: 30
age_max_months: 36
domains: [SELF_CARE]
duration_minutes: 8
materials: ["quần thun rộng", "ghế thấp để ngồi"]
goal: "Bé kéo quần lên/xuống phần lớn với hỗ trợ"
steps:
  - "Chọn quần dễ (thun, không khuy khó)"
  - "Bé cầm bo ống kéo xuống khi ngồi bô"
  - "Sau đó kéo lên, mẹ chỉnh eo"
  - "Khen quá trình, không chỉ kết quả sạch tã"
parent_phrases: ["Hai tay kéo nào", "Đứng vững đã", "Mẹ giúp một chút"]
easier: "Mẹ kéo 80%, bé kéo nốt"
harder: "Bé tự kéo gần xong trước khi gọi mẹ"
safety: "Không để bé đứng trên mặt ướt; hỗ trợ thăng bằng"
video_url: ""
reviewed_by: []
reviewed_at: null
```

```yaml
id: act_sc_potty_024_14
title: "Rửa tay sau bô"
age_min_months: 24
age_max_months: 36
domains: [SELF_CARE]
duration_minutes: 5
materials: ["xà phòng", "khăn", "ghế đẩu"]
goal: "Ghép 'xong bô → rửa tay' thành nếp"
steps:
  - "Ngay sau bô/thay tã: 'Bước tiếp theo: rửa tay'"
  - "Làm 3 bước như hoạt động rửa tay trước ăn"
  - "Dùng cùng bài hát để dễ nhớ"
  - "Kết: 'Sạch rồi, chơi tiếp'"
parent_phrases: ["Xong bô, rửa tay nào", "Xát xà phòng", "Sạch rồi"]
easier: "Khăn ướt dịu khi chưa tới vòi nước"
harder: "Bé tự nhắc 'rửa tay' sau bô"
safety: "Vệ sinh tay người lớn sau hỗ trợ; nước vừa"
video_url: ""
reviewed_by: []
reviewed_at: null
```

### 4.4. An toàn sinh hoạt hàng ngày (4)

```yaml
id: act_sc_safe_018_15
title: "Ngồi mới ăn"
age_min_months: 18
age_max_months: 30
domains: [SELF_CARE]
duration_minutes: 5
materials: ["ghế ăn / chiếu ngồi cố định", "đồ ăn nhẹ mềm"]
goal: "Bé học quy tắc: có đồ ăn thì ngồi"
steps:
  - "Trước khi đưa đồ: 'Ngồi xuống đã nhé'"
  - "Chỉ đưa bánh/hoa quả khi bé ngồi"
  - "Nếu đứng dậy: tạm dừng đồ ăn, mời ngồi lại (không la)"
  - "Khen khi ngồi ổn định"
parent_phrases: ["Ngồi rồi mới ăn", "Gấu ngồi đây nào", "Giỏi, đang ngồi ăn"]
easier: "Bé ngồi lòng mẹ"
harder: "Bé tự leo ghế ăn rồi vỗ tay báo sẵn sàng"
safety: "Không chạy khi nhai; cắt mềm chống hóc; giám sát"
video_url: ""
reviewed_by: []
reviewed_at: null
```

```yaml
id: act_sc_safe_024_16
title: "Nóng — dừng tay"
age_min_months: 24
age_max_months: 36
domains: [SELF_CARE]
duration_minutes: 5
materials: ["cốc nước ấm (không nóng bỏng) để làm mẫu", "hoặc ảnh minh họa ấm đun"]
goal: "Phản xạ nghe 'nóng' và dừng tay gần đồ ấm"
steps:
  - "Chỉ cốc ấm: 'Nóng — không chạm'"
  - "Làm mẫu rút tay, mặt bình tĩnh"
  - "Cho bé luyện nói 'nóng' và rút tay giả"
  - "Nhắc lại khi vào bếp (bé không vào gần bếp một mình)"
parent_phrases: ["Nóng", "Dừng tay", "Nhờ mẹ/bố lấy"]
easier: "Chỉ quan sát mẹ làm mẫu"
harder: "Bé nhắc người lớn 'nóng' khi thấy hơi nước"
safety: "Không dùng đồ thật đang sôi; khoảng cách an toàn với bếp/gas"
video_url: ""
reviewed_by: []
reviewed_at: null
```

```yaml
id: act_sc_safe_024_17
title: "Đồ nhỏ không vào miệng"
age_min_months: 24
age_max_months: 36
domains: [SELF_CARE]
duration_minutes: 8
materials: ["hộp đồ chơi lớn an toàn", "vài vật to (>3 cm) vs minh họa 'không'"]
goal: "Phân biệt đồ chơi miệng được / không được (qua chơi, không hù)"
steps:
  - "Chọn 2–3 món to an toàn để 'được cầm'"
  - "Với vật nhỏ (chỉ người lớn cầm): 'Cái này không vào miệng'"
  - "Chơi phân loại: bỏ món to vào hộp 'chơi'"
  - "Kết: kiểm tra sàn cùng mẹ"
parent_phrases: ["Cái to chơi được", "Cái nhỏ không vào miệng", "Nhờ mẹ giữ"]
easier: "Chỉ chơi với món to, mẹ nói quy tắc"
harder: "Bé đưa vật nhỏ cho mẹ thay vì bỏ miệng"
safety: "Không để vật nhỏ trong tầm với không giám sát; chống hóc"
video_url: ""
reviewed_by: []
reviewed_at: null
```

```yaml
id: act_sc_safe_030_18
title: "Một quy tắc trước bữa"
age_min_months: 30
age_max_months: 36
domains: [SELF_CARE]
duration_minutes: 3
materials: ["không bắt buộc"]
goal: "Trước mỗi bữa, nhắc 1 câu quy tắc an toàn (ngồi / chậm / nóng)"
steps:
  - "Trước khi ngồi bàn: hỏi 'Hôm nay quy tắc gì?'"
  - "Gợi ý nếu cần: 'Ngồi ăn' hoặc 'Thổi nguội'"
  - "Bé nhắc lại 1 câu"
  - "Bắt đầu bữa"
parent_phrases: ["Quy tắc hôm nay là…", "Gấu nhắc mẹ nghe nào", "Giỏi, nhớ rồi"]
easier: "Mẹ nói, bé gật"
harder: "Bé chọn giữa 2 quy tắc"
safety: "Giọng vui; không biến thành kiểm tra điểm"
video_url: ""
reviewed_by: []
reviewed_at: null
```

**Tổng hoạt động nháp:** 18 (`act_sc_*`) · Phân bổ: ăn 5 · ngủ 4 · bô 5 · an toàn sinh hoạt 4.

---

## 5. Map ReminderType ↔ SELF_CARE + mẫu thông báo

### 5.1. Bảng map

| ReminderType (SPEC) | Nhánh SELF_CARE | Mặc định SPEC | Gợi ý nội dung deep-link | Tuân R1–R7 |
|---|---|---|---|---|
| `ROUTINE_MEAL` | `SC_EAT` | Tắt · giờ PH đặt | Mở hoạt động ăn hôm nay / checklist ăn | R1 ngoại lệ giờ PH đặt; R5 gộp; R6 giọng mời; R7 tắt loại |
| `ROUTINE_NAP` | `SC_SLEEP` | Tắt | Gợi ý hát ru / hạ kích thích | như trên |
| `ROUTINE_BEDTIME` | `SC_SLEEP` (+ READING) | Tắt | Nghi thức 4 bước / đọc sách | như trên |
| `ROUTINE_POTTY` | `SC_POTTY` | Tắt · cách X giờ trong khung | Mời ngồi bô nhẹ | Interval do PH; không “trách vì ướt” |
| `READING` | Hỗ trợ ngủ / gắn kết (không thay LANGUAGE thuần) | Tắt | 1 cuốn trước ngủ | R6; có thể gộp với BEDTIME (R5) |
| `HEALTH_CHECKUP` | Sức khỏe (ngoài kỹ năng tự lập) | Tắt · **ngày PH nhập** | Xem chi tiết lịch đã nhập | **Không tự tạo lịch tiêm** |

> Alias trong brief agent (“NAP / BEDTIME / POTTY”) = `ROUTINE_NAP` / `ROUTINE_BEDTIME` / `ROUTINE_POTTY` theo SPEC 5.2.

**Ngoài phạm vi map sâu của agent D (để sibling/tech):** `DAILY_ACTIVITY`, `MILESTONE_CHECK`, `JOURNAL_WEEKLY`, `SCREEN_TIME` — vẫn tuân R1–R7 toàn cục.

### 5.2. Mẫu copy thông báo (giọng mời · R6 · qua biên tập trước ship)

| Type | Title | Body | Nút (SPEC 5.4) |
|---|---|---|---|
| `ROUTINE_MEAL` | Giờ ăn của Gấu | "Đến giờ ăn của Gấu rồi — ngồi bàn vui vẻ nhé?" | Đã xong · Hoãn 15 phút |
| `ROUTINE_MEAL` (biến thể) | Bữa trưa nhẹ | "Gấu sắp đói đến nơi. Chuẩn bị một bữa đơn giản nhé?" | Đã xong · Hoãn 15 phút |
| `ROUTINE_NAP` | Ngủ trưa | "Gấu chuẩn bị ngủ trưa nhé — thử hát ru bài quen thuộc?" | Đã xong · Hoãn 15 phút |
| `ROUTINE_BEDTIME` | Sắp đến giờ ngủ | "30 phút nữa đến giờ ngủ: tắt màn hình, đọc 1 cuốn sách nhé?" | Đã xong · Hoãn 15 phút |
| `ROUTINE_BEDTIME` (ngắn) | Nghi thức ngủ | "Mình làm 4 bước ngủ với Gấu tối nay nhé?" | Đã xong · Hoãn 15 phút |
| `ROUTINE_POTTY` | Nhắc bô nhẹ | "Nhắc nhẹ: cho Gấu ngồi bô thử nhé? Không sao nếu chưa sẵn sàng." | Đã xong · Hoãn 15 phút |
| `READING` | Đọc cùng Gấu | "Đọc cùng Gấu 1 cuốn sách trước khi ngủ nhé?" | Đã xong · Hoãn 15 phút |
| `HEALTH_CHECKUP` | Lịch đã lưu | "Ngày mai Gấu có lịch khám/tiêm bạn đã lưu — xem lại giúp nhé?" | Xem chi tiết · Đã đi |

**Cấm copy (ví dụ phản R6):** "Bạn chưa cho bé ăn", "Bé lại tè dầm rồi", "Bạn bỏ lỡ giờ ngủ".

### 5.3. HEALTH_CHECKUP — quy tắc nội dung

- App **không** generate lịch tiêm từ tuổi / không push “đến mũi X”. **[C]** SPEC 5.2.
- PH nhập ngày theo sổ tiêm / cơ sở y tế; app chỉ nhắc theo ngày đã lưu.
- Có thể hiện **bảng tham khảo** Chương trình TCMR + câu: *"Vui lòng xác nhận lịch với cơ sở y tế."* — không coi là chỉ định y khoa.
- Channel: `ch_health` (`IMPORTANCE_HIGH`) — vẫn R1: nếu PH đặt giờ trong đêm thì gửi (ngoại lệ); không tự đề xuất giờ đêm.

### 5.4. Liên hệ R1–R7 (checklist triển khai nội dung)

| Quy tắc | Áp dụng cho copy/lịch SELF_CARE |
|---|---|
| **R1** | Không spam ngoài 21:30–07:00 trừ `ROUTINE_*` / `HEALTH_CHECKUP` PH tự đặt |
| **R2** | `ROUTINE_*` / `READING` / `HEALTH` do PH đặt **không** tính vào quota 2 noti app/ngày |
| **R3** | Chủ yếu `DAILY_ACTIVITY`; với routine có thể hỏi “đổi giờ / thưa hơn / tắt” nếu bỏ qua nhiều (đề xuất giai đoạn 2) |
| **R4** | Không áp trực tiếp routine; với hoạt động: nếu đã chơi ≥1 thì bỏ `DAILY_ACTIVITY` |
| **R5** | Gộp BEDTIME + READING nếu ≤10 phút |
| **R6** | Chỉ câu mời; biên tập duyệt |
| **R7** | Mọi noti có lối tắt loại nhắc |

---

## 6. Cẩm nang phụ huynh (~9 chủ đề) — outline

> F7 SPEC: ~20 bài toàn app; agent D đề xuất **nhánh sinh hoạt**. Mỗi bài: 400–700 chữ mục tiêu · **không chẩn đoán** · CTA: hoạt động liên quan / bật reminder · `reviewed_by` bắt buộc trước publish.

| # | slug | Chủ đề | Outline | Không viết |
|---|---|---|---|---|
| 1 | `guide_an_va` | Ăn vạ / khóc đòi đồ ăn | (1) Bình thường hóa giai đoạn (2) Kiểm tra đói/mệt/bệnh — khi nào hỏi bác sĩ (3) Ngồi bàn + lựa chọn giới hạn 2 món (4) Không ép thìa (5) CTA: `act_sc_eat_024_03` | Không gắn nhãn rối loạn hành vi |
| 2 | `guide_bieng_an` | Biếng ăn | (1) Định nghĩa đời thường vs cảnh báo (sụt cân — gặp BS) (2) Khẩu phần nhỏ, lặp món (3) Không dùng đồ ngọt làm thưởng (4) Cùng ăn gia đình (5) CTA: thìa / khay | Không kê thực đơn trị liệu |
| 3 | `guide_kho_ngu` | Khó ngủ | (1) Nghi thức cố định (2) Giảm màn hình (3) Phân biệt đêm thức vs ốm (4) An ủi ngắn, nhất quán (5) CTA: nghi thức 4 bước + `ROUTINE_BEDTIME` | Không kê thuốc Melatonin |
| 4 | `guide_ngu_trua` | Ngủ trưa thất thường | (1) Nhịp theo tuổi thay đổi (2) Cửa sổ ngủ (3) Môi trường dịu (4) Không so “bé nhà người ta” (5) CTA: `ROUTINE_NAP` | Không bắt buộc số giờ chuẩn |
| 5 | `guide_tap_bo` | Tập bô bắt đầu thế nào | (1) Dấu hiệu sẵn sàng (2) Làm quen không ép (3) Tai nạn là bình thường (4) Quần dễ (5) CTA: làm quen bô + `ROUTINE_POTTY` | Không deadline “sạch tã trước mẫu giáo” |
| 6 | `guide_te_dem` | Tè đêm / tã đêm | (1) Đêm thường muộn hơn ngày (2) Hạn chế nước gần giờ ngủ vừa phải (3) Không phạt (4) Khi nào hỏi BS (đau, khát nhiều…) (5) CTA: nghi thức ngủ | Không chẩn đoán đái dầm |
| 7 | `guide_rua_tay` | Rửa tay thành thói quen | (1) 2 mốc: trước ăn / sau vệ sinh (2) Bài hát đếm (3) Làm mẫu (4) Ghế đẩu an toàn (5) CTA: `act_sc_eat_024_04` | Không bài giảng vi khuẩn đe dọa |
| 8 | `guide_ngoi_an` | Bé không chịu ngồi ăn | (1) Ghế phù hợp (2) Bữa ngắn (3) Quy tắc ngồi mới ăn (4) Giảm đồ chơi trên bàn (5) CTA: `act_sc_safe_018_15` | Không buộc ngồi lâu gây sợ |
| 9 | `guide_an_toan_bep` | An toàn quanh bếp (mức sinh hoạt) | (1) Vùng cấm bếp (2) Từ “nóng” (3) Quay lưng núm nồi (4) Khi nào cần nội dung an toàn chuyên sâu → trỏ game/module khác (5) CTA: `act_sc_safe_024_16` | Không thay bài cháy/gas/điện đầy đủ |

---

## 7. Ranh giới với PHYSICAL / SOCIAL_EMOTIONAL

| Chủ đề | Thuộc **SELF_CARE (D)** | Nhường **PHYSICAL (B)** | Nhường **SOCIAL_EMOTIONAL (C)** |
|---|---|---|---|
| Xúc thìa, ngồi ghế ăn, rửa tay, bô, nghi thức ngủ | ✅ Kỹ năng nếp sống | Chỉ khi trọng tâm là **khéo tay/thăng bằng** thuần (xếp khối, nhảy) không gắn bữa/ngủ | Cảm xúc lúc ăn vạ → C viết bài cảm xúc; D giữ cẩm nang “ăn vạ” góc nếp/bữa |
| Chạy, leo, đá bóng | ❌ | ✅ | — |
| Gọi tên cảm xúc, xin lỗi, luân phiên đồ chơi | ❌ (trừ từ tín hiệu bô gắn LANGUAGE nhẹ) | — | ✅ |
| Cắn bạn, chia sẻ | ❌ | — | ✅ cẩm nang XH |
| An toàn đường/điện/nước/cháy chuyên sâu | ❌ chỉ cross-link | Có thể vận động thoát hiểm → B nếu có | — |
| Đọc sáchก่อน ngủ | `READING` reminder + bước trong nghi thức ngủ | — | LANGUAGE/AESTHETIC sở hữu hoạt động đọc-kể thuần |

**Quy tắc tránh trùng ID:** mọi hoạt động agent D dùng prefix `act_sc_*` / mốc `ms_sc_*` / guide `guide_*` sinh hoạt. Không dùng `act_phys_*`, `act_soc_*`.

**Phụ thuộc agent A:** map mã `SELF_CARE` trong khung GDMN; D không redefine 5 lĩnh vực Bộ.

---

## 8. Nguồn & giả định

| Mục | Loại | Ghi chú |
|---|---|---|
| Lĩnh vực `SELF_CARE`, Reminder R1–R7, khuôn YAML, F5/F7/F8 | **[C]** | SPEC 1.0 (bản đầy đủ mục 5 từ parent; file `docs/SPEC_MVP.md` hiện rút gọn — neo Reminder lấy từ bản paste 1.0) |
| Band mục tiêu & checklist mốc | **[T]+[G]** | Gợi ý thực hành VN + tinh thần WHO/CDC; cần chuyên gia GDMN/nhi duyệt |
| 18 hoạt động YAML + 9 cẩm nang | **[G]** | Nháp nội dung; `reviewed_by: []` |
| Ranh giới không thay game an toàn | **[C]+[G]** | La bàn dự án: *Bé Gấu An Toàn* riêng; BeGau thử nghiệm phụ |

---

## 9. Đề xuất cho parent (tóm tắt quyết định)

1. Chốt quota hoạt động SELF_CARE trong ~90 act MVP (đề xuất **~18–22**, ~20% thư viện).
2. Bật mặc định reminder: giữ SPEC (routine tắt; PH tự bật) — đúng “không làm phiền”.
3. Cẩm nang ưu tiên ship P0: ăn vạ, biếng ăn, khó ngủ, tập bô (4 bài) trước pilot.
4. HEALTH: chỉ form nhập lịch + tham khảo TCMR; không engine tiêm tự động.
5. Đồng bộ copy noti với agent E (UX tone) và F (channel / WorkManager).
