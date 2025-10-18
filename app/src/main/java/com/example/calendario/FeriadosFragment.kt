package com.example.calendario

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.calendario.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FeriadosFragment : Fragment() {

    private lateinit var tvMesFeriados: TextView
    private lateinit var tvFeriadosDetalle: TextView
    private var mesSeleccionado: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Recibimos el mes pasado desde la Activity DetalleFeriados
        mesSeleccionado = arguments?.getString("MES_SELECCIONADO")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflamos el diseño del fragmento (fragment_feriados.xml)
        val view = inflater.inflate(R.layout.fragment_feriados, container, false)

        // Referencias a los TextView
        tvMesFeriados = view.findViewById(R.id.tv_mes_feriados)
        tvFeriadosDetalle = view.findViewById(R.id.tv_feriados_detalle)

        val mes = mesSeleccionado
        if (mes != null) {
            tvMesFeriados.text = "Feriados de $mes"
            tvFeriadosDetalle.text = "Cargando feriados..."

            //Llamado a la API en un hilo de ejecución distinto (Dispatchers.IO)
            lifecycleScope.launch(Dispatchers.IO) {
                try {
                    val holidays = RetrofitClient.api.getPublicHolidays(2025, "AR")

                    val numeroMes = when (mes.lowercase()) {
                        "enero" -> 1; "febrero" -> 2; "marzo" -> 3; "abril" -> 4
                        "mayo" -> 5; "junio" -> 6; "julio" -> 7; "agosto" -> 8
                        "septiembre" -> 9; "octubre" -> 10; "noviembre" -> 11; "diciembre" -> 12
                        else -> 0
                    }

                    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                    val feriadosFiltrados = holidays.filter {
                        val fecha = LocalDate.parse(it.date, formatter)
                        fecha.monthValue == numeroMes
                    }

                    val texto = if (feriadosFiltrados.isEmpty()) {
                        "No hay feriados este mes"
                    } else {
                        feriadosFiltrados.joinToString("\n") { "- ${it.date}: ${it.localName}" }
                    }

                    //Volvemos al hilo principal para actualizar la interfaz
                    withContext(Dispatchers.Main) {
                        tvFeriadosDetalle.text = texto
                    }

                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        tvFeriadosDetalle.text = "Error al cargar feriados: ${e.message}"
                    }
                }
            }
        } else {
            tvMesFeriados.text = "Error: mes no encontrado"
            tvFeriadosDetalle.text = ""
        }

        return view
    }
}
