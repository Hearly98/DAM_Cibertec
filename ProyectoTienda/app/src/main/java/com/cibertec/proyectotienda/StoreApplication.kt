package com.cibertec.proyectotienda

import android.app.Application
import androidx.room.Room

class StoreApplication : Application() {
    companion object {
       lateinit var database: StoreDatabase
    }

    override fun onCreate() {
        super.onCreate()
        // Aquí debes inicializar la base de datos
        database = Room.databaseBuilder(
            this,
            StoreDatabase::class.java,
            "StoreDatabase"
        ).build()
    }
}
