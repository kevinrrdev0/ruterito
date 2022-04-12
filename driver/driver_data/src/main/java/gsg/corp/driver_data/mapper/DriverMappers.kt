package gsg.corp.driver_data.mapper

import gsg.corp.driver_data.remote.dto.Data
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