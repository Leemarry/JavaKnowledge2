package com.example.javaknowledge2.test.RegExp.String;

public class UrlProcessor {

    public static void main(String[] args) {
        String fullUrl = "http://localhost:9090/7/sss77/efuav-ortho-img/pointcloud/cs2eaf67c99859a8e2ccacb38df049f201d/cs2/map/21/1702446/858473.png";
        String[] result = processUrl(fullUrl);

        if (result != null) {
            System.out.println("EndpointExt: " + result[0]);
            System.out.println("Path Template: " + result[1]);
        }
    }

    public static String[] processUrl(String fullUrl) {
        // 首先，去除URL的协议部分（http:// 或 https://）
        String urlWithoutProtocol = fullUrl.replaceFirst("^http://localhost:9090", "");

        // 使用/分割URL
        String[] parts = urlWithoutProtocol.split("/");



        // 构建EndpointExt，从startIndex开始到倒数第四个元素（因为要去掉最后三个）
        StringBuilder endpointExt = new StringBuilder();
        for (int i = 0; i < parts.length - 3; i++) {
            if (i > 1) {
                endpointExt.append("/");
            }
            endpointExt.append(parts[i]);
        }

        // 构建路径模板
        String pathTemplate = "/{z}/{x}/{y}.png";

        // 返回结果
        return new String[]{endpointExt.toString(), pathTemplate};
    }
}