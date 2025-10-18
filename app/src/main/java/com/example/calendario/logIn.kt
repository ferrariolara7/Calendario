package com.example.calendario

import android.content.Intent
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.calendario.model.AppDatabase
import kotlinx.coroutines.launch

class logIn : AppCompatActivity() {

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
        setContentView(R.layout.activity_log_in)

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

        // Si el usuario ya está logueado, ir directo a la pantalla principal
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
        if (isLoggedIn) {
            val intent = Intent(this, ListaMeses::class.java)
            startActivity(intent)
            finish()
        }

        // Acción del botón de login
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
                        // Guardar sesión si el usuario marcó "Recordar usuario"
                        val editor = sharedPreferences.edit()
                        if (chkRememberUser.isChecked) {
                            editor.putBoolean("isLoggedIn", true)
                            editor.putString("userEmail", userEmail)
                        } else {
                            editor.clear()
                        }
                        editor.apply()

                        // Ir a la pantalla principal
                        val intentLista = Intent(this@logIn, ListaMeses::class.java)
                        intentLista.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intentLista)
                        finish()

                    } else {
                        Toast.makeText(
                            this@logIn,
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
