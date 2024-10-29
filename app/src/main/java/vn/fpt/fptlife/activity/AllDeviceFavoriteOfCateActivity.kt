package vn.fpt.fptlife.activity

import android.graphics.Color
import android.os.Bundle
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
import vn.fpt.fptlife.adapter.AllDeviceFavoriteCategoryAdapter
import vn.fpt.fptlife.databinding.ActivityAllDeviceFavoriteOfCateBinding
import vn.fpt.fptlife.model.DeviceResponse.DataDevice
import vn.fpt.fptlife.model.GetAllDeviceHomeIndexResponse
import vn.fpt.fptlife.my_interface.AllDeviceOfCateActivityView
import vn.fpt.fptlife.presenter.AllDeviceOfCateActivityPresenter

class AllDeviceFavoriteOfCateActivity : AppCompatActivity(), AllDeviceOfCateActivityView {
    private lateinit var binding: ActivityAllDeviceFavoriteOfCateBinding
    private lateinit var presenter: AllDeviceOfCateActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAllDeviceFavoriteOfCateBinding.inflate(layoutInflater)
        presenter = AllDeviceOfCateActivityPresenter(this)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.toolbar2.imgbBack.setOnClickListener { v -> onBackPressed() }
        binding.toolbar2.tvSave.setTextColor(Color.rgb(254, 89, 42))
        binding.toolbar2.tvTitle.text = "Xem tất cả"

        val bundle = intent.extras ?: return
        val keyDevice = bundle.getString("object_device")
        if (intent != null) {
            val deviceData =
                Gson().fromJson<DataDevice>(keyDevice, object : TypeToken<DataDevice?>() {}.type)
            presenter.callApiGetDeviceOfRoom(deviceData.uriGetAllData)
        }

        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvAllDeviceFavorite.setLayoutManager(linearLayoutManager)
    }

    override fun callApiSuccess(
        getAllDeviceHomeIndexResponse: GetAllDeviceHomeIndexResponse, url: String
    ) {
        val allDeviceFavoriteCategoryAdapter = AllDeviceFavoriteCategoryAdapter(applicationContext, getAllDeviceHomeIndexResponse)
        binding.rvAllDeviceFavorite.setAdapter(allDeviceFavoriteCategoryAdapter)
    }

    override fun callApiFailure() {
        Toast.makeText(this, "Call Api Failure", Toast.LENGTH_SHORT).show()
    }
}