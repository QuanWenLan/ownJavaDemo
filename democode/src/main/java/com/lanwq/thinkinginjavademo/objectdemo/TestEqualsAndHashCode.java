package com.lanwq.thinkinginjavademo.objectdemo;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Vin lan
 * @className TestEqualsAndHashCode
 * @description TODO 用来测试重写equals()方法和hashCode()方法
 * @createTime 2020-10-12  11:34
 **/
public class TestEqualsAndHashCode {
    public static void main(String[] args) {
        Person person1 = new Person("小明", 20);
        Person person2 = new Person("小明", 20);
        System.out.println(person1 == person2); // false
        System.out.println(person1.equals(person2)); // false
        testWithBothMethod(person1, person2);

        // 重写equals()而没有重写hashCode()出现的问题
        Person2 a = new Person2("小明", 20);
        Person2 b = new Person2("小明", 20);
        testWithoutHashCode(a, b);
    }

    private static void testWithoutHashCode(Person2 person1, Person2 person2) {
        System.out.println(person1.equals(person2)); // 输出是为true
        Set<Person2> set = new HashSet<>();
        set.add(person1);

        System.out.println("重写了equals()而没有重写hashCode()方法：" + set.contains(person2));
    }

    private static void testWithBothMethod(Person person1, Person person2) {
        System.out.println(person1.equals(person2)); // 输出是为true
        Set<Person> set = new HashSet<>();
        set.add(person1);

        System.out.println("重写了equals()和hashCode()方法：" + set.contains(person2));
    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(age, person.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}

// 重写了equals()而没有重写hashCode()
class Person2 {
    private String name;
    private int age;

    public Person2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person2() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person2 person = (Person2) o;
        return Objects.equals(name, person.name) && Objects.equals(age, person.age);
    }

}
