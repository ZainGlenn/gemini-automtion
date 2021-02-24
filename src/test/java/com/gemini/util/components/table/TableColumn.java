package com.gemini.util.components.table;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface TableColumn {
    int idx();

    Class<? extends CustomValueParser> parser() default PassThroughValueParser.class;
}
