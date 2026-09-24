# -*- coding: utf-8 -*-
"""Append Northern VN folk rhymes/lullabies about family to resource catalog."""
import json
from pathlib import Path

ROOT = Path(r"D:\AI Kiem Tien\ideas\BeGau")
CAT = ROOT / "content/resources/catalog.json"
WEB = ROOT / "web/content/resources-catalog.json"

NEW = [
  # --- Đồng dao miền Bắc ---
  {
    "id": "dd_ba_cong",
    "category": "dong_dao",
    "title": "Bà còng đi chợ trời mưa",
    "ageHint": "24–36 tháng",
    "region": "Miền Bắc",
    "themes": ["ba", "gia_dinh"],
    "source": "Đồng dao dân gian miền Bắc",
    "license": "folk_traditional",
    "howToUse": "Giọng vui; chỉ «bà» trong tranh/ảnh gia đình nếu có.",
    "body": "Bà còng đi chợ trời mưa\nHỏi thăm cậu ấm hết chưa cậu về\nCậu về cậu mặc áo the\nCậu đi câu cá đứng kề ao sâu.",
  },
  {
    "id": "dd_rong_ran",
    "category": "dong_dao",
    "title": "Rồng rắn lên mây",
    "ageHint": "24–36 tháng",
    "region": "Miền Bắc",
    "themes": ["choi_nhom"],
    "source": "Đồng dao / trò chơi dây dân gian miền Bắc",
    "license": "folk_traditional",
    "howToUse": "Nắm tay thành dây dài; đi theo nhịp. Bé nhỏ: chỉ hát 4 câu đầu.",
    "body": "Rồng rắn lên mây\nCó cây nêu cao\nCó nhà sàn thấp\nCó anh học trò\nCó cô tát nước bên sông\nRồng rắn lên mây…",
  },
  {
    "id": "dd_con_kien",
    "category": "dong_dao",
    "title": "Con kiến mà leo cành đa",
    "ageHint": "18–36 tháng",
    "region": "Miền Bắc",
    "themes": ["choi"],
    "source": "Đồng dao dân gian Việt Nam (phổ biến miền Bắc)",
    "license": "folk_traditional",
    "howToUse": "Ngón tay «leo» lên cánh tay bé; cười, không hù.",
    "body": "Con kiến mà leo cành đa\nLeo phải cành cụt leo ra leo vào\nCon kiến mà leo cành đào\nLeo phải cành cụt leo vào leo ra.",
  },
  {
    "id": "dd_cai_bong",
    "category": "dong_dao",
    "title": "Cái bống cái bang",
    "ageHint": "18–30 tháng",
    "region": "Miền Bắc",
    "themes": ["choi"],
    "source": "Đồng dao dân gian",
    "license": "folk_traditional",
    "howToUse": "Vỗ nhẹ mu bàn tay theo nhịp.",
    "body": "Cái bống cái bang\nNằm trong cái hang\nChui ra chui vào\nÚ òa!",
  },
  {
    "id": "dd_thang_cuoi",
    "category": "dong_dao",
    "title": "Thằng Cuội ngồi gốc cây đa",
    "ageHint": "24–36 tháng",
    "region": "Miền Bắc",
    "themes": ["trang", "gia_dinh"],
    "source": "Đồng dao / chuyện trăng dân gian",
    "license": "folk_traditional",
    "howToUse": "Chỉ mặt trăng buổi tối; kể ngắn chị Hằng – Cuội.",
    "body": "Thằng Cuội ngồi gốc cây đa\nBên cạnh có chị Hằng Nga ở cùng\nCó chú chó trắng chung lòng\nCùng nhìn trần thế mênh mông đêm dài.\n\n(Biến thể lời theo nhà — hỏi ông bà bản quen.)",
  },
  {
    "id": "dd_ong_trang",
    "category": "dong_dao",
    "title": "Ông trăng ơi",
    "ageHint": "18–36 tháng",
    "region": "Miền Bắc",
    "themes": ["trang"],
    "source": "Đồng dao dân gian (biến thể phổ biến miền Bắc)",
    "license": "folk_traditional",
    "howToUse": "Chỉ trăng; giọng nhỏ.",
    "body": "Ông trăng ơi ông trăng ơi\nÔng lên cao mãi rồi\nSáng cho bé ngủ ngon\nMai dậy bé chơi sân.",
  },
  {
    "id": "dd_mot_hai_ba",
    "category": "dong_dao",
    "title": "Một hai ba bốn (đếm ngón)",
    "ageHint": "18–36 tháng",
    "region": "Miền Bắc",
    "themes": ["dem", "gia_dinh"],
    "source": "Đồng dao đếm ngón tay dân gian",
    "license": "folk_traditional",
    "howToUse": "Cầm từng ngón tay bé đếm chậm.",
    "body": "Một — hai — ba — bốn — năm\nSáu — bảy — tám — chín — mười\nMười ngón tay xinh\nCủa bé nhà mình.",
  },
  {
    "id": "dd_bit_mat",
    "category": "dong_dao",
    "title": "Bịt mắt bắt dê",
    "ageHint": "30–36 tháng",
    "region": "Miền Bắc",
    "themes": ["choi_nhom"],
    "source": "Trò chơi dân gian / đồng dao",
    "license": "folk_traditional",
    "howToUse": "Chơi nhẹ trong phòng trống; người lớn dẫn. Không bắt buộc bịt mắt với bé rất nhỏ.",
    "body": "Bịt mắt bắt dê\nDê chạy vòng quanh\nAi bắt được dê\nĐược làm ông/bà chủ một lần.",
  },
  {
    "id": "dd_co_chan_chi",
    "category": "dong_dao",
    "title": "Cổ chân chị (đi chợ)",
    "ageHint": "24–36 tháng",
    "region": "Miền Bắc",
    "themes": ["chi", "gia_dinh"],
    "source": "Đồng dao dân gian miền Bắc (biến thể)",
    "license": "folk_traditional",
    "howToUse": "Vỗ đùi / đung đưa chân theo nhịp.",
    "body": "Cổ chân chị\nChị đi chợ về\nMua cái bánh đa\nCho em em ăn\nCho chị chị ăn\nCòn dư một cái\nĐể dành ông bà.",
  },
  {
    "id": "dd_chim_da_da",
    "category": "dong_dao",
    "title": "Chim đa đa",
    "ageHint": "18–36 tháng",
    "region": "Miền Bắc",
    "themes": ["choi"],
    "source": "Đồng dao dân gian",
    "license": "folk_traditional",
    "howToUse": "Vỗ tay «đa đa»; giả tiếng chim.",
    "body": "Chim đa đa\nBay về nhà\nĂn thóc mẹ\nUống nước cha\nNgủ ngon nào.",
  },
  {
    "id": "dd_ong_troi",
    "category": "dong_dao",
    "title": "Ông trời ơi",
    "ageHint": "24–36 tháng",
    "region": "Miền Bắc",
    "themes": ["choi"],
    "source": "Đồng dao dân gian miền Bắc",
    "license": "folk_traditional",
    "howToUse": "Dang tay nhìn trời; vui, không sợ.",
    "body": "Ông trời ơi ông trời ơi\nMây trắng bay chơi\nNắng vàng ấm quá\nXuống chơi với chúng em đi!",
  },
  {
    "id": "dd_nha_co_ai",
    "category": "dong_dao",
    "title": "Nhà mình có ai",
    "ageHint": "18–36 tháng",
    "region": "Miền Bắc",
    "themes": ["gia_dinh", "me", "ba", "bo"],
    "source": "Đồng dao gọi tên người thân (dạng truyền miệng sân chơi)",
    "license": "folk_traditional",
    "howToUse": "Chỉ ảnh hoặc người thật: mẹ, bố, bà, ông… Thay tên nhà mình.",
    "body": "Nhà mình có mẹ\nNhà mình có bố\nNhà mình có bà\nNhà mình có ông\nCả nhà thương nhau\nHết lòng hết lòng.",
  },
  {
    "id": "dd_di_cho_me",
    "category": "dong_dao",
    "title": "Mẹ đi chợ",
    "ageHint": "18–36 tháng",
    "region": "Miền Bắc",
    "themes": ["me", "gia_dinh"],
    "source": "Đồng dao dân gian (biến thể phổ biến)",
    "license": "folk_traditional",
    "howToUse": "Giả bộ xách giỏ; kể đồ mẹ mua.",
    "body": "Mẹ đi chợ\nMua cái gì?\nMua con cá\nMua bó rau\nMua quả chuối\nVề cho bé ăn ngon.",
  },
  {
    "id": "dd_ba_ke",
    "category": "dong_dao",
    "title": "Bà ơi bà",
    "ageHint": "18–36 tháng",
    "region": "Miền Bắc",
    "themes": ["ba", "gia_dinh"],
    "source": "Đồng dao / lời gọi thân thương truyền miệng (không phải bài nhạc thương mại)",
    "license": "folk_traditional",
    "howToUse": "Gọi bà (hoặc ảnh bà); ôm nhẹ. Không thay bằng bài bản quyền trên mạng.",
    "body": "Bà ơi bà\nCháu yêu bà\nBà kể chuyện\nCháu nghe bà\nBà cười vui\nCháu cười theo.",
  },
  {
    "id": "dd_ong_bao",
    "category": "dong_dao",
    "title": "Ông ơi ông",
    "ageHint": "18–36 tháng",
    "region": "Miền Bắc",
    "themes": ["ong", "gia_dinh"],
    "source": "Đồng dao gọi ông (cặp với «Bà ơi bà»)",
    "license": "folk_traditional",
    "howToUse": "Chỉ ông / ảnh ông; giọng ấm.",
    "body": "Ông ơi ông\nCháu chào ông\nÔng dẫn cháu\nĐi quanh sân\nÔng kể chuyện\nCháu lắng nghe.",
  },
  {
    "id": "dd_em_be",
    "category": "dong_dao",
    "title": "Em bé nhà mình",
    "ageHint": "18–30 tháng",
    "region": "Miền Bắc",
    "themes": ["gia_dinh"],
    "source": "Đồng dao ngắn truyền miệng",
    "license": "folk_traditional",
    "howToUse": "Chỉ bé trong gương hoặc ảnh.",
    "body": "Em bé nhà mình\nMắt sáng môi xinh\nTay nắm tay mẹ\nChân tập đi quanh.",
  },
  # --- Ca dao / hát ru miền Bắc về mẹ, cha, anh chị em ---
  {
    "id": "ca_cong_cha",
    "category": "hat",
    "title": "Công cha như núi Thái Sơn",
    "ageHint": "30–36 tháng (nghe)",
    "region": "Miền Bắc",
    "themes": ["bo", "me", "gia_dinh"],
    "source": "Ca dao dân gian Việt Nam",
    "license": "folk_traditional",
    "howToUse": "Đọc chậm như thơ; không giảng dài. Chỉ bố mẹ khi đọc.",
    "body": "Công cha như núi Thái Sơn\nNghĩa mẹ như nước trong nguồn chảy ra\nMột lòng thờ mẹ kính cha\nCho tròn chữ hiếu mới là đạo con.",
  },
  {
    "id": "hat_cai_ngu",
    "category": "hat",
    "title": "Cái ngủ mày ngủ cho lâu",
    "ageHint": "0–36 tháng",
    "region": "Miền Bắc",
    "themes": ["me", "ru"],
    "source": "Hát ru dân gian miền Bắc",
    "license": "folk_traditional",
    "howToUse": "Ru ngủ; giọng nhỏ, lặp. Bé không cần hát theo.",
    "body": "Cái ngủ mày ngủ cho lâu\nMẹ mày đi cấy đồng sâu mẹ mày về\nCái ngủ mày ngủ cho ngon\nMẹ mày đi gánh nước non mẹ mày về.",
  },
  {
    "id": "hat_me_ganh_nuoc",
    "category": "hat",
    "title": "Con ơi con ngủ (mẹ gánh nước)",
    "ageHint": "0–36 tháng",
    "region": "Miền Bắc",
    "themes": ["me", "ru"],
    "source": "Hát ru / ca dao dân gian miền Bắc",
    "license": "folk_traditional",
    "howToUse": "Ru hoặc đọc chậm trước ngủ.",
    "body": "À ơi… à ơi…\nCon ơi con ngủ cho ngon\nĐể mẹ gánh nước cho toàn ruộng sâu\nBao giờ cho đến tháng mười\nMẹ về mẹ kể chuyện cười cho con.",
  },
  {
    "id": "hat_gio_dua",
    "category": "hat",
    "title": "Gió đưa cành trúc la đà",
    "ageHint": "18–36 tháng",
    "region": "Miền Bắc",
    "themes": ["ru", "me"],
    "source": "Ca dao / hát ru dân gian",
    "license": "folk_traditional",
    "howToUse": "Đung đưa; giọng êm.",
    "body": "Gió đưa cành trúc la đà\nKỳ đài tắm mát, ơi là kỳ đài\nKỳ đài tắm mát, ơi là kỳ đài…\n\n(Biến thể dài theo vùng — hỏi ông bà miền Bắc bản quen thuộc.)",
  },
  {
    "id": "hat_au_o_bac",
    "category": "hat",
    "title": "Ầu ơ… ví dầu (bản Bắc)",
    "ageHint": "0–36 tháng",
    "region": "Miền Bắc",
    "themes": ["me", "ru"],
    "source": "Hát ru dân gian miền Bắc",
    "license": "folk_traditional",
    "howToUse": "Lặp «ầu ơ»; ghép câu ví dầu quen.",
    "body": "Ầu ơ… ầu ơ…\nVí dầu cầu ván đóng đinh\nCầu tre lắc lẻo gập ghềnh khó đi\nKhó đi mẹ dắt con đi\nCon đi trường học mẹ đi trường đời\nẦu ơ… ầu ơ…",
  },
  {
    "id": "ca_anh_em",
    "category": "hat",
    "title": "Anh em như thể chân tay",
    "ageHint": "30–36 tháng (nghe)",
    "region": "Miền Bắc",
    "themes": ["anh_chi_em", "gia_dinh"],
    "source": "Ca dao dân gian Việt Nam",
    "license": "folk_traditional",
    "howToUse": "Khi có anh/chị/em; nói thương nhau — không bắt thuộc.",
    "body": "Anh em như thể chân tay\nRách lành đùm bọc, dở hay đỡ đần.",
  },
  {
    "id": "ca_chi_nga",
    "category": "hat",
    "title": "Chị ngã em nâng",
    "ageHint": "30–36 tháng (nghe)",
    "region": "Miền Bắc",
    "themes": ["anh_chi_em", "gia_dinh"],
    "source": "Ca dao dân gian Việt Nam",
    "license": "folk_traditional",
    "howToUse": "Đọc khi anh chị em chơi cùng; khen khi biết nhường.",
    "body": "Chị ngã em nâng\nRách lành đùm bọc lấy nhau.",
  },
  {
    "id": "ca_ga_mot_me",
    "category": "hat",
    "title": "Gà cùng một mẹ",
    "ageHint": "30–36 tháng (nghe)",
    "region": "Miền Bắc",
    "themes": ["anh_chi_em", "gia_dinh"],
    "source": "Ca dao dân gian",
    "license": "folk_traditional",
    "howToUse": "Nói anh chị em cùng nhà; ngắn gọn.",
    "body": "Gà cùng một mẹ\nChớ hoài đá nhau.",
  },
  {
    "id": "ca_con_co_dem",
    "category": "hat",
    "title": "Con cò mà đi ăn đêm",
    "ageHint": "24–36 tháng",
    "region": "Miền Bắc",
    "themes": ["ru"],
    "source": "Ca dao dân gian Việt Nam (phổ biến miền Bắc)",
    "license": "folk_traditional",
    "howToUse": "Giọng chậm; dang tay làm cánh cò. Bản đầy đủ dài — chỉ 4 câu đầu với bé nhỏ.",
    "body": "Con cò mà đi ăn đêm\nĐậu phải cành mềm lộn cổ xuống ao\nÔng ơi ông vớt tôi nao\nTôi có lòng nào ông hãy xáo măng…\n\n(Dừng sớm nếu bé sợ; có nhà chỉ hát hai câu đầu.)",
  },
  {
    "id": "ca_me_cha_nuoi",
    "category": "hat",
    "title": "Công mẹ sinh thành",
    "ageHint": "30–36 tháng (nghe)",
    "region": "Miền Bắc",
    "themes": ["me", "bo", "gia_dinh"],
    "source": "Ca dao dân gian",
    "license": "folk_traditional",
    "howToUse": "Đọc nhẹ; chỉ mẹ/bố.",
    "body": "Công mẹ sinh thành\nNghĩa cha dưỡng dục\nMột lòng thảo kính\nẤy là đạo con.",
  },
  {
    "id": "hat_ru_me_yeu",
    "category": "hat",
    "title": "Ru con — mẹ yêu con",
    "ageHint": "0–36 tháng",
    "region": "Miền Bắc",
    "themes": ["me", "ru", "gia_dinh"],
    "source": "Hát ru truyền miệng miền Bắc (dạng rút gọn)",
    "license": "folk_traditional",
    "howToUse": "Ôm ru; thay «bé» bằng tên con.",
    "body": "À ơi… à ơi…\nCon ơi mẹ yêu con lắm\nCon ngủ cho ngoan\nMai dậy mẹ bế con chơi\nÀ ơi… à ơi…",
  },
  {
    "id": "hat_ru_ba_ke",
    "category": "hat",
    "title": "Ru con — bà kể chuyện",
    "ageHint": "0–36 tháng",
    "region": "Miền Bắc",
    "themes": ["ba", "ru", "gia_dinh"],
    "source": "Hát ru / lời ru truyền miệng (ông bà miền Bắc)",
    "license": "folk_traditional",
    "howToUse": "Nhờ bà hát thêm bản nhà; app chỉ giữ khung ngắn.",
    "body": "À ơi… ầu ơ…\nCháu ơi ngủ ngon\nBà kể chuyện xưa\nChuyện làng chuyện xã\nCháu nghe bà ru\nÀ ơi… ầu ơ…",
  },
  {
    "id": "ca_doi_ta",
    "category": "hat",
    "title": "Ta về ta tắm ao ta",
    "ageHint": "30–36 tháng (nghe)",
    "region": "Miền Bắc",
    "themes": ["gia_dinh", "que"],
    "source": "Ca dao dân gian Việt Nam",
    "license": "folk_traditional",
    "howToUse": "Nói về nhà mình / quê ông bà — gần gũi, không giảng.",
    "body": "Ta về ta tắm ao ta\nDù trong dù đục ao nhà vẫn hơn.",
  },
  {
    "id": "hat_ly_cay_da",
    "category": "hat",
    "title": "Lý cây đa (lời quen miền Bắc)",
    "ageHint": "24–36 tháng",
    "region": "Miền Bắc",
    "themes": ["choi"],
    "source": "Dân ca / đồng dao lưu truyền (biến thể miền Bắc)",
    "license": "folk_traditional",
    "howToUse": "Vỗ tay theo nhịp; hỏi ông bà thêm câu.",
    "body": "Lý cây đa\nLý cây đa\nCành cao cành thấp\nLá xanh lá già\nChim về đậu đó\nHót vang sân nhà.",
  },
  {
    "id": "hat_dan_vit",
    "category": "hat",
    "title": "Đàn vịt con (lời nhà trẻ phổ biến)",
    "ageHint": "18–36 tháng",
    "region": "Miền Bắc",
    "themes": ["choi", "me"],
    "source": "Bài hát thiếu nhi lưu truyền rộng nhà trẻ VN (biến thể)",
    "license": "folk_or_widely_circulated",
    "howToUse": "Giả vịt «cạp cạp»; đi lạch bạch.",
    "body": "Đàn vịt con\nBơi trên ao\nMẹ vịt kêu\nCạp cạp cạp\nCon theo mẹ\nBơi quanh ao.\n\n(Có biến thể lời — dùng bản cô giáo lớp bé.)",
  },
  {
    "id": "hat_chim_sau",
    "category": "hat",
    "title": "Chim sâu (lời ngắn phổ biến)",
    "ageHint": "18–36 tháng",
    "region": "Miền Bắc",
    "themes": ["choi"],
    "source": "Đồng dao / bài hát miệng sân chơi phổ biến",
    "license": "folk_or_widely_circulated",
    "howToUse": "Vẫy tay như cánh.",
    "body": "Chim sâu sâu\nBay lao xao\nHót líu lo\nTrên cành cao\nBé nghe chim\nCười toe toét.",
  },
  # --- Hướng tìm bài thiếu nhi (không chép lời bản quyền) ---
  {
    "id": "tip_bai_ba_me",
    "category": "sach",
    "title": "Bài hát về bà/mẹ (hỏi ông bà / cô giáo)",
    "ageHint": "18–36 tháng",
    "region": "Miền Bắc",
    "themes": ["ba", "me", "gia_dinh"],
    "source": "Hướng tìm — không chép lời thương mại",
    "license": "tip_find",
    "howToUse": "Hỏi bà/cô lớp bé hát giúp; ghi lời nhà mình vào sổ. Không tải video quảng cáo.",
    "body": "Một số bài nhà trẻ hay hát về bà, mẹ, cả nhà (thường có nhạc sĩ — app không chép lời):\n• Cháu yêu bà / Bà ơi bà…\n• Cả nhà thương nhau\n• Mẹ yêu không nào\n• Đưa cơm cho mẹ đi cày (dân ca / bài học thuộc lòng — hỏi bản cô dạy)\n\nCách làm an toàn: học miệng từ ông bà hoặc cô giáo; hát không loa quảng cáo.",
  },
  {
    "id": "tip_hat_ru_bac",
    "category": "sach",
    "title": "Sưu tầm hát ru ông bà miền Bắc",
    "ageHint": "0–36 tháng",
    "region": "Miền Bắc",
    "themes": ["ru", "ba", "me"],
    "source": "Hướng thực hành gia đình",
    "license": "tip_find",
    "howToUse": "Ghi âm giọng bà (chỉ trong máy nhà, không đăng). Học 1–2 câu mỗi tuần.",
    "body": "Hỏi ông bà:\n1) Quê mình ru bằng «à ơi» hay «ầu ơ»?\n2) Câu nào về mẹ đi cấy / gánh nước / chợ?\n3) Có câu gọi tên con không?\nGhi vào điện thoại offline — giữ trong nhà, không đưa lên mạng.",
  },
]

# Tag existing items that are Northern / family-relevant
EXISTING_TAGS = {
  "dd_nu_na": {"region": "Miền Bắc", "themes": ["ru"]},
  "dd_chi_chi": {"region": "Miền Bắc", "themes": ["choi"]},
  "dd_thang_bom": {"region": "Miền Bắc", "themes": ["choi"]},
  "dd_dung_dang": {"region": "Miền Bắc", "themes": ["choi"]},
  "dd_keo_cua": {"region": "Miền Bắc", "themes": ["gia_dinh", "choi"]},
  "dd_con_co": {"region": "Miền Bắc", "themes": ["choi"]},
  "dd_bau_ai": {"region": "Miền Bắc", "themes": ["anh_chi_em", "gia_dinh"]},
  "dd_hom_na": {"region": "Miền Bắc", "themes": ["ru"]},
  "dd_chim_chim": {"region": "Miền Bắc", "themes": ["choi"]},
  "hat_ru_a_oi": {"region": "Miền Bắc", "themes": ["me", "ru"]},
  "hat_ru_vi_dao": {"region": "Miền Bắc", "themes": ["me", "ru"]},
  "hat_dan_ga": {"region": "Cả nước / nhà trẻ", "themes": ["me", "choi"]},
  "hat_ly_cay_bong": {"region": "Nam Bộ (tham khảo)", "themes": ["choi"]},
  "dd_hoa_buoi": {"region": "Cả nước", "themes": ["choi"]},
}


def main():
  data = json.loads(CAT.read_text(encoding="utf-8"))
  data["version"] = 4
  data["curation_policy"] = (
    "Dong dao & hat ru dan gian mien Bac: du loi. "
    "Uu tien me/ba/gia dinh. Bai nhac thuong mai: chi huong tim, khong chep loi. "
    "Tranh PNG ke chuyen. Sach NXB: chi huong tim."
  )
  data["disclaimer"] = (
    "Đồng dao/hát ru dân gian (ưu tiên miền Bắc; có biến thể nhà). "
    "Bài thiếu nhi bản quyền thương mại: chỉ hướng tìm. Nháp — chưa chuyên gia duyệt."
  )

  # optional theme category label already covered by tags
  ids = {i["id"] for i in data["items"]}
  for iid, meta in EXISTING_TAGS.items():
    for it in data["items"]:
      if it["id"] == iid:
        it["region"] = meta["region"]
        it["themes"] = meta["themes"]

  # insert new items before first to_tranh / sach that aren't tips — after last hat/dong_dao block
  insert_at = 0
  for idx, it in enumerate(data["items"]):
    if it["category"] in ("to_tranh", "sach", "mau_cau") and not it["id"].startswith("tip_"):
      insert_at = idx
      break
  else:
    insert_at = len(data["items"])

  added = 0
  for n in NEW:
    if n["id"] in ids:
      continue
    data["items"].insert(insert_at, n)
    insert_at += 1
    ids.add(n["id"])
    added += 1

  # theme filter labels for UI
  data["themeLabels"] = {
    "gia_dinh": "Gia đình",
    "me": "Mẹ",
    "ba": "Bà",
    "bo": "Bố / ông",
    "ong": "Ông",
    "anh_chi_em": "Anh chị em",
    "ru": "Hát ru",
    "choi": "Chơi",
    "mien_bac": "Miền Bắc",
  }

  text = json.dumps(data, ensure_ascii=False, indent=2) + "\n"
  CAT.write_text(text, encoding="utf-8")
  WEB.write_text(text, encoding="utf-8")

  dd = sum(1 for i in data["items"] if i["category"] == "dong_dao")
  hat = sum(1 for i in data["items"] if i["category"] == "hat")
  print(f"added={added} dong_dao={dd} hat={hat} total={len(data['items'])}")


if __name__ == "__main__":
  main()
