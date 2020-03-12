package com.lanwq.thinking_in_java_demo.demo.sevenreusingclass;

/**
 * @program: ThinkingInJavaDemo -->Chess
 * @Description : <blue>父子类初始化，带参数</blue>  当没有默认的基类构造器的时候必须要显示的使用super关键字调用基类构造器
 * @author: lanwenquan
 * @creatTime: 2019-11-21 21 : 15
 **/

import static net.mindview.util.Print.*;
import static net.mindview.util.Print.print;

class Game {
    Game(int i) {
        print("Game constructor");
    }

    Game() {
        print("no args constructor");
    }
}

class BoardGame extends Game {
    BoardGame(int i) {
//        super(i);
        print("BoardGame constructor");
    }
}

public class Chess extends BoardGame {
    Chess() {
        super(11);  // 调用父类或者基类的构造器要放在导出类（子类）的构造器的第一行
        print("Chess constructor");
    }

    public static void main(String[] args) {
        Chess c = new Chess();
    }
}
