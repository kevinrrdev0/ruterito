package gsg.corp.driver_domain.use_case

import gsg.corp.driver_domain.model.UserInfo
import gsg.corp.driver_domain.repository.DriverRepository

class VerificationUser(
    private val repository: DriverRepository
) {
    suspend operator fun invoke(userName:String, userPassword:String):Result<UserInfo>{
        return repository.verificationUser(userName,userPassword)
    }
}