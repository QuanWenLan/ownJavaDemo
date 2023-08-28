package org.quange.springframework.aop;

/**
 * @author Lan
 * @createTime 2023-08-28  10:06
 **/
public interface PointcutAdvisor extends Advisor {

    /**
     * Get the Pointcut that drives this advisor.
     */
    Pointcut getPointcut();

}
