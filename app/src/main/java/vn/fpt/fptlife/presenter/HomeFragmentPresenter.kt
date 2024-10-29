package vn.fpt.fptlife.presenter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vn.fpt.fptlife.api.ApiService
import vn.fpt.fptlife.model.RoomResponse
import vn.fpt.fptlife.my_interface.HomeFragmentView

class HomeFragmentPresenter(homeFragmentView: HomeFragmentView) {
    private val mHomeFragmentView: HomeFragmentView = homeFragmentView
    private lateinit var mListRoom: List<RoomResponse.Room>

    fun callApiGetRoom(houseId: String?) {
        ApiService.apiService.getRoom(20, 1, houseId).enqueue(object : Callback<RoomResponse?> {
            override fun onResponse(call: Call<RoomResponse?>, response: Response<RoomResponse?>) {
                if (response.body() != null) {
                    mListRoom = response.body()!!.data.rooms!!
                    mHomeFragmentView.callApiSuccess(mListRoom, houseId!!)
                }
            }

            override fun onFailure(call: Call<RoomResponse?>, throwable: Throwable) {
                mHomeFragmentView.callApiFailure()
            }
        })
    }
}