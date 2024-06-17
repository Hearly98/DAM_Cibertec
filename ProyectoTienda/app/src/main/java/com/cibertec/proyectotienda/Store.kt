package com.cibertec.proyectotienda
/*Se crea la base de datos Store, la clase ViewHolder para el adaptador, y una interfaz que pueda comunicar al activity con el adaptador*/
class Store (
    var id: Long = 0,
    var name: String,
    var phone: String = "",
    var website: String = "",
    var isFavorite: Boolean = false
)