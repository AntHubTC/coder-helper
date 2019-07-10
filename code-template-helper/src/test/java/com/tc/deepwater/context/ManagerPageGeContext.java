package com.tc.deepwater.context;

import com.tc.codehelper.core.ge.model.GEOutputModel;
import com.tc.codehelper.core.ge.model.GETemplateModel;

public class ManagerPageGeContext extends DeepwaterGeContext {
    public ManagerPageGeContext() {
    }

    public ManagerPageGeContext(GETemplateModel templateModel, GEOutputModel outputModel) {
        super(templateModel, outputModel);
    }

    // 管理主页面组件名称
    public String getMainPageComponentName() {
        return (String) this.get("mainPageComponentName");
    }

    public void setMainPageComponentName(String mainPageComponentName) {
        this.put("mainPageComponentName", mainPageComponentName);
    }

    // 管理主页面组件class
    public String getMainPageComponentClass() {
        return (String) this.get("mainPageComponentClass");
    }

    public void setMainPageComponentClass(String mainPageComponentClass) {
        this.put("mainPageComponentClass", mainPageComponentClass);
    }

    // 新增修改页面组件名称
    public String getAddModifyPageComponentName() {
        return (String) this.get("addModifyPageComponentName");
    }

    public void setAddModifyPageComponentName(String addModifyPageComponentName) {
        this.put("addModifyPageComponentName", addModifyPageComponentName);
    }

    // 新增修改页面组件class
    public String getAddModifyPageComponentClass() {
        return (String) this.get("addModifyPageComponentClass");
    }

    public void setAddModifyPageComponentClass(String addModifyPageComponentClass) {
        this.put("addModifyPageComponentClass", addModifyPageComponentClass);
    }
}
