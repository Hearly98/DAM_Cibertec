package com.cibertec.proyectotienda

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.proyectotienda.databinding.ItemTiendaBinding
//Agregamos variables
class StoreAdapter (private var stores: MutableList<StoreEntity>,
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
        val store = stores.get(position)
        //Trabajamos con este objeto
        with(holder){
            setListener(store)
            //Trabajando por ahora con el nombre de la tienda
            binding.tvName.text = store.name
            binding.cbFavorite.isChecked = store.isFavorite
        }
    }
    //Nos dará la cantidad de fragmentos en el padre
    override fun getItemCount(): Int = stores.size

        fun setStores(stores:MutableList<StoreEntity>){
            this.stores = stores
            notifyDataSetChanged()
        }
        fun add(storeEntity:StoreEntity){
            stores.add(storeEntity)
            notifyDataSetChanged()
        }

        fun update(storeEntity : StoreEntity){
            val index = stores.indexOf(storeEntity)
            if(index != -1){
                stores.set(index, storeEntity)
                notifyItemChanged(index)
            }
        }
        fun delete(storeEntity: StoreEntity){
            val index = stores.indexOf(storeEntity)
            if(index != -1){
                stores.removeAt(index)
                notifyItemRemoved(index)
            }
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
        fun setListener(storeEntity: StoreEntity){

            //Configurando el evento más basico
            with(binding.root){
                setOnClickListener {  listener.onClick(storeEntity) }
                setOnLongClickListener {
                    listener.onDeleteStore(storeEntity)
                    true
                }
            }
            binding.cbFavorite.setOnClickListener {
                listener.onFavoriteStore(storeEntity)
            }
        }
    }
    }