package com.community.test.config;

import com.community.test.components.InterceptorHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: TestPro
 * @author: WolfSky
 * @create: 2019-11-28 08:36
 **/
@Configuration
public class Myconfig implements WebMvcConfigurer {

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new InterceptorHandler()).addPathPatterns("/");
    }

}
