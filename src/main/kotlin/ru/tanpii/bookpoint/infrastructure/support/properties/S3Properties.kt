package ru.tanpii.bookpoint.infrastructure.support.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "cloud.aws")
class S3Properties {
    lateinit var credentials: Credentials
    lateinit var region: String
    lateinit var endpoint: String
    lateinit var bucketName: Map<String, String>

    data class Credentials(
        val accessKey: String,
        val secretKey: String
    )
}
