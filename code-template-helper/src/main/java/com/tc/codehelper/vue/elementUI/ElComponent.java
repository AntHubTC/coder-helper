package com.tc.codehelper.vue.elementUI;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * elementUI组件抽象基类
 */
public abstract class ElComponent implements Serializable{
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
