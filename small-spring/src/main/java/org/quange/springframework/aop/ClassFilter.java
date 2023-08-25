package org.quange.springframework.aop;

/**
 * @author Lan
 * @createTime 2023-08-25  16:04
 * Filter that restricts matching of a pointcut or introduction to
 * a given set of target classes.
 * 定义类匹配类，用于切点找到给定的接口和目标类。
 **/
public interface ClassFilter {
    /**
     * Should the pointcut apply to the given interface or target class?
     * @param clazz the candidate target class
     * @return whether the advice should apply to the given target class
     */
    boolean matches(Class<?> clazz);
}
