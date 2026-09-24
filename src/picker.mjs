/**
 * Daily activity picker — port of gaucon DailyPicker (CONSENSUS §3.1).
 * Stable for same childId|date.
 */

function dailySeed(childId, dayIso) {
  const s = `${childId}|${dayIso}`;
  let h = 0;
  for (let i = 0; i < s.length; i++) h = (Math.imul(31, h) + s.charCodeAt(i)) | 0;
  return h >>> 0;
}

function mulberry32(a) {
  return function () {
    let t = (a += 0x6d2b79f5);
    t = Math.imul(t ^ (t >>> 15), t | 1);
    t ^= t + Math.imul(t ^ (t >>> 7), t | 61);
    return ((t ^ (t >>> 14)) >>> 0) / 4294967296;
  };
}

function daysBetween(isoA, isoB) {
  const a = new Date(isoA + "T12:00:00");
  const b = new Date(isoB + "T12:00:00");
  return Math.round((b - a) / 86400000);
}

/**
 * @param {object} input
 * @param {string} input.childId
 * @param {number} input.ageMonths
 * @param {string} input.todayIso
 * @param {Array} input.activities
 * @param {Array<{activityId:string, primaryDomain?:string, completedDate:string, feedback?:string}>} [input.history14Days]
 * @param {string[]} [input.practicingDomains]
 * @param {number} [input.maxItems]
 * @param {number} [input.maxTotalMinutes]
 * @param {string[]} [input.excludeExtraIds] ids already shown today (swap)
 */
export function pickDaily(input) {
  const {
    childId,
    ageMonths: age,
    todayIso,
    activities,
    history14Days = [],
    practicingDomains = [],
    maxItems = 3,
    maxTotalMinutes = 30,
    excludeExtraIds = [],
  } = input;

  const excludeIds = new Set([
    ...excludeExtraIds,
    ...history14Days
      .filter((h) => daysBetween(h.completedDate, todayIso) <= 14)
      .map((h) => h.activityId),
  ]);

  const notFitDomains = new Set(
    history14Days.filter((h) => h.feedback === "NOT_FIT").map((h) => h.primaryDomain).filter(Boolean),
  );

  const domainLastUsed = new Map();
  for (const h of history14Days) {
    if (!h.primaryDomain) continue;
    const prev = domainLastUsed.get(h.primaryDomain);
    if (!prev || h.completedDate > prev) domainLastUsed.set(h.primaryDomain, h.completedDate);
  }

  const practicing = new Set(practicingDomains);

  const candidates = activities.filter(
    (a) =>
      !a.isRetired &&
      age >= a.ageMinMonths &&
      age <= a.ageMaxMonths &&
      !excludeIds.has(a.id),
  );

  if (!candidates.length) return [];

  const rng = mulberry32(dailySeed(childId, todayIso));
  const weightDomainRecency = 3;
  const weightPracticing = 2;
  const weightNotFitPenalty = 4;

  const scored = candidates.map((a) => {
    const primary = a.domains?.[0];
    let score = 0;
    if (primary) {
      const last = domainLastUsed.get(primary);
      const daysSince = last == null ? 14 : Math.min(14, Math.max(0, daysBetween(last, todayIso)));
      score += weightDomainRecency * daysSince;
      if (practicing.has(primary)) score += weightPracticing;
      if (notFitDomains.has(primary)) score -= weightNotFitPenalty;
    }
    return { activity: a, score, tie: rng() };
  });

  scored.sort((x, y) => y.score - x.score || x.tie - y.tie);

  const picked = [];
  let totalMin = 0;
  const domainsUsed = new Set();

  for (const item of scored) {
    if (picked.length >= maxItems) break;
    const a = item.activity;
    if (totalMin + (a.durationMinutes || 0) > maxTotalMinutes && picked.length) continue;
    picked.push(a);
    totalMin += a.durationMinutes || 0;
    if (a.domains?.[0]) domainsUsed.add(a.domains[0]);
  }

  if (picked.length >= 2 && domainsUsed.size < 2) {
    const lastDur = picked[picked.length - 1]?.durationMinutes || 0;
    const alt = scored
      .map((s) => s.activity)
      .find(
        (cand) =>
          !picked.some((p) => p.id === cand.id) &&
          cand.domains?.[0] &&
          !domainsUsed.has(cand.domains[0]) &&
          totalMin - lastDur + (cand.durationMinutes || 0) <= maxTotalMinutes,
      );
    if (alt) picked[picked.length - 1] = alt;
  }

  return picked.map((a) => a.id);
}

export { dailySeed };
