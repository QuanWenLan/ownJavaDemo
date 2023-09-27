package com.lanwq.thinkinginjavademo.sevenreusingclass;

/**
 * @program: ThinkingInJavaDemo -->Detergent
 * @Description : <blue>复用类，继承语法</blue>  使用继承时会自动得到基类中所有的方法和域
 * @author: lanwenquan  Detergent 清洁剂
 * @creatTime: 2019-11-20 22 : 14
 **/

class Cleanser {
    private String s = "Cleanser";

    public void append(String a) {
        s += a;
    }

    public void dilute() {
        append(" dilute() ");
    }

    public void apply() {
        append(" apply() ");
    }

    public void scrub() {
        append(" scrub() ");
    }

    @Override
    public String toString() {
        return "Cleanser{" +
                "s='" + s + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Cleanser cleanser = new Cleanser();
        cleanser.dilute();cleanser.apply();cleanser.scrub();
        System.out.println(cleanser);
    }
}

public class Detergent extends Cleanser{
//    修改基类的方法
    public void scrub() {
        append(" Detergent.scrub()");
        super.scrub();// call base-class version
    }

//    继承类自己的方法
    public void foam() {
        append(" foam() ");
    }

    public static void main(String[] args) {
        Detergent detergent = new Detergent();
        detergent.dilute();
        detergent.apply();
        detergent.scrub();
        detergent.foam();
        System.out.println(detergent);   // 这里继承了基类（父类）的tostring方法
        System.out.println("testing base class");
        Cleanser.main(args);
    }
}

class NewDetergent extends Detergent {
    public void scrub(){
        append(" NewDetergent.scrub() ");
        super.scrub(); // 调用父类或者基类的方法，不用放在第一行
    }

    public static void main(String[] args) {
        NewDetergent newDetergent = new NewDetergent();
        newDetergent.scrub();
        System.out.println(newDetergent);
    }
}
