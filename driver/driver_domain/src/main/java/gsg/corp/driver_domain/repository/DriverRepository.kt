package gsg.corp.driver_domain.repository

import gsg.corp.driver_domain.model.UserInfo

interface DriverRepository {

    suspend fun verificationUser(user:String,password:String):Result<UserInfo>

}