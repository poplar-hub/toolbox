package com.dabbler.tools.ast.java;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Administrator
 * @version 1.0
 * @date 2023/6/16
 */
public class ClassTypeElement extends AbstractJavaElement{

    private String fullQualifiedName;

    public ClassTypeElement(String fullQualifiedName) {
        super(fullQualifiedName);
        this.fullQualifiedName = fullQualifiedName;
        this.name = StringUtils.substring(fullQualifiedName,StringUtils.lastIndexOf(fullQualifiedName,".")+1);
    }

    public String getSimpleName(){
        return name;
    }

    @Override
    protected String getFormattedContent() {
        return name;
    }
}
