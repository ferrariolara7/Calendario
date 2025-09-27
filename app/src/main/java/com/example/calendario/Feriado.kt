package com.example.calendario

data class Feriado(
    val fecha: String,  // Ej: "2 de abril"
    val nombre: String,
    val tipo: String // "Feriado Nacional", "No Laborable"
)

val feriadosPorMes = mapOf(
    "Enero" to listOf(
        Feriado("1 de enero","Año Nuevo", "Feriado Nacional"),
        Feriado("6 de enero","Reyes Magos", "No Laborable")
    ),
    "Febrero" to listOf(
        Feriado("3 de marzo","Carnaval", "Feriado Nacional"),
        Feriado("4 de marzo","Carnaval", "Feriado Nacional")
    ),
    "Marzo" to listOf(
        Feriado("24 de marzo","Día Nacional de la Memoria por la Verdad y la Justicia", "Feriado Nacional")
    ),
    "Abril" to listOf(
        Feriado("2 de abril","Día del Veterano y de los Caídos en la Guerra de Malvinas", "Feriado Nacional"),
        Feriado("16 de abril","Jueves Santo", "No Laborable"),
        Feriado("17 de abril","Viernes Santo", "Feriado Nacional")
    ),
    "Mayo" to listOf(
        Feriado("1 de mayo","Día del Trabajador", "Feriado Nacional"),
        Feriado("25 de mayo","Día de la Revolución de Mayo", "Feriado Nacional")
    ),
    "Junio" to listOf(
        Feriado("16 de junio","Paso a la Inmortalidad del General Martín Miguel de Güemes", "Feriado Nacional"),
        Feriado("20 de junio","Paso a la Inmortalidad del General Manuel Belgrano", "Feriado Nacional")
    ),
    "Julio" to listOf(
        Feriado("9 de julio","Día de la Independencia", "Feriado Nacional")
    ),
    "Agosto" to listOf(
        Feriado("17 de agosto","Paso a la Inmortalidad del General José de San Martín", "Feriado Nacional")
    ),
    "Septiembre" to emptyList(), // 👈 así lo cargas vacío
    "Octubre" to listOf(
        Feriado("13 de octubre","Día del Respeto a la Diversidad Cultural", "Feriado Nacional")
    ),
    "Noviembre" to listOf(
        Feriado("20 de noviembre","Día de la Soberanía Nacional", "Feriado Nacional")
    ),
    "Diciembre" to listOf(
        Feriado("8 de diciembre","Inmaculada Concepción de María", "Feriado Nacional"),
        Feriado("25 de diciembre","Navidad", "Feriado Nacional")
    )
)

