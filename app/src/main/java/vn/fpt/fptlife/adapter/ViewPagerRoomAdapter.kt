package vn.fpt.fptlife.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import vn.fpt.fptlife.fragment.AllDeviceFragment
import vn.fpt.fptlife.model.RoomResponse

class ViewPagerRoomAdapter(
    fm: FragmentManager,
    behavior: Int,
    private val listRoom: List<RoomResponse.Room>?,
    var houseId: String
) :
    FragmentStatePagerAdapter(fm, behavior) {
    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            AllDeviceFragment().allDeviceInstance(houseId)
        } else {
            AllDeviceFragment().deviceOfRoomInstance(houseId, listRoom!![position].id)
        }
    }

    override fun getCount(): Int {
        return listRoom?.size ?: 0
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return listRoom!![position].name
    }
}
