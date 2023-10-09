package com.onlinetours.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onlinetours.base.extensions.onClick
import com.onlinetours.databinding.CountryItemBinding
import com.onlinetours.domain.entities.Regions


open class CountryAdapter(
    private val listener: (Regions) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var bindind: CountryItemBinding

    private var countries = mutableListOf<Regions>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        bindind = CountryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(bindind)
    }

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return (holder as ViewHolder).bind(listener, position)
    }

    inner class ViewHolder(private val binding: CountryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            listener: (Regions) -> Unit,
            position: Int
        ) = with(itemView) {
            val city = countries[position]
            binding.titleName.text = city.name
            binding.viewAll.onClick {
                listener(city)
            }
        }
    }

    fun getData(): List<Regions> {
        return countries
    }

    fun setData(list: MutableList<Regions>) {
        countries.clear()
        countries = list
        this.notifyDataSetChanged()
    }
}