package com.gaucon.feature.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaucon.contentseed.ResourceCatalogLoader
import com.gaucon.contentseed.ResourceCategoryDto
import com.gaucon.contentseed.ResourceItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LibraryUiState(
    val loading: Boolean = true,
    val categories: List<ResourceCategoryDto> = emptyList(),
    val filter: String = "ALL",
    val items: List<ResourceItem> = emptyList(),
    val disclaimer: String = "",
    val error: String? = null,
)

@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val catalogLoader: ResourceCatalogLoader,
) : ViewModel() {

    private val _uiState = MutableStateFlow(LibraryUiState())
    val uiState: StateFlow<LibraryUiState> = _uiState.asStateFlow()

    fun load() {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true, error = null) }
            runCatching {
                val c = catalogLoader.catalog()
                _uiState.update {
                    it.copy(
                        loading = false,
                        categories = c.categories,
                        items = filterItems(c.items, it.filter),
                        disclaimer = c.disclaimer,
                    )
                }
            }.onFailure { e ->
                _uiState.update {
                    it.copy(loading = false, error = e.message ?: "Không tải được tài liệu.")
                }
            }
        }
    }

    fun setFilter(categoryId: String) {
        viewModelScope.launch {
            val c = catalogLoader.catalog()
            _uiState.update {
                it.copy(
                    filter = categoryId,
                    items = filterItems(c.items, categoryId),
                )
            }
        }
    }

    private fun filterItems(all: List<ResourceItem>, filter: String): List<ResourceItem> =
        if (filter == "ALL") all else all.filter { it.category == filter }
}
