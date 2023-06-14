package com.example.pokemon_mvp.api

import com.example.pokemon_mvp.response.PokemonDetails
import com.example.pokemon_mvp.response.PokemonsResults
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {

    @GET(value = "pokemon?limit=1154")
    suspend fun getListPokemon(): Response<PokemonsResults>

    @GET(value = "pokemon/{id}")
    suspend fun getDetailsPokemon(@Path("id") id: Int): Response<PokemonDetails>
}