package io.github.bayu1993.dicodingrecyclerviewkotlinwithapi

import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.Unconfined
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Created by Bayu teguh pamuji on 9/19/18.
 * email : bayuteguhpamuji@gmail.com.
 */

class TestContextProvider : CoroutineContextProvider(){
    override val main: CoroutineContext = Unconfined
}