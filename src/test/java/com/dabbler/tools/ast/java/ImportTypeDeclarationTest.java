package com.dabbler.tools.ast.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author poplar-hub
 * @version 1.0
 * @date 2023/7/27
 */
class ImportTypeDeclarationTest {

    @Test
    void getFormattedContent() {

        ImportTypeDeclaration importTypeDeclaration = new ImportTypeDeclaration("com.dabbler.utils.StringUtils");
        System.out.println(importTypeDeclaration.getFormattedContent());
    }
}