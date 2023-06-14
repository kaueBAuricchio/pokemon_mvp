package com.example.pokemon_mvp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokemon_mvp.R
import com.example.pokemon_mvp.listener.SelectedListener
import com.example.pokemon_mvp.view.fragment.DetailFragment

class MainActivity : AppCompatActivity(), SelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSelected(id: Int) {
        val bundle = Bundle()
        bundle.putInt("id", id)

        val detailFragment = DetailFragment()
        detailFragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, detailFragment)
            .addToBackStack(null)
            .commit()
    }
}