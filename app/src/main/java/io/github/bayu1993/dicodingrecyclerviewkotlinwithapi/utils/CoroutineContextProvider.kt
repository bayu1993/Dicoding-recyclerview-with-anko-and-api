package io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.utils

import kotlinx.coroutines.experimental.android.UI
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Created by Bayu teguh pamuji on 9/19/18.
 * email : bayuteguhpamuji@gmail.com.
 */
open class CoroutineContextProvider{
    open val main: CoroutineContext by lazy { UI}
}