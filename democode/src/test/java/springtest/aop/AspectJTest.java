package springtest.aop;

import org.aspectj.lang.JoinPoint;
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

    /**
     * joinPoint（连接点、切入点）：表示可以将横切的逻辑织入的地方，比如方法调用、方法执行、属性设置等。
     *
     * Pointcut 通过表达式定义的一组joinPoint的集合，比如定义一个pointCut为"com.lucky.test包下的所有Service中的add方法"，
     * 这样就可以定义哪些具体的joinPoint需要织入横切逻辑.
     *
     * Advice（增强）：横切的具体逻辑，比如日志打印，权限校验等这些系统需求就需要在业务代码上增强功能，这些具体的横切逻辑就叫做Advice
     *
     * aspect（切面）：切点和增强组合一起就叫做切面，一个切面就定义了在哪些连接点需要织入什么横切逻辑
     *
     * target（目标）：需要织入切面的具体目标对象，比如在UserService类的addUser方法前面织入打印日志逻辑，那么UserService这个类就是目标对象
     *
     * weaving（织入）：将横切逻辑添加到目标对象的过程叫做织入
     * 总结：在target的joinPoint处weaving一个或多个以Advice和pointCut组成的
     */
    @Pointcut("execution(* springtest.aop..*.test(..))")
    public void test() {

    }

    @Before("test()")
    public void beforeTest(JoinPoint joinPoint) {
        System.out.println("@Before beforeTest");
    }

    @After("test()")
    public void afterTest() {
        System.out.println("@After afterTest");
    }

    @AfterThrowing("test()")
    public void afterThrowing(JoinPoint joinPoint) {
        System.out.println("@AfterThrowing ");
    }

    @AfterReturning("test()")
    public void afterReturning() {
        System.out.println("@AfterReturning");
    }

    @Around("test()")
    public Object aroundTest(ProceedingJoinPoint p) {
        System.out.println("@Around 方法 before");
        Object o = null;
        try {
            o = p.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("@Around 方法 after");
        return o;
    }

}
