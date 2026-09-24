# Đội kiểm thử UI — Hiến chương

> App: **Hôm nay chơi gì?** · package `com.gaucon.app` · module `gaucon/`  
> Persona AI nội bộ — static review + evidence screenshot; **không** sửa production code.

## Mục tiêu

Đánh giá chất lượng giao diện người dùng: thanh điều hướng, iconography, polish visual, cảm ứng / accessibility. Verdict phục vụ Applier / parent trước Closed testing.

## Vai trò

| ID | Vai trò | Trọng tâm |
|---|---|---|
| **U1** | Bottom nav & icons | `NavigationBar` / `NavigationBarItem`, Material Icons vs placeholder glyph, tab enabled/disabled, route stub |
| **U2** | Visual polish | Theme colors, typography, spacing, card elevation, contrast light theme |
| **U3** | Touch / a11y | Hit target, fontScale, TalkBack labels, focus order |
| **U4** | Consensus | Hợp nhất U1–U3 → `00_CONSENSUS_UI.md` + EDITS_REQUIRED ưu tiên |

## Artifact

- Nguồn: `gaucon/app/.../MainActivity.kt`, `core/designsystem`, `feature/*`
- Deps: `gaucon/gradle/libs.versions.toml`, `gaucon/app/build.gradle.kts`
- Evidence: screenshot LDPlayer / thiết bị (nếu có)

## Verdict

`PASS` · `FAIL` · `WARN` · `BLOCKED`

## Deliverables

`qa/experts/ui/01_U1_nav_icons.md` · `02_U2_…` · `03_U3_…` · `00_CONSENSUS_UI.md` (U4)
