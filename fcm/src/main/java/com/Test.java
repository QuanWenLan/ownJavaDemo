package com;

import com.google.android.fcm.server.FcmSenderUtil;
import com.google.android.fcm.server.MessageResponse;

import java.util.*;

/**
 * @author Vin lan
 * @className Test
 * @description
 * @createTime 2022-04-26  11:54
 **/
public class Test {
    public static void main(String[] args) {
        // 这个很重要，需要设置代理连接到 谷歌的消息服务 才行
        /*System.setProperty("proxyHost", "127.0.0.1");
        System.setProperty("proxyPort", "7890");*/

        Map<String, String> data = new HashMap<>(16);
        data.put("clientId", "vin002");
        long start = System.currentTimeMillis();
        System.out.println("开始发送消息:" + start);
        FcmSenderUtil.initSdk("hs", "serviceAccountKey.json");
        /*List<MessageResponse> responses = FcmSenderUtil.sendSingleMsgToMultipleDevice("hs",
                Collections.singletonList("dYFGvUnBTUG5PgCVka7Pac:APA91bHjMF45yGAabA-K7MQaOAkrR6C5mDuYCQXXSmkxhJ6e85Wp-CuzfPGCTwRT_eJXhbDXGkn5BHcxAtA4mbeQR2cFk7u0pS_DuevElJPmGzVfHlywOxaQNxD1UyEvzynRd77t4hcY"),
                "这是标题", "这是消息体", data);
        System.out.println("耗时：" + ((System.currentTimeMillis() - start) / 1000));
        System.out.println(responses);*/
    }
}
