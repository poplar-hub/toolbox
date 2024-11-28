package com.dabbler.tools.utils;



import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * @author RedDabbler
 * @version 1.0
 * @date 2021/12/9
 */
@Slf4j
public class ZipUtils {

    private ZipUtils(){
        throw new UnsupportedOperationException();
    }


    /**
     *  列出压缩文件内所有文件名称
     * @param zipFileName zip文件
     * @param fileOnly 是否只有文件，不包含目录
     * @return
     * @throws IOException
     */
    public static List<String> listFromZip(String zipFileName,boolean fileOnly) throws IOException {
        List<String> fileNames = Lists.newArrayList();
        // 要用GBK编码，否则报错 java.nio.charset.MalformedInputException: Input length = 1
        try(ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFileName),Charset.forName("GBK"))){
            ZipEntry zipEntry ;
            while ((zipEntry= zipInputStream.getNextEntry())!=null){
                log.info("解析到文件:{},文件大小:{}",zipEntry.getName(),zipEntry.getCompressedSize());
                if(fileOnly && zipEntry.isDirectory()){
                    continue;
                }
                fileNames.add(zipEntry.getName());
            }
        }
        return fileNames;
    }


    /** 解压压缩文件
     * @param zipFileName
     * @param passwd
     * @param destDir
     * @throws IOException
     */
    public static void unzip(String zipFileName,String passwd,String destDir)  throws IOException {
        try(ZipFile zipFile = new ZipFile(zipFileName,Charset.forName("GBK"))){
            ZipEntry zipEntry ;
            for(Enumeration<? extends ZipEntry> entryEnumeration = zipFile.entries();entryEnumeration.hasMoreElements();){

                zipEntry = entryEnumeration.nextElement();
                String fileName = zipEntry.getName();
                InputStream inputStream = zipFile.getInputStream(zipEntry);
                Path path = Paths.get(destDir,fileName);
                if (!Files.exists(path)){
                    if (!Files.exists(path.getParent())){
                        Files.createDirectories(path.getParent());
                    }
                    Files.createFile(path);
                }
                Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
            }

        }

    }

}
