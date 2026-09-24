# X5 — Editor-in-chief · CONSENSUS REVIEW (Wave 4)

> Workspace: `ideas/BeGau` · Ngày: 2026-09-24  
> Nguồn: `plans/WAVE4_EXPERT_QA.md`, `plans/CONSENSUS.md`, X1–X4 (`01`…`04`)  
> **Chỉ tổng hợp biên tập** — không sửa seed/code/AGENTS/PROJECT_STATE  
> **Không** claim duyệt chuyên gia người thật · Seed vẫn `draft_unreviewed` / `reviewed_by: []`

---

## 1. Tóm tắt 4 góc nhìn (X1–X4)

| ID | Vai trò | Verdict ngắn | Điểm mạnh giữ | Lệch / rủi ro chính |
|---|---|---|---|---|
| **X1** | Sư phạm GDMN | Taxonomy 6 domain **chấp nhận được** cho app PH; quota 90 HĐ **PASS**; cân band tuổi **FAIL nhẹ–vừa** | Tone “không ép” nhiều chỗ; primary 16/14/16/14/12/18 khớp CONSENSUS | **18–24 under-served** (visible ~34; SOC chỉ ~3); `goal` neo CDC/số khối → ép đạt chuẩn; potty `harder` dễ hiểu nhầm tập bô sớm |
| **X2** | Phát triển & an toàn | Mọi HĐ có `safety` (PASS kỹ thuật); ranh giới *Bé Gấu An Toàn* **đúng hướng** | `phy_07` / `cog_09` mẫu safety tốt; không HĐ đuối nước / cháy / ATGT | **Hóc** (AES-08 hạt DIY 18m, phy_14 gạo/đậu); `act_sc_safe_030_18` safety lệch tone; web **chưa** checklist mốc + **0** cẩm nang; SC “đồ nhỏ” bắt đầu muộn (24m) |
| **X3** | Nội dung & giọng văn | Tone CONSENSUS §2.3 **khớp**; DoD đọc core ≤30s **PASS**; không “bỏ lỡ / chậm hơn bạn” | PP ngắn, dễ/khó cụ thể; Android reminder tone tốt | ~**20 goal** còn CDC + English trên S05; Welcome **GDMN** dễ overclaim; cặp **cog_07 ↔ LANG-10** trùng ý mạnh |
| **X4** | UI/UX web pilot | Happy path S01→S06 **OK**; contrast AA **PASS**; brand forest (không purple) | Disclaimer banner; feedback 😊😐😕 có nhãn chữ; Đổi gợi ý đúng hướng | Thư viện **cap 60** + copy “thu hẹp” sai; filter empty **không copy**; sau complete Today **không đổi**; thiếu tab Nhật ký / S03; touch filter/nav &lt;48px; CTA S05 không sticky |

**Hội tụ X5:** Nội dung MVP **đủ để pilot hẹp** nếu siết P0 an toàn + copy goal + gap 18–24 + vài lỗ UI web. **Chưa** sẵn sàng pilot phụ huynh rộng / Closed testing nếu bỏ qua P0.

---

## 2. Bảng P0 / P1 / P2 hợp nhất (bỏ trùng)

*Quy ước id: `HĐ-*` = seed hoạt động · `UI-*` = web/app shell · `COPY-*` = chuỗi PH · `SYS-*` = nguyên tắc / không ship nguy hiểm.*

### P0 — trước pilot phụ huynh rộng (Applier Wave 5 nếu X6 APPROVE)

| ID | Loại | Nguồn | Việc | Ghi chú gộp |
|---|---|---|---|---|
| **HĐ-P0-01** | HĐ | X1+X3 | Viết lại **goal** bỏ / ẩn CDC + English + chỉ tiêu số (≥ màu, số khối, “lịch sự”) khỏi bề mặt S05 | Phủ ~20 `phy_*`/`cog_*` + Top 10 X1; mẫu: `phy_13`, `phy_02` (nhóm CDC), `cog_08`, `cog_10`, `SOC-14` |
| **HĐ-P0-02** | HĐ | X1 | Siết **potty** `act_sc_potty_018_10`: `harder` không khung giờ sau ăn kiểu tập bô | Chuyển khung giờ → HĐ ≥24–30 |
| **HĐ-P0-03** | HĐ | X1+X3 | Tách / gộp / đổi ngữ cảnh **`cog_07_hai_buoc_don_do` ↔ `LANG-10`** | Tránh Daily Picker 2 thẻ cùng trò 2 bước bóng→giỏ |
| **HĐ-P0-04** | HĐ | X1 | Tăng phủ **18–24**: thêm ≥8–10 HĐ (ưu tiên `SOCIAL_EMOTIONAL`) **hoặc** hạ `ageMin` có kiểm soát — mục tiêu visible 18–24 **≥50** | X6 ký: ADD mới vs chỉ hạ tuổi |
| **HĐ-P0-05** | HĐ | X2 | Siết **hóc**: `AES-08` (DIY hạt ≥24 / 18–23 lục lạc sẵn); `phy_14` mặc định **chỉ nước** (hạt ≥30); `phy_11` kích thước tối thiểu + không vòng cổ | Materials + `safety` |
| **HĐ-P0-06** | HĐ | X2 | Viết lại `safety` **`act_sc_safe_030_18`** (ngồi khi ăn / nóng / không chạy miệng còn đồ) | Hiện chỉ tone — lệch SC_SAFE |
| **HĐ-P0-07** | HĐ | X2 | Hạ `ageMin` → **18** hoặc thêm bản 18–24 cho **`act_sc_safe_024_17`** (đồ nhỏ không vào miệng) | Oral exploration sớm |
| **UI-P0-01** | UI | X4 | Thư viện: nút **Xem thêm** / bỏ cap 60; sửa copy “thu hẹp bộ lọc”; **empty state** khi filter = 0 | Bug UX + 30 HĐ “mất” |
| **UI-P0-02** | UI | X4 | After-complete trên Today: đánh dấu / tách “đã chơi hôm nay” | Cảm nhận giá trị sau S06 |
| **UI-P0-03** | UI | X4 | Touch: nav + chip filter ≥44–48px; CTA Chi tiết **sticky**/fixed; `:focus-visible` rõ | A11y ông bà |
| **UI-P0-04** | UI | X2+X4 | Progress: banner mốc **không dismiss** + stub 6–12 mốc neo (3 trạng thái) **hoặc** ít nhất copy kỳ vọng rõ nếu chưa ship mốc | X2 = checklist; X4 chấp nhận stub nếu trung thực |
| **UI-P0-05** | UI | X4 | 1 dòng IA: Nhật ký & S03 chưa có trên web → có trên Android / sắp có | Quản lý kỳ vọng |
| **COPY-P0-01** | COPY | X1+X3+X4 | Welcome / UI: **không** claim “chuẩn Bộ / chuyên gia duyệt”; làm mềm dòng GDMN | “Tham khảo hướng… nội dung nháp” |
| **SYS-P0-01** | SYS | X1+X2 | Giữ disclaimer `draft_unreviewed`; **không** nhét module cháy/điện/nước/đường; giữ cross-link `phy_07` / `cog_09` | Ranh giới *An Toàn* |

### P1 — trước Closed testing / Wave 5 nội dung sâu

| ID | Loại | Nguồn | Việc |
|---|---|---|---|
| **HĐ-P1-01** | HĐ | X1+X3 | Phân biệt `phy_09`/`cog_03`, `phy_13`/`cog_15`; theo dõi `phy_03`/`AES-01`; rút `cog_10` ≤2 domain |
| **HĐ-P1-02** | HĐ | X1 | Thu hẹp span 18–36 / tách bản dễ–khó; nhân điều kiện tuổi trong `harder` (X2) |
| **HĐ-P1-03** | HĐ | X1 | Top 10 còn lại: `LANG-07` (“bao nhiêu” → harder ≥30); `SOC-01` (gọi tên cảm xúc từ ~24); `cog_15` bỏ che mẫu quiz |
| **HĐ-P1-04** | HĐ | X2 | Đồng bộ safety WC/trượt rửa tay–bô; rà `phy_16` hầm gối (thở/mặt); chuẩn hóa dây ≤ không quấn cổ |
| **HĐ-P1-05** | HĐ | X2 | Ship **≥4 cẩm nang** CONSENSUS (ăn vạ, biếng ăn, khó ngủ, tập bô) + CTA sang HĐ |
| **UI-P1-01** | UI | X4 | Tab **Nhật ký** stub; S03 “Để sau” tối giản; thẻ Today = 1 control; ghim từ Thư viện |
| **UI-P1-02** | UI | X3+X4 | Banner nhắc in-app web + đồng bộ bank Android; Progress đổi “MVP ≥3/tuần” → gợi ý nhẹ không guilt |
| **COPY-P1-01** | COPY | X3 | `LANG-05` tách PP; thêm PP đầy đủ cho “Nóng — dừng tay”; rà parentPhrases không phán xét |
| **SYS-P1-01** | SYS | X1 | (Tuỳ chọn) chế độ hiển thị gộp thẩm mĩ–XH cho đối thoại GV nhà trẻ |

### P2 — sau pilot / polish

| ID | Loại | Nguồn | Việc |
|---|---|---|---|
| **HĐ-P2-01** | HĐ | X1+X3 | Backlog chủ đề VN + gắn bó 18–24 (xem §3 ADD) |
| **HĐ-P2-02** | HĐ | X2 | Đủ ~20 cẩm nang; deep-link *An Toàn*; mở rộng 0–6 tuổi |
| **UI-P2-01** | UI | X4 | Onboarding 3 slide; motion 2–3; icon không emoji; dark mode; brand giảm cream/terracotta nếu parent muốn |
| **COPY-P2-01** | COPY | X3 | Khen cụ thể thay “Giỏi quá”; `checklist` → “danh sách…”; ẩn jargon dev; CTA “Hoàn thành” |
| **SYS-P2-01** | SYS | X1+X2 | Duyệt người thật → `reviewed_by`; đối chiếu chọn lọc VBHN; nghiên cứu PH hiểu disclaimer CDC |

---

## 3. KEEP / FIX / ADD

### KEEP (không đụng Wave 5 trừ khi X6 bắt buộc)

- Quota primary **16 / 14 / 16 / 14 / 12 / 18** và enum 6 domain (quyết định sản phẩm CONSENSUS).
- Tone “không ép / mỗi bé một nhịp”; không so sánh bạn bè / percentile trên UI.
- Ranh giới *Bé Gấu An Toàn*: chỉ `safety` sinh hoạt + `SC_SAFE_DAILY`.
- Text-first; DoD core ≤30s (đã đạt kỹ thuật).
- Mẫu safety tốt: `phy_07_bac_thang_tay_vin`, `cog_09_ghe_thap_voi_do`.
- Tiến trình thìa theo tuổi (`act_sc_eat_018_01` / `030_05`) — không gộp.
- `content_status: draft_unreviewed`, `reviewed_by: []`, banner disclaimer (làm cứng hơn ở Progress nếu ship mốc).
- Android reminder title mẫu X3 (giọng mời).

### FIX (áp P0/P1 — id ưu tiên)

| Ưu tiên | ID HĐ / UI | Hướng sửa ngắn |
|---|---|---|
| P0 | Goals CDC/English (~20) + `phy_13`, `cog_08`, `SOC-14` | Goal = lời mời chơi; nguồn nội bộ |
| P0 | `act_sc_potty_018_10` harder | Bỏ khung giờ tập bô |
| P0 | `cog_07` ↔ `LANG-10` | Một primary / đổi ngữ cảnh |
| P0 | `AES-08`, `phy_14`, `phy_11` | Materials + safety hóc |
| P0 | `act_sc_safe_030_18`, `act_sc_safe_024_17` | Safety vật lý; tuổi 18–24 chống hóc |
| P0 | Web Library / Today complete / touch / Welcome GDMN | UI-P0-01…05, COPY-P0-01 |
| P1 | `LANG-07`, `SOC-01`, `cog_10`, `cog_15`, `phy_09`/`cog_03`… | Theo X1 Top 10 + X3 trùng nhẹ |

### ADD (nội dung mới — đề xuất; X6 quyết có ship Wave 5 không)

*Gộp X1 §4 + X3 §4; ưu tiên 18–24 & SOCIAL_EMOTIONAL & VN nhà.*

| # | Ý HĐ (id gợi ý) | Domain gợi ý | Band | Ghi chú |
|---:|---|---|---|---|
| 1 | `act_soc_chi_khoe_do` | SOC + LANG | 18–26 | Proto-declarative; mẫu A X1 |
| 2 | Tách ngắn – hợp lại (peek) | SOC | 18–24 | Không “luyện bỏ mẹ” |
| 3 | Chơi song song hai khay | SOC | 18–28 | Trước chia sẻ |
| 4 | `act_cog_do_hat_hop` | COG + PHY | 18–28 | Hạt lớn / không hạt nhỏ; mẫu B X1 |
| 5 | `act_sc_bo_do_vao_gio` | SC + COG | 18–30 | Giúp việc; mẫu C X1 |
| 6 | Đi chân đất 2–3 bề mặt | PHY | 18–30 | Cảm giác thân |
| 7 | Ảnh gia đình gọi tên | LANG + SOC | 18–30 | |
| 8 | Tưới cây / lau lá | AES / SOC | 18–36 | Trùng bonus X3 |
| 9 | Đưa đồ khi được xin | SOC | 18–30 | Tiền-chia sẻ |
| 10 | Ú òa khăn 18–24 | COG / SOC | 18–24 | |
| 11 | Hét / thì thầm thư viện âm | LANG + AES | 18–30 | |
| 12 | Xếp nắp xoong size | COG | 18–24 | |
| 13 | Mang túi nhẹ 2–3 bước | PHY + SC | 18–30 | |
| 14 | Ngắm mây/lá ban công có rào | AES | 18–36 | |
| 15 | Chào thú nhồi / tạm biệt đêm | SOC | 18–30 | Trước SOC-04 cửa lớn |
| 16 | Áo mưa / ủng | SC / PHY | 24–36 | X3 VN |
| 17 | Đi chợ rau thật ngắn | LANG | 24–36 | Khác LANG-07 đồ chơi |
| 18 | Chào / gọi điện ông bà | SOC + LANG | 18–36 | |
| 19 | Dép vào–ra cửa | SC | 18–30 | |
| 20 | Bỏ rác vào thùng | SC | 18–30 | |
| 21 | Đánh răng / lau răng gương | SC | 24–36 | Tách nghi thức ngủ |
| 22 | Quạt mát–lạnh (không thò nan) | SC_SAFE | 24–36 | Mức sinh hoạt |
| 23 | Sân CC / công viên 5–10′ | PHY | 24–36 | Giám sát |
| 24 | Mâm cơm chỉ bát–đũa | SC / LANG | 18–36 | Không ép ăn |
| 25 | Tết / Trung thu nhẹ | AES / SOC | 24–36 | Không tôn giáo sâu |

**Khuyến nghị X5 cho Wave 5:** nếu X6 cho ADD — ưu tiên ship **#1–5 + #7 + #9** (đủ kéo SOC 18–24) trước; phần VN X3 vào backlog P1/P2.

---

## 4. Rủi ro nếu không làm P0

| Nếu bỏ | Hệ quả |
|---|---|
| **HĐ-P0-01 / COPY-P0-01** (goal CDC + claim GDMN) | PH/ông bà đọc như checklist đạt mốc / “chuẩn Bộ” → ép phát triển, mất uy tín, lệch CONSENSUS §8 |
| **HĐ-P0-05…07** (hóc + safety SC) | Rủi ro **an toàn thật** (hóc hạt/nắp chai, hiểu sai HĐ “an toàn”); đụng pháp lý cảm nhận dù là draft |
| **HĐ-P0-02** (potty harder) | Ép tập bô 18–24 → stress bé/PH, trái tone “không ép” |
| **HĐ-P0-04** (thiếu 18–24 / SOC) | Toddler đầu MVP đói gắn bó–cảm xúc; picker 18m chỉ ~1/3 thư viện → pilot lệch tuổi |
| **HĐ-P0-03** (trùng 2 bước) | Daily trùng trải nghiệm → PH nghĩ app nghèo / lỗi |
| **UI-P0-01** (library 60 + empty) | 30 HĐ “biến mất”; filter 0 kết quả im lặng → mất tin tưởng |
| **UI-P0-02** (complete không đổi Today) | Cảm giác “làm xong chẳng gì” → KPI ≥3/tuần khó đạt |
| **UI-P0-03** (touch / focus) | Ông bà (Bà Hạnh) khó bấm chip; bàn phím/SR kém → loại persona chính |
| **SYS-P0-01** bỏ disclaimer / tràn An Toàn | Overclaim duyệt hoặc trùng module nguy hiểm → lệch la bàn sản phẩm |

---

## 5. Gợi ý cho X6 Senior EDU (điểm cần ký)

X6 ghi `05_SENIOR_EDU_SIGN_OFF.md` với `VERDICT: APPROVE | APPROVE_WITH_EDITS | REJECT`. Các điểm **nên ký rõ**:

1. **Taxonomy:** Chấp nhận 6 mã (gồm `SELF_CARE`) là quyết định sản phẩm — **không** tuyên bố = lĩnh vực Bộ; UI không claim “theo đúng CT GDMN đã duyệt”?
2. **P0 bắt buộc Applier:** Đồng ý toàn bộ bảng §2 P0, hay chỉ subset? Liệt kê id cấm/cho phép trong `EDITS_REQUIRED` nếu `APPROVE_WITH_EDITS`.
3. **ADD vs chỉ FIX:** Cho phép thêm HĐ mới Wave 5 (mục tiêu ~90 → ~98–100 có kiểm soát) hay **chỉ** sửa seed hiện có + hạ `ageMin`?
4. **Ưu tiên ADD:** Ký danh sách tối thiểu (đề xuất X5: #1–5, #7, #9) nếu APPROVE ADD.
5. **Potty 18–24:** Giữ làm quen sớm nhưng cấm `harder` khung giờ — đồng ý X1/X5?
6. **Goal & nguồn:** CDC/WHO chỉ `source_refs` nội bộ — **cấm** hiện trên goal PH?
7. **Checklist web:** Ship stub 6–12 mốc ngay (UI-P0-04) hay hoãn + disclaimer đủ?
8. **Cẩm nang:** P0 hay P1 (≥4 bài)? X2 xếp P1 — X6 có nâng P0 không?
9. **UI P0:** Cho phép Applier sửa web (library, complete, touch, Welcome) khi APPROVE?
10. **Status nội dung:** Giữ `draft_unreviewed` / không nâng `internal_reviewed` trừ khi X6 **ghi rõ** (vẫn chưa “chuyên gia Bộ”).
11. **Ranh giới An Toàn:** Xác nhận không ADD cháy/điện/nước/đường.

---

## 6. Disclaimer

Tài liệu này và toàn bộ báo cáo X1–X4 / X5 là **persona AI nội bộ** (kiểm thử biên tập & sư phạm giả lập).

- **Không** phải duyệt của giáo viên mầm non, chuyên gia tâm lý/nhi khoa, hay phụ huynh pilot người thật.
- **Không** thay `reviewed_by`, chứng nhận Bộ GD&ĐT, CDC/WHO, hay tư vấn y tế.
- Seed / web vẫn phải coi là **`draft_unreviewed`** cho đến khi có quy trình duyệt người thật và X6 (nếu APPROVE) chỉ cho phép chỉnh nháp nội bộ theo luồng Wave 5.
- Thử nghiệm phụ `ideas/BeGau` — **không đổi hướng active** (`traffic-count-analyzer`).

---

## Báo cáo điều phối (X5 → parent)

```
STATUS: OK
SCOPE: X5 Editor-in-chief — tổng hợp Wave 4 từ X1–X4 + CONSENSUS/WAVE4 thành 00_CONSENSUS_REVIEW (tóm tắt 4 góc; P0/P1/P2 hợp nhất bỏ trùng; KEEP/FIX/ADD; rủi ro P0; gợi ý ký X6; disclaimer). Không sửa seed/code/AGENTS/PROJECT_STATE. Không claim duyệt chính thức.
DONE: Đã đọc WAVE4_EXPERT_QA, CONSENSUS, 01–04 experts; ghi đủ 6 mục bắt buộc vào đúng 1 file output.
FILES: qa/experts/00_CONSENSUS_REVIEW.md
DEVIATIONS: Không mở lại seed JSON / browser — tin báo cáo X1–X4 + CONSENSUS đã trích. Gộp ADD X1+X3 thành một backlog (25 ý) thay vì hai danh sách riêng.
BLOCKERS: Không. Chờ X6 Senior EDU (05_SENIOR_EDU_SIGN_OFF.md) trước Wave 5 Applier.
ERRORS: Không.
NEXT_FOR_PARENT: (1) Chạy X6 với file này làm đầu vào. (2) Nếu X6 APPROVE / APPROVE_WITH_EDITS → Wave 5 Applier theo P0 (+ EDITS_REQUIRED). (3) Không nâng reviewed_by / claim chuyên gia chỉ vì X5.
```

---

*Hết X5 · Chỉ file này trong phạm vi giao.*
