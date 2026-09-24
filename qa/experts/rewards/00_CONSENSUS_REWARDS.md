# CONSENSUS — Hệ thống Gấu Xu & đổi thưởng

> R4 tổng hợp · 2026-09-24  
> Nguồn: R1 economy · R2 psychology · R3 missions · CHARTER  
> **Chỉ kế hoạch** — chưa code. Phase: **Rewards P1 (opt-in)**, không ship máy slot MVP.

---

```
STATUS: OK
SCOPE: Chốt đơn vị GX, earn/cap, catalog quy đổi, nhiệm vụ/streak, guardrail nuôi dưỡng, phase ship.
DONE: Hợp nhất R1–R3; điều hòa số earn lệch; bảng giá vật phẩm; weekly budget; cấm food-as-control.
FILES: qa/experts/rewards/00_CHARTER.md, 01_R1_economy.md, 02_R2_psychology.md, 03_R3_missions.md, 00_CONSENSUS_REWARDS.md
DEVIATIONS: CONSENSUS sản phẩm loại «coin» khỏi MVP core → Rewards = P1 Soft Ledger của PH, tắt mặc định hoặc sau onboarding «Bật sổ thưởng gia đình».
BLOCKERS: Parent chốt: (1) bật FoodTreat mặc định hay ẩn, (2) tên GX giữ hay đổi.
ERRORS: Không
NEXT_FOR_PARENT: Duyệt số liệu §2–§5; nếu OK → backlog implement ledger Room + UI Đổi thưởng.
VERDICT: PASS với điều kiện R2 (connection-first, FoodTreat không hero).
```

---

## 1. Bản chất hệ thống (chốt ethics)

| Được | Không được |
|---|---|
| **PH** kiếm GX khi **chơi thật với con** (complete HĐ) | Bé grind màn hình / máy xu |
| Đổi = **gợi ý** PH tự mua/làm ngoài đời | App ship hàng / IAP bắt buộc / ads |
| Hero catalog: **SharedMoment → Experience → Play** | Hero = kẹo / bim bim / ép ăn |
| Streak **thưởng mềm**, nghỉ không shame | Copy «mất streak / đừng phá chuỗi» |
| Soft ledger + daily/weekly cap | Slot, jackpot, leaderboard, FOMO |

**Disclaimer màn Đổi thưởng (bắt buộc):**

> Đây là sổ gợi ý cho phụ huynh sau khi chơi cùng con — không phải phần thưởng để ép bé. Đồ ăn/uống do bạn quyết; app không khuyến khích dùng đồ ngọt để điều khiển hành vi. Mỗi bé một nhịp riêng.

---

## 2. Đơn vị & earn (điều hòa R1 + R3)

**Tên:** Gấu Xu (**GX**).

### 2.1. Bảng kiếm (chốt)

| Mã | Sự kiện | GX | Điều kiện |
|---|---|---:|---|
| `EARN_COMPLETE` | Hoàn thành 1 HĐ | **+15** | 1× / `activityId` / ngày; cooldown ≥ 8 phút giữa 2 lần earn |
| `EARN_FEEDBACK` | Chọn cảm xúc sau HĐ | **+3** | 1× / log; mọi lựa chọn (kể cả «chưa hợp») |
| `EARN_JOURNAL` | Nhật ký/khoảnh khắc gắn ngày có HĐ | **+5** | ≤ số complete trong ngày; không journal trống |
| `EARN_LOGIN` | Chỉ mở app | **0** | — |
| `EARN_STREAK_DAY` | Ngày có ≥1 HĐ, chuỗi ≥2 | **+4** | Soft; đứt chuỗi = về 0 **im lặng**, **giữ** số dư GX |
| `EARN_STREAK_7` | Mốc 7 ngày có chơi | **+15** | One-shot / chu kỳ |
| `EARN_WEEK_KPI` | ≥ **3** complete / tuần lịch | **+25** | Khớp KPI SPEC |

### 2.2. Trần chống grind

| Cap | Giá trị |
|---|---:|
| Daily hard | **45 GX / ngày / trẻ** |
| Weekly hard | **150 GX / tuần / trẻ** |
| Max complete được GX / ngày | **3** (lần 4+ vẫn log, 0 GX) |

Overflow: bỏ, không chuyển ngày. Toast mềm: «Hôm nay đủ nhịp thưởng rồi — phần còn lại là chơi thật.»

### 2.3. Thu nhập mẫu (healthy)

| Nhịp | HĐ/tuần | Ước GX/tuần |
|---|---:|---:|
| Dưới KPI | 2 | ~45–70 |
| **KPI** | **3** | **~80–100** |
| Đều (gần 1 HĐ/ngày) | 5–6 | ~120–150 (chạm weekly cap) |

**Neo thiết kế:** **~90 GX/tuần** ở KPI 3–4 HĐ.

---

## 3. Bảng giá quy đổi (catalog chốt)

App **không** trừ tiền thật. «Đổi» = PH đánh dấu đã tặng/chuẩn bị ngoài đời (trừ GX trong sổ).

**Default sort UI:** SharedMoment → Experience → Play → FoodTreat (FoodTreat **ẩn** nếu PH chưa bật «Hiện gợi ý đồ ăn»).

### 3.1. SharedMoment (ưu tiên)

| ID | Vật phẩm / khoảnh khắc | GX | VND hint |
|---|---|---:|---|
| mom_song_pick | Bé chọn bài hát / nhảy 1 bài | 25 | 0 |
| mom_choose_game | Bé chọn trò tiếp theo | 28 | 0 |
| mom_story_10 | Thêm 10 phút chuyện/sách | 30 | 0 |
| mom_bath_toys | Tắm chơi thêm 5 phút | 35 | 0 |
| mom_sticker | Sticker khen cụ thể | 40 | 10–30k |
| mom_grandparents | Gọi ông bà khoe trò hôm nay | 45 | 0 |
| mom_picnic_blanket | Picnic thảm tại nhà | 60 | 0–40k |
| mom_photo_print | In 1 ảnh chơi cùng nhau | 80 | 5–25k |

### 3.2. Experience

| ID | Mục | GX | VND hint |
|---|---|---:|---|
| exp_bus_ride | Chuyến xe buýt/tàu vui ngắn | 120 | 20–60k |
| exp_park | Công viên / sân chơi 1 buổi | 200 | 50–150k |
| exp_swim_paddle | Bể bơi nông (gia đình quen) | 260 | 70–180k |
| exp_zoo_corner | Góc thú / bảo tàng thiếu nhi | 280 | 80–200k |

### 3.3. Play

| ID | Mục | GX | VND hint |
|---|---|---:|---|
| play_reuse | Đồ chơi từ nhà (carton, chai sạch) | 40 | 0–20k |
| play_art | Sáp / màu ngón tay an toàn | 140 | 30–90k |
| play_small | **Đồ chơi nhỏ** | **150** | 30–80k |
| play_book | Sách tranh 1 cuốn | 180 | 40–120k |
| play_mid | **Đồ chơi trung** | **320** | 100–250k |

### 3.4. FoodTreat (tùy chọn · disclaimer · không hero)

> Nhãn cố định: «PH quyết định — không khuyến khích ép ăn / dùng đồ ăn điều khiển hành vi.»

| ID | Mục | GX | VND hint | Ghi chú |
|---|---|---:|---|---|
| food_lollipop | **Kẹo mút** (1) | **35** | 5–15k | Caution đường |
| food_snack | **Bim bim** gói nhỏ | **45** | 10–25k | Caution |
| food_yogurt | Sữa chua ít đường thêm | 45 | 10–30k | Ưu tiên hơn kẹo |
| food_fruit_cup | Trái cây cắt / cup | 50 | 15–40k | Healthy-leaning |
| food_watermelon | **Nước dưa hấu** / dưa cắt | **50** | 15–35k | Í đường thêm = tốt hơn |
| food_sugarcane | **Nước mía** (ly) | **55** | 10–20k | Caution đường |
| food_che_beans | Chè đậu / tàu hủ ít đường | 65 | 15–35k | Caution |
| food_banhmi_mini | Bánh mì mini / bánh bao nhỏ | 70 | 15–40k | Trung tính |
| food_oc | **Ăn ốc** (suất nhỏ / share) | **90** | 40–80k | Vệ sinh/cay do PH |

---

## 4. Thời gian đủ đổi (@ ~90 GX/tuần)

| Vật phẩm | GX | Thời gian ước lượng |
|---|---:|---|
| Thêm chuyện / chọn bài hát | 25–30 | **~1–2 ngày** chơi |
| Kẹo mút | 35 | **~3 ngày** |
| Bim bim / sữa chua | 45 | **~3–4 ngày** |
| Nước mía / dưa hấu | 50–55 | **~4–5 ngày** |
| Ăn ốc | 90 | **~1 tuần** (đúng KPI) |
| Đồ chơi nhỏ | 150 | **~1,5–2 tuần** |
| Công viên | 200 | **~2–2,5 tuần** |
| Đồ chơi trung | 320 | **~3,5–4 tuần (~1 tháng)** |

**Ý đồ:** treat nhỏ = feedback nhanh; đồ chơi/outing = nhịp gia đình thật, không «mỗi ngày 1 đồ».

---

## 5. Nhiệm vụ & streak (chốt R3 + chỉnh)

| Vòng | Quy tắc | Thưởng |
|---|---|---|
| Ngày | ≥1 complete hợp lệ | Earn theo §2; không thưởng login |
| Chuỗi | Ngày liên tiếp có ≥1 HĐ | +4/ngày từ ngày 2; mốc 7 → +15; **cấm** notif «mất streak» |
| Tuần | ≥3 complete (KPI) | +25 GX one-shot |
| Anti-abuse | 1× activityId/ngày · cooldown 8′ · max 3 earn complete/ngày · freeze nếu đồng hồ máy lùi | — |

---

## 6. Phase ship

| Phase | Nội dung |
|---|---|
| **MVP hiện tại** | Không UI GX (giữ S04–S06 + nhật ký + nhắc) |
| **P1 Soft Ledger** | Bật opt-in «Sổ thưởng gia đình»; wallet GX; catalog; redeem checkbox; FoodTreat mặc định **ẩn** |
| **P2** | PH chỉnh giá GX/custom item; thống kê tuần nhẹ (không %) |
| **Không làm** | IAP bán GX, ads, leaderboard, exact-alarm nag streak |

---

## 7. Dữ liệu kỹ thuật gợi ý (khi implement)

- `wallet_balance` / `gx_ledger` (Room) theo `childId`
- Event: `COMPLETE | FEEDBACK | JOURNAL | STREAK | WEEK_KPI | REDEEM`
- Redeem: trừ GX + ghi `itemId` + timestamp (không cần ảnh/ship)
- Feature flag: `rewards_enabled`, `foodtreat_visible`

---

## 8. Việc Parent cần chốt

1. Giữ tên **Gấu Xu**?  
2. FoodTreat **ẩn mặc định** (khuyến nghị R2/R4) hay hiện?  
3. Cho phép implement P1 ngay sau growth/journal, hay chỉ giữ doc?

---

*Không thay chuyên gia nuôi dưỡng người thật. Nội dung thưởng vẫn draft_unreviewed khi ship UI.*
