package io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.api

import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.data.repository.ApiRepository
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

/**
 * Created by Bayu teguh pamuji on 9/19/18.
 * email : bayuteguhpamuji@gmail.com.
 */

class ApiRepositoryTest{
    @Test
    fun testDoRequest(){
        val apiRepository = mock(ApiRepository::class.java)
        val url = "https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League"
        apiRepository.doRequest(url)
        verify(apiRepository).doRequest(url)
    }
}