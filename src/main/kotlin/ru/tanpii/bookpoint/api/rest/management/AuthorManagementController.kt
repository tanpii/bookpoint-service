package ru.tanpii.bookpoint.api.rest.management

import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.tanpii.bookpoint.api.model.request.AddAuthorRequest
import ru.tanpii.bookpoint.domain.service.AuthorService
import ru.tanpii.bookpoint.support.reflection.ManagementApi

@ManagementApi
@RestController
@RequestMapping("/api/v1/management/author")
class AuthorManagementController(
    private val authorService: AuthorService,
) {

    @PostMapping
    fun addAuthor(@ModelAttribute request: AddAuthorRequest) = authorService.addAuthor(request)
}
