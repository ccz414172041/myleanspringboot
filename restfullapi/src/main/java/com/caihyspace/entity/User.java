package com.caihyspace.entity;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;


/**
 *  user实体类
 */
@JacksonXmlRootElement
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
