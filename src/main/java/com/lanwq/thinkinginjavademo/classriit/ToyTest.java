package com.lanwq.thinkinginjavademo.classriit;

/**
 * @program: javaDemo->ToyTest
 * @description: 测试Class的对象的相关方法
 * @author: lanwenquan
 * @date: 2020-05-12 19:51
 */
public class ToyTest {
    static void printInfo(Class cc) {
        System.out.println("class name:" + cc.getName() + " is interface?[" + cc.isInterface() + "]");
        System.out.println("simple name:" + cc.getSimpleName());
        System.out.println("canonical name:" + cc.getCanonicalName());
    }

    public static void main(String[] args) {
        Class c = null;
        try {
            // 类的全限定名，创建一个对象
            c = Class.forName("com.lanwq.thinkinginjavademo.classriit.FancyToy");
        } catch (ClassNotFoundException e) {
            System.out.println("");
            System.exit(1);
        }
        printInfo(c);
        for (Class face : c.getInterfaces()
        ) {
            printInfo(face);
        }

        Class superclass = c.getSuperclass();
        Object obj = null;
        try {
            // 创建一个对象，要求必须有一个默认的无参构造器
            obj = superclass.newInstance();
        } catch (InstantiationException e) {
            System.out.println("cannot instantiate");
        } catch (IllegalAccessException e) {
            System.out.println("cannot access");
            System.exit(1);
        }
        printInfo(obj.getClass());
    }
}

interface HasBatteries {
}

interface Waterproof {
}

interface Shoots {
}

interface Shoots2 {
}

class ToyDD {

}

class Toy extends ToyDD {
    private Integer a = 10;
    public Toy() {
    }

    public Toy(int i) {
    }

}

class FancyToy extends Toy implements HasBatteries, Waterproof, Shoots, Shoots2 {
    FancyToy() {
        super(1);
    }
}
