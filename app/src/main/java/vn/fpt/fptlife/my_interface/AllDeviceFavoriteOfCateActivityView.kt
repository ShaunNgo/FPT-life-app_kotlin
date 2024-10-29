package vn.fpt.fptlife.my_interface

import vn.fpt.fptlife.model.GetAllDeviceHomeIndexResponse

interface AllDeviceFavoriteOfCateActivityView {
    fun callApiSuccess(getAllDeviceHomeIndexResponse: GetAllDeviceHomeIndexResponse?, url: String?)
    fun callApiFailure()
}
