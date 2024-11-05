package com.example.javaknowledge2.demos.common;

import org.springframework.beans.factory.annotation.Value;

public class FilePathConfig {
    @Value("${file.path.windows}")
    private String windowsFilePath;

    @Value("${file.path.linux}")
    private String linuxFilePath;

    @Value("${file.path.common}")
    private String commonFilePath;

    // ... getter 和 setter 方法 ...


    public String getWindowsFilePath() {
        return windowsFilePath;
    }

    public void setWindowsFilePath(String windowsFilePath) {
        this.windowsFilePath = windowsFilePath;
    }

    public String getLinuxFilePath() {
        return linuxFilePath;
    }

    public void setLinuxFilePath(String linuxFilePath) {
        this.linuxFilePath = linuxFilePath;
    }

    public String getCommonFilePath() {
        return commonFilePath;
    }

    public void setCommonFilePath(String commonFilePath) {
        this.commonFilePath = commonFilePath;
    }
}
