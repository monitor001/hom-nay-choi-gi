package com.gaucon.feature.reminder

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ReminderSettingsScreen(
    onOpenRewards: (() -> Unit)? = null,
    onOpenJournal: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    viewModel: ReminderSettingsViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    var permissionGranted by remember {
        mutableStateOf(
            if (Build.VERSION.SDK_INT < 33) {
                true
            } else {
                ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) ==
                    PackageManager.PERMISSION_GRANTED
            },
        )
    }
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { granted -> permissionGranted = granted }

    LaunchedEffect(Unit) { viewModel.load() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text("Nhắc nhở", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
        Text("Thông báo định kỳ", style = MaterialTheme.typography.headlineMedium)
        if (onOpenRewards != null) {
            OutlinedButton(
                onClick = onOpenRewards,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("Cửa hàng Gấu Xu")
            }
        }
        if (onOpenJournal != null) {
            OutlinedButton(
                onClick = onOpenJournal,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("Nhật ký")
            }
        }
        Text(
            "Chọn giờ nhận gợi ý chơi cùng con. Nhắc dùng báo thức hệ thống — vẫn hiện khi app đã đóng hoặc sau khi khởi động lại máy. Không gửi trong giờ yên lặng (21:30–07:00) trừ khi bạn tự đặt giờ.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        if (state.childName != null) {
            Text("Cho bé: ${state.childName}", style = MaterialTheme.typography.bodyLarge)
        }
        if (state.loading) {
            Text("Đang tải…")
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("Bật nhắc mỗi ngày", style = MaterialTheme.typography.titleMedium)
                Switch(checked = state.enabled, onCheckedChange = viewModel::setEnabled)
            }
            Text("Giờ nhắc", style = MaterialTheme.typography.titleMedium)
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
                Column {
                    Text("Giờ", style = MaterialTheme.typography.labelMedium)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        OutlinedButton(onClick = { viewModel.setHour(state.hour - 1) }) { Text("−") }
                        Text(
                            "%02d".format(state.hour),
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.padding(horizontal = 12.dp),
                        )
                        OutlinedButton(onClick = { viewModel.setHour(state.hour + 1) }) { Text("+") }
                    }
                }
                Column {
                    Text("Phút", style = MaterialTheme.typography.labelMedium)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        OutlinedButton(onClick = { viewModel.setMinute(state.minute - 5) }) { Text("−") }
                        Text(
                            "%02d".format(state.minute),
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.padding(horizontal = 12.dp),
                        )
                        OutlinedButton(onClick = { viewModel.setMinute(state.minute + 5) }) { Text("+") }
                    }
                }
            }
            Text(
                "Lịch hiện tại: %02d:%02d".format(state.hour, state.minute),
                style = MaterialTheme.typography.bodyLarge,
            )
            if (!permissionGranted) {
                Text(
                    "Máy đang tắt quyền thông báo — nhắc sẽ không hiện.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error,
                )
                OutlinedButton(
                    onClick = {
                        if (Build.VERSION.SDK_INT >= 33) {
                            permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text("Cho phép thông báo")
                }
            } else {
                Text(
                    "Quyền thông báo: đã bật",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
            Spacer(Modifier.height(8.dp))
            Button(onClick = { viewModel.save() }, modifier = Modifier.fillMaxWidth()) {
                Text("Lưu lịch nhắc")
            }
            OutlinedButton(
                onClick = { viewModel.sendTestNotification() },
                modifier = Modifier.fillMaxWidth(),
                enabled = permissionGranted,
            ) {
                Text("Thử thông báo ngay")
            }
            state.savedMessage?.let {
                Text(it, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.secondary)
            }
        }
    }
}
