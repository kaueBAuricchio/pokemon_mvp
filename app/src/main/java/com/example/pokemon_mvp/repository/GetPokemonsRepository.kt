package com.example.pokemon_mvp.repository

import com.example.pokemon_mvp.model.PokeItem

class GetPokemonsRepository {
    private val repository = PokemonRepository()

    suspend fun listAll(): List<PokeItem> {
        return repository.getAllPokemons()
    }
}