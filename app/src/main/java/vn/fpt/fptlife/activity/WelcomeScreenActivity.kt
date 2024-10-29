package vn.fpt.fptlife.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import vn.fpt.fptlife.R
import vn.fpt.fptlife.adapter.ViewPagerWelcomeAdapter
import vn.fpt.fptlife.databinding.ActivityWelcomeScreenBinding

class WelcomeScreenActivity : AppCompatActivity() {
    private lateinit var viewPagerAdapter: ViewPagerWelcomeAdapter
    private lateinit var binding: ActivityWelcomeScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        binding = ActivityWelcomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.imgbNext.setOnClickListener {
            if (getItem(0) < 3) {
                binding.slideViewPager.setCurrentItem(getItem(1), true)
            } else {
                val i = Intent(this, HouseManageActivity::class.java)
                startActivity(i)
                finish()
            }
        }

        binding.tvSkip.setOnClickListener {
            val i = Intent(this, HouseManageActivity::class.java)
            startActivity(i)
            finish()
        }

        setUpIndicator(0)
        binding.slideViewPager.addOnPageChangeListener(viewListener)
        viewPagerAdapter = ViewPagerWelcomeAdapter(this)
        binding.slideViewPager.setAdapter(viewPagerAdapter)
    }

    private fun getItem(i: Int): Int {
        return binding.slideViewPager.currentItem + i
    }

    fun setUpIndicator(position: Int) {
        val dots = arrayOfNulls<ImageView>(3)
        binding.indicatorLayout.removeAllViews()
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(8, 0, 8, 0)
        for (i in dots.indices) {
            dots[i] = ImageView(this)
            dots[i]!!.setImageResource(R.drawable.indicatorinactive)
            dots[i]!!.setLayoutParams(params)
            binding.indicatorLayout.addView(dots[i])
        }
        dots[position]!!.setImageResource(R.drawable.indicatoractive)
    }

    var viewListener: OnPageChangeListener = object : OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

        override fun onPageSelected(position: Int) { setUpIndicator(position) }

        override fun onPageScrollStateChanged(state: Int) {}
    }
}