package ru.tanpii.bookpoint.api.rest.public

import org.springframework.web.bind.annotation.*
import ru.tanpii.bookpoint.api.model.request.CommentRequest
import ru.tanpii.bookpoint.domain.service.CommentService
import ru.tanpii.bookpoint.support.security.softUserContext
import ru.tanpii.bookpoint.support.security.userContext
import java.util.*

@RestController
@RequestMapping("/api/v1/comments")
class CommentController(
    private val commentService: CommentService
) {

    @GetMapping("/{bookId}")
    fun getBookComments(@PathVariable bookId: Long, @RequestParam page: Int) = softUserContext {
        commentService.getBookComments(bookId, page)
    }

    @GetMapping("/user")
    fun getUserComments(@RequestParam page: Int) = userContext {
        commentService.getUserComments(page)
    }

    @PostMapping("/{bookId}")
    fun addComment(@PathVariable bookId: Long, @RequestBody request: CommentRequest) = userContext {
        commentService.addComment(bookId, request)
    }

    @DeleteMapping
    fun deleteComment(@RequestParam commentId: UUID) = userContext {
        commentService.deleteComment(commentId)
    }
}
