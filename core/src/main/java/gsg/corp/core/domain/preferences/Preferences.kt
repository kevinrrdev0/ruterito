package gsg.corp.core.domain.preferences

import gsg.corp.core.domain.model.UserInfo

interface Preferences {


    fun saveId(id:Int)
    fun saveName(name:String)
    fun saveRole(role:String)
    fun saveUserName(username:String)

    fun loadUserInfo(): UserInfo

    fun saveCredentials(flk:Boolean)
    fun loadSaveCredentials(): Boolean

    companion object {
        const val KEY_ID = "id"
        const val KEY_NAME = "name"
        const val KEY_ROLE = "role"
        const val KEY_USER_NAME = "username"
        const val KEY_SAVE_CREDENTIALS = "save_credentials"

    }
}