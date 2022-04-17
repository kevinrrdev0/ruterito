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
import gsg.corp.driver_domain.use_case.DriverUseCases
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject


@HiltViewModel
class RouteDetailViewModel @Inject constructor(
    private val driverUseCases: DriverUseCases,
    savedStateHandle: SavedStateHandle
):ViewModel() {
    var state by mutableStateOf(RouteDetailState())
    private set
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    init {
        val argument = savedStateHandle.get<Int>("id")
        Log.d("kevinrrdev", "argument: $argument")
    }

    fun onUriChange(item:Uri){
        state = state.copy(uri = item)
    }

    fun onUploadPhoto(){
        val fileName = "data.txt"

        var file = File(fileName)
        viewModelScope.launch {
            driverUseCases.updateRoute(file = file,uri =state.uri,"")
        }

    }

}