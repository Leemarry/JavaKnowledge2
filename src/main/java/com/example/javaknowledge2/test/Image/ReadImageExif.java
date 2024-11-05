package com.example.javaknowledge2.test.Image;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReadImageExif {
    public static void main(String[] args) {
        try {
            File imageFile =new File("F:\\3d\\model\\格林美\\images\\survey\\0ab3c9a969b07b84b856acda90cb7ae843207bc2.JPG");
            Metadata metadata = ImageMetadataReader.readMetadata(imageFile);

            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {
                    System.out.println(tag.getTagName() + " : " + tag.getDescription());
                }
            }
        } catch (ImageProcessingException | IOException e) {
            e.printStackTrace();
        }
    }
}


