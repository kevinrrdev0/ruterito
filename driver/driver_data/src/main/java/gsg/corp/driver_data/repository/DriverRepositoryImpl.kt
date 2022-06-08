package gsg.corp.driver_data.repository

import android.net.Uri
import android.util.Log
import gsg.corp.driver_data.mapper.toRoute
import gsg.corp.driver_data.mapper.toUserInfo
import gsg.corp.driver_data.remote.DriverApi
import gsg.corp.driver_data.remote.request.RouteDriverRequest
import gsg.corp.driver_data.remote.request.VerificationRequest
import gsg.corp.driver_domain.model.Route
import gsg.corp.driver_domain.model.UserInfo
import gsg.corp.driver_domain.repository.DriverRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.lang.Exception

class DriverRepositoryImpl(
    private val api: DriverApi
):DriverRepository {
    override suspend fun verificationUser(user: String, password: String): Result<UserInfo> {

        return try {
            val loginDto = api.verificationUser(VerificationRequest(user,password))
            Result.success(
                loginDto.userInfo.toUserInfo()
            )

        }catch (e: Exception){
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun getRoutes(id: Int): Result<List<Route>> {
        return try {
            val routeDto = api.getRoutes(RouteDriverRequest(id))
            Result.success(routeDto.routes.map {
                it.toRoute()
            })
        }catch (e:Exception){
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun updateRoute(file: File, uri: Uri, path: String) {

        val profileImage: RequestBody = RequestBody.create(
            "image/jpg".toMediaTypeOrNull(),
            file
        )

        val profileImageBody: MultipartBody.Part =
            MultipartBody.Part.createFormData(
                "file",
                file.name, profileImage
            )
        val id =getMultiPartFormRequestBody("1")
        val id_state =getMultiPartFormRequestBody("4")
        val routeDto = api.getUpload(profileImageBody,id,id_state)
        routeDto.codigo
    }

    fun getMultiPartFormRequestBody(tag: String?): RequestBody {
        return RequestBody.create(MultipartBody.FORM, tag!!)

    }
}