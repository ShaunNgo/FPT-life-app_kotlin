package vn.fpt.fptlife.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vn.fpt.fptlife.R
import vn.fpt.fptlife.databinding.ItemDeviceCategoryBinding
import vn.fpt.fptlife.model.DeviceResponse
import vn.fpt.fptlife.model.DeviceResponse.DataDevice
import vn.fpt.fptlife.my_interface.IClickItemGetAllDeviceListener
import vn.fpt.fptlife.my_interface.IClickItemListener


class DeviceCategoryAdapter(
    context: Context?,
    deviceResponse: DeviceResponse,
    iClickItemListener: IClickItemListener,
    iClickItemGetAllDeviceListener: IClickItemGetAllDeviceListener

) :
    RecyclerView.Adapter<DeviceCategoryAdapter.DeviceCategoryViewHolder>() {
    private val mContext: Context? = context
    private val mListDeviceCategory: List<DataDevice> = deviceResponse.data
    private val mIClickItemListener: IClickItemListener = iClickItemListener
    private val mIClickItemGetAllDeviceListener: IClickItemGetAllDeviceListener = iClickItemGetAllDeviceListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceCategoryViewHolder {
        val binding = ItemDeviceCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DeviceCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DeviceCategoryViewHolder, position: Int) {
        val category = mListDeviceCategory[position]

        holder.binding.tvNameCategory.text = category.name

        holder.binding.tvQuantity.text = "(" + category.devices.total + ")"

        val gridLayoutManager = GridLayoutManager(mContext, 2)
        holder.binding.rvAllDevice.setLayoutManager(gridLayoutManager)

        val deviceAdapter = DeviceAdapter()
        deviceAdapter.setData(category.devices.devices, mContext!!, mIClickItemListener)
        holder.binding.rvAllDevice.setAdapter(deviceAdapter)

        holder.binding.tvAllDevice.setOnClickListener {
            mIClickItemGetAllDeviceListener.onClickItemGetAll(
                holder.binding.tvAllDevice,
                category
            )
        }
    }

    override fun getItemCount(): Int {
        return mListDeviceCategory.size
    }

    class DeviceCategoryViewHolder(var binding: ItemDeviceCategoryBinding) :
        RecyclerView.ViewHolder(binding.getRoot())
}
