# O1 — Activity complete → GX earn (vận hành)

> Expert O1 · QA luồng Hoàn thành → ledger → màn «Đã chơi xong»  
> Ngày: 2026-09-24 · **Chỉ đọc code — không sửa production**  
> Charter: `qa/experts/gx/00_CHARTER.md` · Rates: `qa/experts/rewards/00_CONSENSUS_REWARDS.md`

---

## STATUS

```
STATUS: WARN
SCOPE: Tap Hoàn thành → ActivityLog + COMPLETE earn (+15?) → UI celebration (earnMessage/balance) → Về Hôm nay
DONE:
  - Đọc ActivityViewModel.complete / ActivityDetailScreen (completed branch)
  - Đọc GxRewardsUseCase.onActivityCompleted + GxRates / GxKinds
  - Đối chiếu rates CONSENSUS §2
  - Trace MainActivity onCompleted = popBackStack(Today) chỉ từ nút «Về Hôm nay»
FILES: qa/experts/gx/01_O1_complete_earn.md
DEVIATIONS: Không sửa production; so với X2 cũ — onDone-early đã được vá trong code hiện tại
BLOCKERS: Không (static QA; chưa chạy APK thiết bị)
ERRORS: Không P0 trên happy path; P1 silent deny + CTA khi rewards tắt
NEXT_FOR_PARENT: Apply EDITS_REQUIRED (message cooldown/cap/duplicate; CTA khi rewardsEnabled=false); O4 consensus
VERDICT: WARN — happy path earn+celebration OK; cooldown/cap/duplicate vẫn im lặng
```

---

## 1. Happy path (code trace)

| Bước | Code | Kết quả mong đợi |
|---|---|---|
| 1. Tap «Hoàn thành · nhận Gấu Xu» | `ActivityDetailScreen` → `viewModel.complete()` (không gọi `onCompleted`) | `completing=true`, nút disable |
| 2. Ghi buổi chơi | `activityLogRepository.insert(ActivityLog…)` | Log luôn được ghi (kể cả khi không earn) |
| 3. Earn | `prefs.rewardsEnabled()` → `gxRewards.onActivityCompleted` | Eligible: insert ledger `kind=COMPLETE`, `amount=room` (thường **15**) |
| 4. UI celebration | `completed=true`, `earnMessage`, `gxAwarded`, `gxBalance` | Màn «Đã chơi xong!» + message + (nếu awarded>0) «Số dư: N GX» |
| 5. Về Hôm nay | `Button(onClick = onCompleted)` → `popBackStack(Routes.Today)` | Pop **sau** celebration, không tự pop |

**Rates khớp CONSENSUS (GxRates):** COMPLETE=15 · STREAK_DAY=4 · STREAK_7=15 · WEEK_KPI=25 · DAILY_CAP=45 · WEEKLY_CAP=150 · MAX_COMPLETE=3/ngày · COOLDOWN=8 phút.

Message earn thành công ví dụ: `"+15 GX hoàn thành HĐ"` (+ streak/KPI nếu đủ điều kiện, nối bằng ` · `).

---

## 2. Check bugs (yêu cầu O1)

| Check | Kết quả | Chi tiết |
|---|---|---|
| **onDone quá sớm** (pop trước celebration) | **OK (đã vá)** | `complete()` **không** gọi `onCompleted`. Celebration khi `state.completed`; pop chỉ từ nút «Về Hôm nay». (X2 cũ mô tả `complete { onDone() }` — không còn đúng code hiện tại.) |
| **rewardsEnabled=false bỏ earn** | **Đúng hành vi gate** | `EarnResult(0, null)` → message fallback «Đã ghi nhận buổi chơi.»; `gxAwarded=0` → ẩn số dư. **Log HĐ vẫn insert.** CTA nút vẫn hứa «nhận Gấu Xu» → lệch UX (P1). |
| **Cooldown / caps im lặng** | **BUG P1** | Chỉ `completesToday >= MAX` có copy mềm. Cooldown, trùng `activityId`/ngày, `remainingCap==0` → `message=null` → UI chỉ «Đã ghi nhận buổi chơi.» không giải thích 0 GX. |
| **EarnResult.awarded vs message** | **OK cơ bản** | `awarded>0` → `notes.joinToString`; VM fallback `"+N GX"` nếu message null. `awarded==0` + null message → generic. Balance chỉ hiện khi `gxAwarded > 0` — đúng, nhưng thiếu lý do 0. |

---

## 3. Issue table

| ID | Sev | Issue | Evidence |
|---|---|---|---|
| O1-01 | **P1** | Cooldown / trùng HĐ / day·week cap: deny earn **không có message** riêng | `GxRewardsUseCase` L104–110: `else -> null` cho mọi nhánh không phải max-complete; VM L93 fallback generic |
| O1-02 | **P1** | `rewardsEnabled=false`: nút vẫn «Hoàn thành · nhận Gấu Xu» dù không earn | `ActivityDetailScreen` L165; gate VM L83–87 |
| O1-03 | **P2** | `EARN_FEEDBACK` (+3 CONSENSUS) chưa gắn luồng complete | `GxRates.FEEDBACK=3` có; `onActivityCompleted` không insert FEEDBACK — ngoài happy-path O1 nhưng lệch bảng kiếm |
| — | — | **Không P0** trên happy path (rewards bật, lần earn đầu, ngoài cooldown) | Ledger COMPLETE + celebration + pop thủ công |

---

## 4. EDITS_REQUIRED (concrete)

1. **`GxRewardsUseCase.onActivityCompleted`** — khi `awarded==0`, set `message` theo lý do (ưu tiên thứ tự):
   - trùng COMPLETE cùng `activityId` hôm nay → vd. «Hôm nay đã nhận GX cho hoạt động này.»
   - `now - lastAt < COOLDOWN_MS` → vd. «Chờ thêm vài phút rồi nhận GX nhé.» (hoặc còn lại phút)
   - `completesToday >= MAX` → giữ copy hiện có
   - `remainingCap(…, COMPLETE)==0` (day/week) → «Hôm nay/tuần đủ nhịp thưởng rồi — phần còn lại là chơi thật.»
2. **`ActivityDetailScreen`** — nếu `!rewardsEnabled` (cần expose flag từ VM/prefs): đổi CTA thành «Hoàn thành» (bỏ «· nhận Gấu Xu»); celebration giữ «Đã ghi nhận…» OK.
3. **Optional P2:** gắn `EARN_FEEDBACK` +3 sau chọn cảm xúc (riêng flow), không trộn vào nút Hoàn thành nếu chưa có UI feedback.

---

## 5. Parent format

```
STATUS: WARN
SCOPE: Complete → COMPLETE ledger (+15) → celebration → Về Hôm nay
DONE: Static trace Activity VM/Screen + GxRewardsUseCase vs CONSENSUS rates
FILES: qa/experts/gx/01_O1_complete_earn.md
DEVIATIONS: Không sửa production
BLOCKERS: Chưa device QA APK
ERRORS: O1-01 silent cooldown/cap/dup; O1-02 CTA khi rewards off
NEXT_FOR_PARENT: Patch message deny + CTA; O4 gom; re-QA O1 sau patch
```
