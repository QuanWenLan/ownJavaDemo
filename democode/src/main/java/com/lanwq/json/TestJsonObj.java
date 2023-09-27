package com.lanwq.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TestJsonObj
 * @Description TODO
 * @Author Administrator
 * @Date 2019/12/2 14:43
 */
public class TestJsonObj {
    public static void main(String[] args) {
        /*String str = "{\"success\":\"true\",\"returnAddress\":\"123\"}";
        JSONObject jsonObject = JSON.parseObject(str);
        System.out.println(jsonObject.toString());
        System.out.println(jsonObject.getString("success"));
        System.out.println(str);*/

        /*String str2 = "{\"success\":\"true\",\"returnAddress\":\"123\", \"data\":{\"id\": 123}}";
        JSONObject jsonObject2 = JSON.parseObject(str2);
        System.out.println(jsonObject2.getString("data"));
        System.out.println(JSON.parseObject(jsonObject2.getString("data")).getString("id"));*/

        String str3 = "{\"success\":\"true\",\"returnAddress\":\"123\", \"data\":[{\"id\": 123},{\"id\": 223}]}";
        /*JSONObject jsonObject3 = JSON.parseObject(str3);
        System.out.println(jsonObject3);
        System.out.println(jsonObject3.toJSONString());
        System.out.println("data " + jsonObject3.getString("data"));
        JSONArray data = JSON.parseArray(jsonObject3.getString("data"));
        for (Object d: data) {
            System.out.println(d);
            System.out.println(JSON.parseObject(d.toString()).getString("id"));
            System.out.println(JSON.parseObject(String.valueOf(d)).getString("id"));
        }
//        System.out.println(JSON.parseObject(jsonObject3.getString("data")).getString("id"));
        String faceIds = getFaceIds(jsonObject3);
        System.out.println("faceIds: " + faceIds);*/

        /*Map map = new HashMap();
        System.out.println(map);
        gg();*/

        /*String str3 = "{\"success\":\"true\",\"returnAddress\":\"123\", " +
                "\"data\":[{\"id\": 123},{\"id\": 223},{\"id\": 223, \"name\": 22333}]}";
        Map<String, ArrayList<JSONObject>> stringArrayListMap = groupBYCamera(str3);
        for (String a : stringArrayListMap.keySet()) {
            System.out.println(a + " : " + stringArrayListMap.get(a) + "size: " + stringArrayListMap.get(a).size());
        }*/
        System.out.println(isJSON2(str3));

        String s = "http://65.65.100.195/queryDygabzy?zylx=fzxy";
        s.substring(s.indexOf("?"));
    }

    private static Map<String, ArrayList<JSONObject>> groupBYCamera(String facesEntity) {
        System.out.println(facesEntity);
        Map<String, ArrayList<JSONObject>> map = new HashMap<>();
        JSONObject jsonObject = JSON.parseObject(facesEntity);
        String data = jsonObject.getString("data");
        JSONArray dataArray = JSON.parseArray(data);

        for (Object d : dataArray) {
            JSONObject value = JSON.parseObject(String.valueOf(d));
            String camera = value.getString("id");
            if (map.get(camera) == null) {
                map.put(camera, new ArrayList<JSONObject>());
            }
            ArrayList<JSONObject> jsonObjects = map.get(camera);

            if (map.containsKey(camera)) {
                jsonObjects.add(value);
            }
        }
        return map;
    }

    private static String getFaceIds(Object o) {
        JSONObject facesImageJson = (JSONObject) o;
        String data = facesImageJson.getString("data");
        JSONArray dataArray = JSON.parseArray(data);
        StringBuilder sb = new StringBuilder();
        for (int i = 0, len = dataArray.size(); i < len; i++) {
            Object obj = dataArray.get(i);
            if (i == len - 1) {
                sb.append(JSON.parseObject(String.valueOf(obj)).get("id"));
            } else {
                sb.append(JSON.parseObject(String.valueOf(obj)).get("id"));
                sb.append(",");
            }
        }
        return String.valueOf(sb);
    }

    public static void gg() {
        String faceIds = "123456", startTime = "2018-10-18 00:00:00", endTime = "2019-11-16 23:59:59";
        float similarityScore = 0.92f;
        String json = "{\"faceId\": \"" + faceIds + "\", \"scoreThreshold\": " + similarityScore + ", " +
                "\"displayType\": \"score\", \"genderConfidence\": 3, \"raceConfidence\": 3, " +
                "\"hatConfidence\": 3, \"glassesConfidence\": 3, \"starttime\": \"" + startTime + "\", " +
                "\"endtime\": \"" + endTime + "\"}";
        System.out.println(json);
        System.out.println(JSONObject.parseObject(json));
    }

    public static boolean isJSON2(String str) {
        boolean result = false;
        try {
            Object obj = JSON.parse(str);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}