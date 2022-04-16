package gsg.corp.driver_domain.model

data class Route(
    val id:Int,
    val routeType:String,
    val provider:String,
    val providerPhone:String,
    val providerOtherPhone:String,
    val customer:String,
    val customerPhone:String,
    val customerOtherPhone:String,
    val customerDistrict:String,
    val customerAddress:String,
    val customerRef:String,
    val product:String,
    val productDetail:String,
    val payMethod:String,
    val amountCollect:String,
    val payDetail:String,
    val dateRoute:String,
    val state:String
)
