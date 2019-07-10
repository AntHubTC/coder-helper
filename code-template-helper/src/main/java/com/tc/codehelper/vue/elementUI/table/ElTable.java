package com.tc.codehelper.vue.elementUI.table;

import com.tc.codehelper.vue.elementUI.ElComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * table组件
 */
public class ElTable extends ElComponent {

    // 表格中的所有的列
    public List<ElTableColumn> columns = new ArrayList<>();

    // 添加列
    public ElTable addColumn(ElTableColumn tableColumn) {
        this.columns.add(tableColumn);
        return this;
    }

    public List<ElTableColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<ElTableColumn> columns) {
        this.columns = columns;
    }
}
