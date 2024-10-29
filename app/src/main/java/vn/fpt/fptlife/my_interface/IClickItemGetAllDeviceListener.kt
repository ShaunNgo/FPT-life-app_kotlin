package vn.fpt.fptlife.my_interface

import android.widget.TextView
import vn.fpt.fptlife.model.DeviceResponse.DataDevice

interface IClickItemGetAllDeviceListener {
    fun onClickItemGetAll(tv: TextView?, deviceResponse: DataDevice?){}
}
