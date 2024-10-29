package vn.fpt.fptlife.presenter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vn.fpt.fptlife.activity.HouseManageActivity
import vn.fpt.fptlife.api.ApiService
import vn.fpt.fptlife.model.House
import vn.fpt.fptlife.model.HousesResponse
import vn.fpt.fptlife.my_interface.HouseManageActivityView

class HouseManageActivityPresenter(houseManageView: HouseManageActivityView) {
    private var mHouseManageView: HouseManageActivityView = houseManageView
    private lateinit var mListHouse: List<House>

    fun callApiGetAllHouse() {
        ApiService.apiService.getAllHouse().enqueue(object : Callback<HousesResponse?> {
            override fun onResponse(call: Call<HousesResponse?>, response: Response<HousesResponse?>) {
                if (response.body() != null) {
                    mListHouse = response.body()!!.housesData.houses
                    mHouseManageView.callApiSuccess(mListHouse)
                }
            }

            override fun onFailure(call: Call<HousesResponse?>, throwable: Throwable) {
                mHouseManageView.callApiFailure()
            }
        })
    }
}