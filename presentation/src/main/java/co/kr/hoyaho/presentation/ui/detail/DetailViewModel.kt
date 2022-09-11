package co.kr.hoyaho.presentation.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.kr.hoyaho.domain.model.News
import co.kr.hoyaho.domain.usecase.SaveNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val saveNewsUseCase: SaveNewsUseCase
) : ViewModel() {

    private var _title: MutableLiveData<String> = MutableLiveData()
    val title: LiveData<String> get() = _title

    private var _author: String = ""
    val author: String get() = _author

    private var _elapsed: String = ""
    val elapsed: String get() = _elapsed

    private var _imgUrl: String = ""
    val imgUrl: String get() = _imgUrl

    private var _content: String = ""
    val content: String get() = _content

    fun setNews(news: News) {
        _title.value = news.title
        _author = news.author
        _elapsed = news.elapsed
        _imgUrl = news.imgUrl
        _content = news.content
    }

    fun saveNews(news: News) {
        viewModelScope.launch {
            saveNewsUseCase.invoke(news)
        }
    }
}
