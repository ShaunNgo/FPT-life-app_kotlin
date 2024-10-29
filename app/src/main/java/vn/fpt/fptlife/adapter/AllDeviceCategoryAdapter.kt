package vn.fpt.fptlife.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vn.fpt.fptlife.databinding.ItemAllDeviceCategoryBinding
import vn.fpt.fptlife.model.GetAllDeviceHomeIndexResponse
import vn.fpt.fptlife.my_interface.IClickItemListener

class AllDeviceCategoryAdapter(
    context: Context,
    getAllDeviceHomeIndexResponse: GetAllDeviceHomeIndexResponse,
    iClickItemListener: IClickItemListener
) : RecyclerView.Adapter<AllDeviceCategoryAdapter.AllDeviceCategoryViewHolder>() {
    private var mContext: Context = context
    private var mAllDeviceCategory: GetAllDeviceHomeIndexResponse = getAllDeviceHomeIndexResponse
    private var mIClickItemListener: IClickItemListener = iClickItemListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllDeviceCategoryViewHolder {
        val itemBinding =
            ItemAllDeviceCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllDeviceCategoryViewHolder(itemBinding)
    }

    override fun onBindViewHolder(
        holder: AllDeviceCategoryViewHolder,
        position: Int
    ) {
        holder.binding.tvNameOfCategory.text = "Thiết bị"

        holder.binding.tvQuantityDevice.text = mAllDeviceCategory.data.total.toString()

        val gridLayoutManager = GridLayoutManager(mContext, 2)
        holder.binding.rvAllDeviceOfCate.setLayoutManager(gridLayoutManager)

        val deviceAdapter = DeviceAdapter()
        deviceAdapter.setData(mAllDeviceCategory.data.devices, mContext, mIClickItemListener)
        holder.binding.rvAllDeviceOfCate.setAdapter(deviceAdapter)
    }

    override fun getItemCount(): Int {
        return 1
    }

    class AllDeviceCategoryViewHolder(var binding: ItemAllDeviceCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)
}