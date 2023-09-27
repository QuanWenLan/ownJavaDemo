package com.lanwq.thinkinginjavademo.eightpolymorphism.music;

/**
 * @program: ThinkingInJavaDemo -->Music
 * @Description : <blue>向上转型和继承</blue>
 * @author: lanwenquan
 * @creatTime: 2019-12-01 16 : 34
 **/

public class Music {
    public static void tune(Instrument instrument) {
        instrument.play(Note.MIDDLE_C);  // 如何知道调用的是哪个类，虽然传递过来的时候是传了一个明确的子类
    }

    public static void main(String[] args) {
        Wind wind = new Wind();
        tune(wind);
    }
}
