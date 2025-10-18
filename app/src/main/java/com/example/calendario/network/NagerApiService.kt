package com.example.calendario.network

import com.example.calendario.data.PublicHoliday
import retrofit2.http.GET
import retrofit2.http.Path

interface NagerApiService {
    @GET("api/v3/publicholidays/{year}/{countryCode}")
    suspend fun getPublicHolidays(
        @Path("year") year: Int,
        @Path("countryCode") countryCode: String
    ): List<PublicHoliday>
}
