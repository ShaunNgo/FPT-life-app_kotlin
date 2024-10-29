package vn.fpt.fptlife.activity

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.PopupMenu
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
import vn.fpt.fptlife.adapter.AllDeviceCategoryAdapter
import vn.fpt.fptlife.databinding.ActivityAllDeviceOfCateBinding
import vn.fpt.fptlife.model.DeviceResponse.DataDevice
import vn.fpt.fptlife.model.GetAllDeviceHomeIndexResponse
import vn.fpt.fptlife.my_interface.AllDeviceOfCateActivityView
import vn.fpt.fptlife.my_interface.IClickItemListener
import vn.fpt.fptlife.presenter.AllDeviceOfCateActivityPresenter

class AllDeviceOfCateActivity : AppCompatActivity(), AllDeviceOfCateActivityView {
    private lateinit var binding: ActivityAllDeviceOfCateBinding
    private lateinit var presenter: AllDeviceOfCateActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAllDeviceOfCateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.toolbar1.tvTitle.text = "Xem tất cả"
        binding.toolbar1.imgbBack.setOnClickListener { onBackPressed() }

        presenter = AllDeviceOfCateActivityPresenter(this)
        val bundle = intent.extras ?: return
        val keyDevice = bundle.getString("object_device")

        if (intent != null) {
            val deviceData =
                Gson().fromJson<DataDevice>(keyDevice, object : TypeToken<DataDevice?>() {}.type)
            presenter.callApiGetDeviceOfRoom(deviceData.uriGetAllData)
        }

        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvAllDevice.setLayoutManager(linearLayoutManager)
    }

    private fun onClickShowPopup(position: Int, button: ImageButton) {
        val popupMenu = PopupMenu(applicationContext, button)
        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            val itemId = item.itemId
            if (itemId == R.id.action_popup_fav) {
                Toast.makeText(applicationContext, "fav $position", Toast.LENGTH_SHORT).show()
            } else if (itemId == R.id.action_popup_edit) {
                Toast.makeText(applicationContext, "edit $position", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    applicationContext,
                    "Unknown menu item selected",
                    Toast.LENGTH_SHORT
                ).show()
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

    override fun callApiSuccess(
        getAllDeviceHomeIndexResponse: GetAllDeviceHomeIndexResponse, url: String
    ) {
        val allDeviceCategoryAdapter = AllDeviceCategoryAdapter(
            applicationContext,
            getAllDeviceHomeIndexResponse,
            object : IClickItemListener {
                override fun onClickItem(position: Int?, btn: ImageButton?) {
                    onClickShowPopup(position!!, btn!!)
                }
            })
        binding.rvAllDevice.adapter = allDeviceCategoryAdapter
    }

    override fun callApiFailure() {
        Toast.makeText(this, "Call Api Failure", Toast.LENGTH_SHORT).show()
    }
}