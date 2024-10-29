package vn.fpt.fptlife.model

import java.util.Date

data class DeviceResponse(
    var status: Int,
    var message: String,
    var data: ArrayList<DataDevice>
) {

    class DataDevice(
        var id: String,
        var name: String,
        var rogoDevTypeCode: Int,
        var devices: Device,
        var uriGetAllData: String
    )

    class Element(
        var id: String,
        var name: String,
        var elementId: String,
        var profileDeviceId: String,
        var profileRoomId: String,
        var flfIconId: String,
        var isDisplay: Int,
        var isFavorited: Int,
        var createdDate: Date,
        var modifiedDate: Date,
        var isDisabled: Int,
        var room: Room,
        var icon: Icon
    )

    class Group(
        var id: String,
        var flfCustomerId: Int,
        var profileRoomId: String,
        var profileHouseId: String,
        var vgroupId: String,
        var elementId: Int,
        var flfCategoryId: String,
        var flfIconId: Any,
        var rogoDevSubTypeCode: Int,
        var name: String,
        var slug: String,
        var createdDate: Date,
        var modifiedDate: Date,
        var isDisabled: Int,
        var icon: Any
    )

    class Icon(
        var pathname: String,
        var id: String,
        var deviceName: String,
        var rogoDevSubTypeCode: Int,
        var rogoDevSubTypeStr: String,
        var iconType: String,
        var isDisabled: Int,
        var isDefault: Int,
        var sort: Int,
        var createdDate: Date,
        var modifiedDate: Date
    )

    class RogoDevSubType(
        var id: String,
        var name: String,
        var devSubTypeCode: Int,
        var devSubTypeStr: String,
        var sort: Int,
        var createdDate: Date,
        var modifiedDate: Date,
        var isDisabled: Int,
        var isDefault: Int
    )

    class Room(
        var id: String,
        var sort: Int,
        var name: String,
        var slug: String,
        var profileHouseId: String,
        var rogoId: String,
        var fssId: String,
        var flfCustomerId: Int,
        var isDisabled: Int,
        var createdDate: Date,
        var modifiedDate: Date
    )
}
