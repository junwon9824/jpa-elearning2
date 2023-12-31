//package io.sample.learn.config;
//
//import org.springframework.context.annotation.*;
//import org.springframework.web.servlet.config.annotation.*;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//    public static final String ALLOWED_METHOD_NAMES = "GET,HEAD,POST,PUT,DELETE,TRACE,OPTIONS,PATCH";
//
//    @Override
//    public void addCorsMappings(  CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:8080") //
//                .allowedMethods(ALLOWED_METHOD_NAMES.split(","));
//    }
//}