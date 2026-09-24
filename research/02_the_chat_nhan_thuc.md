# Gấu Con MVP — Thể chất + Nhận thức (18–36 tháng)

> Subagent B · File duy nhất được ghi · Nháp nội dung — **chưa chuyên gia duyệt** · Không chẩn đoán y tế  
> Thử nghiệm phụ `ideas/BeGau` · **Không đổi hướng active**

---

## Disclaimer (bắt buộc hiển thị / gắn UI checklist)

- Checklist mốc chỉ hỗ trợ **quan sát – ghi nhận** theo nhịp từng bé; **không** chẩn đoán, không xếp hạng “chậm/nhanh”, không thay khám bác sĩ.
- Tone: “mỗi bé có nhịp riêng”. Nếu phụ huynh lo lắng, mất kỹ năng đã có, hoặc nhiều mục “Chưa” quanh tuổi checklist CDC tương ứng → gợi ý nhẹ: **nên hỏi bác sĩ / cơ sở y tế** (không tự kết luận).
- WHO Motor Development Study chỉ có **6 mốc vận động thô đến ~24 tháng** (đi một mình kết thúc cửa sổ 99th ~17,6 tháng). Với 18–36 tháng, mốc vận động/nhận thức chi tiết chủ yếu tham chiếu **CDC Learn the Signs. Act Early.** (≥75% trẻ đạt theo tuổi checklist).
- Hoạt động dưới đây = **nháp sư phạm**; mọi mục có `reviewed_by: []`. Không xuất bản seed trước khi có duyệt chuyên gia.

---

## Nguồn & phân loại

| Loại | Nguồn | Dùng cho |
|---|---|---|
| Chính thức (quốc tế) | CDC *Learn the Signs. Act Early.* — checklist 18 tháng, 2 tuổi, 30 tháng, 3 tuổi (Milestone Moments / trang milestones CDC) | Checklist PHYSICAL + COGNITIVE theo tuổi |
| Chính thức (quốc tế) | WHO Multicentre Growth Reference Study — Motor Development Study (Acta Paediatrica Suppl. 2006; bảng percentiles WHO) | Bối cảnh: cửa sổ đi một mình; **không** có chạy/nhảy/xếp khối trong bộ 6 mốc WHO |
| Đặc tả sản phẩm | `docs/SPEC_MVP.md` v1.0; `plans/PLAN_PHAN_TICH.md` | Mã `PHYSICAL` / `COGNITIVE`; band tuổi; khuôn YAML |
| Giả định chưa xác minh | Mục tiêu band diễn giải theo SPEC + CDC; chưa đối chiếu từng dòng Chương trình GDMN Bộ GD&ĐT bản chính thức trong phiên này | Mục tiêu phát triển theo band |
| Tham khảo (không dùng làm checklist chính) | Tài liệu lâm sàng/đại học về toddler motor (vd. tóm tắt phát triển toddler) | Chỉ để hiểu tiến trình; **không** trích làm mốc app |

**Giả định ghi rõ:**
1. Band sản phẩm `18–24m` / `24–30m` / `30–36m` map gần với CDC **18m → 2y → 30m → 3y** (CDC không có checklist riêng “24–30m”; dùng 2y rồi 30m).
2. Đồ dùng ưu tiên nhà Việt: gối, ghế thấp, cầu thang có tay vịn, bóng mềm/vải, hộp sữa/hộp bánh trống, muỗng nhựa, cốc, khăn, sách tranh cứng, bút sáp lớn, giấy, nắp chai lớn, ống hút, đậu/hạt to (chỉ khi giám sát chặt), quần áo rộng.
3. An toàn chuyên sâu (điện, nước, đường…) thuộc *Bé Gấu An Toàn* — ở đây chỉ `safety` **gắn hoạt động**.

---

# ## PHYSICAL

## 1. Mục tiêu phát triển theo band

### 18–24 tháng — vận động thô + tinh

| Nhánh | Mục tiêu định hướng (giả định sản phẩm, bám CDC 18m → tiến tới 2y) |
|---|---|
| Thô | Đi vững không vịn (nền WHO: đi một mình thường đạt trước ~18m ở hầu hết trẻ khỏe); leo lên/xuống ghế sofa/ghế thấp; bắt đầu chạy ngắn / đá bóng nhẹ về cuối band |
| Tinh | Nguệch ngoạc; xúc/ăn bằng tay; thử muỗng; cầm–thả đồ chơi có chủ đích |
| Trải nghiệm | Không gian sàn sạch; chơi đứng–ngồi–đi luân phiên; người lớn ở gần khi leo |

### 24–30 tháng

| Nhánh | Mục tiêu định hướng (CDC 2y + tiến tới 30m) |
|---|---|
| Thô | Chạy; đá bóng; đi vài bậc thang (không trèo bốn chân) có/không người giúp; chuẩn bị nhảy hai chân |
| Tinh | Ăn bằng muỗng thành thạo hơn; xoay/vặn nắp–núm đơn giản; lật trang sách (cuối band: từng trang) |
| Trải nghiệm | Sân/hành lang có giám sát; hoạt động 5–10 phút; nghỉ khi mệt |

### 30–36 tháng

| Nhánh | Mục tiêu định hướng (CDC 30m → 3y) |
|---|---|
| Thô | Nhảy hai chân rời mặt đất; thăng bằng ngắn; mặc/cởi một số đồ rộng |
| Tinh | Xâu hạt/macaroni lớn; dùng nĩa; kiểm soát cầm nắm tinh hơn (chuẩn bị cho vẽ vòng tròn — dual với nhận thức/thẩm mỹ) |
| Trải nghiệm | Chuỗi vận động 2 bước (đi → nhảy / với → đặt); vẫn giám sát té–hóc |

*SPEC ví dụ 24–36m: nhảy hai chân, xếp chồng ~6 khối — xếp khối đưa vào dual PHYSICAL+COGNITIVE bên dưới.*

## 2. Checklist mốc (3 trạng thái)

**Trạng thái UI:** `DONE` = Đã làm được · `PRACTICING` = Đang tập · `NOT_YET` = Chưa  
**Không** tính điểm so sánh với trẻ khác.

### Map tuổi checklist → band app

| Band app | Checklist CDC tham chiếu chính |
|---|---|
| 18–24m | CDC **18 months** (+ ghi nhận sớm các mục CDC **2 years** nếu bé đã làm được) |
| 24–30m | CDC **2 years** (+ ghi nhận sớm mục CDC **30 months**) |
| 30–36m | CDC **30 months** → CDC **3 years** |

### A. Vận động / thể chất — mục CDC (dịch ý, giữ nghĩa gốc)

**CDC 18 months — Movement/Physical**

| id | Mốc (VI) | Nguồn |
|---|---|---|
| phy_cdc_18_walk | Đi không cần vịn người/đồ vật | CDC 18m |
| phy_cdc_18_scribble | Nguệch ngoạc | CDC 18m |
| phy_cdc_18_cup | Uống cốc không nắp (có thể đổ) | CDC 18m |
| phy_cdc_18_finger_feed | Tự ăn bằng tay | CDC 18m |
| phy_cdc_18_spoon_try | Thử dùng muỗng | CDC 18m |
| phy_cdc_18_climb_furniture | Leo lên/xuống ghế/sofa không cần giúp | CDC 18m |

**CDC 2 years — Movement/Physical**

| id | Mốc (VI) | Nguồn |
|---|---|---|
| phy_cdc_24_kick | Đá bóng | CDC 2y |
| phy_cdc_24_run | Chạy | CDC 2y |
| phy_cdc_24_stairs | Đi (không trèo) vài bậc thang có/không giúp | CDC 2y |
| phy_cdc_24_spoon_eat | Ăn bằng muỗng | CDC 2y |

**CDC 30 months — Movement/Physical**

| id | Mốc (VI) | Nguồn |
|---|---|---|
| phy_cdc_30_twist | Dùng tay vặn/xoay (núm cửa, mở nắp) | CDC 30m |
| phy_cdc_30_undress | Cởi một số đồ (quần rộng, áo khoác mở) | CDC 30m |
| phy_cdc_30_jump | Nhảy hai chân rời mặt đất | CDC 30m |
| phy_cdc_30_pages | Lật sách từng trang khi được đọc cùng | CDC 30m |

**CDC 3 years — Movement/Physical**

| id | Mốc (VI) | Nguồn |
|---|---|---|
| phy_cdc_36_string | Xâu đồ lớn (hạt lớn / macaroni) | CDC 3y |
| phy_cdc_36_dress | Mặc một số đồ (quần rộng, áo khoác) | CDC 3y |
| phy_cdc_36_fork | Dùng nĩa | CDC 3y |

### B. Ghi chú WHO (bối cảnh, không nhân bản checklist toddler)

- Đi một mình: cửa sổ WHO ~**8,2–17,6 tháng** (1st–99th); trung vị ~12 tháng. Nguồn: WHO Motor Development Study / bảng percentiles WHO.
- App MVP: nếu bé **18m+** vẫn chưa đi độc lập → không gắn nhãn “chậm”; gợi ý hỏi bác sĩ + giữ mục `phy_cdc_18_walk` ở `NOT_YET`/`PRACTICING`.

### C. Mục bổ sung giả định (không gắn nhãn CDC/WHO)

Chỉ gợi ý theo dõi nội bộ, tách UI “theo dõi thêm” nếu product cần:

| id | Mốc giả định | Lý do |
|---|---|---|
| phy_hyp_stack_4 | Xếp ≥4 khối | Thường gặp trong tài liệu toddler; **chưa** xác minh chuẩn CDC LTSAE hiện hành |
| phy_hyp_stack_6 | Xếp ~6 khối | Khớp ví dụ SPEC; giả định sản phẩm |

---

## 3. Hoạt động nháp PHYSICAL (~15)

```yaml
# --- PHYSICAL draft activities ---
# reviewed_by: [] trên mọi mục · duration phút · age_* tháng

- id: phy_01_di_theo_duong_goi
  title: Đi theo đường gối
  age_min: 18
  age_max: 24
  domains: [PHYSICAL]
  duration: 8
  materials: ["2–4 cái gối hoặc đệm mỏng xếp thành đường"]
  goal: "Luyện đi vững, đổi hướng nhẹ trên bề mặt nhà."
  steps:
    - "Xếp gối thành đường thẳng ngắn trên sàn sạch."
    - "Cầm tay bé bước chậm từng gối; buông dần khi bé vững."
    - "Vỗ tay khi bé đi hết đường; làm lại 2–3 lượt rồi nghỉ."
  parent_phrases:
    - "Con bước từng bước nhé."
    - "Bố/mẹ ở đây, con cứ đi."
  easier: "Chỉ 1–2 gối; luôn nắm tay."
  harder: "Đường hơi cong; để bé tự đi, người lớn đi cạnh."
  safety: "Sàn khô, không dây điện lộ; không để bé leo cao gối chồng."
  reviewed_by: []

- id: phy_02_leo_ghe_thap
  title: Lên xuống ghế thấp
  age_min: 18
  age_max: 26
  domains: [PHYSICAL]
  duration: 7
  materials: ["1 ghế thấp/ghế nhựa vững, gần tường"]
  goal: "Luyện leo lên/xuống có kiểm soát (bám CDC leo ghế 18m)."
  steps:
    - "Đặt ghế thấp sát tường hoặc chỗ vững."
    - "Hướng dẫn bé đặt tay–chân lần lượt lên ghế rồi ngồi."
    - "Tập xuống: chân trước, người lớn giữ hông nếu cần."
  parent_phrases:
    - "Tay con đặt đây, chân kia."
    - "Xuống chậm thôi."
  easier: "Người lớn nâng nhẹ hông; chỉ lên, chưa xuống."
  harder: "Tự lên xuống 2–3 lần; thêm ngồi vững 5 giây."
  safety: "Không dùng ghế xoay/ghế cao không ổn định; luôn ở trong tầm với."
  reviewed_by: []

- id: phy_03_nguech_sap_lon
  title: Nguệch ngoạc sáp lớn
  age_min: 18
  age_max: 28
  domains: [PHYSICAL, AESTHETIC]
  duration: 10
  materials: ["Giấy A4/báo cũ", "1–2 bút sáp/bút lông to"]
  goal: "Cầm–kéo trên mặt phẳng (CDC scribble 18m)."
  steps:
    - "Dán hoặc kê giấy trên bàn thấp/sàn."
    - "Đưa sáp lớn; để bé tự kéo nguệch."
    - "Nói ngắn về đường bé vẽ; dừng khi bé chán."
  parent_phrases:
    - "Con kéo nét này đẹp quá."
    - "Sáp ở trên giấy nhé."
  easier: "Người lớn giữ tay bé vẽ 1–2 nét rồi buông."
  harder: "Đổi màu; vẽ đứng tại tường có giấy dán (giám sát)."
  safety: "Chọn đồ không độc hại ghi trên bao bì; không để bé bỏ sáp vào miệng; giám sát liên tục."
  reviewed_by: []

- id: phy_04_an_muong_tap
  title: Tập xúc muỗng
  age_min: 18
  age_max: 30
  domains: [PHYSICAL, SELF_CARE]
  duration: 10
  materials: ["Muỗng nhựa", "Cháo/sữa chua/cơm mềm trong bát"]
  goal: "Thử → dùng muỗng (CDC 18m try spoon → 2y eats with spoon)."
  steps:
    - "Cho bé cầm muỗng; người lớn cầm tay hướng 1–2 thìa đầu."
    - "Để bé tự xúc; chấp nhận đổ."
    - "Khen nỗ lực, không ép hết suất."
  parent_phrases:
    - "Con xúc một thìa nào."
    - "Đổ cũng không sao, mình lau rồi làm tiếp."
  easier: "Thức ăn dính muỗng dễ (sữa chua); người lớn xúc phần lớn."
  harder: "Bé tự xúc phần lớn bữa phụ."
  safety: "Ngồi vững; miếng nhỏ; không chạy khi miệng còn thức ăn (chống hóc)."
  reviewed_by: []

- id: phy_05_da_bong_mem
  title: Đá bóng mềm
  age_min: 22
  age_max: 30
  domains: [PHYSICAL]
  duration: 8
  materials: ["Bóng vải/bóng cao su mềm size vừa"]
  goal: "Đá bóng có chủ đích (CDC 2y)."
  steps:
    - "Đặt bóng trước chân bé trên sàn trống."
    - "Mẫu đá nhẹ; mời bé đá về phía người lớn."
    - "Lăn bóng lại; chơi 5–8 lượt."
  parent_phrases:
    - "Chân con đá bóng đi!"
    - "Bóng bay về mẹ/bố này."
  easier: "Người lớn giữ tay bé đứng vững rồi đá."
  harder: "Đá vào hộp/cửa mở làm “khung thành”."
  safety: "Không bóng nhỏ nuốt được; sân trống không góc nhọn; không đá gần cầu thang."
  reviewed_by: []

- id: phy_06_chay_san_ngan
  title: Chạy đoạn ngắn trong nhà
  age_min: 24
  age_max: 32
  domains: [PHYSICAL]
  duration: 6
  materials: ["Hành lang/phòng trống ~3–5 bước"]
  goal: "Chạy ngắn có dừng (CDC 2y run)."
  steps:
    - "Chỉ điểm bắt đầu và đích (gối/ghế)."
    - "Đếm “1-2-3” rồi chạy cùng bé."
    - "Ôm/vỗ tay ở đích; nghỉ giữa các lượt."
  parent_phrases:
    - "Sẵn sàng… chạy!"
    - "Dừng ở gối này nhé."
  easier: "Đi nhanh thay vì chạy."
  harder: "Đổi hướng nhẹ hoặc mang bóng mềm."
  safety: "Thu dọn đồ dưới chân; không chạy bếp/nhà vệ sinh ướt; giám sát té."
  reviewed_by: []

- id: phy_07_bac_thang_tay_vin
  title: Đi bậc thang có tay vịn
  age_min: 24
  age_max: 36
  domains: [PHYSICAL]
  duration: 7
  materials: ["Cầu thang nhà có tay vịn", "người lớn sát bên"]
  goal: "Đi vài bậc (không trèo bốn chân) — CDC 2y."
  steps:
    - "Bé đứng bậc dưới, một tay vịn."
    - "Lên 2–4 bậc chậm; người lớn đi lệch một bậc."
    - "Xuống cùng cách; dừng nếu bé sợ."
  parent_phrases:
    - "Tay con nắm chặt."
    - "Một bậc nữa thôi."
  easier: "Chỉ lên; người lớn giữ hai tay."
  harder: "Thêm bậc; giảm cầm tay còn đi cạnh."
  safety: "Luôn có người lớn; cửa chặn cầu thang khi không chơi; không chạy trên cầu thang. Không thay module an toàn chuyên sâu."
  reviewed_by: []

- id: phy_08_nhay_hai_chan
  title: Nhảy hai chân tại chỗ
  age_min: 28
  age_max: 36
  domains: [PHYSICAL]
  duration: 6
  materials: ["Sàn phẳng", "vòng dây/khăn làm “vũng” ảo (tuỳ chọn)"]
  goal: "Nhảy hai chân rời đất (CDC 30m)."
  steps:
    - "Mẫu nhảy nhẹ tại chỗ."
    - "Cầm hai tay bé nhảy cùng 3–5 cái."
    - "Để bé tự nhảy vào “vũng” khăn."
  parent_phrases:
    - "Nhún… nhảy!"
    - "Hai chân cùng lúc."
  easier: "Chỉ nhún gối, chưa rời đất."
  harder: "Nhảy khỏi bậc thấp 1 cấp (giám sát sát)."
  safety: "Không nhảy giường/sofa cao; bề mặt không trơn."
  reviewed_by: []

- id: phy_09_van_nap_hop
  title: Vặn mở nắp hộp
  age_min: 28
  age_max: 36
  domains: [PHYSICAL, COGNITIVE]
  duration: 8
  materials: ["Hộp nhựa/hũ có nắp vặn lớn", "đồ chơi nhỏ bên trong"]
  goal: "Xoay–vặn bằng tay (CDC 30m twist)."
  steps:
    - "Cho bé thấy đồ trong hộp; đóng nắp lỏng."
    - "Hướng dẫn xoay tay; để bé thử."
    - "Khi mở được, chơi ngắn rồi cất."
  parent_phrases:
    - "Con xoay này."
    - "Mở ra có gì thế?"
  easier: "Nắp chỉ cần ấn/kéo, chưa vặn."
  harder: "Nắp chặt hơn một chút; hộp thứ hai."
  safety: "Không hạt nhỏ/pin; nắp đủ lớn không nuốt được."
  reviewed_by: []

- id: phy_10_lat_sach_tung_trang
  title: Lật sách từng trang
  age_min: 28
  age_max: 36
  domains: [PHYSICAL, LANGUAGE]
  duration: 8
  materials: ["Sách tranh bìa cứng trang dày"]
  goal: "Lật từng trang (CDC 30m)."
  steps:
    - "Ngồi cạnh bé, mở sách."
    - "Để bé lật; nếu lật nhiều trang, nhẹ nhàng tách lại một trang."
    - "Chỉ 1–2 hình mỗi trang; đọc ngắn."
  parent_phrases:
    - "Một trang thôi nhé."
    - "Con lật trang tiếp theo nào."
  easier: "Sách vài trang rất dày; người lớn giữ mép trang."
  harder: "Bé tự lật xuyên suốt 1 cuốn ngắn."
  safety: "Không sách giấy mỏng dễ xé nuốt; giám sát."
  reviewed_by: []

- id: phy_11_xau_nui_ong
  title: Xâu nui/ống lớn
  age_min: 30
  age_max: 36
  domains: [PHYSICAL]
  duration: 10
  materials: ["Ống hút cứng/cọng dây chắc", "nui ống lớn hoặc hạt xâu to (đường kính lớn)"]
  goal: "Xâu đồ lớn (CDC 3y string)."
  steps:
    - "Luồn sẵn 1 hạt mẫu."
    - "Đưa dây/ống cho bé xâu thêm 3–5 hạt."
    - "Đếm to từng hạt; dừng khi bé mệt."
  parent_phrases:
    - "Lỗ này, luồn vào."
    - "Thêm một cái nữa."
  easier: "Vòng lớn xâu vào ống giấy vệ sinh."
  harder: "Xâu 8–10 hạt; làm vòng đeo tay giả."
  safety: "Chỉ dùng hạt/nui **to**; giám sát chống hóc 100% thời gian; cất ngay sau chơi."
  reviewed_by: []

- id: phy_12_mac_quan_rong
  title: Tập mặc quần rộng
  age_min: 30
  age_max: 36
  domains: [PHYSICAL, SELF_CARE]
  duration: 8
  materials: ["Quần short/jogger rộng", "ghế thấp để ngồi"]
  goal: "Mặc một số đồ (CDC 3y); cởi đơn giản (CDC 30m)."
  steps:
    - "Bé ngồi; đặt sẵn ống quần."
    - "Hướng dẫn chân lần lượt; kéo nhẹ phần đai."
    - "Thử cởi: kéo ống xuống."
  parent_phrases:
    - "Chân này vào đây."
    - "Con kéo lên nào."
  easier: "Chỉ luồn một chân; người lớn làm phần còn lại."
  harder: "Tự mặc gần xong; thêm áo khoác mở khóa."
  safety: "Không giật mạnh; tránh dây rút dài quanh cổ."
  reviewed_by: []

- id: phy_13_xep_khoi_thap
  title: Xếp tháp khối/hộp
  age_min: 20
  age_max: 36
  domains: [PHYSICAL, COGNITIVE]
  duration: 8
  materials: ["4–8 khối gỗ/hộp sữa trống cùng cỡ"]
  goal: "Chồng khối (tinh + quan hệ không gian); mục tiêu sản phẩm ~4–6 khối."
  steps:
    - "Đặt 1 khối làm đế."
    - "Mời bé chồng thêm từng cái."
    - "Đếm; cho phép đổ tháp và xếp lại."
  parent_phrases:
    - "Để lên trên nào."
    - "Cao quá, đổ mất thôi — vui không?"
  easier: "Chồng 2–3 khối lớn."
  harder: "Thử 6+ khối hoặc hai tháp cạnh nhau."
  safety: "Khối không nhỏ nuốt được; không ném hướng mặt."
  reviewed_by: []

- id: phy_14_chuyen_nuoc_coc
  title: Chuyển nước/cơm muỗng giữa hai cốc
  age_min: 24
  age_max: 36
  domains: [PHYSICAL]
  duration: 8
  materials: ["2 cốc nhựa", "muỗng", "nước ít hoặc gạo/đậu to (không phải hạt tiêu)"]
  goal: "Điều khiển muỗng–cổ tay; đổ có kiểm soát."
  steps:
    - "Cho ít nước/gạo vào cốc A."
    - "Bé múc chuyển sang cốc B trên khay/chậu."
    - "Lau tay; khen cố gắng."
  parent_phrases:
    - "Từ từ, múc nhẹ."
    - "Đổ vào cốc kia."
  easier: "Dùng tay chuyển bóng cotton thay muỗng."
  harder: "Nước nhiều hơn một chút; đích hẹp hơn."
  safety: "Ngồi; lượng ít tránh ngập/hóc nếu dùng hạt; chọn hạt to hoặc chỉ nước; trải khăn."
  reviewed_by: []

- id: phy_15_tung_bong_hai_tay
  title: Tung–bắt bóng gần bằng hai tay
  age_min: 26
  age_max: 36
  domains: [PHYSICAL]
  duration: 7
  materials: ["Bóng vải nhẹ"]
  goal: "Phối hợp tay–mắt; đứng vững khi với."
  steps:
    - "Đứng cách bé nửa mét; lăn hoặc tung nhẹ bóng vào tay bé."
    - "Mời bé đẩy/tung lại."
    - "3–6 lượt rồi đổi sang ngồi nếu mệt."
  parent_phrases:
    - "Hai tay bắt này!"
    - "Tung cho mẹ/bố nào."
  easier: "Chỉ lăn bóng trên sàn."
  harder: "Đứng xa hơn một bước; bắt không ôm vào người."
  safety: "Bóng nhẹ; không chơi gần góc tủ kính."
  reviewed_by: []
```

---

# ## COGNITIVE

## 1. Mục tiêu phát triển theo band

### 18–24 tháng

| Chủ đề | Mục tiêu định hướng (CDC 18m → tiến 2y) |
|---|---|
| Bắt chước & dụng cụ | Bắt chước việc nhà đơn giản; chơi đồ chơi đúng cách sơ cấp (đẩy xe…) |
| Nhân quả | Nhấn/đẩy cái gì đó → có phản hồi (cuối band) |
| Phân loại sơ cấp | Nhóm “cùng loại” rất đơn giản (hai quả bóng) — giả định trải nghiệm, chưa phải mốc CDC bắt buộc |
| Không gian | Cất–lấy đồ khỏi hộp; chồng 2+ vật |

### 24–30 tháng

| Chủ đề | Mục tiêu định hướng (CDC 2y → 30m) |
|---|---|
| Hai tay phối hợp | Một tay giữ, một tay thao tác (mở nắp) |
| Giả vờ đơn giản | Đặt “thức ăn” lên đĩa đồ chơi; nuôi búp bê bằng khối |
| Màu & chỉ dẫn | Nhận ≥1 màu khi được hỏi (30m); làm theo 2 bước |
| Giải quyết vấn đề | Dùng ghế thấp để với (an toàn có người lớn) |

### 30–36 tháng

| Chủ đề | Mục tiêu định hướng (CDC 30m → 3y) |
|---|---|
| Biểu tượng | Giả vờ phong phú hơn; kể trình tự rất ngắn khi chơi |
| Hình & bắt chước | Vẽ vòng tròn khi được mẫu (3y) |
| An toàn nhận thức gắn lời nhắc | Tránh chạm vật nóng khi được cảnh báo (3y) — **không** thay curriculum an toàn chuyên sâu |
| So sánh | To/nhỏ, nhiều/ít trong thao tác thật (giả định SPEC) |

## 2. Checklist mốc nhận thức (3 trạng thái)

Cùng `DONE` / `PRACTICING` / `NOT_YET`. Chỉ lấy mục **Cognitive** CDC (không lẫn Language trừ khi dual-activity).

### CDC 18 months — Cognitive

| id | Mốc (VI) | Nguồn |
|---|---|---|
| cog_cdc_18_chores | Bắt chước việc nhà (quét nhà…) | CDC 18m |
| cog_cdc_18_simple_play | Chơi đồ chơi kiểu đơn giản (đẩy xe…) | CDC 18m |

### CDC 2 years — Cognitive

| id | Mốc (VI) | Nguồn |
|---|---|---|
| cog_cdc_24_two_hands | Một tay giữ vật, tay kia thao tác (giữ hộp mở nắp) | CDC 2y |
| cog_cdc_24_switches | Thử công tắc/núm/nút trên đồ chơi | CDC 2y |
| cog_cdc_24_multi_toy | Chơi ≥2 đồ cùng lúc (đồ ăn lên đĩa) | CDC 2y |

### CDC 30 months — Cognitive

| id | Mốc (VI) | Nguồn |
|---|---|---|
| cog_cdc_30_pretend | Giả vờ (nuôi búp bê bằng khối như thức ăn) | CDC 30m |
| cog_cdc_30_problem | Giải quyết vấn đề đơn giản (đứng ghế thấp để với) | CDC 30m |
| cog_cdc_30_two_step | Làm theo hướng dẫn 2 bước | CDC 30m |
| cog_cdc_30_color | Biết ≥1 màu (chỉ đúng khi hỏi) | CDC 30m |

### CDC 3 years — Cognitive

| id | Mốc (VI) | Nguồn |
|---|---|---|
| cog_cdc_36_circle | Vẽ vòng tròn khi được chỉ mẫu | CDC 3y |
| cog_cdc_36_hot | Không chạm vật nóng khi được cảnh báo | CDC 3y |

### Mục giả định sản phẩm (không gắn CDC)

| id | Mốc giả định | Ghi chú |
|---|---|---|
| cog_hyp_sort_color | Nhóm 2–3 đồ theo màu | Khớp ví dụ SPEC; trải nghiệm |
| cog_hyp_big_small | Chỉ đúng to/nhỏ khi so sánh cặp | Khớp ví dụ SPEC |
| cog_hyp_hide_find | Tìm đồ vừa giấu tầm mắt | Trò nhân quả–trí nhớ làm việc ngắn |

---

## 3. Hoạt động nháp COGNITIVE (~15)

```yaml
# --- COGNITIVE draft activities ---
# reviewed_by: [] 

- id: cog_01_bat_chuoc_quet_nha
  title: Bắt chước quét nhà
  age_min: 18
  age_max: 26
  domains: [COGNITIVE, PHYSICAL]
  duration: 7
  materials: ["Chổi nhỏ/khăn lau", "vùng sàn nhỏ"]
  goal: "Bắt chước việc nhà (CDC 18m)."
  steps:
    - "Người lớn quét/lau 2–3 nhịp."
    - "Đưa chổi/khăn cho bé làm theo."
    - "Khen và cùng “xong việc”."
  parent_phrases:
    - "Con quét như mẹ/bố này."
    - "Sạch chưa ta?"
  easier: "Chỉ đưa khăn; người lớn cầm tay lau."
  harder: "Quét rồi đổ “rác” giấy vào túi."
  safety: "Không hóa chất tẩy; cán chổi không để bé vung gần mặt kính."
  reviewed_by: []

- id: cog_02_day_xe_do_choi
  title: Đẩy xe/đồ kéo
  age_min: 18
  age_max: 24
  domains: [COGNITIVE, PHYSICAL]
  duration: 8
  materials: ["Xe đẩy đồ chơi hoặc hộp có dây kéo chắc"]
  goal: "Chơi đúng chức năng sơ cấp (CDC 18m simple play)."
  steps:
    - "Đặt xe trước bé; mẫu đẩy."
    - "Để bé đẩy theo đường gối."
    - "Đổi hướng; dừng khi chán."
  parent_phrases:
    - "Xe chạy nào!"
    - "Đẩy tới gối kia."
  easier: "Người lớn kéo dây, bé đi theo."
  harder: "Chở thêm 1 món đồ trên xe."
  safety: "Dây ngắn không quấn cổ; sàn trống."
  reviewed_by: []

- id: cog_03_mo_nap_hop
  title: Giữ hộp – mở nắp
  age_min: 22
  age_max: 30
  domains: [COGNITIVE, PHYSICAL]
  duration: 8
  materials: ["Hộp nhựa nắp đậy", "đồ thú vị bên trong"]
  goal: "Hai tay phối hợp (CDC 2y)."
  steps:
    - "Cho bé thấy đồ trong hộp."
    - "Hướng dẫn một tay giữ hộp, tay kia kéo/vặn nắp."
    - "Chơi với đồ bên trong 1–2 phút."
  parent_phrases:
    - "Tay này giữ, tay kia mở."
    - "Mở ra rồi!"
  easier: "Nắp lỏng chỉ cần kéo."
  harder: "Thêm lần đóng lại rồi mở."
  safety: "Không đồ nhỏ nuốt được."
  reviewed_by: []

- id: cog_04_nut_do_choi
  title: Bấm nút đồ chơi có phản hồi
  age_min: 22
  age_max: 30
  domains: [COGNITIVE]
  duration: 6
  materials: ["Đồ chơi nút đèn/nhạc nhẹ hoặc điều khiển TV giả (hộp)"]
  goal: "Thử nút/núm (CDC 2y switches)."
  steps:
    - "Chỉ nút; bấm mẫu một lần."
    - "Để bé bấm; gọi tên hiệu ứng (“đèn sáng”)."
    - "Tắt/nghỉ sớm tránh quá kích thích."
  parent_phrases:
    - "Con bấm nút này xem sao."
    - "Ồ, có tiếng kìa!"
  easier: "Chỉ 1 nút."
  harder: "Chọn đúng nút trong 2 nút theo lời."
  safety: "Âm lượng thấp; pin chắc không tháo được; giới hạn thời gian."
  reviewed_by: []

- id: cog_05_do_an_len_dia
  title: Đặt “đồ ăn” lên đĩa
  age_min: 22
  age_max: 32
  domains: [COGNITIVE]
  duration: 8
  materials: ["Đĩa nhựa", "2–4 món đồ chơi thức ăn hoặc khối/lá giả"]
  goal: "Chơi nhiều đồ cùng lúc (CDC 2y)."
  steps:
    - "Đặt đĩa giữa hai người."
    - "Mời bé đặt từng món lên đĩa."
    - "“Mời ăn” giả vờ ngắn."
  parent_phrases:
    - "Để lên đĩa nào."
    - "Thêm một món nữa."
  easier: "Chỉ 1 món."
  harder: "Phân “của bé / của mẹ” hai đĩa."
  safety: "Không đồ giống thật gây nhầm nuốt (pin, thuốc)."
  reviewed_by: []

- id: cog_06_nuoi_bup_be
  title: Nuôi búp bê/gấu bằng khối
  age_min: 28
  age_max: 36
  domains: [COGNITIVE, SOCIAL_EMOTIONAL]
  duration: 8
  materials: ["Búp bê/gấu bông", "khối hoặc muỗng+bát"]
  goal: "Giả vờ (CDC 30m pretend)."
  steps:
    - "Nói “Gấu đói quá”."
    - "Đưa khối/muỗng; bé “đút”."
    - "Đặt gấu “ngủ” sau khi ăn."
  parent_phrases:
    - "Đút cho gấu nào."
    - "Ngon quá!"
  easier: "Người lớn đút mẫu 1 lần."
  harder: "Thêm bước lau miệng / đắp chăn."
  safety: "Mắt/phụ kiện gấu chắc; không nút nhỏ long."
  reviewed_by: []

- id: cog_07_hai_buoc_don_do
  title: Làm theo hai bước đơn giản
  age_min: 28
  age_max: 36
  domains: [COGNITIVE, LANGUAGE]
  duration: 6
  materials: ["1 đồ chơi", "hộp/giỏ"]
  goal: "Làm theo 2 bước (CDC 30m)."
  steps:
    - "Nói rõ: “Nhặt bóng và bỏ vào giỏ.”"
    - "Chờ bé làm; nhắc lại nguyên câu nếu cần (không tách sẵn trừ khi easier)."
    - "Khen khi hoàn thành cả hai."
  parent_phrases:
    - "Nhặt bóng và bỏ vào giỏ."
    - "Giỏi quá, con làm đủ hai việc!"
  easier: "Làm từng bước có pause."
  harder: "Thêm bước thứ ba nhẹ (“đậy nắp”)."
  safety: "Đồ không nguy hiểm; giỏ không cạnh cầu thang."
  reviewed_by: []

- id: cog_08_chi_mau_do
  title: Chỉ màu đỏ (hoặc một màu)
  age_min: 28
  age_max: 36
  domains: [COGNITIVE, LANGUAGE]
  duration: 7
  materials: ["2–3 bút sáp/khối khác màu, có 1 màu đỏ"]
  goal: "Nhận ≥1 màu (CDC 30m)."
  steps:
    - "Chỉ và nói “Đây màu đỏ”."
    - "Hỏi: “Cái nào màu đỏ?”"
    - "Đúng thì chơi ngắn với màu đó."
  parent_phrases:
    - "Đỏ đây này."
    - "Con chỉ quả đỏ nào?"
  easier: "Chỉ 2 lựa chọn lệch rõ (đỏ vs xanh)."
  harder: "3–4 màu; hỏi màu thứ hai."
  safety: "Sáp lớn; không để ngậm."
  reviewed_by: []

- id: cog_09_ghe_thap_voi_do
  title: Dùng ghế thấp để với đồ
  age_min: 28
  age_max: 36
  domains: [COGNITIVE, PHYSICAL]
  duration: 6
  materials: ["Ghế thấp vững", "đồ đặt vừa tầm khi đứng trên ghế (không cao)"]
  goal: "Problem-solving đơn giản (CDC 30m) **có giám sát**."
  steps:
    - "Đặt đồ hơi cao vừa phải; ghế ở gần."
    - "Hỏi: “Làm sao lấy được?”; chờ bé kéo ghế."
    - "Giữ ghế/hông khi bé lên; lấy đồ; xuống ngay."
  parent_phrases:
    - "Con cần gì để với tới?"
    - "Ghế giúp con này."
  easier: "Người lớn đẩy ghế tới gần rồi để bé leo."
  harder: "Bé tự nghĩ kéo ghế từ xa hơn 1–2 bước."
  safety: "Chỉ ghế thấp; người lớn giữ; **cấm** ghế cao/bàn; không biến thành trò leo tủ — an toàn chuyên sâu xem *Bé Gấu An Toàn*."
  reviewed_by: []

- id: cog_10_ve_vong_tron_mau
  title: Vẽ vòng theo mẫu
  age_min: 32
  age_max: 36
  domains: [COGNITIVE, PHYSICAL, AESTHETIC]
  duration: 8
  materials: ["Giấy", "sáp lớn"]
  goal: "Vẽ vòng khi được chỉ (CDC 3y)."
  steps:
    - "Người lớn vẽ vòng chậm vừa nói “vòng tròn”."
    - "Mời bé vẽ; chấp nhận méo."
    - "Khoe tranh; không sửa nét."
  parent_phrases:
    - "Làm vòng như này."
    - "Vòng của con đây!"
  easier: "Vẽ trên cát/gạo khay bằng ngón tay."
  harder: "Vẽ vài vòng; tô vào trong vòng mẫu."
  safety: "Sáp an toàn trẻ em; giám sát miệng."
  reviewed_by: []

- id: cog_11_nong_khong_cham
  title: Nghe lời “nóng — không chạm”
  age_min: 30
  age_max: 36
  domains: [COGNITIVE]
  duration: 5
  materials: ["Cốc ấm (không nóng bỏng) hoặc tranh bếp", "không dùng lửa thật để “thử”"]
  goal: "Tránh chạm khi được cảnh báo (CDC 3y) — **nhắc nhận thức**, không dạy cứu hộ."
  steps:
    - "Chỉ cốc ấm/tranh bếp: “Nóng, không chạm.”"
    - "Mời bé chỉ tay “không” hoặc lùi bước."
    - "Khen khi bé không chạm; chuyển chơi khác."
  parent_phrases:
    - "Nóng — không chạm."
    - "Con nhớ giỏi quá."
  easier: "Chỉ tranh/ảnh, không vật ấm."
  harder: "Nhắc trong bối cảnh bếp thật khi người lớn nấu (bé đứng xa)."
  safety: "Không để bé kiểm chứng bằng cách chạm nóng; khoảng cách an toàn; nội dung sâu về bếp/lửa thuộc an toàn chuyên biệt."
  reviewed_by: []

- id: cog_12_to_nho_hai_hop
  title: To và nhỏ
  age_min: 24
  age_max: 36
  domains: [COGNITIVE, LANGUAGE]
  duration: 7
  materials: ["2 hộp/quả bóng khác cỡ rõ"]
  goal: "So sánh to/nhỏ (giả định SPEC)."
  steps:
    - "Đặt hai đồ cạnh nhau; nói “to / nhỏ”."
    - "Hỏi: “Đâu là cái to?”"
    - "Đảo vị trí; hỏi lại."
  parent_phrases:
    - "Cái này to, cái này nhỏ."
    - "Cho mẹ cái nhỏ nào."
  easier: "Chênh lệch kích thước rất lớn."
  harder: "Thêm mức “vừa”; xếp theo thứ tự."
  safety: "Đồ không nhỏ nuốt được."
  reviewed_by: []

- id: cog_13_nhom_theo_mau
  title: Nhóm đồ cùng màu
  age_min: 26
  age_max: 36
  domains: [COGNITIVE]
  duration: 8
  materials: ["6–8 kẹp áo/khối 2 màu", "2 đĩa/khay"]
  goal: "Phân loại màu sơ cấp (giả định SPEC)."
  steps:
    - "Để mẫu 1 đỏ trên đĩa A, 1 xanh trên đĩa B."
    - "Mời bé bỏ từng cái đúng đĩa."
    - "Đếm mỗi nhóm."
  parent_phrases:
    - "Đỏ với đỏ."
    - "Xanh để đây."
  easier: "Chỉ 4 đồ, 2 màu."
  harder: "3 màu hoặc thêm hình dạng."
  safety: "Kẹp không quá nhỏ; giám sát."
  reviewed_by: []

- id: cog_14_giau_tim_khan
  title: Giấu–tìm dưới khăn
  age_min: 18
  age_max: 28
  domains: [COGNITIVE]
  duration: 6
  materials: ["Khăn", "1 đồ chơi"]
  goal: "Nhân quả / tìm kiếm vật vừa thấy."
  steps:
    - "Cho bé thấy đồ; đậy khăn trước mặt bé."
    - "Hỏi “Đâu rồi?”; để bé kéo khăn."
    - "Lặp 3–4 lần; đổi chỗ giấu trong tầm nhìn."
  parent_phrases:
    - "Đâu mất tiêu rồi?"
    - "À, ở đây!"
  easier: "Đậy nửa khăn, còn lộ một phần."
  harder: "Giấu dưới 1 trong 2 khăn."
  safety: "Không bịt mặt bé lâu; không túi nilon mỏng."
  reviewed_by: []

- id: cog_15_xep_khoi_theo_mau
  title: Xếp khối theo mẫu tháp
  age_min: 24
  age_max: 36
  domains: [COGNITIVE, PHYSICAL]
  duration: 8
  materials: ["6 khối 2 màu"]
  goal: "Bắt chước mẫu đơn giản + chồng khối."
  steps:
    - "Người lớn xếp mẫu 3 khối (vd. đỏ-xanh-đỏ)."
    - "Mời bé xếp tháp giống bên cạnh."
    - "So sánh ngắn; chơi đổ tháp."
  parent_phrases:
    - "Làm giống tháp mẹ/bố."
    - "Màu này tiếp theo nào?"
  easier: "Chỉ bắt chước chiều cao, không màu."
  harder: "Mẫu 4–5 khối; bé xếp không nhìn mẫu (che lại)."
  safety: "Khối lớn; không ném."
  reviewed_by: []
```

---

## Phân bổ & ghi chú cho Daily Picker

### Tag `domains`

| Quy ước | Cách dùng |
|---|---|
| Primary | Phần tử đầu trong `domains[]` = lĩnh vực “tính quota ngày” |
| Dual | Thêm domain thứ 2 khi bước chơi thật sự đụng kỹ năng đó (vd. `phy_13` PHYSICAL+COGNITIVE; `cog_07` COGNITIVE+LANGUAGE) |
| Tránh | Gắn 3+ domain trừ khi product quy định; không gắn `SELF_CARE` sâu (ăn–bô–ngủ) — để agent D |

### Gợi ý quota MVP (giả định, chưa chốt CONSENSUS)

- Mỗi ngày 2–3 hoạt động: ưu tiên **≥1 PHYSICAL hoặc COGNITIVE** xen kẽ tuần, tránh 7 ngày chỉ một mã.
- Ưu tiên `duration` 5–10'; band 18–24m; có thể 8–15 với 30–36m.
- Lọc `age_min ≤ age_months < age_max` (hoặc ≤ age_max nếu inclusive — chốt ở TECH).
- Nếu checklist band đang nhiều `NOT_YET` ở vận động thô → Picker ưu tiên `phy_01`, `phy_05`, `phy_06` trước tinh/nhảy.
- Dual-domain giúp đủ ~90 hoạt động toàn app mà không nhân bản nội dung.

### Tránh trùng *Bé Gấu An Toàn*

| Được làm ở file này | Không làm |
|---|---|
| `safety` 1–2 câu gắn thao tác (té, hóc, ghế thấp) | Module nước/điện/cháy/đường/cấp cứu |
| `cog_11` chỉ luyện **nghe cảnh báo “nóng”** | Kịch bản nguy hiểm đầy đủ, quiz tình huống |
| Cầu thang có người lớn trong `phy_07` | Giáo án ATGT / “dạo phố” |

### Trường bắt buộc trước khi seed

- Mọi YAML giữ `reviewed_by: []` đến khi có tên chuyên gia/GV MN/phụ huynh pilot.
- Cần đồng bộ id với agent A (khung) và E (DoD nội dung) trước khi import Room.

### Việc còn mở cho parent

1. Đối chiếu chính thức **Chương trình GDMN Bộ GD&ĐT** (bản PDF/QĐ) với bảng mục tiêu band — hiện là giả định.
2. Quyết định có hiển thị mục `phy_hyp_*` / `cog_hyp_*` trên UI checklist hay chỉ dùng nội bộ Picker.
3. Chốt inclusive/exclusive `age_max` và trọng số dual-domain trong Daily Picker.

---

## Phụ lục — URL tham chiếu đã dùng

- CDC milestones index: https://www.cdc.gov/act-early/milestones/index.html  
- CDC 18 months / 2 years / 30 months / 3 years (cùng hệ LTSAE)  
- CDC Milestone Moments booklet (PDF LTSAE)  
- WHO motor percentiles PDF: https://cdn.who.int/media/docs/default-source/child-growth/child-growth-standards/indicators/motor-development-milestones/mm_percentiles_table.pdf  
- WHO Motor Development Study: Acta Paediatrica Supplement 2006;450:86–95  

*Hết file subagent B.*
