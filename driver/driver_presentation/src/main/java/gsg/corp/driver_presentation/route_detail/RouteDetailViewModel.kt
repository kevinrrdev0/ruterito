package gsg.corp.driver_presentation.route_detail

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gsg.corp.core.Util.UiEvent
import gsg.corp.core.domain.model.GeneralType
import gsg.corp.driver_domain.use_case.DriverUseCases
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject


@HiltViewModel
class RouteDetailViewModel @Inject constructor(
    private val driverUseCases: DriverUseCases,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    var state by mutableStateOf(RouteDetailState())
        private set
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val idRoute = savedStateHandle.get<Int>("id")
        Log.d("kevdev", "idroute:$idRoute ")
        idRoute?.let {
            state = state.copy(idRoute = idRoute)
        }
    }

    fun onEvent(event: RouteDetailEvent) {
        when (event) {
            is RouteDetailEvent.OnCommentEnter -> state.copy(comment = event.comment)
            is RouteDetailEvent.OnPathPhotoCollect -> state.copy(pathPhotoCollect = event.path)
            is RouteDetailEvent.OnPathPhotoState -> state.copy(pathPhotoState = event.path)
            is RouteDetailEvent.OnStateSelected -> state.copy(state = GeneralType(event.id,event.name))
        }.also { state = it }
    }

    fun onPathChange(item: String) {
//        state = state.copy(path = item)
    }

    fun onUploadPhoto() {
//        val file = File(state.path)
//        viewModelScope.launch {
//            driverUseCases.updateRoute(file = file, uri = Uri.fromFile(file), state.path)
//        }

    }

}