package com.lanwq.networkprogramming.learnnetty.chat.codec;

import java.util.List;

import com.lanwq.networkprogramming.learnnetty.chat.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * 通常情况下，无论在客户端还是在服务端，当我们收到数据后，首先要做的就是把二进制数据转换到Java对象
 *
 * @author lanwq
 */
public class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List out) {
        /*
        这里的in传递进来的时候就已经是ByteBuf类型，所以不再需要强转。第三个参数是List类型，我们通过向这个List里面添加解码后的结果对象，就可以自动实现结果向下一个Handler传递，这样就实现了解码的逻辑Handler。
        */
        out.add(PacketCodeC.INSTANCE.decode(in));
    }
}
