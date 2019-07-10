package com.tc.codehelper.vue.elementUI.form;

import com.tc.codehelper.vue.elementUI.ElComponent;
import com.tc.codehelper.vue.elementUI.form.component.ElFormInputComponent;
import com.tc.codehelper.vue.elementUI.form.rules.ElFormRule;

import java.util.ArrayList;
import java.util.List;

public class ElFormItem extends ElComponent {
    // 表单的label
    public String label = "未命名";
    // 表单的name
    public String name = "uname";
    // 表单组件
    public ElFormInputComponent component;
    // 表单项验证规则
    public List<ElFormRule> rules = new ArrayList<>();

    public ElFormItem() {
    }

    public ElFormItem(String label, String name, ElFormInputComponent component) {
        this.label = label;
        this.name = name;
        this.component = component;

        // 自动为内容name设置上
        if ("uname".equals(component.getName())) {
            component.setName(this.name);
        }
    }

    public ElFormItem(String label, String name, ElFormInputComponent component, List<ElFormRule> rules) {
        this(label, name, component);
        this.rules = rules;
    }

    /**
     * 添加验证规则
     * @param rules
     * @return
     */
    public ElFormItem addRules(ElFormRule ...rules) {
        for (ElFormRule rule : rules) {
            this.rules.add(rule);
        }
        return this;
    }

    public ElFormItem addToForm(ElForm elForm) {
        elForm.addFormItem(this);
        return this;
    }

    public List<ElFormRule> getRules() {
        return rules;
    }

    public void setRules(List<ElFormRule> rules) {
        this.rules = rules;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ElComponent getComponent() {
        return component;
    }

    public void setComponent(ElFormInputComponent component) {
        this.component = component;
    }
}