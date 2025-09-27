
    package com.example.calendario.model

    import androidx.room.Dao
    import androidx.room.Insert
    import androidx.room.Query

    @Dao
    interface usuarioDAO {

        @Insert
        suspend fun insert(usuario: usuario) // Para registrar un nuevo usuario

        @Query("SELECT * FROM usuario_entity WHERE email = :email AND contrasena = :contrasena")
        suspend fun login(email: String, contrasena: String): usuario?
        // Devuelve el usuario si encuentra coincidencia, o null si no existe
        @Query("SELECT * FROM usuario_entity WHERE email = :email")
        suspend fun getByEmail(email: String): usuario?
    }
