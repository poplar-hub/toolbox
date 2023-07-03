package com.dabbler.tools.ast.java;

import com.dabbler.tools.utils.ConstantValue;
import lombok.Data;

import java.util.List;

/**
 * @author poplar-hub
 * @version 1.0
 * @date 2023/6/16
 */

@Data
public class ClassElement extends AbstractJavaElement{



    private Modifier modifier;
    private ClassTypeElement extend;
    private List<ClassTypeElement> implementList;
    private List<FieldElement> fieldElements;
    private List<ConstructorElement> constructorElements;
    private List<MethodElement> methodElements;


    @Override
    protected String getFormattedContent() {

        StringBuilder stringBuilder =new StringBuilder();

        stringBuilder.append(modifier.getCode()).append(ConstantValue.INDENT);
        appendStatic(stringBuilder);
        appendFinal(stringBuilder);
        stringBuilder.append(ConstantValue.CLASS).append(ConstantValue.INDENT)
                .append(name);
        return null;
    }
}
