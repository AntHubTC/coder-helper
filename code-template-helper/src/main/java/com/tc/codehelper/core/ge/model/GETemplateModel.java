package com.tc.codehelper.core.ge.model;

import java.io.Serializable;

public class GETemplateModel implements Serializable {
    // 模板路径
    private String templatePath;
    // 模板名称
    private String templateName;
    // 字符编码
    private String charset;

    // 验证数据
    public void validate() throws Exception {
        if (null == templatePath || "".equals(templatePath.trim())) {
            throw new Exception("模板路径不能为空！");
        }
        if (null == templateName || "".equals(templateName.trim())) {
            throw new Exception("模板名称不能为空！");
        }
    }

    public GETemplateModel() {
    }

    public GETemplateModel(String templatePath, String templateName) {
        this.templatePath = templatePath;
        this.templateName = templateName;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getCharset() {
        if (null == charset || "".equals(charset.trim())) {
            return "utf-8";
        }
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    @Override
    public String toString() {
        return "GETemplateModel{" +
                "templatePath='" + templatePath + '\'' +
                ", templateName='" + templateName + '\'' +
                ", charset='" + charset + '\'' +
                '}';
    }
}
