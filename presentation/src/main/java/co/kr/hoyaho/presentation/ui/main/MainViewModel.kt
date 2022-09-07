package co.kr.hoyaho.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _toolbarState: MutableLiveData<Pair<String, Boolean>> = MutableLiveData()
    val toolbarState: LiveData<Pair<String, Boolean>> get() = _toolbarState

    fun updateToolbarState(title: String, isBackVisible: Boolean) {
        _toolbarState.postValue(Pair(title, isBackVisible))
    }
}
