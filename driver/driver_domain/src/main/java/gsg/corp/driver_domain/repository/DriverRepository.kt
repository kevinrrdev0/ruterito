package gsg.corp.driver_domain.repository

import gsg.corp.driver_domain.model.Route
import gsg.corp.driver_domain.model.UserInfo

interface DriverRepository {

    suspend fun verificationUser(user:String,password:String):Result<UserInfo>

    suspend fun getRoutes(id:Int):Result<List<Route>>

}