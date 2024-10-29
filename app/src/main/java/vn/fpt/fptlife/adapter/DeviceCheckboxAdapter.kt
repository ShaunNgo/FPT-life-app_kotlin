package vn.fpt.fptlife.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import vn.fpt.fptlife.R
import vn.fpt.fptlife.databinding.ItemDeviceCheckBoxBinding
import vn.fpt.fptlife.model.Device

class DeviceCheckboxAdapter :
    RecyclerView.Adapter<DeviceCheckboxAdapter.DeviceCheckBoxViewHolder>() {
    private lateinit var mListDevice: List<Device>
    private lateinit var mContext: Context

    fun setData(listDevice: ArrayList<Device>, context: Context) {
        mListDevice = listDevice
        mContext = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceCheckBoxViewHolder {
        val binding = ItemDeviceCheckBoxBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DeviceCheckBoxViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: DeviceCheckBoxViewHolder,
        position: Int
    ) {
        val device = mListDevice[position]

        if (device.icon != null) {
            Glide.with(mContext)
                .load(device.icon!!.pathname)
                .placeholder(R.drawable.ic_lightbulb)
                .error(R.drawable.ic_lightbulb)
                .override(70, 70)
                .centerCrop()
                .into(holder.binding.imgvDevice)
        } else {
            Glide.with(mContext)
                .load("")
                .placeholder(R.drawable.ic_lightbulb)
                .error(R.drawable.ic_lightbulb)
                .override(70, 70)
                .centerCrop()
                .into(holder.binding.imgvDevice)
        }
        holder.binding.tvStatus.text = ""

        holder.binding.imgvDevice.setColorFilter(Color.WHITE)

        if (device.element != null) {
            holder.binding.tvRoom.text = device.element!!.room.name
        } else if (device.room != null) {
            holder.binding.tvRoom.text = device.room!!.name
        } else {
            holder.binding.tvRoom.text = ""
        }

        holder.binding.tvDevice.text = device.name

        holder.binding.checkBox.setChecked(device.isFavorited == 1)
    }

    override fun getItemCount(): Int {
        return mListDevice.size
    }

    class DeviceCheckBoxViewHolder(var binding: ItemDeviceCheckBoxBinding) :
        RecyclerView.ViewHolder(binding.root)
}
