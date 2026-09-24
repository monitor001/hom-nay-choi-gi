# Gấu Con MVP — Ngôn ngữ · Tình cảm–XH · Thẩm mỹ

> Subagent C · Thử nghiệm phụ `ideas/BeGau` · **Không đổi hướng active**  
> Phạm vi tuổi MVP: **18–36 tháng** · Nháp nội dung — `reviewed_by: []` trên mọi hoạt động  
> Phần thưởng = tương tác thật (ôm, khen cụ thể, chơi tiếp) — **không** điểm, huy hiệu, streak gây nghiện

---

## 0. Nguyên tắc chung (3 lĩnh vực)

| Nguyên tắc | Áp dụng |
|---|---|
| Mời, không phán xét | “Hôm nay mình thử…”, “Mỗi bé có nhịp riêng” — tránh “bé chậm / bỏ lỡ” |
| Người lớn dẫn | App cho phụ huynh; bé học qua chơi ngoài đời thật |
| ≤ 15 phút | Dừng khi bé chán; được dừng sớm = thành công |
| Vật liệu nhà VN | Đồ sẵn có: sách tranh, thìa, nồi, chậu nhựa, giấy A4, sáp màu, nếp/gạo (giám sát), khăn, gối… |
| An toàn cảm xúc | Không hù dọa, không xấu hổ; cảm xúc được gọi tên và chấp nhận |
| Không chẩn đoán | Checklist mốc = quan sát gợi ý; lo lắng → hỏi bác sĩ / chuyên gia |

**Giả định chưa xác minh:** độ khó hoạt động, thời lượng “thực tế với gia đình bận”, và mức độ khớp từng mục tiêu CT GDMN với thói quen nhà Việt Nam — cần GVMN / tâm lý trẻ / phụ huynh pilot duyệt trước xuất bản.

---

## LANGUAGE

### L.1 Mục tiêu theo nhóm tuổi (nháp)

| Nhóm | Mục tiêu nghe | Mục tiêu nói / giao tiếp | Làm quen sách |
|---|---|---|---|
| **18–24m** | Làm theo yêu cầu 1 bước; hiểu “…đâu?”, tên đồ gần gũi | Từ đơn → câu 2–3 tiếng; chỉ + nói nhu cầu | Mở sách, chỉ tranh, lắng nghe ngắn |
| **24–30m** | Hiểu “cái gì / làm gì / ở đâu”; nghe thơ–đồng dao ngắn | Câu 2+ từ (có động từ); ~vốn từ mở rộng; cử chỉ phong phú | Chỉ đúng vật trong tranh khi được hỏi |
| **30–36m** | Hiểu truyện tranh ngắn; làm theo 2 bước đơn giản | Câu dài hơn; hỏi “ai/cái gì/ở đâu”; kể lại hành động đơn giản | Gọi tên nhân vật/hành động; đọc tiếp tiếng cuối thơ quen |

> Tham chiếu: CT GDMN (VBHN 01/VBHN-BGDĐT) lĩnh vực ngôn ngữ nhà trẻ; CDC Act Early 18/24/30/36m (tham khảo quốc tế, **không** thay chuẩn VN). Không dùng để chẩn đoán.

### L.2 Checklist mốc (quan sát — không chấm điểm)

**18–24 tháng**
- [ ] Làm theo 1 yêu cầu không cần chỉ tay (“Đưa bóng cho mẹ”)
- [ ] Nói ≥ vài từ ngoài mama/dada (hoặc tương đương gia đình)
- [ ] Chỉ đồ / tranh khi được hỏi “…đâu?”
- [ ] Thích ngồi xem sách ngắn với người lớn

**24–30 tháng**
- [ ] Nói cụm ≥ 2 từ (“xin sữa”, “mẹ ôm”)
- [ ] Chỉ đúng vật trong sách khi được hỏi
- [ ] Dùng cử chỉ đa dạng (gật, thổi hôn, “xin”)
- [ ] Bắt chước từ / tiếng động vật gần gũi

**30–36 tháng**
- [ ] Nói câu có chủ–vị đơn giản; dùng “con / mẹ / đi…”
- [ ] Hỏi hoặc trả lời “cái gì / ở đâu / làm gì”
- [ ] Kể lại 1 hành động trong tranh (“bé chạy”)
- [ ] Đọc tiếp tiếng cuối bài thơ / đồng dao quen (có gợi ý)

*Nếu phụ huynh lo lắng về mất kỹ năng đã có hoặc chậm nhiều mốc: gợi ý “nên hỏi bác sĩ” — không tự kết luận.*

### L.3 ~15 hoạt động nháp (YAML)

```yaml
- id: LANG-01
  title: Chỉ tay tìm đồ trong nhà
  age_range: [18, 24]
  domains: [LANGUAGE]
  duration_min: 8
  materials: ["2–4 đồ quen: mũ, dép, bóng, bình nước"]
  goal: "Hiểu và làm theo yêu cầu 1 bước; mở rộng từ chỉ đồ."
  steps:
    - "Đặt 3 đồ trước mặt bé, cách nhau rõ."
    - "Nói chậm: 'Đưa mũ cho mẹ.' Đợi 5–10 giây."
    - "Nếu bé đưa đúng: mỉm cười, nhắc lại từ 'Mũ! Cảm ơn con.'"
    - "Đổi vai: mẹ chỉ / bé nói (hoặc bập bẹ) tên đồ."
  parent_phrases:
    - "Mũ đâu rồi nhỉ?"
    - "Con giỏi quá, đưa đúng rồi."
    - "Mẹ cũng tìm theo con này."
  easier: "Chỉ 2 đồ; mẹ chỉ tay cùng lúc nói."
  harder: "Thêm 1 bước: 'Lấy dép rồi đưa cho bà.'"
  safety: "Tránh đồ nhỏ nuốt được; mặt sàn sạch, không chạy đuổi trong bếp."
  reviewed_by: []

- id: LANG-02
  title: Sách tranh 5 phút
  age_range: [18, 30]
  domains: [LANGUAGE]
  duration_min: 5
  materials: ["1 sách tranh dày trang, ít chữ"]
  goal: "Lắng nghe ngắn; chỉ và gọi tên tranh."
  steps:
    - "Ngồi cạnh hoặc bế; để bé lật trang nếu muốn."
    - "Chỉ 1–2 chi tiết/trang: 'Con mèo đây.'"
    - "Hỏi: 'Mèo đâu?' Đợi bé chỉ hoặc nhìn."
    - "Dừng khi bé quay đi — không ép hết sách."
  parent_phrases:
    - "Con muốn mở trang nào?"
    - "À, đây là quả bóng."
    - "Mai mình xem tiếp nhé."
  easier: "Chỉ xem 2–3 trang; mẹ lật hộ."
  harder: "Hỏi hành động: 'Bé đang làm gì?' (24m+)."
  safety: "Sách không có góc sắc/miếng rời; không để bé xé ăn giấy."
  reviewed_by: []

- id: LANG-03
  title: Đồng dao vỗ tay
  age_range: [18, 36]
  domains: [LANGUAGE, AESTHETIC]
  duration_min: 7
  materials: ["Không bắt buộc; có thể thêm 2 thìa gỗ"]
  goal: "Nghe nhịp – vần; bắt chước tiếng cuối câu."
  steps:
    - "Chọn 1 đồng dao ngắn quen (vd. Nu na nu nống / Chi chi chành chành — bản gia đình dùng)."
    - "Nói rõ, vỗ tay theo nhịp; nhìn mặt bé."
    - "Ngừng trước tiếng cuối: để bé nói/bập bẹ tiếp."
    - "Lặp 2–3 lần rồi chuyển trò khác."
  parent_phrases:
    - "Con vỗ cùng mẹ nào."
    - "… nu nống — con nói tiếp đi!"
    - "Nghe hay quá."
  easier: "Chỉ vỗ tay + nghe; không ép nói."
  harder: "Đổi tốc độ chậm/nhanh; để bé 'dẫn' vỗ."
  safety: "Âm lượng vừa; không đùa giật mạnh tay bé."
  reviewed_by: []

- id: LANG-04
  title: Tên gọi cơ thể
  age_range: [18, 24]
  domains: [LANGUAGE]
  duration_min: 6
  materials: ["Gương nhỏ cố định hoặc ảnh bé"]
  goal: "Hiểu câu hỏi '…đâu?'; gắn từ với bộ phận cơ thể."
  steps:
    - "Chạm nhẹ mũi mình: 'Mũi mẹ đây. Mũi con đâu?'"
    - "Vuốt nhẹ khi bé chỉ đúng; nhắc từ."
    - "Lần lượt: mắt, tay, chân, bụng (3–4 từ/buổi)."
    - "Kết thúc ôm hoặc tickle nhẹ nếu bé thích."
  parent_phrases:
    - "Tay đâu rồi?"
    - "Đúng rồi, tay con đây."
    - "Mẹ yêu đôi tay con."
  easier: "Chỉ 2 bộ phận; mẹ cầm tay bé chỉ."
  harder: "Thêm động từ: 'Vỗ tay', 'Stomp chân'."
  safety: "Không chạm vùng nhạy cảm; tôn trọng nếu bé lắc đầu."
  reviewed_by: []

- id: LANG-05
  title: Nói nhu cầu thật
  age_range: [18, 30]
  domains: [LANGUAGE, SOCIAL_EMOTIONAL]
  duration_min: 5
  materials: ["Ly nước / khăn / đồ chơi bé đang muốn"]
  goal: "Dùng từ/câu ngắn bày tỏ nhu cầu thay vì chỉ kéo tay."
  steps:
    - "Khi bé kéo tay xin: dừng 2 giây, nhìn mắt: 'Con muốn gì?'"
    - "Gợi ý mẫu: 'Nước' hoặc 'Xin nước'."
    - "Đưa ngay khi bé thử nói/ra dấu — củng cố nhanh."
    - "Nhắc lại câu đầy đủ: 'Con xin nước. Đây này.'"
  parent_phrases:
    - "Con muốn nước phải không? Nói 'nước' nào."
    - "Mẹ hiểu rồi, cảm ơn con đã nói."
    - "Lần sau con nói mẹ nghe rõ hơn."
  easier: "Chấp nhận cử chỉ + 1 âm; mẹ nói hộ mẫu."
  harder: "Khuyến khích câu 2 từ: 'xin sữa', 'ôm mẹ'."
  safety: "Không bỏ đói/khát để 'ép nói'; nhu cầu sinh lý ưu tiên."
  reviewed_by: []

- id: LANG-06
  title: Tiếng kêu thú gần gũi
  age_range: [18, 30]
  domains: [LANGUAGE, COGNITIVE]
  duration_min: 8
  materials: ["Đồ chơi thú / ảnh / video tắt tiếng nếu có"]
  goal: "Bắt chước âm thanh; gắn tên con vật."
  steps:
    - "Giơ thú: 'Con gà — ò ó o.'"
    - "Đợi bé bắt chước; khen nỗ lực, không sửa giọng."
    - "Hỏi: 'Gà đâu?' để bé chỉ."
    - "Đổi 2–3 con/buổi (mèo, chó, bò…)."
  parent_phrases:
    - "Gà gáy thế nào nhỉ?"
    - "Giống quá!"
    - "Con chọn con nào tiếp?"
  easier: "1 con vật; mẹ làm mẫu nhiều lần."
  harder: "Thêm động từ: 'Chó chạy', 'Mèo ngủ'."
  safety: "Không dùng tiếng động quá to đột ngột."
  reviewed_by: []

- id: LANG-07
  title: Chợ đồ chơi trong nhà
  age_range: [24, 36]
  domains: [LANGUAGE]
  duration_min: 10
  materials: ["Giỏ/túi; 4–6 đồ chơi hoặc trái cây giả/thật đã rửa"]
  goal: "Luyện câu hỏi–trả lời 'cái gì / bao nhiêu / xin…'."
  steps:
    - "Mẹ làm 'người bán'; bé 'mua'."
    - "Hỏi: 'Con mua cái gì?' Đợi bé chỉ hoặc nói."
    - "Lặp từ: 'Một quả chuối đây. Cảm ơn.'"
    - "Đổi vai sau vài lượt."
  parent_phrases:
    - "Xin gì ạ?"
    - "Con muốn quả nào?"
    - "Cảm ơn khách dễ thương."
  easier: "Chỉ chỉ đồ + nói 1 từ."
  harder: "Thêm 'xin … ạ' và chọn theo màu (30m+)."
  safety: "Không đồ thật sắc/nóng; trái cây cắt hạt nếu có rủi ro."
  reviewed_by: []

- id: LANG-08
  title: Kể lại việc vừa làm
  age_range: [30, 36]
  domains: [LANGUAGE]
  duration_min: 8
  materials: ["Ảnh chụp nhanh hoạt động vừa xong (tùy chọn)"]
  goal: "Kể lại 1–2 hành động đơn giản bằng lời."
  steps:
    - "Sau khi chơi xong, ngồi 1 phút: 'Lúc nãy mình làm gì?'"
    - "Gợi ý tranh/ảnh hoặc đồ vừa dùng."
    - "Mẹ nói mẫu câu ngắn; để bé lặp hoặc bổ sung từ."
    - "Viết 1 dòng nhật ký phụ huynh (app) nếu muốn — không bắt bé."
  parent_phrases:
    - "Con đã xếp khối phải không?"
    - "Kể mẹ nghe một chút nào."
    - "Mẹ thích nghe con kể."
  easier: "Chọn/không bằng gật đầu; mẹ kể, bé chỉ."
  harder: "Hỏi 'rồi sau đó?'; thêm 1 chi tiết."
  safety: "Không quay video dài khiến bé mệt; tôn trọng nếu bé không muốn kể."
  reviewed_by: []

- id: LANG-09
  title: Hộp bí mật cảm giác + từ
  age_range: [24, 36]
  domains: [LANGUAGE, COGNITIVE]
  duration_min: 10
  materials: ["Hộp/túi vải; 3 đồ an toàn khác chất liệu (khăn, thìa nhựa, bóng vải)"]
  goal: "Đoán và gọi tên đồ bằng xúc giác + lời."
  steps:
    - "Bé thọc tay vào (mắt mở cũng được)."
    - "Hỏi: 'Con sờ thấy gì? Mềm hay cứng?'"
    - "Kéo ra, đặt tên cùng nhau."
    - "Lặp với đồ tiếp theo."
  parent_phrases:
    - "Ối, mềm quá!"
    - "Đây là khăn."
    - "Con muốn đồ nào nữa?"
  easier: "Nhìn thấy đồ trước khi bỏ vào hộp."
  harder: "Chỉ mô tả, để mẹ đoán theo lời bé (30m+)."
  safety: "Không vật nhỏ/sắc; giám sát tay trong hộp."
  reviewed_by: []

- id: LANG-10
  title: Theo 2 bước đơn giản
  age_range: [30, 36]
  domains: [LANGUAGE]
  duration_min: 8
  materials: ["Đồ chơi + hộp đựng"]
  goal: "Nghe hiểu chuỗi 2 hành động ngắn."
  steps:
    - "Nói rõ 2 bước: 'Lấy bóng và bỏ vào hộp.'"
    - "Đợi; chỉ nhắc lại nếu cần, không làm hộ ngay."
    - "Khen quá trình: 'Con làm đủ hai việc.'"
    - "Đổi cặp động từ khác (mở–đóng, đưa–đặt)."
  parent_phrases:
    - "Nghe mẹ nhé: một… hai…"
    - "Giỏi quá, làm lần lượt."
    - "Mình thử cặp khác nào."
  easier: "Làm từng bước có nghỉ giữa."
  harder: "Thêm vị trí: '…rồi đưa hộp cho bố'."
  safety: "Không biến thành 'kiểm tra'; giữ vui, dừng khi bé khó chịu."
  reviewed_by: []

- id: LANG-11
  title: Điện thoại giả bộ
  age_range: [24, 36]
  domains: [LANGUAGE, SOCIAL_EMOTIONAL]
  duration_min: 8
  materials: ["Điện thoại đồ chơi hoặc khối gỗ làm máy"]
  goal: "Luyện chào hỏi và câu thoại ngắn."
  steps:
    - "Reo 'ting ting'; đưa máy cho bé."
    - "Mẫu: 'Alo, con là ai? Con chào bà…'"
    - "Để bé bập bẹ/nói; mẹ đáp lại nghiêm túc vui."
    - "Kết: 'Tạm biệt, cúp máy.'"
  parent_phrases:
    - "Alo, ai gọi đó?"
    - "Con nói lớn một chút mẹ nghe."
    - "Tạm biệt bà nhé."
  easier: "Chỉ 'alo' và vẫy tay."
  harder: "Kể 1 việc trong ngày qua điện thoại (30m+)."
  safety: "Không dùng máy thật đang gọi; tránh nội dung sợ hãi."
  reviewed_by: []

- id: LANG-12
  title: Thơ ngắn đọc tiếp
  age_range: [24, 36]
  domains: [LANGUAGE]
  duration_min: 7
  materials: ["1 bài thơ/vè 4–6 câu đã thuộc của gia đình"]
  goal: "Đọc tiếp tiếng/cụm cuối câu quen."
  steps:
    - "Đọc to, chậm, đều."
    - "Ngừng trước tiếng cuối mỗi câu."
    - "Mỉm cười khi bé điền âm/từ gần đúng."
    - "Hát hoặc vỗ nhịp nếu bé thích."
  parent_phrases:
    - "… con thỏ…?"
    - "Đúng rồi!"
    - "Mai mình đọc lại bài này."
  easier: "Chỉ 1 câu lặp đi lặp lại."
  harder: "Để bé 'dẫn' 1 câu (30m+)."
  safety: "Chọn thơ vui, không nội dung trừng phạt/hù dọa."
  reviewed_by: []

- id: LANG-13
  title: Chỉ đường trong phòng
  age_range: [24, 36]
  domains: [LANGUAGE]
  duration_min: 8
  materials: ["Không"]
  goal: "Hiểu từ vị trí: trên/dưới/trong/ngoài (mức làm quen)."
  steps:
    - "Giấu bóng dưới khăn trước mắt bé."
    - "Hỏi: 'Bóng đâu? Dưới khăn.'"
    - "Lặp với 'trong hộp', 'trên ghế' (ghế thấp)."
    - "Để bé giấu, mẹ tìm và nói to vị trí."
  parent_phrases:
    - "Trên ghế này!"
    - "Con giấu khéo quá."
    - "Mẹ tìm thấy rồi."
  easier: "Chỉ 1 cặp: trong/ngoài."
  harder: "Bé nói vị trí trước khi mẹ tìm."
  safety: "Không giấu gần ổ điện/bếp; giám sát leo ghế."
  reviewed_by: []

- id: LANG-14
  title: Nhật ký từ mới trong ngày
  age_range: [18, 36]
  domains: [LANGUAGE]
  duration_min: 5
  materials: ["Giấy hoặc ghi chú app phụ huynh"]
  goal: "Người lớn nhận ra và lặp lại từ bé đang tập — củng cố vốn từ."
  steps:
    - "Cuối ngày, nhớ 1–3 từ/cử chỉ mới của bé."
    - "Ngồi với bé, dùng lại từ đó trong câu vui."
    - "Không sửa phát âm cứng nhắc; mô hình đúng 1 lần nhẹ nhàng."
    - "Ghi nhật ký ngắn cho mình (không chấm điểm bé)."
  parent_phrases:
    - "Hôm nay con nói 'bóng' hay quá."
    - "Mai mình chơi bóng tiếp."
    - "Con đang lớn lên mỗi ngày."
  easier: "Chỉ 1 từ/ngày."
  harder: "Ghép từ mới vào câu 3–4 tiếng (30m+)."
  safety: "Không so sánh với trẻ khác trong lời nói với bé."
  reviewed_by: []

- id: LANG-15
  title: Hỏi–đáp tranh lớn
  age_range: [30, 36]
  domains: [LANGUAGE]
  duration_min: 10
  materials: ["1 tranh khổ A4/A3 hoặc lịch ảnh gia đình"]
  goal: "Trả lời 'ai / cái gì / làm gì'; mở hội thoại 2 lượt."
  steps:
    - "Cùng nhìn 1 tranh 20 giây."
    - "Hỏi lần lượt: 'Ai đây?', 'Đang làm gì?', 'Ở đâu?'"
    - "Đáp lại ý bé bằng cách mở rộng: 'Ừ, mẹ đang nấu.'"
    - "Đổi: bé hỏi mẹ 1 câu (kể cả bập bẹ)."
  parent_phrases:
    - "Con thấy gì nào?"
    - "Mẹ cũng thấy xe đó."
    - "Con hỏi mẹ đi."
  easier: "Chỉ hỏi 'cái gì đây?'."
  harder: "Thêm 'vì sao' rất đơn giản nếu bé sẵn sàng."
  safety: "Tranh không hình ảnh đáng sợ; tránh ép trả lời đúng."
  reviewed_by: []
```

---

## SOCIAL_EMOTIONAL

### S.1 Mục tiêu theo nhóm tuổi (nháp)

| Nhóm | Cảm xúc | Gắn kết | Xã hội / luân phiên |
|---|---|---|---|
| **18–24m** | Biểu lộ vui/buồn; tìm người lớn khi cần an toàn | Chơi cạnh mẹ; “kiểm tra” nhìn lại | Chào/tạm biệt có nhắc; chơi cạnh (song song) |
| **24–30m** | Gọi tên vui–buồn–giận (làm quen); nhìn mặt người lớn khi tình huống mới | Khoẻ khoắn khi chia tay ngắn nếu có nghi thức | Đưa–nhận đồ có người lớn; “đến lượt” rất ngắn |
| **30–36m** | Nhận biết cảm xúc cơ bản ở mình/người khác (mức đơn giản) | Tự hào “nhìn con”; làm theo nếp đơn giản | Chơi cạnh/bắt đầu chơi với bạn; chờ ngắn; cảm ơn/xin |

> Không dùng nội dung hù dọa, xấu hổ, hoặc “phạt cảm xúc”. Giận dữ được chấp nhận + dẫn hành vi an toàn (“tay nhẹ”).

### S.2 Checklist mốc

**18–24 tháng**
- [ ] Đi khám phá nhưng quay nhìn người lớn
- [ ] Chỉ cho người lớn xem thứ thú vị
- [ ] Thích xem sách/chơi ngắn cùng người lớn
- [ ] Biểu lộ rõ khi vui hoặc khó chịu

**24–30 tháng**
- [ ] Để ý khi người khác khóc/buồn (nhìn, dừng lại)
- [ ] Nhìn mặt bố mẹ khi tình huống lạ
- [ ] Tham gia nếp đơn giản khi được mời (“cất đồ”)
- [ ] Chơi cạnh trẻ khác trong thời gian ngắn

**30–36 tháng**
- [ ] Gọi tên 1–2 cảm xúc (vui/buồn) khi được gợi
- [ ] Chào / cảm ơn / tạm biệt trong vài tình huống quen
- [ ] Chờ “đến lượt” vài giây–vài chục giây với hỗ trợ
- [ ] Mời người lớn xem “Con làm được này!”

### S.3 ~15 hoạt động nháp (YAML)

```yaml
- id: SOC-01
  title: Gương mặt cảm xúc
  age_range: [18, 30]
  domains: [SOCIAL_EMOTIONAL, LANGUAGE]
  duration_min: 6
  materials: ["Gương hoặc mặt mẹ"]
  goal: "Nhận biết vui/buồn qua nét mặt; gọi tên cảm xúc."
  steps:
    - "Làm mặt cười: 'Mẹ vui.'"
    - "Làm mặt buồn nhẹ: 'Mẹ buồn.'"
    - "Hỏi: 'Con vui hay buồn?' Chấp nhận mọi câu trả lời."
    - "Ôm nếu bé muốn kết thúc ấm."
  parent_phrases:
    - "Mặt cười là vui."
    - "Buồn cũng được, mẹ ở đây."
    - "Con chỉ mặt vui nào."
  easier: "Chỉ mặt vui."
  harder: "Thêm 'giận' với mặt nghiêm nhưng giọng êm (24m+)."
  safety: "Không làm mặt đáng sợ/hù; không quay phim nếu bé không thích."
  reviewed_by: []

- id: SOC-02
  title: Ôm–thở cùng nhau
  age_range: [18, 36]
  domains: [SOCIAL_EMOTIONAL]
  duration_min: 5
  materials: ["Chỗ ngồi êm"]
  goal: "Gắn kết; làm dịu khi quá tải cảm xúc."
  steps:
    - "Ngồi cùng, ôm hoặc nắm tay (nếu bé đồng ý)."
    - "Hít vào đếm 1–2, thở ra chậm (mẹ làm mẫu)."
    - "Nói: 'Mẹ ở đây với con.'"
    - "Khi dịu hơn, hỏi muốn chơi gì nhẹ."
  parent_phrases:
    - "Mẹ hiểu con đang khó."
    - "Mình thở chậm nào."
    - "Con an toàn rồi."
  easier: "Chỉ ôm, không đếm."
  harder: "Bé thổi 'gió' làm bay khăn (thở ra)."
  safety: "Không ép ôm; không nhốt/cách ly để phạt."
  reviewed_by: []

- id: SOC-03
  title: Đến lượt lăn bóng
  age_range: [24, 36]
  domains: [SOCIAL_EMOTIONAL, PHYSICAL]
  duration_min: 8
  materials: ["1 bóng mềm"]
  goal: "Thực hành luân phiên đưa–nhận rất ngắn."
  steps:
    - "Ngồi đối diện, lăn bóng cho bé: 'Lượt con.'"
    - "Khi bóng về: 'Lượt mẹ.'"
    - "Đếm vui '1–2–3 lượt' tối đa 5–8 lượt."
    - "Dừng khi còn vui — kết bằng high-five."
  parent_phrases:
    - "Đến lượt con này."
    - "Cảm ơn con đã chờ."
    - "Chơi vui quá."
  easier: "Mẹ hỗ trợ tay lăn giúp."
  harder: "Thêm bạn/anh chị; hàng chờ 2 người (30m+)."
  safety: "Bóng nhẹ; không ném mạnh vào mặt."
  reviewed_by: []

- id: SOC-04
  title: Chào và tạm biệt cửa
  age_range: [18, 36]
  domains: [SOCIAL_EMOTIONAL, LANGUAGE]
  duration_min: 5
  materials: ["Không"]
  goal: "Tập hành vi giao tiếp chào / tạm biệt trong nếp thật."
  steps:
    - "Trước khi ra/vào cửa: dừng 3 giây."
    - "Mẫu: 'Con chào bà ạ.' Vẫy tay."
    - "Khen nỗ lực (cử chỉ cũng được)."
    - "Không bắt làm lại nhiều lần nếu bé ngại."
  parent_phrases:
    - "Vẫy tay bye bye nào."
    - "Con chào nhẹ là được rồi."
    - "Bà vui lắm."
  easier: "Chỉ vẫy tay, mẹ nói hộ."
  harder: "Thêm 'cảm ơn' khi nhận đồ (24m+)."
  safety: "Không xấu hổ trước người lạ; tôn trọng tính nhút."
  reviewed_by: []

- id: SOC-05
  title: Nghi thức chia tay ngắn
  age_range: [24, 36]
  domains: [SOCIAL_EMOTIONAL]
  duration_min: 5
  materials: ["Đồ chuyển tiếp: khăn/đồ chơi nhỏ"]
  goal: "Giảm lo khi người lớn ra ngoài ngắn; gắn kết tin cậy."
  steps:
    - "Nói rõ: 'Mẹ ra ngoài một lát, mẹ về.'"
    - "Làm cùng 1 nghi thức cố định (ôm + bye + để đồ chuyển tiếp)."
    - "Về đúng lời hứa càng sớm càng tốt lúc tập."
    - "Khi về: chào ấm, không trách khóc."
  parent_phrases:
    - "Mẹ đi và mẹ về."
    - "Con ở với bà an toàn."
    - "Mẹ về rồi, mẹ đây."
  easier: "Chỉ ra phòng bên cạnh vài phút."
  harder: "Kéo dài dần thời gian vắng (theo nhịp bé)."
  safety: "Không 'biến mất' đột ngột để thử; không dùng lời dọa bỏ."
  reviewed_by: []

- id: SOC-06
  title: Nhìn con — khoe việc vừa làm
  age_range: [24, 36]
  domains: [SOCIAL_EMOTIONAL]
  duration_min: 5
  materials: ["Sản phẩm vừa xếp/vẽ"]
  goal: "Thể hiện tự hào lành mạnh; gắn kết qua chú ý thật."
  steps:
    - "Khi bé kéo tay khoe: dừng việc đang làm nếu có thể."
    - "Ngồi thấp bằng mắt bé: 'Con làm gì đây?'"
    - "Khen cụ thể: 'Con xếp cao quá.' (không chỉ 'giỏi' chung chung)."
    - "Hỏi: 'Con muốn làm tiếp không?'"
  parent_phrases:
    - "Mẹ nhìn đây này."
    - "Con cố xếp thật chăm."
    - "Mẹ thích chơi cùng con."
  easier: "Chỉ mỉm cười + mô tả 1 chi tiết."
  harder: "Bé kể 'con làm…' (30m+)."
  safety: "Tránh so sánh anh chị/hàng xóm."
  reviewed_by: []

- id: SOC-07
  title: Tay nhẹ với bạn
  age_range: [24, 36]
  domains: [SOCIAL_EMOTIONAL]
  duration_min: 8
  materials: ["Búp bê/thú bông; kem dưỡng hoặc không"]
  goal: "Thực hành chạm nhẹ; thay thế hành vi mạnh."
  steps:
    - "Mẫu xoa lưng thú: 'Tay nhẹ.'"
    - "Để bé thử; nếu mạnh: giữ tay êm, làm lại mẫu."
    - "Nói: 'Bạn đau nếu tay mạnh. Mình xoa nhẹ.'"
    - "Khen khi nhẹ."
  parent_phrases:
    - "Nhẹ như lông vũ."
    - "Bạn thích lắm."
    - "Con biết giữ tay rồi."
  easier: "Chỉ xoa thú, chưa có trẻ khác."
  harder: "Áp dụng khi chơi cạnh bạn thật có người lớn."
  safety: "Không đánh trả để 'dạy'; không dọa đau đớn."
  reviewed_by: []

- id: SOC-08
  title: Chia sẻ có người lớn cầm
  age_range: [24, 36]
  domains: [SOCIAL_EMOTIONAL]
  duration_min: 8
  materials: ["2 món giống nhau hoặc đồng hồ cát 1 phút"]
  goal: "Làm quen chia sẻ / chờ mà không cướp."
  steps:
    - "Ưu tiên 2 món giống nhau nếu có."
    - "Nếu 1 món: 'Con chơi 1 phút, rồi đến lượt…' (đồng hồ cát)."
    - "Người lớn giữ nhịp; không bắt bé 'phải vui vẻ chia'."
    - "Cảm ơn cả hai khi đổi lượt."
  parent_phrases:
    - "Mỗi bạn một cái nhé."
    - "Sắp đến lượt con."
    - "Cảm ơn con đã chờ."
  easier: "Chỉ chơi song song, chưa đổi đồ."
  harder: "Đổi lượt 2–3 lần (30m+)."
  safety: "Không lấy đồ đang chơi của bé đột ngột để 'dạy chia'."
  reviewed_by: []

- id: SOC-09
  title: Đọc mặt người khác
  age_range: [24, 36]
  domains: [SOCIAL_EMOTIONAL, LANGUAGE]
  duration_min: 7
  materials: ["Sách tranh có mặt người rõ"]
  goal: "Để ý cảm xúc người khác ở mức đơn giản."
  steps:
    - "Chỉ mặt nhân vật: 'Bạn này đang cười — vui.'"
    - "Hỏi: 'Bạn buồn hay vui?'"
    - "Nối với đời thật: 'Lúc nãy em khóc, em buồn.'"
    - "Hỏi: 'Mình có thể làm gì?' (đưa khăn / ngồi cạnh)."
  parent_phrases:
    - "Bạn trông buồn."
    - "Con muốn ngồi cạnh bạn không?"
    - "Con có trái tim ấm."
  easier: "Chỉ phân biệt cười/khóc."
  harder: "Bé chọn cách an ủi (30m+)."
  safety: "Không bắt bé ôm người đang khó chịu nếu bé sợ."
  reviewed_by: []

- id: SOC-10
  title: Chơi giả bộ chăm em
  age_range: [24, 36]
  domains: [SOCIAL_EMOTIONAL, LANGUAGE]
  duration_min: 10
  materials: ["Búp bê; khăn; thìa/cốc đồ chơi"]
  goal: "Thể hiện quan tâm qua trò chơi giả bộ."
  steps:
    - "Mời: 'Em bé đói, mình cho ăn nào.'"
    - "Để bé xúc/cho ngủ/đắp khăn."
    - "Nói lời dịu: 'Em ngủ ngon.'"
    - "Kết: cất nhẹ nhàng 'em ngủ rồi'."
  parent_phrases:
    - "Con chăm em khéo quá."
    - "Em thích con."
    - "Mẹ thấy con rất dịu dàng."
  easier: "1 hành động: đắp khăn."
  harder: "Chuỗi 2–3 hành động chăm sóc."
  safety: "Không dùng trò để đổ lỗi bé thật; tránh nội dung bệnh/sợ."
  reviewed_by: []

- id: SOC-11
  title: Hộp cảm xúc trong ngày
  age_range: [30, 36]
  domains: [SOCIAL_EMOTIONAL, LANGUAGE]
  duration_min: 8
  materials: ["3 thẻ mặt vui/buồn/giận tự vẽ; hộp nhỏ"]
  goal: "Gọi tên cảm xúc cuối ngày không phán xét."
  steps:
    - "Xếp 3 thẻ ra."
    - "Hỏi: 'Hôm nay mặt con giống thẻ nào?'"
    - "Nghe và xác nhận: 'Con buồn — mẹ hiểu.'"
    - "Hỏi 1 việc làm dịu (ôm, hát, nước)."
  parent_phrases:
    - "Mọi cảm xúc đều được."
    - "Cảm ơn con đã nói."
    - "Mai mình chơi gì vui nào?"
  easier: "Chỉ 2 thẻ vui/buồn."
  harder: "Kể 1 lý do ngắn ('vì…')."
  safety: "Không bắt 'phải vui'; không phạt vì chọn thẻ giận."
  reviewed_by: []

- id: SOC-12
  title: Cất đồ cùng nhạc ngắn
  age_range: [24, 36]
  domains: [SOCIAL_EMOTIONAL]
  duration_min: 7
  materials: ["Thùng đồ chơi; bài hát 1–2 phút"]
  goal: "Làm theo nếp đơn giản với người lớn — gắn kết hợp tác."
  steps:
    - "Bật/hát 1 bài cố định 'giờ cất đồ'."
    - "Mẹ cầm 1 món, bé 1 món — cùng bỏ vào thùng."
    - "Khen hợp tác, không soi số lượng."
    - "High-five khi nhạc hết."
  parent_phrases:
    - "Mình cùng nhau nào."
    - "Con giúp mẹ rồi."
    - "Xong rồi, nhẹ phòng quá."
  easier: "Chỉ cất 2 món."
  harder: "Bé chọn 'nhà' cho từng loại đồ (30m+)."
  safety: "Không đe dọa bỏ đồ chơi nếu không cất."
  reviewed_by: []

- id: SOC-13
  title: Bạn ngồi cạnh mình
  age_range: [24, 36]
  domains: [SOCIAL_EMOTIONAL]
  duration_min: 10
  materials: ["2 bộ đồ chơi giống nhau"]
  goal: "Chơi song song thân thiện; giảm tranh giành."
  steps:
    - "Chuẩn bị 2 không gian cạnh nhau."
    - "Nói: 'Bạn chơi đây, con chơi đây.'"
    - "Người lớn ngồi giữa, bình luận vui (không chỉ đạo nhiều)."
    - "Nếu tranh: nhắc 'tay nhẹ' + thêm món giống."
  parent_phrases:
    - "Hai bạn chơi gần nhau vui quá."
    - "Mỗi bạn một bộ nhé."
    - "Con đang xếp đẹp."
  easier: "Chỉ có người lớn, chưa có bạn."
  harder: "Mời trao đổi 1 món có đồng ý hai bên."
  safety: "Giám sát liên tục; tách nhẹ nếu cắn/đánh."
  reviewed_by: []

- id: SOC-14
  title: Cảm ơn thật lòng
  age_range: [24, 36]
  domains: [SOCIAL_EMOTIONAL, LANGUAGE]
  duration_min: 5
  materials: ["Tình huống thật: đưa bánh, mở cửa…"]
  goal: "Nói/ra dấu cảm ơn trong giao tiếp thật."
  steps:
    - "Khi đưa đồ: dừng 1 nhịp, nhìn bé."
    - "Mẫu: 'Cảm ơn mẹ ạ.'"
    - "Chấp nhận gật/vẫy; đáp lại ấm."
    - "Không bắt lặp lại trước khách."
  parent_phrases:
    - "Cảm ơn con."
    - "Con muốn nói cảm ơn không?"
    - "Mẹ thấy con lịch sự."
  easier: "Chỉ cử chỉ."
  harder: "Bé nói với người khác trong nhà."
  safety: "Không bắt ép trước đám đông."
  reviewed_by: []

- id: SOC-15
  title: Khi giận — chọn việc an toàn
  age_range: [24, 36]
  domains: [SOCIAL_EMOTIONAL]
  duration_min: 6
  materials: ["Gối ôm; chỗ ngồi; giấy để vò"]
  goal: "Có lựa chọn hành vi khi giận thay vì làm đau người."
  steps:
    - "Khi bé giận: xác nhận 'Con đang giận'."
    - "Chỉ 2 lựa chọn: 'ôm gối' hoặc 'vò giấy' (có sẵn)."
    - "Ở gần, giọng thấp, không dài dòng."
    - "Khi dịu: 'Lúc giận mình chọn việc an toàn.'"
  parent_phrases:
    - "Giận được. Làm đau người thì không."
    - "Con chọn gối hay giấy?"
    - "Mẹ ở đây."
  easier: "Chỉ ôm gối với mẹ."
  harder: "Bé tự chỉ chỗ 'góc dịu' (30m+)."
  safety: "Không nhốt phòng tối; không dọa ma/cảnh sát."
  reviewed_by: []
```

---

## AESTHETIC

### A.1 Mục tiêu theo nhóm tuổi (nháp)

| Nhóm | Âm nhạc / nhịp | Tạo hình | Xem / cảm nhận |
|---|---|---|---|
| **18–24m** | Nghe hát, nhún/vỗ theo | Tập cầm sáp, nguệch ngoạc | Thích xem tranh màu rõ |
| **24–30m** | Hát theo vài tiếng; vận động đơn giản theo nhạc | Đường nét tự do; vò/xé giấy lớn | Chọn tranh/màu thích |
| **30–36m** | Hát + động tác bài quen | Nguệch ngoạc có ý định gọi tên; xếp/dán đơn giản | Xem tranh và nói thích gì |

### A.2 Checklist mốc

**18–24 tháng**
- [ ] Phản ứng vui khi nghe nhạc/hát (nhún, vỗ)
- [ ] Cầm sáp/bút lớn nguệch trên giấy
- [ ] Xem tranh với người lớn trong thời gian ngắn

**24–30 tháng**
- [ ] Hát theo hoặc bập bẹ theo giai điệu quen
- [ ] Vẽ đường nét / điểm; thử xé/vò giấy
- [ ] Chỉ tranh hoặc màu mình thích

**30–36 tháng**
- [ ] Hát và vận động theo 1 bài ngắn quen
- [ ] Tô/di màu trong phạm vi rộng (không cần trong hình)
- [ ] Gọi tên bức vẽ theo ý (“con vẽ mưa”)

### A.3 Vật liệu an toàn gợi ý (nhà VN)

| Nên dùng | Lưu ý |
|---|---|
| Giấy A4/báo cũ, băng keo giấy | Giám sát xé — không cho vào miệng nhiều |
| Sáp màu kích thước lớn, không độc (nhãn trẻ em) | Kiểm tra nuốt sáp; cần có người lớn |
| Bút lông màu nước nhạt, khay nhỏ | Áo cũ; trải nilon/bàn ăn |
| Đất nặn tự làm (bột mì + muối + dầu ăn) — nếu gia đình quen | Không nuốt; không dùng bột có phụ gia lạ chưa rõ |
| Nồi/chậu/thìa gỗ làm nhạc cụ | Tránh nồi nóng/sắt nặng |
| Khăn, gối, chai nhựa sạch có nắp chặt (lục lạc) | Nắp phải chắc; không thủy tinh |

**Tránh:** sơn không rõ nguồn, hồ dán công nghiệp mạnh, hạt nhỏ, kéo nhọn, nến/đèn mở, đồ tái chế bẩn/sắc.

### A.4 ~11 hoạt động nháp (YAML)

```yaml
- id: AES-01
  title: Nguệch ngoạc sáp lớn
  age_range: [18, 30]
  domains: [AESTHETIC, PHYSICAL]
  duration_min: 10
  materials: ["Giấy A4 dán bàn; 2–3 sáp màu lớn; áo cũ"]
  goal: "Cảm nhận để lại dấu vết; luyện cầm nắm."
  steps:
    - "Dán giấy; đặt sáp trong tầm với."
    - "Mẹ nguệch mẫu 1 đường rồi để bé."
    - "Nói màu: 'Đỏ này!' Không sửa hình."
    - "Khoe tranh lên tường thấp 1 ngày."
  parent_phrases:
    - "Con vẽ mạnh quá."
    - "Đường này cong đẹp."
    - "Mẹ treo đây cho cả nhà xem."
  easier: "Mẹ cầm tay cùng vẽ vài nét."
  harder: "Chọn màu theo lời (24m+)."
  safety: "Sáp không độc; không để bé cắn nhiều; giám sát liên tục."
  reviewed_by: []

- id: AES-02
  title: Ban nhạc xoong nồi
  age_range: [18, 36]
  domains: [AESTHETIC]
  duration_min: 8
  materials: ["1 nồi/chậu nhựa; 1 thìa gỗ"]
  goal: "Khám phá âm thanh và nhịp đơn giản."
  steps:
    - "Gõ chậm: 'Bốp… bốp…'"
    - "Để bé gõ; bắt chước nhịp bé."
    - "Đổi to/nhỏ: 'Nhẹ nào… mạnh nào…' (vừa phải)."
    - "Kết bằng cúi chào vui."
  parent_phrases:
    - "Nghe hay quá!"
    - "Con làm nhạc trưởng."
    - "Một bài nữa rồi nghỉ."
  easier: "Chỉ vỗ tay theo nhạc điện thoại nhỏ."
  harder: "Gõ theo bài hát đếm 1-2-3 (30m+)."
  safety: "Không nồi kim loại cạnh tai; không đồ nóng/sắt nặng."
  reviewed_by: []

- id: AES-03
  title: Hát ru / hát chơi một bài
  age_range: [18, 36]
  domains: [AESTHETIC, SOCIAL_EMOTIONAL]
  duration_min: 6
  materials: ["Không; có thể thêm khăn đung đưa"]
  goal: "Nghe và hát theo; gắn kết qua giọng người lớn."
  steps:
    - "Chọn 1 bài cố định mỗi tối/điểm ngày."
    - "Hát nhìn mắt bé; nhún nhẹ."
    - "Để trống 1 câu cho bé bập bẹ."
    - "Kết ôm hoặc vỗ lưng."
  parent_phrases:
    - "Mẹ hát cho con."
    - "Con hát cùng mẹ nghe này."
    - "Giọng con ấm quá."
  easier: "Chỉ nghe, không bắt hát."
  harder: "Thêm 2 động tác tay theo lời."
  safety: "Âm lượng êm; không tai nghe lớn cho bé."
  reviewed_by: []

- id: AES-04
  title: Vò–xé giấy mây mưa
  age_range: [24, 36]
  domains: [AESTHETIC, PHYSICAL]
  duration_min: 10
  materials: ["Giấy màu/báo; khay; keo dán hồ bột hoặc băng keo giấy"]
  goal: "Khám phá chất liệu; tạo hình đơn giản."
  steps:
    - "Cùng vò giấy thành 'mây'."
    - "Xé mảnh nhỏ làm 'mưa' (mẹ hỗ trợ)."
    - "Dán lên tờ nền bằng băng keo giấy."
    - "Đặt tên tranh cùng bé."
  parent_phrases:
    - "Mây mềm quá."
    - "Mưa rơi tí tách."
    - "Con làm tranh đẹp."
  easier: "Chỉ vò, không xé."
  harder: "Thêm vẽ tia mưa bằng sáp (30m+)."
  safety: "Không để bé ăn keo; cắt sẵn nếu kéo nguy hiểm; giám sát."
  reviewed_by: []

- id: AES-05
  title: Nhún nhảy theo nhạc
  age_range: [18, 36]
  domains: [AESTHETIC, PHYSICAL]
  duration_min: 7
  materials: ["1 bài nhạc vui vừa đủ nhỏ"]
  goal: "Vận động theo nhịp; biểu lộ vui."
  steps:
    - "Bật nhạc; mẹ nhún mẫu."
    - "Vỗ tay/dậm chân nhẹ theo phách."
    - "Đổi chậm–nhanh 1 lần."
    - "Tắt nhạc, thở cùng nhau."
  parent_phrases:
    - "Nhún nào!"
    - "Chân con đánh nhịp hay."
    - "Hết bài, mình cười nào."
  easier: "Bế nhún nếu bé chưa vững."
  harder: "Đứng dừng khi nhạc 'ngắt' (30m+)."
  safety: "Sàn không trơn; không xoay mạnh cổ; âm lượng bảo vệ thính giác."
  reviewed_by: []

- id: AES-06
  title: Đất nặn bột mì (tùy chọn)
  age_range: [24, 36]
  domains: [AESTHETIC, PHYSICAL]
  duration_min: 12
  materials: ["Bột mì + muối + ít dầu + nước (nặn trước); khay"]
  goal: "Nặn ấn, dẹp, cuốn — cảm nhận thẩm mỹ xúc giác."
  steps:
    - "Đưa một viên; mẹ ấn mẫu."
    - "Để bé ói/dẹp/cuốn tự do."
    - "Gọi tên hình nếu bé nói ('bánh', 'rắn')."
    - "Cất hộp kín sau chơi; rửa tay."
  parent_phrases:
    - "Mềm quá!"
    - "Con ấn mạnh này."
    - "Trông giống bánh bông lan."
  easier: "Chỉ ấn tay."
  harder: "Ép khuôn cốc nhỏ (30m+)."
  safety: "Không nuốt; không dùng nếu gia đình không rõ công thức an toàn; giám sát tuyệt đối."
  reviewed_by: []

- id: AES-07
  title: Xem tranh và chọn thích
  age_range: [18, 36]
  domains: [AESTHETIC, LANGUAGE]
  duration_min: 6
  materials: ["2–3 tranh/lịch ảnh màu"]
  goal: "Quan sát và bày tỏ sở thích thẩm mỹ."
  steps:
    - "Đặt 2 tranh trước mặt."
    - "Hỏi: 'Con thích tranh nào?'"
    - "Nói về màu/vật: 'Mẹ thích màu xanh.'"
    - "Treo tranh bé chọn ở tầm mắt bé."
  parent_phrases:
    - "Con chọn tranh này à?"
    - "Màu vàng sáng quá."
    - "Con có mắt nhìn đẹp."
  easier: "Chỉ nhìn 1 tranh, mẹ mô tả."
  harder: "Nói vì sao thích (30m+)."
  safety: "Tranh không đinh nhọn; tránh hình ảnh đáng sợ."
  reviewed_by: []

- id: AES-08
  title: Lục lạc chai nhựa
  age_range: [18, 30]
  domains: [AESTHETIC]
  duration_min: 8
  materials: ["Chai nhựa sạch nắp chặt; ít gạo/đậu lớn (người lớn đổ)"]
  goal: "Tạo nhạc cụ đơn giản; chơi nhịp."
  steps:
    - "Người lớn đổ nguyên liệu và **dán/siết nắp chắc** trước khi đưa."
    - "Lắc chậm theo bài hát."
    - "Đổi tay; lắc to/nhỏ."
    - "Cất khỏi tầm với khi xong."
  parent_phrases:
    - "Lắc lắc!"
    - "Nghe như mưa."
    - "Con giữ chắc nào."
  easier: "Dùng lục lạc mua sẵn."
  harder: "Lắc theo câu thơ (24m+)."
  safety: "Nắp phải không mở được bởi bé; không dùng hạt nhỏ với trẻ hay bỏ miệng; kiểm tra nứt chai."
  reviewed_by: []

- id: AES-09
  title: Ngón tay màu (tem màu)
  age_range: [24, 36]
  domains: [AESTHETIC]
  duration_min: 10
  materials: ["Màu finger-paint trẻ em hoặc bột màu thực phẩm pha đặc; giấy; khăn ướt"]
  goal: "In dấu ngón tay; khám phá màu."
  steps:
    - "Chấm 1 màu lên đĩa; in vài dấu mẫu."
    - "Để bé in tự do."
    - "Nói tên màu; không ép hình."
    - "Rửa tay vui vẻ ngay sau."
  parent_phrases:
    - "Chấm chấm!"
    - "Đỏ dính tay này."
    - "Tranh của con đây."
  easier: "Chỉ 1 màu."
  harder: "Trộn 2 màu xem đổi (30m+)."
  safety: "Chỉ màu ghi dùng được cho trẻ; không để vào mắt/miệng; trải bàn."
  reviewed_by: []

- id: AES-10
  title: Điệu bộ theo lời bài hát
  age_range: [24, 36]
  domains: [AESTHETIC, LANGUAGE]
  duration_min: 8
  materials: ["1 bài có động tác đơn (vd. vỗ tay, giơ cao)"]
  goal: "Hát + vận động khớp lời — cảm nhận thẩm mỹ vận động."
  steps:
    - "Hát chậm, làm 1 động tác/ câu."
    - "Lặp 2 lần; để bé dẫn động tác lần 3."
    - "Cười khi lệch nhịp — không sửa cứng."
    - "Kết tạo hình 'tượng' đứng yên 3 giây."
  parent_phrases:
    - "Giơ cao nào!"
    - "Con nhảy đúng rồi."
    - "Nhạc trưởng ơi, thêm một lần!"
  easier: "Ngồi vỗ tay."
  harder: "2 động tác xen kẽ (30m+)."
  safety: "Không nhào lộn; khoảng trống đủ."
  reviewed_by: []

- id: AES-11
  title: Xếp hình màu giấy
  age_range: [30, 36]
  domains: [AESTHETIC, COGNITIVE]
  duration_min: 10
  materials: ["Mảnh giấy màu cắt sẵn hình lớn (vuông/tròn); nền A4; keo/băng keo"]
  goal: "Sắp xếp tạo bố cục đơn giản theo ý thích."
  steps:
    - "Đưa 4–6 mảnh lớn."
    - "Mời xếp lên nền 'như thế nào cũng được'."
    - "Dán giúp khi bé ưng ý."
    - "Đặt tên: 'Nhà của con' / 'Vườn hoa' tùy bé."
  parent_phrases:
    - "Con đặt chỗ nào cũng đẹp."
    - "Mảnh tròn ở đây sáng quá."
    - "Mẹ thích bố cục của con."
  easier: "Chỉ xếp không dán."
  harder: "Thêm 1 chi tiết vẽ sau khi dán."
  safety: "Mảnh đủ lớn không nuốt; keo an toàn trẻ em."
  reviewed_by: []
```

---

## Nguồn / tham khảo

| Loại | Chi tiết |
|---|---|
| **Chính thức (VN)** | Chương trình GDMN — Văn bản hợp nhất **01/VBHN-BGDĐT** (Thông tư ban hành CT GDMN): lĩnh vực ngôn ngữ; tình cảm–KNXH; thẩm mỹ (nhà trẻ 12–24 / 24–36 tháng). |
| **Tham khảo quốc tế** | CDC *Learn the Signs. Act Early* — mốc 18 / 24 / 30 / 36 tháng (ngôn ngữ & xã hội–cảm xúc). Dùng để đối chiếu quan sát, **không** thay chuẩn VN, **không** chẩn đoán. |
| **Nội bộ dự án** | `docs/SPEC_MVP.md` (nguyên tắc không phán xét, offline, ≤15 phút); `plans/PLAN_PHAN_TICH.md` (6 mã lĩnh vực); skill đọc-only `giao-duc-mam-non`, `tam-ly-tre-em` tại `ideas/giao-duc-ky-nang-tre/skills`. |
| **Giả định chưa xác minh** | (1) Phân bổ ~15/15/11 hoạt động đủ cover MVP 90 bài khi ghép agent B/D. (2) Vật liệu “nhà VN” phổ biến đủ cho pilot đô thị; nông thôn có thể khác. (3) Đồng dao/thơ cụ thể mang tính ví dụ — cần chọn bản quyền/miền phù hợp khi xuất bản. (4) Chưa GVMN / tâm lý / phụ huynh duyệt; `reviewed_by` trống. |

---

## Ghi chú tích hợp cho parent / agent E–F

- **ID đã dùng:** `LANG-01…15`, `SOC-01…15`, `AES-01…11` (tổng **41** hoạt động nháp).
- **Domain phụ:** một số hoạt động gắn `PHYSICAL` / `COGNITIVE` — khi seed Room nên cho phép multi-domain như SPEC.
- **Phần thưởng UX:** copy khen cụ thể + gợi ý tương tác thật; **không** coin/streak/leaderboard.
- **Tone UI:** câu trong `parent_phrases` có thể tái sử dụng làm chip gợi ý trên màn Chi tiết hoạt động (S0x — do agent E quyết).
