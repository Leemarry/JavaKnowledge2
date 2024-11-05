
# Spring Boot 教程

## 如何使用 Spring Boot 开发 Web 应用

Spring Boot 提供了很多便利的功能，使得开发 Web 应用变得更加简单。下面是一些常见的开发 Web 应用的步骤：

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [MyBatis Quick Start](https://github.com/mybatis/spring-boot-starter/wiki/Quick-Start)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)


## @Controller 与  @RestController 的区别

###  @Controller 注解
@Controller 注解用于标记一个类作为 Spring MVC 控制器，即这个类将处理 HTTP 请求并返回视图（通常是 JSP、Thymeleaf 模板等）。当你使用 @Controller 注解的控制器时，你需要指定返回的是一个视图名称，然后通过视图解析器（如 InternalResourceViewResolver）来解析这个视图名称，并找到对应的视图资源。

@Controller 注解用于标识一个类是一个控制器类，其中的方法将会被 Spring MVC 自动识别为处理请求的处理方法。
likai 示例：
````java
@Controller  
public class MyController {  
  
    @GetMapping("/hello")  
    public String hello(Model model) {  
        model.addAttribute("message", "Hello, World!");  
        return "helloView"; // 返回视图名称，由视图解析器解析  
    }  
}
````

###  @RestController 注解
@RestController 是 @Controller 和 @ResponseBody 的组合。当你使用 @RestController 注解的控制器时，你不需要指定返回的是一个视图名称，而是直接返回数据（通常是 JSON、XML 等）。Spring 会自动将返回的对象转换为 JSON 或 XML 格式（取决于 HTTP 请求的 Accept 头和配置的消息转换器），并写入到 HTTP 响应体中。

@RestController 注解用于标识一个类是一个控制器类，其中的方法将会被 Spring MVC 自动识别为处理请求的处理方法，并且返回的结果将会直接以 JSON 格式返回给客户端。

likai 示例：
````java
@RestController
public class MyRestController {

    @GetMapping("/hello")
    public Map<String, String> hello() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello, World!");
        return response; // 直接返回数据，不需要视图解析器  
    }
}
````
两者的区别主要在于返回值不同。

@Controller 注解的返回值可以是 ModelAndView，View，String，void 等类型，这些返回值将会被 Spring MVC 自动转换为适合的响应格式。

而 @RestController 注解的返回值只能是 ResponseEntity、HttpEntity、ResponseEntity、String、Void 等类型，这些返回值将会被 Spring MVC 自动转换为 JSON 格式的响应。

因此，如果需要返回一个 JSON 格式的响应，则使用 @RestController 注解会更加方便。


