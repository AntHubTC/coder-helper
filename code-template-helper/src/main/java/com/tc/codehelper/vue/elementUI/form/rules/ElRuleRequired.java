package com.tc.codehelper.vue.elementUI.form.rules;

/**
 * 比输验证规则
 */
public class ElRuleRequired extends ElFormRule {
    // 是否必输
    public Boolean required = true;

    public ElRuleRequired() {
        super("此项为必输项");
    }

    public ElRuleRequired(Boolean required) {
        this();
        this.required = required;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }
}
