package com.example.calendario

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private val PREF_NAME = "user_prefs"
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        progressBar = findViewById(R.id.progressBar)

        // Simulación de carga progresiva
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
                Thread.sleep(40) // velocidad de carga (0.04 seg por paso)
            }

            handler.post {
                // Una vez completado el "loading", decide a dónde ir
                val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

                if (isLoggedIn) {
                    startActivity(Intent(this, ListaMeses::class.java))
                } else {
                    startActivity(Intent(this, logIn::class.java))
                }
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }
        }.start()
    }
}
