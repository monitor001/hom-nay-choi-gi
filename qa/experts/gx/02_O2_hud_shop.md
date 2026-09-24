# O2 — GX HUD & Cửa hàng UX

**App:** Hôm nay chơi gì? · `com.gaucon.app` · `0.1.5-shop`  
**Expert:** O2 · GX HUD & Cửa hàng UX  
**Ngày:** 2026-09-24  
**Phạm vi:** static + luồng code (không thiết bị thật)

## STATUS: **WARN**

Bề mặt user-facing **đã có đủ tín hiệu**: tab **Cửa hàng**, chip Gấu Xu (xu vàng hình gấu) trên Today → mở shop, ảnh cartoon catalog, thẻ **«Đã chơi ✓»**, FoodTreat ẩn mặc định.  
Lỗ hổng vận hành: Today **chỉ** `LaunchedEffect(Unit) { load() }` — không reload theo lifecycle resume → HUD số dư / «Đã chơi» có thể **stale** sau redeem hoặc complete nếu composition không bị dispose/re-enter đúng kỳ vọng.

---

## Checklist kỳ vọng user

| Kỳ vọng | Kết quả | Evidence |
|--------|---------|----------|
| Xu vàng hình gấu (gold bear coin) | **PASS** | `GxBalanceChip` + shop cost row dùng `R.drawable.ic_gau_xu` (`feature/rewards/.../ic_gau_xu.png`). Asset = đồng vàng 3D + mặt gấu. Bản sao cũng có ở `core/designsystem`. |
| Tab Cửa hàng | **PASS** | `MainTabs`: `BottomTab(Routes.Shop, "Cửa hàng", Icons.Filled.Storefront)`; `composable(Routes.Shop) { RewardsScreen() }`. |
| Ảnh vật phẩm cartoon | **PASS** | `RewardCatalog` gắn `@DrawableRes`; `ShopItemCard` `painterResource(item.imageRes)`. Drawable: `ic_gau_xu` + **25** `reward_*.png` (đủ ID catalog; `play_book` reuse `reward_story`). |
| Chip Today mở shop | **PASS** | `GxBalanceChip(..., onClick = onOpenShop)`; `MainActivity` `onOpenShop → navigate(Routes.Shop)`. |
| Thẻ «Đã chơi» sau complete | **PASS (UI)** | `ActivityCard`: `done = id in completedIds` → label **«Đã chơi ✓»** + `primaryContainer`. `load()` lấy log trong ngày qua `activityLogRepository.recentForChild`. |
| FoodTreat ẩn mặc định | **PASS** | Prefs default `false`; `RewardCatalog.filter { tier != FOOD_TREAT \|\| food }`; switch «Hiện đồ ăn/uống». |

---

## Evidence (read-only)

### Bottom nav / Shop

```121:127:gaucon/app/src/main/java/com/gaucon/app/MainActivity.kt
private val MainTabs = listOf(
    BottomTab(Routes.Today, "Hôm nay", Icons.Filled.Home),
    BottomTab(Routes.Shop, "Cửa hàng", Icons.Filled.Storefront),
    ...
)
```

### Today chip + completedIds

- Header: logo + greeting + `GxBalanceChip(balance = state.gxBalance, onClick = onOpenShop)`.
- `LaunchedEffect(Unit) { viewModel.load() }` — **không** `LifecycleResumeEffect` / `ON_RESUME`.
- `refreshSuggestions()` chỉ cập nhật `activities` / `emptyHint` — **không** refresh `gxBalance` hay `completedIds`.

### Assets

| Path | Có? |
|------|-----|
| `feature/rewards/src/main/res/drawable/ic_gau_xu.png` | Có |
| `feature/rewards/src/main/res/drawable/reward_*.png` | 25 file, khớp catalog |
| `core/designsystem/.../ic_gau_xu.png` | Có (không dùng bởi chip rewards) |

---

## Issue table

| ID | Sev | Issue | Why it hurts |
|----|-----|-------|--------------|
| H1 | **P1** | Today chỉ load qua `LaunchedEffect(Unit)` — không reload on resume | Sau complete/redeem, nếu quay lại Today mà composition **không** dispose/re-enter (hoặc race với `saveState`/`restoreState` bottom-nav), chip GX và «Đã chơi» giữ snapshot cũ → gãy vòng E2E HUD. |
| H2 | **P2** | `refreshSuggestions()` không gọi lại balance / completedIds | «Đổi gợi ý» có thể hiện thẻ chưa «Đã chơi» / GX cũ nếu log vừa đổi trong cùng session (ít gặp hơn H1). |
| H3 | **P2** | Chip `navigate(Shop) { launchSingleTop }` ≠ pattern tab (`popUpTo` + `saveState`) | Back stack Today→Shop chồng khác với chuyển tab; dễ lệch restore vs Back. Không chặn thấy shop, nhưng dễ stale/UX back. |
| H4 | **P3** | Khi `rewardsEnabled == false`, Today ép `gxBalance = 0` | Chip vẫn hiện «0 GX» — có thể hiểu nhầm «mất xu» thay vì «sổ tắt». |

Không có issue P0 về thiếu tab / thiếu coin / thiếu ảnh / thiếu chip (đã wire).

---

## EDITS_REQUIRED (O2 không sửa code)

### Bắt buộc để O2 → PASS

1. **`TodayScreen` — reload khi resume**  
   Thêm reload lifecycle, ví dụ `LifecycleResumeEffect(Unit) { viewModel.load() }` (hoặc `DisposableEffect` + `ON_RESUME`), **thay hoặc bổ sung** `LaunchedEffect(Unit)` — đảm bảo mỗi lần Today visible lại sau Activity complete / Shop redeem / tab switch thì `gxBalance` + `completedIds` tươi.

2. **Smoke accept**  
   - Complete HĐ → back Today: chip GX tăng (nếu sổ bật) + thẻ **«Đã chơi ✓»**.  
   - Redeem trên Cửa hàng → về Today: chip khớp số dư Shop.

### Nên làm cùng PR (P2)

3. `refreshSuggestions()`: sau khi đổi picks, refresh luôn `gxBalance` + `completedIds` (hoặc gọi lại `load()` gọn).  
4. Chip/`onOpenShop`: dùng cùng options bottom-tab (`popUpTo(start) { saveState = true }`, `launchSingleTop`, `restoreState`) để một đường vào Shop.

### Không cần (đã OK)

- Thêm asset coin / `reward_*.png` mới.  
- Đổi FoodTreat default (đã ẩn).  
- Đổi label «Đã chơi ✓» (đủ rõ so với «Đã chơi»).

---

## Acceptance (O2 re-test)

- [ ] Cold start → bottom bar thấy **Cửa hàng**.  
- [ ] Today ≤ 3s: chip đồng vàng gấu + số + «GX»; tap → `RewardsScreen` «Cửa hàng» / «Đổi Gấu Xu».  
- [ ] Catalog (non-food): ảnh cartoon 72dp; food chỉ khi bật «Hiện đồ ăn/uống».  
- [ ] Complete → Today: «Đã chơi ✓» + balance cập nhật **không cần kill app**.  
- [ ] Redeem → Today: chip khớp số dư shop **không cần kill app**.

---

## Verdict note

**WARN** = HUD/shop surface đạt kỳ vọng nhìn thấy; còn **hố resume/stale (H1)** trước khi gọi PASS vận hành end-to-end.
