package ru.tanpii.bookpoint.api.rest.public

import org.springframework.web.bind.annotation.*
import ru.tanpii.bookpoint.domain.service.AuthorService

@RestController
@RequestMapping("api/v1/author")
class AuthorController(
    private val authorService: AuthorService
) {
    @GetMapping("/list")
    fun getAuthors(@RequestParam page: Int) = authorService.getAuthorsPage(page)

    @GetMapping("/{authorId}")
    fun getAuthorById(@PathVariable authorId: Long) = authorService.getAuthorById(authorId)

    @GetMapping("/name")
    fun getAuthorsByName(@RequestParam page: Int, @RequestParam text: String) =
        authorService.getAuthorsPageByName(page, text)
}