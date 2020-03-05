package com.ceph.objectGateway.configure;

import com.ceph.objectGateway.controller.file.CephUploadFiles;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class BeanConfig {

    /*@Bean
    public ServletRegistrationBean servletTLReportServlet() {
//        return new ServletRegistrationBean(new CephUploadFiles(), "/cephUpcloadFiles");
        ServletRegistrationBean bean = new ServletRegistrationBean(new CephUploadFiles()); //放入自己的Servlet对象实例
        bean.addUrlMappings("/cephUpcloadFiles");  //访问路径值
        return bean;
    }*/
}
