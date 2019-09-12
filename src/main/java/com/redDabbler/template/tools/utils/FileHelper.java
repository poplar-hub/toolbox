package com.redDabbler.template.tools.utils;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {
    private FileHelper() {
        throw new UnsupportedOperationException();
    }

    /**
     * @param filePath
     * @return
     * @throws FileNotFoundException
     */
    public static InputStream getInputStream(String filePath)throws FileNotFoundException {
        File file = new File(filePath);
//        Preconditions.checkArgument(file.exists() && file.isFile(),"文件不存在");
        InputStream inputStream  = new FileInputStream(file);
        return inputStream;
    }

    /**
     *
     * @param filePath
     * @param isDir 当前文件是否是文件夹
     * @return
     * @throws IOException
     */
    public static boolean recursionCreateIfNotExists(String filePath,boolean isDir)throws IOException{
        File file = new File(filePath);
        if (file.exists()){
            if(file.isDirectory() && isDir){
                return true;
            }
            if(file.isFile() && !isDir){
                return true;
            }
            throw new IllegalArgumentException(" create failed ! file has exists  but type not matched");
        }
        if(isDir){
            file.mkdirs();
        }
        File parentDir = file.getParentFile();
        if (!parentDir.exists()){
            parentDir.mkdirs();
        }
        return file.createNewFile();
    }

    /**
     * 递归获取所有的文件
     * @param file
     * @return
     */
    public static List<File> listFiles(File file){
  //      Preconditions.checkArgument(file.exists(),"文件不存在");
        List<File> files = new ArrayList<>();
        recursionFiles(file,files);
        return files;
    }

    /**
     * 递归获取文件
     * @param file
     * @param resultFile
     */
    private static void recursionFiles(File file,List<File>resultFile){
        if (file.isFile()){
            resultFile.add(file);
            return;
        }
        File[] files = file.listFiles();
        for(File tmpFile:files){
            recursionFiles(tmpFile,resultFile);
        }
    }


    public static Path getRelativePath(File srcFile,File descFile){
        Path srcPath = srcFile.toPath();
        Path descPath = descFile.toPath();
        return descPath.relativize(srcPath);
    }
}
