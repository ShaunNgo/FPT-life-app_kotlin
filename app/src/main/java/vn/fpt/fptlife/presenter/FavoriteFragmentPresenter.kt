package vn.fpt.fptlife.presenter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vn.fpt.fptlife.api.ApiService
import vn.fpt.fptlife.model.DeviceResponse
import vn.fpt.fptlife.my_interface.FavoriteFragmentView

class FavoriteFragmentPresenter(favoriteFragmentView: FavoriteFragmentView) {
    private val mFavoriteFragmentView: FavoriteFragmentView = favoriteFragmentView
    private lateinit var mDeviceResponse: DeviceResponse

    fun callApiGetDeviceFav(houseId: String) {
        ApiService.apiService.getDeviceFavorite(houseId, 1)
            .enqueue(object : Callback<DeviceResponse?> {
                override fun onResponse(
                    call: Call<DeviceResponse?>,
                    response: Response<DeviceResponse?>
                ) {
                    if (response.body() != null) {
                        mDeviceResponse = response.body()!!
                        mFavoriteFragmentView.callApiSuccess(mDeviceResponse, houseId)
                    }
                }

                override fun onFailure(call: Call<DeviceResponse?>, response: Throwable) {
                    mFavoriteFragmentView.callApiFailure()
                }

            })
    }
}