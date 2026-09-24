# -*- coding: utf-8 -*-
"""Generate simple line-art SVGs for tô tranh + story prompts (original Gấu Con)."""
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
OUT = ROOT / "content" / "resources" / "images"
WEB = ROOT / "web" / "content" / "resources" / "images"
OUT.mkdir(parents=True, exist_ok=True)
WEB.mkdir(parents=True, exist_ok=True)

SVGS = {
    "chuoi": """<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 200 200" fill="none" stroke="#1a2e28" stroke-width="3" stroke-linecap="round" stroke-linejoin="round">
  <path d="M70 40c-10 40-5 90 20 120 8-35 5-80-5-115z"/>
  <path d="M95 35c-5 45 0 95 25 125 5-40 0-85-10-120z"/>
  <path d="M115 40c0 50 10 95 35 120-5-45-15-90-25-120z"/>
  <path d="M85 28c10-8 25-8 40 0"/>
</svg>""",
    "ga": """<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 200 200" fill="none" stroke="#1a2e28" stroke-width="3" stroke-linecap="round">
  <ellipse cx="100" cy="110" rx="45" ry="35"/>
  <circle cx="130" cy="75" r="22"/>
  <path d="M145 75l20 5-20 5"/>
  <path d="M120 55l5-18 8 14 8-16 5 18"/>
  <path d="M70 100c-20-5-30 10-25 25"/>
  <path d="M85 145l-5 25M110 145l5 25"/>
  <circle cx="135" cy="70" r="2" fill="#1a2e28"/>
</svg>""",
    "meo": """<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 200 200" fill="none" stroke="#1a2e28" stroke-width="3" stroke-linecap="round">
  <circle cx="100" cy="95" r="40"/>
  <path d="M70 70l-15-30 30 18M130 70l15-30-30 18"/>
  <circle cx="85" cy="90" r="4"/><circle cx="115" cy="90" r="4"/>
  <path d="M100 100l-5 8h10z"/>
  <path d="M85 115c8 8 22 8 30 0"/>
  <path d="M55 100c-20 20-15 50 10 45M145 100c20 20 15 50-10 45"/>
  <path d="M140 130c25 5 35 30 20 45"/>
</svg>""",
    "ca": """<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 200 200" fill="none" stroke="#1a2e28" stroke-width="3" stroke-linecap="round">
  <path d="M40 100c20-40 80-50 120-20-40 30-100 40-120 20z"/>
  <path d="M160 80l30-25v70l-30-25"/>
  <circle cx="70" cy="90" r="4" fill="#1a2e28"/>
  <path d="M55 100h25"/>
  <path d="M100 70l10-20M110 125l10 20"/>
</svg>""",
    "mua": """<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 200 200" fill="none" stroke="#1a2e28" stroke-width="3" stroke-linecap="round">
  <path d="M50 80c0-30 40-45 60-20 15-25 50-20 55 10 25 0 35 30 15 45H55c-20-5-20-30-5-35z"/>
  <path d="M70 140l-5 25M95 145l-5 30M120 140l-5 25M145 145l-5 28"/>
</svg>""",
    "mat_troi": """<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 200 200" fill="none" stroke="#1a2e28" stroke-width="3" stroke-linecap="round">
  <circle cx="100" cy="100" r="35"/>
  <path d="M100 40v15M100 145v15M40 100h15M145 100h15M55 55l12 12M133 133l12 12M145 55l-12 12M55 145l12-12"/>
  <path d="M85 95c0 0 5-5 15-5s15 5 15 5M85 115c8 10 22 10 30 0"/>
</svg>""",
    "dep": """<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 200 200" fill="none" stroke="#1a2e28" stroke-width="3" stroke-linecap="round">
  <path d="M40 120c10-25 50-40 90-35 25 5 40 25 35 45H50c-15 0-20-5-10-10z"/>
  <path d="M70 95c15-20 45-25 70-10"/>
  <path d="M55 130h100"/>
</svg>""",
    "coc": """<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 200 200" fill="none" stroke="#1a2e28" stroke-width="3" stroke-linecap="round">
  <path d="M60 50h80l-10 110H70z"/>
  <path d="M140 65h25c10 0 15 20 5 35h-20"/>
  <path d="M75 90h50M75 120h45"/>
</svg>""",
    "xe_dap": """<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 200 200" fill="none" stroke="#1a2e28" stroke-width="3" stroke-linecap="round">
  <circle cx="55" cy="130" r="28"/><circle cx="145" cy="130" r="28"/>
  <path d="M55 130l40-45h35l15 45M95 85l25-25h20M120 85l-25 45"/>
  <circle cx="55" cy="130" r="4"/><circle cx="145" cy="130" r="4"/>
</svg>""",
    "la_cay": """<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 200 200" fill="none" stroke="#1a2e28" stroke-width="3" stroke-linecap="round">
  <path d="M100 170c0-80 50-120 70-130-30 40-40 90-40 130"/>
  <path d="M100 170c0-80-50-120-70-130 30 40 40 90 40 130"/>
  <path d="M100 170V55"/>
  <path d="M100 100c20-10 35-5 45 10M100 100c-20-10-35-5-45 10"/>
</svg>""",
}

for name, svg in SVGS.items():
    (OUT / f"{name}.svg").write_text(svg.strip() + "\n", encoding="utf-8")
    (WEB / f"{name}.svg").write_text(svg.strip() + "\n", encoding="utf-8")

print("wrote", len(SVGS), "svgs")
