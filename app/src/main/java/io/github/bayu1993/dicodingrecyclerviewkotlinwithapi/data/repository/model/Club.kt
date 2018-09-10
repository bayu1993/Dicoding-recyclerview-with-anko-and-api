package io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.data.repository.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Bayu teguh pamuji on 9/1/18.
 * email : bayuteguhpamuji@gmail.com.
 */

data class Club(
        @SerializedName("idTeam")
        val idClub: String? = "",
        @SerializedName("strTeam")
        val nameClub: String? = "",
        @SerializedName("strTeamBadge")
        val badgeClub: String? = "",
        @SerializedName("intFormedYear")
        var teamFormedYear: String? = null,

        @SerializedName("strStadium")
        var teamStadium: String? = null,

        @SerializedName("strDescriptionEN")
        var teamDescription: String? = null
)