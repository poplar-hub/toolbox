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
import java.time.format.DateTimeFormatter;
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

        String selectSql = SqlExpressionUtils.getSelectSQL(Balance.class);
        log.info("selectSQL:{}",selectSql);
//        Field[] fields = balance.getClass().getDeclaredFields();
//        List<String> valueList = Lists.newArrayList();
//        List<String> nameList = Lists.newArrayList();
//        for (Field field:fields){
//            Class clz = field.getType();
//            boolean flag  = clz.isPrimitive();
//            log.info("clz:{},isPrimitive:{}",clz,flag);
//            field.setAccessible(true);
//            Object o = field.get(balance);
//
//
//            TableColumn column = field.getDeclaredAnnotation(TableColumn.class);
//            if (column == null|| o == null){
//                continue;
//            }
//            String objectString = o.toString();
//            if (o instanceof LocalDateTime){
//                LocalDateTime time = (LocalDateTime) o;
//                log.info("{}",time);
//                objectString = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//            }else if(o instanceof String){
//                objectString = "\"" + objectString + "\"";
//            }
//            nameList.add(column.name());
//            valueList.add(objectString);
//        }
//        Table classAnnotion = Balance.class.getDeclaredAnnotation(Table.class);
//        String table = classAnnotion.name();
//        String values = Joiner.on(",").useForNull("").join(valueList).toString();
//        String names = Joiner.on(",").join(nameList).toLowerCase(Locale.ROOT);
//        String insertSQL = "insert into "+table+"("+names+") values ("+values+");";
        String insertSQL = SqlExpressionUtils.getInsertSQL(Balance.class,balance);
        log.info("insertSQL:{}",insertSQL);

    }
}
