package com.lanwq.networkprogramming.learnnetty.login;

import com.lanwq.networkprogramming.learnnetty.protocol.Packet;
import com.lanwq.networkprogramming.learnnetty.protocol.request.LoginRequestPacket;
import com.lanwq.networkprogramming.learnnetty.protocol.PacketCodeC;
import com.lanwq.networkprogramming.learnnetty.protocol.response.LoginResponsePacket;
import com.lanwq.networkprogramming.learnnetty.protocol.response.MessageResponsePacket;
import com.lanwq.networkprogramming.learnnetty.util.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;
import java.util.UUID;

/**
 * @author Vin lan
 * @className ClientLoginHandler
 * @description ChannelInboundHandlerAdapter 是读取数据的逻辑
 * @createTime 2022-12-09  11:04
 **/
public class ClientCommunicationHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() +"：客户端开始登录！");

        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUserName("lan");
        loginRequestPacket.setPassword("pwd");
        // 加密
        ByteBuf encodeByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginRequestPacket);

        ctx.channel().writeAndFlush(encodeByteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf)msg;
        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);
        if (packet instanceof LoginResponsePacket) {
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket)packet;
            if (loginResponsePacket.isSuccess()) {
                System.out.println(new Date() + "：客户端登录成功！");
                 /*
                  客户端登录成功或者失败之后，如何把成功或者失败的标识绑定在客户端的连接上？
                  服务端又如何高效避免客户端重新登录？
                  客户端登录成功之后，给客户端绑定登录成功的标志位
                 */
                LoginUtil.markAsLogin(ctx.channel());
            } else {
                System.out.println(new Date() + "：客户端登录失败！原因：" + loginResponsePacket.getReason());
            }
        } else if (packet instanceof MessageResponsePacket) {
            MessageResponsePacket messageResponsePacket = (MessageResponsePacket) packet;
            System.out.println(new Date() + "：收到服务端的消息：" + messageResponsePacket.getMessage());
        }
    }
}
