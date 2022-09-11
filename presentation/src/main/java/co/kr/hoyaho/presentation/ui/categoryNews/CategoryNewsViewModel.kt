package co.kr.hoyaho.presentation.ui.categoryNews

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import co.kr.hoyaho.domain.NetworkResult
import co.kr.hoyaho.domain.model.News
import co.kr.hoyaho.domain.usecase.GetCategoryNewsUseCase
import co.kr.hoyaho.presentation.ui.util.Event
import co.kr.hoyaho.presentation.ui.util.toErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryNewsViewModel @Inject constructor(
    application: Application,
    private val categoryNewsUseCase: GetCategoryNewsUseCase
) : AndroidViewModel(application) {

    private var _category: String = ""
    val category: String get() = _category

    private val _news: MutableLiveData<List<News>> = MutableLiveData()
    val news: LiveData<List<News>> get() = _news

    private val _showToast: MutableLiveData<Event<String>> = MutableLiveData()
    val showToast: LiveData<Event<String>> = _showToast

    fun setCategory(category: String) {
        _category = category
        fetchCategoryNews()
    }

    private fun fetchCategoryNews() {
        viewModelScope.launch {
            when (val result = categoryNewsUseCase.invoke(_category)) {
                is NetworkResult.Success -> {
                    val news = result.data
                    _news.value = news
                }
                is NetworkResult.Error -> {
                    val msg =
                        result.errorType.toErrorMessage(getApplication<Application>().applicationContext)
                    _showToast.value = Event(msg)
                }
            }
        }
    }
}