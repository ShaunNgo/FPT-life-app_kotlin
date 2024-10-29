package vn.fpt.fptlife.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vn.fpt.fptlife.databinding.ItemAllDeviceCategoryBinding
import vn.fpt.fptlife.model.GetAllDeviceHomeIndexResponse

class AllDeviceFavoriteCategoryAdapter(
    context: Context, getAllDeviceHomeIndexResponse: GetAllDeviceHomeIndexResponse
) : RecyclerView.Adapter<AllDeviceFavoriteCategoryAdapter.AllDeviceCategoryViewHolder>() {
    private var mContext: Context = context
    private var mGetAllDeviceHomeIndexResponse: GetAllDeviceHomeIndexResponse =
        getAllDeviceHomeIndexResponse

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllDeviceCategoryViewHolder {
        val binding =
            ItemAllDeviceCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllDeviceCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllDeviceCategoryViewHolder, position: Int) {
        holder.binding.tvNameOfCategory.text = "Thiết bị"

        holder.binding.tvQuantityDevice.text = mGetAllDeviceHomeIndexResponse.data.total.toString()

        val gridLayoutManager = GridLayoutManager(mContext, 2)
        holder.binding.rvAllDeviceOfCate.setLayoutManager(gridLayoutManager)

        val deviceCheckboxAdapter = DeviceCheckboxAdapter()
        deviceCheckboxAdapter.setData(mGetAllDeviceHomeIndexResponse.data.devices, mContext)

        holder.binding.rvAllDeviceOfCate.setAdapter(deviceCheckboxAdapter)
    }

    override fun getItemCount(): Int {
        return 1
    }


    class AllDeviceCategoryViewHolder(var binding: ItemAllDeviceCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)
}