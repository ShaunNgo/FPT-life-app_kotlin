package vn.fpt.fptlife.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import vn.fpt.fptlife.databinding.ItemHouseBinding
import vn.fpt.fptlife.databinding.ItemSelectedHouseBinding
import vn.fpt.fptlife.model.House

class HouseAdapter(context: Context?, resource: Int, listHouse: List<House>) :
    ArrayAdapter<House>(context!!, resource, listHouse) {

    private var listHouse: List<House>

    init {
        this.listHouse = listHouse
    }

    fun getListHouse(): List<House> {
        return listHouse
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        @SuppressLint("ViewHolder")
        val itemSelectedHouseBinding =
            ItemSelectedHouseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val house = getItem(position)
        if (house != null) {
            itemSelectedHouseBinding.tvSelected.text = house.name
        }
        return itemSelectedHouseBinding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemHouseBinding =
            ItemHouseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val house = getItem(position)
        if (house != null) {
            itemHouseBinding.tvHouse.text = house.name
        }
        return itemHouseBinding.root
    }
}
