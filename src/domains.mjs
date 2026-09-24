export const DOMAIN_LABELS = {
  PHYSICAL: "Thể chất",
  COGNITIVE: "Nhận thức",
  LANGUAGE: "Ngôn ngữ",
  SOCIAL_EMOTIONAL: "Tình cảm – xã hội",
  AESTHETIC: "Thẩm mỹ",
  SELF_CARE: "Tự lập & sinh hoạt",
};

export const DOMAIN_ORDER = Object.keys(DOMAIN_LABELS);

export function domainLabel(code) {
  return DOMAIN_LABELS[code] || code;
}

export function primaryDomain(activity) {
  return activity?.domains?.[0] || null;
}
