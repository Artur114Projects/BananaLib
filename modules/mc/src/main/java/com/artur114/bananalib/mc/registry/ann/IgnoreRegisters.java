package com.artur114.bananalib.mc.registry.ann;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface IgnoreRegisters {
    Class<?>[] value() default {};
}
