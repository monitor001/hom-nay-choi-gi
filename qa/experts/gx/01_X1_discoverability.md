# X1 — Gấu Xu discoverability

**App:** Hôm nay chơi gì? · `com.gaucon.app`  
**Build context:** user report sau cài `0.1.4-rewards` APK  
**Expert:** X1  
**Ngày:** 2026-09-24  

## STATUS: **FAIL**

User report «chưa thấy giao diện gấu xu» **khớp code**: màn `RewardsScreen` có tồn tại và wire đúng route, nhưng **không có lối vào rõ trên bề mặt chính** (bottom-nav / Today). Entry duy nhất nằm sâu trong tab **Nhắc** — dễ bỏ qua hoàn toàn.

---

## Evidence (read-only)

| Surface | File | GX entry? |
|---------|------|-----------|
| Bottom nav | `MainActivity.kt` → `MainTabs` | **Không.** 5 tab: Hôm nay, Tài liệu, Phát triển, Nhật ký, Nhắc. `Routes.Rewards` không nằm trong `MainTabs`. |
| Today | `TodayScreen.kt` | **Không.** Không chip / balance / link GX. Chỉ logo, chào, banner nhắc, danh sách hoạt động. |
| Nhắc (More) | `ReminderSettingsScreen.kt` | **Có, ẩn.** `OutlinedButton` «Sổ Gấu Xu · đổi thưởng» → `onOpenRewards` → `Routes.Rewards`. |
| Rewards UI | `RewardsScreen.kt` | **Có khi đã vào.** Title «Gấu Xu» / «Sổ thưởng gia đình» / số dư `N GX`. |

Luồng thực tế:

```
Tab «Nhắc» → (scroll đầu màn) nút «Sổ Gấu Xu · đổi thưởng» → RewardsScreen
```

`showBottomBar` chỉ bật khi route ∈ `MainTabs` → khi đang ở Rewards, bottom bar **ẩn** (OK cho sub-screen, nhưng càng cần entry rõ từ bề mặt chính).

---

## Issue table

| ID | Sev | Issue | Why it fails user |
|----|-----|-------|-------------------|
| D1 | **P0** | Không có entry GX trên bottom-nav **và** không có chip/balance trên Today | Sau cài APK, user ở «Hôm nay» cả ngày — không bao giờ thấy chữ «Gấu Xu». |
| D2 | **P0** | Entry duy nhất gắn tab «Nhắc» (icon chuông + label nhắc lịch) | Mental model: cài đặt thông báo, không phải sổ thưởng. Nút nằm giữa title nhắc và copy giờ yên lặng — dễ hiểu là phụ kiện nhắc, không phải feature mới. |
| D3 | **P1** | Label nút «Sổ Gấu Xu · đổi thưởng» vs title màn «Gấu Xu» / «Sổ thưởng gia đình» | Ba cách gọi khác nhau; user tìm «gấu xu» trên UI chính sẽ không match. |
| D4 | **P1** | Không hiển thị số dư GX ở bất kỳ surface nào trước khi vào Rewards | Không có tín hiệu «có gì mới / còn N xu» → không có lý do mở. |

---

## EDITS_REQUIRED (concrete — cho eng; X1 không sửa code)

### Bắt buộc (để X1 → PASS)

Chọn **một** primary entry (ưu tiên A; B cũng đủ nếu đủ rõ):

**A — Chip trên Today (khuyến nghị, ít đụng 5-tab)**

1. `TodayScreen`: thêm callback `onOpenRewards: () -> Unit` (và optional `balance: Int` từ ViewModel / use-case GX).
2. Header Today (cạnh logo / greeting): `AssistChip` hoặc `TextButton` label **`Gấu Xu · N`** (N = số dư; nếu chưa load / empty child → `Gấu Xu`).
3. `MainActivity` `composable(Routes.Today)`: truyền `onOpenRewards = { navController.navigate(Routes.Rewards) }`.
4. **Giữ** nút trên `ReminderSettingsScreen` («Sổ Gấu Xu · …» hoặc đồng bộ label) — secondary path OK.

**B — Bottom-nav entry**

1. Thêm tab `BottomTab(Routes.Rewards, "Gấu Xu", icon phù hợp)` **hoặc** đổi label/tab «Nhắc» thành hub «Thêm» có GX nổi bật — **không** để GX chỉ là nút phụ trong màn nhắc.
2. Nếu full 6 tab quá chật: ưu tiên A thay vì nhồi tab.

### Label clarity (cùng PR discoverability)

| Nơi | Label đề xuất |
|-----|----------------|
| Chip Today / tab (nếu có) | **Gấu Xu · N** |
| Nút trong Nhắc (giữ) | **Gấu Xu** hoặc **Sổ Gấu Xu** (bỏ «đổi thưởng» nếu dài; hoặc giữ subtitle phụ) |
| `RewardsScreen` headline | Giữ **Gấu Xu** + phụ đề «Sổ thưởng gia đình» (đã ổn khi đã vào) |

### Không làm (scope X1)

- Không yêu cầu đổi logic earn/redeem (X2/X3).
- Không bắt buộc onboarding tooltip nếu đã có chip/tab rõ.

---

## Acceptance (X1 re-test)

- [ ] Từ cold start → tab **Hôm nay**: thấy **Gấu Xu · N** (hoặc tab bottom-nav **Gấu Xu**) trong ≤ 3 giây, không cần mở Nhắc.
- [ ] Tap → vào `RewardsScreen` (title Gấu Xu / số dư).
- [ ] Từ tab **Nhắc** vẫn mở được sổ (link secondary còn).
- [ ] Copy nhất quán: user tìm «Gấu Xu» thì thấy đúng cụm đó trên entry primary.

**Verdict hiện tại:** FAIL — D1+D2 đủ chứng minh report user.
