package gsg.corp.driver_presentation.dashboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gsg.corp.core.Util.UiEvent
import gsg.corp.core.domain.preferences.Preferences
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel
@Inject constructor(
private val pref: Preferences
): ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var name by mutableStateOf("")
        private set

    init {
        if (pref.loadUserInfo().name.isNotEmpty()){
            name = pref.loadUserInfo().name
        }
    }

    fun onNavigationClick(item:Int){
        viewModelScope.launch {
            _uiEvent.send(UiEvent.Success)
        }
    }

}