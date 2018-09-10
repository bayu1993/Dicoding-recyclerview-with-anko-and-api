package io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.main.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.R
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.R.id.favorites
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.R.id.teams
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.main.ui.fragment.FavoritesFragment
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.main.ui.fragment.TeamsFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                teams -> {
                    loadTeamsFragment(savedInstanceState)
                }
                favorites -> {
                    loadFavoritesFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = teams
    }

    private fun loadTeamsFragment(savedInstanceState: Bundle?) {
        if(savedInstanceState == null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, TeamsFragment(), TeamsFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadFavoritesFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, FavoritesFragment(), FavoritesFragment::class.java.simpleName)
                    .commit()
        }
    }
}
