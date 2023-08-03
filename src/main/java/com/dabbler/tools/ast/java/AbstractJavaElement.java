package com.dabbler.tools.ast.java;

import com.dabbler.tools.ast.AbstractElement;
import com.dabbler.tools.ast.java.constant.Reserve;
import com.dabbler.tools.utils.ConstantValue;

import java.lang.reflect.AnnotatedElement;
import java.util.List;

/**
 * @author poplar-hub
 * @version 1.0
 * @date 2023/6/15
 */
public abstract class AbstractJavaElement extends AbstractElement {


    protected boolean isStatic;
    protected boolean isFinal;
    protected boolean isAbstract;
    protected  String name;
    protected List<AnnotatedElement> anonationElementList;
    private List<String> comments;

    public AbstractJavaElement(){}


    public AbstractJavaElement(String name){

        this.name = name;
    }


    protected abstract String getFormattedContent();

    public  void appendStatic(StringBuilder stringBuilder){
        if (isStatic){
            stringBuilder.append(Reserve.STATIC).append(ConstantValue.INDENT);
        }
    }

    public void appendFinal(StringBuilder stringBuilder){
        if (isFinal){
            stringBuilder.append(Reserve.FINAL).append(ConstantValue.INDENT);
        }
    }

    public boolean isStatic() {
        return isStatic;
    }

    public AbstractJavaElement setStatic(boolean aStatic) {
        isStatic = aStatic;
        return this;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public AbstractJavaElement setFinal(boolean aFinal) {
        isFinal = aFinal;
        return this;
    }

    public boolean isAbstract() {
        return isAbstract;
    }

    public AbstractJavaElement setAbstract(boolean anAbstract) {
        isAbstract = anAbstract;
        return this;
    }

    public String getName() {
        return name;
    }

    public AbstractJavaElement setName(String name) {
        this.name = name;
        return this;
    }

    public List<AnnotatedElement> getAnonationElementList() {
        return anonationElementList;
    }

    public AbstractJavaElement setAnonationElementList(List<AnnotatedElement> anonationElementList) {
        this.anonationElementList = anonationElementList;
        return this;
    }

    public List<String> getComments() {
        return comments;
    }

    public AbstractJavaElement setComments(List<String> comments) {
        this.comments = comments;
        return this;
    }
}
