# X6 — Senior EDU · Ký duyệt nội bộ (Wave 4 → Wave 5)

> Persona: chuyên gia cao cấp giáo dục mầm non / early childhood (lãnh đạo chuyên môn VN)  
> Workspace: `ideas/BeGau` · Ngày: 2026-09-24  
> Nguồn: `plans/WAVE4_EXPERT_QA.md`, `plans/CONSENSUS.md`, X5 `00_CONSENSUS_REVIEW.md`, X1–X4 (`01`…`04`); đối chiếu mẫu seed `content/seed/activities_mvp_draft.json`  
> **Chỉ verdict** — không sửa seed/code/AGENTS/PROJECT_STATE trong phiên này  
> **Không** claim chứng nhận Bộ GD&ĐT / bác sĩ / chuyên gia người thật

---

## VERDICT
STATUS: APPROVE_WITH_EDITS

**Ý nghĩa:** Đồng ý khung sản phẩm MVP (taxonomy 6 domain, tone “không ép”, ranh giới *Bé Gấu An Toàn*, text-first) và **cho phép Wave 5 Applier chạy không hỏi lại parent**, nhưng Applier **chỉ** thực hiện đúng danh sách `## EDITS_REQUIRED` bên dưới (+ nguyên tắc SYS đã nêu). Không mở rộng sang P1/P2 trừ dòng được ghi rõ trong EDITS.

**Lý do nghiêng APPROVE_WITH_EDITS (không REJECT):**  
- Không có blocker CRITICAL (không module cháy/điện/nước/đường; tone cốt lõi khớp CONSENSUS §2.3; 90 HĐ có `safety`; happy path web OK).  
- Có P0 **rõ ràng** về an toàn trẻ (hóc), tone phụ huynh (goal CDC/English + GDMN overclaim), và phủ **18–24** / SOCIAL_EMOTIONAL — đủ để siết trước pilot hẹp, chưa đủ để tống toàn bộ P1 vào Wave 5.

---

## EDITS_REQUIRED

*Applier Wave 5 chỉ làm các dòng dưới. Mỗi dòng = bắt buộc trừ khi ghi “tuỳ chọn”.*

### A. Seed nội dung (HĐ) — P0 bắt buộc

| # | ID | Việc cụ thể |
|---|---|---|
| 1 | **HĐ-P0-01** | Viết lại **mọi `goal`** còn CDC / English kỹ thuật / chỉ tiêu số (≥ màu, số khối, “lịch sự”) trên bề mặt S05. Nguồn CDC/WHO chỉ còn field nội bộ / `source_refs` (không hiện PH). Ưu tiên đã xác nhận seed: nhóm `phy_*`/`cog_*` (~20), đặc biệt `phy_13_xep_khoi_thap`, `phy_02` (nhóm CDC), `cog_08`, `cog_10`, `SOC-14` (bỏ phrase “lịch sự”). Goal = **lời mời chơi**, không checklist đạt mốc. |
| 2 | **HĐ-P0-02** | `act_sc_potty_018_10`: sửa `harder` — **cấm** “Ngồi sau bữa theo khung giờ quen”. Thay bằng ngồi chơi ngắn hơn / tự chạm bô; khung giờ sau ăn chỉ ở HĐ potty ≥24–30. Giữ làm quen sớm 18–24 + tone “không ép ị”. |
| 3 | **HĐ-P0-03** | Tách ngữ cảnh **`cog_07_hai_buoc_don_do` ↔ `LANG-10`**: một bên giữ chơi bóng→giỏ; bên kia đổi sang nếp (cất đồ / đưa–đặt). Khen quá trình, không “làm đủ hai việc” kiểu kiểm tra. Không gộp xóa id nếu còn dùng history. |
| 4 | **HĐ-P0-04** + **ADD** | Tăng phủ **18–24** (ưu tiên `SOCIAL_EMOTIONAL`): **được phép ADD** các HĐ mới theo danh sách tối thiểu dưới đây; giữ tổng ~90 → **≤100** có kiểm soát. Mục tiêu visible 18–24 **≥50** (kết hợp ADD + hạ `ageMin` có kiểm soát ở mục 7). **Không** chỉ hạ tuổi hàng loạt. |
| 5 | **HĐ-P0-05** | Siết **hóc**: `AES-08` — 18–23 chỉ lục lạc sẵn / easier; DIY hạt **≥24** + dán nắp + cất sau chơi; `phy_14_chuyen_nuoc_coc` mặc định **chỉ nước** (hạt ≥30 trong harder/tách); `phy_11_xau_nui_ong` — kích thước tối thiểu + **cấm** vòng đeo cổ thật (harder hiện “vòng đeo tay giả” → siết không quấn cổ). Cập nhật `materials` + `safety`. |
| 6 | **HĐ-P0-06** | Viết lại `safety` **`act_sc_safe_030_18`**: ngồi khi ăn; không chạy khi miệng còn đồ; nóng/thổi nguội — không chỉ “giọng vui / không kiểm tra điểm”. |
| 7 | **HĐ-P0-07** | `act_sc_safe_024_17`: hạ `ageMinMonths` → **18** (hoặc thêm bản 18–24 tương đương) — chống hóc miệng sớm. |

**ADD tối thiểu được phép ship Wave 5** (id gợi ý X5; Applier đặt id ổn định schema hiện có):

| # | Ý HĐ | Band | Ghi chú X6 |
|---:|---|---|---|
| 1 | `act_soc_chi_khoe_do` (chỉ–khoe) | 18–26 | SOC + LANG; không bắt nói |
| 2 | Tách ngắn – hợp lại (peek) | 18–24 | SOC; luôn trong tầm nhìn; không “luyện bỏ mẹ” |
| 3 | Chơi song song hai khay | 18–28 | SOC; trước chia sẻ |
| 4 | `act_cog_do_hat_hop` | 18–28 | COG+PHY; **hạt lớn / không hạt nhỏ**; safety miệng |
| 5 | `act_sc_bo_do_vao_gio` | 18–30 | SC+COG; giúp việc vui |
| 7 | Ảnh gia đình gọi tên | 18–30 | LANG+SOC |
| 9 | Đưa đồ khi được xin | 18–30 | SOC; tiền-chia sẻ |

*Không ship Wave 5:* backlog VN X3 (#16–25), cẩm nang đầy đủ, ADD cháy/điện/nước/đường / quạt nan sâu nếu vượt mức sinh hoạt.

### B. Web / UI — P0 bắt buộc

| # | ID | Việc cụ thể |
|---|---|---|
| 8 | **UI-P0-01** | Thư viện: nút **Xem thêm** hoặc bỏ cap 60; sửa copy “thu hẹp bộ lọc”; **empty state** khi filter = 0. |
| 9 | **UI-P0-02** | After-complete trên Today: đánh dấu / tách “đã chơi hôm nay”. |
| 10 | **UI-P0-03** | Touch: nav + chip filter ≥44–48px; CTA Chi tiết sticky/fixed; `:focus-visible` rõ. |
| 11 | **UI-P0-04** | Progress: **ship stub** 6–12 mốc neo (3 trạng thái) + banner mốc **không dismiss** (copy “mỗi bé một nhịp / không chẩn đoán / hỏi bác sĩ”). Không percentile; không `*_hyp_*` trên UI. |
| 12 | **UI-P0-05** | 1 dòng IA: Nhật ký & S03 chưa có trên web → có trên Android / sắp có. |
| 13 | **COPY-P0-01** | Welcome / UI: **không** claim “chuẩn Bộ / chuyên gia duyệt”; làm mềm dòng GDMN → “Tham khảo hướng giáo dục mầm non — nội dung nháp”. |

### C. Hệ thống / nguyên tắc (không đụng code ngoài sync)

| # | ID | Việc |
|---|---|---|
| 14 | **SYS-P0-01** | Giữ `content_status: draft_unreviewed`, `reviewed_by: []`; disclaimer banner; **không** nhét module cháy/điện/nước/đường; giữ cross-link mẫu `phy_07` / `cog_09`. |
| 15 | Sync | Sau sửa seed: sync `web/content/activities.json` + `gaucon/.../content_seed.json`; backup `activities_mvp_draft.backup.json`; chạy `node tests/content.test.mjs` + `core.test.mjs`; ghi `qa/experts/06_APPLY_LOG.md`. |

### D. Được phép / không được phép ngoài P0

| | |
|---|---|
| **Cho phép** | Toàn bộ bảng A–C ở trên; ADD tối thiểu 7 HĐ (§A #4). |
| **Không cho phép Wave 5** | P1/P2 hàng loạt (cẩm nang ≥4 = **P1**, giữ sau); tab Nhật ký đầy đủ (chỉ stub messaging P0); nâng `reviewed_by` / `internal_reviewed` / claim “đã chuyên gia duyệt”; sửa `AGENTS.md` / `PROJECT_STATE.md`. |
| **P1 tuỳ chọn nếu Applier còn bandwidth** | Chỉ **COPY-P1** nhẹ trên Progress: đổi “MVP ≥3/tuần” → gợi ý không guilt (X3/X4). **Không** bắt buộc. |

---

## 1. Phán quyết chuyên môn (trả lời điểm ký X5 §5)

| # | Điểm | Quyết định X6 |
|---|---|---|
| 1 | **Taxonomy 6 mã** | **Chấp nhận** quyết định sản phẩm (gồm `SELF_CARE`). **Không** tuyên bố = lĩnh vực Bộ. UI không claim “theo đúng CT GDMN đã duyệt”. |
| 2 | **P0 Applier** | Đồng ý **toàn bộ P0** trong `00_CONSENSUS_REVIEW` §2 — thể hiện qua EDITS_REQUIRED. Không subset bỏ hóc / goal / 18–24. |
| 3 | **ADD vs chỉ FIX** | **Cho phép ADD** có kiểm soát (≤100 HĐ) + hạ `ageMin` chọn lọc (`act_sc_safe_024_17`). Không chỉ FIX. |
| 4 | **Ưu tiên ADD** | Ship tối thiểu **#1–5, #7, #9** (X5). |
| 5 | **Potty 18–24** | **Đồng ý** giữ làm quen sớm; **cấm** `harder` khung giờ. |
| 6 | **Goal & nguồn** | CDC/WHO **cấm** hiện trên `goal` PH; chỉ nội bộ. |
| 7 | **Checklist web** | **Ship stub ngay** (UI-P0-04) — không hoãn chỉ disclaimer. |
| 8 | **Cẩm nang** | Giữ **P1** (≥4 bài) — **không** nâng P0 Wave 5. |
| 9 | **UI P0** | **Cho phép** Applier sửa web theo EDITS B. |
| 10 | **Status nội dung** | **Giữ** `draft_unreviewed` / `reviewed_by: []`. **Cấm** nâng `internal_reviewed` / `reviewed_by` vì ký X6 này. |
| 11 | **Ranh giới An Toàn** | **Xác nhận:** không ADD cháy/điện/nước/đường/cấp cứu. |

---

## 2. Phạm vi được phép sau ký

| Phạm vi | Cho phép? |
|---|---|
| `content/seed/activities_mvp_draft.json` (FIX P0 + ADD tối thiểu) | **Có** |
| Sync `web/content/activities.json`, seed Android trong APK path đã nêu WAVE4 | **Có** |
| UI web P0 (library, Today complete, touch/CTA, Progress stub mốc, Welcome copy, IA 1 dòng) | **Có** |
| Cẩm nang / tab Nhật ký đầy đủ / P1 sâu / brand P2 | **Không** (Wave 5 này) |
| `AGENTS.md`, `PROJECT_STATE.md`, hướng active | **Không** |
| Nâng `reviewed_by` / claim Bộ GD&ĐT / bác sĩ | **Cấm tuyệt đối** |

---

## 3. Ưu tiên sư phạm đã cân

1. **An toàn trẻ** — hóc (AES-08, phy_14, phy_11), safety SC lệch tone, chống hóc miệng từ 18m.  
2. **Tone phụ huynh** — bỏ goal “đạt chuẩn CDC”, bỏ GDMN overclaim, bỏ “lịch sự” / khung giờ bô.  
3. **Phủ 18–24** — ADD SOC + an toàn miệng; visible ≥50 là mục tiêu Wave 5.

Pilot hẹp **được** sau khi Applier hoàn tất EDITS + test PASS. Pilot phụ huynh **rộng** / Closed testing vẫn cần P1 (cẩm nang, Nhật ký stub, rà Top 10 còn lại) + duyệt người thật sau này.

---

## 4. Disclaimer persona AI (bắt buộc)

Tài liệu này là **ký duyệt nội bộ giả lập** bởi persona AI **X6 — Senior EDU**, phục vụ luồng Wave 4→5 của thử nghiệm phụ `ideas/BeGau`.

- **Không** phải chữ ký của giáo viên mầm non, chuyên gia tâm lý/nhi khoa, lãnh đạo chuyên môn Sở/Bộ, hay phụ huynh pilot người thật.  
- **Không** thay chứng nhận Bộ GD&ĐT, CDC/WHO, tư vấn y tế, hoặc quy trình `reviewed_by` người thật.  
- Seed/web sau Wave 5 Applier vẫn phải coi là **`draft_unreviewed`**; UI tiếp tục disclaimer “chưa chuyên gia duyệt / không chẩn đoán”.  
- **Cấm** nâng `reviewed_by` / `content_status` lên trạng thái “đã chuyên gia duyệt” chỉ vì file này.  
- Thử nghiệm phụ — **không đổi hướng active** (`traffic-count-analyzer`).

---

## Báo cáo điều phối (X6 → parent)

```
STATUS: OK
SCOPE: X6 Senior EDU — đọc WAVE4, CONSENSUS, 00_CONSENSUS_REVIEW, 01–04; đối chiếu mẫu seed P0; ghi verdict APPROVE_WITH_EDITS + EDITS_REQUIRED. Không sửa seed/code/AGENTS/PROJECT_STATE. Không claim Bộ/bác sĩ/người thật.
DONE: Đã ký STATUS APPROVE_WITH_EDITS; liệt kê EDITS A–D (toàn bộ P0 X5 + ADD #1–5/#7/#9; stub mốc UI-P0-04; cẩm nang giữ P1; cấm nâng reviewed_by); trả lời 11 điểm ký X5; disclaimer persona AI.
FILES: qa/experts/05_SENIOR_EDU_SIGN_OFF.md
DEVIATIONS: Chỉ đọc một phần seed (các id P0 + quét goal CDC) — không audit lại toàn bộ 90 HĐ từng dòng (tin X1–X4 + xác nhận mẫu).
BLOCKERS: Không. Wave 5 Applier được chạy theo EDITS_REQUIRED.
ERRORS: Không.
NEXT_FOR_PARENT: (1) Chạy Wave 5 Applier đúng EDITS_REQUIRED + SYS-P0-01. (2) Không hỏi lại parent trước khi áp. (3) Sau apply: kiểm 06_APPLY_LOG + tests; giữ draft_unreviewed. (4) P1 (cẩm nang ≥4, Nhật ký stub, Top 10 còn lại) lên backlog Closed testing.
```

---

*Hết X6 · Chỉ file này trong phạm vi giao.*
