package com.tc.deepwater.context;

import com.tc.codehelper.core.ge.GECodeContext;
import com.tc.codehelper.core.ge.model.GEOutputModel;
import com.tc.codehelper.core.ge.model.GETemplateModel;

public class DeepwaterGeContext extends GECodeContext {

    public DeepwaterGeContext() {
    }

    public DeepwaterGeContext(GETemplateModel templateModel, GEOutputModel outputModel) {
        super(templateModel, outputModel);
    }

    // 组件名称/组件主类
    public String getMainClass() {
        return (String) super.get("mainClass");
    }

    public DeepwaterGeContext setMainClass(String mainClass) {
        super.put("mainClass", mainClass);
        return this;
    }
}
