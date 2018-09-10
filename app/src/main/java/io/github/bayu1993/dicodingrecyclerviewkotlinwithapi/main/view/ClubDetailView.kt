package io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.main.view

import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.data.repository.model.Club

/**
 * Created by Bayu teguh pamuji on 9/8/18.
 * email : bayuteguhpamuji@gmail.com.
 */
interface ClubDetailView {
    fun showLoading()
    fun hideLoading()
    fun showClubDetail(data:List<Club>)
}