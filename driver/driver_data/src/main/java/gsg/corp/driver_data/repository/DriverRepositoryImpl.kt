package gsg.corp.driver_data.repository

import android.net.Uri
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
        val files= File(uri.path);
        val profileImage: RequestBody = RequestBody.create(
            "image/jpg".toMediaTypeOrNull(),
            files
        )
        val bodyImagen = RequestBody

        val profileImageBody: MultipartBody.Part =
            MultipartBody.Part.createFormData(
                "file",
                files.name, profileImage
            )
        val routeDto = api.getUpload(profileImage)
        routeDto.codigo
    }
}