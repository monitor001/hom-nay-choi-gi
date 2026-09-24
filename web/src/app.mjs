import { ageMonths, toIsoDate, formatViDate } from "./age.mjs";
import { pickDaily } from "./picker.mjs";
import { loadState, saveState, resetState, newChildId } from "./storage.mjs";
import { DOMAIN_LABELS, DOMAIN_ORDER, domainLabel, primaryDomain } from "./domains.mjs";
import { earnOnComplete, redeemItem, gxBalance, tierLabel } from "./gx.mjs";

const root = document.getElementById("app");
let state = loadState();
/** @type {{ version?: number, content_status?: string, activities: any[] }} */
let content = { activities: [] };
/** @type {{ items?: any[], categories?: any[], disclaimer?: string }} */
let resources = { items: [], categories: [] };
/** @type {{ links?: Record<string, string[]> }} */
let resourceLinks = { links: {} };
/** @type {{ tiers?: any[], items?: any[] }} */
let shop = { tiers: [], items: [] };
let route = { name: "boot" };
let libraryFilter = "ALL";
let libraryShowAll = false;
let resourceFilter = "ALL";
let completeDraft = { feedback: null, note: "" };
let shopMessage = null;

/** Stub milestone anchors for Progress (UI-P0-04) — observation only, not diagnosis. */
const MILESTONE_STUBS = [
  { id: "m_walk", label: "Đi vững vài bước có người cạnh", band: "18–24 tháng" },
  { id: "m_point", label: "Chỉ đồ để khoe / xin", band: "18–24 tháng" },
  { id: "m_words", label: "Dùng vài từ hoặc cử chỉ quen", band: "18–24 tháng" },
  { id: "m_stack", label: "Chồng 2–3 khối rồi đổ vui", band: "18–30 tháng" },
  { id: "m_pretend", label: "Giả vờ đơn giản (đút gấu, đẩy xe)", band: "24–30 tháng" },
  { id: "m_two_step", label: "Nghe lời mời hai việc ngắn khi vui", band: "24–36 tháng" },
  { id: "m_spoon", label: "Thử thìa / tự ăn một phần", band: "18–30 tháng" },
  { id: "m_share_try", label: "Thử đưa đồ khi được xin (chưa bắt buộc)", band: "18–30 tháng" },
  { id: "m_color", label: "Chơi gọi tên một màu cùng người lớn", band: "28–36 tháng" },
  { id: "m_jump", label: "Nhảy nhẹ tại chỗ nếu thích", band: "28–36 tháng" },
];

function milestoneStatus(stubId) {
  const map = state.milestoneObs || {};
  return map[stubId] || "not_yet";
}

function completedIdsToday() {
  const today = toIsoDate();
  return new Set(
    (state.logs || []).filter((l) => l.completedAt.startsWith(today)).map((l) => l.activityId),
  );
}

document.documentElement.style.setProperty("--font-scale", String(state.fontScale || 1));

async function boot() {
  const [actRes, catRes, linkRes, shopRes] = await Promise.all([
    fetch("./content/activities.json"),
    fetch("./content/resources-catalog.json"),
    fetch("./content/resources-links.json"),
    fetch("./content/shop-catalog.json"),
  ]);
  content = await actRes.json();
  resources = await catRes.json();
  resourceLinks = await linkRes.json();
  shop = await shopRes.json();
  if (!state.onboardingDone || !state.child) {
    route = { name: "welcome" };
  } else {
    route = { name: "today" };
  }
  render();
}

function persist() {
  saveState(state);
  document.documentElement.style.setProperty("--font-scale", String(state.fontScale || 1));
}

function history14() {
  const today = toIsoDate();
  const cutoff = Date.now() - 14 * 86400000;
  return (state.logs || [])
    .filter((l) => new Date(l.completedAt).getTime() >= cutoff)
    .map((l) => ({
      activityId: l.activityId,
      primaryDomain: l.primaryDomain,
      completedDate: l.completedAt.slice(0, 10),
      feedback: l.feedback,
    }));
}

function ensureTodayPicks({ forceSwap = false, excludeId = null } = {}) {
  const child = state.child;
  if (!child) return [];
  const today = toIsoDate();
  const age = ageMonths(child.birthDate);
  let ids = state.dailyPicks?.[today];

  if (!ids || forceSwap) {
    const excludeExtra = forceSwap
      ? [...(ids || []), ...(excludeId ? [excludeId] : [])]
      : [];
    ids = pickDaily({
      childId: child.id,
      ageMonths: age,
      todayIso: today,
      activities: content.activities,
      history14Days: history14(),
      excludeExtraIds: excludeExtra,
    });
    state.dailyPicks = { ...state.dailyPicks, [today]: ids };
    persist();
  }
  return ids.map((id) => content.activities.find((a) => a.id === id)).filter(Boolean);
}

function completedTodayCount() {
  const today = toIsoDate();
  return (state.logs || []).filter((l) => l.completedAt.startsWith(today)).length;
}

function navHtml(active) {
  const items = [
    { id: "today", icon: "☀️", label: "Hôm nay" },
    { id: "shop", icon: "🏪", label: "Cửa hàng" },
    { id: "resources", icon: "🎨", label: "Tài liệu" },
    { id: "library", icon: "📚", label: "Hoạt động" },
    { id: "more", icon: "⋯", label: "Thêm" },
  ];
  return `<nav class="nav" aria-label="Điều hướng chính">
    ${items
      .map(
        (i) => `<button type="button" data-nav="${i.id}" ${active === i.id ? 'aria-current="page"' : ""}>
      <span aria-hidden="true">${i.icon}</span>${i.label}
    </button>`,
      )
      .join("")}
  </nav>`;
}

function gxChipHtml() {
  if (!state.rewardsEnabled) return "";
  const bal = gxBalance(state.gxLedger);
  return `<button type="button" class="gx-chip" data-nav="shop" title="Cửa hàng Gấu Xu">
    <img src="./assets/shop/ic_gau_xu.png" alt="" width="28" height="28" />
    <strong>${bal}</strong><span>GX</span>
  </button>`;
}

function openLightbox(src, title = "") {
  let el = document.getElementById("lightbox");
  if (!el) {
    el = document.createElement("div");
    el.id = "lightbox";
    el.setAttribute("role", "dialog");
    el.setAttribute("aria-modal", "true");
    document.body.appendChild(el);
    el.addEventListener("click", (ev) => {
      if (ev.target === el || ev.target.closest("[data-lightbox-close]")) closeLightbox();
    });
  }
  el.className = "lightbox open";
  el.innerHTML = `
    <div class="lightbox-bar">
      ${title ? `<p class="lightbox-title">${escapeHtml(title)}</p>` : ""}
      <button type="button" class="btn btn-primary lightbox-close" data-lightbox-close>Đóng tranh</button>
    </div>
    <img class="lightbox-img" src="${escapeAttr(src)}" alt="${escapeAttr(title)}" />
    <p class="lightbox-hint">Phóng to bằng cử chỉ trình duyệt · chạm ngoài ảnh để đóng</p>`;
  document.body.style.overflow = "hidden";
}

function closeLightbox() {
  const el = document.getElementById("lightbox");
  if (el) {
    el.classList.remove("open");
    el.innerHTML = "";
  }
  document.body.style.overflow = "";
}

function resourceById(id) {
  return (resources.items || []).find((x) => x.id === id);
}

function linkedResourceCards(activityId) {
  const ids = resourceLinks.links?.[activityId] || [];
  const items = ids.map(resourceById).filter(Boolean);
  if (!items.length) return "";
  return `<div class="card">
    <h3 style="font-size:1rem;margin:0 0 0.5rem">Tranh / tài liệu cho bé</h3>
    <p class="muted" style="margin-top:0">Chạm tranh để xem toàn màn hình.</p>
    <div class="stack" style="margin-top:0.65rem">
      ${items
        .map((r) => {
          const img = r.image
            ? `<button type="button" class="resource-art-btn" data-action="zoom-image" data-src="./content/resources/${escapeAttr(r.image)}" data-title="${escapeAttr(r.title)}">
                <img src="./content/resources/${escapeAttr(r.image)}" alt="${escapeAttr(r.title)}" loading="lazy" />
                <span class="zoom-badge">Toàn màn hình</span>
              </button>`
            : "";
          return `<div class="card list-item linked-res" style="margin:0;box-shadow:none">
        ${img}
        <strong>${escapeHtml(r.title)}</strong>
        <span class="chip">${escapeHtml((resources.categories || []).find((c) => c.id === r.category)?.label || r.category)}</span>
        <button type="button" class="btn btn-secondary btn-block" data-action="open-resource" data-id="${escapeAttr(r.id)}" style="margin-top:0.5rem">Mở trang tài liệu</button>
      </div>`;
        })
        .join("")}
    </div>
  </div>`;
}

function topbar(subtitle = "") {
  return `<header class="topbar">
    <a class="brand" href="#today" data-nav="today">
      <img class="brand-logo" src="./assets/logo.png" alt="" width="40" height="40" />
      <span class="brand-text"><strong>Hôm nay chơi gì?</strong><small>Web pilot</small></span>
    </a>
    <div class="topbar-end">
      ${gxChipHtml()}
      ${subtitle ? `<span class="pill">${subtitle}</span>` : ""}
    </div>
  </header>`;
}

function disclaimer() {
  if (state.disclaimerDismissed) return "";
  return `<div class="banner" role="status">
    Nội dung nháp — <strong>chưa chuyên gia duyệt</strong>. Không chẩn đoán y tế. Mỗi bé một nhịp riêng.
    <button type="button" data-action="dismiss-disclaimer">Đã hiểu</button>
  </div>`;
}

function renderWelcome() {
  root.innerHTML = `
    ${topbar()}
    <section class="hero-panel">
      <p class="eyebrow">Dành cho phụ huynh · 18–36 tháng</p>
      <div class="hero-brand">
        <img src="./assets/logo.png" alt="Hôm nay chơi gì?" width="120" height="120" class="hero-logo" />
      </div>
      <h1>Hôm nay chơi gì?</h1>
      <p class="lede">Gợi ý 2–3 hoạt động ngắn (5–15 phút), dùng đồ có sẵn trong nhà — chơi cùng con ngoài đời thật, không phải qua màn hình.</p>
      <div class="btn-row">
        <button class="btn btn-primary" type="button" data-action="go-profile">Bắt đầu</button>
      </div>
      <p class="muted" style="margin-top:1.25rem">Tham khảo hướng giáo dục mầm non — nội dung nháp. Prototype web để kiểm thử trước khi làm app; chưa chuyên gia duyệt.</p>
    </section>`;
}

function renderProfile() {
  const c = state.child || {};
  root.innerHTML = `
    ${topbar("Hồ sơ bé")}
    <section class="stack">
      <div class="card">
        <h2>Tạo hồ sơ bé</h2>
        <p class="muted">Chỉ lưu trên máy bạn (localStorage). Không gửi lên máy chủ.</p>
        <form class="stack" id="profileForm" style="margin-top:1rem">
          <label class="field">Tên gọi
            <input name="nickname" required maxlength="40" value="${escapeAttr(c.nickname || "")}" placeholder="Ví dụ: Bé Na" />
          </label>
          <label class="field">Ngày sinh
            <input name="birthDate" type="date" required value="${escapeAttr(c.birthDate || "")}" />
          </label>
          <button class="btn btn-primary btn-block" type="submit">Lưu và xem hôm nay</button>
        </form>
      </div>
      <button class="btn btn-ghost" type="button" data-action="go-welcome">← Quay lại</button>
    </section>`;
}

function renderToday() {
  const child = state.child;
  const age = ageMonths(child.birthDate);
  const picks = ensureTodayPicks();
  const done = completedTodayCount();
  const doneIds = completedIdsToday();
  const pending = picks.filter((a) => !doneIds.has(a.id));
  const played = picks.filter((a) => doneIds.has(a.id));

  const cardHtml = (a, { playedToday = false } = {}) => `<article class="card${playedToday ? " card-done" : ""}">
          <h3>${escapeHtml(a.title)}${playedToday ? ` <span class="chip done-chip">Đã chơi hôm nay</span>` : ""}</h3>
          <div class="meta">
            ${(a.domains || []).map((d) => `<span class="chip domain">${domainLabel(d)}</span>`).join("")}
            <span class="chip">${a.durationMinutes} phút</span>
          </div>
          <p class="muted">${escapeHtml(a.goal || "")}</p>
          <div class="btn-row">
            <button class="btn btn-primary" type="button" data-action="open-activity" data-id="${escapeAttr(a.id)}">${playedToday ? "Xem lại" : "Xem chi tiết"}</button>
            ${playedToday ? "" : `<button class="btn btn-secondary" type="button" data-action="swap-activity" data-id="${escapeAttr(a.id)}">Đổi gợi ý</button>`}
          </div>
        </article>`;

  root.innerHTML = `
    ${topbar(`${age} tháng`)}
    ${disclaimer()}
    <section class="stack">
      <div>
        <p class="eyebrow">Hôm nay</p>
        <h1 style="font-family:var(--display);font-size:1.6rem;margin:0.2rem 0 0.4rem">Chào bạn — chơi cùng ${escapeHtml(child.nickname)} nhé?</h1>
        <p class="muted">Đã chơi ${done} hoạt động hôm nay. Mỗi bé một nhịp riêng.</p>
        ${
          state.rewardsEnabled
            ? `<p class="gx-today">Số dư <strong>${gxBalance(state.gxLedger)} GX</strong> — hoàn thành hoạt động để nhận Gấu Xu, đổi quà ở Cửa hàng.</p>`
            : ""
        }
      </div>
      ${
        picks.length
          ? `${pending.length ? pending.map((a) => cardHtml(a)).join("") : `<div class="card empty"><p>Hôm nay đã chơi hết gợi ý — giỏi quá! Có thể xem lại bên dưới hoặc mở Thư viện.</p></div>`}
             ${played.length ? `<p class="eyebrow" style="margin-top:0.5rem">Đã chơi hôm nay</p>${played.map((a) => cardHtml(a, { playedToday: true })).join("")}` : ""}`
          : `<div class="card empty"><p>Chưa có hoạt động phù hợp tuổi ${age} tháng trong seed. Thử xem Thư viện hoặc kiểm tra ngày sinh.</p></div>`
      }
    </section>
    ${navHtml("today")}`;
}

function renderActivity(id) {
  const a = content.activities.find((x) => x.id === id);
  if (!a) {
    route = { name: "today" };
    return render();
  }
  root.innerHTML = `
    ${topbar("Chi tiết")}
    <section class="stack">
      <button class="btn btn-ghost" type="button" data-action="go-today">← Hôm nay</button>
      <article class="card">
        <h2>${escapeHtml(a.title)}</h2>
        <div class="meta">
          ${(a.domains || []).map((d) => `<span class="chip domain">${domainLabel(d)}</span>`).join("")}
          <span class="chip">${a.durationMinutes} phút</span>
          <span class="chip">${a.ageMinMonths}–${a.ageMaxMonths} tháng</span>
        </div>
        <p><strong>Mục tiêu:</strong> ${escapeHtml(a.goal || "")}</p>
        <p class="muted"><strong>Đồ dùng:</strong> ${(a.materials || []).map(escapeHtml).join(", ") || "Không bắt buộc"}</p>
        <h3 style="margin-top:1rem;font-size:1rem">Các bước</h3>
        <ol class="steps">${(a.steps || []).map((s) => `<li>${escapeHtml(s)}</li>`).join("")}</ol>
        ${
          a.parentPhrases?.length
            ? `<h3 style="margin-top:1rem;font-size:1rem">Câu nói mẫu</h3><div class="phrases">${a.parentPhrases.map((p) => `<span class="phrase">${escapeHtml(p)}</span>`).join("")}</div>`
            : ""
        }
        ${a.easier ? `<p class="muted" style="margin-top:0.85rem"><strong>Dễ hơn:</strong> ${escapeHtml(a.easier)}</p>` : ""}
        ${a.harder ? `<p class="muted"><strong>Khó hơn:</strong> ${escapeHtml(a.harder)}</p>` : ""}
        ${a.safety ? `<div class="safety" role="note"><strong>An toàn:</strong> ${escapeHtml(a.safety)}</div>` : ""}
      </article>
      ${linkedResourceCards(a.id)}
      <div class="cta-sticky" role="region" aria-label="Hành động">
        <button class="btn btn-primary btn-block" type="button" data-action="go-complete" data-id="${escapeAttr(a.id)}">${
          state.rewardsEnabled ? "Hoàn thành · nhận Gấu Xu" : "Đánh dấu hoàn thành"
        }</button>
      </div>
    </section>
    ${navHtml("today")}`;
}

function renderComplete(id) {
  const a = content.activities.find((x) => x.id === id);
  if (!a) {
    route = { name: "today" };
    return render();
  }
  const fb = completeDraft.feedback;
  const justEarned = route.celebrate;
  if (justEarned) {
    root.innerHTML = `
      ${topbar("Đã chơi xong")}
      <section class="stack celebrate">
        <div class="card celebrate-card">
          <img class="gx-big" src="./assets/shop/ic_gau_xu.png" alt="Gấu Xu" width="96" height="96" />
          <h2>Đã chơi xong!</h2>
          <p>${escapeHtml(state.lastEarnMessage || "Đã ghi nhận buổi chơi.")}</p>
          ${
            state.lastGxAwarded > 0
              ? `<p class="gx-balance-line">Số dư: <strong>${gxBalance(state.gxLedger)} GX</strong></p>`
              : ""
          }
          <div class="btn-row" style="margin-top:1.25rem;flex-direction:column">
            <button class="btn btn-primary btn-block" type="button" data-action="go-today">Về Hôm nay</button>
            <button class="btn btn-secondary btn-block" type="button" data-nav="shop">Mở cửa hàng đổi thưởng</button>
          </div>
        </div>
      </section>
      ${navHtml("today")}`;
    return;
  }
  root.innerHTML = `
    ${topbar("Phản hồi")}
    <section class="stack">
      <div class="card">
        <h2>Hôm nay với “${escapeHtml(a.title)}”</h2>
        <p class="muted">Chưa hợp cũng không sao — giúp app gợi ý hợp hơn lần sau.</p>
        <div class="feedback-row" style="margin-top:1rem" role="group" aria-label="Mức độ hợp">
          <button type="button" data-action="set-feedback" data-v="LIKED" aria-pressed="${fb === "LIKED"}">😊 Bé thích</button>
          <button type="button" data-action="set-feedback" data-v="NEUTRAL" aria-pressed="${fb === "NEUTRAL"}">😐 Bình thường</button>
          <button type="button" data-action="set-feedback" data-v="NOT_FIT" aria-pressed="${fb === "NOT_FIT"}">😕 Chưa hợp</button>
        </div>
        <label class="field" style="margin-top:1rem">Ghi chú (tuỳ chọn)
          <textarea data-action="note">${escapeHtml(completeDraft.note || "")}</textarea>
        </label>
        <div class="btn-row">
          <button class="btn btn-primary btn-block" type="button" data-action="save-complete" data-id="${escapeAttr(a.id)}" ${fb ? "" : "disabled"}>Lưu</button>
          <button class="btn btn-ghost" type="button" data-action="open-activity" data-id="${escapeAttr(a.id)}">Quay lại</button>
        </div>
      </div>
    </section>
    ${navHtml("today")}`;
}

function renderLibrary() {
  const age = state.child ? ageMonths(state.child.birthDate) : null;
  let list = content.activities.slice();
  if (libraryFilter !== "ALL") list = list.filter((a) => (a.domains || []).includes(libraryFilter));
  if (age != null) {
    list = [...list].sort((a, b) => {
      const af = age >= a.ageMinMonths && age <= a.ageMaxMonths ? 0 : 1;
      const bf = age >= b.ageMinMonths && age <= b.ageMaxMonths ? 0 : 1;
      return af - bf || a.title.localeCompare(b.title, "vi");
    });
  }

  const CAP = 60;
  const shown = libraryShowAll ? list : list.slice(0, CAP);
  const hasMore = !libraryShowAll && list.length > CAP;

  root.innerHTML = `
    ${topbar("Thư viện")}
    ${disclaimer()}
    <section>
      <p class="eyebrow">Kho hoạt động</p>
      <h1 style="font-family:var(--display);font-size:1.5rem;margin:0.2rem 0 0.75rem">${content.activities.length} gợi ý</h1>
      <div class="filters" role="toolbar" aria-label="Lọc lĩnh vực">
        <button type="button" data-filter="ALL" aria-pressed="${libraryFilter === "ALL"}">Tất cả</button>
        ${DOMAIN_ORDER.map(
          (d) =>
            `<button type="button" data-filter="${d}" aria-pressed="${libraryFilter === d}">${DOMAIN_LABELS[d]}</button>`,
        ).join("")}
      </div>
      <div class="stack">
        ${
          list.length === 0
            ? `<div class="card empty"><p>Không có hoạt động khớp bộ lọc này. Chọn “Tất cả” hoặc lĩnh vực khác.</p></div>`
            : shown
                .map((a) => {
                  const fit = age != null && age >= a.ageMinMonths && age <= a.ageMaxMonths;
                  return `<button type="button" class="card list-item" data-action="open-activity" data-id="${escapeAttr(a.id)}">
              <h3>${escapeHtml(a.title)}</h3>
              <div class="meta">
                ${(a.domains || []).slice(0, 2).map((d) => `<span class="chip domain">${domainLabel(d)}</span>`).join("")}
                <span class="chip">${a.durationMinutes} phút</span>
                ${fit ? `<span class="chip">Phù hợp tuổi</span>` : `<span class="chip">${a.ageMinMonths}–${a.ageMaxMonths} tháng</span>`}
              </div>
            </button>`;
                })
                .join("")
        }
      </div>
      ${
        hasMore
          ? `<div class="btn-row" style="justify-content:center"><button class="btn btn-secondary" type="button" data-action="library-more">Xem thêm (${list.length - CAP} còn lại)</button></div>
             <p class="muted" style="text-align:center">Đang hiện ${CAP} / ${list.length}.</p>`
          : list.length > CAP
            ? `<p class="muted" style="text-align:center">Đang hiện tất cả ${list.length} hoạt động.</p>`
            : ""
      }
    </section>
    ${navHtml("library")}`;
}

function renderProgress() {
  const child = state.child;
  const age = ageMonths(child.birthDate);
  const weekAgo = Date.now() - 7 * 86400000;
  const weekLogs = (state.logs || []).filter((l) => new Date(l.completedAt).getTime() >= weekAgo);
  const byDomain = {};
  for (const l of weekLogs) {
    const d = l.primaryDomain || "OTHER";
    byDomain[d] = (byDomain[d] || 0) + 1;
  }

  const statusLabel = { not_yet: "Chưa thấy", emerging: "Đang lộ", often: "Thường thấy" };

  root.innerHTML = `
    ${topbar("Phát triển")}
    ${disclaimer()}
    <div class="banner banner-sticky" role="status">
      Mỗi bé một nhịp riêng — đây là gợi ý quan sát, <strong>không chẩn đoán</strong>. Nếu lo lắng, hãy hỏi bác sĩ.
    </div>
    <section class="stack">
      <div class="card">
        <h2>${escapeHtml(child.nickname)} · ${age} tháng</h2>
        <p class="muted">Sinh ${formatViDate(child.birthDate)}. Quan sát nhẹ — không xếp hạng, không so sánh với trẻ khác.</p>
        <p class="muted ia-note">Nhật ký đầy đủ &amp; S03 chưa có trên web — có trên Android / sắp có.</p>
      </div>
      <div class="card">
        <h3>Tuần này</h3>
        <p style="font-size:1.6rem;font-family:var(--display);margin:0.4rem 0">${weekLogs.length} hoạt động</p>
        <p class="muted">Gợi ý nhẹ: chơi vài lần trong tuần khi cả nhà vui — không phải chỉ tiêu phải đạt.</p>
        <div class="meta" style="margin-top:0.75rem">
          ${DOMAIN_ORDER.filter((d) => byDomain[d])
            .map((d) => `<span class="chip domain">${domainLabel(d)}: ${byDomain[d]}</span>`)
            .join("") || `<span class="chip">Chưa có dữ liệu tuần này</span>`}
        </div>
      </div>
      <div class="card">
        <h3>Mốc quan sát (stub)</h3>
        <p class="muted">10 mốc neo — ghi nhận của bạn trên máy này. Không percentile, không so bạn bè.</p>
        <ul class="milestone-list">
          ${MILESTONE_STUBS.map((m) => {
            const st = milestoneStatus(m.id);
            return `<li>
              <div class="milestone-head"><strong>${escapeHtml(m.label)}</strong><span class="chip">${escapeHtml(m.band)}</span></div>
              <div class="milestone-actions" role="group" aria-label="${escapeAttr(m.label)}">
                ${["not_yet", "emerging", "often"]
                  .map(
                    (s) =>
                      `<button type="button" data-action="set-milestone" data-mid="${escapeAttr(m.id)}" data-st="${s}" aria-pressed="${st === s}">${statusLabel[s]}</button>`,
                  )
                  .join("")}
              </div>
            </li>`;
          }).join("")}
        </ul>
      </div>
    </section>
    ${navHtml("progress")}`;
}

function renderMore() {
  root.innerHTML = `
    ${topbar("Thêm")}
    <section class="stack">
      <div class="card">
        <h2>Cài đặt</h2>
        <label class="field">Cỡ chữ
          <select data-action="font-scale">
            <option value="1" ${state.fontScale === 1 ? "selected" : ""}>Vừa</option>
            <option value="1.15" ${state.fontScale === 1.15 ? "selected" : ""}>Lớn</option>
            <option value="1.3" ${state.fontScale === 1.3 ? "selected" : ""}>Rất lớn</option>
          </select>
        </label>
      </div>
      <div class="card">
        <h3>Gấu Xu &amp; cửa hàng</h3>
        <p class="muted">Hoàn thành hoạt động để nhận GX · đổi quà do phụ huynh chuẩn bị. Dữ liệu chỉ trên máy.</p>
        <button class="btn btn-secondary btn-block" type="button" data-nav="shop">Mở cửa hàng</button>
      </div>
      <div class="card">
        <h3>Phát triển</h3>
        <button class="btn btn-secondary btn-block" type="button" data-nav="progress">Xem mốc quan sát</button>
      </div>
      <div class="card">
        <h3>Về bản web này</h3>
        <p class="muted">Pilot kiểm thử trước / song song Android. Seed: ${content.activities.length} hoạt động · trạng thái <code>${escapeHtml(content.content_status || "draft")}</code>.</p>
      </div>
      <div class="card">
        <h3>Quyền riêng tư</h3>
        <p class="muted">Dữ liệu chỉ trên trình duyệt. Không đăng nhập, không đồng bộ cloud.</p>
        <button class="btn btn-secondary" type="button" data-action="reset-data">Xóa toàn bộ dữ liệu cục bộ</button>
      </div>
    </section>
    ${navHtml("more")}`;
}

function renderResources() {
  const cats = resources.categories || [];
  let items = resources.items || [];
  if (resourceFilter !== "ALL") items = items.filter((i) => i.category === resourceFilter);
  root.innerHTML = `
    ${topbar("Tài liệu")}
    ${disclaimer()}
    <section>
      <p class="eyebrow">Đồng dao · hát ru · ~100 tranh kể chuyện</p>
      <h1 style="font-family:var(--display);font-size:1.5rem;margin:0.2rem 0 0.5rem">Tài liệu cho phụ huynh</h1>
      <p class="muted">Đồng dao/hát ru đã rà F1–F5 · thư viện tranh kể chuyện (~100, văn hóa VN, đã thẩm định danh mục) · bài nhạc sĩ chỉ hướng tìm. Nháp.</p>
      <div class="filters" role="toolbar" aria-label="Loại tài liệu" style="margin-top:0.85rem">
        <button type="button" data-res-filter="ALL" aria-pressed="${resourceFilter === "ALL"}">Tất cả</button>
        ${cats
          .map(
            (c) =>
              `<button type="button" data-res-filter="${escapeAttr(c.id)}" aria-pressed="${resourceFilter === c.id}">${escapeHtml(c.label)}</button>`,
          )
          .join("")}
      </div>
      <div class="stack" style="margin-top:0.75rem">
        ${items
          .map((r) => {
            const cat = cats.find((c) => c.id === r.category)?.label || r.category;
            const thumb = r.image
              ? `<img class="res-thumb" src="./content/resources/${escapeAttr(r.image)}" alt="" width="72" height="72" />`
              : "";
            return `<button type="button" class="card list-item res-row" data-action="open-resource" data-id="${escapeAttr(r.id)}">
              ${thumb}
              <span class="res-row-text">
                <h3>${escapeHtml(r.title)}</h3>
                <div class="meta">
                  <span class="chip domain">${escapeHtml(cat)}</span>
                  ${r.region ? `<span class="chip">${escapeHtml(r.region)}</span>` : ""}
                  ${r.ageHint ? `<span class="chip">${escapeHtml(r.ageHint)}</span>` : ""}
                </div>
                <p class="muted" style="margin:0.35rem 0 0">${escapeHtml((r.howToUse || r.storyPrompt || "").slice(0, 90))}${((r.howToUse || r.storyPrompt || "").length > 90) ? "…" : ""}</p>
              </span>
            </button>`;
          })
          .join("")}
      </div>
      <p class="muted" style="margin-top:1rem">${escapeHtml(resources.disclaimer || "")}</p>
    </section>
    ${navHtml("resources")}`;
}

function renderResourceDetail(id) {
  const r = resourceById(id);
  if (!r) {
    route = { name: "resources" };
    return render();
  }
  const cat = (resources.categories || []).find((c) => c.id === r.category)?.label || r.category;
  const imgSrc = r.image ? `./content/resources/${escapeAttr(r.image)}` : "";
  const img = r.image
    ? `<button type="button" class="resource-art-btn hero" data-action="zoom-image" data-src="${imgSrc}" data-title="${escapeAttr(r.title)}">
         <img src="${imgSrc}" alt="${escapeAttr(r.title)}" loading="lazy" />
         <span class="zoom-badge">Chạm để xem toàn màn hình cho bé</span>
       </button>
       <button type="button" class="btn btn-primary btn-block" data-action="zoom-image" data-src="${imgSrc}" data-title="${escapeAttr(r.title)}" style="margin-top:0.65rem">Xem tranh toàn màn hình</button>`
    : "";
  const story = r.storyPrompt
    ? `<div class="card" style="margin-top:0.75rem;box-shadow:none;background:rgba(196,107,44,0.08)">
        <h3 style="font-size:1rem;margin:0 0 0.35rem">Gợi ý kể chuyện từ tranh</h3>
        <p style="margin:0">${escapeHtml(r.storyPrompt)}</p>
      </div>`
    : "";
  root.innerHTML = `
    ${topbar("Tài liệu")}
    <section class="stack">
      <button class="btn btn-ghost" type="button" data-action="go-resources">← Tài liệu</button>
      <article class="card">
        <div class="meta">
          <span class="chip domain">${escapeHtml(cat)}</span>
          ${r.region ? `<span class="chip">${escapeHtml(r.region)}</span>` : ""}
          ${r.ageHint ? `<span class="chip">${escapeHtml(r.ageHint)}</span>` : ""}
        </div>
        <h2>${escapeHtml(r.title)}</h2>
        <p class="muted"><strong>Nguồn:</strong> ${escapeHtml(r.source || "")}</p>
        ${r.howToUse ? `<p><strong>Cách dùng:</strong> ${escapeHtml(r.howToUse)}</p>` : ""}
        ${img}
        ${story}
        <pre class="resource-body">${escapeHtml(r.body || "")}</pre>
      </article>
    </section>
    ${navHtml("resources")}`;
}

function renderShop() {
  const bal = gxBalance(state.gxLedger);
  const foodOn = !!state.foodTreatVisible;
  const enabled = state.rewardsEnabled !== false;
  const items = (shop.items || []).filter((i) => i.tier !== "FOOD_TREAT" || foodOn);
  const tiers = shop.tiers || [];
  let lastTier = null;
  const gridParts = [];
  for (const item of items) {
    if (item.tier !== lastTier) {
      lastTier = item.tier;
      gridParts.push(`<h3 class="shop-tier">${escapeHtml(tierLabel(item.tier, tiers))}</h3>`);
      if (item.tier === "FOOD_TREAT") {
        gridParts.push(`<p class="muted shop-caution">PH quyết định — không khuyến khích ép ăn.</p>`);
      }
    }
    const can = enabled && bal >= item.costGx;
    gridParts.push(`<article class="shop-tile">
      <div class="shop-tile-art"><img src="./assets/shop/${escapeAttr(item.image)}" alt="" loading="lazy" /></div>
      <h4>${escapeHtml(item.title)}</h4>
      <div class="shop-price"><img src="./assets/shop/ic_gau_xu.png" alt="" width="18" height="18" /><strong>${item.costGx}</strong></div>
      <button type="button" class="btn ${can ? "btn-primary" : "btn-secondary"} btn-block" data-action="redeem" data-id="${escapeAttr(item.id)}" ${can ? "" : "disabled"}>
        ${can ? "Đổi" : "Chưa đủ"}
      </button>
    </article>`);
  }
  const recent = [...(state.gxLedger || [])].slice(-8).reverse();
  root.innerHTML = `
    ${topbar("Cửa hàng")}
    <section class="shop-page">
      <p class="eyebrow">Cửa hàng</p>
      <h1 style="font-family:var(--display);font-size:1.55rem;margin:0.15rem 0 0.35rem">Đổi Gấu Xu</h1>
      <p class="muted">Chọn ô quà — phụ huynh chuẩn bị khi đổi. Không ép bé bằng đồ ăn.</p>
      <div class="shop-settings card">
        <label class="switch-row">Bật sổ thưởng
          <input type="checkbox" data-action="toggle-rewards" ${enabled ? "checked" : ""} />
        </label>
        <label class="switch-row">Hiện đồ ăn/uống
          <input type="checkbox" data-action="toggle-food" ${foodOn ? "checked" : ""} />
        </label>
      </div>
      ${shopMessage ? `<p class="shop-msg">${escapeHtml(shopMessage)}</p>` : ""}
      <div class="shop-grid">${gridParts.join("")}</div>
      ${
        recent.length
          ? `<h3 style="margin-top:1.25rem">Gần đây</h3>
             <ul class="gx-recent">${recent
               .map(
                 (e) =>
                   `<li>${e.amount >= 0 ? "+" : ""}${e.amount} · ${escapeHtml(e.note || e.kind)}</li>`,
               )
               .join("")}</ul>`
          : ""
      }
    </section>
    ${navHtml("shop")}`;
}

function render() {
  switch (route.name) {
    case "welcome":
      return renderWelcome();
    case "profile":
      return renderProfile();
    case "today":
      return renderToday();
    case "activity":
      return renderActivity(route.id);
    case "complete":
      return renderComplete(route.id);
    case "library":
      return renderLibrary();
    case "resources":
      return renderResources();
    case "resource":
      return renderResourceDetail(route.id);
    case "shop":
      return renderShop();
    case "progress":
      return renderProgress();
    case "more":
      return renderMore();
    default:
      route = { name: "welcome" };
      return renderWelcome();
  }
}

function escapeHtml(s) {
  return String(s ?? "")
    .replace(/&/g, "&amp;")
    .replace(/</g, "&lt;")
    .replace(/>/g, "&gt;")
    .replace(/"/g, "&quot;");
}
function escapeAttr(s) {
  return escapeHtml(s).replace(/'/g, "&#39;");
}

root.addEventListener("click", (e) => {
  const t = e.target.closest("[data-action],[data-nav],[data-filter],[data-res-filter]");
  if (!t) return;

  if (t.dataset.nav) {
    e.preventDefault();
    const n = t.dataset.nav;
    if (n === "today") route = { name: "today" };
    else if (n === "library") route = { name: "library" };
    else if (n === "resources") route = { name: "resources" };
    else if (n === "shop") {
      shopMessage = null;
      route = { name: "shop" };
    }
    else if (n === "progress") route = { name: "progress" };
    else if (n === "more") route = { name: "more" };
    render();
    return;
  }

  if (t.dataset.resFilter) {
    resourceFilter = t.dataset.resFilter;
    render();
    return;
  }

  if (t.dataset.filter) {
    libraryFilter = t.dataset.filter;
    libraryShowAll = false;
    render();
    return;
  }

  const action = t.dataset.action;
  if (action === "zoom-image") {
    openLightbox(t.dataset.src, t.dataset.title || "");
    return;
  }
  if (action === "redeem") {
    const item = (shop.items || []).find((i) => i.id === t.dataset.id);
    if (!item) return;
    const result = redeemItem(state.gxLedger || [], item);
    state.gxLedger = result.ledger;
    shopMessage = result.message;
    persist();
    render();
    return;
  }
  if (action === "library-more") {
    libraryShowAll = true;
    render();
    return;
  }
  if (action === "open-resource") {
    route = { name: "resource", id: t.dataset.id };
    render();
    return;
  }
  if (action === "go-resources") {
    route = { name: "resources" };
    render();
    return;
  }
  if (action === "set-milestone") {
    state.milestoneObs = { ...(state.milestoneObs || {}), [t.dataset.mid]: t.dataset.st };
    persist();
    render();
    return;
  }
  if (action === "go-profile") {
    route = { name: "profile" };
    render();
  } else if (action === "go-welcome") {
    route = { name: "welcome" };
    render();
  } else if (action === "go-today") {
    route = { name: "today" };
    render();
  } else if (action === "dismiss-disclaimer") {
    state.disclaimerDismissed = true;
    persist();
    render();
  } else if (action === "open-activity") {
    route = { name: "activity", id: t.dataset.id };
    render();
  } else if (action === "swap-activity") {
    ensureTodayPicks({ forceSwap: true, excludeId: t.dataset.id });
    render();
  } else if (action === "go-complete") {
    completeDraft = { feedback: null, note: "" };
    route = { name: "complete", id: t.dataset.id };
    render();
  } else if (action === "set-feedback") {
    completeDraft.feedback = t.dataset.v;
    render();
  } else if (action === "save-complete") {
    if (!completeDraft.feedback) return;
    const a = content.activities.find((x) => x.id === t.dataset.id);
    state.logs = [
      ...(state.logs || []),
      {
        activityId: t.dataset.id,
        completedAt: new Date().toISOString(),
        feedback: completeDraft.feedback,
        note: completeDraft.note || "",
        primaryDomain: primaryDomain(a),
      },
    ];
    let awarded = 0;
    let message = "Đã ghi nhận buổi chơi.";
    if (state.rewardsEnabled !== false) {
      const earn = earnOnComplete(state.gxLedger || [], t.dataset.id, toIsoDate());
      state.gxLedger = earn.ledger;
      awarded = earn.awarded;
      message = earn.message;
    }
    state.lastGxAwarded = awarded;
    state.lastEarnMessage = message;
    persist();
    route = { name: "complete", id: t.dataset.id, celebrate: true };
    render();
  } else if (action === "reset-data") {
    if (confirm("Xóa hồ sơ bé và nhật ký trên máy này?")) {
      state = resetState();
      route = { name: "welcome" };
      render();
    }
  }
});

root.addEventListener("change", (e) => {
  const t = e.target;
  if (!(t instanceof HTMLInputElement)) return;
  if (t.dataset.action === "toggle-rewards") {
    state.rewardsEnabled = t.checked;
    persist();
    render();
  } else if (t.dataset.action === "toggle-food") {
    state.foodTreatVisible = t.checked;
    persist();
    render();
  } else if (t.dataset.action === "font-scale") {
    state.fontScale = Number(t.value) || 1;
    persist();
  } else if (t.dataset.action === "note") {
    completeDraft.note = t.value;
  }
});

root.addEventListener("input", (e) => {
  const t = e.target;
  if (t.dataset.action === "note") completeDraft.note = t.value;
});

root.addEventListener("submit", (e) => {
  const form = e.target;
  if (form.id !== "profileForm") return;
  e.preventDefault();
  const fd = new FormData(form);
  const nickname = String(fd.get("nickname") || "").trim();
  const birthDate = String(fd.get("birthDate") || "");
  if (!nickname || !birthDate) return;
  state.child = {
    id: state.child?.id || newChildId(),
    nickname,
    birthDate,
  };
  state.onboardingDone = true;
  persist();
  route = { name: "today" };
  render();
});

boot().catch((err) => {
  root.innerHTML = `<div class="card" style="margin-top:2rem"><h2>Không tải được nội dung</h2><p class="muted">${escapeHtml(err.message)}</p><p class="muted">Chạy <code>python serve.py</code> trong thư mục web.</p></div>`;
});
