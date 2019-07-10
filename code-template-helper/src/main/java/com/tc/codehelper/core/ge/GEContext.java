package com.tc.codehelper.core.ge;

import com.google.gson.Gson;
import com.tc.codehelper.core.ge.model.GEOutputModel;
import com.tc.codehelper.core.ge.model.GETemplateModel;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成引擎上下文
 */
public class GEContext extends HashMap implements Serializable {
    // 模板model
    private GETemplateModel templateModel;
    // 结果输出model
    private GEOutputModel outputModel;

    public GEContext() {
    }

    public GEContext(GETemplateModel templateModel, GEOutputModel outputModel) {
        this.templateModel = templateModel;
        this.outputModel = outputModel;
    }

    // 验证数据
    public void validate() throws Exception {
        if (null == this.templateModel) {
            throw new Exception("模板Model不能为空！");
        }
        if (null == this.outputModel) {
            throw new Exception("输出Model不能为空！");
        }
        this.templateModel.validate();
        this.outputModel.validate();
    }


    public void put(String key,Object val){
        super.put(key, val);
    }

    public Object get(String key){
        return super.get(key);
    }

    public GETemplateModel getTemplateModel() {
        if (null == templateModel) {
            return new GETemplateModel("generate/templates", "template.vm");
        }
        return templateModel;
    }

    public void setTemplateModel(GETemplateModel templateModel) {
        this.templateModel = templateModel;
    }

    public GEOutputModel getOutputModel() {
        if (null == outputModel) {
            return new GEOutputModel("generate/output", "result.txt");
        }
        return outputModel;
    }

    public void setOutputModel(GEOutputModel outputModel) {
        this.outputModel = outputModel;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
