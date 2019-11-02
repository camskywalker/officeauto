package com.lizijian.officeauto.pojo;

import org.springframework.stereotype.Component;

@Component
public class WebApiResult {

    /**
     * 0:返回正常；
     * 1：没找到查询对象
     * 2：未知错误
     */
    private int status;
    private String msg;
    private Object data;

    public void isOk(){
        this.status = 0;
    }

    public void isNull(){
        this.status = 1;
        this.msg = "未找到查询对象";
    }

    public void isErr(){
        this.status = 2;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
