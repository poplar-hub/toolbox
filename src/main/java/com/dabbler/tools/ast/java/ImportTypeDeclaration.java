package com.dabbler.tools.ast.java;

import com.dabbler.tools.ast.java.constant.Reserve;
import org.apache.commons.lang3.StringUtils;

/**
 * @author poplar-hub
 * @version 1.0
 * @date 2023/6/15
 */
public class ImportTypeDeclaration extends AbstractJavaElement{


    public ImportTypeDeclaration(String name) {
        super(name);
    }

    @Override
    protected String getFormattedContent() {
        return Reserve.IMPORT + StringUtils.SPACE + name + ";" + StringUtils.LF;
    }
}
