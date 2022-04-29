package com.lanwq.fcm;

import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

/**
 * @author Vin lan
 * @className FcmAppTest
 * @description
 * @createTime 2022-04-21  15:53
 **/
public class FcmAppTest {


    public static void main(String[] args) {
        System.setProperty("proxyHost", "127.0.0.1");
        System.setProperty("proxyPort", "7890");

        String registrationToken = "c93-txd0Qmia3zbO7cU71M:APA91bEjMFBxkN6v2tVcDK0TGRFt-K5J4Ox2ziRP2CuVZ3ReW27Reed0XtJ37LaynV-rFYK92H8lmdimtV2c86KR3XqvBeslnh9nKjBeuH1qKH3Yx67ipcrG67epVSOm80yVNJc5hwGd";
        FirebaseApp app = FcmSenderUtil.initSdk("hs", "serviceAccountKey.json");

        Message.Builder builder = Message.builder();
        builder.setToken(registrationToken);
        Message message = builder
                .setNotification(Notification.builder()
                .setTitle("this is a title")
                .setBody("this is a body").build())
                .setAndroidConfig(AndroidConfig.builder().setNotification(
                        AndroidNotification.builder()
                                .setColor("#55BEB7")
                                .setBody("这是安卓设置的消息")
                                .setTitle("安卓消息头")
//                                    .setIcon("https://www.server.co/images/favicon.png")
                                .build())
//                        .setRestrictedPackageName("com.etw.healthsmart")
                        .build())
                .putData("name", "lan")
                .putData("message", "this is a message to be showing,测试 setRestrictedPackageName 是否必须？")
                .build();
        try {
            System.out.println("sent message");
            // 运行成功后，每个发送方法都将返回一个消息 ID。Firebase Admin SDK 会返回 projects/{project_id}/messages/{message_id} 格式的消息 ID 字符串
            // projects/my-fcm-message-test/messages/0:1650608698666546%40b3ef3a40b3ef3a
//            String response = FirebaseMessaging.getInstance(app).send(message);
//            System.out.println("Successfully sent message: " + response);

            FcmSenderUtil.sendSingleMsgToSingleDevice("hs", "discount", "this is topic title", "this is a topic body", null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
