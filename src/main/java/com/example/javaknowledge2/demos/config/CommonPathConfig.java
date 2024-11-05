package com.example.javaknowledge2.demos.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CommonPathConfig {
    @Value("${file.path.windows:}")
    private String windowsFilePath;

    @Value("${file.path.linux:}")
    private String linuxFilePath;

    @Value("${file.path.common:/Users/lhk/Desktop/zhzf/pic/}")
    private String commonFilePath;

    private String resolvedFilePath;

    @PostConstruct
    public void init() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            resolvedFilePath = windowsFilePath;
        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            resolvedFilePath = linuxFilePath;
        } else {
            resolvedFilePath = commonFilePath;
        }
        // 将resolvedFilePath设置到某个地方，比如通过依赖注入到一个Service中
    }

    // 提供getter方法来获取resolvedFilePath
    public String getResolvedFilePath() {
        return resolvedFilePath;
    }
}
