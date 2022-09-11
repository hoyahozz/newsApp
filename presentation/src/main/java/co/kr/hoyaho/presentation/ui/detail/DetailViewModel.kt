package co.kr.hoyaho.presentation.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.kr.hoyaho.domain.model.News
import co.kr.hoyaho.domain.usecase.DeleteSavedNewsUseCase
import co.kr.hoyaho.domain.usecase.GetCheckSavedNewsUseCase
import co.kr.hoyaho.domain.usecase.SaveNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val saveNewsUseCase: SaveNewsUseCase,
    private val checkSavedNewsUseCase: GetCheckSavedNewsUseCase,
    private val deleteSavedNewsUseCase: DeleteSavedNewsUseCase
) : ViewModel() {

    private var _title: String = ""
    val title: String get() = _title

    private var _author: String = ""
    val author: String get() = _author

    private var _elapsed: String = ""
    val elapsed: String get() = _elapsed

    private var _imgUrl: String = ""
    val imgUrl: String get() = _imgUrl

    private var _content: String = ""
    val content: String get() = _content

    private var _publishedAt: String = ""

    private var _isSaved: MutableLiveData<Boolean> = MutableLiveData()
    val isSaved: LiveData<Boolean> get() = _isSaved

    fun checkIsSaved() {
        viewModelScope.launch {
            _isSaved.postValue(checkSavedNewsUseCase.invoke(_title))
        }
    }

    fun setNews(news: News) {
        _title = news.title
        _author = news.author
        _imgUrl = news.imgUrl
        _elapsed = news.elapsed
        _content = news.content
        _publishedAt = news.publishedAt
    }

    fun updateSavedState() {
        when(_isSaved.value) {
            true -> {
                deleteSaved()
                _isSaved.value = false
            }
            else -> {
                saveNews(News(_title, _author, _imgUrl, _elapsed, _content, _publishedAt))
                _isSaved.value = true
            }
        }
    }

    private fun saveNews(news: News) {
        viewModelScope.launch {
            saveNewsUseCase.invoke(news)
        }
    }

    private fun deleteSaved() {
        viewModelScope.launch {
            deleteSavedNewsUseCase.invoke(_title)
        }
    }
}
