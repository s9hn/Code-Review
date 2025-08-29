package com.team.searchlibrary.data.search.datasource

import com.team.searchlibrary.core.network.LibraryApi
import com.team.searchlibrary.core.network.model.SearchedBooksResponseDto
import javax.inject.Inject

internal class DefaultBookRemoteDataSource @Inject constructor(
    private val libraryApi: LibraryApi,
) : BookRemoteDataSource {
    override suspend fun getSearchedBooks(
        query: String,
        sort: String,
        page: Int,
        size: Int,
    ): Result<SearchedBooksResponseDto> = runCatching {
        libraryApi.getSearchedBook(
            query = query,
            sort = sort.lowercase(),
            page = page,
            size = size,
            target = null,
        )
    }
}
