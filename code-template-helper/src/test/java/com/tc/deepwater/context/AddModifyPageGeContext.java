package com.tc.deepwater.context;

import com.tc.codehelper.core.ge.model.GEOutputModel;
import com.tc.codehelper.core.ge.model.GETemplateModel;
import com.tc.codehelper.vue.elementUI.form.ElForm;

/**
 * 新增修改页面任务正文
 */
public class AddModifyPageGeContext extends DeepwaterGeContext {

    public AddModifyPageGeContext() {
    }

    public AddModifyPageGeContext(GETemplateModel templateModel, GEOutputModel outputModel) {
        super(templateModel, outputModel);
    }

    public String getAddTitle() {
        return (String) super.get("addTitle");
    }

    public AddModifyPageGeContext setAddTitle(String addTitle) {
        super.put("addTitle", addTitle);
        return this;
    }

    public String getModifyTitle() {
        return (String) super.get("modifyTitle");
    }

    public AddModifyPageGeContext setModifyTitle(String modifyTitle) {
        super.put("modifyTitle", modifyTitle);
        return this;
    }

    public String getBackBtnToPage() {
        return (String) super.get("backBtnToPage");
    }

    public AddModifyPageGeContext setBackBtnToPage(String backBtnToPage) {
        super.put("backBtnToPage", backBtnToPage);
        return this;
    }

    public ElForm getForm() {
        return (ElForm) super.get("form");
    }

    public AddModifyPageGeContext setForm(ElForm form) {
        super.put("form", form);
        return this;
    }
}
