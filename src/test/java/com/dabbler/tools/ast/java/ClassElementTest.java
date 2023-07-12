package com.dabbler.tools.ast.java;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;


/**
 * @author poplar-hub
 * @version 1.0
 * @date 2023/7/10
 */
@Slf4j
class ClassElementTest {


    @Test
    void getFormattedContent() {
        Modifier modifier = Modifier.PRIVATE;
        ClassElement classElement = new ClassElement("Demo",modifier);
        ClassTypeElement extend = new ClassTypeElement("com.tools.ParentDemo");
        classElement.setExtend(extend);

        String content = classElement.getFormattedContent();
        log.info("content:\n{}",content);
    }
}