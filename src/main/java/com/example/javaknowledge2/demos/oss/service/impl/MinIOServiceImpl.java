package com.example.javaknowledge2.demos.oss.service.impl;

import com.example.javaknowledge2.demos.oss.service.IOssService;
import com.example.javaknowledge2.demos.oss.storage.CredentialsToken;
import com.example.javaknowledge2.demos.oss.storage.OssConfiguration;
import com.example.javaknowledge2.demos.oss.storage.OssTypeEnum;
import io.minio.*;
import io.minio.credentials.AssumeRoleProvider;
import io.minio.credentials.Credentials;
import io.minio.errors.*;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;



/**
 * @author sean
 * @version 0.3
 * @date 2021/12/23
 */
@Service
@Slf4j
public class MinIOServiceImpl implements IOssService {

    private MinioClient client;

    @Override
    public OssTypeEnum getOssType() {
        return OssTypeEnum.MINIO;
    }

    @Override
    public CredentialsToken getCredentials() {
        try {
            AssumeRoleProvider provider = new AssumeRoleProvider(OssConfiguration.endpoint, OssConfiguration.accessKey,
                    OssConfiguration.secretKey, Math.toIntExact(OssConfiguration.expire),
                    null, OssConfiguration.region, null, null, null, null);
            Credentials credential = provider.fetch();
            return new CredentialsToken(credential.accessKey(), credential.secretKey(), credential.sessionToken(), OssConfiguration.expire);
        } catch (NoSuchAlgorithmException e) {
            log.debug("无法获取sts。");
            e.printStackTrace();
        }
        return null;
    }


    public URL getObjectUrl2(String bucket, String objectKey) {
        try {
            return new URL(
                    client.getPresignedObjectUrl(
                            GetPresignedObjectUrlArgs.builder()
                                    .method(Method.GET)
                                    .bucket(bucket)
                                    .object(objectKey)
                                    .expiry(Math.toIntExact(OssConfiguration.expire))
                                    .build()));
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException |
                 IOException | NoSuchAlgorithmException | ServerException | InvalidResponseException |
                 XmlParserException e) {
            throw new RuntimeException("OssConfiguration上不存在该文件。");
        }
    }

    @Override
    public Boolean deleteObject(String bucket, String objectKey) {
        try {
            client.removeObject(RemoveObjectArgs.builder().bucket(bucket).object(objectKey).build());
        } catch (MinioException | NoSuchAlgorithmException | IOException | InvalidKeyException e) {
            log.error("未能删除文件。");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public InputStream getObject(String bucket, String objectKey) {
        try {
            GetObjectResponse object = client.getObject(GetObjectArgs.builder().bucket(bucket).object(objectKey).build());
            return new ByteArrayInputStream(object.readAllBytes());
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
            e.printStackTrace();
        }
        return InputStream.nullInputStream();
    }



    @Override
    public void putObject(String bucket, String objectKey, InputStream input) {
        this.createClient();
        try {
            client.statObject(StatObjectArgs.builder().bucket(bucket).object(objectKey).build());
            throw new RuntimeException("文件名已存在。");
        } catch (MinioException | InvalidKeyException | IOException | NoSuchAlgorithmException e) {
            log.info("文件不存在，请开始上传。");
            try {
                ObjectWriteResponse response = client.putObject(
                        PutObjectArgs.builder().bucket(bucket).object(objectKey).stream(input, input.available(), 0).build());
                log.info("上传成功：{}", response.etag());
            } catch (MinioException | IOException | InvalidKeyException | NoSuchAlgorithmException ex) {
                log.error("未能上传：{}", objectKey);
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void createClient() {
//        if (Objects.nonNull(this.client)) {
//            return;
//        }
        this.client = MinioClient.builder()
                .endpoint(OssConfiguration.endpoint)
                .credentials(OssConfiguration.accessKey, OssConfiguration.secretKey)
                .region(OssConfiguration.region)
                .build();
    }

    @Override
    public URL getObjectUrl(String bucket, String objectKey) {
        try {

            return new URL(getPresignedObjectUrl(bucket, objectKey));

        } catch (IOException e) {
            throw new RuntimeException("OssConfiguration上不存在该文件。");
        }
    }
    public String getPresignedObjectUrl(String bucket, String objectKey) {
        String urlString = null;
        try {
            String fileUrl = client.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.PUT)
                            .bucket(bucket)
                            .object(objectKey)
                            .build()
            );
            URL url = new URL(fileUrl);
            String paths = url.getPath();
            String protocol = url.getProtocol();
            String host = url.getHost();
            int port = url.getPort();
            String hostString = protocol + "://" + host;
            if (port != -1) {
                hostString += ":" + port + paths;
            }
//            System.out.println(fileUrl);
            String fileName = paths.substring(paths.lastIndexOf('/') + 1);
            urlString = hostString;
        } catch (Exception e) {
//            LogUtil.logError("Minio获取文件异常：" + e.toString());
        }
        return urlString;
    }

}
