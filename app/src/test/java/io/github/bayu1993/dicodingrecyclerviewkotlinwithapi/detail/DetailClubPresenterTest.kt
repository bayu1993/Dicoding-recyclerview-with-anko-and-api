package io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.detail

import com.google.gson.Gson
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.TestContextProvider
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.data.repository.ApiRepository
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.data.repository.TheSportDBAPI
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.data.repository.model.Club
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.data.repository.model.ClubsResponse
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.main.presenter.DetailClubPresenter
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.main.view.ClubDetailView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by Bayu teguh pamuji on 9/19/18.
 * email : bayuteguhpamuji@gmail.com.
 */

class DetailClubPresenterTest {
    @Mock
    private lateinit var view: ClubDetailView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    private lateinit var presenter: DetailClubPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailClubPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getClubDetailTest() {
        val clubs: MutableList<Club> = mutableListOf()
        val response = ClubsResponse(clubs)
        val id = "1234"

        Mockito.`when`(gson.fromJson(apiRepository.doRequest(TheSportDBAPI.getClubDetail(id)),
                ClubsResponse::class.java)).thenReturn(response)
        presenter.getTeamDetail(id)
        Mockito.verify(view).showLoading()
        Mockito.verify(view).showClubDetail(clubs)
        Mockito.verify(view).hideLoading()
    }
}