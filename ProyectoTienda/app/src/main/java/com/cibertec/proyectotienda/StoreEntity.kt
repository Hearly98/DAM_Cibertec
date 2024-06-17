package com.cibertec.proyectotienda

import androidx.room.Entity
import androidx.room.PrimaryKey

/*Se crea la base de datos Store, la clase ViewHolder para el adaptador, y una interfaz que pueda comunicar al activity con el adaptador*/
@Entity(tableName = "StoreEntity")
data class StoreEntity (@PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var name: String,
    var phone: String = "",
    var website: String = "",
    var isFavorite: Boolean = false
)
//esto se modificará con la base de datos