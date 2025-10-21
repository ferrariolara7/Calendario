package com.example.calendario

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

lateinit var toolbar: Toolbar

class ListaMeses : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val NAV_ICON_RES_ID = R.drawable.ic_icono_menu
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_meses)

        val recyclerViewMeses: RecyclerView = findViewById(R.id.meses)
        val listaDeMeses = listOf(
            "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        )

        recyclerViewMeses.layoutManager = LinearLayoutManager(this)
        MesesAdapter(listaDeMeses) { mesSeleccionado ->
            // Cuando el usuario toca un mes, abrimos la pantalla de detalle
            val intent = Intent(this, DetalleFeriados::class.java)
            intent.putExtra("MES_SELECCIONADO", mesSeleccionado)
            startActivity(intent)
        }.also { recyclerViewMeses.adapter = it }

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Menú"
        toolbar.setNavigationIcon(NAV_ICON_RES_ID)

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
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_login -> {
                startActivity(Intent(this, MainActivity::class.java))
                return true
            }
            R.id.item_TermsyConds -> {
                startActivity(Intent(this, TerminosActivity::class.java))
                return true
            }
            R.id.action_logout -> {
                val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                sharedPreferences.edit().putBoolean("isLoggedIn", false).apply()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
