package id.ronnysugianto.themoviedb.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.lifecycle.MutableLiveData

abstract class BaseViewModel : ViewModel() {
    private val _isInProgress = MutableLiveData<Boolean>()
    private val _error = MutableLiveData<Throwable>()

    fun launch(
        task: suspend CoroutineScope.() -> Unit
    ) {
        viewModelScope.launch {
            try {
                task()
            } catch (throwable: Throwable) {
                _error.value = throwable
            } finally {
                _isInProgress.value = false
            }
        }
    }
}
