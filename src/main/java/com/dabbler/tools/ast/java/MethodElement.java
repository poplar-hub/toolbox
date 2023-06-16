package com.dabbler.tools.ast.java;

import java.util.List;

/**
 * @author poplar-hub
 * @version 1.0
 * @date 2023/6/16
 */
public class MethodElement extends AbstractJavaElement{


    private boolean isNative;
    private boolean isSynchronized;

    private Modifier modifier;
    private ClassTypeElement returnType;
    private List<ParameterElement> parameterElements;

    private List<String> body;


     class ParameterElement extends AbstractJavaElement{
        private ClassTypeElement clz;

    }
}
