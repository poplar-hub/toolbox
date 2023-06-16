package com.dabbler.tools.ast.java;

import lombok.Data;

import java.util.List;

/**
 * @author poplar-hub
 * @version 1.0
 * @date 2023/6/16
 */

@Data
public class ClassElement extends AbstractJavaElement{



    private ClassTypeElement extend;
    private List<ClassTypeElement> implementList;
    private List<FieldElement> fieldElements;
    private List<ConstructorElement> constructorElements;
    private List<MethodElement> methodElements;


}
