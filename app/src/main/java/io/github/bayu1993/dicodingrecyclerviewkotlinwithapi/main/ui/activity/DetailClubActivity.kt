package io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.main.ui.activity

import android.database.sqlite.SQLiteConstraintException
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.R
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.data.local.database
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.data.local.model.ClubFavorite
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.data.repository.ApiRepository
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.data.repository.model.Club
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.main.presenter.DetailClubPresenter
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.main.view.ClubDetailView
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.utils.invisible
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.utils.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class DetailClubActivity : AppCompatActivity(), ClubDetailView {
    private lateinit var presenter: DetailClubPresenter
    private lateinit var club: Club
    private lateinit var id: String
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var teamBadge: ImageView
    private lateinit var teamName: TextView
    private lateinit var teamFormedYear: TextView
    private lateinit var teamStadium: TextView
    private lateinit var teamDesciption: TextView

    private var manuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Team Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        id = intent.getStringExtra("id")

        linearLayout {
            lparams(matchParent, wrapContent)
            orientation = LinearLayout.VERTICAL
            backgroundColor = Color.WHITE
            swipeRefreshLayout = swipeRefreshLayout {
                setColorSchemeResources(
                        R.color.colorAccent,
                        android.R.color.holo_green_dark,
                        android.R.color.holo_green_light,
                        android.R.color.holo_red_light)
                scrollView {
                    isVerticalScrollBarEnabled = false
                    relativeLayout {
                        lparams(matchParent, wrapContent)
                        linearLayout {
                            lparams(matchParent, wrapContent)
                            padding = dip(10)
                            orientation = LinearLayout.VERTICAL
                            gravity = Gravity.CENTER_HORIZONTAL
                            teamBadge = imageView {}.lparams(height = dip(75))
                            teamName = textView {
                                this.gravity = Gravity.CENTER
                                textSize = 20f
                                textColor = ContextCompat.getColor(context, R.color.colorPrimary)
                            }.lparams {
                                topMargin = dip(10)
                            }
                            teamFormedYear = textView {
                                this.gravity = Gravity.CENTER
                            }
                            teamStadium = textView {
                                this.gravity = Gravity.CENTER
                                textColor = ContextCompat.getColor(context, R.color.colorPrimary)
                            }
                            teamDesciption = textView().lparams {
                                margin = dip(10)
                            }
                        }
                        progressBar = progressBar { }.lparams {
                            centerHorizontally()
                        }
                    }
                }
            }
        }

        favoriteState()
        val request = ApiRepository()
        val gson = Gson()

        presenter = DetailClubPresenter(this, request, gson)
        presenter.getTeamDetail(id)
        swipeRefreshLayout.onRefresh {
            presenter.getTeamDetail(id)
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showClubDetail(data: List<Club>) {
        club = Club(data[0].idClub,
                data[0].nameClub,
                data[0].badgeClub,
                data[0].teamFormedYear,
                data[0].teamStadium,
                data[0].teamDescription)
        swipeRefreshLayout.isRefreshing = false
        Picasso.get().load(club.badgeClub).into(teamBadge)
        teamName.text = club.nameClub
        teamDesciption.text = club.teamDescription
        teamFormedYear.text = club.teamFormedYear
        teamStadium.text = club.teamStadium
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        manuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()
                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(
                        ClubFavorite.TABLE_FAVORITE,
                        ClubFavorite.ID_CLUB to club.idClub,
                        ClubFavorite.NAME_CLUB to club.nameClub,
                        ClubFavorite.BADGE_CLUB to club.badgeClub
                )
            }
            snackbar(swipeRefreshLayout, "add to favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(swipeRefreshLayout, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(ClubFavorite.TABLE_FAVORITE, "(ID_CLUB = {id})",
                        "id" to id)
            }
            snackbar(swipeRefreshLayout, "Remove frome favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(swipeRefreshLayout, e.localizedMessage).show()
        }
    }

    private fun setFavorite(){
        if(isFavorite){
            manuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        }else{
            manuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this,R.drawable.ic_add_to_favorites)
        }
    }

    private fun favoriteState(){
        database.use {
            val result = select(ClubFavorite.TABLE_FAVORITE).whereArgs("(ID_CLUB = {id})",
                    "id" to id)
            val favorites = result.parseList(classParser<ClubFavorite>())
            if (!favorites.isEmpty()) isFavorite = true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
