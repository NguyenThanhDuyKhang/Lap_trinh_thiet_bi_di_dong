package com.example.baitap21

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NumberAdapter(private var items: List<Int> = emptyList()) :
    RecyclerView.Adapter<NumberAdapter.VH>() {

    class VH(view: View) : RecyclerView.ViewHolder(view) {
        val tv: TextView = view.findViewById(R.id.tvNumber)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_number, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.tv.text = items[position].toString()
    }

    override fun getItemCount(): Int = items.size

    fun updateList(newList: List<Int>) {
        items = newList
        notifyDataSetChanged()
    }
}
