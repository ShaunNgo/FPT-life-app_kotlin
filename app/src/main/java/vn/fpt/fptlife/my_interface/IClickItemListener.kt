package vn.fpt.fptlife.my_interface

import android.widget.ImageButton

interface IClickItemListener {
    fun onClickItem(position: Int?, btn: ImageButton?)
}
