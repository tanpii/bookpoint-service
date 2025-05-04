package ru.tanpii.bookpoint

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories
import org.springframework.data.web.config.EnableSpringDataWebSupport
import org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import ru.tanpii.bookpoint.infrastructure.support.properties.BookpointKafkaProperties
import ru.tanpii.bookpoint.infrastructure.support.properties.S3Properties
import ru.tanpii.bookpoint.support.properties.InternalApiProperties
import ru.tanpii.bookpoint.support.properties.ManagementApiProperties
import ru.tanpii.bookpoint.support.properties.WebClientProperties

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
@EnableJdbcRepositories
@EnableScheduling
@EnableAspectJAutoProxy
@EnableWebMvc
@EnableConfigurationProperties(
    BookpointKafkaProperties::class,
    InternalApiProperties::class,
    ManagementApiProperties::class,
    S3Properties::class,
    WebClientProperties::class,
)
class BookpointApplication

fun main(args: Array<String>) {
    runApplication<BookpointApplication>(*args)
}
