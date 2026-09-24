import { earnOnComplete, redeemItem, gxBalance, GX_RATES } from "../src/gx.mjs";

function assert(cond, msg) {
  if (!cond) throw new Error(msg);
}

let ledger = [];
const day = "2026-09-24";
const r1 = earnOnComplete(ledger, "act1", day);
assert(r1.awarded === GX_RATES.COMPLETE, `expect +${GX_RATES.COMPLETE} got ${r1.awarded}`);
ledger = r1.ledger;
assert(gxBalance(ledger) === GX_RATES.COMPLETE, "balance after earn");

const r2 = earnOnComplete(ledger, "act1", day);
assert(r2.awarded === 0, "no double earn same activity");
assert(r2.message.includes("đã nhận"), `deny msg: ${r2.message}`);

const shopItem = { id: "mom_sticker", title: "Sticker", costGx: 10 };
const low = redeemItem(ledger, { ...shopItem, costGx: 999 });
assert(!low.ok, "redeem should fail if insufficient");

const ok = redeemItem(ledger, shopItem);
assert(ok.ok, "redeem ok");
assert(gxBalance(ok.ledger) === GX_RATES.COMPLETE - 10, "balance after redeem");

console.log("PASS gx earn + redeem");
