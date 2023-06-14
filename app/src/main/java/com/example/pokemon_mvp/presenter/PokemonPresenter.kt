package com.example.pokemon_mvp.presenter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon_mvp.model.PokeItem
import com.example.pokemon_mvp.model.PokeItemDetails
import com.example.pokemon_mvp.repository.GetPokemonDetailsRepository
import com.example.pokemon_mvp.repository.GetPokemonsRepository
import com.example.pokemon_mvp.view.fragment.DetailFragment
import kotlinx.coroutines.launch

enum class ApiStatus {LOADING, ERROR, DONE}

class PokemonPresenter: ViewModel() {

    private var _pokemonList = MutableLiveData<List<PokeItem>>()
    val pokemonList: LiveData<List<PokeItem>>
        get() = _pokemonList

    private var _pokemonDetails = MutableLiveData<PokeItemDetails>()
    val pokemonDetails: LiveData<PokeItemDetails>
        get() = _pokemonDetails

    private var _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    init {
        getAllPokemons()
        getPokemonDetails(DetailFragment.pokemonId)
    }

    fun getAllPokemons() {
         _status.value = ApiStatus.LOADING
         viewModelScope.launch {
             try {
                 _pokemonList.value = GetPokemonsRepository().listAll()
                 _status.value = ApiStatus.DONE
             } catch (e: Exception) {
                 _status.value = ApiStatus.ERROR
                 Log.d("List Empty", "${e.message}")
                 _pokemonList.value = listOf()
             }
         }
    }

     fun getPokemonDetails(id: Int) {
         _status.value = ApiStatus.LOADING
         viewModelScope.launch {
         try {
             _pokemonDetails.value = GetPokemonDetailsRepository().pokemonDetails(id)
             _status.value = ApiStatus.DONE
         } catch (e: Exception) {
             _status.value = ApiStatus.ERROR
             Log.d("Pokemon not found", "${e.message}")
         }

         }
     }

}