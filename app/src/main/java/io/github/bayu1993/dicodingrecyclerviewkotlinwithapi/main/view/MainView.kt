package io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.main.view

import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.repository.model.Club

/**
 * Created by Bayu teguh pamuji on 9/1/18.
 * email : bayuteguhpamuji@gmail.com.
 */

interface MainView{
    //fun showLoading()
    //fun hideLoading()
    fun showClubs(data : List<Club>)
}