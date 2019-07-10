package com.tc.deepwater.base;

import com.tc.codehelper.core.ge.GEContext;
import com.tc.codehelper.core.ge.GEngine;

import java.util.ArrayList;
import java.util.List;

public abstract class DeepwaterCURDPageGenerate {
    protected static final String AUTHOR = "tangcheng_cd";

    // 任务正文
    private List<GEContext> taskContext = new ArrayList<>();
    // 新增修改页面
    public GEContext getAddModifyTask() {
        return null;
    };
    // 数据表格页面
    public GEContext getDataGridTask() {
        return null;
    };
    // 主页面
    public GEContext getMainPageTask() {
        return null;
    };

    // 管理页面
    public GEContext getManagerPageTask() {
        return null;
    };

    /**
     * 生成代码
     */
    protected final void generate() {
        // 添加任务
        this.addTaskContext(getAddModifyTask());
        this.addTaskContext(getDataGridTask());
        this.addTaskContext(getMainPageTask());
        this.addTaskContext(getManagerPageTask());

        // 创建转换引擎
        GEngine ge = new GEngine(taskContext);
        try {
            ge.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加任务正文
     * @param context
     */
    private void addTaskContext(GEContext context) {
        if (null != context) {
            taskContext.add(context);
        }
    }
}
