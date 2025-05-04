package ru.tanpii.bookpoint.api.rest.management

import org.springframework.web.bind.annotation.*
import ru.tanpii.bookpoint.domain.service.RentService
import ru.tanpii.bookpoint.support.reflection.ManagementApi

@ManagementApi
@RestController
@RequestMapping("/api/v1/management/rent")
class RentManagementController(
    private val rentService: RentService,
) {

    @PostMapping("/{bookId}")
    fun acceptClientRent(@PathVariable bookId: Long) = rentService.acceptRent(bookId)

    @DeleteMapping("/{bookId}")
    fun endClientRent(@PathVariable bookId: Long) = rentService.endRent(bookId)
}
