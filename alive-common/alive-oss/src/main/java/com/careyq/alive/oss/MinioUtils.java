package com.careyq.alive.oss;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.careyq.alive.core.exception.FileUploadException;
import io.minio.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * 存储工具类
 *
 * @author CareyQ
 */
@Slf4j
@Component
public class MinioUtils {

    private static MinioConfiguration config;
    private static MinioClient client;


    /**
     * 存储桶是否存在
     *
     * @param bucketName 桶名称
     * @return 是否存在
     */
    public static boolean bucketExists(String bucketName) {
        boolean exists;
        try {
            exists = client.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            log.error("[Minio-bucketExists] error", e);
            return false;
        }
        return exists;
    }

    /**
     * 创建存储桶
     *
     * @param bucketName 桶名称
     */
    public static void createBucket(String bucketName) {
        try {
            client.makeBucket(MakeBucketArgs.builder()
                    .bucket(bucketName)
                    .build());
        } catch (Exception e) {
            log.error("[Minio-createBucket] error", e);
        }
    }

    /**
     * 删除存储桶
     *
     * @param bucketName 桶名称
     */
    public static void removeBucket(String bucketName) {
        try {
            client.removeBucket(RemoveBucketArgs.builder()
                    .bucket(bucketName)
                    .build());
        } catch (Exception e) {
            log.error("[Minio-removeBucket] error", e);
        }
    }

    /**
     * 上传文件
     *
     * @param file 文件
     * @param path 路径
     * @return 文件路径
     */
    public static String upload(MultipartFile file, String path) {
        String originalFilename = file.getOriginalFilename();
        if (StrUtil.isEmpty(originalFilename)) {
            throw new FileUploadException("文件名不能为空");
        }

        if (!bucketExists(config.getBucketName())) {
            createBucket(config.getBucketName());
        }

        String fileName = IdUtil.fastSimpleUUID() + originalFilename.substring(originalFilename.lastIndexOf(StrUtil.DOT));
        String objectName = StrUtil.isNotEmpty(path) ? path + StrUtil.SLASH + fileName : fileName;
        try {
            PutObjectArgs args = PutObjectArgs.builder()
                    .bucket(config.getBucketName())
                    .object(objectName)
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build();
            client.putObject(args);
        } catch (Exception e) {
            log.error("文件上传出错", e);
            throw new FileUploadException(e.getMessage());
        }
        return objectName;
    }

    /**
     * 获取文件 URL
     *
     * @param path 文件路径
     * @return 文件 URL
     */
    public static String getFileUrl(String path) {
        return getFileDomain() + path;
    }

    /**
     * 获取文件域名
     *
     * @return 文件域名
     */
    public static String getFileDomain() {
        String endPoint = config.getEndPoint();
        String prefix = endPoint.startsWith("http") ? endPoint : "https://" + endPoint;
        return prefix + StrUtil.SLASH + config.getBucketName() + "/";
    }

    @Autowired
    private void setInstance(MinioConfiguration config, MinioClient client) {
        MinioUtils.config = config;
        MinioUtils.client = client;
    }

}
