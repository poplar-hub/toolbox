package com.dabbler.tools.ast.java;

import com.dabbler.tools.utils.ConstantValue;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author poplar-hub
 * @version 1.0
 * @date 2023/6/16
 */
@Data
public class MethodElement extends AbstractJavaElement{


    private boolean isNative;
    private boolean isSynchronized;

    private Modifier modifier;
    private ClassTypeElement returnType;
    private List<ParameterElement> parameterElements;

    private List<ClassTypeElement> exceptionTypes;
    private List<String> body;



    @Override
    public String getFormattedContent() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(modifier.getCode());
        appendStatic(stringBuilder);
        appendFinal(stringBuilder);
        if (isSynchronized){
            stringBuilder.append(ConstantValue.SYNCHRONIZED).append(ConstantValue.INDENT);
        }
        if (isNative){
            stringBuilder.append(ConstantValue.NATIVE).append(ConstantValue.INDENT);
        }
        stringBuilder.append(returnType.getFormattedContent()).append(name).append("(")
                .append(parameterElements.stream().map(ParameterElement::getFormattedContent).collect(Collectors.joining(",")))
                .append(")").append(ConstantValue.INDENT)
                .append(ConstantValue.THROWS).append(exceptionTypes.stream().map(ClassTypeElement::getFormattedContent).collect(Collectors.joining(",")));
        if(isAbstract() || isNative){
            return stringBuilder.toString();
        }
        stringBuilder.append("{").append("\n")
                .append(body.stream().collect(Collectors.joining("\n"))).append("\n")
                .append("}");
        return stringBuilder.toString();
    }


    class ParameterElement extends AbstractJavaElement{
        private ClassTypeElement clz;

         @Override
         public String getFormattedContent() {

             return clz.getFormattedContent() + ConstantValue.INDENT + name;
         }
     }


}
