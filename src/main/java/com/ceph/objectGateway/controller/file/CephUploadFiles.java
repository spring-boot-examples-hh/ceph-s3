package com.ceph.objectGateway.controller.file;

import com.alibaba.fastjson.JSONObject;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ceph.objectGateway.util.AwsClientUtil;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @author:LHH
 * @desc: ceph存储文件
 * @date: 2020-2-27 17:54:07
 **/
@WebServlet(name = "cephUploadFiles", urlPatterns = {"/cephUpcloadFiles"})
@MultipartConfig
public class CephUploadFiles extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){

        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("access-control-allow-headers", "x-requested-with,content-type");
        response.setHeader("access-control-allow-methods", "OPTIONS,POST");
        response.setHeader("access-control-allow-origin", "*");
        System.out.println("ddddddd");
        PrintWriter pw = null;
        try{
            pw = response.getWriter();
            Part part = request.getPart("file");
            String bucketName = request.getParameter("bucketName");

            AmazonS3 s3 = AwsClientUtil.s3Client();
            System.out.println("222:");
            if (s3 != null) {
                // 获取文件大小
                InputStream is = part.getInputStream();

                String name = part.getSubmittedFileName();//获取文件名;
                long size = part.getSize();
                System.out.println(size);

                String fileName = name.substring(0, name.lastIndexOf("."));
                // 获取文件后缀
                String suffix = name.substring(name.lastIndexOf(".") + 1);
                //key 使用uuid随机生成
                String key = fileName + "" + new Date().getTime() + "." + suffix;
                ObjectMetadata metadata = new ObjectMetadata();
                // 必须设置ContentLength
                metadata.setContentLength(size);

                PutObjectRequest putRequest = new PutObjectRequest(bucketName, key, is, metadata);
                //公有读
                s3.putObject(putRequest.withCannedAcl(CannedAccessControlList.PublicRead));

                s3.shutdown();
                GeneratePresignedUrlRequest guRequest = new GeneratePresignedUrlRequest(bucketName, key);
                URL url = s3.generatePresignedUrl(guRequest);
                String imgUrl = url.getProtocol() + "://" + url.getHost() + ":" + url.getPort() + url.getPath();
                String path = url.getPath();
                JSONObject result = new JSONObject();
                result.put("fileName", key);
                result.put("bucketName", bucketName);
                result.put("size", size);
                result.put("url", imgUrl);
                result.put("path", path);

                /*JSONObject result = new JSONObject();
                result.put("url", "http://10.88.8.10:18890/chenoss6d1b6bcd/%E8%BD%AE%E6%92%AD21582785734276.png");
                result.put("path", "/chenoss6d1b6bcd/%E8%BD%AE%E6%92%AD21582785734276.png");
                result.put("bucketName", bucketName);
                result.put("size", size);*/
                System.out.println("result" + result);
                pw.print(result);
                if(pw!=null) pw.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            pw.print(e.getMessage());
        }finally {System.out.println("7777:");
            if(pw!=null) pw.close();
        }
    }

}