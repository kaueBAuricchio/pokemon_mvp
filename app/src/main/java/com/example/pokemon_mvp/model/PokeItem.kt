package com.example.pokemon_mvp.model

import com.example.pokemon_mvp.response.PokemonList
import java.util.Locale

data class PokeItem(
    val id: Int,
    val name: String,
    val img: String
) {
    val formatId = "N° ${id.toString().padStart(3,'0')}"
}

private const val URL_RAW = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"

fun PokemonList.toDomain(): PokeItem{
    val arrayUrl = url.split("/")
    val id = arrayUrl[arrayUrl.size - 2].toInt()
    val name = name.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
    }
    val img = "$URL_RAW$id.png"
    return PokeItem(id, name, img)
}