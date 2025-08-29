package com.team.searchlibrary.data.search.datasource

import com.team.searchlibrary.core.network.model.SearchedBooksResponseDto

internal interface BookRemoteDataSource {

    suspend fun getSearchedBooks(
        query: String,
        sort: String,
        page: Int,
        size: Int,
    ): Result<SearchedBooksResponseDto>
}
