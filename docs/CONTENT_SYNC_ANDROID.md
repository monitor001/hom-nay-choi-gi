# Sync web → Android content

Canonical sources live under `content/`. Web mirrors under `web/content/`. Android packs under `gaucon/content-seed/src/main/assets/`.

## Activities (97)

| Source | Dest |
|---|---|
| `content/seed/activities_mvp_draft.json` | `web/content/activities.json` |
| same | `gaucon/content-seed/src/main/assets/content_seed.json` |

Use `scripts/wave5_apply_seed.mjs` when editing activities.

## Resource library (catalog v6 + 100 images)

| Source | Android asset |
|---|---|
| `content/resources/catalog.json` | `resources_catalog.json` |
| `content/resources/activity_links.json` | `resources_links.json` |
| `content/resources/images/*.png` | `resources/images/*.png` |

After editing folk/art, re-copy into `gaucon/content-seed/src/main/assets/` before building APK.

## App surfaces

- Tab **Tài liệu** → LibraryScreen (đồng dao / hát / tranh / sách / mẫu câu)
- Activity detail → **Tài liệu gợi ý** via `resources_links.json`
- Tab **Nhắc** → ReminderSettingsScreen (giờ PH + bật/tắt + xin quyền thông báo)
