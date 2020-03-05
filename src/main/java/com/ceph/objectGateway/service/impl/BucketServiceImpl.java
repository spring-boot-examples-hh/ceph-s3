package com.ceph.objectGateway.service.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.ceph.common.HaoCangServerResponse;
import com.ceph.objectGateway.service.BucketService;
import com.ceph.objectGateway.util.AwsClientUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BucketServiceImpl implements BucketService {

    /**
     * 获取BucketList
     * @return
     */
    public HaoCangServerResponse getBucketList(){
        AmazonS3 s3 = AwsClientUtil.s3Client();
        if(s3 !=null){
            List<Bucket> bucketList =  s3.listBuckets();
            return HaoCangServerResponse.createSuccess(bucketList);
        }
        return HaoCangServerResponse.createError("ceph连接异常");
    }
    /**
     * 创建 bucket
     * @return
     */
    public HaoCangServerResponse createBucket(String bucketName){
        if(StringUtils.isNotBlank(bucketName)){
            AmazonS3 s3 = AwsClientUtil.s3Client();
            if(s3 !=null){
                List<Bucket> bucketList =  s3.listBuckets();
                for (Bucket bucket : bucketList) {
                    if(bucket.getName().equals(bucketName)){
                        return HaoCangServerResponse.createError(bucketName+"已存在");
                    }
                }
                Bucket bucket;
                try {
                    bucket = s3.createBucket(bucketName);
                }catch (AmazonServiceException e){
                    return HaoCangServerResponse.createError("bucket创建异常",e);
                }catch (SdkClientException e){
                    return HaoCangServerResponse.createError("bucket创建异常",e);
                }
                return HaoCangServerResponse.createSuccess(bucket);

            }
            return HaoCangServerResponse.createError("ceph连接异常");
        }
        return HaoCangServerResponse.createError("参数异常");
    }
}
