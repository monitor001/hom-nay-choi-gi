# Q2 — Crash / khởi động (Android)

> Persona: crash / Application / Hilt / Room / seed · đội APK (`00_CHARTER.md`)  
> App: `com.gaucon.app` · module `gaucon/`  
> Ngày: 2026-09-24  
> **Không** sửa code trong lượt này — chỉ báo cáo + `EDITS_REQUIRED`

---

## STATUS

**FAIL** (static review — P0 launch path) · device logcat **BLOCKED**

| Check | Result |
|---|---|
| `adb devices` | SDK adb OK (`%LOCALAPPDATA%\Android\Sdk\platform-tools\adb.exe`); **0 devices** attached |
| logcat on launch | **BLOCKED** — không có device/emulator |
| `:app:compileDebugKotlin` | **OK** (exit 0) — không còn lỗi import kiểu `ReminderLog` |
| `runBlocking` on main | **Không thấy** |
| Hilt providers (Room DAOs, repos, picker, APIs) | **Đủ** trong `AppModules.kt` |
| `ReminderLog` import (`Mappers.kt`) | **Đã có** — không cần patch thêm |

---

## Issue table

| ID | Sev | Where | Symptom | Likely trigger |
|---|---|---|---|---|
| **C1** | **P0** | `MainActivity.kt` L53–66 | Snapshot / multithreaded Compose state write → crash hoặc UI treo khi cold start | Mọi lần mở app: `fontScale` / `onboardingDone` gán **bên trong** `withContext(Dispatchers.IO)` |
| **C2** | **P1** | `GauConApp.kt` L26–28 + `ContentSeedLoader` | First-session Today trống / seed “chết im” | Seed fire-and-forget + `runCatching` nuốt mọi lỗi; Today/`GetTodayPicks` không chờ seed |
| **C3** | **P1** | `GauConApp.kt` L31–34 | `UninitializedPropertyAccessException: workerFactory` | `Configuration.Provider` đọc `lateinit workerFactory` nếu WorkManager init trước `Application.onCreate` (hiếm sau khi đã remove initializer; vẫn mỏng) |
| **C4** | **P2** | `ActivityDetailScreen.kt` L67–69 | `!!` trên `safety` | Không crash khi có `isNullOrBlank` guard; dễ vỡ nếu refactor guard |
| **C5** | **P2** | `OnboardingViewModel.saveChild` | Lỗi Room/WM nuốt im — user bấm Lưu không vào Today | Exception trong `viewModelScope.launch` không `try`/`onFailure` UI |
| — | OK | `AppModules` Room + DAOs + `ReminderLogDao` | Graph compile | `fallbackToDestructiveMigration()` OK MVP |
| — | OK | Manifest WM initializer `tools:node="remove"` | Khớp Hilt `Configuration.Provider` | — |
| — | OK | Không `!!` / `runBlocking` trên đường Application | — | Chỉ `safety!!` (C4) |

---

## P0 crash list (launch / first paint)

1. **C1 — Compose state off Main** (`MainActivity.onCreate` → `LaunchedEffect`): mọi cold start đọc DataStore trên IO rồi **ghi** `mutableStateOf` trên background thread → đường crash khởi động rõ nhất trong static review.

---

## Review notes (không P0)

- **Hilt graph:** `ContentSeedLoader`, `UserPreferences`, repos, `HiltWorkerFactory`, `DailyActivityWorker` đều `@Inject` / bind đủ; compile xác nhận.
- **Room:** `build()` không mở DB; query qua coroutine IO — không thấy main-thread DB.
- **Seed JSON ↔ DTO:** camelCase + `ignoreUnknownKeys` khớp `content_seed.json`; `Domain.valueOf` có `runCatching` → không crash enum lạ.
- **First session WM:** `OnboardingViewModel` gọi `ReminderScheduler` → `WorkManager.getInstance` **sau** `Application.onCreate` → thường an toàn nếu C3 không bị early init.

---

## EDITS_REQUIRED

### E1 — P0: Compose state chỉ ghi trên Main (`MainActivity.kt`)

**File:** `gaucon/app/src/main/java/com/gaucon/app/MainActivity.kt`  
**Replace** L53–66:

```kotlin
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
        scale to prefs.isOnboardingDone()
    }
    fontScale = resolved
    onboardingDone = done
}
```

### E2 — P1: Seed không nuốt lỗi + Today chờ seed

**A. `GauConApp.kt` L26–28** — log failure (ít nhất):

```kotlin
appScope.launch {
    runCatching { contentSeedLoader.upsertIfNewer() }
        .onFailure { android.util.Log.e("GauConApp", "content seed failed", it) }
}
```

**B. `TodayViewModel.kt`** — inject `ContentSeedLoader`, gọi trước picks (idempotent khi `version` đã set):

```kotlin
// constructor + field
private val contentSeedLoader: ContentSeedLoader,

fun load() {
    viewModelScope.launch {
        _uiState.update { it.copy(loading = true) }
        runCatching { contentSeedLoader.upsertIfNewer() }
        // ... existing child / getTodayPicks ...
    }
}
```

Feature module cần `implementation(project(":content-seed"))` nếu chưa có.

### E3 — P1: Guard `HiltWorkerFactory` (`GauConApp.kt`)

```kotlin
override val workManagerConfiguration: Configuration
    get() {
        check(::workerFactory.isInitialized) {
            "HiltWorkerFactory accessed before Application.onCreate / Hilt inject"
        }
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }
```

Giữ `super.onCreate()` **trước** mọi code khác trong `onCreate` (hiện đúng).

### E4 — P2: bỏ `!!` (`ActivityDetailScreen.kt` L67–69)

```kotlin
val safetyText = activity.safety?.takeIf { it.isNotBlank() }
if (safetyText != null) {
    Text("An toàn", style = MaterialTheme.typography.titleMedium)
    Text(safetyText, style = MaterialTheme.typography.bodyLarge)
}
```

### E5 — P2 (optional): `OnboardingViewModel.saveChild`

Bọc `try/catch` hoặc `runCatching`; chỉ gọi `onDone()` khi thành công; expose lỗi UI (SnackBar / Text).

---

## Device

```
adb devices
List of devices attached
(empty)
```

→ **BLOCKED** cho logcat launch. Khi có máy:  
`adb install -r <apk>` → `adb logcat --pid=$(adb shell pidof -s com.gaucon.app)` và xác nhận C1 đã hết sau E1.

---

## Return for parent / Q5

| Field | Value |
|---|---|
| **STATUS** | **FAIL** (P0 C1) · logcat **BLOCKED** |
| **P0** | C1 Compose state write on IO in `MainActivity` LaunchedEffect |
| **P1** | C2 seed race/silent fail · C3 WM `lateinit` guard |
| **Compile** | OK — no ReminderLog-style missing imports |
| **EDITS** | E1–E4 (E5 optional) |
