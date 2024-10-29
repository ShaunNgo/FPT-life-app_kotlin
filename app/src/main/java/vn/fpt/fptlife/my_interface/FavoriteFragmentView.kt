package vn.fpt.fptlife.my_interface

import vn.fpt.fptlife.model.DeviceResponse

interface FavoriteFragmentView {
    fun callApiSuccess(deviceResponse: DeviceResponse?, houseId: String?)
    fun callApiFailure()
}
