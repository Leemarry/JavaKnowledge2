/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.javaknowledge2.demos.web;

import com.example.javaknowledge2.demos.annotation.CurrentUser.CurrentUser;
import com.example.javaknowledge2.demos.config.CommonPathConfig;
import com.example.javaknowledge2.demos.entity.EfUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Controller
public class BasicController {

    private final CommonPathConfig commonPathConfig;
    @Autowired
    public BasicController(CommonPathConfig commonPathConfig) {
        this.commonPathConfig = commonPathConfig;
    }

    public String getResolvedFilePath() {
        String resolvedFilePath = commonPathConfig.getResolvedFilePath(); // 获取解析后的文件路径
        // 业务逻辑代码
        return resolvedFilePath;
    }

    // http://127.0.0.1:8080/hello?name=lisi
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam(name = "name", defaultValue = "unknown user") String name) {
        return "Hello " + name;
    }

    // http://127.0.0.1:8080/user
    @RequestMapping("/user")
    @ResponseBody
    public User user(@CurrentUser EfUser efUser ) {
        User user = new User();
        user.setName("theonefx");
        user.setAge(666);
        return user;
    }

    // http://127.0.0.1:8080/save_user?name=newName&age=11
    @RequestMapping("/save_user")
    @ResponseBody
    public String saveUser(User u) {
        return "user will save: name=" + u.getName() + ", age=" + u.getAge();
    }

    // http://127.0.0.1:8080/html
    @RequestMapping("/html")
    public String html() {
        return "index";
    }

    // http://127.0.0.1:8080/imgs/14.jpg
    @RequestMapping("/imgs/{img}")
    public void img(@PathVariable("img") String img, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 构建文件的完整路径
        String imgPath = getResolvedFilePath();
        String filePath = imgPath + img;
        // 读取文件并将其输出到响应中
        File file = new File(filePath);
        if (file.exists()) {
            // 设置响应的内容类型
            response.setContentType("image/jpeg"); // 根据实际图片类型修改
            // 输出文件内容
            try (FileInputStream fis = new FileInputStream(file);
                 OutputStream os = response.getOutputStream()) {
                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) > 0) {
                    os.write(buffer, 0, len);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            // 如果文件不存在，返回 404 错误
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    // http://127.0.0.1:8080/helloView
    @GetMapping("/helloView")
    public String hello(Model model) {
//        ModelAndView modelAndView = new ModelAndView("/helloView");
//        modelAndView.addObject("message", "张三");
//        model.addAttribute("message", "Hello, World!");
        return "/helloView"; // 返回视图名称，由视图解析器解析
    }

    @ModelAttribute
    public void parseUser(@RequestParam(name = "name", defaultValue = "unknown user") String name
            , @RequestParam(name = "age", defaultValue = "12") Integer age, User user) {
        user.setName("zhangsan");
        user.setAge(18);
    }
}
