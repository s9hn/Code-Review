package com.team.searchlibrary.core.network

import com.team.searchlibrary.core.network.model.SearchedBooksResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface LibraryApi {

    @GET("search/book")
    suspend fun getSearchedBook(
        @Query("query") query: String,
        @Query("sort") sort: String?,
        @Query("page") page: Int?,
        @Query("size") size: Int?,
        @Query("target") target: String?,
    ): SearchedBooksResponseDto
}
