package com.redDabbler.template.tools.utils;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;


public class FileHelperTest {

    @Test
    public void getInputStream() {
    }

    @Test
    public void recursionCreateIfNotExists() {
    }

    @Test
    public void listFiles() {
    }

    @Test
    public void readFileLines() {
    }

    @Test
    public void listModifyFile() throws FileNotFoundException, ParseException, IOException {
        Date startTime = DateUtils.parseDate("20190922","yyyyMMdd");
        List<File> files = FileHelper.listModifyFile(new File("E:\\"),startTime,new Date());
        for(File t:files){
            System.out.println(t.getCanonicalPath());
        }
    }

    @Test
    public void getRelativePath() {
    }
}