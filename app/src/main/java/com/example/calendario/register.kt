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

                // 1. Crear el Intent para ir a la lista
                val intentLista = Intent(this, ListaMeses::class.java)

                // 2. Opcional pero recomendado: Limpiar la pila de actividades
                intentLista.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)

                // 3. ¡INICIAR LA ACTIVIDAD!
                startActivity(intentLista)

                // 4. ¡CERRAR LA ACTIVIDAD DE REGISTRO!
                // Esto evita que el usuario regrese a la pantalla de registro con el botón 'Atrás'.
                finish()

                // Opcional: Mostrar un Toast de éxito
                Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


