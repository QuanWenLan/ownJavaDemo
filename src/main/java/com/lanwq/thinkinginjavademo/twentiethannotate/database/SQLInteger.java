package com.lanwq.thinkinginjavademo.twentiethannotate.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Vin lan
 * @className SQLInteger
 * @description TODO 数据库int字段
 * @createTime 2020-11-04  16:02
 **/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLInteger {
    int value() default 0;

    String name() default " ";

    Constraints constraints() default @Constraints; // 使用了嵌套注解
}
