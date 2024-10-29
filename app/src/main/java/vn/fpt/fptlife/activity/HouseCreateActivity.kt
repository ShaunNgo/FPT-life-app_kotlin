package vn.fpt.fptlife.activity

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import vn.fpt.fptlife.R
import vn.fpt.fptlife.databinding.ActivityHouseCreateBinding
import vn.fpt.fptlife.databinding.LayoutDialogInputBinding
import vn.fpt.fptlife.databinding.LayoutDialogLocationBinding
import vn.fpt.fptlife.databinding.LayoutDialogSuccessBinding

class HouseCreateActivity : AppCompatActivity() {
    private lateinit var mActivityHouseCreateBinding: ActivityHouseCreateBinding
    private lateinit var mLayoutDialogInputBinding: LayoutDialogInputBinding
    private lateinit var mLayoutDialogLocationBinding: LayoutDialogLocationBinding
    private lateinit var mLayoutDialogSuccessBinding: LayoutDialogSuccessBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        mActivityHouseCreateBinding = ActivityHouseCreateBinding.inflate(layoutInflater)
        setContentView(mActivityHouseCreateBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        mActivityHouseCreateBinding.toolbar2.imgbBack.setOnClickListener { clickBack() }
        mActivityHouseCreateBinding.toolbar2.tvSave.setOnClickListener {
            openSuccessDialog(
                Gravity.CENTER
            )
        }
        mActivityHouseCreateBinding.toolbar2.tvTitle.text = "Tạo nhà mới"
        mActivityHouseCreateBinding.llInputHouseName.setOnClickListener {
            openInputDialog(
                Gravity.CENTER
            )
        }
        mActivityHouseCreateBinding.llInputHouseLocation.setOnClickListener {
            openLocationDialog(
                Gravity.BOTTOM
            )
        }
    }

    fun clickBack() {
        val i = Intent(this, HouseManageActivity::class.java)
        startActivity(i)
        finish()
    }

    fun openInputDialog(gravity: Int) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        mLayoutDialogInputBinding = LayoutDialogInputBinding.inflate(layoutInflater)
        dialog.setContentView(mLayoutDialogInputBinding.root)
        val window = dialog.window ?: return
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //xác định vị trí dialog
        val windowAttributes = window.attributes
        windowAttributes.gravity = gravity
        window.setAttributes(windowAttributes)
        mLayoutDialogInputBinding.btnCancelInput.setOnClickListener { dialog.dismiss() }
        mLayoutDialogInputBinding.btnAccept.setOnClickListener {
            val houseName = mLayoutDialogInputBinding.edtInputName.text.toString()
            mActivityHouseCreateBinding.tvInputHouseName.text = houseName
            dialog.dismiss()
        }
        dialog.show()
    }

    fun openLocationDialog(gravity: Int) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        mLayoutDialogLocationBinding = LayoutDialogLocationBinding.inflate(layoutInflater)
        dialog.setContentView(mLayoutDialogLocationBinding.root)
        val window = dialog.window ?: return
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //xác định vị trí dialog
        val windowAttributes = window.attributes
        windowAttributes.gravity = gravity
        window.setAttributes(windowAttributes)

        //number picker
        val places: Array<String> = getResources().getStringArray(R.array.locations)
        mLayoutDialogLocationBinding.npLocation.setMinValue(0)
        mLayoutDialogLocationBinding.npLocation.setMaxValue(60)
        mLayoutDialogLocationBinding.npLocation.setDisplayedValues(places)
        mActivityHouseCreateBinding.tvInputHouseLocation.text = places[mLayoutDialogLocationBinding.npLocation.value]
        mLayoutDialogLocationBinding.npLocation.setOnValueChangedListener { picker, oldVal, newVal ->
            mActivityHouseCreateBinding.tvInputHouseLocation.text = places[newVal]
            val color = Color.WHITE
            mActivityHouseCreateBinding.tvInputHouseLocation.setTextColor(color)
        }
        mLayoutDialogLocationBinding.imgbClose.setOnClickListener { dialog.dismiss() }
        mLayoutDialogLocationBinding.btnAccept.setOnClickListener { dialog.dismiss() }
        //hiển thị dialog
        dialog.show()
    }

    fun openSuccessDialog(gravity: Int) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        mLayoutDialogSuccessBinding = LayoutDialogSuccessBinding.inflate(layoutInflater)
        dialog.setContentView(mLayoutDialogSuccessBinding.root)
        val window = dialog.window ?: return
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //xác định vị trí dialog
        val windowAttributes = window.attributes
        windowAttributes.gravity = gravity
        window.setAttributes(windowAttributes)
        mLayoutDialogSuccessBinding.btnSuccess.setOnClickListener {
            dialog.dismiss()
            val i = Intent(this, HouseManageActivity::class.java)
            startActivity(i)
            finish()
        }
        dialog.show()
    }
}