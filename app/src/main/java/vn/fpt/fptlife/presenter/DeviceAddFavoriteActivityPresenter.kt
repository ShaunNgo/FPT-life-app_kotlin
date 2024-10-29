package vn.fpt.fptlife.presenter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vn.fpt.fptlife.api.ApiService
import vn.fpt.fptlife.model.DeviceResponse
import vn.fpt.fptlife.my_interface.DeviceAddFavoriteActivityView

class DeviceAddFavoriteActivityPresenter(deviceAddFavoriteActivityView: DeviceAddFavoriteActivityView) {
    private var mDeviceAddFavoriteActivityView: DeviceAddFavoriteActivityView = deviceAddFavoriteActivityView
    private lateinit var mDeviceResponse: DeviceResponse

    fun callApiGetDeviceFav(houseId: String?) {
        ApiService.apiService.getDevice(houseId)!!.enqueue(object : Callback<DeviceResponse?> {
            override fun onResponse(call: Call<DeviceResponse?>, response: Response<DeviceResponse?>) {
                if (response.body() != null) {
                    mDeviceResponse = response.body()!!
                    mDeviceAddFavoriteActivityView.callApiSuccess(mDeviceResponse, houseId)
                }
            }

            override fun onFailure(call: Call<DeviceResponse?>, throwable: Throwable) {}
        })
    }
}
