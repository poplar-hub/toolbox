package com.dabbler.tools.ast.java;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author poplar-hub
 *
 * @version 1.0
 * @date 2023/7/26
 */
@Slf4j
class MethodElementTest {

    @Test
    void getFormattedContent() {
        ClassTypeElement classTypeElement = new ClassTypeElement("com.dabbler.Lab");
        MethodElement methodElement = new MethodElement("getName",Modifier.PUBLIC,classTypeElement,null);
        log.info("formattedContent {}",methodElement.getFormattedContent());
    }
}