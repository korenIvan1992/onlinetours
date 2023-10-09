package com.onlinetours.ui.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onlinetours.R
import com.onlinetours.base.extensions.inflate
import com.onlinetours.base.extensions.onClick
import com.onlinetours.databinding.CityItemBinding
import com.onlinetours.domain.entities.City


/**
 * Created by koren_ivan on 2020.
 */

open class CityAdapter(
    private val listener: (City) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var bindind: CityItemBinding

    private var cyties = mutableListOf<City>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        bindind = CityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(bindind)
    }

    override fun getItemCount() = cyties.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return (holder as ViewHolder).bind(listener, position)
    }

    inner class ViewHolder(private val binding: CityItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            listener: (City) -> Unit,
            position: Int
        ) = with(itemView) {
            val city = cyties[position]
            binding.titleName.text = city.name
            binding.viewAll.onClick {
                listener(city)
            }
        }
    }

    fun getData(): List<City> {
        return cyties
    }

    fun setData(list: MutableList<City>) {
        cyties.clear()
        cyties = list
        this.notifyDataSetChanged()
    }
}