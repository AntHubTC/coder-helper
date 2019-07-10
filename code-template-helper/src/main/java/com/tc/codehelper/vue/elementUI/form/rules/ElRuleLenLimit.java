package com.tc.codehelper.vue.elementUI.form.rules;

/**
 * 长度验证规则
 */
public class ElRuleLenLimit extends ElFormRule {
    private Integer min;
    private Integer max;

    public ElRuleLenLimit(Integer min, Integer max) {
        super("长度在 " + min + " 到 " + max + " 之间");
        this.min = min;
        this.max = max;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }
}
