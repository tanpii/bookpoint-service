package ru.tanpii.bookpoint.infrastructure.support.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.tanpii.bookpoint.infrastructure.support.properties.S3Properties
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import java.net.URI

@Configuration
class S3Configuration(
    private val properties: S3Properties
) {
    @Bean
    fun s3Client(): S3Client {

        return S3Client.builder()
            .credentialsProvider {
                AwsBasicCredentials.create(properties.credentials.accessKey, properties.credentials.secretKey)
            }
            .region(Region.of(properties.region))
            .endpointOverride(URI.create(properties.endpoint))
            .build()
    }
}
