package com.ceph;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@ServletComponentScan   //启动器启动时，扫描本目录以及子目录带有的webservlet注解的
public class CephApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CephApplication.class, args);
	}

	/* 打包启动项目 extends SpringBootServletInitializer 实现 configure方法 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){

		return builder.sources(CephApplication.class);
	}
}
