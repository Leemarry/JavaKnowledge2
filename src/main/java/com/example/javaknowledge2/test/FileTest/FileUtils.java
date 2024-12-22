package com.example.javaknowledge2.test.FileTest;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;


public class FileUtils {

    /**
     * 删除指定目录及其所有子目录和文件。
     * @param directory 要删除的目录
     */
    public static void deleteFolderContents(Path directory) {
        try {
            // 递归删除文件夹下的所有内容
            Files.walk(directory)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
            System.out.println("删除成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 复制文件或目录到指定目标位置。
     * @param source 源文件或目录
     * @param target 目标文件或目录
     * @throws IOException 如果复制操作失败
     */
    public static void copy(Path source, Path target) throws IOException {
        Files.walkFileTree(source, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Path targetFile = target.resolve(source.relativize(file));
                Files.copy(file, targetFile, StandardCopyOption.REPLACE_EXISTING);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                Path targetDir = target.resolve(source.relativize(dir));
                Files.createDirectories(targetDir);
                return FileVisitResult.CONTINUE;
            }
        });
        System.out.println("复制成功！");
    }

    /**
     * 移动文件或目录到指定目标位置。
     * @param source 源文件或目录
     * @param target 目标文件或目录
     * @throws IOException 如果移动操作失败
     */
    public static void move(Path source, Path target) throws IOException {
        Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("移动成功！");
    }

    /**
     * 检查文件或目录是否存在。
     * @param path 文件或目录的路径
     * @return 如果存在返回 true，否则返回 false
     */
    public static boolean exists(Path path) {
        return Files.exists(path);
    }

    //
    /**
     * 保存上传的文件到指定的本地路径。
     * @param file 上传的文件
     * @param path 保存的目标路径
     * @throws IOException 如果保存文件时发生错误
     */
    public static void saveFile(MultipartFile file, String path) throws IOException {
        // 获取文件名
        String fileName = file.getOriginalFilename();
        if (fileName == null) {
            throw new IllegalArgumentException("文件名不能为空");
        }

        // 创建目标路径
        Path targetPath = Paths.get(path, fileName);

        // 确保目标目录存在
        if (!Files.exists(targetPath.getParent())) {
            Files.createDirectories(targetPath.getParent());
        }

        try {
            // 将文件写入目标路径
            file.transferTo(targetPath);
            System.out.println("文件保存成功: " + targetPath.toString());
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("保存文件失败", e);
        }
    }

    /**
     * 保存上传的文件到指定的本地路径。
     * @param file 上传的文件
     * @param path 保存的目标路径
     * @throws IOException 如果保存文件时发生错误
     */
    public static void saveFile(File file, String path) throws IOException {
        // 确保源文件存在
        if (!file.exists()) {
            throw new IllegalArgumentException("源文件不存在: " + file.getAbsolutePath());
        }

        // 创建目标路径
        Path targetPath = Paths.get(path, file.getName());

        // 确保目标目录存在，否则创建
        if (!Files.exists(targetPath.getParent())) {
            Files.createDirectories(targetPath.getParent()); // 创建父目录
        }

        // 复制文件到目标路径
        try {
            Files.copy(file.toPath(), targetPath); // 文件复制
            System.out.println("文件保存成功: " + targetPath.toString());
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("保存文件失败", e); // 抛出异常，方便后续处理
        }
    }


    /**
     * 将字节流写入指定文件。
     *
     * @param bytes 要写入的字节数组
     * @param path  保存的目标路径
     * @throws IOException 如果写入时发生错误
     */
    public static void writeToFile(byte[] bytes, String path) throws IOException {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path))) {
            bos.write(bytes);
            System.out.println("文件写入成功: " + path);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("写入文件失败", e);
        }
    }

    /**
     * 将输入流写入指定文件。
     *
     * @param input 输入流
     * @param path  保存的目标路径
     * @throws IOException 如果写入时发生错误
     */
    public static void writeToFile(InputStream input, String path) throws IOException {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path))) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            System.out.println("文件写入成功: " + path);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("写入文件失败", e);
        }
    }

    /**
     * 检查文件夹是否存在，如果不存在则创建它。
     *
     * @param dirPath 要检查或创建的文件夹路径
     * @throws IOException 如果创建文件夹时发生错误
     */
    public static void createDirectoryIfNotExists(String dirPath) throws IOException {
        Path path = Paths.get(dirPath);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
            System.out.println("文件夹创建成功: " + dirPath);
        } else {
            System.out.println("文件夹已存在: " + dirPath);
        }
    }

    /**
     * 在指定文件夹下查找一个特定名称的文件。
     *
     * @param dirPath 文件夹路径
     * @param fileName 要查找的文件名
     * @return 如果文件存在则返回其完整路径，否则返回 null
     * @throws IOException 如果查找时发生错误
     */
    public static String findFileInDirectory(String dirPath, String fileName) throws IOException {
        Path dir = Paths.get(dirPath);
        if (!Files.isDirectory(dir)) {
            throw new IllegalArgumentException("指定的路径不是一个文件夹: " + dirPath);
        }

        try (Stream<Path> paths = Files.walk(dir)) {
            Optional<Path> result = paths.filter(path -> path.getFileName().toString().equals(fileName)).findFirst();
            return result.map(Path::toString).orElse(null); // 如果找到，返回文件路径；否则返回 null
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("查找文件时发生错误", e);
        }
    }

}

