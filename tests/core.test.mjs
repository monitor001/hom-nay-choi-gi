import { ageMonths, toIsoDate } from "../src/age.mjs";
import { pickDaily, dailySeed } from "../src/picker.mjs";

function assert(cond, msg) {
  if (!cond) throw new Error(msg);
}

const birth = "2024-03-15";
const today = new Date(2026, 8, 24); // 24 Sep 2026
assert(ageMonths(birth, today) === 30, `ageMonths expected 30 got ${ageMonths(birth, today)}`);
assert(ageMonths("2026-09-24", today) === 0, "same day -> 0");
assert(ageMonths("2027-01-01", today) === 0, "future clamped");

const acts = [
  { id: "a1", ageMinMonths: 18, ageMaxMonths: 36, domains: ["LANGUAGE"], durationMinutes: 10 },
  { id: "a2", ageMinMonths: 18, ageMaxMonths: 36, domains: ["PHYSICAL"], durationMinutes: 10 },
  { id: "a3", ageMinMonths: 18, ageMaxMonths: 36, domains: ["COGNITIVE"], durationMinutes: 10 },
  { id: "a4", ageMinMonths: 40, ageMaxMonths: 48, domains: ["LANGUAGE"], durationMinutes: 10 },
];

const day = "2026-09-24";
const p1 = pickDaily({
  childId: "c1",
  ageMonths: 24,
  todayIso: day,
  activities: acts,
});
assert(p1.length === 3, `expect 3 picks got ${p1.length}`);
assert(!p1.includes("a4"), "out of age excluded");
assert(dailySeed("c1", day) === dailySeed("c1", day), "seed stable");

const p2 = pickDaily({
  childId: "c1",
  ageMonths: 24,
  todayIso: day,
  activities: acts,
});
assert(p1.join() === p2.join(), "same day same picks");

const p3 = pickDaily({
  childId: "c1",
  ageMonths: 24,
  todayIso: day,
  activities: acts,
  history14Days: [{ activityId: "a1", primaryDomain: "LANGUAGE", completedDate: "2026-09-20" }],
});
assert(!p3.includes("a1"), "recent history excluded");

console.log("PASS age + picker", { age: ageMonths(birth, today), picks: p1, today: toIsoDate(today) });
