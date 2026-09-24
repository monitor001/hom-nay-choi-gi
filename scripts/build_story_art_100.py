# -*- coding: utf-8 -*-
"""Story art catalog: 10 existing + 90 new = 100 toddler VN illustrations."""
import json
from pathlib import Path

ROOT = Path(r"D:\AI Kiem Tien\ideas\BeGau")
OUT = ROOT / "qa/experts/art/STORY_ART_100.json"

STYLE = (
    "Soft children's board-book illustration, Vietnamese culture, toddler 18-36 months, "
    "warm gentle pastel colors, clear single subject, cozy Northern Vietnamese home or yard, "
    "no text, no letters, no logos, no scary imagery, no weapons, square 1:1, polished storybook quality"
)

# existing 10
EXISTING = [
    ("chuoi", "Quả chuối", "Bunch of ripe bananas on a woven bamboo mẹt tray in a Vietnamese kitchen"),
    ("ga", "Con gà", "Friendly yellow chicken in a rural Vietnamese yard with thatched house"),
    ("meo", "Con mèo", "Cute calm cat resting on a wooden chair in a Vietnamese living room"),
    ("ca", "Con cá", "Simple colorful fish in a clear bowl, child-friendly, no hooks"),
    ("mua", "Mưa trên sân", "Gentle rain in a Vietnamese courtyard with banana plant and terracotta jar"),
    ("mat_troi", "Mặt trời", "Warm friendly sun over green Vietnamese hills and rooftops"),
    ("dep", "Đôi dép", "Pair of simple children's slippers by a wooden doorstep"),
    ("coc", "Cái cốc", "Child's drinking cup with water on a low wooden table"),
    ("xe_dap", "Xe đạp", "Small bicycle in a sunny Vietnamese alley, helmet nearby"),
    ("la_cay", "Lá cây", "Large green tropical leaf held gently, outdoor garden"),
]

NEW = [
    # Gia đình
    ("me_bong_con", "Mẹ bế con", "Vietnamese mother gently holding toddler, warm home, loving"),
    ("ba_ke_chuyen", "Bà kể chuyện", "Vietnamese grandmother telling a story to toddler on a chiếu mat"),
    ("ong_dan_choi", "Ông dẫn chơi", "Vietnamese grandfather walking with toddler in a sunny yard"),
    ("ca_nha_com", "Cả nhà ăn cơm", "Family meal around low table with rice bowls, chopsticks, warm light"),
    ("om_ba", "Ôm bà", "Toddler hugging grandmother, soft smiling faces"),
    ("chao_bo", "Chào bố", "Toddler waving hello to father at the door"),
    ("anh_em_choi", "Anh em chơi", "Two young siblings sharing wooden blocks on the floor"),
    ("me_hat_ru", "Mẹ hát ru", "Mother rocking toddler to sleep in a dim cozy room"),
    # Nhà cửa
    ("cua_so", "Cửa sổ", "Open wooden window looking onto green trees and blue sky"),
    ("san_gach", "Sân gạch", "Vietnamese brick courtyard with a small plant pot"),
    ("giuong_ngu", "Giường ngủ", "Simple toddler bed with pillow and soft blanket"),
    ("den_ngu", "Đèn ngủ", "Warm night lamp glowing on a bedside table"),
    ("ban_chai", "Bàn chải đánh răng", "Child toothbrush and cup by a bathroom sink"),
    ("khan_mat", "Khăn mặt", "Soft towel hanging on a wooden peg"),
    ("ghe_go", "Ghế gỗ thấp", "Small wooden stool for a toddler"),
    ("tu_lanh", "Tủ lạnh", "Friendly household fridge door slightly open with fruit bowl"),
    ("noi_com", "Nồi cơm", "Rice cooker on kitchen counter, steam soft, safe"),
    ("chieu_coi", "Chiếu cói", "Traditional woven chiếu mat rolled partly open on floor"),
    # Động vật quen
    ("cho_con", "Con chó", "Friendly small dog sitting in yard, soft fur, no teeth bare"),
    ("vit_ao", "Con vịt", "Yellow ducklings near a pond edge, gentle water"),
    ("heo_con", "Heo con", "Cute piglet in a clean farmyard, friendly"),
    ("chim_se", "Chim sẻ", "Small sparrows on a power line / branch, soft sky"),
    ("bo_sua", "Bò", "Calm cow in green field, distant farmhouse"),
    ("ech_xanh", "Con ếch", "Friendly green frog on a lotus leaf"),
    ("buom_vang", "Bướm", "Yellow butterfly over garden flowers"),
    ("tom_song", "Tôm", "Simple shrimp illustration in clear water, not scary"),
    ("cua_bien", "Cua", "Friendly cartoon crab on sand, soft colors"),
    ("tho_trang", "Thỏ", "White rabbit in a garden, gentle"),
    # Thiên nhiên
    ("cay_dua", "Cây dừa", "Tall coconut palm against blue sky, Vietnamese coast feel"),
    ("hoa_sen", "Hoa sen", "Pink lotus flower on calm pond"),
    ("hoa_mai", "Hoa mai", "Yellow mai blossoms for Tet, soft festive, no firecrackers"),
    ("ruong_lua", "Ruộng lúa", "Green rice paddies with soft morning light"),
    ("song_nuoc", "Sông nước", "Calm river with small wooden boat far away"),
    ("cau_vong", "Cầu vồng", "Rainbow after rain over Vietnamese village roofs"),
    ("ong_trang", "Ông trăng", "Friendly round moon in night sky with soft clouds"),
    ("sao_dem", "Sao đêm", "Gentle night sky with a few stars, not dark-scary"),
    ("may_trang", "Mây trắng", "Fluffy white clouds in blue sky"),
    ("con_mua_xuan", "Mưa xuân", "Light spring drizzle on banana leaves"),
    ("soi_da", "Hòn đá", "Smooth river stones by water"),
    ("hoa_phuong", "Hoa phượng", "Red flame tree blossoms, summer Vietnam"),
    # Đồ ăn quen
    ("bat_com", "Bát cơm", "Bowl of white rice with chopsticks beside"),
    ("qua_xoai", "Quả xoài", "Ripe mango on a plate"),
    ("dua_hau", "Dưa hấu", "Watermelon slice, bright red and green"),
    ("cam_ngot", "Quả cam", "Orange fruit cut open, juicy"),
    ("hop_sua", "Hộp sữa", "Simple milk carton and glass, child-friendly"),
    ("banh_mi", "Bánh mì", "Vietnamese bánh mì loaf on cutting board, gentle"),
    ("trung_luoc", "Trứng luộc", "Soft boiled egg in a small cup"),
    ("rau_muong", "Rau muống", "Plate of cooked rau muống, green vegetables"),
    ("banh_chung", "Bánh chưng", "Square bánh chưng on banana leaf, Tet calm scene"),
    ("kem_que", "Kem que", "Simple ice cream stick, summer treat"),
    # Sinh hoạt
    ("rua_tay", "Rửa tay", "Toddler hands under tap with soap bubbles"),
    ("danh_rang", "Đánh răng", "Toddler brushing teeth at sink with parent nearby silhouette"),
    ("mac_ao", "Mặc áo", "Toddler putting on a soft t-shirt with help"),
    ("xep_khoi", "Xếp khối", "Colorful wooden blocks tower on floor"),
    ("doc_sach", "Đọc sách", "Parent and toddler looking at a picture book together"),
    ("da_bong", "Đá bóng", "Soft ball on grass, toddler feet nearby"),
    ("ve_sap", "Vẽ sáp màu", "Chunky crayons and paper on low table"),
    ("di_cho", "Đi chợ với mẹ", "Mother carrying basket at outdoor market stall with fruit"),
    ("tuoi_cay", "Tưới cây", "Toddler watering a small potted plant with tiny can"),
    ("quet_nha", "Quét nhà", "Small broom and dustpan, pretend clean-up"),
    ("uong_nuoc", "Uống nước", "Toddler sitting drinking from cup"),
    ("ngu_ngon", "Ngủ ngon", "Toddler asleep with stuffed toy, soft night light"),
    # Văn hóa VN nhẹ
    ("non_la", "Nón lá", "Traditional conical leaf hat hanging on wall peg"),
    ("ao_dai_me", "Áo dài mẹ", "Mother in soft pastel áo dài standing gently, modest"),
    ("den_long", "Đèn lồng", "Simple red-yellow lantern for Mid-Autumn, soft glow, no fire"),
    ("trong_tre_em", "Trống trẻ em", "Small toy drum for children, playful"),
    ("phong_den", "Phóng đèn trời", "NO - skip fireworks; instead: paper star lantern on stick for kids"),
    ("sao_tre", "Sao giấy Trung thu", "Child holding a paper star lantern on a stick, Mid-Autumn parade soft"),
    ("mere_tre", "Mẹt tre", "Empty woven bamboo tray on wooden table"),
    ("quat_nan", "Quạt nan", "Traditional folding fan on a stool"),
    ("am_tra", "Ấm trà", "Simple teapot and cups for family tea, calm"),
    ("tranh_dong_ho", "Đồng hồ treo tường", "Round wall clock showing morning time"),
    # Đồ chơi / đồ vật
    ("bong_mem", "Bóng mềm", "Soft colorful play ball"),
    ("xe_do_choi", "Xe đồ chơi", "Wooden toy car on floor"),
    ("gau_bong", "Gấu bông", "Cute teddy bear toy on pillow (toy only, not brand mascot)"),
    ("bup_be", "Búp bê", "Simple cloth doll sitting upright"),
    ("thuyen_giay", "Thuyền giấy", "Paper boat floating in a basin of water"),
    ("may_bay_giay", "Máy bay giấy", "Paper airplane on windowsill"),
    ("o_to_bus", "Xe buýt đồ chơi", "Colorful toy bus"),
    ("den_pin", "Đèn pin", "Small flashlight beam on floor, playful not scary"),
    ("chuong_xe", "Chuông xe đạp", "Bicycle bell close-up, shiny friendly"),
    ("gio_xach", "Giỏ xách", "Woven shopping basket with vegetables"),
    # Thêm đời sống
    ("bus_that", "Xe buýt đường phố", "Gentle Vietnamese street with green bus far away, safe sidewalk view"),
    ("truong_mam_non", "Cổng trường mầm non", "Colorful kindergarten gate with plants, morning"),
    ("cong_vien", "Công viên", "Small park with bench and young tree"),
    ("cau_truot", "Cầu trượt", "Low playground slide, soft sand, safe"),
    ("xich_du", "Xích đu", "Toddler swing in garden, parent nearby silhouette"),
    ("ho_ca", "Hồ cá", "Garden fish pond with orange fish, safe fence feel"),
    ("vuon_rau", "Vườn rau", "Small home vegetable garden beds"),
    ("gian_hoa", "Giàn hoa", "Climbing flowers on a trellis by house wall"),
    ("o_khoa", "Ổ khóa cửa", "Simple door latch close-up, household"),
    ("chia_khoa", "Chìa khóa", "House keys on a wooden hook"),
]

def prompt_for(slug, title, scene):
    return f"{STYLE}. Subject: {title} ({slug}). Scene: {scene}."

def main():
    items = []
    for slug, title, scene in EXISTING:
        items.append({
            "id": f"to_{slug}",
            "slug": slug,
            "title": f"Tranh: {title}",
            "status": "existing",
            "image": f"images/{slug}.png",
            "prompt": prompt_for(slug, title, scene),
            "storyPrompt": "",
        })
    for slug, title, scene in NEW:
        if slug == "phong_den":
            continue  # replaced by sao_tre
        items.append({
            "id": f"to_{slug}",
            "slug": slug,
            "title": f"Tranh: {title}",
            "status": "todo",
            "image": f"images/{slug}.png",
            "prompt": prompt_for(slug, title, scene),
            "scene": scene,
            "storyPrompt": "",
        })

    # ensure exactly ~100: pad or trim
    # existing 10 + new without phong_den
    while len(items) < 100:
        pad_n = len(items) - 9
        slug = f"canh_que_{pad_n}"
        title = f"Cảnh quê {pad_n}"
        scene = "Peaceful Vietnamese countryside path with green trees and small house"
        items.append({
            "id": f"to_{slug}",
            "slug": slug,
            "title": f"Tranh: {title}",
            "status": "todo",
            "image": f"images/{slug}.png",
            "prompt": prompt_for(slug, title, scene),
            "scene": scene,
        })
    items = items[:100]

    data = {
        "version": 1,
        "target_count": 100,
        "existing": 10,
        "todo": sum(1 for i in items if i["status"] == "todo"),
        "art_bible": STYLE,
        "items": items,
    }
    OUT.parent.mkdir(parents=True, exist_ok=True)
    OUT.write_text(json.dumps(data, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
    print(f"wrote {len(items)} items, todo={data['todo']}")

if __name__ == "__main__":
    main()
