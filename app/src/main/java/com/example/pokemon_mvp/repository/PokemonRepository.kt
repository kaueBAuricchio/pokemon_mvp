package com.example.pokemon_mvp.repository

import com.example.pokemon_mvp.api.ApiService
import com.example.pokemon_mvp.model.PokeItem
import com.example.pokemon_mvp.model.PokeItemDetails
import com.example.pokemon_mvp.model.toDomain

class PokemonRepository {

    private val api = ApiService()

    suspend fun getAllPokemons(): List<PokeItem> {
        val response = api.getPokemons()
        return response.map { it.toDomain() }
    }

    suspend fun getPokemonDetails(id: Int): PokeItemDetails? {
        val response = api.getDetailsPokemon(id)
        return response?.toDomain()
    }
}