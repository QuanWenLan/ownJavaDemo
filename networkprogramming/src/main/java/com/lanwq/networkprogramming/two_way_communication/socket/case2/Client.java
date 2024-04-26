package com.lanwq.networkprogramming.two_way_communication.socket.case2;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345); // 连接服务器

            // 获取客户端输入流和输出流
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));

            // 发送数据给服务器
            String message = "Hello, Server!";
            writer.write(message + "\n");
            writer.flush();
            System.out.println("Sent to server: " + message);

            // 读取服务器响应
            String serverResponse = reader.readLine();
            System.out.println("Server response: " + serverResponse);

            // 关闭连接
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
