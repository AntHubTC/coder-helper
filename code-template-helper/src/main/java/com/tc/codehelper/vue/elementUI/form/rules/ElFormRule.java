package com.tc.codehelper.vue.elementUI.form.rules;

import com.google.gson.Gson;

import java.io.Serializable;

public abstract class ElFormRule implements Serializable {
    // 验证失败提示消息
    private String message = "";
    // 触发验证条件
    private String trigger = "blur";

    public ElFormRule() {
    }

    public ElFormRule(String message) {
        this.message = message;
    }

    public ElFormRule(String message, String trigger) {
        this.message = message;
        this.trigger = trigger;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
