package com.example.calendario

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MesesAdapter(
    private val listaMeses: List<String>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<MesesAdapter.MesViewHolder>() {

    class MesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreMes: TextView = itemView.findViewById(R.id.mes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_meses, parent, false)
        return MesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MesViewHolder, position: Int) {
        val mes = listaMeses[position]
        holder.nombreMes.text = mes
        holder.itemView.setOnClickListener { onItemClick(mes) }
    }

    override fun getItemCount() = listaMeses.size
}
