package com.lanwq.designpattern.template;

/**
 * @ClassName Game
 * @Description TODO 模板方法模式类
 * @Author lanwenquan
 * @Date 2020/1/13 14:37
 */
public abstract class Game {
    abstract void initialize();
    abstract void startPlay();
    abstract void endPlay();

    // 模板 ,顶级的逻辑结构
    public final void play() {
        // 游戏初始化
        initialize();
        // 开始游戏
        startPlay();
        // 结束游戏
        endPlay();
    }
}