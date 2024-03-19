package proxy.jdkproxy;

/**
 * @ClassName Apple
 * @Description TODO
 * @Author lanwenquan
 * @Date 2020/4/9 13:09
 */
public class Apple implements Fruit {
    @Override
    public int show() {
        System.out.println("apple show, i am a apple <<< invoked ");
        return 1;
    }

    @Override
    public int sale() {
        System.out.println("卖水果了。。。");
        return 0;
    }
}
