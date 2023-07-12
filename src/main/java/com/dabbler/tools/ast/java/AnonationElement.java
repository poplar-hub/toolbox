package com.dabbler.tools.ast.java;

import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.AnnotatedType;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @date 2023/6/16
 */
public class AnonationElement extends AbstractJavaElement{

    private AnnotatedType  annotatedType;

    private List<AnnotatedParameterizedType> annotatedParameterizedTypeList;

    public AnonationElement(String name) {
        super(name);
    }


    @Override
    protected String getFormattedContent() {
        return "@"+name;
    }


}
