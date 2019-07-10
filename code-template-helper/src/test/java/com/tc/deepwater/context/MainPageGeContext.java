package com.tc.deepwater.context;

import com.tc.codehelper.core.ge.model.GEOutputModel;
import com.tc.codehelper.core.ge.model.GETemplateModel;

public class MainPageGeContext extends DeepwaterGeContext {
    public MainPageGeContext() {
    }

    public MainPageGeContext(GETemplateModel templateModel, GEOutputModel outputModel) {
        super(templateModel, outputModel);
    }

    public String getTitle() {
        return (String) this.get("title");
    }

    public void setTitle(String title) {
        this.put("title", title);
    }

    public String getSearchPlaceHolder() {
        return (String) this.get("searchPlaceHolder");
    }

    public void setSearchPlaceHolder(String searchPlaceHolder) {
        this.put("searchPlaceHolder", searchPlaceHolder);
    }

    public String getGridComponentName() {
        return (String) this.get("gridComponentName");
    }

    public void setGridComponentName(String gridComponentName) {
        this.put("gridComponentName", gridComponentName);
    }

    public String getGridComponentClass() {
        return (String) this.get("gridComponentClass");
    }

    public void setGridComponentClass(String gridComponentClass) {
        this.put("gridComponentClass", gridComponentClass);
    }

    public String getDeleteDataURLName() {
        return (String) this.get("deleteDataURLName");
    }

    public void setDeleteDataURLName(String deleteDataURLName) {
        this.put("deleteDataURLName", deleteDataURLName);
    }
}
