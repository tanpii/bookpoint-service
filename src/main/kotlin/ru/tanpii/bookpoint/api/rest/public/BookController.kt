package ru.tanpii.bookpoint.api.rest.public

import org.springframework.web.bind.annotation.*
import ru.tanpii.bookpoint.domain.service.BookService
import ru.tanpii.bookpoint.support.security.softUserContext

@RestController
@RequestMapping("api/v1/book")
class BookController(
    private val bookService: BookService
) {

    @GetMapping("/info/{bookId}")
    fun getBookInfo(@PathVariable bookId: Long) = softUserContext {
        bookService.findBook(bookId)
    }

    @GetMapping("/list")
    fun getBooksPage(
        @RequestParam page: Int,
        @RequestParam bookName: String?,
        @RequestParam author: String?,
        @RequestParam genres: List<Int>?
    ) = bookService.findBooksPage(page, bookName, author, genres)

    @GetMapping("/author/{authorId}")
    fun getAuthorBooks(@PathVariable authorId: Long, @RequestParam page: Int) =
        bookService.findAuthorBooksPage(authorId, page)

    @GetMapping("/genre/{genreId}")
    fun getBooksByGenre(@PathVariable genreId: Long, @RequestParam page: Int) =
        bookService.findBooksByGenre(genreId, page)
}
