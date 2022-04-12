package gsg.corp.driver_data.repository

import gsg.corp.driver_data.mapper.toUserInfo
import gsg.corp.driver_data.remote.DriverApi
import gsg.corp.driver_data.remote.request.VerificationRequest
import gsg.corp.driver_domain.model.UserInfo
import gsg.corp.driver_domain.repository.DriverRepository
import java.lang.Exception

class DriverRepositoryImpl(
    private val api: DriverApi
):DriverRepository {
    override suspend fun verificationUser(user: String, password: String): Result<UserInfo> {

        return try {
            val loginDto = api.verificationUser(VerificationRequest(user,password))
            Result.success(
                loginDto.data.toUserInfo()
            )

        }catch (e: Exception){
            e.printStackTrace()
            Result.failure(e)
        }
    }
}