package com.tc.codehelper.vue.elementUI.form;

import com.tc.codehelper.vue.elementUI.ElComponent;
import com.tc.codehelper.vue.elementUI.form.rules.ElFormRule;
import com.tc.codehelper.vue.elementUI.form.rules.ElFormValidatorRule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElForm extends ElComponent {
    public String size = "mini";
    public String labelWidth = "90px";
    public String labelPosition = "right";
    public String addURLName;
    public String modifyURLName;
    public List<ElFormItem> formItems = new ArrayList<>();

    public ElForm() {
    }

    public ElForm(List<ElFormItem> formItems) {
        this.formItems = formItems;
    }

    // 添加表单项
    public ElForm addFormItem(ElFormItem item) {
        this.formItems.add(item);
        return this;
    }

    // 获取引入的外部验证器
    public List<String> getValidators() {
        List<String> validators = new ArrayList<>();
        for (ElFormItem formItem : this.formItems) {
            List<ElFormRule> rules = formItem.getRules();
            for (ElFormRule rule : rules) {
                if (rule instanceof ElFormValidatorRule) {
                    String validator = ((ElFormValidatorRule) rule).getValidator();
                    if (!validators.contains(validator)) {
                        validators.add(validator);
                    }
                }
            }
        }
        return validators;
    }

    // 获取表单规则
    public Map<String, List<ElFormRule>> getFormRules() {
        Map<String, List<ElFormRule>> resultMap = new HashMap<>();

        for (ElFormItem formItem : this.formItems) {
            resultMap.put(formItem.getName(), formItem.getRules());
        }

        return resultMap;
    }

    public String getAddURLName() {
        return addURLName;
    }

    public void setAddURLName(String addURLName) {
        this.addURLName = addURLName;
    }

    public String getModifyURLName() {
        return modifyURLName;
    }

    public void setModifyURLName(String modifyURLName) {
        this.modifyURLName = modifyURLName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getLabelWidth() {
        return labelWidth;
    }

    public void setLabelWidth(String labelWidth) {
        this.labelWidth = labelWidth;
    }

    public String getLabelPosition() {
        return labelPosition;
    }

    public void setLabelPosition(String labelPosition) {
        this.labelPosition = labelPosition;
    }

    public List<ElFormItem> getFormItems() {
        return formItems;
    }

    public void setFormItems(List<ElFormItem> formItems) {
        this.formItems = formItems;
    }
}
