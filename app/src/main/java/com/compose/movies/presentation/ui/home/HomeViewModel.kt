package com.compose.movies.presentation.ui.home

import android.util.Log
import androidx.lifecycle.*
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

    private var lastSearch = "-"

    private val _status = MutableLiveData<ApiStatus>()

    val status: LiveData<ApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<Show>>()

    val properties: LiveData<List<Show>>
        get() = _properties

    private val _navigateToSelectedProperty = MutableLiveData<Show?>()

    val navigateToSelectedProperty: LiveData<Show?>
        get() = _navigateToSelectedProperty


    init {
        getListShows()
    }

    fun getListShows(filter: String = "") {
        if (lastSearch != filter) {
            lastSearch = filter
            viewModelScope.launch {
                getListShowsUseCase(filter).collect {
                    when (it) {
                        is Response.Success -> {
                            it.data.apply {
                                _properties.postValue(this)
                            }
                            _status.value = ApiStatus.DONE
                        }
                        is Response.Error ->
                            _status.value = ApiStatus.ERROR
                        is Response.Loading -> _status.value = ApiStatus.LOADING
                    }
                }
            }
        }
    }

    fun displayPropertyDetails(value: Show) {
        _navigateToSelectedProperty.value = value
    }


    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

    override fun onCleared() {
        super.onCleared()
        bannerActive = false
    }
}