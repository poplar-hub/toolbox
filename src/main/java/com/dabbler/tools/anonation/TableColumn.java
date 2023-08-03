package com.dabbler.tools.anonation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author poplar-hub
 * @version 1.0
 * @date 2023/8/3
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface TableColumn {

    /**
     * 列名
     * @return
     */
    public String name();

    /**
     * 注释
     * @return
     */
    public String comment() default "";


}
