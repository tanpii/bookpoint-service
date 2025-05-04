package ru.tanpii.bookpoint.infrastructure.s3

import org.springframework.stereotype.Service
import ru.tanpii.bookpoint.domain.service.ImageService
import java.io.InputStream

private const val BUCKET_NAME = "author"

@Service
class AuthorS3ImageService(
    private val s3ImageComponent: S3ImageComponent,
) : ImageService {

    override fun uploadImage(file: InputStream, name: String) =
        s3ImageComponent.uploadImage(file, BUCKET_NAME, name)

    override fun getImageUrl(name: String) = s3ImageComponent.getImageUrl(BUCKET_NAME, name)
}
