package vn.fpt.fptlife.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import vn.fpt.fptlife.R
import vn.fpt.fptlife.activity.AllDeviceOfCateActivity
import vn.fpt.fptlife.adapter.DeviceCategoryAdapter
import vn.fpt.fptlife.databinding.FragmentAllDeviceBinding
import vn.fpt.fptlife.model.DeviceResponse
import vn.fpt.fptlife.model.DeviceResponse.DataDevice
import vn.fpt.fptlife.my_interface.AllDeviceFragmentView
import vn.fpt.fptlife.my_interface.IClickItemGetAllDeviceListener
import vn.fpt.fptlife.my_interface.IClickItemListener
import vn.fpt.fptlife.presenter.AllDeviceFragmentPresenter


class AllDeviceFragment : Fragment(), AllDeviceFragmentView {
    private val ARG_PARAM1 = "param1"
    private val ARG_PARAM2 = "param2"
    private var houseId: String? = null
    private var roomId: String? = null
    private lateinit var presenter: AllDeviceFragmentPresenter
    private lateinit var binding: FragmentAllDeviceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            houseId = it.getString(ARG_PARAM1)
            roomId = it.getString(ARG_PARAM2)
            presenter = AllDeviceFragmentPresenter(this)
            presenter.callApiGetDeviceOfRoom(houseId, roomId)

        }
    }

    fun allDeviceInstance(houseId: String?) =
        AllDeviceFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, houseId)
            }
        }

    fun deviceOfRoomInstance(houseId: String?, roomId: String?) =
        AllDeviceFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, houseId)
                putString(ARG_PARAM2, roomId)
            }
        }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllDeviceBinding.inflate(inflater, container, false)
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvDevice.layoutManager = linearLayoutManager
        return binding.root
    }

    private fun onClickShowPopup(position: Int, button: ImageButton) {
        val popupMenu = PopupMenu(context, button)
        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            val itemId = item.itemId
            when (itemId) {
                R.id.action_popup_fav -> {
                    Toast.makeText(context, "fav $position", Toast.LENGTH_SHORT).show()
                }

                R.id.action_popup_edit -> {
                    Toast.makeText(context, "edit $position", Toast.LENGTH_SHORT).show()
                }

                else -> {
                    Toast.makeText(context, "Unknown menu item selected", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
        popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
        try {
            val fieldMPopup = PopupMenu::class.java.getDeclaredField("mPopup")
            fieldMPopup.isAccessible = true
            val mPopup = fieldMPopup[popupMenu]
            val setMethod = mPopup.javaClass.getDeclaredMethod(
                "setForceShowIcon",
                Boolean::class.javaPrimitiveType
            )
            setMethod.invoke(mPopup, true)
        } catch (e: Exception) {
            Log.e("Main", "Error", e)
        } finally {
            popupMenu.show()
        }
    }

    fun onClickGoToGetAll(textView: TextView, device: DataDevice) {
        val intent = Intent(context, AllDeviceOfCateActivity::class.java)
        val bundle = Bundle()
        bundle.putString("object_device", Gson().toJson(device))
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun callApiSuccess(
        deviceResponse: DeviceResponse?,
        houseId: String?,
        roomId: String?
    ) {
        if (deviceResponse != null) {
            val deviceCategoryAdapter =
                DeviceCategoryAdapter(context, deviceResponse, object : IClickItemListener {
                    override fun onClickItem(position: Int?, btn: ImageButton?) {
                        onClickShowPopup(position!!,btn!!)
                    }
                }, object : IClickItemGetAllDeviceListener {
                    override fun onClickItemGetAll(tv: TextView?, deviceResponse: DataDevice?) {
                        onClickGoToGetAll(tv!!, deviceResponse!!)
                    }
                })
            binding.rvDevice.setAdapter(deviceCategoryAdapter)
        }
    }

    override fun callApiFailure() {
        Toast.makeText(context, "Call Api Failure", Toast.LENGTH_SHORT).show()
    }

}