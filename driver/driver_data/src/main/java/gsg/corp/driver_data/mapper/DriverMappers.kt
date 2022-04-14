package gsg.corp.driver_data.mapper

import gsg.corp.driver_data.remote.dto.Data
import gsg.corp.driver_domain.model.Route
import gsg.corp.driver_domain.model.UserInfo

fun Data.toUserInfo():UserInfo{
    return UserInfo(
        id = idusuario,
        name = nombres,
        user=usuario,
        idRole = id_rol,
        role = rol
    )
}

fun gsg.corp.driver_data.remote.dto.route.Data.toRoute():Route{
    return Route(
        id=id,
        routeType = tipo_ruta,
        provider = proveedor,
        providerPhone = cel_proveedor,
        customer = cliente,
        customerPhone = cel_cliente,
        customerDistrict = distrito,
        customerAddress = direccion,
        customerRef = referencia,
        product=producto,
        productDetail = producto_detalle,
        payMethod = metodo_pago,
        amountCollect = monto_cobrar,
        payDetail = detalle_pago,
        dateRoute = fecha_ruta,
        state = estado
    )
}