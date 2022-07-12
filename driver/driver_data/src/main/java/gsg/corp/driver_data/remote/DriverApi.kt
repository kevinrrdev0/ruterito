package gsg.corp.driver_data.remote

import gsg.corp.core.Util.LOGIN_ROUTE_V2
import gsg.corp.core.Util.ROUTE_DRIVER_V2
import gsg.corp.core.Util.STATUS_ROUTE_V3
import gsg.corp.driver_data.remote.dto.LoginDto
import gsg.corp.driver_data.remote.dto.UploadDto
import gsg.corp.driver_data.remote.dto.route.RouteDto
import gsg.corp.driver_data.remote.request.RouteDriverRequest
import gsg.corp.driver_data.remote.request.VerificationRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface DriverApi {

    @POST(LOGIN_ROUTE_V2)
    suspend fun verificationUser(@Body verificationRequest: VerificationRequest): LoginDto
    //MOVIE_HOME_ROUTE -> ?page=1&api_key=f46b58478f489737ad5a4651a4b25079


    @POST(ROUTE_DRIVER_V2)
    suspend fun getRoutes(@Body routeDriverRequest: RouteDriverRequest): RouteDto

    @Multipart
    @POST(STATUS_ROUTE_V3)
    suspend fun getUpload(@Part file: MultipartBody.Part,
                          @Part("id") id: RequestBody,
                          @Part("id_state") id_state: RequestBody
    ): UploadDto

    companion object {
        const val BASE_URL = "http://159.203.109.69/api/v1/"//http://159.203.109.69/api/v1/
    }
}