package com.dabbler.tools.ast.java;

import lombok.extern.slf4j.XSlf4j;

import java.lang.reflect.AnnotatedArrayType;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.AnnotatedType;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @date 2023/6/16
 */
@XSlf4j
public class AnonationElement extends AbstractJavaElement{

    private AnnotatedType  annotatedType;

    private List<AnnotatedParameterizedType> annotatedParameterizedTypeList;



    protected String getFormattedContent() {
        return "@"+name;
    }


}
