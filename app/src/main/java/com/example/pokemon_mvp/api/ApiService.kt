package com.example.pokemon_mvp.api

import com.example.pokemon_mvp.response.PokemonDetails
import com.example.pokemon_mvp.response.PokemonList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ApiService {
    private val retrofit = PokemonHelper.getRetrofit()

    suspend fun getPokemons(): List<PokemonList> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(PokemonApi::class.java).getListPokemon()
            response.body()?.results?: emptyList()
        }
    }

    suspend fun getDetailsPokemon(id: Int): PokemonDetails?{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(PokemonApi::class.java).getDetailsPokemon(id)
            response.body()
        }
    }
}