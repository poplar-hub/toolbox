package com.dabbler.tools.ast.java;

/**
 * @author Administrator
 * @version 1.0
 * @date 2023/6/16
 */
public class FieldElement extends AbstractJavaElement{


    private ClassTypeElement clz;
    private boolean isTransient;
    private boolean isVolatile;

    @Override
    protected String getFormattedContent() {
        return null;
    }
}
