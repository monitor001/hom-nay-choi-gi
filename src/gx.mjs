/** Soft ledger Gấu Xu — mirror Android GxRates (local-only). */

export const GX_RATES = {
  COMPLETE: 15,
  DAILY_CAP: 45,
  WEEKLY_CAP: 150,
  MAX_COMPLETE_EARNS_PER_DAY: 3,
  COOLDOWN_MS: 8 * 60 * 1000,
};

function dayBounds(isoDate) {
  const start = new Date(`${isoDate}T00:00:00`).getTime();
  return [start, start + 86400000];
}

function weekBounds(isoDate) {
  const d = new Date(`${isoDate}T12:00:00`);
  const day = d.getDay(); // 0 Sun
  const mondayOffset = day === 0 ? -6 : 1 - day;
  const monday = new Date(d);
  monday.setDate(d.getDate() + mondayOffset);
  const iso = monday.toISOString().slice(0, 10);
  const start = new Date(`${iso}T00:00:00`).getTime();
  return [start, start + 7 * 86400000];
}

export function gxBalance(ledger = []) {
  return ledger.reduce((s, e) => s + (e.amount || 0), 0);
}

function earnedBetween(ledger, startMs, endMs) {
  return ledger
    .filter((e) => e.amount > 0 && e.at >= startMs && e.at < endMs)
    .reduce((s, e) => s + e.amount, 0);
}

function countCompleteToday(ledger, activityId, dayStart, dayEnd) {
  return ledger.filter(
    (e) =>
      e.kind === "COMPLETE" &&
      e.refId === activityId &&
      e.at >= dayStart &&
      e.at < dayEnd,
  ).length;
}

function completeEarnCountToday(ledger, dayStart, dayEnd) {
  return ledger.filter(
    (e) => e.kind === "COMPLETE" && e.amount > 0 && e.at >= dayStart && e.at < dayEnd,
  ).length;
}

function lastCompleteAt(ledger) {
  let max = 0;
  for (const e of ledger) {
    if (e.kind === "COMPLETE" && e.amount > 0 && e.at > max) max = e.at;
  }
  return max;
}

function remainingCap(dayEarned, weekEarned, want) {
  const room = Math.min(GX_RATES.DAILY_CAP - dayEarned, GX_RATES.WEEKLY_CAP - weekEarned);
  return Math.max(0, Math.min(want, room));
}

function newId() {
  return "gx_" + Math.random().toString(36).slice(2, 10) + Date.now().toString(36).slice(-4);
}

/**
 * @returns {{ awarded: number, message: string, ledger: any[] }}
 */
export function earnOnComplete(ledger, activityId, todayIso = new Date().toISOString().slice(0, 10)) {
  const list = [...(ledger || [])];
  const [dayStart, dayEnd] = dayBounds(todayIso);
  const [weekStart, weekEnd] = weekBounds(todayIso);
  const now = Date.now();
  let awarded = 0;
  const notes = [];
  let denyHint = null;

  const already = countCompleteToday(list, activityId, dayStart, dayEnd);
  const completesToday = completeEarnCountToday(list, dayStart, dayEnd);
  const lastAt = lastCompleteAt(list);
  const dayEarned = earnedBetween(list, dayStart, dayEnd);
  const weekEarned = earnedBetween(list, weekStart, weekEnd);

  if (already > 0) denyHint = "Hôm nay đã nhận GX cho hoạt động này rồi.";
  else if (completesToday >= GX_RATES.MAX_COMPLETE_EARNS_PER_DAY)
    denyHint = "Hôm nay đủ nhịp thưởng rồi — phần còn lại là chơi thật.";
  else if (now - lastAt < GX_RATES.COOLDOWN_MS)
    denyHint = "Chờ một chút rồi chơi tiếp nhé (tránh bấm liên tục).";
  else if (remainingCap(dayEarned, weekEarned, GX_RATES.COMPLETE) <= 0)
    denyHint = "Đã chạm trần GX hôm nay/tuần — vẫn ghi nhận buổi chơi.";

  if (
    already === 0 &&
    completesToday < GX_RATES.MAX_COMPLETE_EARNS_PER_DAY &&
    now - lastAt >= GX_RATES.COOLDOWN_MS
  ) {
    const room = remainingCap(dayEarned, weekEarned, GX_RATES.COMPLETE);
    if (room > 0) {
      list.push({
        id: newId(),
        at: now,
        amount: room,
        kind: "COMPLETE",
        refId: activityId,
        note: "Hoàn thành hoạt động",
      });
      awarded += room;
      notes.push(`+${room} GX hoàn thành HĐ`);
      denyHint = null;
    }
  }

  const message =
    awarded > 0 ? notes.join(" · ") : denyHint || "Đã ghi nhận buổi chơi.";
  return { awarded, message, ledger: list };
}

/**
 * @returns {{ ok: boolean, message: string, ledger: any[] }}
 */
export function redeemItem(ledger, item) {
  const bal = gxBalance(ledger);
  if (bal < item.costGx) {
    return { ok: false, message: "Chưa đủ Gấu Xu.", ledger };
  }
  const list = [
    ...(ledger || []),
    {
      id: newId(),
      at: Date.now(),
      amount: -item.costGx,
      kind: "REDEEM",
      refId: item.id,
      note: item.title,
    },
  ];
  return {
    ok: true,
    message: `Đã đổi «${item.title}» (−${item.costGx} GX). PH chuẩn bị khi sẵn sàng.`,
    ledger: list,
  };
}

export function tierLabel(tierId, tiers = []) {
  return tiers.find((t) => t.id === tierId)?.label || tierId;
}
