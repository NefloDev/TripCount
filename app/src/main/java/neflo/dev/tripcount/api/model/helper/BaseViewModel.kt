package neflo.dev.tripcount.api.model.helper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class BaseViewModel : ViewModel() {
    private var mJob: Job? = null

    protected fun <T> responseRequest(liveData: MutableStateFlow<T>, errorHandler: CoroutinesErrorHandler, request: () -> Flow<T>) {
        mJob = viewModelScope.launch { Dispatchers.IO + CoroutineExceptionHandler { _, error ->
            viewModelScope.launch(Dispatchers.Main) {
                errorHandler.onError(error.localizedMessage ?: "An error occurred. Please try again.")
            }
        }
            request().collect {
                withContext(Dispatchers.Main) {
                    liveData.value = it
                }
            }
        }
    }

    protected fun <T> responseRequest(liveData: MutableStateFlow<T>, request: () -> Flow<T>) {
        mJob = viewModelScope.launch(Dispatchers.IO) {
            request().collect {
                withContext(Dispatchers.Main) {
                    liveData.value = it
                }
            }
        }
    }

    protected fun <T> performRequest(request: () -> Flow<T>) {
        mJob = viewModelScope.launch(Dispatchers.IO) {
            request()
        }
    }



    override fun onCleared() {
        mJob?.let {
            if (it.isActive) {
                it.cancel()
            }
        }
    }

    interface CoroutinesErrorHandler {
        fun onError(message: String)
    }

}