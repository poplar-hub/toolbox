package com.dabbler.tools.ast.java;

import com.dabbler.tools.ast.java.constant.Reserve;
import static com.dabbler.tools.utils.ConstantValue.*;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

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

    private List<ClassTypeElement> exceptionTypes;
    private List<String> body;


    public MethodElement(String name){
        super(name);
    }

    public MethodElement(String name,Modifier modifier,ClassTypeElement returnType,List<ParameterElement> parameterElements){
        this(name);
        this.modifier = modifier;
        this.returnType = returnType;
        this.parameterElements = parameterElements;
    }

    @Override
    public String getFormattedContent() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(modifier.getCode());
        appendStatic(stringBuilder);
        appendFinal(stringBuilder);
        if (isSynchronized){
            stringBuilder.append(Reserve.SYNCHRONIZED).append(INDENT);
        }
        if (isNative){
            stringBuilder.append(Reserve.NATIVE).append(INDENT);
        }
        stringBuilder.append(returnType.getFormattedContent()).append(name).append("(")
                .append(parameterElements.stream().map(ParameterElement::getFormattedContent).collect(Collectors.joining(",")))
                .append(")").append(INDENT);
        if (CollectionUtils.isNotEmpty(exceptionTypes)){
            stringBuilder.append(Reserve.THROWS).append(exceptionTypes.stream().map(ClassTypeElement::getFormattedContent).collect(Collectors.joining(",")));
        }

        if(isAbstract() || isNative){
            return stringBuilder.toString();
        }
        stringBuilder.append(OPENING_BRACE).append(LINE_BREAK)
                .append(body.stream().collect(Collectors.joining(LINE_BREAK))).append(LINE_BREAK)
                .append(CLOSING_BRACE);
        return stringBuilder.toString();
    }


    class ParameterElement extends AbstractJavaElement{
        private ClassTypeElement clz;

        public ParameterElement(ClassTypeElement clz,String name) {
            super(name);
            this.clz = clz;
        }

        @Override
         public String getFormattedContent() {

             return clz.getSimpleName() + INDENT + name;
         }
     }


}
