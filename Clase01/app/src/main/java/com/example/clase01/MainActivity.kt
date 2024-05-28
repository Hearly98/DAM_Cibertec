package com.example.clase01

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import java.lang.reflect.Type

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1ra manera de contener en la variable un id
        // 2da manera  var tvejemplo: TextView = findViewById(R.id.tvejemplo)
         var tvejemplo = findViewById<TextView>(R.id.tvejemplo)

        /*tvejemplo.text = "Texto desde Backend de Kotlin"
        tvejemplo.setTextColor(Color.RED)
        tvejemplo.setTypeface(Typeface.MONOSPACE, Typeface.BOLD)

        // Evento para el label
        tvejemplo.setOnClickListener{
            Toast.makeText(this, "TextView cliqueado", Toast.LENGTH_SHORT).show()
            tvejemplo.setTextColor(Color.GREEN)*/

        // Refactorizando
        tvejemplo.apply {
            text= "Texto desde el backend de kotlin"
            setTextColor(Color.RED)
            setTypeface(Typeface.MONOSPACE, Typeface.BOLD)
            setOnClickListener{
                tvejemplo.setTextColor(Color.GREEN)
        }
        }

        //mensaje de error para edit text vacios
        var etEjemplo = findViewById<EditText>(R.id.etEjemplo)
        etEjemplo.apply {
            addTextChangedListener{
                if(etEjemplo.text.length == 0)
                    etEjemplo.setError("Campos Vacíos")
            }
        }
        etEjemplo.setSelection(3)
        var inicio = etEjemplo.selectionStart
        var fin = etEjemplo.selectionEnd
        etEjemplo.selectAll()
    }


}