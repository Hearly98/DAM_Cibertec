package com.cibertec.proyectotienda

interface OnClickListener {
    //Definimos un metodo OnClick que recibirá una tienda
    fun onClick (store: StoreEntity)
    fun onFavoriteStore (store: StoreEntity)
    fun onDeleteStore(storeEntity: StoreEntity)
}