package com.tc.codehelper.vue.elementUI.form.component;

import com.tc.codehelper.vue.elementUI.ElComponent;

/**
 * 表单输入组件
 */
public abstract class ElFormInputComponent extends ElComponent {
    // 组件类型
    public String type;
    // 组件名称
    public String name = "uname";
    // 提示输入
    public String placeholder = "请在此输入";

    public ElFormInputComponent() {
    }

    public ElFormInputComponent(String type) {
        this.type = type;
    }

    public ElFormInputComponent(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public ElFormInputComponent(String type, String name, String placeholder) {
        this.type = type;
        this.name = name;
        this.placeholder = placeholder;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }
}
