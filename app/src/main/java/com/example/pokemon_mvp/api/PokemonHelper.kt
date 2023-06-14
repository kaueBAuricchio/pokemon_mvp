package com.example.pokemon_mvp.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object PokemonHelper {

    fun getRetrofit(): Retrofit{
        return Retrofit.Builder().
        baseUrl(Constants.BASE_URL).
        addConverterFactory(MoshiConverterFactory.create()).
        build()
    }
}