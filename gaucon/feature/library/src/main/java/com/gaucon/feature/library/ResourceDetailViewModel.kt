package com.gaucon.feature.library

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaucon.contentseed.ResourceCatalogLoader
import com.gaucon.contentseed.ResourceItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResourceDetailViewModel @Inject constructor(
    private val catalogLoader: ResourceCatalogLoader,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val resourceId: String = checkNotNull(savedStateHandle["resourceId"])

    private val _item = MutableStateFlow<ResourceItem?>(null)
    val item: StateFlow<ResourceItem?> = _item.asStateFlow()

    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    init {
        viewModelScope.launch {
            _loading.value = true
            _item.value = catalogLoader.getById(resourceId)
            _loading.value = false
        }
    }
}
