package ru.tanpii.bookpoint.domain.service

import java.io.InputStream

interface ImageService {
    fun uploadImage(file: InputStream, name: String): String

    fun getImageUrl(name: String): String
}
