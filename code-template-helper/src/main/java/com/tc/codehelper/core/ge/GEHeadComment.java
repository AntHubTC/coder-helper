package com.tc.codehelper.core.ge;

import com.google.gson.Gson;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 头部文件注释
 */
public class GEHeadComment implements Serializable {
    // 作者
    public String author = "admin";
    // 创建日期
    public String createDate = "";
    // 描述
    public String description = "";

    public GEHeadComment() {
    }

    public GEHeadComment(String author) {
        this.author = author;
    }

    public GEHeadComment(String author, String description) {
        this.author = author;
        this.description = description;
    }

    public GEHeadComment(String author, String description, String createDate) {
        this.author = author;
        this.description = description;
        this.createDate = createDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreateDate() {
        if (null == this.createDate || "".equals(this.createDate.trim())) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            this.createDate = sdf.format(Calendar.getInstance().getTime());
            return this.createDate;
        }
        return this.createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}
