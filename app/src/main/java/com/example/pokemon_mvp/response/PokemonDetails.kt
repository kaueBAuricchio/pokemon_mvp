package com.example.pokemon_mvp.response

import com.squareup.moshi.Json

data class PokemonDetails(
    @Json(name = "id") val id: Int,
    @Json(name = "height") val height: Int,
    @Json(name = "name") val name: String,
    @Json(name = "sprites") val sprites: Sprites,
    @Json(name = "stats") val stats: List<Stats>,
    @Json(name = "types") val types: List<Types>,
    @Json(name = "weight") val weight: Int
)

data class Sprites(
    @Json(name = "other") val other: Other
)

data class Other(
    @Json(name = "official-artwork") val officialArtwork: OfficialArtwork
)

data class OfficialArtwork(
    @Json(name = "front_default") val img: String
)

data class Stats(
    @Json(name = "base_stat") val statValue: Int,
    @Json(name = "stat") val stat: Stat
)

data class Stat(
    @Json(name = "name") val statName: String
)

data class Types(
    @Json(name = "type") val type: Type
)

data class Type(
    @Json(name = "name") val name: String
)

