package vn.fpt.fptlife.model

data class RoomResponse(
    var status: Int,
    var message: String,
    var time: String,
    var data: DataRoom,
) {

    class DataRoom(
        var total: Int,
        var currentPage: Int,
        var lastPage: Int,
        var limit: Int,
        var rooms: ArrayList<Room>? = null
    )

    class Room(
        var name: String?,
        var id: String?,
        var sort: Int?,
        var slug: String?,
        var profileHouseId: String?,
        var rogoId: String?,
        var fssId: String?,
        var flfCustomerId: Int?,
        var isDisabled: Int?,
        var totalDevice: Int?,

        )
}

