package com.example.javaknowledge2.demos.config;


import com.example.javaknowledge2.demos.annotation.CurrentUser.CurrentUserArgumentResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.List;

/*
 * 这个类是Spring MVC的配置类，它实现了WebMvcConfigurer接口，
 * 并提供了一些配置方法，如addViewControllers、addResourceHandlers等。
 * 原文链接：https://blog.csdn.net/qq_46495709/article/details/107015759
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${file.path.windows}")
    private String windowsFilePath;
    @Value("${file.path.linux}")
    private String linuxFilePath;
//    @Bean
//    public InternalResourceViewResolver viewResolver() {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("/web/jsp/"); // JSP文件的前缀路径 /WEB-INF/jsp/目录是相对于你的类路径（通常是src/main/webapp/WEB-INF/jsp/）
//        resolver.setSuffix(".jsp"); // JSP文件的后缀
//        resolver.setViewClass(org.springframework.web.servlet.view.JstlView.class); // 如果你使用JSTL
//        // 可以设置更多属性，如characterEncoding等
//        return resolver;
//    }

//    @Bean
//    public ViewResolver viewResolver() {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("/web/jsp/");//默认jsp页面的前缀为/，这里我没有放到WEB-INF下面
//        resolver.setSuffix(".jsp");//后缀为.jsp
//        resolver.setViewNames("*");
//        return resolver;
//    }
//
    @Bean
    public ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setTemplateMode("HTML");
        templateResolver.setPrefix("/web/html/");//默认html页面的前缀为/，这里我没有放到WEB-INF下面
        templateResolver.setSuffix(".html");//后缀为.html
        templateResolver.setCharacterEncoding("utf-8");
        templateResolver.setCacheable(false);
        return templateResolver;
    }
//
//    @Bean
//    public SpringTemplateEngine templateEngine() {
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver());
//        return templateEngine;
//    }
//
//    @Bean
//    public ThymeleafViewResolver viewResolverThymeLeaf() {
//        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//        viewResolver.setTemplateEngine(templateEngine());
//        viewResolver.setCharacterEncoding("utf-8");
//        viewResolver.setOrder(1);
//        viewResolver.setViewNames(new String[]{"html/*"});
//        return viewResolver;
//    }

    // 你可以在这里添加其他配置，如添加视图控制器、拦截器等
    // 例如，添加一个视图控制器来映射"/"到"index"视图
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("indexs");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new CurrentUserArgumentResolver());
    }
    /**
     * 静态资源文件映射配置
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {  //如果是Windows系统
            registry.addResourceHandler("/img/**").addResourceLocations("file:"+windowsFilePath);
            //        registry.addResourceHandler("/img/**").addResourceLocations("/web/img/"); // 映射静态资源 http://127.0.0.1:8080/img/14.jpg 到 /web/img/ 目录下
            registry.addResourceHandler("/css/**").addResourceLocations("/css/");
            registry.addResourceHandler("/WEB-INF/static/**").addResourceLocations("/WEB-INF/" + "/WEB-INF/static/");
        }else{ //linux和mac系统
            registry.addResourceHandler("/img/**").addResourceLocations("file:"+linuxFilePath+"/");
        }
    }


}