# R3 — Vòng nhiệm vụ, thưởng ngày & streak mềm

> Persona: **Expert R3** · Mission / daily reward / streak  
> App: **Hôm nay chơi gì?** · Parent companion 18–36 tháng  
> Charter: `qa/experts/rewards/00_CHARTER.md`  
> Neo: `plans/CONSENSUS.md` (tone mềm, không «bạn bỏ lỡ»; KPI ≥ 3 HĐ/tuần)  
> Hiện trạng code: `ActivityLog` complete + feedback S06; reminders WorkManager (R1–R7) — **không** có GX/streak trong app.  
> Ngày: 2026-09-24 · **Chỉ kế hoạch** — không code.

---

```
STATUS: OK
SCOPE: Daily login vs play-complete; GX per complete (+ soft feedback); streak ngày có ≥1 HĐ thật; weekly mission = KPI 3 HĐ/tuần; anti-abuse; caps ngày/tuần; hook notif celebrate (không nag mất streak); đề xuất earn rate cho R1 refine giá.
DONE: Đọc charter + CONSENSUS + ActivityLog/Feedback/complete path; thiết kế bảng earn, streak, mission, cap, anti-abuse, copy & notif; lưu file này.
FILES: qa/experts/rewards/03_R3_missions.md
DEVIATIONS: CONSENSUS §6 loại «coin / streak đừng phá chuỗi» khỏi MVP sản phẩm — R3 đề xuất lớp thưởng **mềm, opt-in P1**, không loss-aversion. Số GX là đề xuất R3; R1 chốt tên đơn vị & bảng giá đổi thưởng.
BLOCKERS: Chờ R1 (giá vật phẩm) + R2 (guardrail nuôi dưỡng / copy) để R4 hợp nhất.
ERRORS: Không.
NEXT_FOR_PARENT: R4 đọc R1+R2+R3 → `00_CONSENSUS_REWARDS.md`; quyết định phase (MVP stub ledger vs P1 bật UI).
```

---

## Verdict (charter)

**PASS** — thiết kế ưu tiên **play-complete** (login ≈ 0), căn weekly mission vào **KPI ≥ 3 HĐ/tuần**, streak chỉ thưởng mềm + copy chấp nhận bỏ lỡ, anti-abuse + cap chống farm. Không xung đột tone CONSENSUS nếu **không** ship copy «mất streak / đừng phá chuỗi».

**Ghi chú phase:** Lớp GX/mission = **Rewards P1** (hoặc MVP ledger ẩn đo A/B nội bộ). Wave MVP hiện tại giữ vòng S04→S06 + `ActivityLog` không coin.

---

## 1. Nguyên tắc thiết kế

| # | Nguyên tắc | Hệ quả |
|---|---|---|
| 1 | Thưởng **hành vi chơi thật** đã ghi trong `ActivityLog`, không thưởng mở app | Login / mở tab Hôm nay = **0 GX** (hoặc ≤ 1 GX one-shot/ngày nếu R1 muốn «chào nhẹ» — R3 khuyến nghị **0**) |
| 2 | KPI sản phẩm = đếm complete, không time-in-app | Weekly mission = **≥ 3 complete hợp lệ / tuần lịch** |
| 3 | Soft > addictive | Không biến mất số dư; không đếm ngược «sắp mất streak»; không leaderboard |
| 4 | PH là người chơi hệ thống thưởng | UI thưởng trên màn PH; trẻ không thấy máy slot |
| 5 | Offline / local clock | Earn dựa Room + anti clock-skew; không server anti-cheat ở MVP/P1 |

---

## 2. Định nghĩa sự kiện hợp lệ (source of truth)

**Complete hợp lệ (earn-eligible)** khi thỏa **tất cả**:

| Điều kiện | Chi tiết |
|---|---|
| Có `ActivityLog` mới | `insert` sau CTA «Đã chơi xong» (S05/S06) |
| `childId` = bé đang active | Không cộng chéo hồ sơ |
| Activity tồn tại & chưa retired | `activityId` còn trong catalog |
| Không spam cùng id trong ngày | Cùng `activityId` + cùng ngày lịch local: **chỉ lần 1 được GX** (lần sau vẫn log nhật ký nếu product cho phép, nhưng **0 GX**) |
| Cooldown thời gian | ≥ **8 phút** wall-clock kể từ lần earn trước (khớp HĐ 5–15′) — dùng `elapsedRealtime` cho cooldown; xem §6 |
| Trong daily / weekly cap | Nếu đã chạm cap → log OK, toast mềm «Hôm nay mình đã đủ nhịp thưởng rồi» — **không** chặn complete |
| Clock hợp lệ | Không đang trong trạng thái freeze do clock rollback (§6) |

**Feedback** (`LIKED` / `NEUTRAL` / `NOT_FIT`): optional, sau complete. Bonus feedback **một lần / log**, mọi lựa chọn đều được (kể cả 😕) — thưởng sự thật, không phạt «không hợp».

---

## 3. Bảng earn đề xuất (Gấu Xu — GX)

> Mục tiêu cảm nhận: gia đình đạt KPI (~3 HĐ/tuần) → đủ **đồ nhỏ / đãi nhẹ ~1 lần/tuần**; duy trì đều ~1 HĐ/ngày hoặc weekly + streak mềm → đủ **đồ chơi trung bình ~1 lần/tháng**. R1 refine giá đổi thưởng quanh các mốc dưới.

### 3.1. Giả định giá neo (để R1 căn)

| Mốc đổi thưởng (ví dụ catalog) | GX đề xuất (neo) |
|---|---:|
| Đãi nhỏ / sticker / «thêm 1 câu chuyện» (PH chọn ngoài đời) | **45–60** |
| Đồ chơi / trải nghiệm trung bình | **180–240** |

### 3.2. Earn rates

| Mã sự kiện | Điều kiện | GX | Ghi chú |
|---|---|---:|---|
| `EARN_LOGIN` | Mở app / session ngày | **0** | Không dùng làm động lực chính |
| `EARN_COMPLETE` | Complete hợp lệ | **12** | Trụ cột earn |
| `EARN_FEEDBACK` | Gắn feedback lần đầu trên cùng log | **+3** | Soft; không bắt buộc |
| `EARN_STREAK_DAY` | Ngày có ≥1 complete hợp lệ **và** nối streak (§4) | **+4** | Cộng 1 lần/ngày streak-qualified |
| `EARN_STREAK_M3` | Chạm mốc streak **3** ngày | **+8** | One-shot khi đạt mốc |
| `EARN_STREAK_M7` | Chạm mốc **7** | **+15** | One-shot |
| `EARN_STREAK_M14` | Chạm mốc **14** | **+25** | One-shot; sau đó không escalate gây nghiện |
| `EARN_WEEKLY_MISSION` | Tuần lịch có ≥ **3** complete hợp lệ (distinct days **không** bắt buộc) | **+25** | Khớp SPEC KPI; 1 lần/tuần ISO hoặc tuần local Mon–Sun (chốt: **Mon–Sun local**) |

**Không earn (MVP/P1):** đổi gợi ý, mở thư viện, xem cẩm nang, tick mốc phát triển, bật/tắt nhắc, ghi nhật ký thuần text (có thể P2 +2 GX soft — ngoài scope R3).

### 3.3. Mô phỏng thu nhập gia đình

| Nhịp chơi | /tuần (ước) | /tháng (~4.3 tuần) | Đủ mốc? |
|---|---:|---:|---|
| **KPI tối thiểu** — đúng 3 complete, có feedback, không streak dài | 3×12 + 3×3 + 25 = **70** | ~300 | Đãi nhỏ ≥1/tuần; đồ TB ~1/tháng |
| **Nhịp nhẹ** — 5 complete/tuần, streak ~3–5 | ~5×12 + 5×3 + ~4×4 + 8 + 25 ≈ **129** | ~550 | Thoải mái đãi nhỏ; đồ TB dễ |
| **Nhịp đều** — ~1 complete/ngày, feedback, streak 7+ | chạm **daily/weekly cap** (§5) → ~**110–150**/tuần | ~500–640 | Cap chặn farm; vẫn đủ đồ TB |

---

## 4. Streak — chuỗi ngày có ≥1 hoạt động thật

### 4.1. Quy tắc đếm

| Mục | Quy tắc |
|---|---|
| Đơn vị ngày | Ngày lịch **local** (`ZoneId.systemDefault`) |
| Ngày «có streak» | ≥ **1** complete **hợp lệ** trong ngày |
| Nối chuỗi | Ngày hôm qua (local) cũng là ngày có streak, hoặc hôm nay là ngày đầu sau reset |
| Grace | **Không** grace 36h phức tạp ở P1 — bỏ 1 ngày = reset về 0 **im lặng** |
| Login không đếm | Chỉ complete |

### 4.2. Thưởng streak (mềm)

- Mỗi ngày streak-qualified: `EARN_STREAK_DAY` (+4) — vẫn nằm trong **daily cap**.
- Mốc 3 / 7 / 14: bonus one-shot (§3.2).
- **Không** có mốc 30+ với GX lớn (tránh loss-aversion dài hạn).
- Sau mốc 14: chỉ còn +4/ngày trong cap — không «MUST giữ lửa».

### 4.3. Khi chuỗi đứt — copy & UX (bắt buộc)

| Được | Cấm |
|---|---|
| Ẩn số streak cũ hoặc hiện «Nhịp chơi: bắt đầu lại hôm nay» | «Bạn đã mất streak», «Còn X giờ trước khi mất», «Đừng phá chuỗi» |
| Nút streak trên UI là **nhẹ** (text phụ), không đỏ / countdown | Badge đỏ, progress bar sắp cháy |
| Notif: **không** gửi khi đứt | Push «streak của bạn đã mất» |
| Weekly report (P1): chỉ đếm tích cực tuần này | So sánh «tuần trước bạn kém hơn» |

Tone neo CONSENSUS: *«Mỗi bé một nhịp riêng»*, *«Hôm nay mình thử lại nhé»*.

---

## 5. Caps

| Cap | Giá trị đề xuất | Cách áp |
|---|---:|---|
| **Daily max GX** | **40** | Sum mọi `EARN_*` trong ngày local; vượt → sự kiện sau = 0 GX (log vẫn ghi) |
| **Weekly max GX** | **140** | Sum Mon–Sun local; bảo vệ farm tuần |
| Complete hợp lệ / ngày (pay) | **3** | Trùng daily picker 2–3 HĐ; lần 4+ trong ngày = 0 GX |
| Cùng `activityId` / ngày | **1** lần có GX | Anti same-activity spam |
| Weekly mission payout | **1** / tuần | Dù complete >> 3 |

**Ví dụ đầy daily 40:** 3×(12+3) = 45 → cap cắt còn 40 (hoặc thiết kế: complete 12×3=36 + feedback chỉ 2 lần đầu + streak day trong cap). R4/R1 có thể chỉnh «feedback sau cap = 0» — R3 ưu tiên **hard cap tổng**.

---

## 6. Anti-abuse

| Vector | Mitigation |
|---|---|
| **Complete spam** (bấm liên tục) | Cooldown **8 phút** giữa hai lần earn (`elapsedRealtime`); max **3** pay/ngày |
| **Same activity spam** | 1 GX / `activityId` / ngày local; UI có thể disable «nhận thưởng» nhưng vẫn cho ghi chú |
| **Đổi gợi ý rồi complete clone** | Earn theo `activityId` không theo slot picker — đổi thẻ không nhân GX nếu trùng id trong ngày |
| **Clock change / lùi ngày** | Nếu `wallClock` nhảy **lùi > 15 phút** so với watermark đã lưu: **freeze earn** đến khi wall ≥ watermark; cooldown vẫn bám `elapsedRealtime`. Log complete vẫn cho phép (KPI trung thực) nhưng `gxGranted=0` khi freeze |
| **Tăng tốc ngày** (tua tới để farm streak) | Streak chỉ tính khi ngày local tăng tự nhiên; nếu phát hiện nhảy **tới > 36h** một lần: không backfill streak cho các ngày trống; chỉ xét ngày hiện tại |
| **Nhiều hồ sơ bé** | Cap & streak **theo `childId`**; không cộng dồn để farm đổi thưởng hộ gia đình (số dư GX: R1 chốt — R3 khuyến nghị **1 ví / thiết bị / gia đình** gắn child active để đơn giản P1) |
| **Xóa app / clear data** | Chấp nhận mất sổ local (offline MVP); không anti-fraud nặng |

**Ledger đề xuất (logic, không schema code):** mỗi lần xét earn ghi `{logId, eventCode, gx, dayIso, weekIso, granted:bool, reason?}`.

---

## 7. Weekly mission (khớp SPEC KPI)

| Thuộc tính | Giá trị |
|---|---|
| Tên gợi ý UI | «Nhịp tuần» / «3 lần chơi tuần này» — **không** «nhiệm vụ bắt buộc» |
| Mục tiêu | **≥ 3** complete hợp lệ trong tuần Mon–Sun |
| Tiến độ | `min(count, 3) / 3` — chỉ số tích cực |
| Thưởng | `EARN_WEEKLY_MISSION` = **+25 GX** khi đạt, 1 lần |
| Khi chưa đạt cuối tuần | **Không** push «bạn chưa hoàn thành»; có thể soft in-app trên S04: «Tuần này mình đã chơi X lần — mỗi nhịp đều đáng quý» |
| Quan hệ streak | Độc lập; tuần đạt mission không đòi streak |

---

## 8. Notification hooks

Kênh hiện có: `ch_activity` / `ch_routine` / … (CONSENSUS). Đề xuất:

| Hook | Khi nào | Channel | Bắt buộc? |
|---|---|---|---|
| Celebrate weekly | Đạt 3/3 trong tuần (in-app ngay; push **opt-in**) | `ch_activity` hoặc `ch_reward` (P1) | Không — mặc định in-app only |
| Celebrate streak mốc | Đạt 3 / 7 / 14 (in-app) | in-app snackbar / soft sheet | Push tắt mặc định |
| Daily activity reminder | Đã có R1–R4; **skip nếu đã ≥1 complete hôm nay** | `ch_activity` | Giữ như SPEC — **không** đổi thành «giữ streak» |
| **Mất streak** | — | — | **CẤM** |
| Cap đạt | In-app one-liner | — | Không push |

Copy celebrate gợi ý (R2 tinh chỉnh):

- «Tuần này nhà mình đã có 3 lần chơi cùng nhau — đáng quý lắm.»
- «3 ngày chơi liền — nhịp đẹp. Mai có thể nghỉ cũng không sao.»

---

## 9. Mapping UI / vòng hiện có (không code)

| Màn / sự kiện | Hành vi thưởng |
|---|---|
| S04 Hôm nay | Hiện tiến độ Nhịp tuần `X/3`; streak text phụ optional |
| S05 Complete | Trigger `EARN_COMPLETE` (+ queue streak/weekly) |
| S06 Feedback | Trigger `EARN_FEEDBACK` nếu có |
| Nhật ký S09 | Chỉ đọc `ActivityLog` — không earn thêm |
| Reminder Worker | Không cộng GX khi hiện notif |

---

## 10. Phase đề xuất cho R4

| Phase | Phạm vi R3 |
|---|---|
| **MVP hiện tại** | Giữ complete + reminder; **không** UI GX/streak |
| **Rewards P1** | Ledger local + Nhịp tuần + cap + anti-abuse; streak mềm + celebrate in-app |
| **P2** | Push celebrate opt-in; báo cáo tuần chỉ số dương; ví dụ catalog đổi thưởng (R1) |

---

## 11. Open points → R1 / R2 / R4

| # | Câu hỏi | Owner |
|---|---|---|
| 1 | Neo giá đãi nhỏ 45–60 / đồ TB 180–240 có khớp catalog không? | R1 |
| 2 | Copy celebrate / «bắt đầu lại nhịp» có đủ soft & nuôi dưỡng? | R2 |
| 3 | Ví GX theo device hay theo child? | R1 + R4 |
| 4 | Bật UI thưởng ngay Closed testing hay chỉ ledger ẩn? | R4 + parent |
| 5 | Feedback 😕 có +3 hay 0? (R3: **+3** trung thực) | R2 xác nhận |

---

## 12. Tóm tắt số chốt (để R4 copy)

| Tham số | Giá trị R3 |
|---|---|
| Login GX | **0** |
| Complete hợp lệ | **12** |
| Feedback soft | **+3** |
| Streak day | **+4** |
| Streak mốc 3 / 7 / 14 | **+8 / +15 / +25** |
| Weekly mission (≥3) | **+25** |
| Daily cap | **40 GX** · max **3** pay complete/ngày |
| Weekly cap | **140 GX** |
| Cooldown | **8 phút** |
| Same activity / ngày | **1** GX |
| Shame / mất streak notif | **Cấm** |

---

*Hết 03_R3_missions.md · Expert R3 · Chỉ file này trong phạm vi task.*
