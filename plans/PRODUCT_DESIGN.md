# Gấu Con — Thiết kế sản phẩm / UX (MVP)

> Phiên bản: 1.0 · Nền tảng: Android (phụ huynh) · Nguồn: `docs/SPEC_MVP.md` 1.0  
> Thử nghiệm phụ trong `ideas/BeGau` · **Không đổi hướng active** · **Chưa code Compose/Kotlin**  
> Persona dưới đây suy từ SPEC §1.2 (nhu cầu thiết kế), **không** phải kết quả user research đã xác thực.

---

## 1. Value prop & persona

### Value prop (1 câu)
Mỗi ngày Gấu Con gợi ý 2–3 hoạt động ngắn (5–15 phút) để bố mẹ chơi và dạy con ngoài đời thật — bằng đồ có sẵn trong nhà — kèm mốc phát triển, nhật ký và nhắc nhở nhẹ, không phán xét.

### Persona thiết kế (từ SPEC, chưa phỏng vấn)

| Persona | Hồ sơ | Mục tiêu khi mở app | Ràng buộc UX |
|---|---|---|---|
| **Lan** — bố/mẹ bận | 25–40 tuổi, đi làm; 1 bé 18–36 tháng | Biết *hôm nay chơi gì* trong ≤30 giây; làm một tay khi bế bé | Ít bước, CTA rõ, offline được, không guilt-trip khi bỏ lỡ ngày |
| **Bà Hạnh** — ông bà / người trông | Chăm bé ban ngày; ít quen app | Làm đúng vài bước hoạt động; chữ đọc được từ xa | Cỡ chữ lớn mặc định/đề xuất, contrast cao, ít jargon, nút to |

**Giáo viên mầm non:** ngoài MVP (P2) — không thiết kế sâu ở bản này.

**Đề xuất xác thực (chưa làm):** 5–8 phỏng vấn ngắn bố mẹ + ông bà (18–36m) về “hôm nay chơi gì”, cỡ chữ, và lý do tắt thông báo — ghi là *đề xuất*, không dùng số liệu giả.

---

## 2. Information Architecture

### Bottom nav (5 tab)

| Tab | Màn neo | Nội dung chính |
|---|---|---|
| **Hôm nay** | S04 | Gợi ý ngày + lối tắt nhật ký / nhắc sắp tới |
| **Thư viện** | S07 | Tìm & lọc hoạt động (không phải “thư viện cho bé”) |
| **Phát triển** | S08 | Checklist mốc theo lĩnh vực |
| **Nhật ký** | S09 | Timeline ảnh / từ mới / khoảnh khắc |
| **Thêm** | Hub → S10, S11, S13, S14 | Cẩm nang · Nhắc nhở · Lịch sức khỏe · Cài đặt |

### Map S01–S14 → luồng MVP

```
[Lần đầu]
  S01 Chào mừng → S02 Hồ sơ bé → S03 Nhắc nhở + xin quyền → S04 Hôm nay
                                                              │
[Hàng ngày]                                                   │
  S04 → S05 Chi tiết → S06 Phản hồi hoàn thành ───────────────┘
  S04 → (đổi thẻ) reload picker cùng ngày
  Notif DAILY_ACTIVITY → deep link S05 / S04

[Khám phá]
  Tab Thư viện S07 → S05 → S06
  Tab Phát triển S08 (cập nhật Đã / Đang tập / Chưa)
  Tab Nhật ký S09 (tạo / xem)
  Tab Thêm → S10 Cẩm nang | S11→S12 Nhắc nhở | S13 Sức khỏe | S14 Cài đặt
```

| Mã | Vai trò trong luồng chính |
|---|---|
| S01–S03 | Onboarding một lần (F1 + F8 setup) |
| S04–S06 | Vòng lõi giá trị: gợi ý → làm → phản hồi (F2–F4) |
| S07 | Bổ sung khi “hôm nay” không hợp / muốn chọn chủ đề (F2/F3) |
| S08 | Theo dõi mốc, nuôi ưu tiên picker (F5) |
| S09 | Ghi nhớ & gắn ảnh sau hoàn thành (F6) |
| S10 | Hỗ trợ tình huống chăm sóc (F7) |
| S11–S12 | Quản lý nhắc (F8) |
| S13 | Lịch tiêm/khám do PH nhập (F8 phụ) |
| S14 | Cỡ chữ, dữ liệu, privacy (F9) |

---

## 3. UX chi tiết màn P0

**Tone copy chung (không phán xét):** mời (“…nhé?”), chấp nhận bỏ lỡ, nhắc “mỗi bé một nhịp”. Cấm: “Bạn đã bỏ lỡ…”, “Bé chậm hơn…”, so sánh % với trẻ khác.

**A11y nền (mọi màn):**
- Cỡ chữ: theo setting S14 (Nhỏ / Vừa / Lớn / Rất lớn); mặc định **Lớn** nếu phát hiện font scale hệ thống ≥ 1.3 (giả định UX — cần xác nhận thiết bị thật).
- TalkBack: mọi nút có `contentDescription`; thẻ hoạt động đọc “{tên}, {phút} phút, lĩnh vực {x}”; trạng thái mốc đọc rõ ba mức.
- Mục tiêu đụng: ≥ 48dp; một tay với CTA chính ở nửa dưới màn.
- Contrast: text chính ≥ WCAG AA trên nền sáng; không phụ thuộc chỉ màu cho cảm xúc 😊😐😕 (kèm nhãn chữ).
- Offline: nội dung seed + nhật ký cục bộ vẫn mở được; video có trạng thái “Chưa tải — vẫn làm theo bước”.

### S01 — Chào mừng
| | |
|---|---|
| **Thành phần** | 3 slide (gợi ý chơi thật / không phán xét / nhắc nhẹ); nút **Bắt đầu**; chỉ báo trang |
| **Empty** | N/A |
| **Copy** | Slide 1: “Mỗi ngày vài phút chơi cùng con.” · 2: “Gợi ý bằng đồ có sẵn trong nhà.” · 3: “Mỗi bé một nhịp — không so sánh.” |
| **A11y** | Vuốt ngang có nhãn trang; nút Bắt đầu luôn visible |

### S02 — Tạo hồ sơ bé
| | |
|---|---|
| **Thành phần** | Tên gọi (bắt buộc), ngày sinh (bắt buộc → tuổi tháng), giới tính (tùy chọn), ảnh (tùy chọn + bỏ qua) |
| **Empty / lỗi** | Ngày sinh trống → “Chọn ngày sinh để gợi ý đúng tuổi.” Không bắt họ tên giấy tờ |
| **Copy** | “Tên gọi ở nhà (ví dụ: Gấu, Bon…)” |
| **A11y** | Date picker TalkBack; ảnh có “Thêm ảnh” / “Bỏ qua” |

### S03 — Thiết lập nhắc nhở (+ xin quyền)
| | |
|---|---|
| **Thành phần** | Giải thích ngắn; chọn giờ `DAILY_ACTIVITY` (mặc định 19:30); toggle nếp sinh hoạt phổ biến (mặc định **tắt**); CTA **Bật nhắc nhở** / **Để sau** |
| **Empty** | N/A |
| **Copy** | Theo SPEC §5.7 — xem wireframe mục 5 |
| **A11y** | Time picker; trạng thái quyền đọc được sau khi hệ thống trả lời |

### S04 — Hôm nay (hub)
| | |
|---|---|
| **Thành phần** | Lời chào + tên + “{n} tháng”; 2–3 thẻ hoạt động (ảnh/icon lĩnh vực, phút, 1 dòng goal); nút **Đổi gợi ý** (cùng ngày, tôn trọng seed + lịch sử); khối “Nhắc sắp tới” (0–2 dòng); lối tắt **Thêm vào nhật ký** |
| **Empty** | Không còn hoạt động phù hợp tuổi: “Hôm nay chưa có gợi ý mới. Xem Thư viện hoặc thử lại ngày mai.” |
| **Copy** | “Chào {tên} — hôm nay chơi gì nào?” Không: “Bạn chưa chơi hôm qua.” |
| **A11y** | Thẻ là button; banner xin quyền (nếu từ chối) tối đa 1 lần/tuần, dismiss được |

### S05 — Chi tiết hoạt động
| | |
|---|---|
| **Thành phần** | Tiêu đề, phút, lĩnh vực; video (tùy chọn, không autoplay liên tục); đồ dùng; **các bước 3–5**; câu nói mẫu; **Dễ hơn / Khó hơn**; hộp **An toàn**; CTA **Hoàn thành** |
| **Empty** | Video lỗi mạng: ẩn player, giữ bước text |
| **Copy** | Bước ngắn, động từ; safety không hù dọa thừa |
| **DoD đọc** | Phụ huynh đọc xong phần cần thiết ≤ **30 giây** (goal + đồ dùng + bước) — xem mục 7 |
| **A11y** | Video có nút play rõ; bước đánh số; CTA sticky đáy |

### S06 — Phản hồi hoàn thành
| | |
|---|---|
| **Thành phần** | 3 lựa chọn cảm xúc (😊 Bé thích / 😐 Bình thường / 😕 Chưa hợp); ghi chú tùy chọn; **Thêm ảnh vào nhật ký**; **Xong** |
| **Empty** | Bỏ qua ghi chú/ảnh được |
| **Copy** | “Chưa hợp cũng không sao — mai gợi ý khác nhé.” |
| **A11y** | Ba nút radio có nhãn chữ, không chỉ emoji |

### S07 — Thư viện hoạt động
| | |
|---|---|
| **Thành phần** | Search; chip lọc lĩnh vực / thời lượng / đồ dùng đơn giản; danh sách thẻ → S05 |
| **Empty** | “Không thấy hoạt động khớp. Thử bỏ bớt bộ lọc.” |
| **Copy** | Trung lập, không “bạn nên làm ngay” |
| **A11y** | Chip là toggle có trạng thái; kết quả “N hoạt động” |

### S08 — Mốc phát triển
| | |
|---|---|
| **Thành phần** | Tab/accordion 6 lĩnh vực; checklist theo band tuổi; 3 trạng thái **Đã làm được / Đang tập / Chưa**; banner cố định “Mỗi bé một nhịp” |
| **Empty** | Band chưa có mốc seed: “Đang cập nhật mốc cho độ tuổi này.” |
| **Copy** | Khi nhiều “Chưa”: “Có mốc bé chưa làm — bình thường. Khi sẵn sàng, đổi sang Đang tập.” **Không** % so với trẻ khác / biểu đồ percentile |
| **A11y** | Mỗi mốc: tên + trạng thái hiện tại; disclaimer “không chẩn đoán y tế” gần cuối danh sách |

### S09 — Nhật ký
| | |
|---|---|
| **Thành phần** | Timeline; FAB/ nút **Thêm** (ảnh / từ mới / khoảnh khắc); lọc loại |
| **Empty** | “Chưa có gì trong nhật ký. Lưu một khoảnh khắc hoặc từ mới của {tên} nhé.” |
| **Copy** | Nhẹ, kỷ niệm — không “bạn quên ghi tuần này” trên empty (để reminder `JOURNAL_WEEKLY` đảm nhiệm) |
| **A11y** | Ảnh có mô tả tùy chọn; nút thêm lớn |

### S10 — Cẩm nang
| | |
|---|---|
| **Thành phần** | Danh mục tình huống (~20 bài MVP); bài đọc ngắn + “khi nào nên hỏi bác sĩ” nếu phù hợp |
| **Empty** | Offline thiếu bài: giữ danh mục đã seed |
| **Copy** | Không đổ lỗi PH; không tự chẩn đoán |
| **A11y** | Heading bài rõ; cỡ chữ theo setting |

### S11 — Quản lý nhắc nhở
| | |
|---|---|
| **Thành phần** | List loại nhắc + toggle; giờ/thứ tóm tắt; **Giờ yên lặng**; vào S12 |
| **Empty** | Mọi loại tắt: “Chưa bật nhắc nào. App vẫn dùng bình thường.” |
| **Copy** | R3 prompt (khi cờ bật): “Giờ nhắc này có hợp không?” → Đổi giờ / Nhắc thưa hơn / Tắt |
| **A11y** | Toggle có nhãn loại đầy đủ |

### S12 — Sửa nhắc nhở
| | |
|---|---|
| **Thành phần** | Loại (readonly hoặc chọn), giờ, lặp (thứ / khoảng giờ với POTTY), nội dung tùy chỉnh (optional), lưu |
| **Empty** | N/A |
| **Copy** | Gợi ý mẫu đã duyệt; placeholder “Để trống = dùng câu mặc định” |
| **A11y** | Form tuần tự; lỗi validation nói rõ |

### S13 — Lịch sức khỏe
| | |
|---|---|
| **Thành phần** | List lịch PH nhập; thêm mới; bảng tham khảo tiêm (kèm “xác nhận với cơ sở y tế”) |
| **Empty** | “Chưa có lịch. Thêm theo sổ tiêm hoặc lịch phòng khám.” App **không** tự tạo mũi tiêm |
| **Copy** | Trung lập; không “bạn chậm tiêm” |
| **A11y** | Ngày/giờ đọc được; disclaimer dễ tìm |

### S14 — Cài đặt
| | |
|---|---|
| **Thành phần** | Hồ sơ bé; cỡ chữ; quản lý nhắc (→ S11); xuất ZIP; xóa dữ liệu; chính sách quyền riêng tư |
| **Empty** | N/A |
| **Copy** | Xóa: xác nhận 2 bước, giải thích không hoàn tác |
| **A11y** | Preview cỡ chữ ngay trên màn |

---

## 4. Phản hồi hoàn thành (😊😐😕) & nuôi Daily Picker

### UI S06
| Mã cảm xúc | Nhãn | Ý nghĩa cho PH | Tín hiệu cho hệ thống |
|---|---|---|---|
| 😊 | Bé thích | Buổi chơi vui / hợp | Tăng nhẹ ưu tiên hoạt động/domain tương tự |
| 😐 | Bình thường | Xong, không đặc biệt | Trung tính — chỉ ghi lịch sử 14 ngày |
| 😕 | Chưa hợp | Không khớp lúc này (không = thất bại) | **Giảm trọng số** hoạt động tương tự (SPEC §7.5 bước 5) |

Luồng tối thiểu: chọn 1 cảm xúc → (tuỳ chọn ghi chú/ảnh) → **Xong** → về S04 với thẻ đã hoàn thành đánh dấu / ẩn khỏi “cần làm hôm nay”.

### Cách nuôi thuật toán Daily Picker (MVP — khớp SPEC §7.5)

```
Đầu vào: tuổi tháng, ActivityLog 14 ngày, cảm xúc, MilestoneStatus "Đang tập"
1. Lọc age_min ≤ tuổi ≤ age_max
2. Loại đã làm trong 14 ngày
3. Ưu tiên domain ít làm nhất 7 ngày
4. Ưu tiên domain có mốc "Đang tập"
5. Giảm trọng số hoạt động "tương tự" các lần 😕 (cùng domain chính hoặc cùng tag materials — chi tiết tag: câu hỏi mở §9)
6. Chọn 3 HĐ ≥ 2 domain, tổng phút ≤ 30
7. Seed ổn định: childId + ngày lịch (local) — Đổi gợi ý trong ngày: re-roll có ràng buộc không trùng id đã hiện hôm nay nếu còn ứng viên
```

**Không đưa vào MVP picker:** so sánh với cohort, streak gây tội lỗi, “bé nhà bạn kém hơn…”.

---

## 5. Onboarding + xin quyền thông báo (SPEC §5.7)

### Nguyên tắc
1. **Không** xin `POST_NOTIFICATIONS` ở S01.  
2. Giải thích lợi ích → chọn giờ → mới xin quyền (Android 13+).  
3. Từ chối: app chạy đủ; banner S04 ≤ 1 lần/tuần → mở settings hệ thống.

### Wireframe mô tả (markdown)

```
┌─────────────────────────────┐
│  S01  Chào mừng             │
│  [●○○] slide 1/3            │
│  Mỗi ngày vài phút…         │
│           [ Bắt đầu ]       │
└─────────────┬───────────────┘
              ▼
┌─────────────────────────────┐
│  S02  Hồ sơ bé              │
│  Tên gọi: [________]        │
│  Ngày sinh: [__/__/____]    │
│  Ảnh: [Thêm]  [Bỏ qua]      │
│           [ Tiếp tục ]      │
└─────────────┬───────────────┘
              ▼
┌─────────────────────────────┐
│  S03  Nhắc chơi cùng con    │
│                             │
│  Gấu Con sẽ nhắc bạn        │
│  1 lần mỗi tối để chơi      │
│  cùng con. Đổi giờ hoặc     │
│  tắt bất cứ lúc nào.        │
│                             │
│  Giờ nhắc:  [ 19 : 30  ⌄ ]  │
│  □ Nhắc nếp ăn/ngủ… (tắt)   │
│                             │
│  [  Bật nhắc nhở  ]         │
│  [  Để sau        ]         │
└─────────────┬───────────────┘
              │
     ┌────────┴────────┐
     ▼                 ▼
 System dialog      Skip quyền
 POST_NOTIFICATIONS     │
     │                 │
     └────────┬────────┘
              ▼
┌─────────────────────────────┐
│  S04  Hôm nay               │
│  (nếu denied: banner nhẹ    │
│   “Bật nhắc để không quên   │
│    chơi tối nay” [Bật][X])  │
└─────────────────────────────┘
```

Copy CTA khớp mẫu SPEC §9.9: tiêu đề “Nhắc bạn chơi cùng con mỗi tối”; phụ đề giới hạn 1 lần/ngày + quyền kiểm soát.

---

## 6. Mốc phát triển UI — “mỗi bé một nhịp”

| Làm | Không làm (MVP) |
|---|---|
| 3 trạng thái rõ nghĩa, đổi 1 chạm | % hoàn thành so với “trẻ cùng tháng” |
| Banner / footer: “Mỗi bé một nhịp riêng” | Badge “chậm / sớm / top %” |
| Gợi ý hoạt động từ mốc **Đang tập** (qua picker) | Biểu đồ percentile WHO/CDC trên UI phụ huynh |
| Disclaimer: tham khảo, **không chẩn đoán**; “nên hỏi bác sĩ” khi phù hợp | Push guilt khi nhiều mốc “Chưa” |

WHO/CDC chỉ là nguồn tham chiếu nội dung checklist (research) — **không** hiện số liệu chuẩn tăng trưởng lâm sàng trên S08 MVP.

---

## 7. Definition of Done nội dung + nghiệm thu phụ huynh

### DoD nội dung hoạt động / cẩm nang (trước xuất bản)
- [ ] Đủ trường khuôn SPEC (id, age, domains, steps 3–5, safety, …)
- [ ] `reviewed_by` **không rỗng** (ít nhất 1 chuyên gia) trước khi ship production
- [ ] Đọc goal + materials + steps ≤ **30 giây** (đồng hồ phụ huynh)
- [ ] Copy không phán xét; có dễ hơn/khó hơn khi phù hợp
- [ ] Chuỗi UI trong `strings.xml` (không hard-code) — khi vào giai đoạn code
- [ ] Safety cụ thể với đồ nhà VN; không hù dọa thừa

### Tiêu chí nghiệm thu phụ huynh (chấp nhận bản thử)
| # | Tiêu chí | Cách kiểm |
|---|---|---|
| 1 | Hiểu hoạt động trên S05 trong ≤30s | 5 PH đọc thầm, nói lại được “làm gì / cần gì” |
| 2 | Hoàn thành ≥1 HĐ trong 24h đầu (mục tiêu sản phẩm ≥60% cohort thử — đo sau, không bịa) | Analytics / nhật ký thử |
| 3 | Ông bà đọc được với cỡ Lớn | Thử trên 1 máy + font scale |
| 4 | TalkBack đọc được thẻ S04 và 3 nút S06 | Kiểm thủ công |
| 5 | Từ chối thông báo vẫn dùng Hôm nay / Thư viện | Test S03 “Để sau” |
| 6 | S08 không có so sánh % với trẻ khác | Review UI |

**KPI sản phẩm (SPEC):** ≥ 3 hoạt động hoàn thành / gia đình / tuần — **không** dùng thời gian trong app.

---

## 8. OUT of MVP (tránh scope creep)

### P1 (giai đoạn 2) — không thiết kế sâu / không build MVP
- Nhiều bé / nhiều người chăm + đồng bộ cloud  
- Thư viện **cho bé** (sách nói, hát + hẹn giờ `SCREEN_TIME`)  
- Premium / Play Billing  
- Báo cáo tuần kiểu “đã chơi 5 hoạt động” (nếu làm: chỉ đếm tích cực, không guilt)  
- Cá nhân hóa giờ nhắc tự động (§5.6 — chỉ gợi ý)  
- Mở rộng đủ 0–6 tuổi  

### P2 (giai đoạn 3)
- Lộ trình AI  
- Bản trường mầm non  
- Song ngữ Việt–Anh  

### Cũng OUT MVP (dù hấp dẫn)
- Social feed phụ huynh, streak “đừng phá chuỗi”, leaderboard  
- Chẩn đoán / điểm phát triển / so sánh chuẩn  
- Quảng cáo, SDK quảng cáo, bán dữ liệu  
- Exact alarm / kéo PH mở app tối đa  

---

## 9. Câu hỏi mở — parent chốt trước khi code

1. **Cỡ chữ mặc định:** Vừa hay Lớn cho lần cài đầu (ưu tiên ông bà vs thẩm mỹ bố mẹ trẻ)?  
2. **Đổi gợi ý S04:** giới hạn bao nhiêu lần/ngày? Có cho chọn thủ công 1 HĐ từ Thư viện thay 1 thẻ hôm nay không?  
3. **“Tương tự” khi 😕:** cùng `domains[0]` thôi, hay thêm tag (materials / goal_type)?  
4. **Video trên S05:** bắt buộc cho 90 HĐ MVP hay text-first, video dần?  
5. **S13 trong tab Thêm:** có cần shortcut từ S04 không, hay chỉ deep link từ notif?  
6. **Ảnh nhật ký:** có OCR/gợi ý caption không (đề xuất: **không** MVP)?  
7. **Ngôn ngữ xưng hô:** luôn “bố mẹ” / “bạn” / theo tên bé — chọn 1 giọng strings?  
8. **Onboarding bỏ qua S03:** “Để sau” có còn bật mặc định `DAILY_ACTIVITY` trong app (chỉ không có system notif) không?  
9. **Phỏng vấn xác thực persona:** có chạy 5–8 cuộc trước sprint UI không? (*đề xuất*)  
10. **Dark mode:** SPEC DoD kỹ thuật nhắc chế độ tối — có bắt buộc sprint MVP UI không?

---

## Phụ lục A — Map F1–F9 → màn

| Feature | Màn chính |
|---|---|
| F1 Onboarding & hồ sơ | S01, S02, (S14 sửa hồ sơ) |
| F2 Hoạt động hôm nay | S04, S07 |
| F3 Chi tiết | S05 |
| F4 Hoàn thành | S06 |
| F5 Mốc | S08 |
| F6 Nhật ký | S09 |
| F7 Cẩm nang | S10 |
| F8 Nhắc nhở | S03, S11, S12, S13 |
| F9 Cài đặt | S14 |

## Phụ lục B — Nguồn & giới hạn
- Nguồn ràng buộc: SPEC 1.0 (parent paste), `PLAN_PHAN_TICH.md`, nguyên tắc skill `thiet-ke-sp-tre` (người lớn đồng hành, MVP hẹp).  
- Không bịa kết quả nghiên cứu người dùng; số KPI trong SPEC là **mục tiêu**, chưa đo.  
- Nội dung hoạt động từ research sibling = nháp đến khi `reviewed_by` có chuyên gia.
