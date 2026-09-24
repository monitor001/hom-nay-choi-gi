# CURATION_LOG — G2 Content curator (Wave 2)

> Workspace: `ideas/BeGau` · Nháp · **chưa chuyên gia duyệt** (`content_status: draft_unreviewed`)  
> Ngày: 2026-09-24

## Nguồn

| File | Vai trò |
|---|---|
| `research/01_khung_chuong_trinh.md` | Phân bổ mục tiêu **16 / 14 / 16 / 14 / 12 / 18** (primary) |
| `research/02_the_chat_nhan_thuc.md` | YAML PHYSICAL + COGNITIVE |
| `research/03_ngon_ngu_xa_hoi_tham_my.md` | YAML LANGUAGE + SOCIAL_EMOTIONAL + AESTHETIC |
| `research/04_tu_lap_sinh_hoat.md` | YAML SELF_CARE (18) |
| `docs/SPEC_MVP.md` / `plans/WAVE2_BRIEFS.md` | Schema & tiêu chí chọn lọc |

## Thống kê

| Hạng mục | Số |
|---|---:|
| YAML thô (unique id) | 89 |
| **Giữ từ research** | 86 |
| **Bỏ (trùng / gần trùng)** | 3 |
| **Thêm (curator fill để cân quota)** | 4 |
| **Tổng seed** | 90 |
| Dual-domain (≥2 mã) | 36 |

### Phân bổ primary domain (đếm `domains[0]`)

| Domain | Mục tiêu | Thực tế | Lệch |
|---|---:|---:|---:|
| PHYSICAL | 16 | 16 | +0 |
| COGNITIVE | 14 | 14 | +0 |
| LANGUAGE | 16 | 16 | +0 |
| SOCIAL_EMOTIONAL | 14 | 14 | +0 |
| AESTHETIC | 12 | 12 | +0 |
| SELF_CARE | 18 | 18 | +0 |
| **Tổng** | **90** | **90** | **+0** |

## Bỏ

| id | Lý do |
|---|---|
| `phy_04_an_muong_tap` | Trùng mục tiêu với `act_sc_eat_018_01` (Thìa của Gấu) — nhường primary SELF_CARE |
| `cog_11_nong_khong_cham` | Trùng `act_sc_safe_024_16` (Nóng — dừng tay) — nhường SELF_CARE |
| `SOC-05` | Gần trùng `SOC-04` (Chào và tạm biệt cửa) — giữ 1 nghi thức chào/tạm biệt |

## Thêm (curator)

| id | Domain primary | Căn cứ |
|---|---|---|
| `phy_16_bo_ham_goi` | PHYSICAL | Bù quota PHYSICAL; vận động thô toddler (chui/bò có đích) từ khung 01/CDC gross |
| `phy_17_vac_tui_nhe` | PHYSICAL | Bù quota; mang–xách chức năng nhà VN |
| `LANG-16` | LANGUAGE | Bù quota; hỏi–đáp Ai/Đâu/Gì (mở rộng mục tiêu ngôn ngữ 24–36m trong 01/03) |
| `AES-12` | AESTHETIC | Bù quota; “trưng bày sản phẩm” nêu trong khung thẩm mỹ 01 |

## Sửa / chuẩn hóa

- Đổi tên trường: `age_min` / `age_range` / `age_min_months` → `ageMinMonths` / `ageMaxMonths`; `duration*` → `durationMinutes`; `parent_phrases` → `parentPhrases`.
- Ép `reviewed_by: []`, `isPremium: false`, wrapper `version: 1`, `content_status: "draft_unreviewed"`.
- Bổ sung `safety` mặc định nếu thiếu (rủi ro giám sát/sàn/nuốt dị vật).
- Giữ id gốc research (ổn định); dual-domain giữ nguyên thứ tự trừ khi đã là primary đúng quota.

### Gần-trùng GIỮ (phân biệt mục tiêu)

| Cặp | Cách xử lý |
|---|---|
| `phy_03_nguech_sap_lon` vs `AES-01` | Giữ cả hai: PHYSICAL+AESTHETIC (vận động tinh) vs AESTHETIC (cảm nhận tạo hình) |
| `phy_09_van_nap_hop` vs `cog_03_mo_nap_hop` | Giữ: vặn/xoay vs hai tay giữ–mở |
| `cog_07_hai_buoc_don_do` vs `LANG-10` | Giữ: nhận thức 2 bước vs ngôn ngữ làm theo lời |
| `act_sc_sleep_018_07` vs `AES-03` | Giữ: nghi thức ngủ vs hát chơi thẩm mỹ |
| `phy_12_mac_quan_rong` vs `act_sc_potty_030_13` | Giữ: mặc quần (tinh–thô) vs kéo quần gắn bô (SELF_CARE) |

## Lệch phân bổ

- Không lệch: đúng 16/14/16/14/12/18 theo primary domain.

## Giả định

1. Primary domain = phần tử đầu của `domains[]` (theo `research/01` §5.2).
2. `ageMaxMonths` inclusive cho picker (chưa chốt exclusive ở TECH) — ghi nhận để Wave 3.
3. 4 HĐ thêm là diễn giải sản phẩm từ khung 01 + lỗ hổng quota, **không** phải trích nguyên văn Bộ/CDC.
4. Mọi HĐ vẫn `reviewed_by: []` — không claim đã duyệt chuyên gia / chuẩn Bộ.
5. An toàn chỉ gắn hoạt động; không thay curriculum *Bé Gấu An Toàn*.
6. Tone không phán xét; không chẩn đoán y tế — giữ nguyên copy research khi chuẩn hóa.

## Output

- `content/seed/activities_mvp_draft.json`
- `content/CURATION_LOG.md` (file này)

## Không đụng

- Wave 1 markdown (`research/01–04`, `plans/PRODUCT_DESIGN.md`, `plans/TECH_ARCHITECTURE.md`)
- `AGENTS.md` / `PROJECT_STATE.md`
- Code app
