package com.compose.movies.presentation.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.movies.domain.ApiStatus
import com.compose.movies.domain.model.Show
import com.compose.movies.domain.network.Response
import com.compose.movies.usecase.GetSuggestedShows
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getSuggestedShows: GetSuggestedShows,
) :
    ViewModel() {

    private val _status = MutableLiveData<ApiStatus>()

    val status: LiveData<ApiStatus>
        get() = _status

    private val _listData = MutableLiveData<List<Show>>()

    val listData: LiveData<List<Show>>
        get() = _listData

    var show : Show? = null

    fun start(value : Show){
        show = value
        geListSuggestedShows(value.id)
    }

    private fun geListSuggestedShows(id: Int) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            getSuggestedShows(id).collect {
                when (it) {
                    is Response.Success -> {
                        _listData.postValue(it.data)
                        _status.value = ApiStatus.DONE
                    }
                    is Response.Error -> _status.value = ApiStatus.ERROR

                    is Response.Loading -> _status.value = ApiStatus.LOADING
                }
            }
        }
    }

}