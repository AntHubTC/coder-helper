package com.tc.codehelper;

import com.tc.codehelper.core.ge.GEContext;
import com.tc.codehelper.core.ge.GEngine;
import com.tc.codehelper.core.ge.model.GEOutputModel;
import com.tc.codehelper.core.ge.model.GETemplateModel;

import java.util.ArrayList;
import java.util.List;

public class GEDemo {
    public static void main(String[] args) {
        GETemplateModel gm = new GETemplateModel("templates/general", "vueAddModify.vm");
        GEOutputModel om = new GEOutputModel("output", "dtSystemAddModify.vue");

        List<GEContext> taskContext = new ArrayList<>();

        GEContext ctx1 = new GEContext(gm, om);
        ctx1.put("ctx_addTitle", "新增系统");
        ctx1.put("ctx_modifyTitle", "编辑系统");
        ctx1.put("ctx_mainClass", "dt-system-add-modify");
        ctx1.put("ctx_backBtnToPage", "mainPage");


        taskContext.add(ctx1);

        GEngine ge = new GEngine(taskContext);
        try {
            ge.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
