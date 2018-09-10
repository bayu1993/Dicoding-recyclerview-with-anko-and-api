package io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.main.presenter

import com.google.gson.Gson
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.main.view.ClubDetailView
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.data.repository.ApiRepository
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.data.repository.TheSportDBAPI
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.data.repository.model.ClubsResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by Bayu teguh pamuji on 9/8/18.
 * email : bayuteguhpamuji@gmail.com.
 */

class DetailClubPresenter(private val view: ClubDetailView,
                          private val api: ApiRepository,
                          private val gson: Gson) {
    fun getTeamDetail(teamId: String) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(api.doRequest(TheSportDBAPI.getClubDetail(teamId)),
                    ClubsResponse::class.java)
            uiThread {
                view.hideLoading()
                view.showClubDetail(data.clubs)
            }
        }

    }
}