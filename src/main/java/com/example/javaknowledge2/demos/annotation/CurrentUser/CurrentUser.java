package com.example.javaknowledge2.demos.annotation.CurrentUser;

import java.lang.annotation.*;

/**
 * 自定义参数 User 注解
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {
    // 如果需要的话，可以在这里定义一些属性或元数据
}