package com.dabbler.tools.utils;

import com.dabbler.tools.anonation.Table;
import com.dabbler.tools.anonation.TableColumn;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * 生成SQL
 * @author poplar-hub
 * @version 1.0
 * @date 2023/8/3
 */
public class SqlExpressionUtils {

    public static String getSelectSQL(Class clz){
        Table table  = (Table) clz.getDeclaredAnnotation(Table.class);
        Field[] fields = clz.getDeclaredFields();
        String fieldSQL= Arrays.stream(fields).map(field -> {
            TableColumn column = field.getDeclaredAnnotation(TableColumn.class);
            String columnName = "`"+column.name()+"`";
            return columnName;
        }).collect(Collectors.joining(","));
        String selectSQL = "select " + fieldSQL + " from " +  table.name();
        return selectSQL;
    }

    public static String getInsertSQL(Class clz,Object o) throws IllegalAccessException {
        Field[] fields = clz.getDeclaredFields();
        List<String> valueList = Lists.newArrayList();
        List<String> nameList = Lists.newArrayList();
        for (Field field:fields){
            field.setAccessible(true);
            Object fieldValue = field.get(o);
            TableColumn column = field.getDeclaredAnnotation(TableColumn.class);
            if (column == null|| fieldValue == null){
                continue;
            }
            String objectString = fieldValue.toString();
            if (fieldValue instanceof LocalDateTime){
                objectString = ((LocalDateTime) fieldValue).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                objectString = "\"" + objectString + "\"";
            }else if(fieldValue instanceof String){
                objectString = "\"" + objectString + "\"";
            }
            nameList.add("`"+column.name()+"`");
            valueList.add(objectString);
        }
        Table classAnnotion = (Table) clz.getDeclaredAnnotation(Table.class);
        String table = classAnnotion.name();
        String values = Joiner.on(",").useForNull("").join(valueList);
        String names = Joiner.on(",").join(nameList).toLowerCase(Locale.ROOT);
        String insertSQL = "insert into "+table+"("+names+") values ("+values+");";
        return insertSQL;
    }

}
