# Wave 4–5 — Chuyên gia QA → Senior EDU → tự cập nhật

> Thử nghiệm phụ `ideas/BeGau` · **Không đổi hướng active**  
> Parent ủy quyền (2026-09-24): sau khi có báo cáo X1–X4, **chuyên gia cao cấp giáo dục (X6)** kiểm lại; nếu **ĐỒNG Ý / APPROVE** thì **tự động cập nhật** seed + web (Wave 5).

> **Lưu ý pháp lý/sư phạm:** X6 là persona AI nội bộ — **không** thay chứng nhận chuyên gia người thật. UI vẫn ghi `draft_unreviewed` trừ khi X6 ghi rõ được nâng `internal_reviewed` (vẫn chưa “chuyên gia Bộ”).

---

## Luồng

```
X1–X4 (song song)
    ↓
X5 Editor — 00_CONSENSUS_REVIEW.md (P0/P1/P2)
    ↓
X6 Senior EDU — 05_SENIOR_EDU_SIGN_OFF.md
    ↓ verdict
 APPROVE → Wave 5 Applier (sửa seed + sync web + UI P0)
 REJECT / REVISE → dừng, báo parent
```

| ID | Vai trò | Output | Được sửa sản phẩm? |
|---|---|---|---|
| X1–X4 | Chuyên gia lĩnh vực | `qa/experts/01`…`04` | Không |
| X5 | Tổng hợp biên tập | `qa/experts/00_CONSENSUS_REVIEW.md` | Không |
| **X6** | **Chuyên gia cao cấp giáo dục** | `qa/experts/05_SENIOR_EDU_SIGN_OFF.md` | Không (chỉ verdict) |
| **W5** | Applier | seed JSON + `web/content` + UI P0 tối thiểu | **Có** nếu X6 = APPROVE |

---

## X6 — Verdict bắt buộc

File `05_SENIOR_EDU_SIGN_OFF.md` phải có khối:

```markdown
## VERDICT
STATUS: APPROVE | APPROVE_WITH_EDITS | REJECT
```

- **APPROVE** — đồng ý khung + cho phép áp P0 từ X5 (và chỉnh X6 liệt kê).
- **APPROVE_WITH_EDITS** — đồng ý có điều kiện; Applier chỉ làm đúng danh sách X6 §EDITS_REQUIRED.
- **REJECT** — không cập nhật tự động.

Nếu APPROVE / APPROVE_WITH_EDITS: Applier chạy không hỏi lại parent.

---

## Wave 5 Applier (khi được phép)

1. Backup: `content/seed/activities_mvp_draft.backup.json`
2. Áp P0 nội dung (sửa HĐ theo id; thêm HĐ mới nếu X6 cho phép và giữ ~90 hoặc nâng có kiểm soát)
3. Sync → `web/content/activities.json` + `gaucon/.../content_seed.json`
4. UI P0 từ X4/X5 nếu X6 không cấm (vd. Nhật ký stub, library “xem thêm”, disclaimer)
5. Chạy `node tests/content.test.mjs` + `core.test.mjs`
6. Ghi `qa/experts/06_APPLY_LOG.md`

Không sửa `AGENTS.md` / `PROJECT_STATE.md`. Không claim duyệt Bộ trên UI.
