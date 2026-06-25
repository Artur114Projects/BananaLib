package com.artur114.bananalib.mc.nbt.auto;

import java.lang.annotation.*;

@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface AutoNBTContainer {}