package ru.tanpii.bookpoint.api.rest.public

import org.springframework.web.bind.annotation.*
import ru.tanpii.bookpoint.domain.service.RentService
import java.util.*

@RestController
@RequestMapping("/api/v1/rent")
class RentController(
    private val rentService: RentService
) {

    @GetMapping("/{userId}/history")
    fun getRentHistory(@RequestParam page: Int, @PathVariable userId: String) =
        rentService.getClientRentHistory(page, UUID.fromString(userId))
}
