package ru.tanpii.bookpoint.support.factory

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import ru.tanpii.bookpoint.support.configuration.WebClientBuilders

@Component
class WebClientFactory(
    private val webClientBuilders: WebClientBuilders
) {

    fun createWebClient(name: String): WebClient {
        val webClientBuilder = checkNotNull(webClientBuilders.builders[name]) {
            "WebClient builder $name properties were not found"
        }
        return webClientBuilder.build()
    }
}
