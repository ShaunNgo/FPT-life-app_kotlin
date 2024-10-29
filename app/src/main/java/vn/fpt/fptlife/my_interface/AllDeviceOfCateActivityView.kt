package vn.fpt.fptlife.my_interface

import vn.fpt.fptlife.model.GetAllDeviceHomeIndexResponse

interface AllDeviceOfCateActivityView {
    fun callApiSuccess(getAllDeviceHomeIndexResponse: GetAllDeviceHomeIndexResponse, url: String)
    fun callApiFailure()
}