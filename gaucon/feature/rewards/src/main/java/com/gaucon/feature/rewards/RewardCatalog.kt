package com.gaucon.feature.rewards

import androidx.annotation.DrawableRes

enum class RewardTier { SHARED_MOMENT, EXPERIENCE, PLAY, FOOD_TREAT }

data class RewardCatalogItem(
    val id: String,
    val title: String,
    val costGx: Int,
    val vndHint: String,
    val tier: RewardTier,
    @DrawableRes val imageRes: Int,
    val caution: Boolean = false,
)

val RewardCatalog: List<RewardCatalogItem> = listOf(
    RewardCatalogItem("mom_song_pick", "Bé chọn bài hát / nhảy 1 bài", 25, "0đ", RewardTier.SHARED_MOMENT, R.drawable.reward_song),
    RewardCatalogItem("mom_choose_game", "Bé chọn trò tiếp theo", 28, "0đ", RewardTier.SHARED_MOMENT, R.drawable.reward_choose_game),
    RewardCatalogItem("mom_story_10", "Thêm 10 phút chuyện / sách", 30, "0đ", RewardTier.SHARED_MOMENT, R.drawable.reward_story),
    RewardCatalogItem("mom_bath_toys", "Tắm chơi thêm 5 phút", 35, "0đ", RewardTier.SHARED_MOMENT, R.drawable.reward_bath),
    RewardCatalogItem("mom_sticker", "Sticker khen cụ thể", 40, "10–30 nghìn", RewardTier.SHARED_MOMENT, R.drawable.reward_sticker),
    RewardCatalogItem("mom_grandparents", "Gọi ông bà khoe trò hôm nay", 45, "0đ", RewardTier.SHARED_MOMENT, R.drawable.reward_grandparents),
    RewardCatalogItem("mom_picnic_blanket", "Picnic thảm tại nhà", 60, "0–40 nghìn", RewardTier.SHARED_MOMENT, R.drawable.reward_picnic),
    RewardCatalogItem("mom_photo_print", "In 1 ảnh chơi cùng nhau", 80, "5–25 nghìn", RewardTier.SHARED_MOMENT, R.drawable.reward_photo),
    RewardCatalogItem("exp_bus_ride", "Chuyến xe buýt / tàu vui ngắn", 120, "20–60 nghìn", RewardTier.EXPERIENCE, R.drawable.reward_bus),
    RewardCatalogItem("exp_park", "Công viên / sân chơi 1 buổi", 200, "50–150 nghìn", RewardTier.EXPERIENCE, R.drawable.reward_park),
    RewardCatalogItem("exp_swim_paddle", "Bể bơi nông (gia đình quen)", 260, "70–180 nghìn", RewardTier.EXPERIENCE, R.drawable.reward_swim),
    RewardCatalogItem("exp_zoo_corner", "Góc thú / bảo tàng thiếu nhi", 280, "80–200 nghìn", RewardTier.EXPERIENCE, R.drawable.reward_zoo),
    RewardCatalogItem("play_reuse", "Đồ chơi từ nhà (carton, chai sạch)", 40, "0–20 nghìn", RewardTier.PLAY, R.drawable.reward_reuse),
    RewardCatalogItem("play_art", "Sáp / màu ngón tay an toàn", 140, "30–90 nghìn", RewardTier.PLAY, R.drawable.reward_art),
    RewardCatalogItem("play_small", "Đồ chơi nhỏ", 150, "30–80 nghìn", RewardTier.PLAY, R.drawable.reward_toy_small),
    RewardCatalogItem("play_book", "Sách tranh 1 cuốn", 180, "40–120 nghìn", RewardTier.PLAY, R.drawable.reward_story),
    RewardCatalogItem("play_mid", "Đồ chơi trung", 320, "100–250 nghìn", RewardTier.PLAY, R.drawable.reward_toy_mid),
    RewardCatalogItem("food_yogurt", "Sữa chua ít đường thêm", 45, "10–30 nghìn", RewardTier.FOOD_TREAT, R.drawable.reward_yogurt),
    RewardCatalogItem("food_fruit_cup", "Trái cây cắt / cup", 50, "15–40 nghìn", RewardTier.FOOD_TREAT, R.drawable.reward_fruit),
    RewardCatalogItem("food_watermelon", "Nước dưa hấu / dưa cắt", 50, "15–35 nghìn", RewardTier.FOOD_TREAT, R.drawable.reward_watermelon),
    RewardCatalogItem("food_lollipop", "Kẹo mút (1 cây)", 35, "5–15 nghìn", RewardTier.FOOD_TREAT, R.drawable.reward_lollipop, caution = true),
    RewardCatalogItem("food_snack", "Bim bim gói nhỏ", 45, "10–25 nghìn", RewardTier.FOOD_TREAT, R.drawable.reward_snack, caution = true),
    RewardCatalogItem("food_sugarcane", "Nước mía (ly)", 55, "10–20 nghìn", RewardTier.FOOD_TREAT, R.drawable.reward_sugarcane, caution = true),
    RewardCatalogItem("food_che_beans", "Chè đậu / tàu hủ ít đường", 65, "15–35 nghìn", RewardTier.FOOD_TREAT, R.drawable.reward_che, caution = true),
    RewardCatalogItem("food_banhmi_mini", "Bánh mì mini / bánh bao nhỏ", 70, "15–40 nghìn", RewardTier.FOOD_TREAT, R.drawable.reward_banhmi),
    RewardCatalogItem("food_oc", "Ăn ốc (suất nhỏ / share)", 90, "40–80 nghìn", RewardTier.FOOD_TREAT, R.drawable.reward_oc, caution = true),
)

fun tierLabel(tier: RewardTier): String = when (tier) {
    RewardTier.SHARED_MOMENT -> "Khoảnh khắc"
    RewardTier.EXPERIENCE -> "Trải nghiệm"
    RewardTier.PLAY -> "Đồ chơi"
    RewardTier.FOOD_TREAT -> "Đồ ăn / uống (tùy chọn)"
}
