package com.shadow.entity;

/**
 * @ClassName User
 * @Description TODO
 * @Author CareShadow
 * @Date 2023/11/25 23:07
 * @Version 1.0
 **/
public class User {
    private String name;
    private String age;

    public User(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
