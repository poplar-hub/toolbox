package com.dabbler.tools.ast.java;

import com.dabbler.tools.ast.AbstractElement;

import java.util.List;

/**
 * @author poplar-hub
 * @version 1.0
 * @date 2023/6/15
 */
public class AbstractJavaElement extends AbstractElement {


    protected boolean isStatic;
    protected boolean isFinal;
    protected boolean isAbstract;
    protected String name;
    protected List<AnonationElement> anonationElementList;
    private List<String> comments;


    public AbstractJavaElement(){

    }
}
