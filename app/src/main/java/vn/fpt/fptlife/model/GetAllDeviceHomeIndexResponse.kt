package vn.fpt.fptlife.model

data class GetAllDeviceHomeIndexResponse(
    var status: Int,
    var message: String,
    var data: DataAllDeviceHomeIndex,
) {
    inner class DataAllDeviceHomeIndex(
        var total: Int,
        var devices: ArrayList<Device>
    )
}
