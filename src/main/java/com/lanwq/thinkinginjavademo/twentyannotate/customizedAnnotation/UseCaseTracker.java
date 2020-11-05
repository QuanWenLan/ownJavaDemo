package com.lanwq.thinkinginjavademo.twentyannotate.customizedAnnotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Vin lan
 * @className UseCaseTracker
 * @description TODO
 * @createTime 2020-11-04  15:31
 **/
public class UseCaseTracker {
    public static void trackUseCases(List<Integer> useCases, Class<?> cl) {
        for (Method m : cl.getDeclaredMethods()) {
            UseCase uc = m.getAnnotation(UseCase.class); // 返回指定类型的注解对象
            if (uc != null) {
                System.out.println("Found Use Case:" + uc.id() + " " + uc.description());
                useCases.remove(new Integer(uc.id()));
            }
        }
        for (int i : useCases) {
            System.out.println("Warning: Missing use case-" + i);
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> useCases = new ArrayList<>();
        Collections.addAll(useCases, 47, 48, 49, 50);
        trackUseCases(useCases, PasswordUtils.class);
    }
}
