# -*- coding: utf-8 -*-
import json
from pathlib import Path

ROOT = Path(r"D:\AI Kiem Tien\ideas\BeGau")
c = json.loads((ROOT / "content/resources/catalog.json").read_text(encoding="utf-8"))
folk2 = [
    i
    for i in c["items"]
    if i.get("category") in ("dong_dao", "hat")
    or i["id"] in ("tip_bai_ba_me", "tip_hat_ru_bac")
]
out = ROOT / "qa/experts/folk"
out.mkdir(parents=True, exist_ok=True)
(out / "FOLK_CORPUS_FOR_REVIEW.json").write_text(
    json.dumps({"count": len(folk2), "items": folk2}, ensure_ascii=False, indent=2),
    encoding="utf-8",
)
lines = [
    "# Corpus đồng dao / hát — chờ chuyên gia duyệt\n",
    f"Tổng: {len(folk2)} mục\n",
]
for i in folk2:
    themes = ", ".join(i.get("themes") or [])
    lines.append(f"\n## `{i['id']}` — {i['title']}\n")
    lines.append(
        f"- category: {i.get('category')} · region: {i.get('region', '')} · themes: {themes}\n"
    )
    lines.append(f"- source: {i.get('source')} · license: {i.get('license')}\n")
    lines.append(f"- ageHint: {i.get('ageHint')}\n")
    lines.append(f"\n```\n{i.get('body', '')}\n```\n")
(out / "FOLK_CORPUS_FOR_REVIEW.md").write_text("".join(lines), encoding="utf-8")
print(len(folk2), "written")
