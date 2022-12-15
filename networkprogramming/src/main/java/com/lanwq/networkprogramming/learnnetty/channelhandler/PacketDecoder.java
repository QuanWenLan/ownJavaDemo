package com.lanwq.networkprogramming.learnnetty.channelhandler;

import com.lanwq.networkprogramming.learnnetty.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author Vin lan
 * @className PacketDecoder
 * @description 继承这个类，不需要我们手动释放内存，Netty 会自动进行内存的释放
 * @createTime 2022-12-12  10:35
 **/
public class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        /*
        二进制对象转换成Java对象，我们只需要向 out 这个 list 中添加解码后的结果对象，
        就可以自动实现向下一个 Handler 传递，这样就实现了解码的逻辑 Handler。
         */
        out.add(PacketCodeC.INSTANCE.decode(in));
    }
}
