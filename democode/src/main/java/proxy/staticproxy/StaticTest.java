package proxy.staticproxy;

/**
 * @ClassName StaticTest
 * @Description TODO
 * @Author lanwenquan
 * @Date 2020/4/8 18:58
 */
public class StaticTest {
    public static void main(String[] args) {
        Agent agent = new Agent(new Actor("actor say"), "agent before", "agent after");
        agent.speak();
    }
}