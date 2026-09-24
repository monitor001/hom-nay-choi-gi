# -*- coding: utf-8 -*-
"""Apply F5 folk consensus to catalog.json + sync web."""
import json
from pathlib import Path

ROOT = Path(r"D:\AI Kiem Tien\ideas\BeGau")
CAT = ROOT / "content/resources/catalog.json"
WEB = ROOT / "web/content/resources-catalog.json"

REMOVE_IDS = {
    "dd_hoa_buoi",
    "dd_ong_trang",
    "dd_bit_mat",
    "dd_co_chan_chi",
    "dd_chim_da_da",
    "dd_ong_troi",
    "dd_nha_co_ai",
    "dd_di_cho_me",
    "dd_ba_ke",
    "dd_ong_bao",
    "dd_em_be",
    "hat_ru_me_yeu",
    "hat_ru_ba_ke",
    "hat_ly_cay_da",
    "hat_chim_sau",
    "ca_me_cha_nuoi",
}

FIX_BODY = {
    "dd_nu_na": (
        "Nu na nu nống\n"
        "Cái trống nằm trong\n"
        "Con ong nằm ngoài\n"
        "Củ khoai chấm mật\n"
        "Phật ngồi Phật khóc\n"
        "Con cóc nhảy ra\n"
        "Con gà ú ụ\n"
        "\n"
        "(Biến thể theo nhà — hỏi ông bà. Bản rút: dừng sau 4 câu.)"
    ),
    "dd_chi_chi": (
        "Chi chi chành chành\n"
        "Cái đanh thổi lửa\n"
        "Con ngựa chết trương\n"
        "Ba vương bú tí\n"
        "Bắt dế đi tìm\n"
        "Ù à ù ập"
    ),
    "dd_keo_cua": (
        "Kéo cưa lừa xẻ\n"
        "Ông thợ nào khỏe\n"
        "Về ăn cơm vua\n"
        "Ông thợ nào thua\n"
        "Về bú tí mẹ"
    ),
    "dd_thang_cuoi": (
        "Thằng Cuội ngồi gốc cây đa\n"
        "Để trâu ăn lúa gọi cha ời ời\n"
        "Cha còn cắt cỏ trên trời\n"
        "Mẹ còn cưỡi ngựa đi mời quan viên\n"
        "Ông thì cầm bút cầm nghiên\n"
        "Ông thì cầm tiền đi chuộc lá đa\n"
        "\n"
        "(Tuổi nhỏ: chỉ 2–4 câu đầu. Chuyện chị Hằng kể riêng — không nhét vào lời đồng dao này.)"
    ),
    "dd_hom_na": (
        "Hom na hom nống\n"
        "Cái trống nằm trong\n"
        "Con ong nằm ngoài\n"
        "\n"
        "(Biến thể mở của «Nu na». Nhà bạn có thể hát «Ù a ù à» — dùng đúng bản nhà.)"
    ),
    "dd_mot_hai_ba": (
        "Một — hai — ba — bốn — năm\n"
        "Sáu — bảy — tám — chín — mười\n"
        "\n"
        "(Kết bài theo nhà — hỏi ông bà / cô giáo.)"
    ),
    "hat_cai_ngu": (
        "Cái ngủ mày ngủ cho lâu\n"
        "Mẹ mày đi cấy đồng sâu chưa về\n"
        "Bắt được con trắm con trê\n"
        "Cắm cổ mang về cho cái ngủ ăn"
    ),
    "hat_gio_dua": (
        "Gió đưa cành trúc la đà\n"
        "Tiếng chuông Trấn Vũ, canh gà Thọ Xương\n"
        "\n"
        "(Biến thể Huế: «Tiếng chuông Thiên Mụ…» — hỏi ông bà bản quen.)"
    ),
    "ca_con_co_dem": (
        "Con cò mà đi ăn đêm\n"
        "Đậu phải cành mềm lộn cổ xuống ao\n"
        "\n"
        "(Bản cổ còn câu tiếp — dừng đây với bé nhỏ. Không đọc phần «xáo măng».)"
    ),
    "dd_thang_bom": (
        "Thằng Bờm có cái quạt mo\n"
        "Phú ông xin đổi ba bò chín trâu\n"
        "Bờm rằng Bờm chẳng lấy trâu\n"
        "Phú ông xin đổi nắm xôi Bờm cười.\n"
        "\n"
        "(Bản đủ dài hơn — hỏi ông bà nếu muốn nghe tiếp.)"
    ),
}

REGION = {
    "dd_thang_bom": "Cả nước",
    "dd_bau_ai": "Cả nước",
    "dd_chim_chim": "Cả nước",
    "hat_ru_vi_dao": "Cả nước",
    "dd_thang_cuoi": "Cả nước",
    "dd_mot_hai_ba": "Cả nước",
    "ca_cong_cha": "Cả nước",
    "ca_anh_em": "Cả nước",
    "ca_chi_nga": "Cả nước",
    "ca_ga_mot_me": "Cả nước",
    "ca_doi_ta": "Cả nước",
    "hat_gio_dua": "Miền Bắc (Hà Nội; có biến thể Huế)",
    "dd_nu_na": "Miền Bắc",
    "dd_chi_chi": "Miền Bắc",
    "dd_keo_cua": "Miền Bắc",
    "dd_dung_dang": "Miền Bắc",
    "dd_con_co": "Miền Bắc (Lạng Sơn)",
    "dd_hom_na": "Miền Bắc",
    "dd_ba_cong": "Miền Bắc",
    "dd_rong_ran": "Miền Bắc",
    "dd_con_kien": "Miền Bắc",
    "dd_cai_bong": "Cả nước",
    "hat_ru_a_oi": "Miền Bắc",
    "hat_cai_ngu": "Miền Bắc",
    "hat_me_ganh_nuoc": "Miền Bắc",
    "hat_au_o_bac": "Miền Bắc",
    "ca_con_co_dem": "Cả nước",
    "hat_ly_cay_bong": "Nam Bộ (tham khảo)",
    "hat_dan_ga": "Cả nước / nhà trẻ",
    "hat_dan_vit": "Cả nước / nhà trẻ",
}

TIP_SONGS = {
    "hat_dan_ga": {
        "title": "Đàn gà con (hướng tìm — bài có nhạc sĩ)",
        "category": "sach",
        "license": "tip_find",
        "source": "Hướng tìm — không chép lời (nhạc Filippenko, lời Việt Anh / SGK)",
        "ageHint": "18–36 tháng",
        "region": "Cả nước / nhà trẻ",
        "themes": ["me", "choi"],
        "howToUse": "Hỏi cô giáo lớp bé hát giúp; không tải video quảng cáo.",
        "body": (
            "Bài «Đàn gà con» phổ biến nhà trẻ nhưng **có tác giả** "
            "(không phải đồng dao cổ).\n"
            "App **không** chép lời. Học miệng từ cô / ông bà."
        ),
    },
    "hat_dan_vit": {
        "title": "Đàn vịt con (hướng tìm — bài có nhạc sĩ)",
        "category": "sach",
        "license": "tip_find",
        "source": "Hướng tìm — không chép lời (thường gắn Mộng Lân)",
        "ageHint": "18–36 tháng",
        "region": "Cả nước / nhà trẻ",
        "themes": ["me", "choi"],
        "howToUse": "Hỏi cô giáo; học miệng tại lớp.",
        "body": (
            "Bài «Đàn vịt con» nhà trẻ thường có nhạc sĩ — "
            "app **không** chép lời.\nHọc từ cô giáo / bản nhà mình."
        ),
    },
}


def main():
    data = json.loads(CAT.read_text(encoding="utf-8"))
    data["version"] = 5
    data["content_status"] = "draft_unreviewed"
    data["folk_review"] = {
        "panel": "F1–F5",
        "consensus": "qa/experts/folk/00_CONSENSUS_FOLK.md",
        "note": "Persona AI nội bộ — chưa chuyên gia văn hóa dân gian người thật duyệt.",
    }
    data["curation_policy"] = (
        "Dong dao/ca dao/hat ru: chi loi da kiem F1–F5 (draft). "
        "Bai nhac si: tip_find, khong chep loi. "
        "Khong bia loi roi gan folk_traditional."
    )
    data["disclaimer"] = (
        "Đồng dao/hát dân gian đã qua rà soát nội bộ F1–F5 (persona AI) — "
        "vẫn nháp, chưa chuyên gia người thật. "
        "Bài thiếu nhi có nhạc sĩ: chỉ hướng tìm. Có biến thể nhà — hỏi ông bà."
    )

    new_items = []
    removed = []
    for it in data["items"]:
        iid = it["id"]
        if iid in REMOVE_IDS:
            removed.append(iid)
            continue
        if iid in TIP_SONGS:
            tip = dict(TIP_SONGS[iid])
            tip["id"] = iid
            new_items.append(tip)
            continue
        if iid in FIX_BODY:
            it["body"] = FIX_BODY[iid]
            it["source"] = it.get("source", "") + " · đã chỉnh theo F1/F5"
            if "đã chỉnh theo F1/F5" not in (it.get("source") or ""):
                pass
            # clean duplicate suffix
            src = it.get("source") or ""
            if src.count("· đã chỉnh theo F1/F5") > 1:
                it["source"] = src.split(" · đã chỉnh")[0] + " · đã chỉnh theo F1/F5"
            elif "đã chỉnh theo F1/F5" not in src:
                it["source"] = (src + " · đã chỉnh theo F1/F5").strip(" ·")
        if iid in REGION:
            it["region"] = REGION[iid]
        # age gates
        if iid == "dd_chi_chi":
            it["ageHint"] = "30–36 tháng (nghe PH)"
            it["howToUse"] = (
                "Chạm ngón tay. Có hình ảnh «chết trương» — "
                "chỉ nghe với PH; có thể dừng sau 2 câu đầu."
            )
        if iid == "ca_con_co_dem":
            it["ageHint"] = "36+ tháng (nghe PH)"
            it["howToUse"] = (
                "Giọng chậm; dang tay. Chỉ 2 câu đã rút — "
                "không đọc phần tiếp của bản cổ với bé nhỏ."
            )
        if iid == "dd_thang_bom":
            it["ageHint"] = "30–36 tháng (nghe)"
            it["howToUse"] = "Bé nhỏ: bản rút 4 câu. Cười là đủ."
        if iid == "dd_keo_cua":
            it["ageHint"] = "18–36 tháng"
            it["howToUse"] = "Hai người nắm tay kéo qua kéo lại như cưa. Bản miền Bắc phổ biến."
        if iid == "dd_nu_na":
            it["howToUse"] = "Vỗ tay hoặc đung đưa. Biến thể theo nhà — hỏi ông bà."
        if iid == "dd_thang_cuoi":
            it["ageHint"] = "30–36 tháng (nghe)"
            it["howToUse"] = "Chỉ mặt trăng nếu muốn; lời đồng dao khác chuyện chị Hằng."
        if iid == "hat_cai_ngu":
            it["howToUse"] = "Ru ngủ; giọng nhỏ, lặp. Bé không cần hát theo."
        if iid == "hat_gio_dua":
            it["howToUse"] = "Đung đưa; giọng êm. Hỏi ông bà biến thể quê mình."
        new_items.append(it)

    data["items"] = new_items
    text = json.dumps(data, ensure_ascii=False, indent=2) + "\n"
    CAT.write_text(text, encoding="utf-8")
    WEB.write_text(text, encoding="utf-8")

    folk = [i for i in new_items if i.get("category") in ("dong_dao", "hat")]
    tips = [i for i in new_items if i.get("license") == "tip_find"]
    print("removed", len(removed), removed)
    print("folk_left", len(folk), "tips", len(tips), "total", len(new_items))


if __name__ == "__main__":
    main()
