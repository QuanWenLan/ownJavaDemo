package com.lanwq.thinkinginjavademo.twentiethannotate.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Vin lan
 * @className SQLString
 * @description TODO 数据库String字段
 * @createTime 2020-11-04  16:00
 **/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLString {
    public int value() default 0;  // 使用value的元素，可以在指定单个的时候直接使用

    public String name() default " ";

    Constraints constraints() default @Constraints;
//    Constraint constraints() default @Constraint(allowNull = true);
}
