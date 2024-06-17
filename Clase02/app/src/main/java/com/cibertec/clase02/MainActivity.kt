package com.cibertec.clase02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.MultiAutoCompleteTextView
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //obteniendo al id de textNombre
        var etNombre = findViewById<EditText>(R.id.etNombre)
        //Si el input está vacío enviará un mensaje de error
        etNombre.addTextChangedListener {
            if(etNombre.text.length == 0)
                etNombre.setError("Campos Vacíos")
        }
        var autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        //Obtener el listado de Paises de la carpeta values
        var paises: Array<String> = resources.getStringArray(R.array.arregloPaisesA)

        //Armamos el constructor para el adaptador
        //ArrayAdapter es un metodo que se usa para proporcionar acceso a una coleccion de datos y vincularlo
        //a una vista que los mostrará, en este caso toma tres parametros:
        // contexto actual: esto es necesario para que el adapter sepa donde debe mostrarse
        // recurso de diseño: esto define como se verá cada elemento del desplegable
        // el ArrayList: la lista que contiene los datos a mostrar en el autoCompleteTextView
        var adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            android.R.layout.simple_dropdown_item_1line, paises
        )
        //vincular el adaptador al autoCompleteTextView, asi al momento de que el usuario escriba se le sugieran los países definidos en la lista
        autoCompleteTextView.setAdapter(adapter)

        var multiAutoCompleteTextView = findViewById<MultiAutoCompleteTextView>(R.id.multiAutoCompleteTextView)
        //vincular el adaptador al multiAutoCompleteTextView
        multiAutoCompleteTextView.setAdapter(adapter)
        //setTokenizer sirve para definir como se deben separar las entradas, en este caso usamos CommaTokenizer
        //CommaTokenizer: al escribir en el campo y colocar una coma(,) el multiAutoCompleteTextView lo tomará como una nueva entrada
        //que debe ser autocompletada de manera independiente
        multiAutoCompleteTextView.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())
    }

}