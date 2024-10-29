package vn.fpt.fptlife.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vn.fpt.fptlife.databinding.ItemHouseManageBinding
import vn.fpt.fptlife.model.House
import vn.fpt.fptlife.my_interface.IClickItemHouseListener

class HouseManageAdapter(
    private val mListHouse: List<House>?,
    private val iClickItemHouseListener: IClickItemHouseListener
) :
    RecyclerView.Adapter<HouseManageAdapter.HouseManageHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HouseManageHolder {
        val itemHouseManageBinding =
            ItemHouseManageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HouseManageHolder(itemHouseManageBinding)
    }

    override fun onBindViewHolder(
        holder: HouseManageHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        val house = mListHouse!![position] ?: return
        holder.itemHouseManageBinding.tvHouseManage.text = house.name
        holder.itemHouseManageBinding.layoutItem.setOnClickListener { v ->
            iClickItemHouseListener.onClickItemHouse(
                house,
                position
            )
        }
    }

    override fun getItemCount(): Int {
        return mListHouse?.size ?: 0
    }

    class HouseManageHolder(var itemHouseManageBinding: ItemHouseManageBinding) :
        RecyclerView.ViewHolder(
            itemHouseManageBinding.getRoot()
        )
}
