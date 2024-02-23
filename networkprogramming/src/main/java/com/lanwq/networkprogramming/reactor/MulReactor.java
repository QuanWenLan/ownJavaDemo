package com.lanwq.networkprogramming.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Vin lan
 * @className MulReactor
 * @description 对于多个CPU的机器，为充分利用系统资源，将Reactor拆分为两部分
 * @createTime 2023-03-28  10:34
 * https://www.cnblogs.com/crazymakercircle/p/9833847.html
 * 这里是服务端
 **/
public class MulReactor implements Runnable {
    /**
     *  subReactors集合, 一个selector代表一个subReactor
     */
    private Selector[] selectors = new Selector[2];
    private int next = 0;
    private final ServerSocketChannel serverSocket;

    MulReactor(int port) throws IOException {
        //Reactor初始化
        selectors[0] = Selector.open();
        selectors[1] = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(port));
        //非阻塞
        serverSocket.configureBlocking(false);
        //分步处理,第一步,接收accept事件
        SelectionKey sk = serverSocket.register(selectors[0], SelectionKey.OP_ACCEPT);
        //attach callback object, Acceptor
        sk.attach(new Acceptor());
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                for (int i = 0; i < 2; i++) {
                    selectors[i].select();
                    Set selected = selectors[i].selectedKeys();
                    Iterator it = selected.iterator();
                    while (it.hasNext()) {
                        //Reactor负责dispatch收到的事件
                        dispatch((SelectionKey) (it.next()));
                    }
                    selected.clear();
                }

            }
        } catch (IOException ex) { /* ... */ }
    }

    private void dispatch(SelectionKey k) {
        Runnable r = (Runnable) (k.attachment());
        //调用之前注册的callback对象
        if (r != null) {
            r.run();
        }
    }

    class Acceptor { // ...
        public synchronized void run() throws IOException {
            SocketChannel connection =
                    serverSocket.accept(); //主selector负责accept
            if (connection != null) {
                new SingleThreadHandler(selectors[next], connection); //选个subReactor去负责接收到的connection
            }
            if (++next == selectors.length) {
                next = 0;
            }
        }
    }
}
