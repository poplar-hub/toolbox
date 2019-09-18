package com.redDabbler.template.tools.utils;

import org.junit.Test;

public class PropertiesUtilsTest {

    @Test
    public void getClassPath() {
        String rootPath = PropertiesUtils.getProjectBootPath();
        String pat = PropertiesUtils.getClassLoaderPath();
        System.out.println(rootPath);
        System.out.println(pat);
    }


}