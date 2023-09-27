package com.lanwq.thinkinginjavademo.thread.bingfa.threadsingnal;

/**
 * @ClassName SignalMissedTest
 * @Description 信号丢失测试
 * @Author lanwenquan
 * @Date 2020/05/28 15:13
 */
public class SignalMissedTest {
    public static void main(String[] args) {
        SignalMissed signalMissed = new SignalMissed();
        new Thread(new Runnable() {

            @Override
            public void run() {

            }
        });
    }
}
