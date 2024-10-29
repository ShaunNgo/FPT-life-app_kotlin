package vn.fpt.fptlife.presenter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vn.fpt.fptlife.api.ApiService
import vn.fpt.fptlife.model.DeviceResponse
import vn.fpt.fptlife.my_interface.AllDeviceFragmentView

class AllDeviceFragmentPresenter(allDeviceFragmentView: AllDeviceFragmentView) {
    private val mAllDeviceFragmentView: AllDeviceFragmentView = allDeviceFragmentView
    private lateinit var mDeviceResponse: DeviceResponse

    fun callApiGetDeviceOfRoom(houseId: String?, roomId: String?) {
        ApiService.apiService.getDeviceOfRoom(houseId, roomId)
            .enqueue(object : Callback<DeviceResponse?> {
                override fun onResponse(call: Call<DeviceResponse?>, response: Response<DeviceResponse?>) {
                    if (response.body() != null) {
                        mDeviceResponse = response.body()!!
                        mAllDeviceFragmentView.callApiSuccess(mDeviceResponse, houseId, roomId)
                    }
                }

                override fun onFailure(call: Call<DeviceResponse?>, throwable: Throwable) {
                    mAllDeviceFragmentView.callApiFailure()
                }
            })
    }
}
