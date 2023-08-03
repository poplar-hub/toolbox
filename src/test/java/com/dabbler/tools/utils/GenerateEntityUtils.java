package com.dabbler.tools.utils;

import com.dabbler.tools.anonation.Table;
import com.dabbler.tools.anonation.TableColumn;
import com.dabbler.tools.utils.domain.Balance;
import com.github.yitter.idgen.YitIdHelper;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @version 1.0
 * @date 2023/8/3
 */
@Slf4j
public class GenerateEntityUtils {


    @Test
    public void testBalance() throws IllegalAccessException {

        Balance balance  = new Balance();
        balance.setId(YitIdHelper.nextId()+"");
        balance.setCate("人情往来");
        balance.setBalanceType(1);
        balance.setDeptCode("10101");
        balance.setDeptName("研发部");
        balance.setMonth("2023-08");
        balance.setCrtTm(LocalDateTime.now());


        Table annotation = balance.getClass().getDeclaredAnnotation(Table.class);
        String table = annotation.name();
        log.info("tableName:{}",table);
        Field[] fields = balance.getClass().getDeclaredFields();
        String fieldSQL= Arrays.stream(fields).map(field -> {
            TableColumn column = field.getDeclaredAnnotation(TableColumn.class);
            String columnName = column.name();
            return columnName;
        }).collect(Collectors.joining(","));


        String selectSql = "select " + fieldSQL + " from " + table;
        log.info("selectSQL:{}",selectSql);

        List<String> valueList = Lists.newArrayList();
        List<String> nameList = Lists.newArrayList();
        for (Field field:fields){
            Class clz = field.getType();
            boolean flag  = clz.isPrimitive();
            log.info("clz:{},isPrimitive:{}",clz,flag);
            field.setAccessible(true);
            Object o = field.get(balance);

            TableColumn column = field.getDeclaredAnnotation(TableColumn.class);
            if (column == null|| o == null){
                continue;
            }
            nameList.add(column.name());
            valueList.add(o.toString());
        }

        String values = Joiner.on(",").useForNull("").join(valueList).toString();
        String names = Joiner.on(",").join(nameList).toLowerCase(Locale.ROOT);
        String insertSQL = "insert into "+table+"("+names+") values ("+values+");";
        log.info("insertSQL:{}",insertSQL);

    }
}
