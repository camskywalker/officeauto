package com.lizijian.officeauto.pojo;

import java.util.List;

public class Url {
    private Integer id;
    private String httpUrl;
    private List<Role> needRoles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    public List<Role> getNeedRoles() {
        return needRoles;
    }

    public void setNeedRoles(List<Role> needRoles) {
        this.needRoles = needRoles;
    }

    @Override
    public String toString() {
        return "Url{" +
                "id=" + id +
                ", httpUrl='" + httpUrl + '\'' +
                ", needRoles=" + needRoles +
                '}';
    }
}
