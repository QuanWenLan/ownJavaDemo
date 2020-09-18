package com.ett.jasper.po;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author Vin lan
 * @className User
 * @description TODO
 * @createTime 2020-09-08  15:10
 **/
@Entity
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private String id;
    @Column(name = "user_name")
    private String userName;
    @Column
    private Integer age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}


