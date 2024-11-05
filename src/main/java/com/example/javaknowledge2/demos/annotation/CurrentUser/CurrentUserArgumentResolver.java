package com.example.javaknowledge2.demos.annotation.CurrentUser;

import com.example.javaknowledge2.demos.entity.EfUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {

    private static final Logger log = LoggerFactory.getLogger(CurrentUserArgumentResolver.class);

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || !authentication.isAuthenticated()) {
//            // 处理未认证的情况，可以抛出异常或返回null等
//            System.out.println("未认证");
//            throw new IllegalStateException("User not authenticated");
//        }
//        Object principal = authentication.getPrincipal();
//        if (principal instanceof UserDetails) {
//            UserDetails userDetails = (UserDetails) principal;
//            // 使用UserDetails中的信息（如用户名）来查找EfUser
//            String username = userDetails.getUsername();
//            EfUser efUser =new EfUser();
//            efUser.setName("username");
//            if (efUser == null) {
//                // 处理未找到EfUser的情况，可以抛出异常或返回null等
//                throw new IllegalStateException("EfUser not found for user: " + username);
//            }
//            return efUser;
//        }
//        // 处理非UserDetails类型的情况
//        throw new IllegalStateException("Principal is not an instance of UserDetails");
        EfUser efUser =new EfUser();
        efUser.setName("username");
        return efUser;
    }
}
