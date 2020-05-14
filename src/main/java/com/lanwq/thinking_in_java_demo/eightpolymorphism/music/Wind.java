package com.lanwq.thinking_in_java_demo.eightpolymorphism.music;

/**
 * @program: ThinkingInJavaDemo -->Wind
 * @Description : <blue></blue>
 * @author: lanwenquan
 * @creatTime: 2019-12-01 16 : 31
 **/

class Instrument {
    public void play(Note note) {
        System.out.println("Instrument.play()");
    }
}

public class Wind extends Instrument {
//    重写方法
    public void play(Note note) {
        System.out.println("Wind.play() "  + note);
    }
}

