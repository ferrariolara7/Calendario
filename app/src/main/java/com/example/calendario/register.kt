package com.example.calendario

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.calendario.model.AppDatabase
import com.example.calendario.model.usuario
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    lateinit var btnRegister: Button
    lateinit var emailEdit: EditText
    lateinit var passwordEdit: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Inicializar vistas
        btnRegister = findViewById(R.id.btnRegister)
        emailEdit = findViewById(R.id.regEmail)
        passwordEdit = findViewById(R.id.regPassword)

        btnRegister.setOnClickListener {
            val emailText = emailEdit.text.toString().trim()
            val passwordText = passwordEdit.text.toString().trim()

            if (emailText.isEmpty() || passwordText.isEmpty()) {
                Toast.makeText(this, "Ingrese los datos solicitados", Toast.LENGTH_SHORT).show()
            } else {
                // Guardar en la base de datos
                val db = AppDatabase.getDatabase(this)
                val usuarioDao = db.usuarioDao()

                lifecycleScope.launch {
                    // Crear el usuario
                    val nuevoUsuario = usuario(nombre = emailText, email = emailText, contrasena = passwordText)
                    usuarioDao.insert(nuevoUsuario)

                    // Luego de guardar, ir a la siguiente pantalla
                    val intentLista = Intent(this@RegisterActivity, ListaMeses::class.java)
                    intentLista.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intentLista)
                    finish()
                }

                // Opcional: Toast dentro de la coroutine
                lifecycleScope.launch {
                    Toast.makeText(this@RegisterActivity, "Registro Exitoso", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}


