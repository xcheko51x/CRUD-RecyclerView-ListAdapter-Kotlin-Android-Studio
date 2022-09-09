package com.example.crud_recyclerview_listadapter_kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class NombresAdapter(private val itemClickListener: OnItemClickListener): ListAdapter<Nombre, NombresAdapter.ViewHolder>(DiffCallback()) {

    private class DiffCallback : DiffUtil.ItemCallback<Nombre>() {
        override fun areItemsTheSame(oldItem: Nombre, newItem: Nombre): Boolean {
            return oldItem.nombre == newItem.nombre
        }

        override fun areContentsTheSame(oldItem: Nombre, newItem: Nombre): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_nombres, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), itemClickListener)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val tvNombre = itemView.findViewById(R.id.tvNombre) as TextView
        private val btnEditar = itemView.findViewById(R.id.btnEdit) as Button
        private val btnBorrar = itemView.findViewById(R.id.btnDelete) as Button

        fun bind(item: Nombre, clickListener: OnItemClickListener) {
            tvNombre.text = item.nombre

            btnEditar.setOnClickListener { clickListener.onItemEditar(adapterPosition, item) }
            btnBorrar.setOnClickListener { clickListener.onItemBorrar(adapterPosition) }
        }
    }

    interface OnItemClickListener {
        fun onItemEditar(position: Int, item: Nombre)
        fun onItemBorrar(position: Int)
    }
}