package com.redDabbler.template.tools.utils;

public class PropertiesUtils {

    private PropertiesUtils() {
        throw new UnsupportedOperationException();
    }

    /**
     * 项目根路径
     * @return
     */
    public static String getProjectBootPath(){
        return System.getProperty("user.dir");
    }

    /**
     * 类加载器的路径，即class文件路径
     * @return
     */
    public static String getClassLoaderPath(){
       return  Thread.currentThread().getContextClassLoader().getResource(".").getPath();
    }

    /**
     * 当前类的文件路径夹
     * @param clazz
     * @return
     */
    public static String getCurrentClassPath(Class clazz){
        return  clazz.getResource(".").getPath();
    }


}
