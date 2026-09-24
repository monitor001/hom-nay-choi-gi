/**
 * Wave 5 Applier — seed P0 edits (X6 EDITS_REQUIRED A).
 * Run from repo root: node scripts/wave5_apply_seed.mjs
 */
import { readFileSync, writeFileSync, copyFileSync } from "node:fs";
import { dirname, join } from "node:path";
import { fileURLToPath } from "node:url";

const root = join(dirname(fileURLToPath(import.meta.url)), "..");
const seedPath = join(root, "content", "seed", "activities_mvp_draft.json");
const backupPath = join(root, "content", "seed", "activities_mvp_draft.backup.json");
const webPath = join(root, "web", "content", "activities.json");
const androidPath = join(
  root,
  "gaucon",
  "content-seed",
  "src",
  "main",
  "assets",
  "content_seed.json",
);

copyFileSync(seedPath, backupPath);

const data = JSON.parse(readFileSync(seedPath, "utf8"));
const byId = new Map(data.activities.map((a) => [a.id, a]));
const get = (id) => {
  const a = byId.get(id);
  if (!a) throw new Error(`missing ${id}`);
  return a;
};

/** A1 — rewrite goals: invite to play VI; no CDC/English/numeric checklist on surface */
const GOAL_MAP = {
  phy_02_leo_ghe_thap: "Mời bé thử lên–xuống ghế thấp cùng người lớn — vui và có người giữ.",
  phy_03_nguech_sap_lon: "Cùng nguệch sáp lớn trên giấy — để lại dấu vết, không cần hình đẹp.",
  phy_05_da_bong_mem: "Đá bóng mềm về phía người lớn hoặc tường gần — chơi chuyển động cùng nhau.",
  phy_06_chay_san_ngan: "Chạy ngắn trên sân/phòng rồi dừng khi nghe tín hiệu vui.",
  phy_07_bac_thang_tay_vin: "Đi vài bậc cầu thang có tay vịn — người lớn đi cạnh, không vội.",
  phy_08_nhay_hai_chan: "Nhảy nhẹ tại chỗ hoặc xuống nệm thấp — hai chân cùng lúc nếu bé muốn.",
  phy_09_van_nap_hop: "Vặn–mở nắp hộp đồ chơi lớn bằng tay — khám phá xoay và siết.",
  phy_10_lat_sach_tung_trang: "Lật từng trang sách ảnh dày — chậm, không xé.",
  phy_11_xau_nui_ong: "Xâu vòng/nui ống to vào dây chắc — chơi tinh, luôn có người lớn.",
  phy_12_mac_quan_rong: "Thử luồn chân vào quần rộng — người lớn giúp phần còn lại, không ép.",
  phy_13_xep_khoi_thap: "Chồng khối/hộp thành tháp rồi đổ vui vẻ — không cần cao bao nhiêu.",
  cog_01_bat_chuoc_quet_nha: "Bắt chước việc nhà vui (quét, lau) bằng đồ chơi hoặc khăn thật.",
  cog_02_day_xe_do_choi: "Đẩy xe/đồ chơi theo cách dùng quen — chạy ngắn trong nhà.",
  cog_03_mo_nap_hop: "Mở–đậy nắp hộp bằng hai tay — khám phá trong–ngoài.",
  cog_04_nut_do_choi: "Bấm/xoay nút–núm đồ chơi lớn — xem đèn/nhạc hoặc chuyển động.",
  cog_05_do_an_len_dia: "Xếp đồ chơi thức ăn lên đĩa/khay — chơi nhiều món cùng lúc.",
  cog_06_nuoi_bup_be: "Giả vờ đút/nuôi búp bê hoặc gấu — chăm sóc vui.",
  cog_07_hai_buoc_don_do: "Nghe lời mời hai việc nhẹ về cất đồ — khen cố gắng, không kiểm tra.",
  cog_08_chi_mau_do: "Cùng chỉ và gọi tên một màu trên đồ chơi — chơi, không kiểm tra đúng/sai.",
  cog_09_ghe_thap_voi_do: "Nghĩ cách lấy đồ hơi cao bằng ghế thấp — luôn có người lớn giữ.",
  cog_10_ve_vong_tron_mau: "Vẽ vòng theo mẫu người lớn — chấp nhận méo, khoe tranh.",
  cog_12_to_nho_hai_hop: "So sánh đồ to–nhỏ bằng hai hộp — nói to/nhỏ khi chơi.",
  cog_13_nhom_theo_mau: "Gom đồ cùng màu vào khay — chơi phân loại vui, không điểm.",
};

for (const [id, goal] of Object.entries(GOAL_MAP)) {
  get(id).goal = goal;
  // Internal refs only — not shown on S05 unless UI adds field later
  get(id).source_refs = get(id).source_refs || ["internal:milestone_bank_draft"];
}

// SOC-14: remove “lịch sự”
{
  const a = get("SOC-14");
  a.parentPhrases = a.parentPhrases.map((p) =>
    p.includes("lịch sự") ? "Mẹ thấy con đang cố nói cảm ơn." : p,
  );
  if (/lịch sự/i.test(a.goal)) a.goal = "Nói/ra dấu cảm ơn khi muốn — không bắt trước khách.";
}

/** A2 — potty harder: no schedule after meals */
{
  const a = get("act_sc_potty_018_10");
  a.harder = "Ngồi chơi ngắn hơn một chút, hoặc để bé tự chạm/chỉ bô rồi đứng dậy — vẫn không ép ị.";
}

/** A3 — split cog_07 ↔ LANG-10 contexts */
{
  const cog = get("cog_07_hai_buoc_don_do");
  cog.title = "Cất đồ theo hai lời mời";
  cog.materials = ["Vài món đồ chơi trên sàn", "giỏ/hộp đựng"];
  cog.goal = "Nghe lời mời nhẹ: nhặt một món rồi bỏ vào giỏ — khen quá trình, không “làm đủ hai việc”.";
  cog.steps = [
    "Nói rõ, giọng vui: “Nhặt áo len và bỏ vào giỏ.” (hoặc món khác đang nằm).",
    "Chờ bé làm; nếu khó thì làm cùng, không tách thành bài kiểm tra.",
    "Khen cố gắng: “Con đang giúp dọn đồ!” — dừng khi bé muốn chơi khác.",
  ];
  cog.parentPhrases = [
    "Nhặt cái này và bỏ vào giỏ nhé.",
    "Con đang giúp mẹ/bố dọn đồ!",
    "Thêm một món nữa nếu con muốn.",
  ];
  cog.easier = "Chỉ một việc: “Bỏ vào giỏ”; người lớn làm bước kia.";
  cog.harder = "Đổi món: đưa khăn đặt lên ghế rồi ngồi cạnh.";
  cog.safety = "Đồ không nguy hiểm; giỏ không cạnh cầu thang; không biến thành kiểm tra.";

  const lang = get("LANG-10");
  lang.title = "Nghe hai bước chơi bóng";
  lang.materials = ["Bóng mềm", "giỏ/hộp"];
  lang.goal = "Nghe hiểu chuỗi hai hành động vui với bóng — khen quá trình.";
  lang.steps = [
    "Nói rõ: “Lấy bóng và bỏ vào giỏ.”",
    "Đợi; chỉ nhắc lại nếu cần, không làm hộ ngay.",
    "Khen: “Con nghe và làm lần lượt!” — không nói kiểu kiểm tra điểm.",
    "Đổi cặp vui khác: lăn bóng–nhặt, đưa bóng–ôm.",
  ];
  lang.parentPhrases = [
    "Nghe mẹ/bố nhé: một… hai…",
    "Con làm lần lượt, vui quá!",
    "Mình thử cặp khác nào.",
  ];
}

/** A5 — AES-08 choke safety */
{
  const a = get("AES-08");
  a.materials = [
    "Lục lạc mua sẵn (ưu tiên 18–23 tháng)",
    "HOẶC từ ≥24 tháng: chai nhựa sạch + hạt/đậu lớn do người lớn đổ, dán nắp chắc",
  ];
  a.steps = [
    "18–23 tháng: chỉ đưa lục lạc mua sẵn, đã kiểm tra nắp/khớp.",
    "≥24 tháng nếu DIY: người lớn đổ hạt lớn, dán/siết nắp chắc trước khi đưa — bé không tham gia đổ hạt.",
    "Lắc chậm theo bài hát; đổi tay; lắc to/nhỏ.",
    "Cất khỏi tầm với ngay khi xong; không để bé tự mở nắp.",
  ];
  a.easier = "Chỉ lục lạc mua sẵn mọi tuổi trong band này.";
  a.harder = "≥24 tháng: lắc theo câu thơ ngắn; vẫn cấm mở nắp.";
  a.safety =
    "18–23 tháng: không DIY hạt trong chai. DIY chỉ ≥24 tháng + dán nắp không mở được + hạt lớn + giám sát + cất sau chơi. Kiểm tra nứt chai. Chống hóc.";
}

/** A5 — phy_14 water only default */
{
  const a = get("phy_14_chuyen_nuoc_coc");
  a.title = "Chuyển nước muỗng giữa hai cốc";
  a.materials = ["2 cốc nhựa", "muỗng", "ít nước sạch", "khay/chậu hứng"];
  a.steps = [
    "Cho ít nước vào cốc A trên khay.",
    "Bé múc chuyển sang cốc B — ngồi chơi.",
    "Lau tay; khen cố gắng.",
  ];
  a.easier = "Dùng tay chuyển bóng cotton / miếng xốp ướt thay muỗng.";
  a.harder =
    "≥30 tháng: thử gạo/đậu to trên khay (không hạt nhỏ); lượng ít; vẫn ngồi. Hoặc nước nhiều hơn một chút, đích hẹp hơn.";
  a.safety =
    "Mặc định chỉ nước. Không dùng hạt/gạo dưới 30 tháng. Ngồi; lượng ít; giám sát miệng; trải khăn. Chống hóc.";
}

/** A5 — phy_11 size + no necklace */
{
  const a = get("phy_11_xau_nui_ong");
  a.materials = [
    "Ống hút cứng / cọng dây ngắn chắc (không quấn cổ)",
    "Nui ống hoặc vòng xâu to — lỗ lớn, cạnh ngắn nhất ≥ khoảng miệng hộp đựng thuốc tiêu chuẩn chống hóc (dùng đồ to rõ)",
  ];
  a.harder =
    "Xâu thêm vài vòng vào ống giấy vệ sinh / thanh gỗ ngắn — làm “vòng đeo tay giả” ngắn; cấm đeo cổ / quấn cổ.";
  a.safety =
    "Chỉ hạt/vòng to không nuốt được; giám sát chống hóc 100% thời gian; cất ngay sau chơi; cấm làm vòng đeo cổ thật hoặc dây dài quanh cổ.";
}

/** A6 — act_sc_safe_030_18 safety rewrite */
{
  const a = get("act_sc_safe_030_18");
  a.safety =
    "Nhắc ngồi khi ăn; không chạy/đi khi miệng còn đồ; đồ nóng — thổi nguội / chờ người lớn đưa. Giọng vui, không thành bài kiểm tra điểm.";
}

/** A7 — ageMin 18 for oral-exploration safety */
{
  const a = get("act_sc_safe_024_17");
  a.ageMinMonths = 18;
}

/** A4 — ADD 7 activities (X6 #1–5, #7, #9) */
const NEW = [
  {
    id: "act_soc_chi_khoe_do",
    title: "Chỉ và khoe đồ",
    ageMinMonths: 18,
    ageMaxMonths: 26,
    domains: ["SOCIAL_EMOTIONAL", "LANGUAGE"],
    durationMinutes: 6,
    materials: ["2–3 món đồ chơi quen", "chỗ ngồi cạnh nhau"],
    goal: "Bé chỉ hoặc đưa đồ để khoe — người lớn nhìn và gọi tên; không bắt nói.",
    steps: [
      "Ngồi cùng tầm mắt; để đồ trong tầm với.",
      "Khi bé chỉ/đưa: nhìn theo, nói ngắn “Xe của con!” hoặc “Con đang khoe.”",
      "Khoe lại một món của mình (mẫu); chờ bé nếu muốn.",
      "Dừng khi bé quay đi chơi khác.",
    ],
    parentPhrases: ["Con đang khoe gì thế?", "Mẹ/bố nhìn đây này.", "Xe đỏ của con!"],
    easier: "Chỉ phản ứng khi bé chỉ; chưa khoe lại.",
    harder: "Hỏi nhẹ “Cho mẹ xem?” — chấp nhận lắc đầu.",
    safety: "Đồ to, không cạnh sắc; không ép nói từ.",
    reviewed_by: [],
    isPremium: false,
  },
  {
    id: "act_soc_tach_hop_lai_peek",
    title: "Tách ngắn – hợp lại (peek)",
    ageMinMonths: 18,
    ageMaxMonths: 24,
    domains: ["SOCIAL_EMOTIONAL"],
    durationMinutes: 5,
    materials: ["Ghế/cửa thấp hoặc khăn mỏng", "không gian một phòng"],
    goal: "Người lớn khuất nhẹ vài giây rồi hiện lại — luôn trong tầm nhìn/nghe; gắn bó vui, không luyện “bỏ mẹ”.",
    steps: [
      "Báo trước: “Mẹ/bố ra sau ghế một chút rồi về.”",
      "Khuất 2–3 giây vẫn nói chuyện được; hiện ra “Ú òa!”.",
      "Ôm/cười; lặp 2–3 lần nếu bé vui.",
      "Dừng ngay nếu bé khóc hoặc bám chặt.",
    ],
    parentPhrases: ["Mẹ/bố đây này!", "Con vẫn thấy mẹ/bố.", "Ú òa!"],
    easier: "Chỉ che mặt bằng tay trước mặt bé.",
    harder: "Ra sau ghế lâu hơn một nhịp — vẫn gọi tên liên tục.",
    safety: "Không đóng cửa, không ra phòng khác, không “luyện bỏ”; luôn nghe thấy tiếng người lớn.",
    reviewed_by: [],
    isPremium: false,
  },
  {
    id: "act_soc_choi_song_song_khay",
    title: "Chơi song song hai khay",
    ageMinMonths: 18,
    ageMaxMonths: 28,
    domains: ["SOCIAL_EMOTIONAL"],
    durationMinutes: 8,
    materials: ["Hai khay/chậu nhỏ", "đồ chơi giống hoặc tương tự (khối, muỗng)"],
    goal: "Chơi cạnh nhau với khay riêng — nền tảng trước chia sẻ; không bắt đưa đồ.",
    steps: [
      "Đặt hai khay cạnh nhau; mỗi người một khay.",
      "Người lớn chơi khay mình, mô tả ngắn việc đang làm.",
      "Nếu bé lấy đồ của mình: để yên hoặc đổi nhẹ nhàng — không giảng “phải chia”.",
      "Kết: cất cùng nhau nếu bé muốn.",
    ],
    parentPhrases: ["Khay của con đây.", "Mẹ/bố chơi khay này.", "Mình ngồi cạnh nhau vui."],
    easier: "Chỉ một loại đồ, khoảng cách gần.",
    harder: "Đổi món giữa hai khay khi bé đồng ý.",
    safety: "Đồ to; không tranh giành thành ép chia sẻ; giám sát.",
    reviewed_by: [],
    isPremium: false,
  },
  {
    id: "act_cog_do_hat_hop",
    title: "Đổ hạt lớn vào hộp",
    ageMinMonths: 18,
    ageMaxMonths: 28,
    domains: ["COGNITIVE", "PHYSICAL"],
    durationMinutes: 8,
    materials: [
      "Hộp/khay miệng rộng",
      "Hạt/đồ lớn rõ (khối gỗ nhỏ an toàn, nắp chai to, hoặc pompon to) — cấm hạt nhỏ/gạo/đậu",
    ],
    goal: "Đưa–đổ đồ lớn vào hộp — khám phá trong–ngoài; luôn chống hóc.",
    steps: [
      "Người lớn mẫu: thả 1 món to vào hộp, nghe tiếng “cộp”.",
      "Mời bé thả thêm; chấp nhận đổ ra sàn có khay hứng.",
      "Đếm vui vài cái; dừng khi mệt.",
      "Cất hết món nhỏ hơn miệng hộp thuốc tiêu chuẩn ngay sau chơi.",
    ],
    parentPhrases: ["Bỏ vào hộp nào.", "Cộp!", "Thêm một cái nữa."],
    easier: "Chỉ 2–3 món rất to; người lớn giữ hộp.",
    harder: "Đổ từ hộp này sang hộp kia.",
    safety:
      "Chỉ đồ lớn không nuốt được; cấm hạt nhỏ/gạo/đậu/viên bi. Giám sát miệng 100%; ngồi chơi; cất sau chơi. Chống hóc.",
    reviewed_by: [],
    isPremium: false,
  },
  {
    id: "act_sc_bo_do_vao_gio",
    title: "Bỏ đồ vào giỏ giúp việc",
    ageMinMonths: 18,
    ageMaxMonths: 30,
    domains: ["SELF_CARE", "COGNITIVE"],
    durationMinutes: 7,
    materials: ["Giỏ/túi vải", "3–5 món đồ nhẹ (áo, khăn, thú)"],
    goal: "Giúp bỏ vài món vào giỏ — việc nhà vui, không bắt dọn hết phòng.",
    steps: [
      "Đặt giỏ giữa sàn; để 3–5 món trong tầm với.",
      "Mời: “Bỏ khăn vào giỏ giúp mẹ/bố nhé?”",
      "Làm cùng 1–2 món; khen giúp việc.",
      "Xong sớm — không kéo thành phiên dọn nhà.",
    ],
    parentPhrases: ["Giúp mẹ/bố một chút nhé.", "Bỏ vào giỏ!", "Cảm ơn con đã giúp."],
    easier: "Chỉ một món; người lớn chỉ tay vào giỏ.",
    harder: "Bé mang giỏ nhẹ 2–3 bước tới ghế.",
    safety: "Giỏ không nặng; không dây dài quanh cổ; sàn sạch.",
    reviewed_by: [],
    isPremium: false,
  },
  {
    id: "act_lang_anh_gia_dinh",
    title: "Ảnh gia đình gọi tên",
    ageMinMonths: 18,
    ageMaxMonths: 30,
    domains: ["LANGUAGE", "SOCIAL_EMOTIONAL"],
    durationMinutes: 6,
    materials: ["2–4 ảnh in hoặc album điện thoại (ông bà, bố mẹ, thú cưng)"],
    goal: "Chỉ ảnh người thân và nghe/gọi tên — gắn bó và ngôn ngữ, không kiểm tra.",
    steps: [
      "Mở 1 ảnh; chỉ: “Đây bà nội.”",
      "Chờ bé chỉ/bập bẹ; nhắc lại tên ấm.",
      "Lật ảnh khác nếu bé còn hứng.",
      "Ôm hoặc nói “Nhớ bà” — không ép nói đủ từ.",
    ],
    parentPhrases: ["Đây ai đây?", "Bà của con.", "Con chỉ bố/mẹ nào?"],
    easier: "Chỉ 1 ảnh quen nhất.",
    harder: "Hỏi “Bà đâu?” trong 2 ảnh.",
    safety: "Không ảnh đáng sợ; giữ máy chắc, không dây sạc gần cổ.",
    reviewed_by: [],
    isPremium: false,
  },
  {
    id: "act_soc_dua_do_khi_xin",
    title: "Đưa đồ khi được xin",
    ageMinMonths: 18,
    ageMaxMonths: 30,
    domains: ["SOCIAL_EMOTIONAL"],
    durationMinutes: 5,
    materials: ["1–2 món đồ chơi bé đang cầm", "người lớn ngồi gần"],
    goal: "Thử đưa đồ khi nghe “Cho mẹ/bố xem?” — tiền-chia sẻ; chấp nhận chưa đưa.",
    steps: [
      "Khi bé cầm đồ: đưa tay mở, nói “Cho mẹ/bố xem một chút?”",
      "Nếu đưa: cảm ơn, xem 2–3 giây, trả lại ngay.",
      "Nếu không: mỉm cười “Không sao, con giữ.” — không giật.",
      "Lặp thỉnh thoảng trong ngày, không thành bài tập.",
    ],
    parentPhrases: ["Cho mẹ/bố xem nhé?", "Cảm ơn con.", "Con giữ cũng được."],
    easier: "Chỉ xin đồ người lớn đang cầm mẫu đưa–nhận.",
    harder: "Xin đưa cho người thứ hai trong nhà (ông/bà).",
    safety: "Không giật đồ; không phạt khi không đưa; tránh tranh chấp thành ép chia sẻ.",
    reviewed_by: [],
    isPremium: false,
  },
];

for (const a of NEW) {
  if (byId.has(a.id)) throw new Error(`id exists ${a.id}`);
  data.activities.push(a);
  byId.set(a.id, a);
}

// Keep draft status
data.content_status = "draft_unreviewed";
data.version = data.version || 1;

const json = JSON.stringify(data, null, 2) + "\n";
writeFileSync(seedPath, json);
writeFileSync(webPath, json);
writeFileSync(androidPath, json);

const vis18 = data.activities.filter((a) => a.ageMinMonths <= 18 && a.ageMaxMonths >= 18).length;
const pc = {};
for (const a of data.activities) {
  const p = a.domains[0];
  pc[p] = (pc[p] || 0) + 1;
}
console.log(
  JSON.stringify(
    {
      total: data.activities.length,
      visibleAt18: vis18,
      primaryCount: pc,
      added: NEW.map((a) => a.id),
      content_status: data.content_status,
    },
    null,
    2,
  ),
);
