package com.example.calendario

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.Toolbar
import android.content.Intent
import android.widget.Button


lateinit var toolbar: Toolbar

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

        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)
        supportActionBar!!.title = resources.getString(R.string.titulo)

        supportActionBar?.title = "Menú"

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnTerminos = findViewById<Button>(R.id.btnTerminos)

        btnLogin.setOnClickListener {
            val intent = Intent(this, logIn::class.java)
            startActivity(intent)
        }

        btnTerminos.setOnClickListener {
            val intent = Intent(this, TerminosActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_principal, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_login -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.item_TermsyConds -> {
                val intent = Intent(this, TerminosActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}