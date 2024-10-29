package vn.fpt.fptlife.my_interface

import vn.fpt.fptlife.model.House

fun interface IClickItemHouseListener {
    fun onClickItemHouse(house: House, position: Int)
}