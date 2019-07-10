package com.tc.deepwater.context;

import com.tc.codehelper.core.ge.model.GEOutputModel;
import com.tc.codehelper.core.ge.model.GETemplateModel;
import com.tc.codehelper.vue.elementUI.table.ElTable;

/**
 * 数据表格页面任务正文
 */
public class DataGridPageGeContext extends DeepwaterGeContext {

    public DataGridPageGeContext() {
    }

    public DataGridPageGeContext(GETemplateModel templateModel, GEOutputModel outputModel) {
        super(templateModel, outputModel);
    }
    
    // 表格
    public ElTable getTable() {
        return (ElTable) this.get("table");
    }

    public void setTable(ElTable table) {
        this.put("table", table);
    }

    // 加载数据的URL的名称
    public String getLoadDataURLName() {
        return (String) this.get("loadDataURLName");
    }

    public void setLoadDataURLName(String loadDataURLName) {
        this.put("loadDataURLName", loadDataURLName);
    }
}
