package com.example.pokemon_mvp.view.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon_mvp.databinding.FragmentPokemonListBinding
import com.example.pokemon_mvp.listener.SelectedListener
import com.example.pokemon_mvp.presenter.ApiStatus
import com.example.pokemon_mvp.presenter.PokemonPresenter
import com.example.pokemon_mvp.repository.GetPokemonDetailsRepository
import com.example.pokemon_mvp.view.adapter.AdapterPokemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PokemonListFragment : Fragment() {

    private var _binding: FragmentPokemonListBinding? = null
    private val binding get() = _binding!!

    private val presenter: PokemonPresenter by viewModels()
    private lateinit var adapter: AdapterPokemon
    private lateinit var recyclerView: RecyclerView

    private lateinit var listener: SelectedListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = try {
            context as SelectedListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context debe implementar el listener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // val viewModel2 = ViewModelProvider(this)[PokeViewModel::class.java]
        adapter = AdapterPokemon()
        recyclerView = binding.recyclerViewPoke
        recyclerView.adapter = adapter

        observeApiStatus()
        observeListPokemon()
        onClickItem()
    }

    private fun observeApiStatus() {
        presenter.status.observe(viewLifecycleOwner) { status->
            when (status) {
                ApiStatus.LOADING -> {
                    binding.statusOffline.visibility = View.GONE
                    binding.shimmerLoading.visibility = View.VISIBLE
                    binding.recyclerViewPoke.visibility =View.GONE
                }
                ApiStatus.ERROR -> {
                    binding.statusOffline.visibility = View.VISIBLE
                    binding.shimmerLoading.visibility = View.GONE
                    binding.recyclerViewPoke.visibility =View.GONE
                }
                ApiStatus.DONE -> {
                    binding.statusOffline.visibility = View.GONE
                    binding.shimmerLoading.visibility = View.GONE
                    binding.recyclerViewPoke.visibility =View.VISIBLE
                }
            }
        }
    }

    private fun observeListPokemon() {
        presenter.pokemonList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun onClickItem() {
        adapter.onItemClickListener = { poke ->
            //Toast.makeText(requireContext(), poke.name, Toast.LENGTH_LONG).show()
            //test(poke.id)
            listener.onSelected(poke.id)
        }
    }

    private fun test(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("tag", "llamada...")
            val res = GetPokemonDetailsRepository().pokemonDetails(id)
            Log.d("tag", "$res")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}