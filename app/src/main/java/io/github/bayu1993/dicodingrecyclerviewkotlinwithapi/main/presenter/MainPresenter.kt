package io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.main.presenter

import com.google.gson.Gson
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.data.repository.ApiRepository
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.data.repository.TheSportDBAPI
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.data.repository.model.ClubsResponse
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.main.view.MainView
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Created by Bayu teguh pamuji on 9/1/18.
 * email : bayuteguhpamuji@gmail.com.
 */

class MainPresenter(private val view: MainView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {
    fun getClubs(league: String) {
        //view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBAPI.getTeams(league)),
                        ClubsResponse::class.java)
            }
            view.showClubs(data.await().clubs)
        }
    }
}