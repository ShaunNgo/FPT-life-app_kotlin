package vn.fpt.fptlife.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import vn.fpt.fptlife.R

class ViewPagerWelcomeAdapter(private var context: Context) : PagerAdapter() {

    private val images = intArrayOf(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3
    )

    private val headings = intArrayOf(
        R.string.heading_one,
        R.string.heading_two,
        R.string.heading_three
    )

    private val descriptions = intArrayOf(
        R.string.desc_one,
        R.string.desc_two,
        R.string.desc_three
    )

    fun ViewPagerWellcomeAdapter(context: Context?) {
        this.context = context!!
    }


    override fun getCount(): Int {
        return headings.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.slider_layout, container, false)
        val slideTitleImage = view.findViewById<ImageView>(R.id.title_Image)
        val slideHeading = view.findViewById<TextView>(R.id.tv_heading)
        val slideDescriptions = view.findViewById<TextView>(R.id.tv_description)

        slideTitleImage.setImageResource(images[position])
        slideHeading.setText(headings[position])
        slideDescriptions.setText(descriptions.get(position))

        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}