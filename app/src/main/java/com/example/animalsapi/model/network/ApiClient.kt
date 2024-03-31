package com.example.animalsapi.model.network

import com.example.animalsapi.model.entity.Animal
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface AnimalService {
    @Headers("X-Api-Key: prmpivhtP5mLYItk9WU5lQ==NBV9LAWocqMuzYdy")
    @GET("/v1/animals")
    fun getAnimals(@Query("name") name: String): Call<List<Animal>>
}
fun createApiService(): AnimalService {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.api-ninjas.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(AnimalService::class.java)
}
