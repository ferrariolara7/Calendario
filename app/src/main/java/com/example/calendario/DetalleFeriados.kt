package com.example.calendario

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DetalleFeriados : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_feriados)

        val mes = intent.getStringExtra("MES_SELECCIONADO")

        // Crear una instancia del fragmento y pasarle el mes seleccionado
        val fragment = FeriadosFragment()
        val bundle = Bundle()
        bundle.putString("MES_SELECCIONADO", mes)
        fragment.arguments = bundle

        // Cargar el fragmento en el contenedor de la Activity
        supportFragmentManager.beginTransaction()
            .replace(R.id.contenedorFragment, fragment)
            .commit()
    }
}
