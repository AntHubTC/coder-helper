package com.tc.deepwater.gecase.dtsystem;

import com.tc.codehelper.core.ge.GEContext;
import com.tc.codehelper.core.ge.GEHeadComment;
import com.tc.codehelper.core.ge.model.GEOutputModel;
import com.tc.codehelper.core.ge.model.GETemplateModel;
import com.tc.codehelper.vue.elementUI.form.ElForm;
import com.tc.codehelper.vue.elementUI.form.ElFormItem;
import com.tc.codehelper.vue.elementUI.form.component.ElInput;
import com.tc.codehelper.vue.elementUI.form.rules.ElRuleLenLimit;
import com.tc.codehelper.vue.elementUI.form.rules.ElRuleNumLetter;
import com.tc.codehelper.vue.elementUI.form.rules.ElRuleRequired;
import com.tc.codehelper.vue.elementUI.table.ElTable;
import com.tc.codehelper.vue.elementUI.table.ElTableColumn;
import com.tc.deepwater.base.*;
import com.tc.deepwater.context.AddModifyPageGeContext;
import com.tc.deepwater.context.DataGridPageGeContext;
import com.tc.deepwater.context.MainPageGeContext;
import com.tc.deepwater.context.ManagerPageGeContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class Generate extends DeepwaterCURDPageGenerate {
    // 几个文件名
    private static final String RESULT_ADD_MODIFY = "dtSystemAddModify";
    private static final String RESULT_DATA_GRID = "dtSystemGrid";
    private static final String RESULT_MAIN_PAGE = "dtSystemMainPage";
    private static final String RESULT_MANAGER_PAGE = "dtSystemManager";

    private static final String RESULT_ADD_MODIFY_CLASS = "dt-system-add-modify";
    private static final String RESULT_DATA_GRID_CLASS = "dt-system-grid";
    private static final String RESULT_MAIN_PAGE_CLASS = "dt-system-main-page";
    private static final String RESULT_MANAGER_PAGE_CLASS = "dt-system-manager";

    private static final String RESULT_ADD_MODIFY_FILE = RESULT_ADD_MODIFY + ".vue";
    private static final String RESULT_DATA_GRID_FILE = RESULT_DATA_GRID + ".vue";
    private static final String RESULT_MAIN_PAGE_FILE = RESULT_MAIN_PAGE + ".vue";
    private static final String RESULT_MANAGER_PAGE_FILE = RESULT_MANAGER_PAGE + ".vue";

    @Test
    public void run() {
        super.generate();
    }

    // 新增修改页面任务
    @Override
    public GEContext getAddModifyTask() {
        String headCommentDescription = "系统新增删除"; // 注释描述
        String pageAddTitle = "新增系统";// 新增标题
        String pageModifyTitle = "编辑系统";// 修改标题
        String backBtnToPage = "mainPage";// 点击返回页面前往的页面
        String addURLName = "ADD_DTSYSTEM_INFO"; // 新增URL请求名称
        String modifyURLName = "MODIFY_DTSYSTEM_INFO"; // 修改I啊URL请求名称

        GETemplateModel gm = DeepWaterTemplate.getTemplateModel(
                DeepWaterTemplate.ADD_MODIFY_FORM);
        GEOutputModel om = new GEOutputModel(
                "output", // 输出路径
                RESULT_ADD_MODIFY_FILE // 输出名称
        );
        AddModifyPageGeContext pageContext = new AddModifyPageGeContext(gm, om);
        pageContext.setHeadComment( // 头部注释
                new GEHeadComment(AUTHOR, headCommentDescription));

        pageContext.setAddTitle(pageAddTitle); // 新增标题
        pageContext.setModifyTitle(pageModifyTitle);// 修改标题
        pageContext.setMainClass(RESULT_ADD_MODIFY_CLASS);// 组件名称
        pageContext.setBackBtnToPage(backBtnToPage);// 点击返回页面前往的页面

        ElForm elForm = new ElForm();
        elForm.setAddURLName(addURLName);
        elForm.setModifyURLName(modifyURLName);

        pageContext.setForm(elForm);

        ElFormItem item1 = new ElFormItem("系统编号", "sysNo", new ElInput()).addToForm(elForm);
        ElFormItem item2 = new ElFormItem("系统类型", "sysType", new ElInput()).addToForm(elForm);
        ElFormItem item3 = new ElFormItem("系统名称", "sysName", new ElInput()).addToForm(elForm);

        item1.addRules(
                new ElRuleRequired(), // 必输项
                new ElRuleLenLimit(1, 4), // 长度限制
                new ElRuleNumLetter() // 只能输入数字和字母
        );
        item2.addRules(
                new ElRuleRequired() // 必输项
        );
        item3.addRules(
                new ElRuleLenLimit(1, 66) // 长度限制
        );

        return pageContext;
    }

    // 生成表格数据组件页面
    @Override
    public GEContext getDataGridTask() {
        String headCommentDescription = "系统数据表格"; // 注释描述
        String loadDataURLName = "SEARCH_DTSYSTEM_BY_EXAMPLE";// 设置获取加载数据的URL

        GETemplateModel gm = DeepWaterTemplate.getTemplateModel(DeepWaterTemplate.GRID_COMPONENT);
        GEOutputModel om = new GEOutputModel(
                "output", // 输出路径
                RESULT_DATA_GRID_FILE // 输出名称
        );
        DataGridPageGeContext pageContext = new DataGridPageGeContext(gm, om);
        pageContext.setHeadComment( // 头部注释
                new GEHeadComment(AUTHOR, headCommentDescription));

        pageContext.setMainClass(RESULT_DATA_GRID_CLASS);// 组件名称
        pageContext.setLoadDataURLName(loadDataURLName);// 设置获取加载数据的URL

        // 添加表格
        ElTable elTable = new ElTable();
        pageContext.setTable(elTable);

        // 添加表格列
        elTable.addColumn(new ElTableColumn("系统编号", "sysNo"));
        elTable.addColumn(new ElTableColumn("系统类型", "sysType"));
        elTable.addColumn(new ElTableColumn("系统名称", "sysName"));

        return pageContext;
    }

    @Override
    public GEContext getMainPageTask() {
        String headCommentDescription = "管理首页"; // 注释描述
        String pageTitle = "迁移系统管理"; // 页面标题
        String searchPlaceHolder = "输入系统编号或系统名称搜索";
        String deleteDataURLName = "DELETE_DTSYSTEM_INFO";

        GETemplateModel gm = DeepWaterTemplate.getTemplateModel(DeepWaterTemplate.MAIN_PAGE_COMPONENT);
        GEOutputModel om = new GEOutputModel(
                "output", // 输出路径
                RESULT_MAIN_PAGE_FILE // 输出名称
        );
        MainPageGeContext pageContext = new MainPageGeContext(gm, om);
        pageContext.setHeadComment( // 头部注释
                new GEHeadComment(AUTHOR, headCommentDescription));

        pageContext.setMainClass(RESULT_MAIN_PAGE_CLASS);// 组件名称
        pageContext.setTitle(pageTitle); // 标题名称
        pageContext.setSearchPlaceHolder(searchPlaceHolder); // 搜索提示
        pageContext.setGridComponentName(RESULT_DATA_GRID); // 表格组件名称
        pageContext.setGridComponentClass(RESULT_DATA_GRID_CLASS); // 表格组件class
        pageContext.setDeleteDataURLName(deleteDataURLName); // 删除请求URL名称

        return pageContext;
    }

    @Override
    public GEContext getManagerPageTask() {
        String headCommentDescription = "管理首页"; // 注释描述
        String pageTitle = "迁移系统管理"; // 页面标题
        String searchPlaceHolder = "输入系统编号或系统名称搜索";
        String deleteDataURLName = "DELETE_PARAM_INFO";

        GETemplateModel gm = DeepWaterTemplate.getTemplateModel(DeepWaterTemplate.MANAGER_PAGE_COMPONENT);
        GEOutputModel om = new GEOutputModel(
                "output", // 输出路径
                RESULT_MANAGER_PAGE_FILE // 输出名称
        );
        ManagerPageGeContext pageContext = new ManagerPageGeContext(gm, om);
        pageContext.setHeadComment( // 头部注释
                new GEHeadComment(AUTHOR, headCommentDescription));

        pageContext.setMainClass(RESULT_MANAGER_PAGE_CLASS);
        // 主页组件信息
        pageContext.setMainPageComponentName(RESULT_MAIN_PAGE);
        pageContext.setMainPageComponentClass(RESULT_MAIN_PAGE_CLASS);
        // 新增修改组件信息
        pageContext.setAddModifyPageComponentName(RESULT_ADD_MODIFY);
        pageContext.setAddModifyPageComponentClass(RESULT_ADD_MODIFY_CLASS);

        return pageContext;
    }
}
