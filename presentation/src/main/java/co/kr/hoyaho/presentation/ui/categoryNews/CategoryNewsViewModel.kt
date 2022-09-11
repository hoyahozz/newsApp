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

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isError: MutableLiveData<Boolean> = MutableLiveData()
    val isError: LiveData<Boolean> = _isError

    fun setCategory(category: String) {
        _category = category
        fetchCategoryNews()
    }

    fun fetchCategoryNews() {
        if(_news.value == null) {
            viewModelScope.launch {
                _isLoading.postValue(true)
                when (val result = categoryNewsUseCase.invoke(_category)) {
                    is NetworkResult.Success -> {
                        val news = result.data
                        _news.value = news
                        _isLoading.postValue(false)
                        _isError.postValue(false)
                    }
                    is NetworkResult.Error -> {
                        val msg =
                            result.errorType.toErrorMessage(getApplication<Application>().applicationContext)
                        _showToast.value = Event(msg)
                        _isLoading.postValue(false)
                        _isError.postValue(true)
                    }
                }
            }
        }
    }
}
