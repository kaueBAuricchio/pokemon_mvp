package com.example.pokemon_mvp.response

import com.squareup.moshi.Json

data class PokemonsResults(
    @Json(name = "results") val results: List<PokemonList>
)

data class PokemonList(
    @Json(name = "name") val name: String,
    @Json(name = "url") val url: String
)
