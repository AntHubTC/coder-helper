package com.tc.codehelper.vue.elementUI.table;

import com.tc.codehelper.vue.elementUI.ElComponent;

public class ElTableColumn extends ElComponent {
    private String label;
    private String prop;
    private String width = "200";

    public ElTableColumn() {
    }

    public ElTableColumn(String label, String prop) {
        this.label = label;
        this.prop = prop;
    }

    public ElTableColumn(String label, String prop, String width) {
        this.label = label;
        this.prop = prop;
        this.width = width;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }
}
