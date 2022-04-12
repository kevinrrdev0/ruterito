package gsg.corp.core.preferences

import android.content.SharedPreferences
import gsg.corp.core.domain.model.UserInfo
import gsg.corp.core.domain.preferences.Preferences

class DefaultPreferences(
    private val pref: SharedPreferences,
) : Preferences {
    override fun saveId(id: Int) {
        pref.edit()
            .putInt(Preferences.KEY_ID, id)
            .apply()
    }

    override fun saveName(name: String) {
        pref.edit()
            .putString(Preferences.KEY_NAME, name)
            .apply()
    }

    override fun saveRole(role: String) {
        pref.edit()
            .putString(Preferences.KEY_ROLE, role)
            .apply()
    }

    override fun saveUserName(username: String) {
        pref.edit()
            .putString(Preferences.KEY_USER_NAME, username)
            .apply()
    }

    override fun loadUserInfo(): UserInfo {
        val id = pref.getInt(Preferences.KEY_ID, -1)
        val name = pref.getString(Preferences.KEY_NAME, null)
        val username = pref.getString(Preferences.KEY_USER_NAME, null)
        return UserInfo(
            id,
            name ?: "Sin Nombre",
            username?:""
        )
    }

    override fun saveCredentials(flk: Boolean) {
        pref.edit()
            .putBoolean(Preferences.KEY_SAVE_CREDENTIALS,flk)
            .apply()
    }

    override fun loadSaveCredentials(): Boolean {
        return pref.getBoolean(Preferences.KEY_SAVE_CREDENTIALS,false)
    }
}