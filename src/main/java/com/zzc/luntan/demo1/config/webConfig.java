package com.zzc.luntan.demo1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class webConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyIntercepter()).addPathPatterns("/test/1/**").excludePathPatterns("/test/2/**");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("index").setViewName("index");
        registry.addViewController("loginTake").setViewName("tiezi/tiezi");
        registry.addViewController("/upload.do").setViewName("upfile/fileHome");
        registry.addViewController("/registeTake").setViewName("registe/registe");
    }
}
