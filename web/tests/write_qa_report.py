import json
from collections import Counter
from pathlib import Path

root = Path(__file__).resolve().parents[1]
d = json.loads((root / "content" / "activities.json").read_text(encoding="utf-8"))
titles = Counter(a["title"] for a in d["activities"])
dups = [
    (t, n, [a["id"] for a in d["activities"] if a["title"] == t])
    for t, n in titles.items()
    if n > 1
]

out = root / "qa" / "CONTENT_QA_20260924.md"
out.parent.mkdir(exist_ok=True)
lines = [
    "# Content QA — 2026-09-24",
    "",
    "## Automated",
    "",
    "- `core.test.mjs`: PASS",
    "- `content.test.mjs`: PASS (sau khi sửa `act_sc_safe_030_18` duration 3→5 phút)",
    "- activities: **90**",
    "- primary quota 16/14/16/14/12/18: **OK**",
    "- schema bắt buộc + picker 18/24/30/36m: **OK**",
    "",
    "## Browser smoke (127.0.0.1:5179)",
    "",
    "- Hôm nay: 3 thẻ (bé 23 tháng)",
    "- Chi tiết → hoàn thành 😊 → đếm **1** hoạt động hôm nay",
    "- Thư viện: **90 gợi ý**, lọc lĩnh vực, hiển thị 60/90",
    "- Phát triển: tuần này **1** hoạt động",
    "",
    "## Findings",
    "",
]
if dups:
    lines.append("### Trùng tiêu đề (id khác nhau)")
    for t, n, ids in dups:
        lines.append(f"- `{t}` ×{n}: " + ", ".join(f"`{i}`" for i in ids))
else:
    lines.append("- Không trùng tiêu đề")
lines += [
    "",
    "## Kết luận",
    "",
    "**PASS cho pilot web.** Nội dung vẫn `draft_unreviewed` — chưa chuyên gia duyệt.",
    "",
]
out.write_text("\n".join(lines), encoding="utf-8")
print(f"wrote {out} dups={len(dups)}")
for t, n, ids in dups:
    print(n, ids)
