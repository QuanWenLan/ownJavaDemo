package com.lanwq.thinkinginjavademo.twentiethannotate.customizedAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Vin lan
 * @className UseCase
 * @description TODO 自定义注解, 可以用来跟踪项目中的用例
 * @createTime 2020-11-04  15:07
 **/

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UseCase {
    public int id() default 0;

    public String description() default "no description";
}
/**
 * 有元素的注解，元素定义，类似接口中方法的定义。int元素id,String元素description
 * 注解元素可用的类型如下：
 * 1. 所有的基本类型（int,float,boolean等）
 * 2. String
 * 3. Class
 * 4. Enum
 * 5. Annotation
 * 6. 以上类型的数组
 */
