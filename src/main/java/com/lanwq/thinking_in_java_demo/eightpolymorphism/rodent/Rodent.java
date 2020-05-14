package com.lanwq.thinking_in_java_demo.eightpolymorphism.rodent;

/**
 * @program: ThinkingInJavaDemo -->Rodent
 * @Description : <blue></blue>
 * @author: lanwenquan
 * @creatTime: 2019-12-01 17 : 31
 * Rodent:啮齿动物，Gerbil: 鼹鼠，Hamster:大颊鼠
 **/

public class Rodent {
    public void bite() {
        System.out.println("rodent.bite() ");
    }

    public static void main(String[] args) {
        Rodent[] rodent = {new Rodent(), new Mouse(), new Gerbil(), new Hamster()};
        for (Rodent r :
                rodent) {
            r.bite();
        }
    }
}

class Mouse extends Rodent {
    public void bite() {
        System.out.println("Mouse.bite() ");
    }
}

class Gerbil extends Rodent {
    public void bite() {
        System.out.println("Gerbil.bite() ");
    }
}

class Hamster extends Rodent {
    public void bite() {
        System.out.println("Hamster.bite() ");
    }
}