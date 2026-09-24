# U3 — Touch targets & a11y (Compose / `gaucon`)

> Persona: Expert U3 · App: **Hôm nay chơi gì?** · Module `gaucon/`  
> Ngày: 2026-09-24 · Emulator quan sát: **LDPlayer** (screenshot PH)  
> Nguồn: `MainActivity.kt` / `GauConNav`, `TodayScreen.kt`, `ActivityDetailScreen.kt`, `Theme.kt`, `UserPreferences`  
> **Không** sửa production code — chỉ audit + `EDITS_REQUIRED`.

---

```
STATUS: FAIL
SCOPE: Bottom nav touch / a11y; Scaffold + edge-to-edge; fontScale path; card min-size; contentDescription.
DONE: Code review MainActivity NavigationBar + Today cards + Theme fontScale; đối chiếu screenshot LDPlayer (dot icons, 4 tab xám).
FILES: qa/experts/ui/03_U3_touch.md
DEVIATIONS: Không đo dp thật trên thiết bị (ước lượng từ Material3 defaults + screenshot); Wave 3 stub tab documented nhưng vẫn FAIL vì UX dead-nav.
BLOCKERS: Không.
ERRORS: Không crash; lỗi là tương tác / a11y.
NEXT_FOR_PARENT: Applier ưu tiên U3-P0-01 (dead tabs) trước Closed testing; gắn Icon + contentDescription cùng PR.
```

---

## Tóm tắt (PH / LDPlayer)

Bottom bar hiện **5 tab** nhưng chỉ **Hôm nay** chọn được. Bốn tab còn lại xám, `enabled=false`, `onClick={}` — PH thấy IA rồi không vào được → cảm giác **app hỏng**. «Icon» thực tế là glyph `Text("•")` / `Text("○")` → chấm nhỏ, **không** đạt nhận diện icon / hit affinity.

---

## Bảng audit

| Hạng mục | Bằng chứng code | Verdict |
|---|---|---|
| **NavigationBarItem `enabled=false`** | `MainActivity.kt` L113–140: Thư viện / Phát triển / Nhật ký / Thêm | **FAIL (P0)** |
| **`onClick` rỗng** | Tất cả 5 item `onClick = { }` (kể cả Hôm nay) | **FAIL (P0)** |
| **Icon = Text glyph** | `icon = { Text("•") }` / `Text("○")` — không `Icon` / vector | **FAIL (P1)** |
| **`contentDescription`** | Không có trên icon tab; feature screens không dùng `Icon`/`contentDescription` | **FAIL (P1)** |
| **Min 48dp target** | Material3 NavBarItem thường ≥48dp chiều cao bar; glyph chấm làm **visual** hit affinity rất nhỏ; `ActivityCard` chỉ `.clickable` không `heightIn(min=48.dp)` / `minimumInteractiveComponentSize` tường minh | **WARN / P1** |
| **Scaffold padding** | `NavHost(modifier = Modifier.padding(padding))` — content nhận `paddingValues` bottom bar | **PASS** (list không bị che bởi bar *nếu* insets OK) |
| **Edge-to-edge** | `enableEdgeToEdge()`; **không** `WindowInsets` / `safeDrawing` / `systemBarsPadding` trong feature screens | **WARN (P1)** — status bar / gesture inset có thể đè chữ boot & header |
| **`fontScale` path** | Prefs + auto `1.15f` nếu system ≥ 1.3; `GauConTheme` nhân lại `sp` trong Typography trong khi Compose đã scale `sp` theo `LocalDensity.fontScale` | **WARN (P2)** — rủi ro double-scale / thiếu UI đổi cỡ trên Android |

---

## Chi tiết phát hiện

### 1. Dead tabs (P0) — khớp screenshot

```106:140:gaucon/app/src/main/java/com/gaucon/app/MainActivity.kt
                    NavigationBarItem(
                        selected = true,
                        onClick = { },
                        icon = { Text("•") },
                        label = { Text("Hôm nay") },
                    )
                    // Other tabs placeholder — inactive Wave 3
                    NavigationBarItem(
                        selected = false,
                        onClick = { },
                        enabled = false,
                        icon = { Text("○") },
                        label = { Text("Thư viện") },
                    )
                    // … Phát triển / Nhật ký / Thêm — cùng pattern
```

- 4 tab **hiện diện** nhưng không nhận touch → đúng báo cáo PH «không chọn được».
- `enabled=false` + màu xám = trông như **broken**, không như «chưa mở».
- Tab Hôm nay cũng `onClick = { }` — vô hại khi đã selected, nhưng không có semantics / route guard rõ.

**Khuyến nghị (bắt buộc chọn một):**

1. **Ẩn** tab chưa ship (chỉ còn «Hôm nay» hoặc 1–2 tab thật), **hoặc**
2. Giữ đủ 5 tab nhưng **`enabled=true`**, `onClick` → navigate màn stub **«Sắp có»** (copy ngắn + CTA về Hôm nay).

Không để tab visible + disabled im lặng.

### 2. Icon thiếu / hit affinity (P1)

- Glyph `•` / `○` trên screenshot = chấm tím/xám nhỏ — PH không đọc được nghĩa tab bằng icon.
- `Text` trong slot `icon` **không** có `contentDescription` → TalkBack đọc kém / chỉ dựa label (Material thường ghép label; icon vẫn nên vector + CD hoặc `Modifier.semantics { invisibleToUser() }` nếu decorative).
- Đề xuất: `Icons.Outlined.*` (hoặc drawable brand) + `contentDescription = "Hôm nay" | "Thư viện" | …` (VI); đảm bảo vùng item ≥ **48×48 dp**.

### 3. Scaffold + edge-to-edge (P1)

| Điểm | Quan sát |
|---|---|
| Bottom inset từ Scaffold | Có — `padding` truyền vào NavHost |
| `enableEdgeToEdge()` | Có trong `onCreate` |
| Feature `padding` | Chỉ `20.dp` cứng — **không** cộng statusBars |
| Boot text | `Text("Đang mở…")` không `fillMaxSize` / không inset |

Rủi ro LDPlayer / gesture nav: header Today hoặc «← Quay lại» sát mép trên; list cuối card có thể OK nhờ Scaffold nhưng **không** chứng minh safe-area đầy đủ.

### 4. `fontScale` (P2 / a11y path)

```53:66:gaucon/app/src/main/java/com/gaucon/app/MainActivity.kt
            LaunchedEffect(systemScale) {
                val (resolved, done) = withContext(Dispatchers.IO) {
                    val stored = prefs.fontScale()
                    val scale = when {
                        stored != 1.0f -> stored
                        systemScale >= 1.3f -> {
                            prefs.setFontScale(1.15f)
                            1.15f
                        }
                        else -> 1.0f
                    }
                    ...
```

- Theme nhân `fontSize * fontScale` trong khi `sp` đã theo system → với user accessibility large text, có thể **phóng quá** hoặc lệch vs web S14-lite.
- Android **chưa** có màn «Thêm» để đổi Vừa/Lớn (tab Thêm dead) → path fontScale gần như chỉ auto-write prefs.

### 5. Clickable cards (P1)

```88:94:gaucon/feature/today/src/main/java/com/gaucon/feature/today/TodayScreen.kt
private fun ActivityCard(...) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
```

- Nội dung 3 dòng + `padding(16.dp)` thường >48dp chiều cao → **thực tế ổn** trên list đầy đủ.
- Thiếu: `role = Role.Button`, `contentDescription` / merged semantics tiêu đề; không `heightIn(min = 48.dp)` tường minh (card ngắn / empty state sau này dễ lệch).
- `TextButton` «Để sau» / «Đổi gợi ý»: Material3 mặc định có min interaction — **PASS tạm**; vẫn nên kiểm tay với fontScale 1.3.

### 6. Màn khác (ghi nhận)

- `ActivityDetailScreen`: `TextButton("← Quay lại")` — OK target Material; thiếu `contentDescription` riêng nếu bỏ mũi tên text.
- Không có `Icon` UI nào trong feature → `contentDescription` gần như **0** trên toàn app Compose.

---

## ## EDITS_REQUIRED

### P0

| ID | Vấn đề | Sửa đề xuất |
|---|---|---|
| **U3-P0-01** | 4 tab `enabled=false` + `onClick={}` — dead nav, PH tưởng hỏng | **Tất cả tab đang hiện phải clickable.** Wave 3: (A) ẩn tab chưa có **hoặc** (B) `enabled=true` → route/composable stub **«Sắp có»** + nút về Hôm nay. Cấm để tab visible disabled không giải thích. |

### P1

| ID | Vấn đề | Sửa đề xuất |
|---|---|---|
| **U3-P1-01** | Icon = `Text("•"/"○")` — thiếu nhận diện, hit affinity thấp | Thay bằng `Icon` / vector; giữ label VI; vùng item ≥ **48dp**. |
| **U3-P1-02** | Thiếu `contentDescription` icon / semantics card | Mỗi icon tab: CD tiếng Việt khớp label; `ActivityCard`: `semantics { role = Role.Button }` + CD = title (hoặc `clickable(onClickLabel=…)`). |
| **U3-P1-03** | `enableEdgeToEdge` không kèm insets feature | `Modifier.windowInsetsPadding(WindowInsets.safeDrawing)` (hoặc dựa `Scaffold` contentWindowInsets đầy đủ) cho Today / Activity / boot. |
| **U3-P1-04** | Card `clickable` không min-size tường minh | `Modifier.heightIn(min = 48.dp)` + `fillMaxWidth`; ưu tiên `Card(onClick=…)` M3 nếu API module cho phép. |

### P2

| ID | Vấn đề | Sửa đề xuất |
|---|---|---|
| **U3-P2-01** | `fontScale` Theme × system `sp` + tab Thêm dead | Document công thức scale (tránh double); khi mở «Thêm»/Sắp có: UI Vừa/Lớn như web; test TalkBack + fontScale 1.3 trên LDPlayer/máy thật. |

---

## Checklist khuyến nghị (Definition of Done U3)

- [ ] Mọi tab **visible** đều nhận touch và dẫn tới màn thật hoặc **«Sắp có»**
- [ ] Không còn `NavigationBarItem(..., enabled = false)` cho tab đang vẽ trên UI
- [ ] Icon vector + `contentDescription` VI; không dùng chấm `Text` làm icon
- [ ] Mọi control tương tác ≥ **48×48 dp** (verify Layout Inspector / screenshot có scale)
- [ ] Scaffold / safeDrawing: không cắt header, không sát status bar
- [ ] Card hoạt động: semantics button + CD

---

```
STATUS: FAIL
TOP_TOUCH_FAIL: U3-P0-01 Dead bottom tabs (enabled=false) — PH không chọn được; icon glyph chấm
```
