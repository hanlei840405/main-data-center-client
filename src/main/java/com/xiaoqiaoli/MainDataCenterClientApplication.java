package com.xiaoqiaoli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

import java.util.Arrays;

@SpringBootApplication
@ImportResource({"main-data-center-client-provider.xml","main-data-center-client-consumer.xml"})
@EnableCaching
public class MainDataCenterClientApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(MainDataCenterClientApplication.class, args);
		String[] names = ctx.getBeanDefinitionNames();
		Arrays.sort(names);
		for(String name : names) {
			System.out.println(name);
		}
	}
}
