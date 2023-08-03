package com.dabbler.tools.ast.java;

import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @date 2023/6/16
 */
public class ConstructorElement extends MethodElement{
    ConstructorElement(boolean isNative, boolean isSynchronized, Modifier modifier, ClassTypeElement returnType, List<ParameterElement> parameterElements, List<ClassTypeElement> exceptionTypes, List<String> body) {
        super(isNative, isSynchronized, modifier, returnType, parameterElements, exceptionTypes, body);
    }
//    public ConstructorElement(String name) {
//      //  super(name);
//    }
}
