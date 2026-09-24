# O3 — Redeem & ledger vận hành

> Expert O3 · QA Redeem / sổ Gấu Xu / prefs  
> Ngày: 2026-09-24 · **Chỉ đọc code — không sửa production**  
> App: `0.1.5-shop` · Room `GauConDatabase` **version = 3**

---

## STATUS

```
STATUS: PASS
SCOPE: Redeem trừ GX, số dư, recent log, rewardsEnabled gate, FoodTreat ẩn mặc định,
       journal earn cùng ngày, caps không chặn redeem
DONE:
  - Đọc GxRewardsUseCase.redeem / onJournalSaved
  - Đọc RewardsViewModel load / redeem / setRewardsEnabled / setFoodTreatVisible
  - Đọc GxLedgerDao + GxLedgerRepositoryImpl + Room version 3
  - Đọc JournalViewModel.saveNote → onJournalSaved
  - Đọc UserPreferences rewardsEnabled / foodTreatVisible
  - Đọc RewardsScreen canRedeem / message
FILES: qa/experts/gx/03_O3_redeem_ledger.md
DEVIATIONS: Không
BLOCKERS: Không
ERRORS: Không (checklist vận hành redeem/ledger OK)
NEXT_FOR_PARENT:
  - Không bắt buộc patch O3
  - Optional polish E1–E2 nếu muốn UX/race sạch hơn
  - Consensus O4 có thể lấy PASS này làm đầu vào
VERDICT: PASS — thiếu GX có message; REDEEM âm; FoodTreat ẩn mặc định;
         journal cần complete cùng ngày; daily/weekly cap không chặn redeem
```

---

## Checklist (kết quả)

| # | Kiểm tra | Kết quả | Evidence ngắn |
|---|---|---|---|
| 1 | Insufficient balance message | **PASS** | `redeem`: `"Chưa đủ Gấu Xu (cần $cost, đang có $bal)."` |
| 2 | Negative ledger `REDEEM` | **PASS** | `insert(..., -cost, GxKinds.REDEEM, ...)` |
| 3 | Food treat ẩn mặc định | **PASS** | prefs `?: false`; catalog filter `FOOD_TREAT` chỉ khi `food` |
| 4 | Journal earn cần complete cùng ngày | **PASS** | `hadCompleteToday` từ ledger COMPLETE **hoặc** activity log hôm nay |
| 5 | Daily/weekly caps trên redeem? | **PASS (không chặn)** | `redeem` chỉ check `cost` + `balance`; **không** gọi `remainingCap` |

---

## 1. Evidence

### 1.1. `GxRewardsUseCase.redeem`

```140:146:gaucon/domain/src/main/java/com/gaucon/domain/usecase/GxRewardsUseCase.kt
    suspend fun redeem(childId: String, itemId: String, cost: Int, title: String): EarnResult {
        if (cost <= 0) return EarnResult(0, "Mục không hợp lệ.")
        val bal = ledger.balance(childId)
        if (bal < cost) return EarnResult(0, "Chưa đủ Gấu Xu (cần $cost, đang có $bal).")
        insert(childId, -cost, GxKinds.REDEEM, itemId, "Đổi: $title")
        return EarnResult(-cost, "Đã ghi nhận đổi «$title». Bạn tự chuẩn bị ngoài đời nhé.")
    }
```

- Thiếu số dư → `awarded=0` + message rõ (cần / đang có).
- Đủ → ghi `amount = -cost`, `kind = REDEEM`.
- **Không** đọc `earnedBetween` / `remainingCap` → cap ngày/tuần **không** chặn đổi thưởng (đúng kỳ vọng O3).

### 1.2. Ledger DAO / Repo / Room

- `GauConDatabase` **version = 3**, entity `GxLedgerEntity` trong schema.
- `balance` = `SUM(amount)` (earn dương + redeem âm) → số dư phản ánh redeem.
- `earnedBetween` chỉ `amount > 0` → redeem **không** làm méo cap earn.
- `insert` + `recent(ORDER BY createdAt DESC)` → sau redeem, VM reload balance + recent 20.

### 1.3. `RewardsViewModel`

- `load`: đọc `rewardsEnabled`, `foodTreatVisible`, balance, recent; filter catalog  
  `tier != FOOD_TREAT || food`.
- `setFoodTreatVisible`: ghi prefs + cập nhật `items` ngay (ẩn/hiện FoodTreat không cần reload full).
- `setRewardsEnabled`: ghi prefs + cập nhật flag UI.
- `redeem`:
  - Gate `!rewardsEnabled` → `"Sổ thưởng đang tắt."` (không gọi use case).
  - Gọi `gxRewards.redeem` → set `message`, refresh `balance` + `recent`.

### 1.4. Prefs

```94:107:gaucon/core/datastore/src/main/java/com/gaucon/core/datastore/UserPreferences.kt
    /** Soft ledger Gấu Xu — mặc định bật (P1). */
    suspend fun rewardsEnabled(): Boolean =
        context.dataStore.data.first()[Keys.rewardsEnabled] ?: true
    ...
    /** Catalog FoodTreat ẩn mặc định (R2). */
    suspend fun foodTreatVisible(): Boolean =
        context.dataStore.data.first()[Keys.foodTreatVisible] ?: false
```

- `RewardsUiState.foodTreatVisible = false` khớp default prefs.
- Fresh install: FoodTreat **không** hiện cho đến khi PH bật switch «Hiện đồ ăn/uống».

### 1.5. Journal earn — cùng ngày

```113:137:gaucon/domain/src/main/java/com/gaucon/domain/usecase/GxRewardsUseCase.kt
    suspend fun onJournalSaved(...): EarnResult {
        ...
        val hadCompleteToday = ledger.completeEarnCountBetween(...) > 0 ||
            activityLogRepository.completedCountToday(childId, today.toString()) > 0
        if (!hadCompleteToday) {
            return EarnResult(0, null)
        }
        ...
        val room = remainingCap(dayEarned, weekEarned, GxRates.JOURNAL)
        ...
    }
```

- `JournalViewModel.saveNote`: chỉ gọi earn khi `prefs.rewardsEnabled()`; message gắn sau «Đã lưu…».
- Không complete hôm nay → **0 GX**, message null (im lặng — đúng gate).
- Cap ngày/tuần **áp trên journal earn** (đúng), không liên quan redeem.

### 1.6. UI shop (bổ sung)

- `canRedeem = rewardsEnabled && balance >= cost`.
- Không đủ / tắt sổ → nút disabled label **«Chưa đủ GX»** (không gọi `redeem` → message use case #1 ít khi thấy từ UI; vẫn đúng defense-in-depth ở domain).

---

## 2. WARN nhẹ (không hạ PASS)

| ID | Mô tả | Mức |
|---|---|---|
| W1 | Khi `rewardsEnabled=false` nhưng đủ GX, UI vẫn hiện «Chưa đủ GX» (nhãn nút) thay vì «Sổ tắt» — chỉ misleading label; VM vẫn chặn redeem đúng. | UX polish |
| W2 | `redeem` check balance rồi insert không atomic — double-tap cực nhanh lý thuyết có thể âm; thực tế UI disable sau khi hết GX. | Race hiếm |

---

## 3. EDITS_REQUIRED

**Không bắt buộc** cho STATUS PASS.

### (Optional) E1 — Label nút khi sổ tắt

- Khi `!rewardsEnabled`: nút disabled text **«Sổ thưởng đang tắt»** (hoặc ẩn CTA), đừng dùng «Chưa đủ GX».

### (Optional) E2 — Chống double redeem

- Disable nút trong lúc `redeem` đang chạy, hoặc check lại balance trong transaction/single-flight.

---

## 4. Acceptance (re-test O3 trên máy)

| # | Kiểm tra | Pass khi |
|---|---|---|
| 1 | Đổi khi đủ GX | Balance giảm đúng `cost`; recent có dòng `Đổi: …` / amount âm |
| 2 | Đổi khi thiếu (nếu gọi được) | Message «Chưa đủ Gấu Xu (cần …, đang có …)» |
| 3 | Fresh prefs FoodTreat | Không thấy tier đồ ăn/uống; bật switch → hiện |
| 4 | Journal không complete hôm nay | Lưu OK, **không** +GX |
| 5 | Journal sau ≥1 complete hôm nay | Có thể +GX journal (nếu còn room cap) |
| 6 | Đã chạm daily/weekly cap earn | Vẫn đổi thưởng được nếu còn balance |
| 7 | Tắt «Bật sổ thưởng» | Redeem bị chặn; message «Sổ thưởng đang tắt.» |

---

## 5. Verdict

**STATUS: PASS.**  
Luồng redeem/ledger vận hành đúng checklist O3: message thiếu GX, `REDEEM` âm trên Room v3, FoodTreat ẩn mặc định, journal earn phụ thuộc complete cùng ngày, cap earn **không** chặn redeem. EDITS chỉ optional polish (W1/W2).
