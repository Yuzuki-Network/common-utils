package dev.yuzuki.utils.event;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Listener {
    int priority() default 0;
    boolean parallel() default false;
}
