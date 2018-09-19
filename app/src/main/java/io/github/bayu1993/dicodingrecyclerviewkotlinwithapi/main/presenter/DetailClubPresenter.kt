package io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.main.presenter

import com.google.gson.Gson
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.data.repository.ApiRepository
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.data.repository.TheSportDBAPI
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.data.repository.model.ClubsResponse
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.main.view.ClubDetailView
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

/**
 * Created by Bayu teguh pamuji on 9/8/18.
 * email : bayuteguhpamuji@gmail.com.
 */

class DetailClubPresenter(private val view: ClubDetailView,
                          private val api: ApiRepository,
                          private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {
    fun getTeamDetail(teamId: String) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(api.doRequest(TheSportDBAPI.getClubDetail(teamId)),
                        ClubsResponse::class.java)
            }
            view.showClubDetail(data.await().clubs)
            view.hideLoading()
        }

    }
}