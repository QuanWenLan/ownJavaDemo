package com.lanwq.thinkinginjavademo.fifthinitializationcleanup;

/*
 *   Spiciness: 辛辣
 *   枚举关键字的使用场景：在需要使用 群组 并使用枚举类型集时
 *   初始化枚举
 * */
public enum Spiciness {
    NOT, MILD, MEDIUM, HOT, FLAMING
}


class SimpleEnumUse {
    public static void main(String[] args) {
        Spiciness hotWot = Spiciness.MEDIUM;
        System.out.println(hotWot);
    }
}

class EnumOrder {
    public static void main(String[] args) {
        for (Spiciness en : Spiciness.values())
            System.out.print(en + " ordinal: " + en.ordinal());
    }
}