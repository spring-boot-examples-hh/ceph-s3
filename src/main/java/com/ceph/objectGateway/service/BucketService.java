package com.ceph.objectGateway.service;

import com.ceph.common.HaoCangServerResponse;

public interface BucketService {
    /**
     * 获取BucketList
     * @return
     */
    HaoCangServerResponse getBucketList();

    /**
     * 创建 bucket
     * @return
     */
    HaoCangServerResponse createBucket(String bucketName);
}
