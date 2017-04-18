package com.watershooter.lighting.common.bundle;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * BunbleValue注解，用于数据的保存和恢复。
 * Created by macchen on 15/4/28.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BundleValue {
    BundleType type();

    String name() default "";
}
