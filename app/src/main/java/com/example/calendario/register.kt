package com.example.calendario

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
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


