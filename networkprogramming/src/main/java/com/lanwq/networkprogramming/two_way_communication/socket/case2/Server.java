package com.lanwq.networkprogramming.two_way_communication.socket.case2;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345); // 创建服务器套接字，监听端口12345
            System.out.println("Server started, waiting for client...");

            Socket clientSocket = serverSocket.accept(); // 等待客户端连接
            System.out.println("Client connected");

            // 获取客户端输入流和输出流
            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));

            // 读取客户端发送的数据
            String clientMessage;
            while ((clientMessage = reader.readLine()) != null) {
                System.out.println("Client: " + clientMessage);
                // 向客户端发送响应数据
                writer.write("Server received: " + clientMessage + "\n");
                writer.flush();
            }

            // 关闭连接
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
