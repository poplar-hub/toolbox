package com.dabbler.tools.ast.java;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author poplar
 * @version 1.0
 * @date 2023/7/10
 */
@Slf4j
class FieldElementTest {

    @Test
    void getFormattedContent() {
        ClassTypeElement classTypeElement =new ClassTypeElement("java.lang.String");
        FieldElement fieldElement = new FieldElement(Modifier.PRIVATE,classTypeElement,"field");
        log.info("field:\n{}",fieldElement.getFormattedContent());
    }
}