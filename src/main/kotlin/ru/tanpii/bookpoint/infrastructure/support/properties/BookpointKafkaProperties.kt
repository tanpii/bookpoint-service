package ru.tanpii.bookpoint.infrastructure.support.properties

import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "kafka")
class BookpointKafkaProperties {
    var clusters: Map<String, KafkaProperties> = mutableMapOf()
}
