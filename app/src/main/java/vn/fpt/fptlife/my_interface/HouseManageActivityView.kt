package vn.fpt.fptlife.my_interface

import vn.fpt.fptlife.model.House

interface HouseManageActivityView {
    fun callApiSuccess(listHouse: List<House>)
    fun callApiFailure()
}