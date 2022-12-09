package com.lanwq.networkprogramming.learnnetty.login;

import com.lanwq.networkprogramming.learnnetty.protocol.Packet;
import com.lanwq.networkprogramming.learnnetty.protocol.PacketCodeC;
import com.lanwq.networkprogramming.learnnetty.protocol.request.LoginRequestPacket;
import com.lanwq.networkprogramming.learnnetty.protocol.request.MessageRequestPacket;
import com.lanwq.networkprogramming.learnnetty.protocol.response.LoginResponsePacket;
import com.lanwq.networkprogramming.learnnetty.protocol.response.MessageResponsePacket;
import com.lanwq.networkprogramming.learnnetty.util.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * @author Vin lan
 * @className ServerLoginHandler
 * @description
 * @createTime 2022-12-09  11:06
 **/
public class ServerCommunicationHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf requestByteBuf = (ByteBuf) msg;
        Packet packet = PacketCodeC.INSTANCE.decode(requestByteBuf);

        if (packet instanceof LoginRequestPacket) {
            System.out.println(new Date() + ": 客户端开始登录……");
            // 登录流程
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;
            System.out.println(loginRequestPacket.toString());
            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginResponsePacket.setVersion(packet.getVersion());
            if (valid(loginRequestPacket)) {
                loginResponsePacket.setSuccess(true);
                System.out.println(new Date() + ": 登录成功! ");
            } else {
                loginResponsePacket.setReason("账号密码校验失败");
                loginResponsePacket.setSuccess(false);
                System.out.println(new Date() + ": 登录失败!");
            }
            // 登录响应
            ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);
        } else if (packet instanceof MessageRequestPacket) {
            // 客户端发来消息
            MessageRequestPacket messageRequestPacket = ((MessageRequestPacket) packet);

            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());
            messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");
            ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), messageResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);
        }
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
