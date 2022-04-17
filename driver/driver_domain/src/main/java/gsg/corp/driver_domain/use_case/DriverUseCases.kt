package gsg.corp.driver_domain.use_case

data class DriverUseCases(
    val verificationUser: VerificationUser,
    val getRoutes: GetRoutes,
    val updateRoute: UpdateRoute
)
