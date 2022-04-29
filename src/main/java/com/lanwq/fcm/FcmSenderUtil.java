package com.lanwq.fcm;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Vin lan
 * @className FcmSender
 * @description
 * @createTime 2022-04-22  10:46
 **/
public class FcmSenderUtil {
    private static Logger log = LoggerFactory.getLogger(FcmSenderUtil.class);

    private static Map<String, FirebaseApp> firebaseAppMap = new ConcurrentHashMap<>();

    private static AndroidConfig.Builder androidConfigBuilder = AndroidConfig.builder();

    private static AndroidNotification.Builder androidNotificationBuilder = AndroidNotification.builder();

    private static ApnsConfig.Builder apnsBuilder = ApnsConfig.builder();

    public static final int MAX_DEVICE = 500;

    /**
     * @param appName name
     * @return Whether there is already an instance
     */
    public static boolean isInit(String appName) {
        return firebaseAppMap.get(appName) != null;
    }

    /**
     * init firebase application
     *
     * @param appName     application name
     * @param jsonPath    json config path, resource files relative to the current project {push-util.properties hs.fcm.jsonPath}
     * @param databaseUrl url
     */
    public static FirebaseApp initSdk(String appName, String jsonPath, String databaseUrl) {
        try {
            InputStream inputStream = FcmSenderUtil.class.getResourceAsStream("/" + jsonPath);
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(inputStream))
                    .setDatabaseUrl(databaseUrl)
                    .build();
            FirebaseApp firebaseApp = FirebaseApp.initializeApp(options);
            if (isInit(appName)) {
                return firebaseAppMap.get(appName);
            }
            if(StringUtils.isNotBlank(appName)) {
                firebaseAppMap.put(appName, firebaseApp);
            } else {
                firebaseAppMap.put(FirebaseApp.DEFAULT_APP_NAME, firebaseApp);
            }
            firebaseAppMap.put(appName, firebaseApp);
            return firebaseApp;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("file not found, msg: " + e.getMessage() + "");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("file input stream error, msg: " + e.getMessage() + "");
        }
        return null;
    }

    /**
     * init firebase application
     *
     * @param appName  application name
     * @param jsonPath json path, resource files relative to the current project {push-util.properties hs.fcm.jsonPath}
     */
    public static FirebaseApp initSdk(String appName, String jsonPath) {
        try {
            InputStream inputStream = FcmSenderUtil.class.getResourceAsStream("/" + jsonPath);
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(inputStream))
                    .build();
            FirebaseApp firebaseApp = FirebaseApp.initializeApp(options);
            if (isInit(appName)) {
                return firebaseAppMap.get(appName);
            }
            if(StringUtils.isNotBlank(appName)) {
                firebaseAppMap.put(appName, firebaseApp);
            } else {
                firebaseAppMap.put(FirebaseApp.DEFAULT_APP_NAME, firebaseApp);
            }
            return firebaseApp;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("Fail to finding out file, msg: " + e.getMessage() + "");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Getting File input stream error, msg: " + e.getMessage() + "");
        }
        return null;
    }

    /**
     * init firebase application
     *
     * @param inputStream  inputStream
     */
    public static FirebaseApp initSdk(String appName,InputStream inputStream) {
        try {
            if(inputStream == null) {
                throw new IOException("input stream must not be null");
            }
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(inputStream))
                    .build();
            FirebaseApp firebaseApp = FirebaseApp.initializeApp(options);
            if (isInit(firebaseApp.getName())) {
                return firebaseAppMap.get(firebaseApp.getName());
            }

            if(StringUtils.isNotBlank(appName)) {
                firebaseAppMap.put(appName, firebaseApp);
            } else {
                firebaseAppMap.put(FirebaseApp.DEFAULT_APP_NAME, firebaseApp);
            }
            return firebaseApp;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("Fail to finding out file, msg: " + e.getMessage() + "");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Getting File input stream error, msg: " + e.getMessage() + "");
        }
        return null;
    }


    /**
     * @param appName      app name
     * @param deviceToken  token generated by multiple device connecting to the FCM server，max 500
     * @param title        title
     * @param body         body
     * @param data         data msg body
     * @return if success,return message_id
     */
    public static String sendSingleMsgToSingleDevice(String appName, String deviceToken, String title, String body, Map<String, String> data) {

        String response = null;
        FirebaseApp firebaseApp = firebaseAppMap.get(appName);
        if (firebaseApp == null) {
            if (firebaseAppMap.get(FirebaseApp.DEFAULT_APP_NAME) != null) {
                firebaseApp = firebaseAppMap.get(FirebaseApp.DEFAULT_APP_NAME);
            } else {
                log.debug("firebase is not init,please init firebase first according to the appName");
                return null;
            }
        }

        try {

            androidNotificationBuilder.setTitle(title).setBody(body);
            AndroidNotification androidNotification = androidNotificationBuilder.build();
            androidConfigBuilder.setNotification(androidNotification);
            AndroidConfig androidConfig = androidConfigBuilder.build();

            Message.Builder builder = Message.builder();
            builder.setToken(deviceToken).setNotification(
                    Notification.builder()
                            .setTitle(title)
                            .setBody(body)
                            .build())
                    .setAndroidConfig(androidConfig);

            putDataIntoMsg(data, builder);
            Message message = builder.build();

            response = FirebaseMessaging.getInstance(firebaseApp).send(message);
            log.info("Successfully sent message: " + response);
        } catch (FirebaseMessagingException e) {
            log.error("Fail to send message to fcm ["
                    + ToStringBuilder.reflectionToString(deviceToken, ToStringBuilder.getDefaultStyle())
                    + "] on fcm, " + e.getMessage());
            
        }
        return response;
    }

    /**
     * push a single Android message by token
     *
     * @param appName      app name
     * @param deviceToken  token generated by multiple device connecting to the FCM server，max 500
     * @param title        title
     * @param body         body
     * @param data         data msg body
     * @param androidConfig An {@link AndroidConfig} instance.
     * @return  if success,return message_id
     */
    public static String sendSingleMsgToSingleDevice(String appName, String deviceToken, String title, String body,
                                                   AndroidConfig androidConfig, Map<String, String> data) {
        FirebaseApp firebaseApp = firebaseAppMap.get(appName);
        if (firebaseApp == null) {
            if (firebaseAppMap.get(FirebaseApp.DEFAULT_APP_NAME) != null) {
                firebaseApp = firebaseAppMap.get(FirebaseApp.DEFAULT_APP_NAME);
            } else {
                log.debug("firebase is not init,please init firebase first according to the appName");
                return null;
            }
        }

        try {
            Message.Builder builder = Message.builder();
            builder.setToken(deviceToken).setNotification(
                    Notification.builder()
                            .setTitle(title)
                            .setBody(body)
                            .build())
                    .setAndroidConfig(androidConfig)
                    .build();
            putDataIntoMsg(data, builder);
            Message message = builder.build();

            String response = FirebaseMessaging.getInstance(firebaseApp).send(message);
            log.info("Successfully sent android message: " + response);
            return response;
        } catch (FirebaseMessagingException e) {
            log.error("Fail to send message to fcm ["
                    + ToStringBuilder.reflectionToString(deviceToken, ToStringBuilder.getDefaultStyle())
                    + "] on fcm, " + e.getMessage());
            
        }
        return  null;
    }

    /**
     * @param appName      app name
     * @param deviceTokens token generated by multiple device connecting to the FCM server，max 500
     * @param title        title
     * @param body         body
     * @param data         data msg body
     * @return if success,return list <success message id>
     */
    public static List<String> sendSingleMsgToMultipleDevice(String appName, List<String> deviceTokens, String title,
                                                             String body, Map<String, String> data) {
        FirebaseApp firebaseApp = firebaseAppMap.get(appName);
        if (firebaseApp == null) {
            if (firebaseAppMap.get(FirebaseApp.DEFAULT_APP_NAME) != null) {
                firebaseApp = firebaseAppMap.get(FirebaseApp.DEFAULT_APP_NAME);
            } else {
                log.debug("firebase is not init,please init firebase first according to the appName");
                return Collections.emptyList();
            }
        }

        try {
            androidNotificationBuilder.setTitle(title).setBody(body);
            AndroidNotification androidNotification = androidNotificationBuilder.build();
            androidConfigBuilder.setNotification(androidNotification);
            AndroidConfig androidConfig = androidConfigBuilder.build();

            MulticastMessage.Builder builder = MulticastMessage.builder();
            builder.addAllTokens(deviceTokens).setNotification(
                    Notification.builder()
                            .setTitle(title)
                            .setBody(body)
                            .build())
                    .setAndroidConfig(androidConfig)
                    .build();
            if (data != null && !data.isEmpty()) {
                for (String key : data.keySet()) {
                    builder.putData(key, data.get(key));
                }
            }
            MulticastMessage message = builder.build();
            BatchResponse batchResponse = FirebaseMessaging.getInstance(firebaseApp).sendMulticast(message);
            if (batchResponse.getFailureCount() > 0) {
                List<SendResponse> responses = batchResponse.getResponses();
                List<String> failedTokens = new ArrayList<>();
                for (int i = 0; i < responses.size(); i++) {
                    if (!responses.get(i).isSuccessful()) {
                        // The order of responses corresponds to the order of the registration tokens.
                        failedTokens.add(deviceTokens.get(i));
                    }
                }
                log.debug("List of tokens that caused failures: " + failedTokens);
            }

            log.info("Successfully sent message: " + batchResponse);
            List<String> msgRes = new ArrayList<>(batchResponse.getSuccessCount());
            batchResponse.getResponses().forEach(r -> msgRes.add(r.getMessageId()));
            return msgRes;
        } catch (FirebaseMessagingException e) {
            log.error("Fail to send message to fcm ["
                    + ToStringBuilder.reflectionToString(deviceTokens, ToStringBuilder.getDefaultStyle())
                    + "] on fcm, " + e.getMessage());
            
        }
        return Collections.emptyList();
    }

    /**
     *
     * @param appName      app name
     * @param deviceTokens token generated by multiple device connecting to the FCM server，max 500
     * @param title        title
     * @param body         body
     * @param androidConfig An {@link AndroidConfig} instance.
     * @param data         data msg body
     * @return if success,return list <success message id>
     */
    public static List<String> sendSingleMsgToMultipleDevice(String appName, List<String> deviceTokens, String title, String body,
                                                             AndroidConfig androidConfig, Map<String, String> data) {
        FirebaseApp firebaseApp = firebaseAppMap.get(appName);
        if (firebaseApp == null) {
            if (firebaseAppMap.get(FirebaseApp.DEFAULT_APP_NAME) != null) {
                firebaseApp = firebaseAppMap.get(FirebaseApp.DEFAULT_APP_NAME);
            } else {
                log.debug("firebase is not init,please init firebase first according to the appName");
                return Collections.emptyList();
            }
        }

        try {
            MulticastMessage.Builder builder = MulticastMessage.builder();
            builder.addAllTokens(deviceTokens).setNotification(
                    Notification.builder()
                            .setTitle(title)
                            .setBody(body)
                            .build())
                    .setAndroidConfig(androidConfig)
                    .build();
            if (data != null && !data.isEmpty()) {
                for (String key : data.keySet()) {
                    builder.putData(key, data.get(key));
                }
            }
            MulticastMessage message = builder.build();
            BatchResponse batchResponse = FirebaseMessaging.getInstance(firebaseApp).sendMulticast(message);
            if (batchResponse.getFailureCount() > 0) {
                List<SendResponse> responses = batchResponse.getResponses();
                List<String> failedTokens = new ArrayList<>();
                for (int i = 0; i < responses.size(); i++) {
                    if (!responses.get(i).isSuccessful()) {
                        // The order of responses corresponds to the order of the registration tokens.
                        failedTokens.add(deviceTokens.get(i));
                    }
                }
                log.debug("List of tokens that caused failures: " + failedTokens);
            }

            log.info("Successfully sent message: " + batchResponse);
            List<String> msgRes = new ArrayList<>(batchResponse.getSuccessCount());
            batchResponse.getResponses().forEach(r -> msgRes.add(r.getMessageId()));
            return msgRes;
        } catch (FirebaseMessagingException e) {
            log.error("Fail to send message to fcm ["
                    + ToStringBuilder.reflectionToString(deviceTokens, ToStringBuilder.getDefaultStyle())
                    + "] on fcm, " + e.getMessage());
            
        }
        return Collections.emptyList();
    }

    /**
     * Push android message by topic
     *
     * @param appName      app name
     * @param title        title
     * @param body         body
     * @param topic        topic
     * @param data         data msg body
     */
    public static void sendSingleTopicMesToDevice(String appName, String topic, String title, String body, Map<String, String> data) {
        FirebaseApp firebaseApp = firebaseAppMap.get(appName);
        if (firebaseApp == null) {
            if (firebaseAppMap.get(FirebaseApp.DEFAULT_APP_NAME) != null) {
                firebaseApp = firebaseAppMap.get(FirebaseApp.DEFAULT_APP_NAME);
            } else {
                log.debug("firebase is not init,please init firebase first according to the appName");
                return;
            }
        }

        try {
            androidNotificationBuilder.setTitle(title).setBody(body);
            AndroidNotification androidNotification = androidNotificationBuilder.build();
            androidConfigBuilder.setNotification(androidNotification);
            AndroidConfig androidConfig = androidConfigBuilder.build();

            Message.Builder builder = Message.builder();
            builder.setTopic(topic).setNotification(
                    Notification.builder()
                            .setTitle(title)
                            .setBody(body)
                            .build())
                    .setAndroidConfig(androidConfig)
                    .build();
            putDataIntoMsg(data, builder);
            Message message = builder.build();
            String response = FirebaseMessaging.getInstance(firebaseApp).send(message);
            log.info("Successfully sent android topic message: " + response);
        } catch (FirebaseMessagingException e) {
            log.error("Fail to send message to fcm ["
                    + ToStringBuilder.reflectionToString(topic, ToStringBuilder.getDefaultStyle())
                    + "] on fcm, " + e.getMessage());
            
        }
    }

    /**
     * Push android message by topic
     *
     * @param appName app name
     * @param topic   topic name
     * @param title   title
     * @param body    body
     */
    public static void sendSingleTopicMesToDevice(String appName, String topic, String title, String body,
                                                  AndroidConfig androidConfig, Map<String, String> data) {
        FirebaseApp firebaseApp = firebaseAppMap.get(appName);
        if (firebaseApp == null) {
            if (firebaseAppMap.get(FirebaseApp.DEFAULT_APP_NAME) != null) {
                firebaseApp = firebaseAppMap.get(FirebaseApp.DEFAULT_APP_NAME);
            } else {
                log.debug("firebase is not init,please init firebase first according to the appName");
                return;
            }
        }

        try {
            Message.Builder builder = Message.builder();
            builder.setTopic(topic).setNotification(
                    Notification.builder()
                            .setTitle(title)
                            .setBody(body)
                            .build())
                    .setAndroidConfig(androidConfig)
                    .build();
            putDataIntoMsg(data, builder);
            Message message = builder.build();
            String response = FirebaseMessaging.getInstance(firebaseApp).send(message);
            log.info("Successfully sent android topic message: " + response);
        } catch (FirebaseMessagingException e) {
            log.error("Fail to send message to fcm ["
                    + ToStringBuilder.reflectionToString(topic, ToStringBuilder.getDefaultStyle())
                    + "] on fcm, " + e.getMessage());
            
        }
    }

    private static void putDataIntoMsg(Map<String, String> data, Message.Builder builder) {
        if (data != null && !data.isEmpty()) {
            for (String key : data.keySet()) {
                builder.putData(key, data.get(key));
            }
        } else {
            log.info("data id empty");
        }
    }

    /**
     * Multiple devices subscribe to a topic
     *
     * @param appName      app name
     * @param deviceTokens client token,max:1000
     * @param topic        the topic is going to be add
     */
    public static TopicManagementResponse registrationTopic(String appName, List<String> deviceTokens, String topic) {
        FirebaseApp firebaseApp = firebaseAppMap.get(appName);
        if (firebaseApp == null) {
            if (firebaseAppMap.get(FirebaseApp.DEFAULT_APP_NAME) != null) {
                firebaseApp = firebaseAppMap.get(FirebaseApp.DEFAULT_APP_NAME);
            } else {
                log.debug("firebase is not init,please init firebase first according to the appName");
                return null;
            }
        }

        try {
            TopicManagementResponse response = FirebaseMessaging.getInstance(firebaseApp).subscribeToTopic(deviceTokens, topic);
            log.info(response.getSuccessCount() + " tokens were subscribed successfully, fail:" + response.getFailureCount());
            return response;
        } catch (FirebaseMessagingException e) {
            log.error("Fail to subscribe topic ["
                    + ToStringBuilder.reflectionToString(deviceTokens, ToStringBuilder.getDefaultStyle())
                    + "], " + e.getMessage());
            
        }
        return null;
    }

    /**
     * Multiple devices unsubscribe from a topic
     *
     * @param appName      app name
     * @param deviceTokens client token,max:1000
     * @param topic        the topic is going to be cancel
     */
    public static void cancelTopic(String appName, List<String> deviceTokens, String topic) {
        FirebaseApp firebaseApp = firebaseAppMap.get(appName);
        if (firebaseApp == null) {
            if (firebaseAppMap.get(FirebaseApp.DEFAULT_APP_NAME) != null) {
                firebaseApp = firebaseAppMap.get(FirebaseApp.DEFAULT_APP_NAME);
            } else {
                log.debug("firebase is not init,please init firebase first according to the appName");
                return;
            }
        }

        try {
            TopicManagementResponse response = FirebaseMessaging.getInstance(firebaseApp).unsubscribeFromTopic(deviceTokens, topic);
            log.info(response.getSuccessCount() + " tokens were unsubscribed successfully, fail:" + response.getFailureCount());
        } catch (FirebaseMessagingException e) {
            log.error("Fail to unsubscribe topic ["
                    + ToStringBuilder.reflectionToString(deviceTokens, ToStringBuilder.getDefaultStyle())
                    + "], " + e.getMessage());
            
        }
    }




    public static void sendMsgToIos(String appName, String token, String title, String body, String packageName) {

        FirebaseApp firebaseApp = firebaseAppMap.get(appName);
        if (firebaseApp == null) {
            return;
        }

        try {
//            apnsBuilder.putHeader()
            Message message = Message.builder()
                    .setToken(token)
                    .setApnsConfig(apnsBuilder.build())
                    .build();

            String response = FirebaseMessaging.getInstance(firebaseApp).send(message);
            log.info("Successfully sent android message: " + response);
        } catch (FirebaseMessagingException e) {
            log.error("Fail to send message to fcm ["
                    + ToStringBuilder.reflectionToString(token, ToStringBuilder.getDefaultStyle())
                    + "] on fcm, " + e.getMessage());
            
        }
    }
}
