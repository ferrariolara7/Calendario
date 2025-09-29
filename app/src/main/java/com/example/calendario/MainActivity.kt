package com.example.calendario

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.calendario.model.AppDatabase
import com.example.calendario.model.usuario
import kotlinx.coroutines.launch
import android.content.Context
import android.content.SharedPreferences
import android.widget.CheckBox

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private val PREF_NAME = "user_prefs"
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvRegister: TextView

    private lateinit var chkRememberUser: CheckBox


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Ajuste de padding para system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Vinculación de vistas
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        btnLogin = findViewById(R.id.btnLogIn)
        tvRegister = findViewById(R.id.tvRegister)
        chkRememberUser = findViewById(R.id.chkRememberUser)


        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        // Si el usuario ya está logueado, ir directo a la lista
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
        if (isLoggedIn) {
            val intent = Intent(this, ListaMeses::class.java)
            startActivity(intent)
            finish()
        }


        btnLogin.setOnClickListener {
            val userEmail = email.text.toString().trim()
            val userPassword = password.text.toString().trim()

            if (userEmail.isEmpty() || userPassword.isEmpty()) {
                Toast.makeText(this, "Por favor, ingrese email y contraseña.", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val db = AppDatabase.getDatabase(this)
                val usuarioDao = db.usuarioDao()

                lifecycleScope.launch {
                    val usuario = usuarioDao.login(userEmail, userPassword)
                    if (usuario != null) {
                        // Si el login es exitoso
                        if (chkRememberUser.isChecked) {
                            // Guardamos los datos si el usuario marcó "Recordar usuario"
                            val editor = sharedPreferences.edit()
                            editor.putBoolean("isLoggedIn", true)
                            editor.putString("userEmail", userEmail)
                            editor.apply()
                        } else {
                            // Si no lo marcó, limpiamos cualquier dato previo
                            val editor = sharedPreferences.edit()
                            editor.clear()
                            editor.apply()
                        }

                        // Ir a la pantalla principal
                        val intentLista = Intent(this@MainActivity, ListaMeses::class.java)
                        intentLista.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intentLista)
                        finish()

                    } else {
                        //Usuario o contraseña incorrectos
                        Toast.makeText(
                            this@MainActivity,
                            "Email o contraseña incorrectos",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        // Acción texto registrarse
        tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
