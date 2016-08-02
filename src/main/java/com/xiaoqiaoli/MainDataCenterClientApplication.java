package com.xiaoqiaoli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"main-data-center-client-provider.xml","main-data-center-client-consumer.xml"})
@EnableCaching
public class MainDataCenterClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainDataCenterClientApplication.class, args);
	}
}
