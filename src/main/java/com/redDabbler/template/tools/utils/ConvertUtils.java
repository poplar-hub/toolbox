package com.redDabbler.template.tools.utils;
import java.util.Iterator;
import java.util.List;

public class ConvertUtils {

    private ConvertUtils() {
        throw new UnsupportedOperationException();
    }

    public static <T> String listToStr(List<T> values,String separator){
        if (values==null){
            return "''";
        }
        StringBuilder stringBuilder = new StringBuilder("'");
        Iterator<T> iterable = values.iterator();
        while(iterable.hasNext()){
            T t = iterable.next();
            stringBuilder.append(t.toString());
            if(iterable.hasNext()){
                stringBuilder.append(separator);
            }
        }
        stringBuilder.append("'");
        return stringBuilder.toString();
    }

}
