package com.ceph.objectGateway.controller.file;

import com.ceph.common.HaoCangServerResponse;
import com.ceph.objectGateway.service.BucketFileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 对象存储相关的文件接口
 */
@Controller
@RequestMapping(value = "/bucketFile")
public class BucketFileController {

    @Resource
    private BucketFileService bucketFileService;

    /**
     * 根据 bucketName 查询所有的文件（不分页，直接查询ceph服务）
     * @param bucketName
     * @return
     */
    @RequestMapping(value = "/bucketFileList",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public HaoCangServerResponse bucketFileList(@RequestParam(value = "bucketName")String bucketName){
       return bucketFileService.bucketFileList(bucketName);
    }

    //文件上传
    @RequestMapping(value = "/fileUpload",method = {RequestMethod.POST})
    @ResponseBody
    public HaoCangServerResponse fileUpload(@RequestParam(value = "file")MultipartFile multipartFile, @RequestParam(value = "bucketName")String bucketName){
        return bucketFileService.fileUpload(multipartFile,bucketName);
    }

    //文件下载
    @RequestMapping(value = "/fileDownLoad",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public HaoCangServerResponse fileDownLoad(@RequestParam(value = "bucketName")String bucketName,@RequestParam(value = "filename")String filename){

        return bucketFileService.fileDownLoad(bucketName,filename);
    }

}
