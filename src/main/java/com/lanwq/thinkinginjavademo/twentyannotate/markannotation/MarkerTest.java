package com.lanwq.thinkinginjavademo.twentyannotate.markannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Vin lan
 * @className MarkerTest
 * @description TODO
 * @createTime 2020-11-04  15:02
 **/

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MarkerTest {
}

/**
 * @MarkerTest 接口和普通的一个空的接口类似，定义注解会用到一些 <p>元注解(meta-annotation)</p> 如：@Target，@Retention
 */
