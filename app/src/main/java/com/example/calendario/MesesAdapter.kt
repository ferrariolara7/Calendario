package com.example.calendario

// Importaciones
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.calendario.DetalleFeriados
import android.content.Intent

// Declaración del Adapter con el constructor que recibe la lista de meses
class MesesAdapter(private val meses: List<String>) :
    RecyclerView.Adapter<MesesAdapter.MesViewHolder>() {

    // ----------------------------------------------------
    // 2. MesViewHolder: Define y maneja el clic de la vista individual
    // ----------------------------------------------------
    class MesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // Referencia a tu TextView con ID 'mes' de item_meses.xml
        val nombreMesTextView: TextView = itemView.findViewById(R.id.mes)

        init {
            // Manejador de clic: INICIA LA NUEVA ACTIVIDAD Y ENVÍA EL DATO
            itemView.setOnClickListener {

                val position = adapterPosition

                // Validación para evitar clics inválidos
                if (position != RecyclerView.NO_POSITION) {

                    // Obtener el nombre del mes directamente del TextView (el dato más actualizado)
                    val mesSeleccionado = nombreMesTextView.text.toString()

                    // 1. Crear el Intent
                    val context = itemView.context
                    val intent = Intent(context,DetalleFeriados::class.java)

                    // 2. Empaquetar el nombre del mes como un "Extra"
                    intent.putExtra("MES_SELECCIONADO", mesSeleccionado)

                    // 3. Iniciar la actividad
                    context.startActivity(intent)
                }
            }
        }
    }
    // ----------------------------------------------------

    // ----------------------------------------------------
    // 3. onCreateViewHolder: Crea (infla) el layout de cada ítem
    // ----------------------------------------------------
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_meses, parent, false) // Inflamos el diseño de tu tarjeta
        return MesViewHolder(view)
    }
    // ----------------------------------------------------

    // ----------------------------------------------------
    // 4. onBindViewHolder: Asigna el dato (el nombre del mes) a la vista
    // ----------------------------------------------------
    override fun onBindViewHolder(holder: MesViewHolder, position: Int) {
        val mesActual = meses[position]
        holder.nombreMesTextView.text = mesActual
    }
    // ----------------------------------------------------

    // ----------------------------------------------------
    // 5. getItemCount: Devuelve el total de meses
    // ----------------------------------------------------
    override fun getItemCount(): Int {
        return meses.size
    }
    // ----------------------------------------------------
}