package com.xiaoqiaoli.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by hanlei6 on 2016/8/6.
 */
@Configuration
public class ResourceConfig extends WebMvcConfigurerAdapter {
    @Value("${ext.image.dir}")
    private String imageDir;
    @Value("${ext.office.dir}")
    private String officeDir;
    @Value("${ext.media.dir}")
    private String mediaDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/ext/**").addResourceLocations(
                "file:///" + imageDir, "file:///" + officeDir, "file:///" + mediaDir);
    }
}
