package com.cibertec.proyectotienda

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.proyectotienda.databinding.ItemTiendaBinding
//Agregamos variables
class StoreAdapter (private var stores: MutableList<Store>,
                    private var listener: OnClickListener):
                    RecyclerView.Adapter<StoreAdapter.ViewHolder>(){

    //mContext es un miembro de Context. Es una buena practica
    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //inicializamos dicha variable
        mContext = parent.context
        //inflamos la vista
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_tienda, parent, false)
        //pasamos a la vista
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //creamos una constante
        val store = stores[position]
        //Trabajamos con este objeto
        with(holder){
            setListener(store)
            //Trabajando por ahora con el nombre de la tienda
            binding.tvName.text = store.name
        }
    }
    //Nos dará la cantidad de fragmentos en el padre
    override fun getItemCount(): Int = stores.size
        fun add(store:Store){
            stores.add(store)
            notifyDataSetChanged()
        }
    /*Crear una clase interna Inner Class ViewHolder que recibirá una vista view tipo View.Luego
* se hereda de recyclerView.
* De ahí su ViewHolder recibe una vista y se pasa el view*/
    inner class ViewHolder(view : View):RecyclerView.ViewHolder(view) {
        /*Ahora vinculamos la vista con ViewBinding
        * Una vez sincronizado, volvemos al adaptador para crear esa constante. Aparece despues de
        * la sincronizacion algo como Item.... Le ponemos bind para que reciba una vista.
        * Por ello, se pone el view.*/
        val binding = ItemTiendaBinding.bind(view)
        /*Creamos nuestro listener para vincular nuestro ViewHolder.
        * Crearemos una funcion setListener para que reciba la tienda*/
        fun setListener(store: Store){
            //Configurando el evento más basico
            binding.root.setOnClickListener{ listener.onClick(store)}
        }
    }
    }