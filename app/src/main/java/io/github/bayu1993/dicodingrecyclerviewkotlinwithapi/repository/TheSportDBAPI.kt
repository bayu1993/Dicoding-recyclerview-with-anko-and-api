package io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.repository

import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.BuildConfig

/**
 * Created by Bayu teguh pamuji on 9/1/18.
 * email : bayuteguhpamuji@gmail.com.
 */

object TheSportDBAPI{
    fun getTeams(league:String?):String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/search_all_teams.php?l=" + league
    }
}