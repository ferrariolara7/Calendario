package com.example.calendario

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListaMeses : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_meses)

        // 1. Obtener la referencia al RecyclerView con el ID 'meses'
        val recyclerViewMeses: RecyclerView = findViewById(R.id.meses)

        // 2. Crear los datos
        val listaDeMeses = listOf(
            "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        )

        // 3. Configurar el Manager y el Adapter
        recyclerViewMeses.layoutManager = LinearLayoutManager(this)
        recyclerViewMeses.adapter = MesesAdapter(listaDeMeses)
    }
}