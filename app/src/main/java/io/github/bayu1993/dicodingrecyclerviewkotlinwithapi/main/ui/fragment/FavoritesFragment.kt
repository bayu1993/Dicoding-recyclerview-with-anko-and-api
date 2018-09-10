package io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.main.ui.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.R
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.data.local.database
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.data.local.model.ClubFavorite
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.main.adapter.ClubFavoriteAdapter
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.main.ui.activity.DetailClubActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

/**
 * Created by Bayu teguh pamuji on 9/7/18.
 * email : bayuteguhpamuji@gmail.com.
 */

class FavoritesFragment : Fragment(), AnkoComponent<Context> {
    private lateinit var swiperefresh: SwipeRefreshLayout
    private lateinit var listClubFav: RecyclerView
    private lateinit var adapter: ClubFavoriteAdapter
    private var favoriteClubs: MutableList<ClubFavorite> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = ClubFavoriteAdapter(favoriteClubs) {
            ctx.startActivity<DetailClubActivity>("id" to "${it.idClub}")
        }

        listClubFav.adapter = adapter
        showClubsFav()
        swiperefresh.onRefresh {
            showClubsFav()
        }
    }

    private fun showClubsFav() {
        context?.database?.use {
            swiperefresh.isRefreshing = false
            val result = select(ClubFavorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<ClubFavorite>())
            favoriteClubs.clear()
            favoriteClubs.addAll(favorite)
            adapter.notifyDataSetChanged()
            Log.d("cek localdb", "${Gson().toJsonTree(favorite)}")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View {
        return with(ui) {
            linearLayout {
                lparams(matchParent, wrapContent)
                topPadding = dip(16)
                leftPadding = dip(16)
                rightPadding = dip(16)
                swiperefresh = swipeRefreshLayout {
                    setColorSchemeResources(
                            R.color.colorAccent,
                            android.R.color.holo_green_light,
                            android.R.color.holo_orange_light,
                            android.R.color.holo_red_light
                    )
                    listClubFav = recyclerView {
                        lparams(matchParent, wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }
                }
            }
        }
    }

}