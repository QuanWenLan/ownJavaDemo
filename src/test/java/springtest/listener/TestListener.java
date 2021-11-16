package springtest.listener;

import org.springframework.context.ApplicationListener;

/**
 * @author Vin lan
 * @className TestListener
 * @description 定义监听器
 * @createTime 2021-08-26  11:26
 **/
public class TestListener implements ApplicationListener<TestEvent> {
    @Override
    public void onApplicationEvent(TestEvent event) {
        event.print();
    }
}
