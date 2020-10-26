package com.lanwq.thinkinginjavademo.classrtti;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vin lan
 * @className FilledList
 * @description TODO 使用泛型语法。存储了一个类的引用，稍候又产生了一个List，填充这个List对象使用时newInstance()方法，
 * 通过该引用生成的。
 * @createTime 2020-09-18  15:30
 **/
class CountedInteger {
    private static long counter;
    private final long id = counter++;
    @Override
    public String toString() {
        return Long.toString(id);
    }
}

public class FilledList<T> {
    private Class<T> type;
    public FilledList(Class<T> type) {
        this.type = type;
    }
    public List<T> create(int nElements) {
        ArrayList<T> result = new ArrayList<>();
        try {
            for (int i = 0; i < nElements; i++) {
                result.add(type.newInstance()); // 使用这个方法必须要有一个默认的无参构造器
            }
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return result;
    }

    public static void main(String[] args) {
        FilledList<CountedInteger> filledList = new FilledList<>(CountedInteger.class);
        System.out.println(filledList.create(15));
    }
}
