package com.example.calendario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.calendario.network.RetrofitClient
import kotlinx.coroutines.launch

class ApiTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api_test)

        lifecycleScope.launch {
            try {
                val holidays = RetrofitClient.api.getPublicHolidays(2025, "AR")
                Log.d("API", "Feriados: ${holidays.size}")
                holidays.forEach {
                    Log.d("API", "${it.date} - ${it.localName}")
                }
            } catch (e: Exception) {
                Log.e("API", "Error: ${e.message}", e)
            }
        }
    }
}
