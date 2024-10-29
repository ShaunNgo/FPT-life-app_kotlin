package vn.fpt.fptlife.presenter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vn.fpt.fptlife.api.ApiService
import vn.fpt.fptlife.model.GetAllDeviceHomeIndexResponse
import vn.fpt.fptlife.my_interface.AllDeviceOfCateActivityView

class AllDeviceOfCateActivityPresenter(allDeviceOfCateActivityView: AllDeviceOfCateActivityView) {
    private val mAllDeviceOfCateActivityView: AllDeviceOfCateActivityView = allDeviceOfCateActivityView
    private lateinit var mGetAllDeviceHomeIndexResponse: GetAllDeviceHomeIndexResponse

    fun callApiGetDeviceOfRoom(url: String?) {
        ApiService.apiService.getAllDeviceHomeIndex(url)
            .enqueue(object : Callback<GetAllDeviceHomeIndexResponse?> {
                override fun onResponse(call: Call<GetAllDeviceHomeIndexResponse?>, response: Response<GetAllDeviceHomeIndexResponse?>) {
                    if (response.body() != null) {
                        mGetAllDeviceHomeIndexResponse = response.body()!!
                        mAllDeviceOfCateActivityView.callApiSuccess(mGetAllDeviceHomeIndexResponse, url!!)
                    }
                }

                override fun onFailure(
                    call: Call<GetAllDeviceHomeIndexResponse?>, throwable: Throwable) {
                }
            })
    }
}
