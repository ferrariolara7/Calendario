package com.example.calendario

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private val PREF_NAME = "user_prefs"
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // 👇 Agregá esto acá
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 1)
        }

        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        progressBar = findViewById(R.id.progressBar)


        simulateLoading()
    }


    private fun simulateLoading() {
        val handler = Handler(Looper.getMainLooper())
        var progressStatus = 0

        Thread {
            while (progressStatus < 100) {
                progressStatus += 2
                handler.post {
                    progressBar.progress = progressStatus
                }
                Thread.sleep(40)
            }

            handler.post {
                val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)


                if (isLoggedIn) {
                    // 🔔 acá llamás a la función que está más abajo 👇
                    mostrarNotificacion(
                        "¡Bienvenida de nuevo!",
                        "Tu usuario fue recordado correctamente."
                    )
                    startActivity(Intent(this, ListaMeses::class.java))
                } else {
                    startActivity(Intent(this, logIn::class.java))
                }

                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }
        }.start()
    }

    // 🔹 ACÁ VA la función mostrarNotificacion
    private fun mostrarNotificacion(titulo: String, mensaje: String) {
        val channelId = "canal_recordar_usuario"

        // Crear canal (solo Android 8+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Recordar usuario"
            val descriptionText = "Canal de notificaciones para recordar usuario"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(titulo)
            .setContentText(mensaje)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true) // 💡 se cierra al tocarla

        with(NotificationManagerCompat.from(this)) {
            notify(1, builder.build())
        }
    }

}
