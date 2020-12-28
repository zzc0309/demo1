package com.zzc.luntan.demo1.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    public static List<String> getFiles(String path) {
        List<String> File=new ArrayList<>();
        File file = new File(path);
        // 如果这个路径是文件夹
        if (file.isDirectory()) {
            // 获取路径下的所有文件
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                // 如果还是文件夹 递归获取里面的文件 文件夹
                if (files[i].isDirectory()) {
                    System.out.println("目录：" + files[i].getPath());
                    getFiles(files[i].getPath());

                } else {

                    File.add(files[i].getName());
                }
            }

        } else {
            System.out.println("文件：" + file.getPath());
            File.add(file.getName());
        }
        return File;
    }
}
