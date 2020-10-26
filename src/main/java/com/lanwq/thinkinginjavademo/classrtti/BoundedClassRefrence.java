package com.lanwq.thinkinginjavademo.classrtti;

/**
 * @program: javaDemo->BoundedClassRefrence
 * @description: 绑定Class的引用
 * @author: lanwenquan
 * @date: 2020-05-12 21:28
 */
public class BoundedClassRefrence {
    public static void main(String[] args) throws Exception {
        Class<? extends Number> bounded = int.class;
        bounded = double.class;
        bounded = Number.class;
        bounded = float.class;
        // 以上都是可以的，因为 ? 通配符代表 任何事物

        Class<FancyToy> fancyToyClass = FancyToy.class;
        // 产生一个具体的类型
        FancyToy fancyToy = fancyToyClass.newInstance();
        // 某个类，是FancyToy的超类
        Class<? super FancyToy> up = fancyToyClass.getSuperclass();
        System.out.println(up.getName());

    }
}
