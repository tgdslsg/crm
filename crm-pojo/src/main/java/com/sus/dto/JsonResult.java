package com.sus.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by Administrator on 2017/3/20.
 */
public class JsonResult {

    public static final String SUCCESS = "success";
    public static final String ERROR = "error";

    private String message;
    private String state;
    private Object data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public JsonResult() {
    }

    public JsonResult(String state) {
        this.state = state;
    }

    public JsonResult(String state, String message) {
        this.state = state;
        this.message = message;
    }

    public JsonResult(String state,Object data) {
        this.state = state;
        this.data = data;
    }

    public JsonResult(String state, String message, Object data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }
}
