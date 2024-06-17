package com.cibertec.proyectotienda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.cibertec.proyectotienda.databinding.ActivityMainBinding
import java.util.concurrent.LinkedBlockingDeque

class MainActivity : AppCompatActivity(), OnClickListener {
    //lateinit: propiedad a la cual se le indica que será declarada mas tarde y no al momento de declararse, debe ser un var nunca un val
    private lateinit var mBinding: ActivityMainBinding
    //Instanciamos el adaptador personalizado (StoreAdapter) para proporcionar vistas a un componente de RecyclerView
    private lateinit var mAdapter: StoreAdapter
    //Gestiona como los elementos dedl RecyclerView se distribuyen en una cuadricula
    private lateinit var mGridLayout: GridLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.btnSave.setOnClickListener{
            val store = StoreEntity(name = mBinding.etName.text.toString().trim())

            //Se pone hilo para que se ejecute en segundo plano
            Thread{
                StoreApplication.database.storeDao().addStore(store)
            }.start()
            //Agregamos al Adaptador
            mAdapter.add(store)
        }
        setupRecyclerView()
    }
    private fun getStores(){

        val queue = LinkedBlockingDeque<MutableList<StoreEntity>>()
        Thread{
            val stores = StoreApplication.database.storeDao().getAllStores()
            queue.add(stores)
        }.start()
        mAdapter.setStores(queue.take())
    }
    private fun setupRecyclerView(){
        mAdapter = StoreAdapter(mutableListOf(),this)
        mGridLayout = GridLayoutManager(this,2 )
        getStores()
        mBinding.recyclerView.apply{
            setHasFixedSize(true)
            layoutManager = mGridLayout
            adapter = mAdapter
        }
    }


    // CTRL + I nos construye funciones
    override fun onClick(store: StoreEntity) {

    }
    override fun onFavoriteStore(storeEntity: StoreEntity) {
      storeEntity.isFavorite = !storeEntity.isFavorite
        val queue = LinkedBlockingDeque<StoreEntity>()
        Thread{
            StoreApplication.database.storeDao().updateStore(storeEntity)
            queue.add(storeEntity)
        }.start()
        mAdapter.update(queue.take())
    }

    override fun onDeleteStore(storeEntity: StoreEntity) {
       val queue = LinkedBlockingDeque<StoreEntity>()
        Thread {
            StoreApplication.database.storeDao().deleteStore(storeEntity)
            queue.add(storeEntity)
        }.start()
        mAdapter.delete(queue.take())
    }

}