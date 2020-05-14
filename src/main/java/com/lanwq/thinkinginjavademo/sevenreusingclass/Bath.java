package com.lanwq.thinkinginjavademo.sevenreusingclass;

/**
 * @program: ThinkingInJavaDemo -->Bath
 * @Description : <blue>引用对象初始化的几种方法</blue>
 * @author: lanwenquan
 * @creatTime: 2019-11-20 21 : 58
 **/

class Soap {
    private String s;

    Soap() {
        System.out.println("soap()");
        s = "constructed";
    }

    @Override
    public String toString() {
        return "Soap{" +
                "s='" + s + '\'' +
                '}';
    }
}

public class Bath {
    private String s1 = "happy", s2 = "happy", s3, s4;
    private Soap castille;
    private static int i;
    private float toy;

    public Bath() {
        System.out.println("inside bath()");
        s3 = "toy";
        toy = 3.14f;
        castille = new Soap();
    }

    {
        System.out.println("非静态实例代码块");
        i = 47;
    }

    static  {
        System.out.println("静态实例代码块");
        i = 47;
    }

    @Override
    public String toString() {
        if (s4 == null)
            s4 = "toy";
        return "Bath{" +
                "s1='" + s1 + '\'' +
                ", s2='" + s2 + '\'' +
                ", s3='" + s3 + '\'' +
                ", s4='" + s4 + '\'' +
                ", castille=" + castille +
                ", i=" + i +
                ", toy=" + toy +
                '}';
    }

    public static void main(String[] args) {
        Bath b = new Bath();
        System.out.println(b);
    }
}
