package com.lanwq.thinkinginjavademo.sevenreusingclass;

/**
 * @program: ThinkingInJavaDemo -->SprinklerSystem
 * @Description : <blue>复用类，组合语法</blue>
 * @author: lanwenquan
 * @creatTime: 2019-11-20 21 : 51
 **/

class WaterSource {
    private String s;
    WaterSource() {
        System.out.println("waterSource()");
        s = "constructed";
    }

    @Override
    public String toString() {
        return s;
    }
}

public class SprinklerSystem {
    private String val1, val2, val3, val4;
    private WaterSource source = new WaterSource();
    private int i;
    private float f;

    @Override
    public String toString() {
        return "SprinklerSystem{" +
                "val1='" + val1 + '\'' +
                ", val2='" + val2 + '\'' +
                ", val3='" + val3 + '\'' +
                ", val4='" + val4 + '\'' +
                ", source=" + source +
                ", i=" + i +
                ", f=" + f +
                '}';
    }

    public static void main(String[] args) {
        SprinklerSystem sprinklers = new SprinklerSystem();
        System.out.println(sprinklers);
    }
}
