# X1 — Sư phạm GDMN · Gấu Con (Wave 4)

> Persona: chuyên gia sư phạm mầm non (kiểm thử nội dung)  
> Workspace: `ideas/BeGau` · Ngày: 2026-09-24  
> Nguồn: `plans/CONSENSUS.md`, `research/01_khung_chuong_trinh.md`, seed 90 HĐ, `web/qa/CONTENT_QA_20260924.md`  
> **Không** phải duyệt chuyên gia chính thức · Seed vẫn `draft_unreviewed` / `reviewed_by: []`

---

## 0. Phân loại bằng chứng

| Loại | Ví dụ trong báo cáo này |
|---|---|
| **Quan sát có bằng chứng** | Đếm primary 16/14/16/14/12/18; số HĐ visible theo band tuổi; trích `goal`/`steps` từ seed |
| **Giả định sư phạm** | Diễn giải mục tiêu CT nhà trẻ/mẫu giáo theo kinh nghiệm GDMN + map đã nêu ở research A — **chưa** đối chiếu dòng-từng-dòng PDF VBHN |
| **Đề xuất** | Top 10 sửa; backlog HĐ mới; P0/P1/P2 |

---

## 1. Căn chỉnh 6 domain vs CT GDMN (nhà trẻ / mẫu giáo)

### 1.1. Map tổng quan — **hợp lý về sản phẩm**, chưa phải “chuẩn Bộ”

| CT GDMN | App Gấu Con | Đánh giá X1 |
|---|---|---|
| Nhà trẻ (~3–36 tháng): **4** lĩnh vực — TC; NT; NN; **TC–XH + thẩm mĩ (gộp)** | 6 mã tách `SOCIAL_EMOTIONAL` + `AESTHETIC` + thêm `SELF_CARE` | **Chấp nhận được** cho app phụ huynh: dễ lọc gợi ý. Khi nói với GV nhà trẻ cần UI/copy “gộp thẩm mĩ–XH”. |
| Mẫu giáo (3–6 tuổi): **5** lĩnh vực | 5 mã trùng tên + `SELF_CARE` | **Khớp khung tên** lĩnh vực mẫu giáo; `SELF_CARE` = quyết định sản phẩm (nếp nhà / ROUTINE_*), **không** tuyên bố là lĩnh vực thứ 6 của Bộ. |
| Chăm sóc–nuôi dưỡng / vệ sinh thân thể (nằm trong CT nhà trẻ & thể chất) | Phần lớn vào `SELF_CARE`; một phần dual `PHYSICAL` (mặc quần, chuyển nước…) | **Ổn** nếu primary rõ; tránh double-count khi báo cáo “đủ lĩnh vực Bộ”. |

**Kết luận căn chỉnh:** Taxonomy 6 mã **bám được** ngôn ngữ 5 lĩnh vực mẫu giáo + nhu cầu gia đình toddler. **Không** nên claim trên UI “theo đúng chương trình Bộ” / “đạt chuẩn GDMN” — đúng CONSENSUS §8.

### 1.2. Độ khớp nội dung theo lĩnh vực (quan sát seed)

| Mã | Khớp CT / thực hành GDMN 18–36m | Lệch / rủi ro |
|---|---|---|
| `PHYSICAL` | Vận động thô–tinh, leo có giám sát, nguệch — đúng cửa sổ toddler | Nhiều `goal` neo CDC như chỉ tiêu đạt; cầu thang / ghế với đồ cần safety rõ (X2 bổ sung) |
| `COGNITIVE` | Giả vờ, mở–đậy, phân loại sơ cấp, 2 bước — phù hợp | Trùng chủ đề với `LANGUAGE` (2 bước); “theo mẫu màu” dễ thành kiểm tra |
| `LANGUAGE` | Sách, chỉ đồ, cơ thể, nhu cầu, giả bộ thoại — đúng trọng tâm nhà trẻ | Band 18–24 mỏng hơn 24–36; một số mục tiêu hỏi “bao nhiêu” / 2 bước hơi sớm nếu min tuổi thấp |
| `SOCIAL_EMOTIONAL` | Gắn bó, cảm xúc, luân phiên, song song — đúng hướng | **Thiếu phủ 18–24** (chỉ ~3 HĐ visible); dễ lệch sang “dạy lễ nghi” (cảm ơn, chia sẻ) |
| `AESTHETIC` | Nhạc–nhịp, dấu vết, thưởng thức — đủ cho MVP | Trùng mạnh với `PHYSICAL` nguệch sáp (`AES-01` ≈ `phy_03`); nhà trẻ vốn gộp TC–XH–thẩm mĩ |
| `SELF_CARE` | Ăn–ngủ–bô–an toàn sinh hoạt — đúng nỗi đau PH | Bô 18–24 + nhánh `harder` theo khung giờ dễ thành **ép tập bô sớm** nếu PH hiểu nhầm |

### 1.3. Nguyên tắc sư phạm sản phẩm (CONSENSUS) vs seed

- Tone “không ép / không bắt” xuất hiện tốt ở nhiều `steps` (ví dụ sách, chia sẻ, thìa) — **điểm cộng**.
- Ngược lại, cụm `goal` kiểu “bám CDC…”, “mục tiêu ~4–6 khối”, “Nhận ≥1 màu” đẩy PH về **chuẩn đạt** thay vì **lời mời chơi** — lệch nguyên tắc “mỗi bé một nhịp”.

---

## 2. Phân bổ 90 HĐ & độ phủ band 18–24 / 24–30 / 30–36

### 2.1. Primary domain — **đạt quota CONSENSUS**

| PHYSICAL | COGNITIVE | LANGUAGE | SOCIAL_EMOTIONAL | AESTHETIC | SELF_CARE | Tổng |
|---:|---:|---:|---:|---:|---:|---:|
| 16 | 14 | 16 | 14 | 12 | 18 | **90** |

Khớp chốt A/CONSENSUS. Dual-domain: 36 HĐ; 1 HĐ triple+ (`cog_10` — nên rút ≤2 domain, P1).

### 2.2. Độ phủ theo band tuổi (filter inclusive `ageMin≤age≤ageMax`)

**Số HĐ bé ở tuổi đó có thể thấy (visible):**

| Band | Visible | Nhận xét X1 |
|---|---:|---|
| **18–24m** | **34** | **Thiếu rõ** so với kế hoạch research A (~28 “chủ yếu” + span). Picker 18–23 tháng chọn trong ~1/3 thư viện. |
| **24–30m** | **78** | Đủ / hơi dày |
| **30–36m** | **80** | Đủ / hơi dày |

**Phân lớp span tuổi (quan sát):**

| Lớp | Số HĐ |
|---|---:|
| Chủ yếu hẹp 18–24 (`ageMax≤24` kiểu span ngắn) | **6** |
| Span rộng / đa band | **72** |
| Chủ yếu 30–36 | **12** |

Age span phổ biến: `(24,36)=34`, `(18,30)=10`, `(18,36)=9`, `(30,36)=11`. Nhiều HĐ “neo 24–36” → **đầu MVP (18–24) bị bỏ trống tương đối**.

**Visible × primary trong band 18–24:** PHYSICAL 6 · COGNITIVE 6 · LANGUAGE 7 · **SOCIAL_EMOTIONAL 3** · AESTHETIC 6 · SELF_CARE 6.

→ Lệch sư phạm lớn nhất: **gắn bó / cảm xúc / tách–hợp lúc 18–24** (đúng giai đoạn nhạy của nhà trẻ) lại là domain mỏng nhất.

### 2.3. Verdict phân bổ

| Tiêu chí | Kết quả |
|---|---|
| Quota 6 domain | PASS (kỹ thuật / CONSENSUS) |
| Cân band tuổi | **FAIL nhẹ–vừa** về sư phạm: 18–24 under-served; 24–36 over-served |
| Cân trong 18–24 theo domain | **SOCIAL_EMOTIONAL P0** cần bổ sung hoặc hạ `ageMin` có kiểm soát |

---

## 3. Top 10 HĐ cần sửa mục tiêu / steps

*(Ưu tiên tác động sư phạm: ép đạt chuẩn, kiểm tra, tuổi lệch, trùng dual nguy hiểm.)*

| # | ID | Lý do (bằng chứng / giả định) | Đề xuất sửa |
|---|---|---|---|
| 1 | `phy_13_xep_khoi_thap` | `goal` ghi **“mục tiêu sản phẩm ~4–6 khối”** — biến chơi thành chỉ tiêu; span 20–36 quá rộng cho một mục tiêu số | Đổi goal → “Chồng khối / hộp lớn, vui khi đổ”; bỏ số khối khỏi goal; đưa 4–6 vào `harder` 30m+; cân nhắc tách bản 18–24 (2–3 khối) |
| 2 | `phy_02_leo_ghe_thap` (đại diện nhóm goal CDC) | Goal “bám CDC leo ghế 18m” khiến PH đọc như **checklist đạt mốc** | Viết lại goal kiểu lời mời: “Leo ghế thấp có người lớn cạnh”; chuyển CDC sang `source_refs` nội bộ, không hiện trong goal PH |
| 3 | `cog_08_chi_mau_do` | Goal “Nhận ≥1 màu (CDC 30m)” = chuẩn tối thiểu; steps dễ thành **trắc nghiệm màu** | Goal: “Chơi với một màu quen (ví dụ đỏ) — chỉ / cầm / nói nếu muốn”; chấp nhận chỉ tay không nói; bỏ “≥1” |
| 4 | `cog_10_ve_vong_tron_mau` | Goal CDC 3y + `harder` “tô vào trong vòng mẫu”; **3 domain**; dễ ép hình đúng | Primary `AESTHETIC` hoặc `PHYSICAL`; goal “kéo nét vòng chơi”; xóa tô trong mẫu ở MVP; ageMin giữ ≥32 |
| 5 | `LANG-07` | Goal gồm **“bao nhiêu”** từ 24m — đếm số sớm so với trọng tâm NN nhà trẻ (tên đồ, xin–cảm ơn cử chỉ) | Goal chỉ “cái gì / xin…”. “Bao nhiêu” chuyển `harder` ≥30m hoặc HĐ riêng |
| 6 | `SOC-01` | AgeMin 18 + goal “gọi tên cảm xúc” — **gán nhãn lời** có thể vượt khả năng 18–20m; dễ drill mặt cười/buồn | 18–24: chỉ bắt chước mặt + ôm; gọi tên cảm xúc từ ~24m (`harder` hoặc tách HĐ) |
| 7 | `act_sc_potty_018_10` | Goal/steps tổng thể tốt (“không ép ị”) nhưng `harder`: “Ngồi sau bữa theo khung giờ quen” **dễ hiểu thành lịch tập bô 18–24** | `harder` chỉ: ngồi chơi ngắn hơn / tự chạm bô; khung giờ sau ăn → chuyển sang HĐ ≥24–30 (`act_sc_potty_024_*`) |
| 8 | `cog_15_xep_khoi_theo_mau` | Step “So sánh ngắn”; phrase “Làm giống tháp…”; `harder` che mẫu = **kiểm tra trí nhớ** | Đổi “so sánh” → “cùng nhìn hai tháp”; harder: thêm 1 khối vui, không che mẫu ở MVP |
| 9 | `LANG-10` + `cog_07_hai_buoc_don_do` | Hai HĐ gần trùng mục tiêu 2 bước; phrase “làm đủ hai việc” = khen **kết quả kiểm tra** | Giữ một primary (`LANGUAGE` hoặc `COGNITIVE`); HĐ còn lại đổi ngữ cảnh (nếp sinh hoạt vs chơi); khen quá trình (“con nghe rồi làm lần lượt”) |
| 10 | `SOC-14` | Phrase **“Mẹ thấy con lịch sự”** — đánh giá đạo đức; goal “cảm ơn” dễ thành biểu diễn trước khách (dù step đã cấm ép) | Bỏ “lịch sự”; goal “tập dấu hiệu cảm ơn khi muốn”; phrase: “Cảm ơn con / Con muốn nói cảm ơn không?” |

**Ghi chú gần Top 10 (P1):** `phy_12_mac_quan_rong` (goal kép CDC 3y/30m); `cog_09_ghe_thap_voi_do` (mô hình “kéo ghế với đồ” — an toàn + hiểu nhầm); `AES-01` vs `phy_03` (trùng nguệch — gộp hoặc phân biệt rõ thẩm mĩ vs tinh).

---

## 4. Chủ đề còn thiếu — nên bổ sung (ý HĐ mới)

Ưu tiên **18–24m** và `SOCIAL_EMOTIONAL` / chơi cảm giác / giúp việc nhà đơn giản. *Chỉ ý tưởng — chưa full YAML; 3 mẫu phác cuối mục.*

1. **Chạm–chỉ để khoe** (proto-declarative): bé chỉ đồ thú vị, người lớn đặt tên — gắn bó + ngôn ngữ.  
2. **Tách ngắn – hợp lại** (peek quanh ghế/cửa, luôn trong tầm nhìn): giảm lo 18–24, không “luyện bỏ mẹ”.  
3. **Chơi song song với người lớn** (hai khay đồ cạnh nhau): nền tảng XH trước chia sẻ.  
4. **Đổ–rót hạt lớn / gạo vào hộp** (COGNITIVE + PHYSICAL): nhân quả, tập trung — giám sát miệng.  
5. **Giặt đồ giả / bỏ quần áo vào giỏ**: giúp việc nhà 18–24 (SELF_CARE nhẹ + giả vờ sớm).  
6. **Đi chân đất trên 2–3 bề mặt** (thảm / chiếu / sàn): cảm giác thân thể — PHYSICAL.  
7. **Xem ảnh gia đình gọi tên**: LANGUAGE + SOCIAL (mặt quen).  
8. **Tưới cây / lau lá 1–2 cái**: chăm sóc sinh vật — thẩm mĩ–XH nhà trẻ.  
9. **Bé đưa đồ khi được xin** (“cho mẹ mượn 1 giây”): tiền-chia sẻ không dùng đồng hồ cát.  
10. **Ú òa khăn** biến thể 18–24: khớp object permanence vui, không giấu lâu.  
11. **Hét / thì thầm thư viện âm** (LANGUAGE + AESTHETIC): khám phá giọng, không “ngồi im”.  
12. **Xếp nắp xoong đúng size** (1–2 nắp): phân loại chức năng — COGNITIVE 18–24.  
13. **Mang túi nhẹ 2–3 bước** giúp mẹ: tự lập sơ cấp + vận động.  
14. **Ngắm mây / lá ngoài ban công có rào** (AESTHETIC thưởng thức thiên nhiên).  
15. **Chào thú nhồi / tạm biệt ban đêm**: nếp cảm xúc trước SOC-04 cửa lớn.

### Mẫu phác (2–3) — chưa schema production

**Mẫu A — `act_soc_chi_khoe_do` (ý)**  
- domains: `[SOCIAL_EMOTIONAL, LANGUAGE]` · age: 18–26 · ~5–7 phút  
- goal: “Bé chỉ một thứ thú vị; người lớn gọi tên và vui cùng — không bắt bé nói.”  
- steps: (1) Đi chậm trong phòng. (2) Khi bé chỉ/đưa, dừng và gọi tên. (3) Lặp 3–5 lần rồi dừng khi bé quay đi.

**Mẫu B — `act_cog_do_hat_hop` (ý)**  
- domains: `[COGNITIVE, PHYSICAL]` · age: 18–28 · ~8 phút  
- goal: “Đổ–rót đồ lớn vào hộp, xem đầy/vơi.”  
- steps: Hạt nui/ống lớn hoặc khối vải; một hộp; làm mẫu 1 lần; để bé tự; dừng khi chán.  
- safety: Không hạt nhỏ; giám sát miệng liên tục.

**Mẫu C — `act_sc_bo_do_vao_gio` (ý)**  
- domains: `[SELF_CARE, COGNITIVE]` · age: 18–30 · ~5 phút  
- goal: “Cùng bỏ 2–3 món vào giỏ đồ bẩn — giúp việc vui.”  
- steps: Đưa từng món; nói “vào giỏ”; không sửa nếu sai chỗ; khen cố gắng.

---

## 5. Rủi ro sư phạm

| Rủi ro | Mức | Hiện diện trong seed / sản phẩm | Hướng giảm |
|---|---|---|---|
| **Ép phát triển / “đạt mốc tháng”** | Cao | Goal gắn CDC + số khối + “≥1 màu” | Goal = lời mời; mốc chỉ checklist quan sát S08, không nhét vào HĐ |
| **So sánh trẻ / thành tích** | Trung bình | Ít so sánh bạn bè (tốt);仍 có “làm giống mẫu”, “lịch sự”, khen “đủ hai việc” | Sửa phrase; cấm copy so sánh trên UI |
| **Ép tập bô / nếp ăn** | Trung bình–cao | Potty 18m + harder khung giờ; ăn thìa đã có “không ép” (tốt) | Siết harder potty; giữ “chưa sẵn sàng cũng được” |
| **Biến HĐ thành bài kiểm tra** | Trung bình | Màu, 2 bước, xếp theo mẫu, tô vòng | Thêm “dừng khi khó chịu”; bỏ harder kiểu quiz |
| **Trùng / nhiễu dual-domain** | Thấp–TB | Nguệch sáp đôi; 2 bước đôi; triple domain | 1 primary rõ; ≤2 domain |
| **Hiểu nhầm “chuẩn Bộ / CDC”** | Cao (uy tín) | Research + goal CDC; QA web đã disclaimer draft | UI không claim duyệt; `source_refs` ẩn với PH |
| **Thiếu trải nghiệm gắn bó 18–24** | Cao (sư phạm) | Chỉ 3 SOC visible 18–24 | Backlog mục 4 + hạ age có kiểm soát |
| **An toàn thao tác bị đọc như khuyến khích leo** | TB (chờ X2) | Ghế với đồ, cầu thang | Safety đầu steps; không hard-sell “tự nghĩ cách leo” |

Không thấy pattern `steps` khuyến khích phạt / bắt khóc; nhiều câu “không ép” — **giữ và nhân rộng**.

---

## 6. Khuyến nghị P0 / P1 / P2 cho parent

### P0 — trước khi mở rộng pilot phụ huynh thật
1. **Tăng phủ 18–24**: thêm ≥8–10 HĐ (ưu tiên `SOCIAL_EMOTIONAL` + chơi cảm giác/giúp việc); hoặc hạ `ageMin` có chọn lọc cho HĐ XH/ngôn ngữ phù hợp — mục tiêu visible 18–24 **≥50**.  
2. **Viết lại goal “chuẩn đạt”** (CDC / số khối / ≥1 màu / lịch sự) theo Top 10 §3 — ưu tiên `phy_13`, nhóm goal CDC PHYSICAL/COGNITIVE, `SOC-14`, potty `harder`.  
3. **Giữ disclaimer**: không claim “chuyên gia duyệt” / “chuẩn Bộ” trên web/Android (khớp CONTENT_QA + CONSENSUS).

### P1 — biên tập nội dung Wave 5
1. Gộp/tách trùng: nguệch sáp; cặp 2 bước LANG/COG; rút `cog_10` còn ≤2 domain.  
2. Thu hẹp span 18–36 cho HĐ có mục tiêu lệch giai đoạn (tách bản dễ/khó).  
3. Ship backlog §4 (10–15 ý); bổ sung “chế độ trường”: gộp hiển thị thẩm mĩ–XH cho đối thoại GV nhà trẻ.  
4. Rà toàn bộ `parentPhrases` theo checklist không phán xét (CONSENSUS §2.3).

### P2 — sau pilot / trước store
1. GV mầm non + (tuỳ chọn) chuyên gia phát triển trẻ **duyệt có tên** → `reviewed_by` không rỗng.  
2. Đối chiếu có chọn lọc mục tiêu với 01/VBHN-BGDĐT (không paste nguyên văn).  
3. Nghiên cứu PH VN hiểu disclaimer CDC/WHO; A/B phân bổ domain theo tuần dùng thật.

---

## Báo cáo điều phối

```
STATUS: OK
SCOPE: X1 sư phạm GDMN — căn chỉnh 6 domain vs CT; phân bổ 90 HĐ & band tuổi; top 10 sửa; thiếu chủ đề; rủi ro; P0/P1/P2. Không sửa seed/code/AGENTS/PROJECT_STATE. Không claim duyệt chính thức.
DONE: Đã đọc WAVE4_EXPERT_QA, CONSENSUS, research/01_khung_chuong_trinh, seed 90 HĐ, CONTENT_QA_20260924; skill giao-duc-mam-non (chỉ đọc). Đã ghi đủ 6 mục bắt buộc vào file output.
FILES: qa/experts/01_su_pham_gdmn.md
DEVIATIONS: Tạo rồi xóa file tạm phân tích `_tmp_x1_scan.json` trong cùng thư mục (không giữ). Không đọc toàn văn PDF VBHN — căn chỉnh dựa research A + giả định sư phạm (đã gắn nhãn).
BLOCKERS: Không blocker kỹ thuật. Thiếu duyệt người thật → mọi kết luận = kiểm thử persona, không thay reviewed_by.
ERRORS: Không.
NEXT_FOR_PARENT: (1) Chờ X2–X4 rồi X5 gộp. (2) Nếu chốt Wave 5: áp P0 — cân 18–24 + sửa Top 10 goal/steps. (3) Không nâng content_status / reviewed_by chỉ vì file X1 này.
```

---

*Hết X1 · Chỉ file này trong phạm vi giao.*
