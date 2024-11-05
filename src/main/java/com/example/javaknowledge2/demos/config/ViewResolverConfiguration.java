//package com.example.javaknowledge2.demos.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.ViewResolver;
//import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//import org.thymeleaf.spring5.SpringTemplateEngine;
//import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
//import org.thymeleaf.spring5.view.ThymeleafViewResolver;
//import org.thymeleaf.templateresolver.ITemplateResolver;
//
//@Configuration
//public class ViewResolverConfiguration {
//    @Configuration//用来定义 DispatcherServlet 应用上下文中的 bean
//    @EnableWebMvc
//    @ComponentScan(basePackages = {"com.example.javaknowledge2.demos"})
//    public class WebConfig implements WebMvcConfigurer {
//        @Bean
//        public ViewResolver viewResolver() {
//            InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//            resolver.setPrefix("/web/jsp/");//默认jsp页面的前缀为/，这里我没有放到WEB-INF下面
//            resolver.setSuffix(".jsp");//后缀为.jsp
//            resolver.setViewNames("*");
//            resolver.setOrder(2);
//            return resolver;
//        }
//
//        @Bean
//        public ITemplateResolver templateResolver() {
//            SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
//            templateResolver.setTemplateMode("HTML5");
//            templateResolver.setPrefix("/web/html/");//默认html页面的前缀为/，这里我没有放到WEB-INF下面
//            templateResolver.setSuffix(".html");//后缀为.html
//            templateResolver.setCharacterEncoding("utf-8");
//            templateResolver.setCacheable(false);
//            return templateResolver;
//        }
//
//        @Bean
//        public SpringTemplateEngine templateEngine() {
//            SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//            templateEngine.setTemplateResolver(templateResolver());
//            // templateEngine
//            return templateEngine;
//        }
//
//        @Bean
//        public ThymeleafViewResolver viewResolverThymeLeaf() {
//            ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//            viewResolver.setTemplateEngine(templateEngine());
//            viewResolver.setCharacterEncoding("utf-8");
//            viewResolver.setOrder(1);
//            viewResolver.setViewNames(new String[]{"html/*", "vue/*"});
//            return viewResolver;
//        }
////
////        @Override
////        public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
////            configurer.enable();
////        }
//
//        @Override
//        public void addResourceHandlers(ResourceHandlerRegistry registry) {
//            registry.addResourceHandler("/img/**").addResourceLocations("/img/");
//            registry.addResourceHandler("/css/**").addResourceLocations("/css/");
//            registry.addResourceHandler("/WEB-INF/static/**").addResourceLocations("/WEB-INF/" + "/WEB-INF/static/");
//        }
//    }
//
//}
