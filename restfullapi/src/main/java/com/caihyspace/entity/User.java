package com.caihyspace.entity;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 *  user实体类
 */
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;

    private String name;

    private Integer age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
