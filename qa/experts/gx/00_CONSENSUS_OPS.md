# CONSENSUS QA vận hành — Gấu Xu

> O4 · 2026-09-24 · Nguồn O1–O3 · Applier đã vá P1 trong phiên

---

```
STATUS: OK (static + code fix) · device lab BLOCKED
SCOPE: Complete→earn→HUD→shop→redeem vận hành
DONE: Charter; O1 WARN→patched deny messages; O2 WARN→Today/Shop ON_RESUME reload; O3 PASS redeem; CTA theo rewardsEnabled
FILES: qa/experts/gx/*; GxRewardsUseCase.kt; TodayScreen.kt; RewardsScreen.kt; ActivityViewModel/DetailScreen
DEVIATIONS: Không adb device — chưa logcat runtime
BLOCKERS: Không
ERRORS: (đã hết P1 chính) stale balance; silent deny; CTA GX khi sổ tắt
NEXT_FOR_PARENT: Cài APK mới; smoke: hoàn thành → thấy +GX → Về Hôm nay thấy chip cập nhật + «Đã chơi» → Cửa hàng đổi item
```

## Kết quả chuyên gia

| Expert | Verdict | Ghi chú |
|---|---|---|
| **O1** Complete→earn | WARN → **patched** | Celebration OK; thêm denyHint cooldown/dup/cap |
| **O2** HUD/shop | WARN → **patched** | Tab + chip + ảnh OK; reload ON_RESUME |
| **O3** Redeem | **PASS** | Trừ GX, FoodTreat ẩn, journal gated |

## Luồng vận hành chuẩn (sau vá)

1. **Hôm nay** — chip gấu vàng + số GX (bấm → Cửa hàng)
2. Mở HĐ → **Hoàn thành · nhận Gấu Xu**
3. Màn **Đã chơi xong!** + số GX / lý do không cộng
4. **Về Hôm nay** — thẻ «Đã chơi ✓», chip cập nhật
5. Tab **Cửa hàng** — ảnh cartoon, Đổi nếu đủ GX; đồ ăn ẩn đến khi bật

## P2 mở (không chặn ship)

- FEEDBACK +3 chưa gắn UI cảm xúc
- Double-tap redeem race (hiếm)
