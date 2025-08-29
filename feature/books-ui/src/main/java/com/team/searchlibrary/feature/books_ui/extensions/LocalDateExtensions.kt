package com.team.searchlibrary.feature.books_ui.extensions

import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

fun String.toLocalDate(): LocalDate {
    val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")
    return LocalDate.parse(this, formatter)
}

fun LocalDate.toFormattedDate(): String {
    return format(
        DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")
    )
}

fun Int.toFormattedPrice(): String {
    return NumberFormat.getNumberInstance(Locale.KOREA).format(this) + "원"
}
