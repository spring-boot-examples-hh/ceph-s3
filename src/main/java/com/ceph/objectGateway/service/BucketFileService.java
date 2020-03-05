package com.ceph.objectGateway.service;

import com.ceph.common.HaoCangServerResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface BucketFileService {
    /**
     * 获取bucket内的所有对象
     * @param bucketName
     * @return
     */
    HaoCangServerResponse bucketFileList(String bucketName);

    /**
     * 文件上传
     * @param multipartFile
     * @param bucketName
     * @return
     */
    HaoCangServerResponse fileUpload(MultipartFile multipartFile, String bucketName);

    /**
     * 文件下载
     * @param bucketName
     * @param key
     * @return
     */
    HaoCangServerResponse fileDownLoad(String bucketName,String key);
}
