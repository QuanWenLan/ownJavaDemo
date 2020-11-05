package com.lanwq.thinkinginjavademo.twentyannotate.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Vin lan
 * @className DBTable
 * @description TODO 数据库表名
 * @createTime 2020-11-04  15:56
 **/
@Target(ElementType.TYPE) // applies to classes only
@Retention(RetentionPolicy.RUNTIME)
public @interface DBTable {
    public String name() default " ";
}
