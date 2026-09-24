# Khung chương trình giáo dục — Gấu Con

> Subagent A · Workspace `ideas/BeGau` · Thử nghiệm phụ  
> MVP: trẻ **18–36 tháng**, ~90 hoạt động · Định hướng mở rộng **0–6 tuổi**  
> **Không chẩn đoán y tế** · Nội dung hoạt động = nháp cần chuyên gia duyệt  
> Ngày soạn: 2026-09-24

---

## 1. Map lĩnh vực Bộ GD&ĐT ↔ mã app

### 1.1. Cơ sở pháp lý (đã xác minh tồn tại văn bản)

| Văn bản | Vai trò | URL / nơi tra |
|---|---|---|
| Thông tư **17/2009/TT-BGDĐT** (25/7/2009) | Ban hành Chương trình GDMN | [vanban.chinhphu.vn](https://vanban.chinhphu.vn/default.aspx?docid=89927&pageid=27160) |
| Thông tư **28/2016/TT-BGDĐT**, **51/2020/TT-BGDĐT** | Sửa đổi/bổ sung chương trình | [Công báo — TT 51/2020](https://congbao.chinhphu.vn/van-ban/thong-tu-so-51-2020-tt-bgddt-33480.htm) |
| Văn bản hợp nhất **01/VBHN-BGDĐT** (13/4/2021) | Bản hợp nhất hiện hành của chương trình | [Công báo — 01/VBHN-BGDĐT](https://congbao.chinhphu.vn/van-ban/van-ban-hop-nhat-so-01-vbhn-bgddt-33656.htm) |

**Cấu trúc lĩnh vực theo chương trình (đã xác minh qua mô tả công khai / tổng hợp văn bản):**

- **Nhà trẻ** (khoảng 3 tháng – 36 tháng): 4 lĩnh vực — thể chất; nhận thức; ngôn ngữ; tình cảm – xã hội và thẩm mĩ (gộp).
- **Mẫu giáo** (3–6 tuổi): 5 lĩnh vực — thể chất; nhận thức; ngôn ngữ; tình cảm – xã hội; thẩm mĩ.

App **Gấu Con** chuẩn hóa thành **6 mã** (5 lĩnh vực mẫu giáo + `SELF_CARE` bổ sung cho sinh hoạt gia đình / reminder ROUTINE_*). Đây là **quyết định sản phẩm**, không phải thay thế chương trình Bộ.

### 1.2. Bảng map chính

| Tên lĩnh vực (Bộ GD&ĐT / chương trình) | Mã app | Phạm vi nội dung app | Mục tiêu gợi ý MVP **18–36 tháng** (ví dụ, không phải chuẩn chẩn đoán) | Ghi chú mở rộng **0–6 tuổi** |
|---|---|---|---|---|
| Giáo dục phát triển **thể chất** (vận động thô + tinh; vệ sinh thân thể trong CT) | `PHYSICAL` | Đi, chạy, leo có giám sát; cầm nắm, xếp khối, tô vẽ nguệch; phối hợp mắt–tay | 18–24m: đi vững, leo ghế sofa có người lớn; cầm thìa/cốc; nguệch ngoạc. 24–30m: chạy, đá bóng nhẹ, nhảy thử hai chân. 30–36m: nhảy hai chân rõ hơn, xếp ~6 khối, lật trang sách | 0–12m: lật, ngồi, bò, đứng/đi theo cửa sổ WHO. 3–6y: thăng bằng, ném/bắt, cắt kéo an toàn |
| Giáo dục phát triển **nhận thức** | `COGNITIVE` | Quan sát, so sánh, phân loại, nguyên nhân–kết quả đơn giản, chơi giả vờ | 18–24m: bắt chước việc nhà đơn giản; chơi đồ chơi đúng chức năng. 24–30m: phân loại màu/hình sơ cấp; giải quyết “lấy ghế để với”. 30–36m: biết ≥1 màu khi hỏi; làm theo 2 bước đơn giản | Sơ sinh–1y: theo dõi vật, tìm vật bị che. Mẫu giáo: đếm, khái niệm thời gian/không gian đơn giản |
| Giáo dục phát triển **ngôn ngữ** | `LANGUAGE` | Nghe–hiểu, nói, cử chỉ giao tiếp, đọc sách cùng bố mẹ | 18–24m: ≥ vài từ ngoài mama/dada; làm theo 1 lệnh không cử chỉ. 24–30m: cụm 2 từ; chỉ bộ phận cơ thể. 30–36m: ~50 từ; câu có động từ; gọi tên tranh khi hỏi | 0–12m: bi bô, đáp lại tên. 3–6y: kể chuyện ngắn, hỏi–đáp, làm quen chữ cái (không ép đọc sớm) |
| Giáo dục phát triển **tình cảm – kỹ năng xã hội** | `SOCIAL_EMOTIONAL` | Gắn bó an toàn, cảm xúc, chơi cạnh/cùng bạn, luân phiên, đồng cảm sơ cấp | 18–24m: tách ngắn nhưng kiểm tra bố mẹ còn gần; chỉ để chia sẻ thú vị. 24–30m: nhìn mặt người lớn khi tình huống mới; nhận ra người khác buồn. 30–36m: chơi gần trẻ khác; làm theo thói quen đơn giản | 0–12m: mỉm cười xã hội, sợ người lạ. 3–6y: hợp tác nhóm, giải quyết xung đột có người lớn |
| Giáo dục phát triển **thẩm mỹ** | `AESTHETIC` | Cảm nhận âm nhạc, nhịp; tạo hình; thưởng thức tranh/ảnh/thiên nhiên | 18–24m: hát theo vài tiếng; nghe nhạc và cử động. 24–30m: vẽ nguệch; vỗ tay theo nhịp. 30–36m: hát đoạn ngắn; chọn màu yêu thích; “trưng bày” sản phẩm | Nhà trẻ: thẩm mĩ thường gộp với tình cảm–XH trong CT. 3–6y: hát–múa, tạo hình có chủ đề, thưởng thức nghệ thuật |
| *(Bổ sung app — không phải lĩnh vực thứ 6 độc lập trong CT mẫu giáo)* **Tự lập & sinh hoạt** | `SELF_CARE` | Ăn, ngủ, vệ sinh/bô, mặc/cởi, cất đồ, an toàn cơ bản tại nhà; gắn reminder `ROUTINE_*` | 18–24m: tự cầm đồ ăn; uống cốc (có thể đổ); đưa tay/chân khi mặc quần áo. 24–30m: dùng thìa khá hơn; cởi quần/áo lỏng. 30–36m: tập bô theo nhịp gia đình; cất đồ chơi có nhắc | Trùng một phần “chăm sóc–nuôi dưỡng” trong CT nhà trẻ. 3–6y: tự phục vụ bữa ăn, vệ sinh cá nhân, an toàn đường phố sơ cấp |

**Lưu ý map nhà trẻ:** ở chương trình nhà trẻ, **tình cảm – xã hội và thẩm mĩ** thường **một lĩnh vực gộp**. App vẫn tách `SOCIAL_EMOTIONAL` và `AESTHETIC` để lọc gợi ý và báo cáo phụ huynh rõ ràng hơn; khi đối chiếu giáo án trường nhà trẻ, có thể gộp hiển thị.

---

## 2. Nhóm tuổi app và cách tính tuổi theo tháng

### 2.1. Nhóm tuổi (theo SPEC_MVP)

| Mã nhóm | Khoảng | Vai trò trong sản phẩm |
|---|---|---|
| `0–3m` | 0 ≤ tuổi < 3 tháng | Mở rộng sau MVP |
| `3–6m` | 3 ≤ tuổi < 6 | Mở rộng |
| `6–9m` | 6 ≤ tuổi < 9 | Mở rộng |
| `9–12m` | 9 ≤ tuổi < 12 | Mở rộng |
| `12–18m` | 12 ≤ tuổi < 18 | Mở rộng |
| `18–24m` | 18 ≤ tuổi < 24 | **MVP** |
| `24–30m` | 24 ≤ tuổi < 30 | **MVP** |
| `30–36m` | 30 ≤ tuổi < 36 | **MVP** |
| `3–4y` | 36 ≤ tuổi < 48 tháng | Mở rộng |
| `4–5y` | 48 ≤ tuổi < 60 | Mở rộng |
| `5–6y` | 60 ≤ tuổi < 72 | Mở rộng (đối chiếu Bộ chuẩn 5 tuổi khi làm giai đoạn sau) |

Một hoạt động có thể gắn **một hoặc nhiều** nhóm tuổi (ví dụ `18–24m` + `24–30m`).

### 2.2. Công thức tính tuổi theo tháng từ ngày sinh

**Đầu vào:** `birthDate` (ngày–tháng–năm), `today` (ngày hệ thống, local timezone phụ huynh).

**Thuật toán đề xuất (lịch dân sự, dễ giải thích cho QA):**

```
ageMonths =
  (today.year - birthDate.year) * 12
  + (today.month - birthDate.month)

nếu today.day < birthDate.day thì ageMonths = ageMonths - 1

nếu ageMonths < 0 thì ageMonths = 0   // bảo vệ ngày sinh tương lai / lỗi nhập
```

**Gán nhóm:** tìm hàng trong bảng §2.1 sao cho `lowerBound ≤ ageMonths < upperBound`.

**Ví dụ:** sinh 15/03/2024, hôm nay 24/09/2026 →  
`(2026-2024)*12 + (9-3) = 30`; ngày 24 ≥ 15 → **30 tháng** → nhóm `30–36m`.

**Ghi chú kỹ thuật (giả định sản phẩm, chờ TECH_ARCHITECTURE chốt):**

- Lưu `birthDate` local, không sync cloud ở MVP nếu privacy yêu cầu offline-first.
- Không dùng “tuổi theo năm dương lịch” (ví dụ “2 tuổi”) làm khóa lọc nội dung — luôn dùng **tháng**.
- WHO khi công bố percentile đôi khi quy đổi ngày ÷ **30.4375**; app **không** cần bắt chước trừ khi đối chiếu số liệu WHO trong báo cáo nội bộ. Checklist phụ huynh dùng công thức lịch dân sự ở trên.
- Trẻ sinh non: **không tự điều chỉnh tuổi chỉnh** trong MVP; UI có thể ghi chú “nếu bé sinh non, hãy hỏi bác sĩ về theo dõi phát triển” (xem §3).

---

## 3. Nguyên tắc sư phạm cho phụ huynh

### 3.1. Không phán xét

- Cấm copy kiểu: “Bạn đã bỏ lỡ…”, “Bé đang chậm hơn bạn bè…”, “Đã muộn rồi…”.
- Ưu tiên: quan sát → gợi ý chơi → ghi nhận cố gắng của bố mẹ và bé.
- Checklist mốc = **gợi ý quan sát**, không phải điểm số xếp hạng.

### 3.2. “Mỗi bé một nhịp”

- Luôn nhắc biến thiên bình thường giữa các trẻ.
- Gợi ý hoạt động theo **độ khó linh hoạt** (dễ hơn / khó hơn một nấc), không khóa cứng “đúng tháng này phải làm được”.
- Cho phép bỏ qua / “chưa phù hợp hôm nay” mà không làm giảm “điểm phụ huynh”.

### 3.3. Không chẩn đoán y tế

- App **không** đưa kết luận: chậm phát triển, tự kỷ, ADHD, thiếu dinh dưỡng, bệnh lý.
- Không so sánh percentile WHO/CDC như “bé thuộc X% thấp”.
- Mốc WHO/CDC chỉ dùng để: (a) soạn checklist tham khảo, (b) quyết định khi nào **nhắc hỏi chuyên gia** — không tự sàng lọc lâm sàng.

### 3.4. Khi nào ghi “nên hỏi bác sĩ” (hoặc chuyên gia y tế / sàng lọc)

Chỉ hiện copy trung tính, ví dụ: *“Nếu bạn lo lắng về sự phát triển của bé, nên trao đổi với bác sĩ hoặc cơ sở y tế. App không thay thế khám chuyên môn.”*

**Gợi ý trigger sản phẩm (tham khảo CDC “Act Early” + nguyên tắc an toàn — chờ chuyên gia duyệt trước khi bật trong UI):**

| Tình huống | Hành vi app đề xuất |
|---|---|
| Phụ huynh chủ động bấm “Tôi đang lo lắng” | Hiện lời khuyên hỏi bác sĩ + checklist để mang đi khám (không kết luận) |
| Nhiều mục mốc ở **cùng một nhóm tuổi** được đánh “chưa thấy” sau khi bé đã qua ngưỡng tuổi đó | Nhắc nhẹ: trao đổi với bác sĩ; **không** hiện % hay nhãn “chậm” |
| Mất kỹ năng đã từng có (hồi quy) | Ưu tiên nhắc hỏi bác sĩ sớm |
| Dấu hiệu cấp: khó thở, co giật, chấn thương đầu, nghi ngộ độc, sốt cao kèm li bì… | Ngoài phạm vi giáo dục — CTA khẩn “gọi cấp cứu / đến cơ sở y tế” (nội dung an toàn do agent D / chuyên gia) |
| Bé sinh non / bệnh nền / đang theo dõi chuyên khoa | Không tự “chỉnh tuổi”; gợi ý hỏi bác sĩ về kế hoạch theo dõi |

**Không** tự động gửi dữ liệu sức khỏe cho bên thứ ba; không thay thế lịch tiêm / khám định kỳ.

---

## 4. Crosswalk mốc phát triển (tham khảo)

### 4.1. Nguồn và mức xác minh

| Nhánh | Nguồn | URL / tài liệu | Đã xác minh? | Dùng thế nào trong app |
|---|---|---|---|---|
| **(a) Việt Nam — chương trình & chuẩn** | Chương trình GDMN: TT 17/2009 + sửa đổi; VBHN **01/VBHN-BGDĐT** (13/4/2021) | [Công báo 01/VBHN](https://congbao.chinhphu.vn/van-ban/van-ban-hop-nhat-so-01-vbhn-bgddt-33656.htm); [TT 17/2009](https://vanban.chinhphu.vn/default.aspx?docid=89927&pageid=27160) | **Có** (tồn tại văn bản & cấu trúc 4/5 lĩnh vực). **Chưa** trích nguyên văn toàn bộ mục tiêu theo tháng vào repo (bản quyền / độ dài) | Map lĩnh vực ↔ mã app; ngôn ngữ “giáo dục theo lĩnh vực” khi nói với trường MN |
| | Bộ chuẩn phát triển trẻ em **5 tuổi**: TT **23/2010/TT-BGDĐT**; bản mới **QĐ 4222/QĐ-BGDĐT** (27/12/2024) — 6 lĩnh vực, 22 chuẩn, 70 chỉ số | [Công báo TT 23/2010](https://congbao.chinhphu.vn/van-ban/thong-tu-so-23-2010-tt-bgddt-1641/575.htm); mô tả QĐ 4222 qua cổng sở/PGD | **Có** số hiệu & cấu trúc lĩnh vực. Chi tiết chỉ số 18–36m **không** thuộc chuẩn 5 tuổi | Tham chiếu giai đoạn mở rộng 5–6y; MVP 18–36m **không** lấy checklist từ chuẩn 5 tuổi |
| **(b) WHO** | Child Growth Standards — **6 mốc vận động thô** (windows of achievement, percentile 1–99) | [Trang WHO motor milestones](https://www.who.int/tools/child-growth-standards/standards/motor-development-milestones); [PDF Windows of achievement](https://www.who.int/docs/default-source/child-growth/child-growth-standards/indicators/motor-development-milestones/who-motor-development-study-windows-of-achievement-for-six-gross-motor-development-milestones.pdf); [Bảng percentile](https://cdn.who.int/media/docs/default-source/child-growth/child-growth-standards/indicators/motor-development-milestones/mm_percentiles_table.pdf?sfvrsn=81f3b60b_5) | **Có** | Chủ yếu `PHYSICAL` **0–18 tháng** (ngồi, bò, đứng, đi…). **Không** cover ngôn ngữ/nhận thức/XH đầy đủ |
| **(c) CDC** | Learn the Signs. Act Early. — mốc theo tháng (định nghĩa: ≥75% trẻ làm được) | [Index](https://www.cdc.gov/act-early/milestones/index.html); [18 months](https://www.cdc.gov/act-early/milestones/18-months.html); [2 years](https://www.cdc.gov/act-early/milestones/2-years.html); [30 months](https://www.cdc.gov/act-early/milestones/30-months.html); [Milestone Moments PDF](https://www.cdc.gov/act-early/media/pdfs/Milestone-Moments-Booklet_Eng-Print_updated-9.2023_508-P.pdf) | **Có** (trang chính thức CDC) | Checklist MVP 18 / 24 / 30 tháng đa lĩnh vực; dịch Việt **nháp** cần chuyên gia + kiểm bản quyền thuật ngữ |

### 4.2. WHO — cửa sổ 6 mốc vận động thô (đã xác minh số liệu công bố)

Khoảng tuổi khoảng percentile 1–99 (tháng), theo WHO Motor Development Study:

| Mốc | Cửa sổ (tháng) |
|---|---|
| Ngồi không cần chống | 3.8 – 9.2 |
| Đứng có hỗ trợ | 4.8 – 11.4 |
| Bò bằng tay–gối | 5.2 – 13.5 |
| Đi có hỗ trợ | 5.9 – 13.7 |
| Đứng một mình | 6.9 – 16.9 |
| Đi một mình | 8.2 – 17.6 |

→ Hữu ích cho mở rộng & checklist `PHYSICAL` dưới 18 tháng; với MVP 18–36m, hầu hết trẻ đã qua “đi một mình” — dùng CDC / mục tiêu CT nhà trẻ cho giai đoạn toddler.

### 4.3. CDC — điểm neo MVP (tóm tắt tham khảo, không copy nguyên checklist dài)

| Tuổi CDC | Ví dụ hành vi (rút gọn) | Map mã app |
|---|---|---|
| 18 tháng | Đi không vịn; nguệch; thử thìa/cốc; vài từ; làm theo 1 lệnh; chỉ để chia sẻ | `PHYSICAL`, `LANGUAGE`, `SELF_CARE`, `SOCIAL_EMOTIONAL`, `COGNITIVE` |
| 2 năm (24 tháng) | Chạy, đá bóng; ăn thìa; cụm ≥2 từ; chỉ bộ phận cơ thể; chơi với ≥2 đồ cùng lúc | `PHYSICAL`, `LANGUAGE`, `COGNITIVE`, `SELF_CARE` |
| 30 tháng | Nhảy hai chân; ~50 từ; câu có động từ; biết ≥1 màu; làm theo 2 bước; cởi quần áo lỏng | `PHYSICAL`, `LANGUAGE`, `COGNITIVE`, `SELF_CARE`, `SOCIAL_EMOTIONAL` |

**Phân biệt rõ:** CDC = công cụ quan sát phụ huynh Mỹ; **không** phải chuẩn pháp lý VN. Khi hiển thị trong app VN: ghi nguồn “tham khảo CDC Learn the Signs” + “không thay khám bác sĩ”.

### 4.4. Giả định crosswalk chưa xác minh

- Bản dịch Việt từng dòng CDC / diễn giải mục tiêu CT nhà trẻ theo **từng tháng** trong seed nội dung — **chưa** có chuyên gia sư phạm/y tế duyệt.
- Việc gộp/tách thẩm mỹ–XH giữa nhà trẻ và app — hợp lý về UX nhưng **chưa** xác nhận với giáo viên MN thực địa.
- Tỷ lệ hoạt động §5 — đề xuất sản phẩm, chưa A/B hay ý kiến chuyên gia.

---

## 5. Quy tắc xuất bản hoạt động & phân bổ ~90 hoạt động MVP

### 5.1. Quy tắc xuất bản (DoD nội dung)

Trước khi `published = true` / đưa vào seed production:

| Trường / điều kiện | Bắt buộc |
|---|---|
| `reviewed_by` | **Không rỗng** — ít nhất một người có thẩm quyền sư phạm/y tế phù hợp (họ tên hoặc mã reviewer nội bộ có audit log) |
| `domains` | ≥1 trong 6 mã; khuyến nghị ≤2 mã chính để gợi ý ngày không bị “đa năng mơ hồ” |
| `age_bands` | Phải giao với MVP `{18–24m, 24–30m, 30–36m}` nếu thuộc gói MVP |
| `steps` | Đọc ≤ ~30 giây; làm được một tay / đồ nhà sẵn có |
| `safety` | Có cảnh báo nếu có rủi ro (nuốt dị vật, độ cao, nước, điện…) |
| Tone | Không phán xét; không cam kết “chữa” hay “đạt chuẩn” |
| Nguồn cảm hứng | Ghi `source_refs` (CT GDMN / CDC / WHO / tự soạn) — phân biệt trích dẫn vs diễn giải |

Nháp research của các agent B/C/D: `reviewed_by: []` — **không** xuất bản lên store.

### 5.2. Phân bổ đề xuất ~90 hoạt động MVP (cân bằng, hơi nhấn toddler)

Tổng mục tiêu: **90**. Mỗi hoạt động gắn 1–2 lĩnh vực; bảng dưới đếm theo **lĩnh vực chính** (primary domain) để tránh double-count khi báo cáo coverage.

| Mã | Số hoạt động (primary) | Tỷ lệ | Lý do nhấn cho 18–36m |
|---|---:|---:|---|
| `PHYSICAL` | 16 | ~18% | Vận động thô/tinh thay đổi nhanh; phụ huynh dễ “làm được hôm nay” |
| `COGNITIVE` | 14 | ~16% | Chơi giả vờ, phân loại — gắn đồ nhà |
| `LANGUAGE` | 16 | ~18% | Cửa sổ ngôn ngữ mạnh; sách & nói chuyện hàng ngày |
| `SOCIAL_EMOTIONAL` | 14 | ~16% | Cảm xúc–gắn bó; tránh quá tải “bài học đạo đức” |
| `AESTHETIC` | 12 | ~13% | Đủ để không bỏ thẩm mỹ; ít hơn vì nhà trẻ gộp lĩnh vực |
| `SELF_CARE` | 18 | ~20% | Ăn–ngủ–bô–an toàn = nỗi đau phụ huynh + reminder ROUTINE_* |
| **Tổng** | **90** | **100%** | |

**Phân theo nhóm tuổi MVP (đếm primary, có thể chồng chéo age_bands):**

| Nhóm | Số HĐ roughly | Ghi chú |
|---|---:|---|
| Chỉ/chủ yếu `18–24m` | 28 | |
| Chỉ/chủ yếu `24–30m` | 30 | |
| Chỉ/chủ yếu `30–36m` | 22 | |
| Span 2 nhóm (ví dụ 18–30m) | 10 | Giảm trùng lặp nội dung |

*(Số hàng age = giả định kế hoạch biên tập; agent B/C/D có thể điều chỉnh ±2 mỗi ô nếu giữ tổng 90 và không để lĩnh vực nào &lt; 10.)*

**Gợi ý lịch ngày:** 2–3 HĐ/ngày từ **khác lĩnh vực**; tránh 3 ngày liên tiếp cùng một primary domain.

---

## 6. Lợi ích tin cậy phụ huynh & hợp tác trường mầm non (giai đoạn 3)

### 6.1. Tin cậy phụ huynh (MVP → giai đoạn 2)

- Nói rõ app **bám khung 5 lĩnh vực Bộ** + tự lập tại nhà → cảm giác “có căn cứ”, không phải trend mạng.
- Minh bạch nguồn mốc (CDC/WHO) và disclaimer y tế → giảm sợ bị “chấm điểm con”.
- Offline-first, không quảng cáo, không bán dữ liệu (SPEC) → phù hợp đối tượng bố mẹ bận / ông bà.
- `reviewed_by` công khai ở mức “đã được giáo viên/chuyên gia X xem” (khi có) → khác biệt với app AI thuần.

### 6.2. Hợp tác trường mầm non (giai đoạn 3 — định hướng)

- Giáo viên dùng cùng **mã lĩnh vực** khi soạn giáo án theo chủ đề → phụ huynh–nhà trường nói chung ngôn ngữ.
- Xuất “nhật ký hoạt động tuần” (PDF/offline share) theo 6 mã — **không** phải hồ sơ bệnh án.
- Có thể map báo cáo lớp sang Bộ chuẩn 5 tuổi (QĐ 4222) khi mở rộng mẫu giáo; MVP toddler dùng mục tiêu CT nhà trẻ + CDC.
- Lối vào B2B: trường / nhóm lớp pilot — vẫn cần duyệt nội dung và thỏa thuận dữ liệu (ưu tiên không thu PII trẻ).

---

## 7. Rủi ro / blocker

| Rủi ro | Mức | Ghi chú / hướng xử lý (cho parent) |
|---|---|---|
| **Chưa có chuyên gia duyệt** sư phạm mầm non / nhi khoa | Cao | Mọi HĐ giữ `reviewed_by: []`; không claim “chuẩn Bộ” hay “đạt WHO” trên store |
| **Bản quyền / tái sử dụng nội dung Bộ** (toàn văn CT, bộ chuẩn, giáo án mẫu) | Trung bình–cao | Chỉ **map lĩnh vực + diễn giải mục tiêu ngắn**; không paste nguyên chương; xin phép hoặc dẫn chiếu công khai khi cần trích dài |
| **CDC/WHO** — dịch & thích ứng văn hóa VN | Trung bình | Ghi nguồn; không dùng logo CDC/WHO nếu chưa đủ điều kiện; chuyên gia rà thuật ngữ |
| Nhầm app thành **công cụ chẩn đoán** | Cao (uy tín + pháp lý cảm nhận) | Copy cứng §3; QA ngôn ngữ định kỳ |
| Nhà trẻ CT **4 lĩnh vực** vs app **6 mã** | Thấp–TB | Tài liệu giải thích cho GV; UI “chế độ trường” có thể gộp thẩm mỹ–XH |
| Thiếu bằng chứng nhu cầu / chi trả | Ngoài scope agent A | Không bịa doanh thu; parent quyết định pilot |
| Sinh non / trẻ đặc biệt | Trung bình | Không “adjusted age” tự động; CTA hỏi bác sĩ |

---

## 8. Nguồn

### Chính thức / đã mở URL trong phiên nghiên cứu

1. Bộ GD&ĐT — Thông tư 17/2009/TT-BGDĐT: https://vanban.chinhphu.vn/default.aspx?docid=89927&pageid=27160  
2. Văn bản hợp nhất 01/VBHN-BGDĐT (13/4/2021): https://congbao.chinhphu.vn/van-ban/van-ban-hop-nhat-so-01-vbhn-bgddt-33656.htm  
3. Thông tư 51/2020/TT-BGDĐT: https://congbao.chinhphu.vn/van-ban/thong-tu-so-51-2020-tt-bgddt-33480.htm  
4. Thông tư 23/2010/TT-BGDĐT (Bộ chuẩn trẻ 5 tuổi — bản cũ): https://congbao.chinhphu.vn/van-ban/thong-tu-so-23-2010-tt-bgddt-1641/575.htm  
5. Quyết định 4222/QĐ-BGDĐT (27/12/2024) — Bộ chuẩn trẻ 5 tuổi (bản mới; xác minh số hiệu & 6 lĩnh vực qua cổng giáo dục địa phương / tổng hợp văn bản)  
6. WHO — Motor development milestones: https://www.who.int/tools/child-growth-standards/standards/motor-development-milestones  
7. WHO — Windows of achievement (PDF): https://www.who.int/docs/default-source/child-growth/child-growth-standards/indicators/motor-development-milestones/who-motor-development-study-windows-of-achievement-for-six-gross-motor-development-milestones.pdf  
8. CDC Learn the Signs — index & 18/24/30 months: https://www.cdc.gov/act-early/milestones/index.html  
9. Nội bộ dự án: `docs/SPEC_MVP.md`, `plans/PLAN_PHAN_TICH.md`, `D:\AI Kiem Tien\AGENTS.md`

### Tham khảo thứ cấp (không dùng làm căn cứ pháp lý duy nhất)

- Bài tổng hợp 5 lĩnh vực MN (UK Academy, ME School, tailieu.vn) — chỉ để đối chiếu tên lĩnh vực khi chưa mở đủ PDF VBHN trong phiên.

---

## 9. Giả định chưa xác minh

1. Toàn văn mục tiêu theo độ tuổi trong **01/VBHN-BGDĐT** khớp 1–1 với ví dụ cột “mục tiêu MVP” ở §1.2 — ví dụ đó mang tính **diễn giải sản phẩm**, có pha CDC, chưa đối chiếu dòng-từng-dòng với PDF Bộ.  
2. Phân bổ 16/14/16/14/12/18 và chia age_bands §5.2 — **chưa** ý kiến chuyên gia hay dữ liệu dùng thử.  
3. Trigger “nhiều mốc chưa thấy → hỏi bác sĩ” — ngưỡng số lượng/tỷ lệ **chưa** chốt lâm sàng.  
4. QĐ **4222/QĐ-BGDĐT** (2024): đã xác minh số hiệu và 6 lĩnh vực công bố công khai; **chưa** đọc toàn bộ 70 chỉ số từ file đính kèm chính thức trong phiên này.  
5. Phụ huynh VN hiểu và chấp nhận disclaimer CDC/WHO khi checklist viết tiếng Việt — chưa nghiên cứu UX.  
6. Giai đoạn 3 hợp tác trường — giả định trường sẵn dùng cùng taxonomy 6 mã; thực tế có thể chỉ quen 5 lĩnh vực mẫu giáo.

---

## 10. Việc giao tiếp với sibling (không sửa file họ)

| Agent | Nhận từ khung này |
|---|---|
| B — Thể chất + Nhận thức | Dùng mã `PHYSICAL` / `COGNITIVE`; neo CDC 18/24/30m + WHO cho &lt;18m; `reviewed_by: []` |
| C — Ngôn ngữ + XH-TC + Thẩm mỹ | `LANGUAGE` / `SOCIAL_EMOTIONAL` / `AESTHETIC`; nhớ nhà trẻ gộp thẩm mỹ–XH |
| D — Tự lập & sinh hoạt | `SELF_CARE` + ROUTINE_*; CTA “hỏi bác sĩ” khi an toàn/y tế |
| E — UX | Tone §3; nhóm tuổi §2; không phán xét trên S-screens |
| F — Tech | Công thức `ageMonths`; enum 6 domain; chặn publish nếu `reviewed_by` rỗng |

---

*Hết file agent A. Chỉ file này được ghi trong phạm vi task.*
