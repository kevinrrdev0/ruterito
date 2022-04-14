package gsg.corp.driver_data.remote

import gsg.corp.core.Util.LOGIN_ROUTE_V2
import gsg.corp.core.Util.ROUTE_DRIVER_V2
import gsg.corp.driver_data.remote.dto.LoginDto
import gsg.corp.driver_data.remote.dto.route.RouteDto
import gsg.corp.driver_data.remote.request.RouteDriverRequest
import gsg.corp.driver_data.remote.request.VerificationRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface DriverApi {

    @POST(LOGIN_ROUTE_V2)
    suspend fun verificationUser(@Body verificationRequest: VerificationRequest): LoginDto

    @POST(ROUTE_DRIVER_V2)
    suspend fun getRoutes(@Body routeDriverRequest: RouteDriverRequest): RouteDto

    companion object {
        const val BASE_URL = "http://159.203.109.69/api/v1/"
    }
}