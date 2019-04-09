package com.caihyspace.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *  user实体类
 */
@JacksonXmlRootElement
@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;
    @JacksonXmlProperty(localName = "name")
    @Column
    private String name;
    @JacksonXmlProperty(localName = "age")
    @Column
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
