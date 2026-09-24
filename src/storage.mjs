const KEY = "gaucon_web_v1";

const defaultState = () => ({
  onboardingDone: false,
  child: null, // { id, nickname, birthDate }
  logs: [], // { activityId, completedAt, feedback, note, primaryDomain }
  dailyPicks: {}, // { [dateIso]: string[] }
  fontScale: 1,
  disclaimerDismissed: false,
  milestoneObs: {},
  gxLedger: [], // { id, at, amount, kind, refId?, note? }
  rewardsEnabled: true,
  foodTreatVisible: false,
  lastEarnMessage: null,
  lastGxAwarded: 0,
});

export function loadState() {
  try {
    const raw = localStorage.getItem(KEY);
    if (!raw) return defaultState();
    return { ...defaultState(), ...JSON.parse(raw) };
  } catch {
    return defaultState();
  }
}

export function saveState(state) {
  localStorage.setItem(KEY, JSON.stringify(state));
}

export function resetState() {
  localStorage.removeItem(KEY);
  return defaultState();
}

export function newChildId() {
  return "child_" + Math.random().toString(36).slice(2, 10);
}
