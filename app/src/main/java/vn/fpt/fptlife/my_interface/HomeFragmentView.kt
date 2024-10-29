package vn.fpt.fptlife.my_interface

import vn.fpt.fptlife.model.RoomResponse

interface HomeFragmentView {
    fun callApiSuccess(listRoom: List<RoomResponse.Room>, houseId: String)
    fun callApiFailure()
}