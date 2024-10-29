package vn.fpt.fptlife.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import vn.fpt.fptlife.R
import vn.fpt.fptlife.databinding.ActivityHomeBinding
import vn.fpt.fptlife.fragment.AccountFragment
import vn.fpt.fptlife.fragment.FavoriteFragment
import vn.fpt.fptlife.fragment.HomeFragment
import vn.fpt.fptlife.fragment.SceneFragment
import vn.fpt.fptlife.fragment.SecurityFragment


class HomeActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityHomeBinding
    lateinit var mHouse: String

    companion object {
        var mHomePosition = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bundle = intent.extras ?: return
        mHouse = bundle.getString("object_house")!!
        mHomePosition = bundle.getInt("house_position")

        mBinding.bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            val itemId: Int = menuItem.getItemId()
            if (itemId == R.id.navHome) {
                loadFragment(HomeFragment().homeInstance(mHouse, mHomePosition), false)
            } else if (itemId == R.id.navContext) {
                loadFragment(SceneFragment(), false)
            } else if (itemId == R.id.navFav) {
                loadFragment(FavoriteFragment().favInstance(mHouse, mHomePosition), false)
            } else if (itemId == R.id.navSecurity) {
                loadFragment(SecurityFragment(), false)
            } else {
                loadFragment(AccountFragment(), false)
            }
            true
        }
        loadFragment(HomeFragment().homeInstance(mHouse, mHomePosition), true)
    }

    private fun loadFragment(fragment: Fragment, isAppInitialized: Boolean) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        if (isAppInitialized) {
            fragmentTransaction.add(R.id.frameLayout, fragment)
        } else {
            fragmentTransaction.replace(R.id.frameLayout, fragment)
        }
        fragmentTransaction.commit()
    }
}