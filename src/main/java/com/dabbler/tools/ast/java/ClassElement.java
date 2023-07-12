package com.dabbler.tools.ast.java;

import com.dabbler.tools.ast.java.constant.Reserve;
import static com.dabbler.tools.utils.ConstantValue.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;
/**
 * @author poplar-hub
 * @version 1.0
 * @date 2023/6/16
 */


public class ClassElement extends AbstractJavaElement{



    private Modifier modifier;
    @Getter
    @Setter
    private ClassTypeElement extend;
    @Getter
    @Setter
    private List<ClassTypeElement> implementList;
    @Getter
    @Setter
    private List<FieldElement> fieldElements;
    private List<ConstructorElement> constructorElements;
    private List<MethodElement> methodElements;

    public ClassElement(String name,Modifier modifier){
        super(name);
        this.modifier = modifier;
    }

    @Override
    protected String getFormattedContent() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(modifier.getCode()).append(INDENT);
        appendStatic(stringBuilder);
        appendFinal(stringBuilder);
        stringBuilder.append(Reserve.CLASS).append(INDENT)
                .append(name);

        if (extend!=null){
            stringBuilder.append(INDENT).append(Reserve.EXTENDS).append(INDENT).append(extend.getName());
        }

        if(CollectionUtils.isNotEmpty(implementList)){
            stringBuilder.append(INDENT)
                    .append(Reserve.IMPLEMENTS).append(INDENT)
                    .append(implementList.stream().map(ClassTypeElement::getName).collect(Collectors.joining(",")));
        }

        stringBuilder.append(OPENING_BRACE).append(LINE_BREAK);

        if (CollectionUtils.isNotEmpty(fieldElements)){
            fieldElements.stream().forEach(fieldElement -> {
               stringBuilder.append(TAB).append(fieldElement.getFormattedContent()).append(SEMICOLON).append(LINE_BREAK);
            });
        }

        stringBuilder.append(CLOSING_BRACE);

        return stringBuilder.toString();
    }




}
