package springtest.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @author Vin lan
 * @className AspectJTest
 * @description 创建 advisor
 * @createTime 2021-08-27  09:57
 *
 * execution(* com.sample.service.impl..*.*(..))
 * execution（）     表达式的主体；
 * 第一个 * 符号     表示返回值的类型任意；
 * com.sample.service.impl	AOP所切的服务的包名，即，我们的业务部分
 * 包名后面的 .. 	表示当前包及子包
 * 第二个 *	        表示类名,*即所有类。 此处可以自定义，
 * .*(..)	        表示任何方法名，括号表示参数，两个点表示任何参数类型
 **/
@Aspect
public class AspectJTest {
    @Pointcut("execution(* springtest.aop..*.test(..))")
    public void test() {
        System.out.println("AspectJTest test");
    }

    @Before("test()")
    public void beforeTest() {
        System.out.println("beforeTest");
    }

    @After("test()")
    public void afterTest() {
        System.out.println("afterTest");
    }

    @Around("test()")
    public Object aroundTest(ProceedingJoinPoint p) {
        System.out.println("before1");
        Object o = null;
        try {
            o = p.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("after1");
        return o;
    }

}
