package vn.fpt.fptlife.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vn.fpt.fptlife.databinding.ItemDeviceCategoryBinding
import vn.fpt.fptlife.model.DeviceResponse
import vn.fpt.fptlife.model.DeviceResponse.DataDevice
import vn.fpt.fptlife.my_interface.IClickItemGetAllDeviceListener

class DeviceCategoryCheckboxAdapter(
    context: Context,
    deviceResponse: DeviceResponse?,
    iClickItemGetAllDeviceListener: IClickItemGetAllDeviceListener
) :
    RecyclerView.Adapter<DeviceCategoryCheckboxAdapter.DeviceCategoryCheckBoxViewHolder>() {
    private val mContext: Context = context
    private val mListDeviceCategory: List<DataDevice> = deviceResponse!!.data
    private val mIClickItemGetAllDeviceListener: IClickItemGetAllDeviceListener =
        iClickItemGetAllDeviceListener


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DeviceCategoryCheckBoxViewHolder {
        val itemDeviceCategoryBinding =
            ItemDeviceCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DeviceCategoryCheckBoxViewHolder(itemDeviceCategoryBinding)
    }

    override fun onBindViewHolder(holder: DeviceCategoryCheckBoxViewHolder, position: Int) {
        val category = mListDeviceCategory!![position]
        holder.itemDeviceCategoryBinding.tvNameCategory.text = category.name
        holder.itemDeviceCategoryBinding.tvQuantity.text = "(" + category.devices.total + ")"
        val gridLayoutManager = GridLayoutManager(mContext, 2)
        holder.itemDeviceCategoryBinding.rvAllDevice.setLayoutManager(gridLayoutManager)
        val deviceCheckboxAdapter = DeviceCheckboxAdapter()
        deviceCheckboxAdapter.setData(category.devices.devices, mContext)
        holder.itemDeviceCategoryBinding.rvAllDevice.setAdapter(deviceCheckboxAdapter)
        holder.itemDeviceCategoryBinding.tvAllDevice.setOnClickListener {
            mIClickItemGetAllDeviceListener.onClickItemGetAll(
                holder.itemDeviceCategoryBinding.tvAllDevice,
                category
            )
        }
    }

    override fun getItemCount(): Int {
        return mListDeviceCategory.size ?: 0
    }

    class DeviceCategoryCheckBoxViewHolder(var itemDeviceCategoryBinding: ItemDeviceCategoryBinding) :
        RecyclerView.ViewHolder(itemDeviceCategoryBinding.getRoot())
}
