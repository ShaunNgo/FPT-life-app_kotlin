package vn.fpt.fptlife.model

import com.google.gson.annotations.SerializedName
import java.util.ArrayList
import java.util.Date

data class Device(
    var features: ArrayList<Int>,
    var productInfos: ArrayList<Int>,
    var id: String,
    var flfCustomerId: Int,
    var rogoId: String,
    var profileGroupDeviceId: String,
    var profileRoomId: String,
    var flfCategoryId: String,
    var flfIconId: String,
    var flfDeviceId: String,
    var profileHouseId: String,
    var devTypeCode: Int,
    var devTypeStr: String,
    var rogoDevSubTypeId: String,
    var name: String,
    var dsn: String,
    var macAddress: String,
    @SerializedName("ETHMac")
    var eTHMac: String,
    @SerializedName("WifiMac")
    var wifiMac: String,
    @SerializedName("BtMac")
    var btMac: String,
    var isConnectedWifi: Int,
    var isFavorited: Int,
    var isSecureDevice: Int,
    var createdDate: Date?, // Can be nullable
    var modifiedDate: Date?, // Can be nullable
    var isDisabled: Int,
    var icon: DeviceResponse.Icon?, // Can be nullable
    var rogoDevSubType: DeviceResponse.RogoDevSubType?, // Can be nullable
    var element: DeviceResponse.Element?, // Can be nullable
    var room: DeviceResponse.Room?, // Can be nullable
    var profileDeviceIds: ArrayList<String>,
    var rogoIds: ArrayList<String>,
    var totalDevice: Int,
    var group: DeviceResponse.Group?, // Can be nullable
    var total: Int,
    var devices: ArrayList<Device>
)