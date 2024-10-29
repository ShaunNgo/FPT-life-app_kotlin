package vn.fpt.fptlife.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import vn.fpt.fptlife.R
import vn.fpt.fptlife.activity.AllDeviceOfCateActivity
import vn.fpt.fptlife.activity.DeviceAddFavoriteActivity
import vn.fpt.fptlife.activity.HomeActivity
import vn.fpt.fptlife.activity.HouseManageActivity
import vn.fpt.fptlife.adapter.DeviceCategoryAdapter
import vn.fpt.fptlife.adapter.HouseAdapter
import vn.fpt.fptlife.databinding.FragmentFavoriteBinding
import vn.fpt.fptlife.model.DeviceResponse
import vn.fpt.fptlife.model.DeviceResponse.DataDevice
import vn.fpt.fptlife.model.House
import vn.fpt.fptlife.my_interface.FavoriteFragmentView
import vn.fpt.fptlife.my_interface.IClickItemGetAllDeviceListener
import vn.fpt.fptlife.my_interface.IClickItemListener
import vn.fpt.fptlife.presenter.FavoriteFragmentPresenter

class FavoriteFragment : Fragment(), FavoriteFragmentView {
    private val ARG_PARAM1 = "param1"
    private val ARG_PARAM2 = "param2"
    private var jsonHouse: String? = null
    private var position: Int? = null
    private var houses: List<House>? = null
    private lateinit var presenter: FavoriteFragmentPresenter
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var houseAdapter: HouseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            jsonHouse = it.getString(ARG_PARAM1)
            position = it.getInt(ARG_PARAM2)
            houses = Gson().fromJson(jsonHouse, object : TypeToken<List<House?>?>() {}.type)
        }
    }

    fun favInstance(jsonHouse: String, position: Int) = FavoriteFragment().apply {
        arguments = Bundle().apply {
            putString(ARG_PARAM1, jsonHouse)
            putInt(ARG_PARAM2, position)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        presenter = FavoriteFragmentPresenter(this)
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        binding.spnHouse.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                HomeActivity.mHomePosition = position
                presenter.callApiGetDeviceFav(houseAdapter.getListHouse()[position].id)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.imgbManageHouse.setOnClickListener {
            val intent = Intent(context, HouseManageActivity::class.java)
            startActivity(intent)
        }

        setHouseAdapter(houses!!)
        binding.spnHouse.setSelection(HomeActivity.mHomePosition)


        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvFavDevice.setLayoutManager(linearLayoutManager)

        binding.btnAddFav.setOnClickListener {
            onClickGoToDeviceUnFav(houses!![HomeActivity.mHomePosition])
        }
        return binding.root
    }

    private fun setHouseAdapter(listHose: List<House>) {
        houseAdapter = HouseAdapter(context, R.layout.item_selected_house, listHose)
        binding.spnHouse.adapter = houseAdapter
    }

    private fun onClickGoToDeviceUnFav(house: House) {
        val intent = Intent(context, DeviceAddFavoriteActivity::class.java)
        intent.putExtra("object_houses", Gson().toJson(house))
        startActivity(intent)
    }

    private fun onClickShowPopup(position: Int, button: ImageButton) {
        val popupMenu = PopupMenu(context, button)
        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            val itemId = item.itemId
            if (itemId == R.id.action_popup_unfav) {
                Toast.makeText(context, "unfav $position", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Unknown menu item selected", Toast.LENGTH_SHORT).show()
            }
            true
        }
        popupMenu.menuInflater.inflate(R.menu.popup_menu_unfav, popupMenu.menu)
        try {
            val fieldMPopup = PopupMenu::class.java.getDeclaredField("mPopup")
            fieldMPopup.isAccessible = true
            val mPopup = fieldMPopup[popupMenu]
            val setMethod = mPopup.javaClass.getDeclaredMethod(
                "setForceShowIcon", Boolean::class.javaPrimitiveType
            )
            setMethod.invoke(mPopup, true)
        } catch (e: Exception) {
            Log.e("Main", "Error", e)
        } finally {
            popupMenu.show()
        }
    }

    private fun onClickGoToGetAll(textView: TextView, device: DataDevice) {
        val intent = Intent(
            context, AllDeviceOfCateActivity::class.java
        )
        val bundle = Bundle()
        bundle.putString("object_device", Gson().toJson(device))
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun callApiSuccess(deviceResponse: DeviceResponse?, houseId: String?) {
        val deviceCategoryAdapter =
            DeviceCategoryAdapter(context, deviceResponse!!, object : IClickItemListener {
                override fun onClickItem(position: Int?, btn: ImageButton?) {
                    onClickShowPopup(position!!, btn!!)
                }
            }, object : IClickItemGetAllDeviceListener {
                override fun onClickItemGetAll(tv: TextView?, deviceResponse: DataDevice?) {
                    onClickGoToGetAll(tv!!, deviceResponse!!)
                }
            })
        binding.rvFavDevice.setAdapter(deviceCategoryAdapter)
    }

    override fun callApiFailure() {
        Toast.makeText(context, "Call Api Failure", Toast.LENGTH_SHORT).show()
    }
}