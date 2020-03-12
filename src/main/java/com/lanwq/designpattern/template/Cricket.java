package com.lanwq.designpattern.template;

/**
 * @ClassName Cricket（板球）
 * @Description TODO 模板方法模式子类
 * @Author lanwenquan
 * @Date 2020/1/13 14:45
 */
public class Cricket extends Game {
    @Override
    void initialize() {
        System.out.println("Cricket Game Initialized! Start playing.");
    }

    @Override
    void startPlay() {
        System.out.println("Cricket Game Started. Enjoy the game!");
    }

    @Override
    void endPlay() {
        System.out.println("Cricket Game Finished!");
    }
}