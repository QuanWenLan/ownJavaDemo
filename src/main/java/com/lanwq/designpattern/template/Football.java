package com.lanwq.designpattern.template;

/**
 * @ClassName Football
 * @Description TODO
 * @Author lanwenquan
 * @Date 2020/1/13 14:51
 */
public class Football extends Game {
    @Override
    void initialize() {
        System.out.println("Football Game Initialized! Start playing.");
    }

    @Override
    void startPlay() {
        System.out.println("Football Game Started. Enjoy the game!");
    }

    @Override
    void endPlay() {
        System.out.println("Football Game Finished!");
    }
}