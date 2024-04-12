package com.lanwq.java8.inaction.chapter3_function_interface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Vin lan
 * @className ThreadWithLambdaTest
 * @description TODO 使用lambda表达式和不适用lambda表达式。 采用的是函数式编程
 * @createTime 2020-10-10  15:43
 * Lambda 表达式允许我们将一个函数作为参数进行传递，一个函数定义了一个行为，语法如下所示。(参数) -> (方法体)
 * @see <a href="https://www.javazhiyin.com/40488.html">https://www.javazhiyin.com/40488.html</a>
 **/
public class ThreadWithLambdaTest {
    public static void main(String[] args) {
        withNoLambda();
        withLambda();

        ThreadWithLambdaTest test = new ThreadWithLambdaTest();
        // consumer，消耗性接口
        // 不带参数，不带返回值
        // 使用lambda
        test.testMyInterface(() -> {
            System.out.println(123);
        });
        // 不使用
        test.testMyInterface(new MyConsumerWithNoParam() {
            @Override
            public void test() {
                System.out.println(233);
            }
        });
        // 带参数，但是没有返回值
        // 使用lambda
        test.testMyInterfaceWithNoReturn((int x) -> {
            System.out.println("传入的参数是：" + x);
        });
        // 不使用
        test.testMyInterfaceWithNoReturn(new MyConsumerWithParamWithNoReturn() {
            @Override
            public void test(int x) {
                System.out.println("传入的参数是：" + x);
            }
        });

        // 带参数，带返回值
        test.testMyInterfaceWithReturn((int x, int y) -> {
            System.out.println("传入的参数是：" + x + " y:" + y);
            return x + y;
        });

        // predicate
        System.out.println(test.testPredicate(str -> str.length() > 5, "hello world"));

        MyPredicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
        List<String> listOfStrings = Arrays.asList("foo", "", "bar", "baz", "");
        List<String> nonEmpty = test.testPredicate2(nonEmptyStringPredicate, listOfStrings);
        System.out.println("nonEmpty = " + nonEmpty);

        // supplier
        System.out.println(test.testSupplier(() -> "hello world"));
    }

    public String testSupplier(MySupplier<String> supplier) {
        return supplier.get();
    }

    public static void withNoLambda() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("withNoLambda do it...");
            }
        }).start();
    }

    public static void withLambda() {
        new Thread(() -> {
            System.out.println("withLambda do it...");
        }).start();
        // 上面传递的是一个函数，定义了线程的具体任务，即行为。通过对比可以得出结论，使用 Lambda 表达式可以替代传统的匿名类开发方式，
        // 不需要创建匿名类即可完成业务逻辑的代码编写，开发效率更高。
    }

    public void testMyInterface(MyConsumerWithNoParam myInterface) {
        myInterface.test();
    }

    public void testMyInterfaceWithNoReturn(MyConsumerWithParamWithNoReturn myInterfaceWithNoReturn) {
        myInterfaceWithNoReturn.test(10);
    }

    public void testMyInterfaceWithReturn(MyFunctionWithParamWithReturn myInterface) {
        System.out.println(myInterface.apply(10, 20));
    }

    // 测试断言
    public boolean testPredicate(MyPredicate<String> predicate, String str) {
        return predicate.test(str);
    }

    public List<String> testPredicate2(MyPredicate<String> predicate, List<String> list) {
        List<String> results = new ArrayList<>();
        for (String s : list) {
            if (predicate.test(s)) {
                results.add(s);
            }
        }
        return results;
    }

    // supplier
}
