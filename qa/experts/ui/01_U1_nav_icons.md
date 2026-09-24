# U1 — Bottom nav & icons

> Persona: Expert U1 · App: **Hôm nay chơi gì?** · package `com.gaucon.app`  
> Charter: `qa/experts/ui/00_CHARTER.md`  
> Evidence: screenshot LDPlayer — 5 nhãn tab, icon = chấm/vòng tròn xám; tab khác không chọn được.  
> Phương pháp: **static review** mã nguồn + đối chiếu deps; **không** sửa production code.

---

```
STATUS: FAIL
SCOPE: Bottom NavigationBar / NavigationBarItem icons & tab interactivity in GauConNav; Material Icons dependency gap; Theme/designsystem icon support.
DONE: Read MainActivity.kt GauConNav bottomBar; Theme.kt; libs.versions.toml; app + designsystem build.gradle.kts; cross-check Wave 3 BUILD_READY / Q3 dead-nav note; write EDITS_REQUIRED.
FILES: qa/experts/ui/00_CHARTER.md (created), qa/experts/ui/01_U1_nav_icons.md
DEVIATIONS: Wave 3 intentionally stubs non-Today tabs (comment "inactive Wave 3") — still FAIL for PH-facing IA (labels visible + dead + fake glyphs). No device automation; LDPlayer screenshot treated as corroborating evidence of Text("•")/"○" render.
BLOCKERS: None for static verdict.
ERRORS: None (compile-path not re-run; issue is intentional placeholder UX, not crash).
NEXT_FOR_PARENT: Applier: add material-icons-extended (or core), replace Text glyphs with Icons.Filled.*; either enable tabs → PlaceholderScreen (“Sắp có”) OR hide 4 tabs until Wave 3+ features exist. U4 may fold U1-P1 into consensus with Q3 UX-P1-02.
```

---

## Tóm tắt (VI)

Bottom bar **hiển thị đủ 5 mục** (Hôm nay, Thư viện, Phát triển, Nhật ký, Thêm) nhưng **không dùng Material Icons**. Icon slot là `Text("•")` / `Text("○")` — trên LDPlayer trông như chấm/vòng xám trong vòng highlight. Bốn tab còn lại `enabled = false` + `onClick = { }` → **không chọn được**. Theme/designsystem không cung cấp Icon set; catalog Gradle **thiếu** `androidx.compose.material:material-icons-*`. Đây là FAIL UX trước Closed testing, dù Wave 3 đã ghi chú stub.

---

## Evidence map

| Nguồn | Quan sát |
|---|---|
| Screenshot LDPlayer | Active: chấm đặc `•` trong circle; inactive: `○` mờ; 5 labels đúng copy VI |
| `MainActivity.kt` L106–140 | `icon = { Text("•") }` / `Text("○")`; 4 item `enabled = false`, `onClick = { }` |
| Comment L112 | `// Other tabs placeholder — inactive Wave 3` |
| `Routes` | Chỉ Welcome / ChildProfile / Today / Activity — **không** route Library/Dev/Journal/More |
| `libs.versions.toml` | Có `material3`, **không** có `material-icons-core` / `material-icons-extended` |
| `app/build.gradle.kts` | `implementation(libs.androidx.compose.material3)` only — no icons artifact |
| `core/designsystem` | `Theme.kt` = color + typography; **không** Icon wrapper; deps = material3 only |

---

## Issue table

| ID | Sev | Where | Symptom | Fix |
|---|---|---|---|---|
| **U1-P1-01** | P1 | `GauConNav` bottomBar icons | PH thấy “icon” = chấm/vòng text; không nhận diện tab bằng glyph chuẩn | Dùng `Icon(Icons.Filled.*)` — Home, MenuBook, TrendingUp, EditNote, MoreHoriz |
| **U1-P1-02** | P1 | `NavigationBarItem` ×4 | Tab hiện label nhưng `enabled=false` / empty onClick → dead nav | **A)** Enable + navigate `PlaceholderScreen` (“Sắp có”, Wave sau); **hoặc B)** Ẩn 4 tab, chỉ giữ «Hôm nay» đến khi có feature |
| **U1-P2-01** | P2 | `libs.versions.toml` + `app/build.gradle.kts` | Không khai báo material-icons → buộc dùng Text placeholder | Thêm library catalog + `implementation` icons-extended (hoặc core nếu đủ) |
| **U1-P2-02** | P2 | `core/designsystem` | Không chuẩn hóa Icon size/tint cho nav | Optional: `GauConNavIcon` / contentDescription VI trong designsystem |
| **U1-P2-03** | P2 | a11y labels | `Text("•")` không có semantics icon; TalkBack đọc kém | `Icon(..., contentDescription = "Hôm nay")` (hoặc null nếu label đã đủ — ưu tiên mô tả khi icon-only) |

**Không P0:** App vẫn mở được Today; dead icons không crash. Severity P1 vì IA giả + dead controls làm PH mất tin trước Play Closed testing.

---

## ## EDITS_REQUIRED

> Applier only — U1 không commit code production.

### 1) Dependency (U1-P2-01)

Trong `gaucon/gradle/libs.versions.toml` `[libraries]`:

```toml
androidx-compose-material-icons-extended = { group = "androidx.compose.material", name = "material-icons-extended" }
```

BOM `composeBom = "2024.12.01"` đã có → **không** cần version riêng.

Trong `gaucon/app/build.gradle.kts`:

```kotlin
implementation(libs.androidx.compose.material.icons.extended)
```

(Nếu catalog alias khác, khớp naming convention hiện có.)

### 2) Icons thật (U1-P1-01)

Trong `MainActivity.kt` / `GauConNav`:

```kotlin
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Icon

// Thay Text("•") / Text("○"):
icon = { Icon(Icons.Filled.Home, contentDescription = null) }       // Hôm nay
icon = { Icon(Icons.Filled.MenuBook, contentDescription = null) }   // Thư viện
icon = { Icon(Icons.Filled.TrendingUp, contentDescription = null) } // Phát triển
icon = { Icon(Icons.Filled.EditNote, contentDescription = null) }   // Nhật ký
icon = { Icon(Icons.Filled.MoreHoriz, contentDescription = null) }  // Thêm
```

`contentDescription = null` OK khi `label` đã có (M3 NavigationBarItem).

### 3) Dead tabs — chọn một hướng (U1-P1-02)

**Hướng A — Placeholder (khuyến nghị nếu giữ IA 5 tab):**

- Thêm `Routes.Library / Development / Journal / More` (hoặc một `placeholder/{tab}`).
- `enabled = true`; `selected` theo `route`; `onClick = { navController.navigate(...) }`.
- Màn tối giản: `PlaceholderScreen(title)` — copy «Sắp có trong bản sau» + không crash.
- `showBottomBar` mở rộng cho các route shell (không chỉ `Routes.Today`).

**Hướng B — Ẩn đến Wave 3+ feature:**

- Chỉ render **một** `NavigationBarItem` «Hôm nay» **hoặc** bỏ hẳn `bottomBar` khi chỉ có Today.
- Tránh hiện 4 label chết — khớp BUILD_READY (feature modules chưa P0).

**Không chấp nhận:** giữ 5 label + `enabled=false` + glyph Text.

### 4) Optional polish (U1-P2-02)

- Export icon tint qua `MaterialTheme.colorScheme.onSurface` / primary khi selected (M3 NavigationBarItem đã xử lý phần lớn).
- Đồng bộ với Q3 **UX-P1-02** (dead bottom nav) — một PR sửa cả icon + interactivity.

---

## Checklist U1

| Cờ | Verdict | Ghi chú |
|---|---|---|
| Material Icons used | **FAIL** | `Text("•")` / `Text("○")` only |
| Icons dep present | **FAIL** | Missing from toml + app gradle |
| All visible tabs selectable | **FAIL** | 4/5 disabled |
| Routes for non-Today tabs | **FAIL** | Absent |
| Theme supports icons | **WARN** | Theme OK for color; no icon kit — not blocking if app module imports Icons directly |
| Screenshot matches code | **PASS** | LDPlayer dots = Text bullets |

---

## Liên kết sibling

- APK Q3: `qa/experts/apk/03_Q3_ux.md` — **UX-P1-02** dead bottom nav (cùng gốc).
- BUILD_READY Wave 3: tab ngoài Today ngoài scope scaffold — U1 vẫn FAIL vì **surface** hiện IA giả.
