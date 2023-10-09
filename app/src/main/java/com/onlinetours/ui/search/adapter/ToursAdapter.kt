package com.onlinetours.ui.search.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onlinetours.R
import com.onlinetours.base.extensions.inflate
import com.onlinetours.base.extensions.onClick
import com.onlinetours.databinding.CityItemBinding
import com.onlinetours.databinding.TourItemBinding
import com.onlinetours.domain.entities.City
import com.onlinetours.domain.entities.result.Tour

open class ToursAdapter(
    private val listener: (Tour) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var bindind: TourItemBinding

    private var tours = mutableListOf<Tour>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        bindind = TourItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(bindind)
    }

    override fun getItemCount() = tours.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return (holder as ViewHolder).bind(listener, position)
    }

    inner class ViewHolder(private val binding: TourItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(
            listener: (Tour) -> Unit,
            position: Int
        ) = with(itemView) {
            val tour = tours[position]
            binding.nameTour.text = tour.offer.originalName
            val price = tour.offer.originalPrice.price
            val currency = tour.offer.originalPrice.currency
            val from = itemView.context.getString(R.string.from_)
            val operatorName = itemView.context.getString(R.string.operator)
            binding.price.text = "$from $price $currency"
            binding.operatorName.text = "$operatorName: ${tour.offer.operatorName}"

            binding.viewAll.onClick {
                listener(tour)
            }
        }
    }

    fun getData(): List<Tour> {
        return tours
    }

    fun setData(list: MutableList<Tour>) {
        tours.clear()
        tours = list
        this.notifyDataSetChanged()
    }
}