package io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.data.local.model

/**
 * Created by Bayu teguh pamuji on 9/8/18.
 * email : bayuteguhpamuji@gmail.com.
 */

data class ClubFavorite(val id: Long?, val idClub: String?, val nameClub: String?, val badgeClub: String?) {
    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val ID_CLUB: String = "ID_CLUB"
        const val NAME_CLUB: String = "NAME_CLUB"
        const val BADGE_CLUB: String = "BADGE_CLUB"
    }
}