package vn.fpt.fptlife.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import vn.fpt.fptlife.R
import vn.fpt.fptlife.activity.HomeActivity.Companion.mHomePosition
import vn.fpt.fptlife.activity.HouseManageActivity
import vn.fpt.fptlife.adapter.CardWeatherAdapter
import vn.fpt.fptlife.adapter.HouseAdapter
import vn.fpt.fptlife.adapter.ViewPagerRoomAdapter
import vn.fpt.fptlife.databinding.FragmentHomeBinding
import vn.fpt.fptlife.model.CardWeather
import vn.fpt.fptlife.model.House
import vn.fpt.fptlife.model.RoomResponse
import vn.fpt.fptlife.my_interface.HomeFragmentView
import vn.fpt.fptlife.presenter.HomeFragmentPresenter


class HomeFragment : Fragment(), HomeFragmentView {
    private val ARG_PARAM1 = "ARG_PARAM1"
    private val ARG_PARAM2 = "ARG_PARAM2"
    private var mJsonHouse: String? = null
    private var mPosition: Int? = null
    private lateinit var houseAdapter: HouseAdapter
    private lateinit var mHouses: List<House>
    private lateinit var presenter: HomeFragmentPresenter
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mJsonHouse = it.getString(ARG_PARAM1)
            mPosition = it.getInt(ARG_PARAM2)
            mHouses = Gson().fromJson(mJsonHouse, object : TypeToken<List<House>>() {}.type)
        }
    }

    fun homeInstance(jsonHouse: String, position: Int): HomeFragment {
        return HomeFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, jsonHouse)
                putInt(ARG_PARAM2, position)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        presenter = HomeFragmentPresenter(this)

        binding.spnHouse.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, i: Int, id: Long) {
                mHomePosition = i
                presenter.callApiGetRoom(houseAdapter.getListHouse()[i].id)
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }

        binding.imgbManageHouse.setOnClickListener {
            val i = Intent(context, HouseManageActivity::class.java)
            startActivity(i)
        }

        setHouseAdapter(mHouses)
        binding.spnHouse.setSelection(mHomePosition)

        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvCardWeather.layoutManager = linearLayoutManager

        val cardWeatherAdapter = CardWeatherAdapter(context)
        cardWeatherAdapter.setData(getCardWeather())
        binding.rvCardWeather.adapter = cardWeatherAdapter

        return binding.root
    }

    fun setHouseAdapter(listHose: List<House>) {
        houseAdapter = HouseAdapter(context, R.layout.item_selected_house, listHose)
        binding.spnHouse.adapter = houseAdapter
    }

    fun getCardWeather(): CardWeather {
        return CardWeather("TP Hồ Chí Minh", "27°C", "49%", "1/1")
    }


    override fun callApiSuccess(listRoom: List<RoomResponse.Room>, houseId: String) {
        val room = RoomResponse.Room("Tất cả thiết bị",null,null,null,null,null,null,null,null,null)
        val newList = mutableListOf<RoomResponse.Room>().apply {
            add(0,room)
            addAll(listRoom)
        }
        if (newList.isNotEmpty()) {
            val mViewPagerRoomAdapter = ViewPagerRoomAdapter(
                requireActivity().supportFragmentManager,
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
                newList,
                houseId
            )
            binding.viewPager.adapter = mViewPagerRoomAdapter
            binding.tabLayout.setupWithViewPager(binding.viewPager)
        }
    }

    override fun callApiFailure() {
        Toast.makeText(context, "Call Api Failure", Toast.LENGTH_SHORT).show()
    }
}