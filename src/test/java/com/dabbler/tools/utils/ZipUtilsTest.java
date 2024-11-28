package com.dabbler.tools.utils;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ZipUtilsTest {

    @Test
    void unzip() throws IOException {
        String zipFileName = "C:\\Users\\Administrator\\Desktop\\test.zip";
        String destDir="C:\\Users\\Administrator\\Desktop\\dest";
      //  ZipUtils.unzip(zipFileName,destDir);
        zipFileName="C:\\Users\\Administrator\\Desktop\\dest\\交易流水证明_用于个人对账_20241006_105055.zip";
        ZipUtils.unzip(zipFileName,"755685",destDir);
    }

    @Test
    public void listFromZip() throws IOException {
        String zipFileName = "C:\\Users\\Administrator\\Desktop\\test.zip";
        ZipUtils.listFromZip(zipFileName,true);
    }

}