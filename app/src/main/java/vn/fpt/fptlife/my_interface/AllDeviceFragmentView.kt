package vn.fpt.fptlife.my_interface

import vn.fpt.fptlife.model.DeviceResponse

interface AllDeviceFragmentView {
    fun callApiSuccess(deviceResponse: DeviceResponse?, houseId: String?, roomId: String?)
    fun callApiFailure()
}
