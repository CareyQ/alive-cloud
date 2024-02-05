package com.careyq.alive.oss.core.client;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.careyq.alive.oss.core.OssProperties;
import lombok.Getter;

import java.io.ByteArrayInputStream;

/**
 * 对象存储客户端，s3 存储协议
 *
 * @author CareyQ
 */
public class OssClient {

    @Getter
    private final Long configId;
    private final OssProperties properties;
    private final AmazonS3 client;

    public OssClient(Long configId, OssProperties properties) {
        this.configId = configId;
        this.properties = properties;

        AwsClientBuilder.EndpointConfiguration endpointConfig = new AwsClientBuilder.EndpointConfiguration(properties.getEndpoint(), properties.getRegion());
        BasicAWSCredentials credentials = new BasicAWSCredentials(properties.getAccessKey(), properties.getSecretKey());

        AmazonS3ClientBuilder builder = AmazonS3Client.builder()
                .withEndpointConfiguration(endpointConfig)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .disableChunkedEncoding();

        this.client = builder.build();
        this.createBucket();
    }

    /**
     * 创建存储空间
     */
    public void createBucket() {
        String bucket = properties.getBucket();
        if (client.doesBucketExistV2(bucket)) {
            return;
        }
        CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucket);
        createBucketRequest.setCannedAcl(CannedAccessControlList.PublicRead);
        client.createBucket(createBucketRequest);
    }

    /**
     * 上传至对象存储
     *
     * @param content     文件内容
     * @param path        文件路径
     * @param contentType 文件类型
     * @return 文件访问地址
     */
    public String upload(byte[] content, String path, String contentType) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(content);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(contentType);
        metadata.setContentLength(inputStream.available());
        PutObjectRequest putObjectRequest = new PutObjectRequest(properties.getBucket(), path, inputStream, metadata);

        client.putObject(putObjectRequest);
        return properties.getBucket() + "." + properties.getEndpoint() + "/" + path;
    }

}
