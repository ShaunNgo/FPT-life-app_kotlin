package vn.fpt.fptlife.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import vn.fpt.fptlife.R
import vn.fpt.fptlife.adapter.DeviceCategoryCheckboxAdapter
import vn.fpt.fptlife.databinding.ActivityDeviceAddFavoriteBinding
import vn.fpt.fptlife.model.DeviceResponse
import vn.fpt.fptlife.model.DeviceResponse.DataDevice
import vn.fpt.fptlife.model.House
import vn.fpt.fptlife.my_interface.DeviceAddFavoriteActivityView
import vn.fpt.fptlife.my_interface.IClickItemGetAllDeviceListener
import vn.fpt.fptlife.presenter.DeviceAddFavoriteActivityPresenter

class DeviceAddFavoriteActivity : AppCompatActivity(), DeviceAddFavoriteActivityView {
    private lateinit var presenter: DeviceAddFavoriteActivityPresenter
    private lateinit var binding: ActivityDeviceAddFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        presenter = DeviceAddFavoriteActivityPresenter(this)
        binding = ActivityDeviceAddFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.toolbar2.imgbBack.setOnClickListener { onBackPressed() }
        binding.toolbar2.tvSave.setTextColor(Color.rgb(254, 89, 42))
        binding.toolbar2.tvTitle.text = "Thêm yêu thích"

        val bundle = intent.extras ?: return
        val keyHouse = bundle.getString("object_houses")

        if (intent != null) {
            val house = Gson().fromJson<House>(keyHouse, object : TypeToken<House?>() {}.type)

            if (house.id != null) {
                presenter.callApiGetDeviceFav(house.id)
            }
        }

        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvDeviceUnchecked.setLayoutManager(linearLayoutManager)
    }

    fun onClickGoToGetAllDevice(textView: TextView, device: DataDevice) {
        val intent = Intent(this, AllDeviceFavoriteOfCateActivity::class.java)
        val bundle = Bundle()
        bundle.putString("object_device", Gson().toJson(device))
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun callApiSuccess(deviceResponse: DeviceResponse?, houseId: String?) {
        val deviceCategoryCheckboxAdapter = DeviceCategoryCheckboxAdapter(
            this,
            deviceResponse,
            object : IClickItemGetAllDeviceListener {
                override fun onClickItemGetAll(tv: TextView?, deviceResponse: DataDevice?) {
                    onClickGoToGetAllDevice(tv!!, deviceResponse!!)
                }
            })

        binding.rvDeviceUnchecked.setAdapter(deviceCategoryCheckboxAdapter)
    }

    override fun callApiFailure() {
        Toast.makeText(this, "Call Api Failure", Toast.LENGTH_SHORT).show()
    }
}