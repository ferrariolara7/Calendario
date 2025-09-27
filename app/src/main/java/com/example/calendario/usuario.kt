package com.example.calendario.model

    import androidx.room.ColumnInfo
    import androidx.room.Entity
    import androidx.room.PrimaryKey

    @Entity(tableName = "usuario_entity")

    data class usuario(
        @ColumnInfo(name = "nombre") var nombre: String,
        @ColumnInfo(name = "email") var email: String,
        @ColumnInfo(name = "contrasena") var contrasena: String
    ) {
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0
    }
