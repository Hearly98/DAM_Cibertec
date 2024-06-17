package com.cibertec.proyectotienda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.cibertec.proyectotienda.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity(), OnClickListener {
    //lateinit: propiedad a la cual se le indica que será declarada mas tarde y no al momento de declararse, debe ser un var nunca un val
    private lateinit var binding: ActivityMainBinding
    //Instanciamos el adaptador personalizado (StoreAdapter) para proporcionar vistas a un componente de RecyclerView
    private lateinit var mAdapter: StoreAdapter
    //Gestiona como los elementos dedl RecyclerView se distribuyen en una cuadricula
    private lateinit var mGridLayout: GridLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener{
            val store = Store(name = binding.etName.text.toString().trim())
            //Agregamos al Adaptador
            mAdapter.add(store)
        }
        setupRecyclerView()
    }
    private fun setupRecyclerView(){
        mAdapter = StoreAdapter(mutableListOf(),this)
        mGridLayout = GridLayoutManager(this,2 )

        binding.recyclerView.apply{
            setHasFixedSize(true)
            layoutManager = mGridLayout
            adapter = mAdapter
        }
    }
    // CTRL + I nos construye funciones
    override fun onClick(store: Store) {
        TODO("Not yet implemented")
    }
}