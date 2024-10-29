package vn.fpt.fptlife.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import vn.fpt.fptlife.R
import vn.fpt.fptlife.adapter.HouseManageAdapter
import vn.fpt.fptlife.databinding.ActivityHouseManageBinding
import vn.fpt.fptlife.model.House
import vn.fpt.fptlife.my_interface.HouseManageActivityView
import vn.fpt.fptlife.presenter.HouseManageActivityPresenter

class HouseManageActivity : AppCompatActivity(), HouseManageActivityView {
    private lateinit var mPresenter: HouseManageActivityPresenter
    private lateinit var mBinding: ActivityHouseManageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mBinding = ActivityHouseManageBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mBinding.toolbar1.tvTitle.text = "Quản lý nhà"
        mBinding.toolbar1.imgbBack.setOnClickListener { onBackPressed() }

        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        mBinding.rvHouse.setLayoutManager(linearLayoutManager)

        mPresenter = HouseManageActivityPresenter(this)
        mPresenter.callApiGetAllHouse()

        mBinding.llCreateHouse.setOnClickListener {
            val i = Intent(this, HouseCreateActivity::class.java)
            startActivity(i)
        }
    }

    private fun onClickGoToHomePage(listHouse: List<House>, position: Int) {
        val intent = Intent(this, HomeActivity::class.java)
        val bundle = Bundle()
        bundle.putString("object_house", Gson().toJson(listHouse))
        bundle.putInt("house_position", position)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun callApiSuccess(listHouse: List<House>) {
        val houseManageAdapter = HouseManageAdapter(listHouse) { house: House?, position: Int -> onClickGoToHomePage(listHouse, position) }
        mBinding.rvHouse.setAdapter(houseManageAdapter)
    }

    override fun callApiFailure() {
        Toast.makeText(this, "Call Api Failure", Toast.LENGTH_SHORT).show()
    }
}