package com.lanwq.thinking_in_java_demo.sevenreusingclass;

/**
 * @program: ThinkingInJavaDemo -->SpaceShipDelegation
 * @Description : <blue>SpaceShip的代理类</blue>
 * @author: lanwenquan
 * @creatTime: 2019-11-21 21 : 49
 **/

public class SpaceShipDelegation {
    private String name;
    private SpaceShipControls controls = new SpaceShipControls();
    public SpaceShipDelegation(String name) {
        this.name = name;
    }
    // delegation methods
    public void up(int velocity){
        controls.up(velocity);
    }
    public void down(int velocity){
        controls.down(velocity);
    }
    public void left(int velocity){
        controls.left(velocity);
    }
    public void right(int velocity){
        controls.right(velocity);
    }
    public void forward(int velocity){
        controls.forward(velocity);
    }
    public void turboBoost(int velocity){
        controls.turboBoost(velocity);
    }

    public static void main(String[] args) {
        SpaceShipDelegation delegation = new SpaceShipDelegation("NSEA Protector");
        delegation.forward(100);
    }
}
