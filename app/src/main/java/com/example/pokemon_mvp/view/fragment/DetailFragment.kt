package com.example.pokemon_mvp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.pokemon_mvp.R
import com.example.pokemon_mvp.databinding.FragmentDetailBinding
import com.example.pokemon_mvp.presenter.ApiStatus
import com.example.pokemon_mvp.presenter.PokemonPresenter
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val presenter: PokemonPresenter by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pokemonId = it.getInt("id")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeStatus()
        observe()
    }

    private fun observe() {
        presenter.pokemonDetails.observe(viewLifecycleOwner) { pokemon ->

            if (pokemon.types.size > 1) {
                binding.tvType1.text = pokemon.types[0]
                binding.tvType1
                binding.tvType2.text = pokemon.types[1]
                binding.tvType2.visibility = View.VISIBLE
            } else {
                binding.tvType1.text = pokemon.types[0]
                binding.tvType2.visibility = View.GONE
            }

            Picasso.get().load(pokemon.img)
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
                .into(binding.image)

            binding.collapsingToolbar.title = pokemon.name
            binding.tvHp.text = pokemon.hp.toString()
            binding.speed.text = pokemon.speed.toString()
            binding.attack.text = pokemon.attack.toString()
            binding.defense.text = pokemon.defense.toString()
            binding.specialAttack.text = pokemon.specialAttack.toString()
            binding.specialDefense.text = pokemon.specialDefense.toString()
            binding.height.text = getString(R.string.metro, pokemon.height.toString())
            binding.weight.text = getString(R.string.kilo, pokemon.weight.toString())
        }
    }

    private fun observeStatus() {
        presenter.status.observe(viewLifecycleOwner) { status ->
            when (status) {
                ApiStatus.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.appBar.visibility = View.GONE
                    binding.nestedScrollView.visibility = View.GONE
                    binding.statusOffline.visibility = View.GONE
                }
                ApiStatus.DONE -> {
                    binding.progressBar.visibility = View.GONE
                    binding.appBar.visibility = View.VISIBLE
                    binding.nestedScrollView.visibility = View.VISIBLE
                    binding.statusOffline.visibility = View.GONE
                }
                ApiStatus.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    binding.appBar.visibility = View.GONE
                    binding.nestedScrollView.visibility = View.GONE
                    binding.statusOffline.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        var pokemonId = 0
    }
}