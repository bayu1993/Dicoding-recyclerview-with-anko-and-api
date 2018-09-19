package io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.data.repository

import android.net.Uri
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
    fun getClubDetail(teamId:String?):String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/lookupteam.php?id=" + teamId
        /*return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("lookupteam.php")
                .appendQueryParameter("id",teamId)
                .build()
                .toString()*/
    }
}