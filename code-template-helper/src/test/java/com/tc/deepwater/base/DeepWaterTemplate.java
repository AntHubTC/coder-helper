package com.tc.deepwater.base;

import com.tc.codehelper.core.ge.model.GETemplateModel;

public class DeepWaterTemplate {

    public static final int ADD_MODIFY_FORM = 0x001;
    public static final int GRID_COMPONENT = 0x002;
    public static final int MAIN_PAGE_COMPONENT = 0x003;
    public static final int MANAGER_PAGE_COMPONENT = 0x004;

    public static GETemplateModel getTemplateModel(int templateType) {
        GETemplateModel gm = null;
        switch (templateType) {
            case ADD_MODIFY_FORM:
                gm = new GETemplateModel("templates/deepWaterGeneral", "vueAddModify.vm");
                break;
            case GRID_COMPONENT:
                gm = new GETemplateModel("templates/deepWaterGeneral", "vueDataGrid.vm");
                break;
            case MAIN_PAGE_COMPONENT:
                gm = new GETemplateModel("templates/deepWaterGeneral", "vueMainPage.vm");
                break;
            case MANAGER_PAGE_COMPONENT:
                gm = new GETemplateModel("templates/deepWaterGeneral", "vueManager.vm");
                break;
            default:
                throw new RuntimeException("请指定正确的模板类型！");
        }

        return gm;
    }
}
