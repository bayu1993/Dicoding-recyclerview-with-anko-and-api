package io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.data.repository.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Bayu teguh pamuji on 9/1/18.
 * email : bayuteguhpamuji@gmail.com.
 */

data class ClubsResponse(
        @SerializedName("teams")
        val clubs:List<Club>
)