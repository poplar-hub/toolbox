package com.dabbler.tools.ast.java;

/**
 * @author Administrator
 * @version 1.0
 * @date 2023/6/16
 */
public class ClassTypeElement extends AbstractJavaElement{

    private String fullQualifiedName;

    @Override
    protected String getFormattedContent() {
        return name;
    }
}
