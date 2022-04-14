package gsg.corp.driver_data.remote.dto.route

data class Data(
    val cel_cliente: String,
    val cel_proveedor: String,
    val cliente: String,
    val detalle_pago: String,
    val direccion: String,
    val distrito: String,
    val driver: String,
    val estado: String,
    val fecha_ruta: String,
    val id: Int,
    val metodo_pago: String,
    val monto_cobrar: String,
    val producto: String,
    val producto_detalle: String,
    val proveedor: String,
    val referencia: String,
    val tipo_ruta: String
)