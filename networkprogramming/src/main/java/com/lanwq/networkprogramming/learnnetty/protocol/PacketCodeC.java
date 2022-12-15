package com.lanwq.networkprogramming.learnnetty.protocol;

import com.lanwq.networkprogramming.learnnetty.protocol.request.LoginRequestPacket;
import com.lanwq.networkprogramming.learnnetty.protocol.request.MessageRequestPacket;
import com.lanwq.networkprogramming.learnnetty.protocol.response.LoginResponsePacket;
import com.lanwq.networkprogramming.learnnetty.protocol.response.MessageResponsePacket;
import com.lanwq.networkprogramming.learnnetty.serialize.impl.JSONSerializer;
import com.lanwq.networkprogramming.learnnetty.serialize.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

import static com.lanwq.networkprogramming.learnnetty.protocol.command.Command.LOGIN_REQUEST;
import static com.lanwq.networkprogramming.learnnetty.protocol.command.Command.LOGIN_RESPONSE;
import static com.lanwq.networkprogramming.learnnetty.protocol.command.Command.MESSAGE_REQUEST;
import static com.lanwq.networkprogramming.learnnetty.protocol.command.Command.MESSAGE_RESPONSE;

/**
 * @author Vin lan
 * @className PacketCodeC
 * @description
 * @createTime 2022-12-08  17:28
 **/
public class PacketCodeC {
    public static final PacketCodeC INSTANCE = new PacketCodeC();
    public static final int MAGIC_NUMBER = 0X12345678;
    private final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private final Map<Byte, Serializer> serializerMap;

    private PacketCodeC() {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMap.put(MESSAGE_RESPONSE, MessageResponsePacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializerAlgorithm(), serializer);
    }

    public ByteBuf encode(ByteBufAllocator byteBufAllocator, Packet packet) {
        // 1 创建 ByteBuf 对象
        ByteBuf byteBuf = byteBufAllocator.ioBuffer();
        // 2 序列化对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);
        // 3 实际编码
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.version);
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
//        System.out.println("byteBuf 的可读字节长度是：" + byteBuf.readableBytes());
        return byteBuf;
    }

    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        // 1 创建 ByteBuf 对象
        // 2 序列化对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);
        // 3 实际编码
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.version);
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf) {
        // 跳过魔数
        byteBuf.skipBytes(4);
        // 跳过版本号
        byteBuf.skipBytes(1);
        // 序列化算法标识
        byte serializeAlgorithm = byteBuf.readByte();
        // 指令
        byte command = byteBuf.readByte();
        // 数据包长度
        int length = byteBuf.readInt();
        // 数据
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }

        return null;
    }

    private Serializer getSerializer(byte serializeAlgorithm) {

        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command) {

        return packetTypeMap.get(command);
    }
}
