package com.tc.codehelper.vue.elementUI.form.rules;

public abstract class ElFormValidatorRule extends ElFormRule{
    public String validator;

    public ElFormValidatorRule(String validator) {
        this.validator = validator;
    }

    public String getValidator() {
        return validator;
    }

    public void setValidator(String validator) {
        this.validator = validator;
    }
}
