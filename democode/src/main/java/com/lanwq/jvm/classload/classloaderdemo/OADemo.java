package com.lanwq.jvm.classload.classloaderdemo;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @program: javaDemo->OADemo
 * @description: 模拟OA程序
 * @author: lanwenquan
 * @date: 2021-03-07 11:44
 */
public class OADemo {
    public static void main(String[] args) throws Exception {
        Double salary = 2000.0;
        Double money = 0.0;
//        test1(salary, money);
//        test2(salary, money);
//        test3(salary, money);
//        test4(salary, money);
        test5(salary, money);
    }

    private static void test1(Double salary, Double money) throws MalformedURLException {
         URL jarPath = new URL("file:D:\\developToolsAndSoft\\jarpackage\\salary.jar");
        // 使用了 URLClassLoader 来加载自定义的一个类
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[] {jarPath});
    }

    private static void test2(Double salary, Double money) throws MalformedURLException {
        //(2) 使用自定义的类加载器,编译文件路径
        SalaryClassLoader loader = new SalaryClassLoader("D:\\project\\classloaderdemo\\");
    }

    private static void test3(Double salary, Double money) throws Exception {
        //(3) 使用jar包中的文件进行加载
        SalaryJarLoader loader = new SalaryJarLoader("D:\\developToolsAndSoft\\jarpackage\\salary.jar");
        while (true) {
            money = calculateSalary3(salary, loader);
            System.out.println("实际到手工资" + money);
            Thread.sleep(1000);
        }
    }

    private static void test4(Double salary, Double money) throws Exception {
        while (true) {
            money = calculateSalary4(salary);
            System.out.println("实际到手工资" + money);
            Thread.sleep(5000);
        }
    }

    private static void test5(Double salary, Double money) throws Exception {
        SalaryJarLoader loader = new SalaryJarLoader("D:\\developToolsAndSoft\\jarpackage\\salary2\\salary.jar");
        System.out.println(loader.getParent());
        final Class<?> class1 = loader.loadClass("com.lanwq.jvm.classload.classloaderdemo.SalaryCaler");
        if(class1 != null) {
            Object o1 = class1.newInstance();
            Double salary1 = (Double)class1.getMethod("calSalary", Double.class).invoke(o1, salary);
            System.out.println("基本的工资：" + salary1);
        }

        SalaryJarLoader loader2 = new SalaryJarLoader("D:\\developToolsAndSoft\\jarpackage\\salary.jar");
        final Class<?> class2 = loader2.loadClass("com.lanwq.jvm.classload.classloaderdemo.SalaryCaler");
        if(class2 != null) {
            Object o2 = class2.newInstance();
            Double salary2 = (Double)class2.getMethod("calSalary", Double.class).invoke(o2, salary);
            System.out.println("实际到手的工资：" + salary2);
        }
    }

    private static Double calculateSalary(Double salary, ClassLoader urlClassLoader) throws Exception {
/*        SalaryCaler salaryCaler = new SalaryCaler();
        return salaryCaler.calSalary(salary);*/
        // 取代上面的代码是： calSalary
        final Class<?> aClass = urlClassLoader.loadClass("com.lanwq.jvm.classload.classloaderdemo.SalaryCaler");
        final Object object = aClass.newInstance();
        Double result = (Double) aClass.getMethod("calSalary", Double.class).invoke(object, salary);
        return result;
    }

    private static Double calculateSalary2(Double salary, ClassLoader classLoader) throws Exception {
        final Class<?> aClass = classLoader.loadClass("com.lanwq.jvm.classload.classloaderdemo.SalaryCaler");
        final Object object = aClass.newInstance();
        Double result = (Double) aClass.getMethod("calSalary", Double.class).invoke(object, salary);
        return result;
    }

    private static Double calculateSalary3(Double salary, ClassLoader classLoader) throws Exception {
        final Class<?> aClass = classLoader.loadClass("com.lanwq.jvm.classload.classloaderdemo.SalaryCaler");
        final Object object = aClass.newInstance();
        Double result = (Double) aClass.getMethod("calSalary", Double.class).invoke(object, salary);
        return result;
    }

    /**
     *  实现热加载,每次都创建一个classloader. 在另外一个项目中修改这个jar包中这个类中计算工资的方式。
     * @param salary
     * @return
     * @throws Exception
     */
    private static Double calculateSalary4(Double salary) throws Exception {
        SalaryJarLoader loader = new SalaryJarLoader("D:\\developToolsAndSoft\\jarpackage\\salary.jar");

        final Class<?> aClass = loader.loadClass("com.lanwq.jvm.classload.classloaderdemo.SalaryCaler");
        final Object object = aClass.newInstance();
        Double result = (Double) aClass.getMethod("calSalary", Double.class).invoke(object, salary);
        return result;
    }

    /**
     *  实现热加载,同类多版本加载
     * @param salary
     * @return
     * @throws Exception
     */
    private static Double calculateSalary5(Double salary, ClassLoader classLoader) throws Exception {
        final Class<?> aClass = classLoader.loadClass("com.lanwq.jvm.classload.classloaderdemo.SalaryCaler");
        final Object object = aClass.newInstance();
        Double result = (Double) aClass.getMethod("calSalary", Double.class).invoke(object, salary);
        return result;
    }
}