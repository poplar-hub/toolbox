package com.dabbler.tools.ast.java;


/**
 * @author poplar-hub
 * @version 1.0
 * @date 2023/6/15
 */
public enum Modifier {

    PUBLIC("public"),
    PROTECTED("protected"),
    DEFAULT(""),
    PRIVATE("private");

    private String code;

    Modifier(String code){
        this.code = code;
    }

    public static Modifier getByCode(String code){
        for (Modifier modifier:Modifier.values()){
            if (modifier.code.equalsIgnoreCase(code)){
                return modifier;
            }
        }
        throw new IllegalArgumentException("不存在的code:"+code);
    }

    public String getCode() {
        return code;
    }
}
