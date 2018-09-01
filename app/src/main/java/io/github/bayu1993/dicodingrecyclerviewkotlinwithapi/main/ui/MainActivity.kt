package io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.main.ui

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.*
import com.google.gson.Gson
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.R
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.R.color.colorAccent
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.main.adapter.ClubAdapter
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.main.presenter.MainPresenter
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.main.view.MainView
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.repository.ApiRepository
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.repository.model.Club
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.utils.invisible
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.utils.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class MainActivity : AppCompatActivity(),MainView {

    lateinit var listClubs: RecyclerView
    //lateinit var progressBar: ProgressBar
    lateinit var swipeRefresh: SwipeRefreshLayout

    private lateinit var spinner: Spinner
    private lateinit var leagueName:String
    private lateinit var clubAdapter:ClubAdapter
    private lateinit var mainPresenter:MainPresenter
    private var clubs:MutableList<Club> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        linearLayout {
            lparams(matchParent, wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            rightPadding = dip(16)
            leftPadding = dip(16)

            spinner = spinner()
            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)
                relativeLayout {
                    lparams(matchParent, wrapContent)
                    listClubs = recyclerView {
                        lparams(matchParent, wrapContent)
                        layoutManager = LinearLayoutManager(ctx )
                    }
                    /*progressBar = progressBar {
                    }.lparams{
                        centerHorizontally()
                    }*/
                }
            }
        }

        clubAdapter = ClubAdapter(clubs)
        listClubs.adapter = clubAdapter

        val requset = ApiRepository()
        val gson =Gson()
        mainPresenter = MainPresenter(this, requset, gson)

        val spinnerItems = resources.getStringArray(R.array.league_array)
        val spinnerAdapter = ArrayAdapter(ctx,R.layout.support_simple_spinner_dropdown_item,spinnerItems)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()
                leagueName = leagueName.replace(" ","%20")
                mainPresenter.getClubs(leagueName)
            }

        }
        swipeRefresh.onRefresh {
            mainPresenter.getClubs(leagueName)
        }
    }

    /*override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }*/

    override fun showClubs(data: List<Club>) {
        swipeRefresh.isRefreshing = false
        clubs.clear()
        clubs.addAll(data)
        clubAdapter.notifyDataSetChanged()
    }
}
