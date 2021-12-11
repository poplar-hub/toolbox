package com.redDabbler.template.tools.utils;



import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author RedDabbler
 * @version 1.0
 * @date 2021/12/9
 */
@Slf4j
public class ZipUtils {

    private ZipUtils(){

    }


    /**
     *
     * @param zipFileName zip文件
     * @param fileOnly 是否只有文件，不包含目录
     * @return
     * @throws IOException
     */
    public static List<String> listFromZip(String zipFileName,boolean fileOnly) throws IOException {
        List<String> fileNames = Lists.newArrayList();
        // 要用GBK编码，否则报错
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFileName), Charset.forName("GBK"));
        ZipEntry zipEntry ;
        while ((zipEntry= zipInputStream.getNextEntry())!=null){
            log.info("解析到文件:{},文件大小:{}",zipEntry.getName(),zipEntry.getCompressedSize());
            if(fileOnly && zipEntry.isDirectory()){
                continue;
            }
            fileNames.add(zipEntry.getName());
        }
        return fileNames;
    }

}
