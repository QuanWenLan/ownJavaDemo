package com.lanwq.networkprogramming.learnnetty.chat.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import com.lanwq.networkprogramming.learnnetty.chat.protocol.Packet;
import com.lanwq.networkprogramming.learnnetty.chat.protocol.PacketCodeC;

/**
 * @author lanwq
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf out) {
        /*
         * PacketEncoder继承自MessageToByteEncoder，泛型参数Packet表示这个类的作用是实现Packet类型对象到二进制数据的转换。
         * 第二个参数是Java对象，而第三个参数是ByteBuf对象，我们要做的事情就是，把Java对象的字段写到ByteBuf对象，而不再需要自行去分配ByteBuf对象。
         */
        PacketCodeC.INSTANCE.encode(out, packet);
    }
}
