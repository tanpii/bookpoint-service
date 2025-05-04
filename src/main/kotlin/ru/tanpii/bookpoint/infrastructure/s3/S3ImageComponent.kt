package ru.tanpii.bookpoint.infrastructure.s3

import org.springframework.stereotype.Component
import ru.tanpii.bookpoint.infrastructure.support.properties.S3Properties
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.ObjectCannedACL
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import java.io.InputStream

@Component
class S3ImageComponent(
    private val s3Client: S3Client,
    private val properties: S3Properties
) {

    fun uploadImage(file: InputStream, bucket: String, name: String) =
        s3Client.putObject(
            PutObjectRequest.builder()
                .bucket(properties.bucketName[bucket])
                .acl(ObjectCannedACL.PUBLIC_READ)
                .key(name)
                .build(),
            RequestBody.fromBytes(file.readBytes())
        ).let {
            getImageUrl(bucket, name)
        }


    fun getImageUrl(bucket: String, name: String) =
        "https://storage.yandexcloud.net/${properties.bucketName[bucket]}/${name.replace(" ", "%20")}"
}