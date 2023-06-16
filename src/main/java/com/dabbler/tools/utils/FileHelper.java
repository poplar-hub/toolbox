package com.dabbler.tools.utils;

import com.google.common.collect.Lists;

import java.io.*;
import java.nio.file.Path;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Date;
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
    public static InputStream getInputStream(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        checkExists(file);
        if (file.isDirectory()) {
            throw new IllegalArgumentException("file is Directory");
        }
        InputStream inputStream = new FileInputStream(file);
        return inputStream;
    }

    /**
     * @param filePath
     * @param isDir    当前文件是否是文件夹
     * @return
     * @throws IOException
     */
    public static boolean recursionCreateIfNotExists(String filePath, boolean isDir) throws IOException {
        File file = new File(filePath);
        if (file.exists()) {
            if (file.isDirectory() && isDir) {
                return true;
            }
            if (file.isFile() && !isDir) {
                return true;
            }
            throw new IllegalArgumentException(" create failed ! file has exists  but type not matched");
        }
        if (isDir) {
            file.mkdirs();
        }
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
        return file.createNewFile();
    }

    /**
     * 递归获取所有的文件
     *
     * @param file
     * @return
     */
    public static List<File> listFiles(File file, FileFilter fileFilter) throws FileNotFoundException {
        checkExists(file);
        List<File> files = new ArrayList();
        recursionFiles(file, files);
        List<File> resultFiles = Lists.newArrayList();
        for (File tmpFile:files){
            if (fileFilter.accept(tmpFile)){
                resultFiles.add(tmpFile);
            }
        }
        return resultFiles;
    }

    /**
     * 递归获取所有文件，不能带过滤条件，容易把文件夹过滤掉
     *
     * @param file
     * @param resultFile
     */
    private static void recursionFiles(File file, List<File> resultFile) {
        if (file.isFile()) {
            resultFile.add(file);
            return;
        }
        File[] files = file.listFiles();;
        for (File tmpFile : files) {
            recursionFiles(tmpFile, resultFile);
        }
    }



    /**
     * 读出文件内容
     *
     * @param file
     * @return
     * @throws FileNotFoundException
     */
    public static List<String> readFileLines(File file) throws FileNotFoundException {
        checkExists(file);
        if (checkDirectory(file)) {
            throw new IllegalArgumentException();
        }

        List<String> lines = new ArrayList<String>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = null;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                    bufferedReader = null;
                } catch (IOException e) {
                    bufferedReader = null;
                }
            }
        }
        return lines;
    }


    public static String readFileString(File file) throws FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException(file.getPath());
        }
        List<String> lines = readFileLines(file);
        StringBuilder total = new StringBuilder("");
        lines.forEach(line->{
            total.append(line);
        });

        return total.toString();
    }
    /**
     * 获取修改日期再某段时间内的文件
     *
     * @param file
     * @param startTime
     * @param endTime
     * @return
     * @throws FileNotFoundException
     */
    public static List<File> listModifyFile(File file, final Date startTime, final Date endTime) throws FileNotFoundException {

        FileFilter dateFilter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                long modifyTime = pathname.lastModified();
                long st = startTime.getTime();
                long et = endTime.getTime();
                if (modifyTime >= st && modifyTime <= et) {
                    return true;
                }
                return false;
            }
        };
        return listFiles(file, dateFilter);
    }


    private static boolean checkExists(File file) {
        if (!file.exists()) {
            return false;
        }
        return true;
    }

    private static boolean checkDirectory(File file) {
        return file.isDirectory();
    }


    public static Path getRelativePath(File srcFile, File descFile) {
        Path srcPath = srcFile.toPath();
        Path descPath = descFile.toPath();
        return descPath.relativize(srcPath);
    }

    public static void createFileIfNotExists(String path) {
        File file = new File(path);
        boolean flag = checkExists(file);
        if (flag) return;
        try {
            File parentDir = file.getCanonicalFile().getParentFile();
            parentDir.mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static  void writeStringToFile(File file,String content){

        if (file == null || file.isDirectory()){
            throw new InvalidParameterException("请检查参数");
        }
        if(!file.exists()){
            try {
                recursionCreateIfNotExists(file.getPath(),false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try(FileWriter fileWriter = new FileWriter(file)){
            fileWriter.write(content);
        }catch (IOException e){

        }

    }
}
