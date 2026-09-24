# -*- coding: utf-8 -*-
"""Post-F5 content polish: links, sources, sync."""
import json
from pathlib import Path

ROOT = Path(r"D:\AI Kiem Tien\ideas\BeGau")
CAT = ROOT / "content/resources/catalog.json"
WEB_CAT = ROOT / "web/content/resources-catalog.json"
LINKS = ROOT / "content/resources/activity_links.json"
WEB_LINKS = ROOT / "web/content/resources-links.json"

# Clean parent-facing source (drop internal F1/F5 jargon)
SOURCE_CLEAN = {
    "dd_nu_na": "Đồng dao dân gian miền Bắc (bản phổ biến)",
    "dd_chi_chi": "Đồng dao / trò chơi ngón tay dân gian miền Bắc (bản phổ biến)",
    "dd_thang_bom": "Ca dao / đồng dao dân gian Việt Nam (bản rút)",
    "dd_keo_cua": "Đồng dao dân gian miền Bắc (bản phổ biến)",
    "dd_hom_na": "Đồng dao dân gian miền Bắc (biến thể mở của Nu na)",
    "dd_thang_cuoi": "Đồng dao dân gian Việt Nam (bản phổ biến)",
    "dd_mot_hai_ba": "Đồng dao đếm ngón tay dân gian",
    "hat_cai_ngu": "Hát ru dân gian miền Bắc (bản phổ biến)",
    "hat_gio_dua": "Ca dao / hát ru dân gian (bản Hà Nội phổ biến)",
    "ca_con_co_dem": "Ca dao dân gian Việt Nam (bản rút cho bé nhỏ)",
}

LINKS_DATA = {
    "version": 5,
    "note": "Sau F5: chỉ link id còn trong catalog; bài nhạc sĩ = tip_find; ưu tiên đồng dao/hát ru đã duyệt.",
    "links": {
        "LANG-02": ["sach_tieu_chi", "dd_nu_na", "dd_mot_hai_ba", "to_chuoi"],
        "LANG-12": ["dd_chi_chi", "dd_keo_cua", "dd_dung_dang"],
        "LANG-15": ["to_meo", "to_ga", "mau_ke_chuyen_tranh"],
        "LANG-16": ["to_mat_troi", "dd_con_co", "mau_ke_chuyen_tranh"],
        "AES-01": ["to_la_cay", "mau_ke_chuyen_tranh"],
        "AES-05": ["hat_dan_ga", "dd_con_kien", "to_ga"],
        "AES-06": ["hat_ru_a_oi", "hat_cai_ngu", "hat_au_o_bac", "dd_nu_na"],
        "AES-07": ["to_chuoi", "to_mat_troi", "mau_ke_chuyen_tranh"],
        "AES-09": ["dd_dung_dang", "dd_rong_ran", "dd_cai_bong"],
        "AES-12": ["to_chuoi", "mau_ke_chuyen_tranh"],
        "SOC-03": ["mau_cho_luot", "ca_anh_em"],
        "SOC-08": ["mau_cho_luot", "ca_chi_nga"],
        "SOC-13": ["mau_cho_luot", "dd_bau_ai"],
        "act_soc_choi_song_song_khay": ["mau_cho_luot"],
        "act_soc_dua_do_khi_xin": ["mau_cho_luot"],
        "act_sc_sleep_018_06": ["hat_ru_a_oi", "hat_cai_ngu", "dd_nu_na"],
        "act_sc_sleep_024_07": ["hat_ru_a_oi", "hat_ru_vi_dao", "hat_me_ganh_nuoc"],
        "act_sc_sleep_024_08": ["hat_au_o_bac", "hat_gio_dua"],
        "phy_01_lat_sach": ["sach_tieu_chi", "to_ga"],
        "cog_08": ["to_chuoi", "mau_ke_chuyen_tranh"],
        "act_sc_eat_018_01": ["mau_an_va", "to_coc"],
        "act_sc_eat_024_03": ["mau_an_va"],
        "act_lang_anh_gia_dinh": [
            "dd_ba_cong",
            "ca_cong_cha",
            "tip_bai_ba_me",
            "tip_hat_ru_bac",
            "to_meo",
        ],
    },
    "suggestedForTopics": {
        "dong_dao": [
            "dd_nu_na",
            "dd_chi_chi",
            "dd_keo_cua",
            "dd_dung_dang",
            "dd_ba_cong",
            "dd_rong_ran",
            "dd_con_kien",
            "dd_con_co",
        ],
        "hat_ru_mien_bac": [
            "hat_ru_a_oi",
            "hat_cai_ngu",
            "hat_au_o_bac",
            "hat_me_ganh_nuoc",
            "hat_gio_dua",
            "tip_hat_ru_bac",
        ],
        "gia_dinh_me_ba": [
            "dd_ba_cong",
            "ca_cong_cha",
            "ca_anh_em",
            "ca_chi_nga",
            "ca_ga_mot_me",
            "tip_bai_ba_me",
        ],
        "to_tranh_ke_chuyen": [
            "mau_ke_chuyen_tranh",
            "to_chuoi",
            "to_ga",
            "to_meo",
            "to_mua",
        ],
        "chia_se_tranh_do": ["mau_cho_luot"],
        "an_va": ["mau_an_va"],
    },
}


def main():
    data = json.loads(CAT.read_text(encoding="utf-8"))
    ids = {i["id"] for i in data["items"]}

    for it in data["items"]:
        if it["id"] in SOURCE_CLEAN:
            it["source"] = SOURCE_CLEAN[it["id"]]
        # strip leftover internal suffix if any
        src = it.get("source") or ""
        if "F1/F5" in src:
            it["source"] = SOURCE_CLEAN.get(it["id"], src.split(" · đã chỉnh")[0].strip())

    data["version"] = 5
    data["disclaimer"] = (
        "Đồng dao/hát ru đã rà soát nội bộ (F1–F5) — vẫn nháp, chưa chuyên gia người thật. "
        "Ưu tiên miền Bắc & gia đình. Bài có nhạc sĩ: chỉ hướng tìm. Có biến thể nhà — hỏi ông bà."
    )

    # validate links against ids
    for aid, rids in LINKS_DATA["links"].items():
        missing = [r for r in rids if r not in ids]
        if missing:
            raise SystemExit(f"missing link targets for {aid}: {missing}")
    for topic, rids in LINKS_DATA["suggestedForTopics"].items():
        missing = [r for r in rids if r not in ids]
        if missing:
            raise SystemExit(f"missing topic {topic}: {missing}")

    text = json.dumps(data, ensure_ascii=False, indent=2) + "\n"
    CAT.write_text(text, encoding="utf-8")
    WEB_CAT.write_text(text, encoding="utf-8")

    links_text = json.dumps(LINKS_DATA, ensure_ascii=False, indent=2) + "\n"
    LINKS.write_text(links_text, encoding="utf-8")
    WEB_LINKS.write_text(links_text, encoding="utf-8")

    folk = sum(1 for i in data["items"] if i["category"] in ("dong_dao", "hat"))
    print("ok catalog", len(data["items"]), "folk", folk, "link_keys", len(LINKS_DATA["links"]))


if __name__ == "__main__":
    main()
