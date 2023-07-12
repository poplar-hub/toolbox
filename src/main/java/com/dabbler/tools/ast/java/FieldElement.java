package com.dabbler.tools.ast.java;

import com.dabbler.tools.utils.ConstantValue;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author poplar-hub
 * @version 1.0
 * @date 2023/6/16
 */
@Getter
@Setter
public class FieldElement extends AbstractJavaElement{


    private Modifier modifier;
    private ClassTypeElement clz;
    private boolean isTransient;
    private boolean isVolatile;
    //TODO 怎么处理
    private String value;

    public FieldElement(Modifier modifier,ClassTypeElement clz,String name) {
        super(name);
        this.modifier = modifier;
        this.clz = clz;
    }

    @Override
    protected String getFormattedContent() {
        StringBuilder stringBuilder =new StringBuilder();
        stringBuilder.append(modifier.getCode()).append(ConstantValue.INDENT).append(clz.getSimpleName()).append(ConstantValue.INDENT).append(this.name);
        if (StringUtils.isNotEmpty(value)){
            stringBuilder.append(ConstantValue.INDENT).append("=").append(ConstantValue.INDENT).append(value);
        }
        stringBuilder.append(ConstantValue.SEMICOLON);

        return stringBuilder.toString();
    }
}
