package org.koreait.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }

    /**
     * PATCH PUT DELETE 등의 요청 메서드를 사용 하기 위한 설정
     * <form mothod=POST>
     *     <input type=hidden name'_method' value="PATCH                 "
     *</form >
     *
     * @return
     */
    public HiddenHttpMethodFilter hiddenHttpMethodFilter (){
        return new HiddenHttpMethodFilter();
    }





}
