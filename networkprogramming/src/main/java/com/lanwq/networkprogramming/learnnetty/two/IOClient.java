package com.lanwq.networkprogramming.learnnetty.two;

import java.net.Socket;
import java.util.Date;

/**
 * @author Vin lan
 * @className IOClient
 * @description
 * @createTime 2022-12-02  16:58
 **/
public class IOClient {
    public static void main(String[] args) {
        new Thread(()->{
            try {
                Socket socket = new Socket("127.0.0.1", 8000);
                while (true) {
                    socket.getOutputStream().write((new Date() + ": hello world").getBytes());
                    Thread.sleep(2000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
