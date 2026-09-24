# -*- coding: utf-8 -*-
"""Apply C1/C2 edits to STORY_ART_100 before batch image gen."""
import json
from pathlib import Path

ROOT = Path(r"D:\AI Kiem Tien\ideas\BeGau")
PATH = ROOT / "qa/experts/art/STORY_ART_100.json"
STYLE = (
    "Soft children's board-book illustration, Vietnamese culture, toddler 18-36 months, "
    "warm gentle pastel colors, clear single subject centered, cozy Northern Vietnamese home or yard, "
    "no text no letters no numbers no logos, no scary imagery, no weapons, square 1:1, polished storybook quality"
)

REPLACEMENTS = {
    "bus_that": {
        "slug": "xe_day_be",
        "title": "Tranh: Xe đẩy em bé",
        "scene": "A baby stroller in a sunny Vietnamese courtyard, soft colors, no street traffic",
    },
    "o_khoa": {
        "slug": "mo_cua",
        "title": "Tranh: Mở cửa chào",
        "scene": "Toddler opening a wooden door to wave hello, warm light from outside, welcoming not locked-in",
    },
}

SCENE_FIX = {
    "ca_nha_com": "One toddler and one adult at a low table with one rice bowl close-up, family soft blurred background",
    "di_cho": "Mother holding a woven basket with one clear bunch of bananas, outdoor market softly blurred",
    "sao_tre": "One toddler holding a paper star lantern on a short stick, soft festival light, no crowd",
    "mua": "Gentle rain falling in a Vietnamese brick courtyard focused on terracotta water jar, soft puddles",
    "cua_so": "Wooden window frame with one green potted plant on the sill, blue sky soft outside",
    "san_gach": "Vietnamese brick courtyard with one large potted plant centered",
    "tu_lanh": "Friendly household fridge with door slightly open showing one bowl of fruit",
    "vit_ao": "One cute yellow duckling at the edge of calm shallow water",
    "chim_se": "One small sparrow on a leafy branch, soft blue sky, no power lines",
    "ruong_lua": "Close-up of green rice stalks in a paddy, morning light",
    "song_nuoc": "A small wooden boat near the viewer on a calm river, soft green banks",
    "cau_vong": "Bright rainbow filling the sky over softly simplified village roofs",
    "sao_dem": "Soft twilight sky with two large gentle stars seen through a window edge, warm room light",
    "may_trang": "One fluffy white cloud in a bright blue sky",
    "con_mua_xuan": "One large banana leaf with clear raindrops, soft spring light",
    "soi_da": "One smooth river stone held carefully, not many small pebbles",
    "danh_rang": "Toddler brushing teeth at sink, smiling parent face visible and warm nearby",
    "xich_du": "Toddler on a low garden swing, smiling parent clearly visible beside",
    "den_pin": "Child-friendly flashlight held in small hands, tiny soft light beam, room not dark",
    "cong_vien": "Toddler sitting on one park bench under a young tree",
    "ho_ca": "One or two large orange fish in a garden pond, soft fence in background",
    "vuon_rau": "One close vegetable bed with leafy greens in a home garden",
    "tranh_dong_ho": "Round wall clock without numerals, simple hands, warm wall",
    "mat_troi": "Large friendly warm sun centered in sky, soft hills faintly in background",
    "la_cay": "One large tropical green leaf as clear single subject, no ambiguous hand",
    "cua_bien": "Friendly soft cartoon crab with claws closed gently on sand, not threatening",
    "ngu_ngon": "Toddler asleep with teddy and warm night lamp glowing, cozy not alone-in-dark",
    "kem_que": "Ice cream in a small cup with spoon, not a sharp stick, summer treat",
    "truong_mam_non": "Colorful kindergarten gate with plants, parent and toddler holding hands at gate",
}

STORY = {
    "me_bong_con": "Ai đây? Mẹ đang bế con — rồi mẹ hôn nhẹ lên má con.",
    "ba_ke_chuyen": "Bà ngồi trên chiếu kể chuyện. Bé nghe — rồi bé cười theo bà.",
    "ong_dan_choi": "Ông nắm tay cháu đi quanh sân. Rồi ông chỉ cây xanh cho cháu xem.",
    "om_ba": "Bé ôm bà thật chặt. Bà cũng ôm bé — ấm ơi là ấm.",
    "chao_bo": "Bố về tới cửa. Bé vẫy tay chào — bố cười thật to.",
    "ca_nha_com": "Bé ngồi ăn cơm với mẹ. Bát cơm thơm — rồi cùng cảm ơn nhau.",
    "me_hat_ru": "Mẹ ru bé ngủ. Bé lim dim — rồi ngủ ngon.",
    "doc_sach": "Mẹ và bé xem sách tranh. Bé chỉ hình — mẹ đọc tên.",
    "di_cho": "Mẹ xách giỏ đi chợ. Trong giỏ có chuối — về nhà sẽ ăn.",
    "non_la": "Nón lá treo trên tường. Ai đội nón? Mẹ đội ra nắng.",
    "banh_chung": "Bánh chưng xanh trên lá chuối. Tết đến — cả nhà cùng ăn.",
    "den_long": "Đèn lồng sáng dịu. Bé nhìn — đẹp quá.",
    "ao_dai_me": "Mẹ mặc áo dài. Mẹ đẹp — bé nắm vạt áo mẹ.",
    "chieu_coi": "Chiếu cói trải sàn. Bé ngồi chơi — rồi nằm nghỉ.",
    "anh_em_choi": "Anh chị em chơi khối gỗ. Xây tháp — tháp cao quá!",
    "rua_tay": "Bé rửa tay với xà phòng. Bọt bay — tay sạch rồi.",
    "xep_khoi": "Bé xếp khối màu. Tháp nghiêng — xây lại nào!",
    "gau_bong": "Gấu bông nằm trên gối. Bé ôm ngủ — chúc ngủ ngon.",
    "bat_com": "Bát cơm trắng thơm. Ai xúc? Bé tập xúc thìa.",
    "hoa_mai": "Hoa mai vàng ngày Tết. Nhà mình có mai — vui quá.",
}

AGE = {
    "sao_dem": ("24–36 tháng", "Xem cùng người lớn; phòng có đèn ngủ."),
    "ong_trang": ("18–36 tháng", "Kể ông trăng cười; để đèn phòng sáng dịu."),
    "den_pin": ("24–36 tháng", "Chơi đèn pin khi phòng vẫn sáng; có PH cạnh."),
    "ngu_ngon": ("18–36 tháng", "Nhấn đèn ngủ + thú bông; không kể bỏ một mình trong tối."),
    "vit_ao": ("18–36 tháng", "Chỉ tranh; gần nước thật luôn có người lớn."),
    "song_nuoc": ("24–36 tháng", "Quan sát tranh; không mô phỏng tự xuống sông."),
    "ho_ca": ("24–36 tháng", "Chỉ nhìn; không khuyến khích với tay xuống nước không giám sát."),
    "cua_bien": ("24–36 tháng", "Cua mềm, càng cụp — không hù kẹp."),
    "truong_mam_non": ("24–36 tháng", "Kể mẹ/bố ở gần; không ép vào một mình."),
    "danh_rang": ("18–36 tháng", "PH hiện rõ, ấm; không bóng mờ."),
    "xich_du": ("18–36 tháng", "Xích đu thấp; PH đứng cạnh rõ."),
    "sao_tre": ("24–36 tháng", "Que ngắn; có PH; ánh sáng dịu."),
    "soi_da": ("24–36 tháng", "Chỉ–kể; không cho sỏi nhỏ vào miệng."),
    "kem_que": ("24–36 tháng", "Giám sát khi ăn kem thật; tranh dùng kem ly."),
    "mo_cua": ("18–36 tháng", "Cửa mở chào — không khóa nhốt."),
}


def prompt(title, scene):
    return f"{STYLE}. Subject: {title}. Scene: {scene}."


def main():
    data = json.loads(PATH.read_text(encoding="utf-8"))
    items = []
    for it in data["items"]:
        slug = it["slug"]
        if slug in REPLACEMENTS:
            r = REPLACEMENTS[slug]
            slug = r["slug"]
            it = {
                "id": f"to_{slug}",
                "slug": slug,
                "title": r["title"],
                "status": "todo",
                "image": f"images/{slug}.png",
                "scene": r["scene"],
                "prompt": prompt(r["title"].replace("Tranh: ", ""), r["scene"]),
                "c1": "SHIP",
                "c2": "SHIP",
            }
        if slug in SCENE_FIX:
            it["scene"] = SCENE_FIX[slug]
            title = it["title"].replace("Tranh: ", "")
            it["prompt"] = prompt(title, it["scene"])
            it["c2"] = "REPROMPT_APPLIED"
        if slug in STORY:
            it["storyPrompt"] = STORY[slug]
        if slug in AGE:
            it["ageHint"], it["howToUse"] = AGE[slug]
            it["c1"] = "AGE_NOTE"
        elif "c1" not in it:
            it["c1"] = "SHIP"
        items.append(it)

    # ensure 100
    data["items"] = items[:100]
    data["todo"] = sum(1 for i in items if i.get("status") == "todo")
    data["expert_pass"] = {
        "C1": "qa/experts/art/01_C1_an_toan_cam_xuc.md",
        "C2": "qa/experts/art/02_C2_ke_chuyen.md",
        "dropped": ["bus_that", "o_khoa"],
        "replaced_with": ["xe_day_be", "mo_cua"],
    }
    PATH.write_text(json.dumps(data, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")

    # export gen queue
    todo = [i for i in data["items"] if i.get("status") == "todo"]
    qpath = ROOT / "qa/experts/art/GEN_QUEUE.json"
    qpath.write_text(json.dumps(todo, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
    print("items", len(data["items"]), "todo", len(todo))
    print("first10", [t["slug"] for t in todo[:10]])


if __name__ == "__main__":
    main()
