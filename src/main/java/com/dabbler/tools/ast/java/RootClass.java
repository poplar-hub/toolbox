package com.dabbler.tools.ast.java;

import java.util.List;

/**
 * @author poplar-hub
 * @version 1.0
 * @date 2023/6/15
 */
public class RootClass extends ClassElement {

    private String packageDeclaration;

    private List<ImportTypeDeclaration> importTypeDeclarations;
    private List<ClassElement> classElements;


}
