# X3 — Nội dung & giọng văn (biên tập phụ huynh)

> Persona: biên tập viên copy phụ huynh · Wave 4 Expert QA  
> Nguồn: `plans/WAVE4_EXPERT_QA.md`, CONSENSUS §2.3, `PRODUCT_DESIGN`, `web/content/activities.json` (90 HĐ), `web/src/app.mjs`  
> **Không** sửa seed/code · **Không** claim “đã chuyên gia duyệt”  
> Ngày: 2026-09-24

---

## 0. Kết luận ngắn

Giọng seed **khớp hướng mời / không phán xét** (CONSENSUS §2.3). Không thấy câu kiểu “bỏ lỡ / chậm hơn bạn bè”. DoD đọc **goal + đồ dùng + bước ≤ 30 giây** đạt (core dài nhất ~24s @160 wpm).  

**Lệch chính:** ~20 `goal` còn neo **CDC + tiếng Anh kỹ thuật** (phụ huynh/ông bà đọc trên S05); 3 cặp HĐ **gần trùng ý**; UI web còn jargon nội bộ (GDMN, MVP). Web **chưa** có nhắc nhở — nên thêm banner nhẹ + bank copy (Android đã có mẫu tốt).

---

## 1. Tone — phán xét / dài >30s / jargon

### 1.1. Phán xét (đối chiếu CONSENSUS §2.3)

| Loại | Kết quả | Bằng chứng |
|---|---|---|
| Cấm: “bỏ lỡ”, “chậm hơn bạn bè”, % so sánh | **Không thấy** trong 90 HĐ + copy web chính | Quét toàn văn seed + `app.mjs` |
| Push guilt / ép bé | **Tốt** — nhiều chỗ chủ động “không ép” (bô, hát, ôm, nói nhu cầu) | vd. `act_sc_potty_*`, `LANG-05` safety, `SOC-02` |
| Soft-hit “phải / không được / bắt buộc” | **Không phải phán xét PH** | “không bắt buộc” (đồ dùng); “không được vào miệng” (dạy an toàn, kèm “không hù”); tag hỏi “phải không?” trong câu nói với bé |

**Quan sát UI (web) — giọng tốt:**  
“Chào bạn — chơi cùng {tên} nhé?”, “Mỗi bé một nhịp riêng”, “Chưa hợp cũng không sao…”, disclaimer “chưa chuyên gia duyệt / không chẩn đoán”.

**Giả định:** Ông bà (persona Bà Hạnh) sẽ khó chịu với CDC/English trên dòng **Mục tiêu** hơn là với câu nói mẫu.

### 1.2. Độ dài đọc (DoD PRODUCT_DESIGN / CONSENSUS)

- **Core (goal + materials + steps):** 0/90 vượt 30s; top ~65 từ / ~24s (`act_sc_sleep_018_06`, `LANG-14`, …).  
- **Đề xuất (không block):** trang S05 đầy đủ (thêm PP + dễ/khó + an toàn) có thể >30s lần đầu — giữ DoD ở core; gói “Dễ hơn / Khó hơn” dưới fold hoặc thu gọn mặc định (P1 UX copy, không đổi seed bắt buộc).

### 1.3. Jargon trên bề mặt phụ huynh

| Vấn đề | Phạm vi | Mức |
|---|---|---|
| `(CDC …)` + Anh: `scribble`, `run`, `twist`, `string`, `switches`, `pretend`, `Problem-solving`, `simple play` trong **goal** | ~20 HĐ `phy_*` / `cog_*` | **P0** |
| `checklist` trong harder | `act_sc_sleep_030_09` | P2 |
| Welcome: “Chương trình **GDMN** Bộ GD&ĐT” | `app.mjs` | **P0** (copy UI) |
| Progress: “Mục tiêu **MVP**: ≥ 3 / tuần” | `app.mjs` | **P1** |
| Thêm: `content_status`, path `gaucon/` | `app.mjs` | P2 (pilot OK nếu tách “dành dev”) |

**Mẫu viết lại goal (P0):**  
- Trước: `Problem-solving đơn giản (CDC 30m) **có giám sát**.` (`cog_09`)  
- Sau: `Bé nghĩ cách lấy đồ an toàn — luôn có người lớn giữ ghế.`  
- Nguồn CDC giữ field nội bộ / research — **không** hiện S05.

---

## 2. `parentPhrases`, `easier` / `harder` — chất lượng

### 2.1. Bao phủ

| Trường | Trạng thái |
|---|---|
| `parentPhrases` | 90/90 có; phần lớn 2–3 câu |
| `easier` / `harder` | 90/90 có; không rỗng / không generic một từ |

### 2.2. Điểm mạnh

- Câu nói **ngắn, nói được ngay** (“Gấu cầm thìa nào”, “Nhún nào!”).  
- Nhiều câu **ghi nhận cố gắng** đúng tone (“Gấu đang tập”, “Con đang lớn lên mỗi ngày”).  
- Easier/harder **thao tác cụ thể** (giảm số bước, nắp lỏng hơn, thêm 1 bước) — phù hợp Lan một tay + Bà Hạnh.

### 2.3. Điểm yếu / chỉnh nhẹ

| ID | Vấn đề | Đề xuất |
|---|---|---|
| `LANG-05` | PP dài 2 mệnh đề: “Con muốn nước phải không? Nói 'nước' nào.” | Tách 2 phrase hoặc rút: “Con muốn nước? Nói ‘nước’ nào.” |
| `act_sc_safe_024_16` | PP cực ngắn (“Nóng”, “Dừng tay”) — ổn với bé; PH có thể cần 1 câu đầy đủ hơn | Thêm 1 PP phụ huynh: “Nóng đó — mình dừng tay nhé.” |
| Nhiều HĐ | “Giỏi quá” chung | P2: khen cụ thể (“Con đưa đúng mũ rồi”) — đã có mẫu tốt ở `LANG-01` |
| `cog_07` / `LANG-10` | PP + bước gần giống nhau | Xem §3 |

**Đánh giá tổng:** đạt mức pilot; không P0 riêng cho easier/harder ngoài việc **lọc jargon khỏi goal** và **tách cặp trùng**.

---

## 3. Trùng ý / trùng tiêu đề còn sót

| Cặp | Tiêu đề | Đánh giá | Hướng xử lý |
|---|---|---|---|
| — | Trùng **title exact** | **0** (khớp CONTENT_QA) | Giữ |
| `cog_07_hai_buoc_don_do` ↔ `LANG-10` | “Làm/Theo 2 bước đơn giản” | **Trùng ý mạnh** (cùng bóng→giỏ/hộp) | **P0/P1:** đổi 1 bên sang ngữ cảnh khác (cất đồ / đưa–đặt) hoặc gộp 1 id, domain chính rõ |
| `phy_09_van_nap_hop` ↔ `cog_03_mo_nap_hop` | Vặn/mở nắp hộp | Gần nhau; khác nhấn (xoay vs hai tay) | **P1:** làm rõ tiêu đề + 1 bước khác biệt; tránh cùng ngày picker |
| `phy_13_xep_khoi_thap` ↔ `cog_15_xep_khoi_theo_mau` | Xếp tháp / theo mẫu | Cạnh nhau hợp lý nếu copy phân biệt “chồng cao” vs “làm giống mẫu” | **P1:** siết goal + PP; picker tránh cả hai cùng ngày |
| `act_sc_eat_018_01` ↔ `act_sc_eat_030_05` | Thìa / dọn thìa | **Tiến trình tuổi** — giữ | Không gộp |
| `phy_03` ↔ `AES-01` | Nguệch sáp | Đã đổi title AES-01 | OK; theo dõi picker |

---

## 4. Đề xuất 10 chủ đề bổ ích còn thiếu (bối cảnh VN)

Ưu tiên đồ có sẵn / ngoài đời thật / 18–36m — **không** lấn module cháy–điện–đường sâu (*Bé Gấu An Toàn*).

1. **Áo mưa / ủng ngày mưa** — mặc–cởi, cảm giác ướt/khô.  
2. **Đi chợ / mua rau thật** (ngắn) — chỉ/ngửi/đếm 1–2 món (khác `LANG-07` chợ đồ chơi).  
3. **Chào ông bà / gọi điện ông bà** — gắn người chăm thứ hai.  
4. **Dép vào–ra cửa** — nếp ra ngoài / về nhà.  
5. **Bỏ rác vào thùng** — 1 bước tự lập + môi trường.  
6. **Đánh răng / lau răng trước gương** — tách rõ khỏi nghi thức ngủ 4 bước.  
7. **Quạt / mát quá–lạnh quá** — từ cảm giác + quy tắc “không thò tay vào nan”.  
8. **Sân chung cư / công viên 5–10 phút** — chạy ngắn, nhặt lá (an toàn giám sát).  
9. **Mâm cơm gia đình** — chỉ bát–đũa–thìa trên bàn (không ép ăn).  
10. **Tết / Trung thu nhẹ** — đèn ông sao giấy hoặc dán cửa đỏ (không nội dung tôn giáo sâu).  
11. *(bonus)* **Tưới cây ban công** — nước nhẹ, quan sát lá.  
12. *(bonus)* **Xe buýt / xe máy đồ chơi kể chuyện đi làm** — ngôn ngữ xã hội; **không** dạy qua đường thật.

---

## 5. Mẫu thông báo nhắc nhở + banner web?

### 5.1. Hiện trạng

| Nền | Nhắc nhở |
|---|---|
| **Web pilot** | **Chưa có** system/in-app reminder; chỉ banner disclaimer nháp |
| **Android** | `ReminderNotifier`: title “Hôm nay mình thử một hoạt động ngắn?” — **đúng tone** §2.3 |

PRODUCT_DESIGN S04 đã phác banner quyền: *“Bật nhắc để không quên chơi tối nay”* — cần **làm mềm** (tránh “không quên” kiểu nợ).

### 5.2. Có nên thêm banner trên web?

**Có — P1 pilot**, banner **in-app** (không cần Web Push):  
- Mục đích: mô phỏng S03/S04 + lấy phản hồi copy trước Closed testing.  
- Tần suất: ≤ 1 lần/ngày session hoặc dismiss 7 ngày (khớp “≤1 lần/tuần” khi denied).  
- Không chặn đọc Hôm nay; CTA phụ “Để sau”.

**Copy đề xuất banner (web):**  
- Body: “Muốn một lời nhắc nhẹ mỗi tối? Trên app sẽ gửi 1 lần/ngày — bạn tắt được bất cứ lúc nào.”  
- CTA: [Xem giờ nhắc] / [Để sau]  
- **Không:** “Bạn đã bỏ lỡ nhắc”, “Bạn chưa chơi hôm qua”.

### 5.3. Bank copy nhắc (đưa Wave 5 / Android strings)

**DAILY_ACTIVITY** (đã gần OK trên Android — giữ / tinh chỉnh):  
- Title: `Hôm nay mình thử một hoạt động ngắn?`  
- Body: `Mỗi bé một nhịp riêng — mở Gấu Con khi bạn sẵn sàng.`  
- Body có tên: `Chào bạn — có gợi ý cho {tên} khi bạn rảnh.`

**Sau khi đã hoàn thành ≥1 HĐ trong ngày (R4 skip — nếu vẫn cần soft):**  
- Không gửi “bạn giỏi vì streak”; chỉ im lặng hoặc (P2) “Hôm nay đã có một lần chơi cùng {tên} — nghỉ cũng được.”

**JOURNAL_WEEKLY:**  
- Title: `Muốn lưu một khoảnh khắc tuần này?`  
- Body: `Một ảnh hoặc một từ mới của {tên} là đủ.`

**ROUTINE nhẹ (khi PH tự bật):**  
- Title: `Giờ {ăn/ngủ/…} của {tên}?`  
- Body: `Chỉ là lời nhắc — làm theo nhịp nhà mình nhé.`

**R3 soft-prompt trong app:**  
- “Giờ nhắc này còn hợp với bạn không?” → Đổi giờ / Thưa hơn / Tắt.

---

## 6. P0 / P1 / P2 — chỉnh copy

### P0 — làm trước khi coi copy PH “sạch pilot”

1. **Gỡ CDC + English khỏi `goal` hiện S05** (~20 `phy_*`/`cog_*`); chuyển nguồn sang field nội bộ / note research.  
2. **Welcome web:** bỏ hoặc chú thích “GDMN” → “gợi ý theo hướng giáo dục mầm non (tham khảo), chưa duyệt chính thức”.  
3. **Tách hoặc gộp `cog_07` ↔ `LANG-10`** (trùng 2 bước bóng→giỏ) để Daily Picker không đưa 2 thẻ “cùng một trò”.

### P1 — nên có trước Closed testing phụ huynh

1. Phân biệt rõ `phy_09`/`cog_03` và `phy_13`/`cog_15` (title + 1 bước + PP).  
2. Progress web: đổi “Mục tiêu MVP ≥ 3/tuần” → “Gợi ý nhẹ: khoảng 3 lần chơi cùng con mỗi tuần cũng tốt — không sao nếu ít hơn.”  
3. Banner nhắc in-app trên web (§5) + đồng bộ bank với Android.  
4. Rút/gói phần dài nhất (`act_sc_sleep_018_06`, `LANG-14`) nếu Bà Hạnh báo “đọc nhiều”; ưu tiên cắt goal jargon trước.  
5. `LANG-05` / safety PP: tách câu dài; thêm 1 PP đầy đủ cho `Nóng — dừng tay`.

### P2 — backlog nội dung & polish

1. 10–12 HĐ chủ đề VN (§4).  
2. Thay “Giỏi quá” chung → khen cụ thể nơi còn generic.  
3. `checklist` → “danh sách 3 món” (`act_sc_sleep_030_09`).  
4. More page: ẩn jargon dev khỏi copy PH.  
5. CTA “Đánh dấu hoàn thành” → “Hoàn thành” (khớp PRODUCT_DESIGN).

---

## Phụ lục — Phạm vi đã đọc

- `plans/WAVE4_EXPERT_QA.md`, `CONSENSUS.md` §2.3, `PRODUCT_DESIGN.md` (tone, S03–S06, notif)  
- `web/content/activities.json` (90) · `web/src/app.mjs` · `web/qa/CONTENT_QA_20260924.md`  
- Tham chiếu Android: `gaucon/.../ReminderNotifier.kt` (không sửa)

---

## Báo cáo agent

```
STATUS: OK
SCOPE: X3 biên tập giọng văn / tone / PP / dễ-khó / trùng ý / chủ đề thiếu / copy nhắc + P0–P2
DONE: Viết đủ 6 mục + bảng ưu tiên vào qa/experts/03_noi_dung_giong_van.md
FILES: qa/experts/03_noi_dung_giong_van.md
DEVIATIONS: Dùng scan tạm local để đếm jargon/trùng; không mở browser smoke lại (đã có CONTENT_QA PASS)
BLOCKERS: Không
ERRORS: Không
NEXT_FOR_PARENT: Chờ X1/X2/X4 → X5 gộp; Wave 5 ưu tiên P0 goal CDC + tách cog_07/LANG-10 + copy Welcome
```
