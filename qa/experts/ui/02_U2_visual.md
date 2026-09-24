# U2 — Visual polish QA · Android «Hôm nay chơi gì?»

> Persona: Expert U2 · Visual / brand polish · Module `gaucon/`  
> App: **Hôm nay chơi gì?** (parent companion, toddler 18–36m)  
> Ngày: 2026-09-24 · Evidence: screenshot LDPlayer (Today S04) + static review  
> Nguồn: `Theme.kt` (GauConTheme), `TodayScreen.kt`, `WelcomeScreen.kt`, `ActivityDetailScreen.kt`, `strings.xml`, `MainActivity.kt` (NavigationBar), `@drawable/ic_logo`  
> **Không** sửa production code — chỉ đề xuất.

---

```
STATUS: FAIL
SCOPE: Visual polish S04 Today (primary complaint) + Theme/nav/Welcome/Detail hierarchy; brand mark; disclaimer tone; domain chips; touch cards.
DONE: Đọc Theme + Today + Welcome + ActivityDetail + strings + MainActivity bottom bar; đối chiếu screenshot beige/white text-only; đề xuất palette teal/coral + cream (tránh purple-on-white); EDITS_REQUIRED theo file.
FILES: qa/experts/ui/02_U2_visual.md
DEVIATIONS: Không chạy thiết bị thật — verdict dựa screenshot + mã Compose; không đánh giá illustration art pipeline (scope art experts).
BLOCKERS: Không (đủ bằng chứng cho FAIL visual).
ERRORS: Không crash visual; chỉ monotonous / thiếu hierarchy / brand yếu.
NEXT_FOR_PARENT: Applier ưu tiên P0 Theme + Today header/card/chips/disclaimer; P1 Welcome logo + Detail chips + nav container; giữ content draft_unreviewed nhưng disclaimer discreet.
```

---

## 1. Verdict ngắn

Giao diện **đúng chức năng nhưng đơn điệu**: nền cream + thẻ trắng + chữ xám, **không có logo**, domain hiện raw enum (`LANGUAGE`), disclaimer nháp chiếm vị trí hero, banner notif dài như đoạn văn. Theme hiện tại (Forest + SoftSand + Amber) **không phải purple**, nhưng `NavigationBar` Material3 mặc định trên screenshot trông **lavender** — lệch brand soft parent-companion và dễ bị đọc là “AI default”.

**PASS:** Contrast chữ tối trên cream nhìn ổn; typography serif cho greeting có điểm nhận diện nhẹ.  
**FAIL:** Hierarchy, brand mark, domain color, card affordance, disclaimer placement, nav tonal.

---

## 2. Bằng chứng (code ↔ screenshot)

| Quan sát UI | Nguồn code |
|---|---|
| Beige bg, thẻ trắng phẳng, text-only | `Theme.kt`: `background = SoftSand (#F7F1E8)`, `surface = #FFFBF6`; `ActivityCard` chỉ `Text` ×3, `elevation = 1.dp` |
| Không logo trên Today | `TodayScreen` header = `Text(greeting)` only; `ic_logo.png` có trong `res/drawable` nhưng **không** được `Image` |
| Disclaimer nháp “to” ngay dưới tiêu đề | Hardcode bodyMedium alpha 0.65 — cùng cột với hero, trước nội dung chính; không dùng `R.string.content_disclaimer` (ngắn hơn) |
| Banner notif verbose | Plain `Text` + `TextButton("Để sau")` — chiếm ~3–4 dòng trước list |
| Domain không chip / tiếng Anh enum | `"${duration} phút · ${domains.firstOrNull()?.name}"` → `LANGUAGE`, `SOCIAL_EMOTIONAL` |
| Bottom bar tím nhạt + icon `•`/`○` | `MainActivity` `NavigationBar` không set `containerColor`; icon = `Text("•")` placeholder |
| Welcome / Detail cùng “wall of text” | `WelcomeScreen` / `ActivityDetailScreen` không logo, không chip màu, disclaimer giữa flow Detail |

---

## 3. Hướng visual khuyến nghị (warm, non-generic)

**Tránh:** purple-on-white / indigo glow; cream + terracotta serif “AI brochure”; dark mode.

**Chọn:** cream surface ấm + **soft teal** (primary / trust) + **coral** (accent / CTA / active nav) — gần “chơi cùng con ngoài đời”, không clinic, không toy-neon.

| Token | Hex gợi ý | Vai trò |
|---|---|---|
| `CreamBg` | `#F6F0E6` | background (giữ gần SoftSand) |
| `SurfaceCard` | `#FFFCF7` | card; viền `#E8DFD2` 1dp thay vì shadow nặng |
| `Teal` | `#2A6F6B` | primary, selected nav, link |
| `TealSoft` | `#D8EBE8` | chip bg / banner container nhẹ |
| `Coral` | `#E07A5F` | secondary / CTA “Đổi gợi ý” / active indicator |
| `Ink` | `#1E2B28` | onBackground (giữ gần Ink hiện tại) |
| `InkMuted` | `#5C6B66` | meta, disclaimer discreet |
| Domain chips | xem bảng §5 | 1 màu soft / domain, chữ Ink hoặc deep tint |

Headline serif **giữ** cho greeting (đã có trong `scaledTypography`); body/title sans mặc định Material OK — đừng thêm font mới Wave này trừ khi product chốt.

---

## 4. Issue table

| ID | Pri | Màn / khu vực | Vấn đề | Impact |
|---|---|---|---|---|
| **VIS-P0-01** | P0 | Theme + Nav | Palette / NavigationBar mặc định đọc lavender trên screenshot; primary Forest chưa đủ “chơi”; secondary Amber ít xuất hiện trên Today → cảm giác beige-only monotonous | Brand cảm nhận “draft UI” |
| **VIS-P0-02** | P0 | Today header | Không `Image(ic_logo)` — app name «Hôm nay chơi gì?» không có mark trên S04 | Không brand test: bỏ nav vẫn không nhận app |
| **VIS-P0-03** | P0 | Today cards | Thẻ text-only, elevation 1dp, không border/leading accent; domain raw enum; touch visual yếu | Khó scan 2–3 gợi ý; không cảm giác “chạm được” |
| **VIS-P0-04** | P0 | Today disclaimer | Dòng `draft_unreviewed` đặt ngay dưới hero greeting → cạnh tranh hierarchy; copy dài hơn `strings.xml` | Nháp thành “hero”; PH thấy app chưa tin cậy |
| **VIS-P1-01** | P1 | Today notif banner | Đoạn văn full-width + “Để sau” text button — clutter trước cards | Làm list gợi ý bị đẩy xuống; monotonous hơn |
| **VIS-P1-02** | P1 | Welcome | 3 slide text-only, không logo / accent bar | Onboarding không “mềm” / brand |
| **VIS-P1-03** | P1 | Activity Detail | Domain string raw; disclaimer giữa title và “Mục tiêu”; không section tint (An toàn) | Detail cùng monotonous; safety không nổi |
| **VIS-P1-04** | P1 | Bottom nav | Icon `•`/`○`; container màu lệch cream/teal | Screenshot “tím nhạt” + placeholder |
| **VIS-P2-01** | P2 | Strings / i18n | Label domain tiếng Anh enum; chưa map VI (Ngôn ngữ, Xã hội–cảm xúc…) | Chip đẹp vẫn khó đọc với ông bà |
| **VIS-P2-02** | P2 | Typography scale | Chỉ scale font; chưa `labelSmall` riêng cho chip/meta | Hierarchy meta vs body vẫn phẳng |

**Nội dung vẫn `draft_unreviewed`:** giữ disclaimer — **discreet**, không hero (labelSmall + InkMuted, hoặc footer list / góc header nhỏ). Không xóa trạng thái nháp.

---

## 5. Domain color chips (đề xuất)

`Domain` enum (`Enums.kt`): PHYSICAL, COGNITIVE, LANGUAGE, SOCIAL_EMOTIONAL, AESTHETIC, SELF_CARE.

| Domain | Chip bg | Chip text | Nhãn VI gợi ý |
|---|---|---|---|
| LANGUAGE | `#DCEEEA` | `#1F5C56` | Ngôn ngữ |
| SOCIAL_EMOTIONAL | `#F3E0DC` | `#8B3F33` | Xã hội–cảm xúc |
| PHYSICAL | `#E3EBD8` | `#3D5A2E` | Vận động |
| COGNITIVE | `#E4E8F2` | `#3A4568` | Nhận thức |
| AESTHETIC | `#F0E6F2` | `#5A3D62` | Thẩm mỹ |
| SELF_CARE | `#EDE6DA` | `#5C4A32` | Tự phục vụ |

Chip: `AssistChip` / custom `Row` + `RoundedCornerShape(50)` + padding H12 V6; **không** pill tím generic. Thời lượng: meta riêng (`5 phút`) cạnh chip, không nhét chung một dòng `· ENUM`.

---

## 6. Hierarchy mục tiêu (Today)

1. **Row:** logo 40–48dp (`ic_logo`) + greeting (headlineMedium)  
2. **List cards** (primary content) — min height ~72–88dp padding, border cream-warm, optional 4dp leading bar theo domain color  
3. **Disclaimer** discreet: dưới greeting **hoặc** cuối LazyColumn — 1 dòng ngắn từ `strings.xml`  
4. **Notif:** Surface nhỏ `TealSoft` + 1 câu rút + TextButton; hoặc gộp icon + 1 dòng  

Draft **không** được đứng giữa logo và thẻ như hiện tại.

---

## ## EDITS_REQUIRED

### P0 — làm ngay (visual FAIL → gần PASS)

| File | Thay đổi cụ thể |
|---|---|
| `gaucon/core/designsystem/.../Theme.kt` | Đổi `LightColors`: primary → soft teal `#2A6F6B`; secondary → coral `#E07A5F`; background cream `#F6F0E6`; surface `#FFFCF7`; thêm `primaryContainer` / `secondaryContainer` / `surfaceVariant` tonal teal-cream (tránh M3 suy ra lavender cho NavigationBar). Giữ `on*` contrast AA. Optional: export `DomainColors` object map `Domain → Color`. |
| `gaucon/feature/today/.../TodayScreen.kt` | Header `Row(verticalAlignment=Center)`: `Image(painterResource(R.drawable.ic_logo), …, Modifier.size(44.dp))` + greeting. Disclaimer: dùng string ngắn / `content_disclaimer`, `labelSmall` + `InkMuted`, **sau** greeting với Spacer nhỏ **hoặc** dưới list — không bodyMedium cạnh hero. `ActivityCard`: tăng padding 20.dp, `border` 1.dp `#E8DFD2`, elevation 0–2; `Row` chip domain (màu + nhãn VI) + duration; title `titleLarge`; goal 2 dòng max nếu cần. Leading 4.dp bar theo domain optional. |
| `gaucon/app/.../strings.xml` | Thêm nhãn domain VI; giữ / siết `content_disclaimer` 1 dòng (đã có). Today bỏ hardcode dài “Nội dung nháp (draft_unreviewed)…”. |

### P1

| File | Thay đổi cụ thể |
|---|---|
| `gaucon/feature/today/.../TodayScreen.kt` | Notif banner: `Surface(color=primaryContainer, shape=RoundedCornerShape(12))` + 1 câu rút gọn + `Để sau`; không đoạn văn trần. |
| `gaucon/app/.../MainActivity.kt` | `NavigationBar(containerColor = MaterialTheme.colorScheme.surface)` hoặc cream; `selected` dùng teal/coral indicator; thay `Text("•")` bằng Icons nhỏ hoặc logo-tint cho tab Hôm nay (Wave 3: tab khác vẫn disabled nhưng đừng lavender). |
| `gaucon/feature/onboarding/.../WelcomeScreen.kt` | Slide 1: logo + title «Hôm nay chơi gì?»; accent coral dưới title 3–4dp; page dots teal/coral. Disclaimer slide 3 giữ nhưng `labelMedium` muted — không style ngang headline. |
| `gaucon/feature/activity/.../ActivityDetailScreen.kt` | Domain chips giống Today; disclaimer discreet dưới meta; block “An toàn” nền `#F8EDE8` / chữ ink (không đỏ alarm). |

### P2

| File | Thay đổi cụ thể |
|---|---|
| `Theme.kt` typography | `labelSmall` / `labelMedium` cố định cho chip & disclaimer (scale theo `fontScale`). |
| Shared UI (optional `designsystem`) | Composable `DomainChip(domain)` + `ActivityCardChrome` để Today/Detail/Library sau này đồng bộ. |

---

## 7. Acceptance (visual)

- [ ] Today: logo `ic_logo` visible cạnh greeting trong first viewport  
- [ ] Disclaimer nháp ≤ 1 dòng muted, **không** chiếm vị trí hero  
- [ ] Mỗi thẻ: domain chip màu + phút; không raw `SOCIAL_EMOTIONAL`  
- [ ] Card rõ touch target (≥48dp chiều cao hữu ích), border/leading khác nền cream  
- [ ] Nav không lavender; tonal cream/teal/coral khớp Theme  
- [ ] Không purple-on-white; không thêm glow / pill cluster trang trí  

---

## 8. Ngoài scope U2

Empty Today / seed race, dead nav UX, S06 feedback → Q3 UX / APK experts.  
Illustration spot art trong card → art experts (U2 chỉ yêu cầu color chip + logo sẵn có).
