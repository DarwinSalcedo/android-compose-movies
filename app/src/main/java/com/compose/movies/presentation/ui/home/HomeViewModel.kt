package com.compose.movies.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.movies.domain.ApiStatus
import com.compose.movies.domain.model.Show
import com.compose.movies.domain.network.Response
import com.compose.movies.usecase.GetListShows
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getListShowsUseCase: GetListShows,
) :
    ViewModel() {

    private var bannerActive: Boolean = true

    private val _status = MutableLiveData<ApiStatus>()

    val status: LiveData<ApiStatus>
        get() = _status

    private val _listData = MutableLiveData<List<Show>>()

    val listData: LiveData<List<Show>>
        get() = _listData

    private val _navigateToDetail = MutableLiveData<Show?>()

    val navigateToDetail: LiveData<Show?>
        get() = _navigateToDetail


    init {
        getListShows()
    }

    fun getListShows() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            getListShowsUseCase("").collect {
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

    fun displayPropertyDetails(value: Show) {
        _navigateToDetail.value = value
    }


    fun displayPropertyDetailsComplete() {
        _navigateToDetail.value = null
    }

    override fun onCleared() {
        super.onCleared()
        bannerActive = false
    }

    fun search(filter: String) {}

}