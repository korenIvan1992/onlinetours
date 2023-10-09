package com.onlinetours.ui.search.adapter

import androidx.recyclerview.widget.DiffUtil
import com.onlinetours.domain.entities.City

class DiffUtilCallback(oldList: List<City>, newList: List<City>) : DiffUtil.Callback() {
    private val oldList: List<City>
    private val newList: List<City>

    init {
        this.oldList = oldList
        this.newList = newList
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old: City = oldList[oldItemPosition]
        val new: City = newList[newItemPosition]
        return old.id === new.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old: City = oldList[oldItemPosition]
        val new: City = newList[newItemPosition]
        return old.name.equals(new.name)
    }
}