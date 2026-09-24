# -*- coding: utf-8 -*-
"""Sync story art PNGs into resources catalog (to_tranh)."""
import json
from pathlib import Path

ROOT = Path(r"D:\AI Kiem Tien\ideas\BeGau")
ART = ROOT / "qa/experts/art/STORY_ART_100.json"
CAT = ROOT / "content/resources/catalog.json"
WEB = ROOT / "web/content/resources-catalog.json"
IMG = ROOT / "content/resources/images"
WEB_IMG = ROOT / "web/content/resources/images"


def default_story(title: str) -> str:
    name = title.replace("Tranh: ", "")
    return f"Đây là gì? {name}. Đang ở đâu? Rồi sẽ ra sao? Kể 3–5 câu cùng bé."


def main():
    art = json.loads(ART.read_text(encoding="utf-8"))
    cat = json.loads(CAT.read_text(encoding="utf-8"))

    # remove old to_tranh items; rebuild from art list where PNG exists
    others = [i for i in cat["items"] if i.get("category") != "to_tranh"]
    new_tranh = []
    missing = []
    for it in art["items"]:
        slug = it["slug"]
        src = IMG / f"{slug}.png"
        asset = Path(r"C:\Users\hoang\.cursor\projects\d-AI-Kiem-Tien-ideas-BeGau\assets") / f"{slug}.png"
        if not src.exists() and asset.exists():
            IMG.mkdir(parents=True, exist_ok=True)
            WEB_IMG.mkdir(parents=True, exist_ok=True)
            src.write_bytes(asset.read_bytes())
            (WEB_IMG / f"{slug}.png").write_bytes(asset.read_bytes())
        if not src.exists():
            missing.append(slug)
            continue
        # ensure web copy
        if not (WEB_IMG / f"{slug}.png").exists():
            (WEB_IMG / f"{slug}.png").write_bytes(src.read_bytes())

        entry = {
            "id": it["id"],
            "category": "to_tranh",
            "title": it["title"],
            "ageHint": it.get("ageHint") or "18–36 tháng",
            "region": "Việt Nam",
            "themes": ["ke_chuyen"],
            "source": "Minh họa thiếu nhi cho «Hôm nay chơi gì?» (board book, văn hóa VN) · đã qua C1/C2 danh mục",
            "license": "original_illustration_app",
            "image": f"images/{slug}.png",
            "howToUse": it.get("howToUse")
            or "Xem tranh cùng bé. Hỏi: Đây là gì? Đang làm gì? Rồi sẽ ra sao? Ghép 3–5 câu chuyện.",
            "storyPrompt": it.get("storyPrompt") or default_story(it["title"]),
            "body": f"Tranh màu: {it['title'].replace('Tranh: ', '')}.\nXem tranh rồi kể theo gợi ý.",
        }
        if it.get("c1") == "AGE_NOTE":
            entry["themes"] = ["ke_chuyen", "age_note"]
        new_tranh.append(entry)

    cat["items"] = others + new_tranh
    cat["version"] = 6
    cat["art_library"] = {
        "target": 100,
        "shipped": len(new_tranh),
        "missing": missing,
        "panel": "A1–A3 + C1–C3",
        "consensus": "qa/experts/art/00_CONSENSUS_ART.md",
    }
    # merge disclaimer
    cat["disclaimer"] = (
        "Đồng dao/hát ru đã rà F1–F5. Tranh kể chuyện minh họa board book VN "
        f"({len(new_tranh)}/{art.get('target_count', 100)} đã sẵn) — đã thẩm định danh mục C1/C2. "
        "Nháp — chưa chuyên gia người thật. Bài nhạc sĩ: chỉ hướng tìm."
    )

    text = json.dumps(cat, ensure_ascii=False, indent=2) + "\n"
    CAT.write_text(text, encoding="utf-8")
    WEB.write_text(text, encoding="utf-8")
    print(f"shipped={len(new_tranh)} missing={len(missing)}")
    if missing[:20]:
        print("missing sample", missing[:20])


if __name__ == "__main__":
    main()
