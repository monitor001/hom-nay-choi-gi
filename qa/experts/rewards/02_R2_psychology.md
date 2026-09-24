# R2 — Tâm lý PH–trẻ & nuôi dưỡng (guardrail thưởng / đổi GX)

> App: **Hôm nay chơi gì?** · Persona: Parenting & child development **R2**  
> Nguồn: `00_CHARTER.md` · `plans/CONSENSUS.md` §2.3 tone · `docs/SPEC_MVP.md` §2 (không phần thưởng gây nghiện)  
> Phạm vi: **guardrail tâm lý + copy** — không bảng giá (R1), không streak/anti-abuse chi tiết (R3), **không code**.

```
STATUS: OK
SCOPE: Guardrails tâm lý cho economy GX ↔ gợi ý vật phẩm đời thật (PH đổi, 18–36m VN)
DONE: Cấm copy/UX food-as-control; chốt ai kiếm GX; ưu tiên connection/choice/play; catalog allowed/discouraged; template disclaimer VI; verdict REVISE
FILES: qa/experts/rewards/02_R2_psychology.md
DEVIATIONS: CONSENSUS §2.3 hiện viết «không coin/huy hiệu gây nghiện» — economy GX chỉ PASS nếu Soft Ledger + guardrail R2 (xem Verdict + EDITS consensus)
BLOCKERS: none (chờ R1 catalog + R3 earn loop khớp EDITS)
ERRORS: none
NEXT_FOR_PARENT: R4 chỉ chốt economy nếu R1/R3 áp EDITS_REQUIRED; nếu catalog «đổi kẹo / bim bim» làm hero → REJECT
VERDICT: REVISE
```

---

## Verdict (ethics)

| Kết quả | Khi nào |
|---|---|
| **PASS** | GX = sổ điểm mềm **của PH**; kiếm khi **hoàn thành chơi thật với con**; đổi = gợi ý PH tự chọn ngoài đời; catalog **connection-first**; disclaimer + cấm framing ép hành vi; UX không slot/particle/urgency cho trẻ |
| **REVISE** *(chốt R2)* | Charter hiện cho phép catalog ví dụ kẹo/bim bim/nước mía/ăn ốc — **được liệt kê trung tính** nhưng **không được** là default / hero / “thưởng vì ngoan”. Cần sửa ranking, nhãn, copy, earn ownership trước khi ship |
| **REJECT** | Bé “kiếm xu” / grind màn hình; streak đổi đồ ngọt; copy “ăn kẹo nếu… / không được ăn nếu chưa…”; badge–leaderboard–FOMO; food dùng làm contingency chính cho toddler |

**Chốt R2:** Economy **không tự động vi phạm ethics** nếu là *parent companion soft ledger*. Economy **vi phạm** nếu biến thành *token economy kiểm soát hành vi trẻ bằng đồ ăn*. Với đề xuất catalog thực phẩm ngọt trong charter → **REVISE bắt buộc**, không PASS nguyên trạng.

Neo CONSENSUS: phần thưởng cốt lõi vẫn là **tương tác thật** (ôm, khen cụ thể, chơi thêm). GX chỉ là **nhật ký nhẹ + gợi ý PH tự thưởng cho *mối quan hệ***, không thay thế gắn bó.

---

## 1. Food-as-control với toddler 18–36m — rủi ro & cấm

### 1.1. Vì sao nguy hiểm ở độ tuổi này

- **Đói–no còn đang học:** Contingency “làm X → được kẹo” làm nhiễu tín hiệu no và gắn đồ ngọt với “làm xong việc / được yêu”.
- **Não tự điều chỉnh còn non:** Toddler cần **đồng điều tiết** (co-regulation), không lịch thưởng–phạt contingency dày đặc kiểu token economy lớp học lớn hơn.
- **Quyền lực & xung đột:** Đồ ăn thành đòn bẩy → tăng “ăn vạ / mặc cả / chỉ ngoan vì có thưởng”.
- **Gắn cảm xúc–đường:** Dễ thành pattern “buồn → ngọt”, “căng → bim bim” — không phù hợp mục tiêu nuôi dưỡng app.
- **Lệch với giọng sản phẩm:** App không phán xét / không KPI thời gian màn hình; dùng thức ăn ép hành vi = phán xét ngầm (“bé phải đáng thưởng”).

### 1.2. CẤM trong copy / UX (hard forbid)

| Cấm | Ví dụ xấu (không ship) |
|---|---|
| Contingency đồ ăn ↔ hành vi | “Chơi xong mới được kẹo”, “Ngoan thì được bim bim”, “Không ăn ốc nếu chưa đủ GX” |
| Đe dọa rút thưởng ăn | “Hôm nay không đủ xu → không có nước mía” |
| Gán nhãn đạo đức qua đồ ăn | “Bé ngoan / bé hư”, “đáng thưởng”, “phạt bằng cách không cho ăn” |
| Ép PH dùng food control | “Mẹo: dùng kẹo để bé ngồi yên lúc chơi” |
| UX khiến bé tưởng app thưởng trực tiếp | Màn đổi thưởng màu slot, confetti lớn, âm thanh “jackpot”, icon kẹo nhảy khi tap complete |
| Đồng nhất “đổi GX = phải mua đồ ngọt” | Hero card chỉ kẹo/bim bim; trống danh mục gắn bó |
| So sánh / xếp hạng thưởng | “Gia đình khác đổi nhiều hơn”, leaderboard đồ ăn |

### 1.3. Được phép nói về thức ăn như thế nào

- **Trung tính, tùy chọn, do PH quyết:** “Một số gia đình đôi khi chọn… — bạn chọn gì hợp nhà mình.”
- **Tách khỏi “bé phải đáng”:** Nhấn **PH tự chăm sóc mối quan hệ / kỷ niệm nhỏ**, không “trả công ngoan”.
- **Ưu tiên dinh dưỡng / văn hóa quen (VN) khi nhắc đồ ăn:** trái cây, sữa chua không đường thêm, nước lọc/nước mát — **không** đẩy đường là mặc định.
- **An toàn ăn uống:** không gợi ý đồ dễ nghẹn cho &lt;36m (kẹo cứng, hạt nhỏ, que kem không giám sát — đồng bộ tinh thần C1).

---

## 2. Ai kiếm GX — ownership cứng

| Ai | Vai trò | Quy tắc |
|---|---|---|
| **Phụ huynh (PRIMARY earner)** | Hoàn thành vòng chơi thật với con (complete activity + nhật ký nhẹ nếu có) | **Chỉ PH** thấy số GX; copy luôn “bạn đã chơi cùng {tên}…” |
| **Trẻ** | Người chơi **ngoài đời**, không phải player economy | **Không** avatar bé kiếm xu; **không** mission “bé mở app / xem video / tap đủ N lần” |
| **Gia đình** | Optional sau này: “cả nhà cùng chơi” | Vẫn ghi nhận trên hồ sơ **caregiver action**, không child grind |

**Anti-patterns earn (R3 phải chặn):**

1. GX tăng khi chỉ mở app / scroll thư viện / đổi gợi ý liên tục.
2. GX gắn nội dung trẻ xem trên màn hình (nếu có video có hẹn giờ — **không** thưởng GX cho phút xem).
3. Copy kiểu “Bé đã kiếm được 10 GX”.
4. Nhiều tầng nhân thưởng kích thích mở app lại trong ngày (xem R3; R2: tối đa ghi nhận **chơi thật**, không dopamine loop).

**Khớp SPEC:** Màn hình cho bố mẹ; bé học qua tương tác thật. GX = tín hiệu **PH đã có mặt**, không phải điểm của trẻ.

---

## 3. Thưởng ưu tiên (hierarchy nuôi dưỡng)

Thứ tự **bắt buộc** khi thiết kế catalog + default UI (trên xuống dưới):

1. **Connection** — ôm, ngồi cạnh, kể 1 câu về lúc chơi, “mẹ/bố thích chơi với con”.
2. **Choice** — bé chọn sách / bài hát / góc chơi tiếp (trong biên an toàn PH đặt).
3. **Play** — thêm 5–10 phút cùng trò, hoặc “chơi lại lần nữa ngày mai”.
4. **Predictability** — giữ nếp (giờ ngủ, giờ chơi cố định), không đổi luật thưởng đột ngột.
5. **Specific praise (process)** — “Con đã kiên nhẫn xếp khối” — không “con giỏi nhất / ngoan nhất”.
6. **Non-food privilege nhẹ** — chọn áo, chọn cốc màu, dán sticker vào nhật ký giấy (ngoài app).
7. **Vật phẩm / trải nghiệm thưa** — đồ chơi nhỏ, ra ngoài ăn món quen **thỉnh thoảng**, không hằng ngày sau mỗi HĐ.
8. **Đường / snack siêu chế biến** — **cuối bảng**, nhãn “tùy chọn · không khuyến khích làm thưởng thường xuyên”.

**Nguyên tắc vàng:** Thưởng tốt nhất với 18–36m là **sự có mặt có chủ đích** của PH, không phải vật đổi được.

---

## 4. Catalog — Allowed vs Discouraged

> App **không ship hàng**. Đây là gợi ý PH tự chuẩn bị. Nhãn UI đề xuất: `Gắn bó` · `Chơi thêm` · `Tùy chọn nhà mình` · `Cân nhắc`.

### 4.1. ALLOWED — ưu tiên cao (default / hero)

| Nhóm | Ví dụ VN | Ghi chú R2 |
|---|---|---|
| Thời gian gắn bó | Ôm 2 phút, đọc 1 quyển, hát ru / bài quen | Hero mặc định |
| Lựa chọn của bé | Chọn trò tiếp, chọn nhạc, chọn góc sàn | Trong biên PH |
| Chơi thêm | Thêm 1 hiệp ngắn, “chơi lại trò hôm nay” | Khớp KPI hoạt động/tuần |
| Nếp dễ đoán | Giữ giờ chơi cố định, countdown dịu trước chuyển hoạt động | Không streak tội lỗi |
| Khen cụ thể | 1 câu process praise | Template sẵn cho PH |
| Trải nghiệm cảm giác an toàn | Xúc cát khô / nước trong chậu có giám sát, nặn bột | An toàn > “đồ hiệu” |
| Đồ chơi/open-ended sẵn có | Khối, bóng mềm, búp bê vải, sách | Không ép mua mới |
| Ngoài trời nhẹ | Ra sân, nhìn mưa từ hiên, đi bộ ngắn | Có caregiver |
| Ẩm thực **dinh dưỡng / văn hóa** (thưa) | Trái cây cắt mềm, sữa chua ít đường, cháo/bánh nhà làm nhỏ | Nhãn “bữa phụ bình thường”, **không** “thưởng ngoan” |
| Đồ uống mát không đẩy đường | Nước lọc, nước mát nhà, sữa theo nếp | Tránh “đổi = nước mía mỗi lần” |

### 4.2. ALLOWED có điều kiện — lộ diện thấp, nhãn cảnh báo

| Nhóm | Ví dụ | Điều kiện |
|---|---|---|
| Snack mặn/ngọt nhẹ theo văn hóa | Bim bim, bánh snack | Chỉ mục “thỉnh thoảng”; disclaimer; không default |
| Đồ ngọt | Kẹo, kem (ly &gt; que nếu &lt;36m) | Không contingency; không sau mỗi HĐ; giám sát nghẹn |
| Đồ uống đường | Nước mía, trà đường | Văn hóa OK nhưng **không** làm reward loop; ưu tiên thưa |
| Ăn ngoài món quen | Ăn ốc, hàng quán quen gia đình | Là **kỷ niệm gia đình**, không “đổi điểm = được đi ăn vì đủ ngoan” |
| Đồ chơi mua mới | Xe đẩy nhỏ, gấu bông | Trần thưa (R1); không thúc FOMO |

### 4.3. DISCOURAGED — không đưa vào catalog MVP (hoặc ẩn sâu + cấm copy contingency)

| Nhóm | Lý do |
|---|---|
| Kẹo cứng / hạt nhỏ / đồ dễ nghẹn làm “thưởng” | An toàn &lt;36m |
| Năng lượng cao / cafein / trà đặc | Không phù hợp toddler |
| “Combo đường mỗi ngày đổi GX” | Food-as-control + nghiện ngọt |
| Màn hình thêm (YouTube, game) đổi GX | Trái SPEC không thưởng gây nghiện / không KPI screen |
| Tiền mặt / quà đắt / so sánh hàng xóm | Áp lực PH, lệch mục tiêu gắn bó |
| Phạt bằng cách trừ quyền ăn uống | Độc hại gắn bó & ăn uống |
| Huy hiệu “bé ngoan” / level đường | Gây nghiện + phán xét |

### 4.4. Quy tắc sắp xếp UI đổi thưởng (R1 phải theo)

1. Tab/section đầu: **Gắn bó & chơi** (≥ 60% slot hiển thị mặc định).
2. Section hai: **Đồ / trải nghiệm không ăn**.
3. Section ba (thu gọn): **Đồ ăn & uống — tùy chọn**, luôn kèm 1 dòng disclaimer ngắn.
4. Không đặt kẹo/bim bim/nước mía ở vị trí đầu danh sách hoặc “phổ biến nhất” nếu metric đó chỉ phản ánh copy cũ.

---

## 5. Copy templates (VI) — màn Đổi thưởng / disclaimer

Tone: **bạn** + tên bé; mời; không phán xét; không FOMO. Khớp CONSENSUS §2.3.

### 5.1. Tiêu đề & lead

- **Tiêu đề:** `Gợi ý nhỏ sau khi chơi cùng {tên}`
- **Lead:** `Gấu Xu ghi lại lúc bạn đã chơi thật với con — không phải điểm của bé trên màn hình.`
- **Phụ:** `Bạn chọn cách phù hợp nhà mình. Không sao nếu chỉ ôm và khen một câu.`

### 5.2. Disclaimer cố định (bắt buộc trên màn redeem / lần đầu mở)

**Bản ngắn (luôn hiện, ~2 dòng):**

> Gấu Xu là sổ ghi nhớ cho bố mẹ, không phải trò đổi thưởng của bé.  
> Ưu tiên ôm, lời khen cụ thể và chơi thêm. Đồ ăn ngọt chỉ là gợi ý thỉnh thoảng — không dùng để ép bé “ngoan”.

**Bản đủ (bottom sheet / “Tìm hiểu thêm”):**

> **Hôm nay chơi gì?** gợi ý việc chơi ngoài đời thật.  
> Khi bạn hoàn thành hoạt động cùng {tên}, app có thể cộng Gấu Xu như một dấu mốc nhẹ.  
> Phần “đổi” chỉ là **ý tưởng** bạn tự chuẩn bị ở nhà hoặc ngoài quán — app không bán hàng và không bắt bạn phải thưởng bằng đồ ăn.  
> Với trẻ 18–36 tháng, gắn bó và chơi cùng hiệu quả hơn kẹo hay bim bim.  
> Nếu nhà mình đang tập nếp ăn uống, hãy chọn gợi ý **không dùng thức ăn để đổi hành vi**.

### 5.3. Nhãn danh mục

| Category | Nhãn UI |
|---|---|
| Connection | `Gắn bó` |
| Choice / play | `Chơi thêm & lựa chọn` |
| Non-food item | `Đồ chơi / trải nghiệm` |
| Food optional | `Đồ ăn & uống · tùy chọn` |

Microcopy section đồ ăn:

> `Thỉnh thoảng · do bạn quyết — không gắn với “bé phải đáng”.`

### 5.4. Card item — mẫu câu

| Loại | Template OK | Cấm |
|---|---|---|
| Connection | `Ôm {tên} và kể một điều bạn thích lúc chơi hôm nay.` | `Thưởng vì ngoan` |
| Choice | `Để {tên} chọn quyển sách hoặc bài hát tiếp theo.` | `Chỉ được chọn nếu đủ xu` (hiện ra như điều kiện với bé) |
| Play | `Chơi lại trò này thêm vài phút, hoặc hẹn “mai mình chơi tiếp”.` | `Phải hoàn thành streak mới được chơi` |
| Food optional | `Nếu hợp nếp nhà, đôi khi cả nhà ăn món quen — như một kỷ niệm, không phải phần thưởng đổi điểm.` | `Đổi 20 GX = 1 cây kẹo` mà không có disclaimer cạnh card |

### 5.5. Sau khi PH “đánh dấu đã chọn gợi ý” (nếu có)

> `Xong rồi. Điều quan trọng là lúc bạn có mặt với {tên} — không phải số xu.`

Không: `Chúc mừng! Bé đã được thưởng!`

### 5.6. Empty / bỏ lỡ (khớp “chưa hợp cũng không sao”)

> `Hôm nay chưa đổi gì cũng ổn. Mai mình chơi tiếp khi bạn sẵn sàng.`

---

## 6. UX / product guardrails (cho R1–R3 & consensus)

1. **Soft ledger, không casino:** không spin, không progress bar “sắp được kẹo”, không countdown redeem.
2. **Số GX kín với trẻ:** không widget lớn kiểu game trên màn hình bé có thể thấy khi PH mở app cạnh con; tránh animation “coin rain”.
3. **Trần đổi đồ ngọt (khuyến nghị sản phẩm):** UI không suggest food reward &gt; 1 lần / ngày và ưu tiên nhắc connection nếu PH vừa chọn food gần đây (R3 có thể implement soft).
4. **Không gắn GX với checklist mốc phát triển** (“đạt mốc → xu”) — tránh biến quan sát thành thi đua.
5. **Không “bạn bỏ lỡ chuỗi đổi thưởng”** — cấm copy streak tội lỗi (CONSENSUS).
6. **Primary KPI vẫn:** hoạt động hoàn thành / tuần — GX chỉ phụ, có thể tắt mục đổi thưởng trong Cài đặt (khuyến nghị P0/P1).

---

## EDITS_REQUIRED

### Cho R1 (Economy / catalog / bảng giá)

1. **Đổi thứ tự catalog:** hero = connection / choice / play; food (kẹo, bim bim, nước mía, ăn ốc…) chỉ group `optional_food` + flag `show_disclaimer=true`.
2. **Cấm SKU framing contingency** trong tên/mô tả item (không “thưởng ngoan”, không “đổi hành vi”).
3. **Nhãn giá GX:** diễn đạt là “gợi ý mức ghi nhận bên PH” chứ không “giá trị bé đổi được”.
4. **Thêm ≥ 8 item non-food** trước khi liệt kê đồ ngọt; tỷ lệ slot mặc định ≥ 60% non-food.
5. **An toàn &lt;36m:** loại hoặc AGE_NOTE kẹo cứng / hạt / que kem; kem ưu tiên ly.
6. Nếu R1 giữ “đổi GX = số gắn từng cây kẹo” như cơ chế chính → R2 đánh giá lại thành **REJECT**.

### Cho R3 (Missions / streak / earn)

1. **Earner = PARENT only** trên mọi mission copy & event name (`parent_completed_activity`, không `child_earned`).
2. **Không GX** cho mở app, xem video, đổi gợi ý spam, hoặc bất kỳ screen-time proxy nào.
3. **Streak:** nếu có, chỉ “nhịp chơi cùng con”, copy chấp nhận bỏ lỡ; **cấm** streak → unlock đồ ngọt.
4. **Anti-abuse tâm lý:** không nhân thưởng liên tiếp trong ngày chỉ để kéo PH mở app; trần earn/ngày mềm.
5. **Không mission “bé ngoan / nghe lời / ngồi yên”** trả GX — lệch nuôi dưỡng gắn bó.

### Cho consensus (`00_CONSENSUS_REWARDS.md` / cập nhật `plans/CONSENSUS.md` nếu parent chốt)

1. **Sửa câu §2.3** từ cấm tuyệt đối “coin” → chốt: *được Soft Ledger GX phía PH; cấm coin/huy hiệu gây nghiện phía trẻ / food-contingency.*
2. Ghi nguyên tắc: **Phần thưởng cốt lõi = tương tác thật**; GX = tùy chọn, tắt được.
3. Ghi **food rewards = optional cultural examples, never default control tool**.
4. KPI không đổi: ≥ 3 HĐ hoàn thành / tuần — **không** tối đa đổi thưởng / thời gian app.

---

## Tóm tắt một dòng cho R4

> **REVISE:** Cho phép GX như sổ mềm của PH sau chơi thật; **ưu tiên gắn bó–lựa chọn–chơi**; đồ ăn chỉ gợi ý thưa + disclaimer; **REJECT** mọi thiết kế biến toddler thành người chơi token đổi đường.
