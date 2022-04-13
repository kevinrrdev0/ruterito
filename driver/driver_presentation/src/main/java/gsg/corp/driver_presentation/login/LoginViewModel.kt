package gsg.corp.driver_presentation.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gsg.corp.core.Util.UiEvent
import gsg.corp.core.Util.UiText
import gsg.corp.driver_domain.use_case.DriverUseCases
import gsg.corp.core.R
import gsg.corp.core.domain.preferences.Preferences
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor(
    private val driverUseCases: DriverUseCases,
    private val pref: Preferences,
) : ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var username by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var saveCredentials by mutableStateOf(false)
        private set
    var isLoading by mutableStateOf(false)
        private set

    init {
        if (pref.loadSaveCredentials()){
            username = pref.loadUserInfo().username
            saveCredentials = pref.loadSaveCredentials()
        }
    }
    
    fun onLogin() {
        isLoading = true
        viewModelScope.launch {
            driverUseCases
                .verificationUser(userName = username, userPassword = password)
                .onSuccess {
                    if (it.id > 0) {
                        pref.saveId(it.id)
                        pref.saveName(it.name)
                        pref.saveRole(it.role)
                        pref.saveUserName(it.user)
                        pref.saveCredentials(saveCredentials)
                        delay(2000)
                        _uiEvent.send(UiEvent.Success)
                    }
                    isLoading = false
                }
                .onFailure {
                    isLoading = false
                    _uiEvent.send(
                        UiEvent.ShowSnackBar(
                            UiText.StringResource(R.string.error_login)
                        )
                    )
                }
        }
    }

    fun onUsernameEnter(item: String) {
        username = item
    }

    fun onPasswordEnter(item: String) {
        password = item
    }

    fun onCheckRemember(item: Boolean) {
        saveCredentials = item
    }


}