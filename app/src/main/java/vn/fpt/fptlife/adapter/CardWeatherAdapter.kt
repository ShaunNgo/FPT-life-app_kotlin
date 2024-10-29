package vn.fpt.fptlife.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vn.fpt.fptlife.databinding.CardWeatherBinding
import vn.fpt.fptlife.model.CardWeather

class CardWeatherAdapter(private val mContext: Context?) :
    RecyclerView.Adapter<CardWeatherAdapter.CardWeatherViewHolder>() {
    private var mCardWeather: CardWeather? = null
    fun setData(cardWeather: CardWeather?) {
        mCardWeather = cardWeather
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardWeatherViewHolder {
        val cardWeatherBinding: CardWeatherBinding =
            CardWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardWeatherViewHolder(cardWeatherBinding)
    }

    override fun onBindViewHolder(holder: CardWeatherViewHolder, position: Int) {
        holder.binding.tvLocation.setText(mCardWeather!!.location)
        holder.binding.tvTemperature.setText(mCardWeather!!.temperature)
        holder.binding.tvTemp.setText(mCardWeather!!.temperature)
        holder.binding.tvHumidity.setText(mCardWeather!!.humidity)
        holder.binding.tvLink.setText(mCardWeather!!.link)
    }

    override fun getItemCount(): Int {
        return 1
    }

    inner class CardWeatherViewHolder(binding: CardWeatherBinding) :
        RecyclerView.ViewHolder(binding.getRoot()) {
        var binding: CardWeatherBinding

        init {
            this.binding = binding
        }
    }
}
