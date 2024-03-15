package proxy.staticproxy;

/**
 * @ClassName Actor
 * @Description TODO
 * @Author lanwenquan
 * @Date 2020/4/8 18:55
 */
public class Actor implements Person {
    private String content;

    public Actor(String content) {
        this.content = content;
    }

    @Override
    public void speak() {
        System.out.println(this.content);
    }
}