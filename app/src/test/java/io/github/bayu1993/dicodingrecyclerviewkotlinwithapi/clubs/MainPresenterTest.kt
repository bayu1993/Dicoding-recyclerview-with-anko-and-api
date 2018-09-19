package io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.clubs

import com.google.gson.Gson
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.TestContextProvider
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.data.repository.ApiRepository
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.data.repository.TheSportDBAPI
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.data.repository.model.Club
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.data.repository.model.ClubsResponse
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.main.presenter.MainPresenter
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.main.view.MainView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/**
 * Created by Bayu teguh pamuji on 9/19/18.
 * email : bayuteguhpamuji@gmail.com.
 */

class MainPresenterTest {
    @Mock
    private lateinit var view: MainView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    private lateinit var presenter: MainPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenter(view,apiRepository,gson, TestContextProvider())
    }

    @Test
    fun testGetClubList() {
        val clubs:MutableList<Club> = mutableListOf()
        val response = ClubsResponse(clubs)
        val league = "English Premiere League"

        `when`(gson.fromJson(apiRepository.doRequest(TheSportDBAPI.getTeams(league)),
                ClubsResponse::class.java)).thenReturn(response)

        presenter.getClubs(league)
        verify(view).showClubs(clubs)
    }
}