// 代码生成时间: 2025-09-22 14:55:30
package com.example.tools;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 批量文件重命名工具类
 * @author YourName
 */
public class BatchFileRenamer {

    /**
     * 批量重命名目录下的所有文件
     * @param directoryPath 要重命名文件的目录路径
     * @param prefix 新文件名前缀
     * @param suffix 新文件名后缀（包含扩展名）
     * @param startIndex 开始重命名的文件索引
     * @throws IOException 如果发生IO异常
     */
    public void renameFiles(String directoryPath, String prefix, String suffix, int startIndex) throws IOException {
        // 确保目录路径存在
        Path directory = Paths.get(directoryPath);
        if (!Files.isDirectory(directory)) {
            throw new IOException("The specified directory does not exist: " + directoryPath);
        }

        // 获取目录下所有文件
        List<File> files = listFiles(directory);

        // 批量重命名文件
        for (int i = 0; i < files.size(); i++) {
            if (i >= startIndex) {
                File file = files.get(i);
                String newName = prefix + (i + 1) + suffix;
                File newFile = new File(file.getParent(), newName);
                // 重命名文件
                file.renameTo(newFile);
                System.out.println("Renamed file: " + file.getName() + " to " + newFile.getName());
            }
        }
    }

    /**
     * 获取目录下所有文件的列表
     * @param directory 目录路径
     * @return 文件列表
     * @throws IOException 如果发生IO异常
     */
    private List<File> listFiles(Path directory) throws IOException {
        try (Stream<Path> paths = Files.walk(directory)) {
            return paths
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        }
    }

    /**
     * 程序入口点
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        // 示例：重命名目录下所有.txt文件，前缀为"renamed_"，后缀为".txt"，从第1个文件开始重命名
        BatchFileRenamer renamer = new BatchFileRenamer();
        try {
            renamer.renameFiles("path/to/your/directory", "renamed_", ".txt", 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
