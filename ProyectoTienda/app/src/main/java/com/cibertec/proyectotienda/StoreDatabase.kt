package com.cibertec.proyectotienda

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [StoreEntity::class], version = 1)
//version 1 porque se acaba de crear
abstract class StoreDatabase : RoomDatabase(){
    abstract fun storeDao(): StoreDao
}