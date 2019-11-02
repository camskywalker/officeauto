package com.lizijian.officeauto.pojo;

public class Major {
    private String name;
    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Major{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    public Major(String name) {
        this.name = name;
    }

    public Major(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public Major() {
    }
}
