package ru.tanpii.bookpoint.domain.service

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import ru.tanpii.bookpoint.api.model.request.AddAuthorRequest
import ru.tanpii.bookpoint.domain.model.entity.AuthorEntity
import ru.tanpii.bookpoint.domain.repository.AuthorRepository
import ru.tanpii.bookpoint.support.mapper.toDto
import ru.tanpii.bookpoint.support.mapper.toPage
import ru.tanpii.bookpoint.support.pageRequest

@Service
class AuthorService(
    private val authorRepository: AuthorRepository,

    @Qualifier("authorS3ImageService")
    private val imageService: ImageService
) {

    fun getAuthorById(authorId: Long) = authorRepository.findById(authorId).orElseThrow { RuntimeException() }.toDto()

    fun getAuthorsPage(page: Int) = pageRequest(page) {
        authorRepository.findAll(it)
    }.toPage()

    fun getAuthorsPageByName(page: Int, text: String) = pageRequest(page) {
        authorRepository.findAllByAuthorNameContainingIgnoreCase(text, it)
    }.toPage()

    fun addAuthor(request: AddAuthorRequest) = request.let {
        AuthorEntity(
            authorName = it.authorName,
            authorPhotoUrl = imageService.uploadImage(it.authorPhoto.inputStream, request.authorName)
        ).let { entity -> authorRepository.save(entity) }
    }
}
