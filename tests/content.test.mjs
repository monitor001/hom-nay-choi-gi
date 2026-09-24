/**
 * Content QA for Gấu Con seed — schema, quota, age bands, safety gaps.
 * Run: node tests/content.test.mjs
 */
import { readFileSync } from "node:fs";
import { fileURLToPath } from "node:url";
import { dirname, join } from "node:path";
import { ageMonths } from "../src/age.mjs";
import { pickDaily } from "../src/picker.mjs";

const root = join(dirname(fileURLToPath(import.meta.url)), "..");
const raw = JSON.parse(readFileSync(join(root, "content", "activities.json"), "utf8"));

const errors = [];
const warnings = [];
const ok = (cond, msg) => {
  if (!cond) errors.push(msg);
};
const warn = (cond, msg) => {
  if (!cond) warnings.push(msg);
};

const REQUIRED = [
  "id",
  "title",
  "ageMinMonths",
  "ageMaxMonths",
  "domains",
  "durationMinutes",
  "materials",
  "goal",
  "steps",
  "parentPhrases",
  "reviewed_by",
];

const DOMAINS = new Set([
  "PHYSICAL",
  "COGNITIVE",
  "LANGUAGE",
  "SOCIAL_EMOTIONAL",
  "AESTHETIC",
  "SELF_CARE",
]);

/** Wave 5: allow 90–100 after controlled ADD; primary near CONSENSUS quota (±6). */
const QUOTA_TARGET = {
  PHYSICAL: 16,
  COGNITIVE: 14,
  LANGUAGE: 16,
  SOCIAL_EMOTIONAL: 14,
  AESTHETIC: 12,
  SELF_CARE: 18,
};
const QUOTA_SLACK = 6;

ok(raw.content_status === "draft_unreviewed", `content_status expected draft_unreviewed got ${raw.content_status}`);
ok(Array.isArray(raw.activities), "activities must be array");
ok(
  raw.activities.length >= 90 && raw.activities.length <= 100,
  `expect 90–100 activities got ${raw.activities.length}`,
);
const ids = new Set();
const primaryCount = Object.fromEntries([...DOMAINS].map((d) => [d, 0]));

for (const a of raw.activities) {
  for (const k of REQUIRED) {
    ok(a[k] !== undefined && a[k] !== null, `${a.id || "?"}: missing ${k}`);
  }
  ok(typeof a.id === "string" && a.id.length > 0, `empty id`);
  ok(!ids.has(a.id), `duplicate id ${a.id}`);
  ids.add(a.id);

  ok(typeof a.title === "string" && a.title.trim().length >= 3, `${a.id}: title too short`);
  ok(Number.isInteger(a.ageMinMonths) && Number.isInteger(a.ageMaxMonths), `${a.id}: age not int`);
  ok(a.ageMinMonths <= a.ageMaxMonths, `${a.id}: ageMin > ageMax`);
  ok(a.ageMinMonths >= 12 && a.ageMaxMonths <= 48, `${a.id}: age outside MVP-ish 12–48 (${a.ageMinMonths}-${a.ageMaxMonths})`);
  // Prefer overlap with 18–36
  warn(
    a.ageMaxMonths >= 18 && a.ageMinMonths <= 36,
    `${a.id}: no overlap with 18–36m`,
  );

  ok(Array.isArray(a.domains) && a.domains.length >= 1 && a.domains.length <= 3, `${a.id}: domains length`);
  for (const d of a.domains || []) ok(DOMAINS.has(d), `${a.id}: unknown domain ${d}`);
  const primary = a.domains?.[0];
  if (primary) primaryCount[primary] = (primaryCount[primary] || 0) + 1;

  ok(a.durationMinutes >= 5 && a.durationMinutes <= 15, `${a.id}: duration ${a.durationMinutes}`);
  ok(Array.isArray(a.materials), `${a.id}: materials`);
  ok(Array.isArray(a.steps) && a.steps.length >= 3, `${a.id}: steps < 3`);
  ok(Array.isArray(a.parentPhrases) && a.parentPhrases.length >= 1, `${a.id}: parentPhrases`);
  ok(Array.isArray(a.reviewed_by) && a.reviewed_by.length === 0, `${a.id}: reviewed_by must be [] for draft`);
  ok(a.isPremium === false || a.isPremium === undefined, `${a.id}: premium should be false`);

  warn(!!(a.safety && String(a.safety).trim()), `${a.id}: missing safety note`);
  warn(!!(a.easier && a.harder), `${a.id}: missing easier/harder`);

  // Tone: no guilt phrases in title/goal
  const blob = `${a.title} ${a.goal}`.toLowerCase();
  ok(!blob.includes("bỏ lỡ") && !blob.includes("chậm hơn"), `${a.id}: judgmental copy`);
}

for (const [d, n] of Object.entries(QUOTA_TARGET)) {
  const got = primaryCount[d] || 0;
  ok(
    Math.abs(got - n) <= QUOTA_SLACK,
    `quota ${d}: expect ~${n} (±${QUOTA_SLACK}) got ${got}`,
  );
}

// Picker smoke at 18, 24, 30, 36 months
for (const months of [18, 24, 30, 36]) {
  const birth = new Date(2026, 8, 24);
  birth.setMonth(birth.getMonth() - months);
  const y = birth.getFullYear();
  const m = String(birth.getMonth() + 1).padStart(2, "0");
  const d = String(birth.getDate()).padStart(2, "0");
  const age = ageMonths(`${y}-${m}-${d}`, new Date(2026, 8, 24));
  ok(Math.abs(age - months) <= 1, `ageMonths approx ${months} got ${age}`);

  const picks = pickDaily({
    childId: "qa_child",
    ageMonths: months,
    todayIso: "2026-09-24",
    activities: raw.activities,
  });
  ok(picks.length >= 2, `picker at ${months}m: expect ≥2 got ${picks.length}`);
  const acts = picks.map((id) => raw.activities.find((a) => a.id === id));
  const domains = new Set(acts.map((a) => a.domains?.[0]).filter(Boolean));
  warn(domains.size >= 2 || picks.length < 2, `picker at ${months}m: only ${domains.size} primary domain(s)`);
  const total = acts.reduce((s, a) => s + (a.durationMinutes || 0), 0);
  ok(total <= 30, `picker at ${months}m: total minutes ${total} > 30`);
}

console.log(
  JSON.stringify(
    {
      activities: raw.activities.length,
      primaryCount,
      errors: errors.length,
      warnings: warnings.length,
      errorSamples: errors.slice(0, 15),
      warningSamples: warnings.slice(0, 10),
    },
    null,
    2,
  ),
);

if (errors.length) {
  console.error(`FAIL content QA: ${errors.length} errors`);
  process.exit(1);
}
console.log("PASS content QA");
