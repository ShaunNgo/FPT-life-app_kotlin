package vn.fpt.fptlife.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import vn.fpt.fptlife.R
import vn.fpt.fptlife.databinding.ItemDeviceBinding
import vn.fpt.fptlife.model.Device
import vn.fpt.fptlife.my_interface.IClickItemListener


class DeviceAdapter : RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder>() {
    private lateinit var mListDevice: List<Device>
    private lateinit var mContext: Context
    private var iClickItemListener: IClickItemListener? = null

    fun setData(
        listDevice: ArrayList<Device>,
        context: Context,
        mIClickItemListener: IClickItemListener
    ) {
        mListDevice = listDevice
        mContext = context
        this.iClickItemListener = mIClickItemListener

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val itemDeviceBinding =
            ItemDeviceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DeviceViewHolder(itemDeviceBinding)
    }

    override fun onBindViewHolder(
        holder: DeviceViewHolder,
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

        if (device.isDisabled == 0) {
            holder.binding.tvStatus.text = "Tắt"
            holder.binding.llDevice.setBackgroundColor(Color.parseColor("#121212"))
            val color = Color.parseColor("#474747")
            holder.binding.tvStatus.setTextColor(color)
            holder.binding.tvRoom.setTextColor(color)
            holder.binding.tvDevice.setTextColor(color)
            holder.binding.imgvDevice.setColorFilter(color)
            holder.binding.imgbDetail.setColorFilter(color)
        } else {
            holder.binding.tvStatus.text = "Mở"
            holder.binding.imgvDevice.setColorFilter(Color.WHITE)
        }

        if (device.element != null) {
            holder.binding.tvRoom.text = device.element!!.room.name
        } else if (device.room != null) {
            holder.binding.tvRoom.text = device.room!!.name
        } else {
            holder.binding.tvRoom.text = ""
        }

        holder.binding.tvDevice.text = device.name

        holder.binding.imgbDetail.setOnClickListener {
            iClickItemListener?.onClickItem(
                position,
                holder.binding.imgbDetail
            )
        }
    }

    override fun getItemCount(): Int {
        return mListDevice.size
    }

    class DeviceViewHolder(var binding: ItemDeviceBinding) : RecyclerView.ViewHolder(
        binding.getRoot()
    )
}
