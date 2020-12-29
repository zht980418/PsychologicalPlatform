package pers.qianyucc.qblog.provider;

import io.minio.*;
import io.minio.errors.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.xmlpull.v1.*;
import pers.qianyucc.qblog.exception.*;

import java.io.*;
import java.security.*;

import static pers.qianyucc.qblog.model.enums.ErrorInfoEnum.FILE_DOWNLOAD_ERROR;
import static pers.qianyucc.qblog.model.enums.ErrorInfoEnum.FILE_UPLOAD_ERROR;

@Slf4j
@Component
public class MinioFileProvider {
    @Value("${minio.access-key}")
    private String accessKey;
    @Value("${minio.secret-key}")
    private String secretKey;
    @Value("${minio.server}")
    private String endPoint;
    @Value("${minio.bucket}")
    private String bucketName;

    public void uploadFile(InputStream in, String fileName, String contentType) {
        try {
            MinioClient minioClient = new MinioClient(endPoint, accessKey, secretKey);
            boolean isExist = minioClient.bucketExists(bucketName);
            if (!isExist) {
                minioClient.makeBucket(bucketName);
            }
            minioClient.putObject(bucketName, fileName, in, contentType);
            in.close();
            log.info("{} upload success", fileName);
        } catch (MinioException | IOException | InvalidKeyException | NoSuchAlgorithmException | XmlPullParserException e) {
            log.error("{} upload error: {}", fileName, e.getMessage());
            throw new BlogException(FILE_UPLOAD_ERROR);
        }
    }

    public InputStream downloadFile(String fileName) {
        try {
            MinioClient minioClient = new MinioClient(endPoint, accessKey, secretKey);
            InputStream in = minioClient.getObject(bucketName, fileName);
            return in;
        } catch (MinioException | IOException | InvalidKeyException | NoSuchAlgorithmException | XmlPullParserException e) {
            log.error("{} download error: {}", fileName, e.getMessage());
            throw new BlogException(FILE_DOWNLOAD_ERROR);
        }
    }
}
