package com.example.prizprojem.data.network

import com.example.prizprojem.data.model.Rule
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PrizApi {


    @GET("movies")
    suspend fun getRules() : Response<List<Rule>>

    companion object {
        // invoke -> MoviesApi() yazınca çalışacak fonksiyondur, özel bir keydir
        operator fun invoke(): PrizApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://60d194a45b017400178f3e51.mockapi.io/")
                .build()
                .create(PrizApi::class.java)
        }
        // singleton yapısı sağlıyor
    }
}