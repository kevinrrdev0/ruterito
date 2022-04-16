package gsg.corp.driver_presentation.route

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gsg.corp.core.Util.UiEvent
import gsg.corp.core.domain.preferences.Preferences
import gsg.corp.driver_domain.use_case.DriverUseCases
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RouteViewModel @Inject constructor(
    private val pref: Preferences,
    private val driverUseCases: DriverUseCases,
) : ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var state by mutableStateOf(RouteState())
        private set


    init {
        getRoutes(id = pref.loadUserInfo().id)

    }

    fun onEvent(event: RouteEvent){
        when(event){
            is RouteEvent.OnToggleRouteClick -> state = state.copy(listRoutes = state.listRoutes.map {
                if(it.route.id == event.routeUiState.route.id){
                    it.copy(isExpanded = !it.isExpanded)
                }else it
            })
        }

    }

    private fun getRoutes(id: Int) {
        state = state.copy(loading = true)
        viewModelScope.launch {
            driverUseCases
                .getRoutes(id)
                .onSuccess {
                    state = state.copy(listRoutes = it.map {
                            r -> RouteUiState(r, false)
                    })
                    state = state.copy(loading = false)
                }
                .onFailure {
                    state = state.copy(loading = false)
                }

        }
    }

}