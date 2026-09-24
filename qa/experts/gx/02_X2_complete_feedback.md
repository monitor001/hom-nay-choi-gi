# X2 — Activity complete → GX earn feedback

> Expert X2 · QA feedback UX sau «Hoàn thành»  
> Ngày: 2026-09-24 · **Chỉ đọc code — không sửa production**  
> User report: «chưa thấy chuyển trạng thái khi hoàn thành»

---

## STATUS

```
STATUS: FAIL
SCOPE: Feedback khi hoàn thành HĐ → earn GX (earnMessage, nav, Today card state)
DONE:
  - Đọc ActivityViewModel.complete / earnMessage
  - Đọc ActivityDetailScreen (UI earnMessage? onDone?)
  - Đọc GxRewardsUseCase.onActivityCompleted
  - Đọc TodayScreen / TodayViewModel (đánh dấu Đã chơi?)
  - Xác nhận MainActivity onCompleted = popBackStack
FILES: qa/experts/gx/02_X2_complete_feedback.md
DEVIATIONS: Không (đúng root-cause dự đoán)
BLOCKERS: Không — chờ apply EDITS_REQUIRED
ERRORS:
  - earnMessage set rồi onDone() pop ngay → UI không kịp hiện
  - ActivityDetailScreen không render earnMessage; không Snackbar/dialog
  - Today cards không đọc ActivityLog → không badge «Đã chơi»
NEXT_FOR_PARENT:
  - Apply EDITS_REQUIRED (Snackbar/dialog trước pop; badge Today; optional delay 1.5s)
  - Re-QA X2 sau patch
VERDICT: FAIL — backend earn OK; feedback UX gần như invisible
```

---

## 1. Evidence (đọc code)

### 1.1. `ActivityViewModel.complete`

```57:78:gaucon/feature/activity/src/main/java/com/gaucon/feature/activity/ActivityViewModel.kt
    fun complete(onDone: () -> Unit) {
        viewModelScope.launch {
            // ... insert ActivityLog ...
            val earnMsg = if (prefs.rewardsEnabled()) {
                gxRewards.onActivityCompleted(child.id, activity.id).message
            } else {
                null
            }
            _uiState.update { it.copy(completing = false, earnMessage = earnMsg) }
            onDone()
        }
    }
```

- `earnMessage` **có** được ghi vào state.
- Ngay sau đó gọi `onDone()` **không delay** → màn detail bị pop trước khi Compose kịp paint message.

### 1.2. `ActivityDetailScreen`

```92:98:gaucon/feature/activity/src/main/java/com/gaucon/feature/activity/ActivityDetailScreen.kt
        Button(
            onClick = { viewModel.complete { onCompleted() } },
            ...
        ) {
            Text("Hoàn thành")
        }
```

- **Không** dùng `state.earnMessage` ở bất kỳ đâu (không Text, Snackbar, AlertDialog).
- Nút chỉ disable khi `completing`; sau khi xong → `onCompleted()` ngay.
- Nav: `MainActivity` `onCompleted = { navController.popBackStack() }`.

### 1.3. `GxRewardsUseCase` (earn logic)

- `onActivityCompleted` trả `EarnResult(awarded, message)` với chuỗi kiểu `"+N GX hoàn thành HĐ"` / cap / streak.
- Logic thưởng **ổn** (cap ngày, cooldown, streak, KPI tuần).
- Lỗi user cảm nhận **không** nằm ở use case — nằm ở **không show message** + **không đổi UI Today**.

### 1.4. Today — không đánh dấu hoàn thành

- `TodayUiState` chỉ có `activities: List<Activity>` — **không** có set completed IDs / log hôm nay.
- `TodayViewModel.load` / `refreshSuggestions` **không** gọi `ActivityLogRepository`.
- `ActivityCard` chỉ title / phút / domain / goal — **không** badge «Đã chơi», không đổi màu/opacity sau complete.
- Sau pop về Today, list trông **y như trước** → khớp complaint «chưa thấy chuyển trạng thái».

---

## 2. Root-cause (xác nhận)

| # | Nguyên nhân | Mức |
|---|---|---|
| A | `onDone()` / `popBackStack()` chạy ngay sau khi set `earnMessage` | **Chính** |
| B | Screen **không bind** `earnMessage` → dù không pop cũng không thấy | **Chính** |
| C | Không Snackbar / dialog / toast | **Chính** |
| D | Today không mark card completed / badge «Đã chơi» | **Chính** (trạng thái danh sách) |
| E | `rewardsEnabled() == false` → `earnMsg = null` (im lặng) | Phụ — vẫn cần feedback «đã ghi nhật ký chơi» |

User đúng: **không có chuyển trạng thái nhìn thấy** dù ActivityLog + ledger có thể đã ghi.

---

## 3. EDITS_REQUIRED (UX chính xác)

### E1 — Hiện feedback **trước** khi pop

1. Trong `ActivityDetailScreen` (hoặc Host):
   - Dùng `SnackbarHost` / `Scaffold` **hoặc** `AlertDialog` ngắn.
   - Khi `state.earnMessage != null` → show message đó.
   - Khi rewards tắt / message null nhưng complete OK → Snackbar mặc định:  
     **«Đã ghi nhận — hôm nay đã chơi!»** (không phụ thuộc GX).
2. **Không** gọi `onCompleted()` / `popBackStack()` trong cùng frame với update state.
3. Thứ tự bắt buộc:
   - complete + set message → show Snackbar/dialog → **đợi** user dismiss **hoặc** auto ~**1.5s** → rồi mới `onCompleted()`.

### E2 — Optional: giữ `earnMessage` trên màn 1.5s

- Trong `ActivityViewModel.complete`: sau `_uiState.update(... earnMessage ...)`  
  `delay(1500)` rồi mới `onDone()`, **hoặc** để Screen `LaunchedEffect(earnMessage)` delay rồi gọi callback.
- Ưu tiên: delay ở **UI layer** (Screen) để dễ test / không block VM nếu navigate khác.

### E3 — Today: badge «Đã chơi»

1. `TodayViewModel`: load `ActivityLog` hôm nay (child active) → `Set<activityId>` completed.
2. `ActivityCard`: nếu id ∈ set → chip / badge text **«Đã chơi»** (Material tertiary/secondary container).
3. Sau quay lại từ detail: `LaunchedEffect` / lifecycle ON_RESUME hoặc reload khi compose lại Today — đảm bảo badge hiện **không** cần restart app.

### E4 — Không bắt buộc nhưng nên

- Nút «Hoàn thành» sau success: đổi label tạm **«Đã xong»** (disabled) trong 1.5s trước pop.
- Rewards screen / balance chip: nếu có trên Today — refresh balance sau complete (ngoài scope X2 tối thiểu).

---

## 4. Acceptance (re-test X2)

| # | Kiểm tra | Pass khi |
|---|---|---|
| 1 | Bấm «Hoàn thành» (rewards ON, lần đầu HĐ trong ngày) | Thấy Snackbar/dialog có `+N GX…` **trước** khi rời màn |
| 2 | Giữ màn ≥ 1s sau complete | Message đọc được; không flash biến mất |
| 3 | Quay Today | Card HĐ vừa xong có badge **«Đã chơi»** |
| 4 | Rewards OFF | Vẫn có feedback «Đã ghi nhận…» (không im lặng) |
| 5 | Complete lần 2 cùng HĐ / hết cap | Message cap/soft (nếu use case trả) vẫn hiện trước pop |

---

## 5. Verdict

**STATUS: FAIL.**  
Earn pipeline (`ActivityLog` + `GxRewardsUseCase`) chạy; **feedback UX = 0** vì pop ngay + không render `earnMessage` + Today không mark done. Áp dụng **EDITS_REQUIRED** E1–E3 rồi re-QA.
