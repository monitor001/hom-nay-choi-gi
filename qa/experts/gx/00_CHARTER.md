# Đội QA vận hành — Gấu Xu & vòng thưởng

> App: **Hôm nay chơi gì?** · `0.1.5-shop` · package `com.gaucon.app`  
> Persona AI — static + luồng code; **không** thay QA thiết bị thật.  
> Ngày: 2026-09-24

## Mục tiêu

Kiểm tra **vận hành end-to-end**: hoàn thành HĐ → cộng GX → HUD/Cửa hàng → đổi thưởng → trạng thái thẻ «Đã chơi».

## Vai trò

| ID | Vai trò | Trọng tâm |
|---|---|---|
| **O1** | Complete → earn | `ActivityViewModel.complete`, caps, cooldown, message màn «Đã chơi xong» |
| **O2** | HUD & shop UX | Tab Cửa hàng, chip GX Today, ảnh vật phẩm, FoodTreat ẩn mặc định |
| **O3** | Redeem / ledger | Trừ GX, số dư, recent log, rewardsEnabled gate |
| **O4** | Consensus | `00_CONSENSUS_OPS.md` + EDITS_REQUIRED ưu tiên P0 |

## Artifact

- Nguồn: `gaucon/feature/{activity,today,rewards}/**`, `domain/usecase/GxRewardsUseCase.kt`
- APK: `dist/hom-nay-choi-gi-0.1.5-shop-debug.apk`

## Verdict

`PASS` · `FAIL` · `WARN` · `BLOCKED`
