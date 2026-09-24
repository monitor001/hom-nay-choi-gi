# Gấu Con — CONSENSUS (Wave 2 · G1)

> Consensus editor · Workspace `ideas/BeGau` · Thử nghiệm phụ  
> Nguồn: research A–D + `PRODUCT_DESIGN` (E) + `TECH_ARCHITECTURE` (F) + `SPEC_MVP` + `PIPELINE_AUTO`  
> **Không đổi hướng active** · **Không code Android** · Không sửa file Wave 1  
> Ngày: 2026-09-24

---

## 1. Tóm tắt thống nhất sản phẩm

**Gấu Con** là app Android dành cho **phụ huynh** (không phải app chơi của trẻ): mỗi ngày gợi ý **2–3 hoạt động ngắn (5–15 phút)** để bố mẹ chơi–dạy con **ngoài đời thật**, dùng đồ có sẵn trong nhà. Kèm theo: checklist mốc phát triển (quan sát, không xếp hạng), nhật ký nhẹ, nhắc nhở nếp sinh hoạt theo R1–R7, cẩm nang tình huống chăm sóc.

**MVP tuổi:** 18–36 tháng (~90 hoạt động). **Định hướng sau:** 0–6 tuổi. **KPI:** ≥ 3 hoạt động hoàn thành / gia đình / tuần — **không** dùng thời gian trong app.

**Giọng sản phẩm:** mời, chấp nhận bỏ lỡ, “mỗi bé một nhịp”; **không** phán xét, **không** chẩn đoán y tế, **không** so sánh % với trẻ khác. An toàn chuyên sâu (nước/điện/cháy/đường) thuộc *Bé Gấu An Toàn* — Gấu Con chỉ gắn `safety` theo thao tác + thói quen sinh hoạt tại nhà.

**MVP kỹ thuật:** offline-first, seed trong APK → Room; **local-only** (chưa cloud/auth). Nhắc bằng WorkManager inexact; không exact alarm; không quảng cáo / không bán dữ liệu.

---

## 2. Chốt 6 domain + ageMonths + tone

### 2.1. Sáu mã lĩnh vực (enum sản phẩm)

| Mã | Tên | Ghi chú |
|---|---|---|
| `PHYSICAL` | Thể chất | Vận động thô + tinh |
| `COGNITIVE` | Nhận thức | Quan sát, phân loại, giả vờ, nhân quả đơn giản |
| `LANGUAGE` | Ngôn ngữ | Nghe–nói, sách cùng bố mẹ |
| `SOCIAL_EMOTIONAL` | Tình cảm – kỹ năng xã hội | Gắn bó, cảm xúc, luân phiên |
| `AESTHETIC` | Thẩm mỹ | Nhạc–nhịp, tạo hình, thưởng thức |
| `SELF_CARE` | Tự lập & sinh hoạt | Ăn–ngủ–bô–an toàn sinh hoạt; gắn `ROUTINE_*` |

Đây là **quyết định sản phẩm** (5 lĩnh vực mẫu giáo Bộ GD&ĐT + `SELF_CARE`). Khi đối chiếu nhà trẻ (4 lĩnh vực gộp TC–XH–thẩm mỹ), UI/trường có thể gộp hiển thị — không đổi enum app.

**Phân bổ primary ~90 HĐ MVP (chốt từ A, G2 cân):**

| PHYSICAL | COGNITIVE | LANGUAGE | SOCIAL_EMOTIONAL | AESTHETIC | SELF_CARE |
|---:|---:|---:|---:|---:|---:|
| 16 | 14 | 16 | 14 | 12 | 18 |

Dual-domain OK (phần tử đầu = primary cho quota ngày). Khuyến nghị ≤ 2 domain / HĐ.

### 2.2. `ageMonths` (chốt)

```
ageMonths =
  (today.year - birthDate.year) * 12
  + (today.month - birthDate.month)
nếu today.day < birthDate.day → ageMonths -= 1
nếu ageMonths < 0 → 0
```

- Lọc nội dung luôn theo **tháng**, không theo “X tuổi dương lịch”.
- Band MVP: `18–24m`, `24–30m`, `30–36m` (`lower ≤ ageMonths < upper`).
- **Inclusive filter hoạt động (chốt):** `age_min_months ≤ ageMonths ≤ age_max_months`.
- Sinh non: **không** auto adjusted age; copy gợi ý hỏi bác sĩ.
- Lưu `birthDate` local; không sync cloud ở MVP.

### 2.3. Tone không phán xét (chốt copy cứng)

| Làm | Cấm |
|---|---|
| “Hôm nay mình thử…”, “Mỗi bé một nhịp riêng” | “Bạn đã bỏ lỡ…”, “Bé chậm hơn bạn bè…” |
| Quan sát → gợi ý chơi → ghi nhận cố gắng | % so với trẻ khác, badge chậm/sớm, percentile trên UI |
| “Chưa hợp cũng không sao” (S06) | Streak gây tội lỗi, leaderboard |
| “Nếu lo lắng, nên hỏi bác sĩ” (trung tính) | Tự kết luận chậm phát triển / tự kỷ / ADHD… |

Checklist mốc: chỉ 3 trạng thái **Đã làm được / Đang tập / Chưa**. Phần thưởng = tương tác thật (ôm, khen cụ thể) — không coin/huy hiệu gây nghiện.

---

## 3. UX IA P0 (từ E) + stack kỹ thuật P0 (từ F)

### 3.1. IA / UX P0 — local-only MVP

**Bottom nav 5 tab:** Hôm nay · Thư viện · Phát triển · Nhật ký · Thêm.

| Mã | Màn P0 | Feature |
|---|---|---|
| S01–S02 | Chào mừng + hồ sơ bé | F1 |
| S03 | Nhắc + xin `POST_NOTIFICATIONS` (sau giải thích; có “Để sau”) | F8 |
| S04–S06 | Hôm nay → Chi tiết → Phản hồi 😊😐😕 | F2–F4 |
| S07 | Thư viện lọc | F2/F3 |
| S08 | Mốc 6 lĩnh vực + banner “mỗi bé một nhịp” | F5 |
| S09 | Nhật ký | F6 |
| S10 | Cẩm nang | F7 |
| S11–S12 | Quản lý / sửa nhắc | F8 |
| S13 | Lịch sức khỏe do PH nhập (không tự tạo mũi tiêm) | F8 |
| S14 | Cài đặt, cỡ chữ, xuất/xóa | F9 |

**Onboarding quyền:** không xin notif ở S01; từ chối vẫn dùng app; banner S04 ≤ 1 lần/tuần.

**Daily Picker (khớp E + F):** lọc tuổi → loại HĐ 14 ngày → ưu tiên domain ít làm 7 ngày + mốc Đang tập → giảm trọng số tương tự sau 😕 → 3 HĐ, ≥ 2 domain, tổng ≤ 30 phút → seed ổn định `childId|date` + persist `DailyPick`.

**DoD nội dung trước production:** `reviewed_by` không rỗng; đọc goal+materials+steps ≤ 30 giây. Nháp Wave 1/2 giữ `reviewed_by: []` / `content_status: draft_unreviewed`.

### 3.2. Stack kỹ thuật P0 — **local-only** (cloud chưa chốt)

| Hạng mục | Chốt MVP |
|---|---|
| Ngôn ngữ / UI | Kotlin 2.x, Jetpack Compose + Material 3 |
| Kiến trúc | MVVM + Clean, multi-module (`app` / `core:*` / `domain` / `feature:*` / `content-seed`) |
| DI / DB / prefs | Hilt, Room, DataStore |
| Nhắc | WorkManager `OneTimeWorkRequest` + delay; channels `ch_activity` / `ch_routine` / `ch_health` / `ch_growth`; **không** `SCHEDULE_EXACT_ALARM` / `USE_EXACT_ALARM` |
| Nội dung | Seed JSON trong APK → Room; `content_version`; soft-delete `isRetired` |
| Backend / auth | **Hoãn** — abstract `ContentApi` / `CloudBackupGateway`; **không bind** Supabase/Firebase đến khi parent chốt |
| Sync cloud | Tắt mặc định; opt-in = P1 |
| Analytics | Crashlytics (scrub PII) tùy chọn Closed testing; KPI chính đếm từ Room |
| minSdk / targetSdk | 26 / 36 |
| Privacy | Nickname đủ; không location/contacts/AD_ID; xuất ZIP + xóa local; không ads SDK |

Reminder R1–R7: giữ nguyên SPEC/F (quiet hours, max 2 suggested/day, auto-degrade soft-prompt, skip nếu đã chơi ≥1, batch ±10′, copy mời, one-tap tắt loại). `ROUTINE_*` / `HEALTH_CHECKUP` mặc định **tắt** — PH tự bật.

---

## 4. XUNG ĐỘT giữa A–F và CÁCH XỬ đã chọn

| # | Xung đột | Nguồn | Cách xử đã chọn |
|---|---|---|---|
| 1 | Phân bổ HĐ: A = 16/14/16/14/12/18; B≈15+15; C=15+15+11; D=18 | A vs B/C/D | **Giữ tỷ lệ A.** G2 cắt/ghép dual để đủ 90; D không vượt quá 18 primary `SELF_CARE`. |
| 2 | Schema tuổi/ID lệch (`age_min` / `age_range` / `age_min_months`; `phy_01` / `LANG-01` / `act_sc_*`) | B/C/D | **Schema JSON thống nhất** (G2): `age_min_months`, `age_max_months`, `domains[]`, `duration_minutes`, `reviewed_by`. ID ổn định dạng `act_{domainShort}_{nnn}` (G2 quy ước trong CURATION_LOG). |
| 3 | Trùng chủ đề: xúc thìa, mặc quần, “nóng—không chạm”, nguệch sáp | B ↔ D; B ↔ C | **Primary theo owner:** nếp ăn/ngủ/bô/an toàn sinh hoạt → `SELF_CARE` (D); khéo tay/thăng bằng thuần → `PHYSICAL` (B); thẩm mỹ dấu vết → `AESTHETIC` (C). Bản dual giữ 1 bản primary; G2 loại bản trùng mục tiêu. |
| 4 | Ranh giới an toàn vs *Bé Gấu An Toàn* | B/C/D + la bàn | Chỉ `safety` 1–2 câu + `SC_SAFE_DAILY`. Không module cháy/điện/nước/đường/cấp cứu trong seed Gấu Con. |
| 5 | Checklist giả định `*_hyp_*` có hiện UI không? | B mở | **MVP S08:** chỉ mốc gắn nguồn CDC/WHO/CT hoặc checklist D đã neo. Mục `hyp_*` = nội bộ Picker / P1. |
| 6 | Video 90 HĐ vs text-first | E §9 | **Text-first.** Video optional, không chặn ship; không autoplay liên tục. |
| 7 | Cỡ chữ mặc định Vừa vs Lớn | E §9 | **Mặc định Vừa**; nếu font scale hệ thống ≥ 1.3 → đề xuất/auto **Lớn**. Ông bà đổi ở S14. |
| 8 | “Tương tự” sau 😕 | E §9 + F | MVP: cùng `domains[0]` (+ cùng `materials` tag nếu G2 có). Không cohort. |
| 9 | Backend Supabase vs Firebase | F §9 | **Local-only MVP.** Chốt vendor = quyết định parent trước cloud P1. |
| 10 | `SPEC_MVP.md` trên disk rút gọn vs paste 1.0 E/F dùng | SPEC vs E/F | CONSENSUS + TECH/PRODUCT là nguồn vận hành Wave 3; không block vì file SPEC rút gọn. Parent có thể đồng bộ SPEC sau. |
| 11 | “Đổi gợi ý” / thay thẻ từ Thư viện | E §9 | Đổi gợi ý: **không giới hạn cứng** nhưng không trùng id đã hiện hôm nay nếu còn ứng viên. Cho **ghim 1 HĐ từ Thư viện** thay 1 thẻ (P0 nhẹ). |
| 12 | S03 “Để sau” còn bật `DAILY_ACTIVITY` trong app? | E §9 | **Có** — lịch trong app bật mặc định 19:30; chỉ thiếu system notification đến khi PH cấp quyền. |
| 13 | Dark mode bắt buộc sprint MVP? | E §9 / SPEC DoD | **P1.** MVP light + a11y contrast; theme tối không chặn GO Wave 3. |
| 14 | Ngôn ngữ xưng hô strings | E §9 | Giọng **“bạn”** + tên gọi bé khi có (“Chào {tên}…”). Không “bạn đã bỏ lỡ”. |
| 15 | Cẩm nang D ~9 vs F7 ~20 bài | D vs SPEC | P0 ship **≥ 4** bài sinh hoạt D ưu tiên (ăn vạ, biếng ăn, khó ngủ, tập bô) + bổ sung dần đủ ~20; không block scaffold. |

---

## 5. QUYẾT ĐỊNH CHỐT

*Parent-ready. **Mặc định áp dụng nếu parent im lặng** (theo PIPELINE_AUTO GO-WITH-RISKS).*

1. **Sản phẩm MVP:** app phụ huynh Android; 18–36 tháng; ~90 HĐ; 6 domain như §2; value prop như §1.
2. **Tone & y tế:** không phán xét; không chẩn đoán; không percentile UI; CTA “hỏi bác sĩ” trung tính khi phù hợp.
3. **ageMonths + filter:** công thức §2.2; `age_min_months ≤ age ≤ age_max_months`.
4. **IA P0:** 5 tab + S01–S14 như §3.1; vòng lõi S04→S05→S06.
5. **Stack P0:** Kotlin/Compose/Hilt/Room/DataStore/WorkManager multi-module như §3.2.
6. **Backend:** **local-only**; không auth/cloud bắt buộc Closed testing nội bộ; abstract API để P1.
7. **Nhắc:** R1–R7; không exact alarm; routine mặc định tắt; `DAILY_ACTIVITY` mặc định 19:30 (app-side).
8. **Nội dung:** tỷ lệ 16/14/16/14/12/18; `reviewed_by: []` đến khi chuyên gia duyệt; UI không claim “đã duyệt chuyên gia” / “chuẩn Bộ”.
9. **Video:** optional; text-first.
10. **Font:** mặc định Vừa; auto Lớn nếu hệ thống ≥ 1.3.
11. **Analytics:** Room = nguồn KPI; Crashlytics optional (scrub PII); không ads/attribution ID.
12. **Đổi gợi ý + ghim từ Thư viện:** như §4 mục 11–12.
13. **Xưng hô strings:** “bạn” + tên bé.
14. **HEALTH:** chỉ lịch PH nhập + bảng TCMR tham khảo; không engine tiêm tự động.
15. **Wave 3:** được phép scaffold theo `BUILD_READY.md` khi cổng Wave 2 đạt; **không** đẩy Play production; **không** đổi hướng active / `AGENTS.md` / `PROJECT_STATE.md`.

---

## 6. LOẠI BỎ / HOÃN (P1 / P2)

### P1 (giai đoạn 2) — hoãn
- Cloud sync / nhiều thiết bị / nhiều người chăm + auth (Supabase hoặc Firebase — parent chốt)
- Thư viện nội dung **cho bé** + `SCREEN_TIME` hẹn giờ
- Premium / Play Billing
- Báo cáo tuần (nếu có: chỉ đếm tích cực, không guilt)
- Cá nhân hóa giờ nhắc tự động
- Đủ nội dung 0–6 tuổi; video đủ cover
- Dark mode; OCR caption nhật ký
- Mục checklist `hyp_*` trên UI; chế độ “gộp lĩnh vực trường”
- Event analytics pipeline đầy đủ; sync content CDN định kỳ

### P2 (giai đoạn 3) — hoãn
- Lộ trình AI
- Bản trường mầm non / B2B
- Song ngữ Việt–Anh
- COPPA/GDPR nếu phát hành ngoài VN (cần counsel)

### Loại khỏi roadmap MVP (không làm)
- Social feed, streak “đừng phá chuỗi”, leaderboard, coin
- Chẩn đoán / điểm phát triển / so sánh chuẩn trên UI
- Quảng cáo, bán dữ liệu, exact alarm “bypass” OEM
- Module an toàn chuyên sâu trùng *Bé Gấu An Toàn*

---

## 7. Điều kiện GO Wave 3

Wave 3 (scaffold Android tối thiểu theo `PIPELINE_AUTO`) được **GO** khi:

| # | Điều kiện | Ghi chú |
|---|---|---|
| 1 | File này có mục **§5 QUYẾT ĐỊNH CHỐT** | ✅ |
| 2 | G2: `content/seed/activities_mvp_draft.json` parse được, ~90 HĐ, enum 6 domain, `content_status: draft_unreviewed` | Song song Wave 2 |
| 3 | G3: `plans/BUILD_READY.md` = **GO** hoặc **GO-WITH-RISKS** | Không có blocker CRITICAL |
| 4 | Parent không override ngược §5 trong cửa sổ đọc ngắn (hoặc im lặng = chấp nhận mặc định) | PIPELINE_AUTO |
| 5 | Phạm vi Wave 3 chỉ scaffold: multi-module, seed load, S01–S02, S04 stub picker, S05 text, Room cơ bản, `DAILY_ACTIVITY` R1–R4 tối thiểu, README + unit test picker/next-fire nếu có code | Không Play production |

**NO-GO nếu:** thiếu CONSENSUS §5; seed không parse; BUILD_READY = NO-GO; hoặc parent cấm code / đổi hướng active.

**Rủi ro chấp nhận khi GO-WITH-RISKS:** OEM giết Worker; nội dung chưa chuyên gia duyệt (giữ disclaimer); backend chưa chốt; SPEC disk rút gọn.

---

## 8. Disclaimer

Nội dung hoạt động, checklist mốc, cẩm nang và copy nhắc trong research Wave 1 và seed Wave 2 là **bản nháp sư phạm / sản phẩm**.

- **Chưa** được giáo viên mầm non, chuyên gia tâm lý/nhi khoa hay phụ huynh pilot duyệt chính thức.
- **Không** phải chẩn đoán y tế; **không** thay khám bác sĩ hay chương trình Bộ GD&ĐT nguyên văn.
- Mọi HĐ giữ `reviewed_by: []` / `draft_unreviewed` cho đến khi có duyệt — **không** xuất bản store với claim “chuẩn Bộ”, “đạt WHO/CDC”, hoặc “đã chuyên gia duyệt”.
- Thử nghiệm phụ trong `ideas/BeGau` — **không đổi hướng active** của dự án AI Kiem Tien (`traffic-count-analyzer`).

---

*Hết CONSENSUS.md · G1 Consensus editor · Chỉ file này được ghi trong phạm vi task.*
