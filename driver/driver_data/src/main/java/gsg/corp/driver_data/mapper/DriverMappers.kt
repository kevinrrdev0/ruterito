package gsg.corp.driver_data.mapper

import gsg.corp.driver_data.remote.dto.Data
import gsg.corp.driver_domain.model.Route
import gsg.corp.driver_domain.model.UserInfo

fun Data.toUserInfo(): UserInfo {
    return UserInfo(
        id = idusuario,
        name = nombres,
        user = usuario,
        idRole = id_rol,
        role = rol
    )
}

fun gsg.corp.driver_data.remote.dto.route.Data.toRoute(): Route {

    val delimit = "/"
    val list = cel_cliente.split(delimit)
    val number1 = if (list.isNotEmpty()) list[0].trim() else ""

    val number2 = if (list.isNotEmpty() && list.size==2) list[1].trim() else ""

    val list2 = cel_proveedor.split(delimit)
    val cellProvider = if (list2.isNotEmpty()) list2[0].trim() else ""

    val otherCellProvider = if (list2.isNotEmpty() && list2.size==2) list2[1].trim() else ""

    return Route(
        id = id,
        routeType = tipo_ruta,
        provider = proveedor,
        providerPhone = cellProvider,
        providerOtherPhone = otherCellProvider,
        customer = cliente,
        customerPhone = number1,
        customerOtherPhone = number2,
        customerDistrict = distrito,
        customerAddress = direccion,
        customerRef = referencia,
        product = producto,
        productDetail = producto_detalle,
        payMethod = metodo_pago,
        amountCollect = monto_cobrar,
        payDetail = detalle_pago,
        dateRoute = fecha_ruta,
        state = estado
    )
}