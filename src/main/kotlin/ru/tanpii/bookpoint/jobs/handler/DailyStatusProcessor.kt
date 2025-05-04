package ru.tanpii.bookpoint.jobs.handler

import ru.tanpii.bookpoint.domain.model.type.DailyJobType

interface DailyStatusProcessor {
    val event: DailyJobType

    fun process()
}
