package ru.mirea.kornilov.sway.data.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import ru.mirea.kornilov.sway.data.network.models.PlaceDto

interface PlaceApi {
    @GET("SWAY/data/src/main/assets/places.json")
    fun getPlaces(): Call<List<PlaceDto>>

    companion object {
        private const val BASE_URL =
            "https://raw.githubusercontent.com/krl76/MobileDevelopment2/main/"

        fun create(): PlaceApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PlaceApi::class.java)
        }
    }
}
