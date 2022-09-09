package com.example.crud_recyclerview_listadapter_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crud_recyclerview_listadapter_kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NombresAdapter.OnItemClickListener {

    var dataSet: MutableList<Nombre> = arrayListOf()
    lateinit var mAdapter: NombresAdapter

    lateinit var binding: ActivityMainBinding

    var isEditar = false
    var posicion = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAdapter = NombresAdapter(this)
        binding.rvList.layoutManager = LinearLayoutManager(this)
        binding.rvList.adapter = mAdapter

        binding.btnAddEdit.setOnClickListener {
            if(!isEditar) {
                dataSet.add(Nombre(binding.etNombre.text.toString().trim()))
                mAdapter.submitList(dataSet)
                mAdapter.notifyDataSetChanged()

                binding.etNombre.setText("")
            } else {
                dataSet[posicion].nombre = binding.etNombre.text.toString()
                posicion = -1
                isEditar = false
                mAdapter.submitList(dataSet)
                mAdapter.notifyDataSetChanged()

                binding.etNombre.setText("")
            }
        }
    }

    override fun onItemEditar(position: Int, item: Nombre) {
        isEditar = true
        posicion = position
        binding.etNombre.setText(item.nombre)
    }

    override fun onItemBorrar(position: Int) {
        dataSet.removeAt(position)
        mAdapter.submitList(dataSet)
        mAdapter.notifyDataSetChanged()
    }
}