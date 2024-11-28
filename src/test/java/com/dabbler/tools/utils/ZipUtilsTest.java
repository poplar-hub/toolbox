package com.dabbler.tools.utils;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ZipUtilsTest {

    @Test
    void unzip() throws IOException {
        String zipFileName = "C:\\Users\\Administrator\\Desktop\\test.zip";
        String destDir="C:\\Users\\Administrator\\Desktop\\dest";
        ZipUtils.unzip(zipFileName,destDir);
    }

    @Test
    public void listFromZip() throws IOException {
        String zipFileName = "C:\\Users\\Administrator\\Desktop\\test.zip";
        ZipUtils.listFromZip(zipFileName,true);
    }
}