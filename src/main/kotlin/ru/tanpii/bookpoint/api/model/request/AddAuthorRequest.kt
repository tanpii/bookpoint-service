package ru.tanpii.bookpoint.api.model.request

import org.springframework.web.multipart.MultipartFile

data class AddAuthorRequest(
    val authorName: String,
    val authorPhoto: MultipartFile
)
