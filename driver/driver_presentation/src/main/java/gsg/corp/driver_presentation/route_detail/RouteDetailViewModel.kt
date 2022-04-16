package gsg.corp.driver_presentation.route_detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import gsg.corp.core.Util.UiEvent
import gsg.corp.driver_domain.use_case.DriverUseCases
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject


@HiltViewModel
class RouteDetailViewModel @Inject constructor(
    private val driverUseCases: DriverUseCases,
    savedStateHandle: SavedStateHandle
):ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    init {
        val argument = savedStateHandle.get<Int>("id")
        Log.d("kevinrrdev", "argument: $argument")
    }

}