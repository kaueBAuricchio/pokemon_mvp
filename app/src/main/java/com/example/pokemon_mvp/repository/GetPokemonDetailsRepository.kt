package com.example.pokemon_mvp.repository

import com.example.pokemon_mvp.model.PokeItemDetails

class GetPokemonDetailsRepository {
    private val repository = PokemonRepository()

    suspend fun pokemonDetails(id: Int): PokeItemDetails? {
        return repository.getPokemonDetails(id)
    }
}