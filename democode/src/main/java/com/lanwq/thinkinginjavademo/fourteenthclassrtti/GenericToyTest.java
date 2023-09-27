package com.lanwq.thinkinginjavademo.fourteenthclassrtti;

/**
 * @author Vin lan
 * @className GenericToyTest
 * @description TODO
 * @createTime 2020-09-18  15:46
 **/
public class GenericToyTest {
    public static void main(String[] args) throws Exception {
        Class<FancyToy> fancyToyClass = FancyToy.class;
        // produces exact type
        FancyToy fancyToy = fancyToyClass.newInstance();
        // 某个类，是FancyToy的超类
        Class<? super FancyToy> up = fancyToyClass.getSuperclass();
        System.out.println(up.getName());
        // this won't compile
//        Class<Toy> up2 = fancyToyClass.getSuperclass();
        // only produce Object
        Object object = up.newInstance();
    }
    /**
     * 如果你手头的是超类，那编译器将只允许你声明超类引用时“某个类，它是FancyToy超类”，就像是在表达式 Class<? super FancyToy>
     * 中所看到的，而不会接受Class<Toy>这样的声明。这看上去有些怪，因为  getSuperclass()方法返回的是基类（不是接口），
     * 并且编译器在编译期间就知道它是什么类型了。
     */
}
