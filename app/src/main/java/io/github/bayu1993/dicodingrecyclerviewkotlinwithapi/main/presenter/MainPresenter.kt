package io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.main.presenter

import com.google.gson.Gson
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.main.view.MainView
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.repository.ApiRepository
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.repository.TheSportDBAPI
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.repository.model.ClubsResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by Bayu teguh pamuji on 9/1/18.
 * email : bayuteguhpamuji@gmail.com.
 */

class MainPresenter(private val view: MainView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson) {
    fun getClubs(league: String) {
        //view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBAPI.getTeams(league)),
                    ClubsResponse::class.java)
            uiThread {
                //view.hideLoading()
                view.showClubs(data.clubs)
            }
        }
    }
}