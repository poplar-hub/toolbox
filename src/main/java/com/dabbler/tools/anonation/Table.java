package com.dabbler.tools.anonation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author poplar-hub
 * @version 1.0
 * @date 2023/8/3
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {

    public String name();

    /**
     * 注释
     * @return
     */
    public String comment() default "";
}
