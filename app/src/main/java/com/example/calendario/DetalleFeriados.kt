package com.example.calendario

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.calendario.Feriado


// import androidx.activity.enableEdgeToEdge
// import androidx.core.view.ViewCompat
// import androidx.core.view.WindowInsetsCompat


class DetalleFeriados : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_feriados)

        val tvMesFeriados: TextView = findViewById(R.id.tv_mes_feriados)
        val tvFeriadosDetalle: TextView = findViewById(R.id.tv_feriados_detalle)

        val mes = intent.getStringExtra("MES_SELECCIONADO")

        if (mes != null) {
            tvMesFeriados.text = "Feriados de $mes"

            val feriados = feriadosPorMes[mes] ?: emptyList()
            val nacionales = feriados.filter { it.tipo == "Feriado Nacional" }
            val noLaborables = feriados.filter { it.tipo == "No Laborable" }

            val texto = StringBuilder()

            if (feriados.isEmpty()) {
                tvFeriadosDetalle.text = "No hay feriados este mes" //does not work
            }

            if (nacionales.isNotEmpty()) {
                texto.append("Feriados Nacionales:\n")
                texto.append(nacionales.joinToString("\n") { "- ${it.fecha}: ${it.nombre}" })
                texto.append("\n\n")
            }

            if (noLaborables.isNotEmpty()) {
                texto.append("Días No Laborables:\n")
                texto.append(noLaborables.joinToString("\n") { "- ${it.fecha}: ${it.nombre}" })
            }

            tvFeriadosDetalle.text = texto.toString()

        } else {
            tvMesFeriados.text = "Error: Mes no encontrado."
        }
    }
}
