package com.mforum.util;

/**
 * 向客户端响应操作结果的数据类型
 * @param <t> 向客户端响应的数据的类型
 */
public class JsonResult<t> {
    private Integer state;
    private String message;
    private t data;

    public JsonResult() {
    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    public JsonResult(Integer state, t data) {
        this.state = state;
        this.data = data;
    }

    public JsonResult(Integer state, String message, t data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public t getData() {
        return data;
    }

    public void setData(t data) {
        this.data = data;
    }
}
