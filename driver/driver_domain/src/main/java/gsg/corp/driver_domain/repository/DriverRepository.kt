package gsg.corp.driver_domain.repository

import android.net.Uri
import gsg.corp.driver_domain.model.Route
import gsg.corp.driver_domain.model.UserInfo
import java.io.File

interface DriverRepository {

    suspend fun verificationUser(user:String,password:String):Result<UserInfo>

    suspend fun getRoutes(id:Int):Result<List<Route>>

    suspend fun updateRoute(file: File, uri:Uri,path:String)

}