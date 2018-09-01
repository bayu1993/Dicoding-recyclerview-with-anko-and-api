package io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.repository

import java.net.URL

/**
 * Created by Bayu teguh pamuji on 9/1/18.
 * email : bayuteguhpamuji@gmail.com.
 */

class ApiRepository{
    fun doRequest(url:String):String{
        return URL(url).readText()
    }
}