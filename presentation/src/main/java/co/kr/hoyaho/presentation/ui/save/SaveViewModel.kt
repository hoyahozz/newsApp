package co.kr.hoyaho.presentation.ui.save

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.kr.hoyaho.domain.model.News
import co.kr.hoyaho.domain.usecase.GetSavedNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaveViewModel @Inject constructor(
    private val savedNewsUseCase: GetSavedNewsUseCase
) : ViewModel() {

    private val _news: MutableLiveData<List<News>> = MutableLiveData()
    val news: LiveData<List<News>> get() = _news

    fun getSavedNews() {
        viewModelScope.launch {
            _news.value = savedNewsUseCase.invoke()
        }
    }
}
