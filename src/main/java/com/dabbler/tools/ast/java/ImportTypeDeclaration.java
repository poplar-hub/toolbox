package com.dabbler.tools.ast.java;

/**
 * @author poplar-hub
 * @version 1.0
 * @date 2023/6/15
 */
public class ImportTypeDeclaration extends AbstractJavaElement{

    private Class clz;

    public ImportTypeDeclaration(String name) {
        super(name);
    }

    @Override
    protected String getFormattedContent() {
        return null;
    }
}
