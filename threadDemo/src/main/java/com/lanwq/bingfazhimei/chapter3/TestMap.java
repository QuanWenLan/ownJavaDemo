package com.lanwq.bingfazhimei.chapter3;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Vin lan
 * @className TestMap
 * @description
 * @createTime 2021-11-11  17:00
 **/
public class TestMap {
    static ConcurrentHashMap<String, List<String>> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
//        进入直播间 topic1，线程 one
        Thread threadOne = new Thread(() -> {
            ArrayList<String> list = new ArrayList<>();
            list.add("device1");
            list.add("device2");
//            map.put("topic1", list);
            List<String> oldList = map.putIfAbsent("topic1", list);
            if (oldList != null) {
                oldList.addAll(list);
            }
            System.out.println(JSON.toJSONString(map));
        });
        //        进入直播间 topic1，线程 two
        Thread threadTwo = new Thread(() -> {
            ArrayList<String> list = new ArrayList<>();
            list.add("device11");
            list.add("device22");
//            map.put("topic1", list);
            List<String> oldList = map.putIfAbsent("topic1", list);
            if (oldList != null) {
                oldList.addAll(list);
            }
            System.out.println(JSON.toJSONString(map));
        });

        //        进入直播间 topic2，线程 three
        Thread threadThree = new Thread(() -> {
            ArrayList<String> list = new ArrayList<>();
            list.add("device111");
            list.add("device222");
//            map.put("topic2", list);
            List<String> oldList = map.putIfAbsent("topic2", list);
            if (oldList != null) {
                oldList.addAll(list);
            }
            System.out.println(JSON.toJSONString(map));
        });

//        启动线程
        threadOne.start();
        threadTwo.start();
        threadThree.start();
    }
}
