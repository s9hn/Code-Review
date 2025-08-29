package com.team.searchlibrary.feature.books_detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.team.searchlibrary.core.router.Route
import com.team.searchlibrary.feature.books_detail.BookDetailScreen
import com.team.searchlibrary.feature.books_ui.model.BookUiModel
import kotlinx.serialization.Serializable

@Serializable
data class BookDetail(
    val id: Long,
    val title: String,
    val contents: String,
    val isbn: String,
    val publishedDate: String,
    val authors: String,
    val publisher: String,
    val price: Int,
    val salePrice: Int,
    val thumbnailUrl: String,
    val isFavorite: Boolean,
) : Route

fun NavGraphBuilder.bookDetailScreen(
    onFavoriteClick: (isFavorite: Boolean) -> Unit,
    onBackClick: () -> Unit,
) {
    composable<BookDetail> { navBackStackEntry ->
        val args = navBackStackEntry.toRoute<BookDetail>()
        BookDetailScreen(
            book = BookUiModel(
                title = args.title,
                contents = args.contents,
                isbn = args.isbn,
                publishedDate = args.publishedDate,
                authors = args.authors,
                publisher = args.publisher,
                price = args.price,
                salePrice = args.salePrice,
                thumbnailUrl = args.thumbnailUrl,
                isFavorite = args.isFavorite,
            ),
            onBackClick = onBackClick,
            onFavoriteClick = onFavoriteClick,
        )
    }
}

fun NavController.navigateToBookDetail(book: BookUiModel) {
    navigate(
        route = BookDetail(
            id = book.id,
            title = book.title,
            contents = book.contents,
            isbn = book.isbn,
            publishedDate = book.publishedDate,
            authors = book.authors,
            publisher = book.publisher,
            price = book.price,
            salePrice = book.salePrice,
            thumbnailUrl = book.thumbnailUrl,
            isFavorite = book.isFavorite,
        )
    )
}
