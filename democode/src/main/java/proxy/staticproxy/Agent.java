package proxy.staticproxy;

/**
 * @ClassName Agent
 * @Description TODO
 * @Author lanwenquan
 * @Date 2020/4/8 18:55
 */
public class Agent implements Person {
    private Actor actor;

    private String beforeWord;
    private String afterWord;

    public Agent(Actor actor, String beforeWord, String afterWord) {
        this.actor = actor;
        this.beforeWord = beforeWord;
        this.afterWord = afterWord;
    }

    @Override
    public void speak() {
        //before speak
        System.out.println("Before actor speak, Agent say: " + beforeWord);
        //real speak
        this.actor.speak();
        //after speak
        System.out.println("After actor speak, Agent say: " + afterWord);
    }
}