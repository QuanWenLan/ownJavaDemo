package com.lanwq.reuse;

public class Chess extends BoardGame{
    Chess () {
        super(11);
        System.out.println("Chess constructor");
    }

    public static void main(String[] args){
        Chess x = new Chess();
    }
}
class Game {
    Game(int i) {
        System.out.println("Game constructor have parameter" + i);
    }
    Game() {
        System.out.println("Game constructor have not parameter");
    }
}
class BoardGame extends Game{
    BoardGame(int i) {
        super(11);
        System.out.println("BoardGame constructor" + i);
    }

}