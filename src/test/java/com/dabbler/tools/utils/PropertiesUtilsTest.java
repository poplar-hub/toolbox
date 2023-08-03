package com.dabbler.tools.utils;


import org.junit.jupiter.api.Test;

public class PropertiesUtilsTest {

    @Test
    public void getClassPath() {
        String rootPath = PropertiesUtils.getProjectBootPath();
        String pat = PropertiesUtils.getClassLoaderPath();
        System.out.println(rootPath);
        System.out.println(pat);
    }


}