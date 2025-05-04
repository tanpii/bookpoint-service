package ru.tanpii.bookpoint.domain.service

import org.springframework.stereotype.Service
import ru.tanpii.bookpoint.api.model.request.AddGenreRequest
import ru.tanpii.bookpoint.domain.model.entity.GenreEntity
import ru.tanpii.bookpoint.domain.repository.GenreRepository
import ru.tanpii.bookpoint.support.mapper.toDto


@Service
class GenreService(
    private val genreRepository: GenreRepository
) {

    fun getGenres() = genreRepository.findAll().map { it.toDto() }

    fun getGenresPageByName(text: String) =
        genreRepository.findAllByGenreNameContainingIgnoreCase(text).map { it.toDto() }
}
