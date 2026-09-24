# C3 — CONSENSUS minh họa (trước / trong gen)

> App: **Hôm nay chơi gì?** · Nguồn: C1 + C2 + Art bible  
> Persona AI — không thay họa sĩ / chuyên gia người thật.

```
STATUS: OK
SCOPE: Chốt danh mục 100 tranh kể chuyện trước gen hàng loạt
DONE: DROP bus_that + o_khoa; thay xe_day_be + mo_cua; REPROMPT scene đa chủ thể; AGE_NOTE 15 mục; storyPrompt 20 mục
FILES: qa/experts/art/00_CONSENSUS_ART.md · STORY_ART_100.json (đã apply)
DEVIATIONS: Không
BLOCKERS: Không
ERRORS: Không
NEXT_FOR_PARENT: Gen 90 PNG → copy images → catalog v6 → C3 spot-check mẫu
```

## Quyết định

| Hạng | Việc |
|---|---|
| DROP | `bus_that`, `o_khoa` |
| Thay | `xe_day_be`, `mo_cua` |
| REPROMPT | Áp scene 1 tiêu điểm theo C2 |
| AGE_NOTE | Giữ + ageHint/howToUse (C1) |
| SHIP gen | ~88 todo + 10 existing = 100 |

**APPROVE_WITH_EDITS** — tiến hành gen theo `GEN_QUEUE.json`.
