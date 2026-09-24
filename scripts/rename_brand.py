# -*- coding: utf-8 -*-
"""Rename Gấu Con → Hôm nay chơi gì?; child nickname Gấu → con."""
import json
import re
from pathlib import Path

ROOT = Path(r"D:\AI Kiem Tien\ideas\BeGau")
APP = "Hôm nay chơi gì?"

PROTECT = [
    ("Bé Gấu An Toàn", "<<<BGAT>>>"),
    ("gấu bông", "<<<TEDDY>>>"),
    ("Gấu bông", "<<<Teddy>>>"),
    ("búp bê/gấu", "<<<DOLTED>>>"),
    ("đút gấu", "<<<FEED_TEDDY>>>"),
    ("phụ kiện gấu", "<<<ACC_TEDDY>>>"),
    ("Mắt/phụ kiện gấu", "<<<EYE_TEDDY>>>"),
]


def protect(s: str) -> str:
    for a, b in PROTECT:
        s = s.replace(a, b)
    return s


def unprotect(s: str) -> str:
    for a, b in PROTECT:
        s = s.replace(b, a)
    return s


def rename_child_bear(text: str) -> str:
    if not isinstance(text, str):
        return text
    s = protect(text)
    replacements = [
        ("Gấu Con", APP),
        ("của Gấu", "của con"),
        ("cùng Gấu", "cùng con"),
        ("Chơi cùng Gấu", "Chơi cùng con"),
        ("Tắt đèn cùng Gấu", "Tắt đèn cùng con"),
        ("Thìa của Gấu", "Thìa của con"),
        ("Gấu dọn thìa", "Con dọn thìa"),
        ("Gấu lấy gối", "Con lấy gối"),
        ("Từ tín hiệu của Gấu", "Từ tín hiệu của con"),
        ("Gấu đói quá", "Bé đói quá"),
        ("Gấu đang tập", "Con đang tập"),
        ("Gấu cầm thìa", "Con cầm thìa"),
        ("Giỏi quá, Gấu", "Giỏi quá, con"),
        ("cảm ơn Gấu", "cảm ơn con"),
        ("Cảm ơn Gấu", "Cảm ơn con"),
        ("giờ ngủ của Gấu", "giờ ngủ của con"),
        ("Chúc Gấu", "Chúc con"),
        ("Mắt Gấu", "Mắt con"),
        ("Phòng tối, Gấu", "Phòng tối, con"),
        ("Gấu tắt đèn", "Con tắt đèn"),
        ("gối của Gấu", "gối của con"),
        ("Gối Gấu", "Gối của con"),
        ("Gấu giỏi", "Con giỏi"),
        ("Gấu ngồi", "Con ngồi"),
        ("Gấu muốn", "Con muốn"),
        ("Gấu tè", "Con tè"),
        ("Bô của Gấu", "Bô của con"),
        ("Gấu lấy cơm", "Con lấy cơm"),
        ("Gấu chọn", "Con chọn"),
        ("Gấu nhắc", "Con nhắc"),
        ("phòng Gấu", "phòng con"),
        ("Hôm nay Gấu chơi", "Hôm nay con chơi"),
        ("Gấu đi đâu", "Con đi đâu"),
        ("Gấu ở trong", "Con ở trong"),
        ("Gấu có cho", "Con có cho"),
        ("miệng Gấu", "miệng con"),
        ("Gấu giữ", "Con giữ"),
        ("Đút cho gấu nào", "Đút cho búp bê nào"),
        ("đặt gấu", "đặt búp bê"),
        ("Đặt gấu", "Đặt búp bê"),
        (" Gấu ", " con "),
        ("«Gấu", "«Con"),
        ("'Gấu", "'Con"),
        ('"Gấu', '"Con'),
        ("Gấu ", "Con "),
        (" Gấu", " con"),
        ("Gấu", "con"),
        (" giúp gấu", " giúp con"),
    ]
    for a, b in replacements:
        s = s.replace(a, b)
    return unprotect(s)


def walk(obj):
    if isinstance(obj, dict):
        return {k: walk(v) for k, v in obj.items()}
    if isinstance(obj, list):
        return [walk(x) for x in obj]
    if isinstance(obj, str):
        return rename_child_bear(obj)
    return obj


def main():
    paths = [
        ROOT / "web/content/activities.json",
        ROOT / "content/seed/activities_mvp_draft.json",
        ROOT / "gaucon/content-seed/src/main/assets/content_seed.json",
        ROOT / "content/resources/catalog.json",
        ROOT / "web/content/resources-catalog.json",
    ]
    for p in paths:
        data = json.loads(p.read_text(encoding="utf-8"))
        data = walk(data)
        if "items" in data:
            for i in data.get("items", []):
                src = i.get("source") or ""
                if "Minh họa" in src or "Gấu Con" in src or "gaucon" in (i.get("license") or ""):
                    i["source"] = (
                        "Minh họa thiếu nhi cho «Hôm nay chơi gì?» "
                        "(phong cách sách board book, văn hóa VN)"
                    )
                if i.get("license") == "original_illustration_gaucon":
                    i["license"] = "original_illustration_app"
        p.write_text(json.dumps(data, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
        print("ok", p.relative_to(ROOT))

    act = json.loads((ROOT / "web/content/activities.json").read_text(encoding="utf-8"))
    blob = json.dumps(act, ensure_ascii=False)
    found = sorted(set(re.findall(r".{0,25}Gấu.{0,25}", blob)))
    print("remaining Gấu snippets:", len(found))
    for f in found:
        print(" -", f)


if __name__ == "__main__":
    main()
