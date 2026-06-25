package com.artur114.bananalib.mc.nbt.auto;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface AutoNBTEntry {
    String value() default "";
}