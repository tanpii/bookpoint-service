package ru.tanpii.bookpoint.infrastructure.minio

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import ru.tanpii.bookpoint.domain.service.ImageService
import java.io.InputStream

@Service
class BookMinioImageService(
    @Value("\${minio.bookBucket}")
    private val bucket: String,
    private val minioImageComponent: MinioImageComponent
) : ImageService {

    override fun uploadImage(file: InputStream, name: String) =
        minioImageComponent.uploadImage(file, bucket, name)

    override fun getImageUrl(name: String) = minioImageComponent.getImageUrl(bucket, name)
}
