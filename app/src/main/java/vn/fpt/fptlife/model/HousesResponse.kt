package vn.fpt.fptlife.model

import com.google.gson.annotations.SerializedName

data class HousesResponse(
    var status: Int,
    var message: String,
    var time: String,
    @SerializedName("data")
    var housesData: HousesData
) {
    inner class HousesData(
        var total: Int,
        var currentPage: Int,
        var lastPage: Int,
        var limit: Int,
        var houses: List<House>
    )
}
