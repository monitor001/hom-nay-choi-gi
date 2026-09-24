# Đội lập kế hoạch Thưởng / Vật phẩm — Hiến chương

> App: **Hôm nay chơi gì?** · Parent companion 18–36 tháng  
> Persona AI nội bộ — **chỉ kế hoạch**, không code Android trong lượt này.  
> Ngày: 2026-09-24

## Ràng buộc sản phẩm (bắt buộc)

1. App dành **phụ huynh**, không phải máy slot cho trẻ. Bé học qua chơi ngoài đời thật.
2. CONSENSUS: không phán xét, không “bạn bỏ lỡ”, không chẩn đoán, KPI = hoạt động hoàn thành / tuần — **không** tối đa thời gian trong app.
3. SPEC: **không phần thưởng gây nghiện**; nội dung trẻ xem (nếu có) có hẹn giờ.
4. Vật phẩm kiểu kẹo / nước / ăn ốc = **catalog ví dụ quy đổi** do PH chọn trong đời thật — app **không** khuyến khích dùng đồ ăn để ép hành vi; ưu tiên nhãn trung tính + cảnh báo nuôi dưỡng.
5. Offline-first, local-only MVP; không IAP bắt buộc; không ads.

## Vai trò

| ID | Vai trò | Deliverable |
|---|---|---|
| **R1** | Economy / bảng giá | Đơn vị tiền ảo, earn rates, catalog vật phẩm + giá quy đổi |
| **R2** | Tâm lý PH–trẻ & nuôi dưỡng | Guardrail: thưởng gì được / cấm; copy tone |
| **R3** | Vòng nhiệm vụ & streak | Thưởng ngày, hoàn thành HĐ, chuỗi ngày, anti-abuse |
| **R4** | Tổng hợp | `00_CONSENSUS_REWARDS.md` — chốt số liệu + phased MVP |

## Đơn vị làm việc đề xuất (R1 có thể đổi tên)

- **Gấu Xu (GX)** — điểm mềm, PH kiếm khi chơi thật với con (complete activity + nhật ký nhẹ).
- **Đổi thưởng** = PH tự mua/chuẩn bị ngoài đời theo bảng gợi ý (app không ship hàng).

## Verdict

`PASS` · `REVISE` · `REJECT` (xung đột CONSENSUS)

## Deliverables

`qa/experts/rewards/01_R1_economy.md` · `02_R2_psychology.md` · `03_R3_missions.md` · `00_CONSENSUS_REWARDS.md`
