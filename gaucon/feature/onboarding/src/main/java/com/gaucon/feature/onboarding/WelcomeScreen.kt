package com.gaucon.feature.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

private data class WelcomeSlide(val title: String, val body: String)

@Composable
fun WelcomeScreen(
    onStart: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val slides = listOf(
        WelcomeSlide(
            "Hôm nay chơi gì?",
            "Mỗi ngày 2–3 hoạt động ngắn để bạn chơi cùng con ngoài đời thật, dùng đồ có sẵn trong nhà.",
        ),
        WelcomeSlide(
            "Mỗi bé một nhịp riêng",
            "Không so sánh phần trăm với trẻ khác. Bỏ lỡ một ngày cũng không sao.",
        ),
        WelcomeSlide(
            "Nháp nội bộ",
            "Nội dung đang ở trạng thái draft_unreviewed — chưa được chuyên gia duyệt. Không phải chẩn đoán y tế.",
        ),
    )
    val pagerState = rememberPagerState(pageCount = { slides.size })
    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        HorizontalPager(state = pagerState, modifier = Modifier.weight(1f)) { page ->
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
            ) {
                Text(slides[page].title, style = MaterialTheme.typography.headlineMedium)
                Spacer(Modifier.height(16.dp))
                Text(slides[page].body, style = MaterialTheme.typography.bodyLarge)
            }
        }
        Column(Modifier.fillMaxWidth()) {
            if (pagerState.currentPage < slides.lastIndex) {
                Button(
                    onClick = { scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) } },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text("Tiếp")
                }
            } else {
                Button(onClick = onStart, modifier = Modifier.fillMaxWidth()) {
                    Text("Bắt đầu")
                }
            }
            Spacer(Modifier.height(8.dp))
            Text(
                "App không xin quyền thông báo ở bước này.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            )
        }
    }
}
