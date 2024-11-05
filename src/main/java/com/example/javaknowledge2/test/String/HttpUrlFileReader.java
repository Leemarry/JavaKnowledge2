package com.example.javaknowledge2.test.String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class HttpUrlFileReader {

    public static void main(String[] args) {
        String urlString = "http://127.0.0.1:9090/efuav-ortho-img/pointcloud/map25c86aa338ffda5f280faf4db3d85c67/map/result.tfw"; // 替换为你的URL

        try {
            URL url = new URL(urlString);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}