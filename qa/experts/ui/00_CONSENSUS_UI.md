# CONSENSUS UI QA — Hôm nay chơi gì?

> U4 tổng hợp · 2026-09-24  
> Evidence: screenshot LDPlayer (icon chấm xám, tab không chọn)  
> Applier đã áp P0/P1 trong cùng phiên

---

```
STATUS: OK (static + code applied) · device re-verify PENDING
SCOPE: U1 nav/icons + U2 visual + U3 touch → EDITS + Applier
DONE: Consensus; Material Icons; tabs chọn được + ComingSoon; Theme teal/coral; Today logo + chips VN; version 0.1.1-ui / code 4
FILES: qa/experts/ui/*; MainActivity.kt; Theme.kt; TodayScreen.kt; libs.versions.toml; app+today build.gradle
DEVIATIONS: Wave 3 tabs = stub «Sắp có» (không full feature)
BLOCKERS: Cần PH cài lại APK trên LDPlayer để xác nhận icon/nav
ERRORS: (đã hết) Text("•")/enabled=false làm nav chết
NEXT_FOR_PARENT: Cài dist APK mới; chạm 5 tab; kiểm logo + chip domain trên Today
```

## P0 (áp dụng)

| ID | Fix |
|---|---|
| U1/U3 dead icons | `material-icons-extended` + `Icons.Filled.*` + `contentDescription` |
| U1/U3 dead tabs | 5 tab `enabled`; Library/Growth/Journal/More → `ComingSoonScreen` |
| U2 monotonous | Theme teal/coral/cream; Today header logo; domain chips VI; disclaimer nhỏ |

## Ship

`dist/hom-nay-choi-gi-0.1.1-ui-debug.apk` sau rebuild.
