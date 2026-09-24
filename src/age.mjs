/**
 * ageMonths — CONSENSUS §2.2 (portable to Android).
 * @param {string|Date} birthDate ISO yyyy-mm-dd or Date
 * @param {Date} [today]
 */
export function ageMonths(birthDate, today = new Date()) {
  const b = typeof birthDate === "string" ? parseIsoDate(birthDate) : birthDate;
  if (!b || Number.isNaN(b.getTime())) return 0;
  let months =
    (today.getFullYear() - b.getFullYear()) * 12 +
    (today.getMonth() - b.getMonth());
  if (today.getDate() < b.getDate()) months -= 1;
  return months < 0 ? 0 : months;
}

export function parseIsoDate(iso) {
  const [y, m, d] = iso.split("-").map(Number);
  if (!y || !m || !d) return null;
  return new Date(y, m - 1, d);
}

export function toIsoDate(date = new Date()) {
  const y = date.getFullYear();
  const m = String(date.getMonth() + 1).padStart(2, "0");
  const d = String(date.getDate()).padStart(2, "0");
  return `${y}-${m}-${d}`;
}

export function formatViDate(iso) {
  const d = parseIsoDate(iso);
  if (!d) return iso;
  return d.toLocaleDateString("vi-VN");
}
