package com.artur114.bananalib.mc.register;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.PACKAGE })
public @interface IgnoreRegisters {
    Class<?>[] value() default {};
}
