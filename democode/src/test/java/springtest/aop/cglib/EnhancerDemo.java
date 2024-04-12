package springtest.aop.cglib;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author Vin lan
 * @className EnhancerDemo
 * @description
 * @createTime 2021-08-30  15:30
 **/
public class EnhancerDemo {
    public static void main(String[] args) {
//        指定生成的字节码文件位置
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\projects\\ownJavaDemo");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(EnhancerDemo.class);
        enhancer.setCallback(new MethodInterceptorImpl());

        EnhancerDemo demo = (EnhancerDemo) enhancer.create();
        demo.test();
        System.out.println(demo);
    }

    public void test() {
        System.out.println("EnhancerDemo apply()");
        test2();
    }

    /**
     * cglib 创建的代理是通过继承来实现的。
     * 代理类是被代理类的子类，子类重写了 test2() 方法，所以在 apply() 中调用 test2() 会调用到子类中去（也就是代理类中，然后代理类中
     * 又是会调用 intercept 方法）。
     */
    public void test2() {
        System.out.println("EnhancerDemo 嵌套调用 test2()");
    }

    static class MethodInterceptorImpl implements MethodInterceptor {

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("before invoke" + method);
            Object result = methodProxy.invokeSuper(o, objects);
            System.out.println("after invoke" + method);
            return result;
        }
    }
}
