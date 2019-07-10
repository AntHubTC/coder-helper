package com.tc.codehelper.core.ge;

import com.tc.codehelper.core.AbstractEngine;

import java.util.ArrayList;
import java.util.List;

public class GEngine extends AbstractEngine {

    private List<GEContext> taskContextList;

    public GEngine() {
    }
    public GEngine(List<GEContext> taskContextList) {
        this.taskContextList = taskContextList;
    }

    @Override
    public void run() throws Exception {
        System.out.println("总任务数：" + taskContextList.size());
        System.out.println("正在验证任务配置信息...");
        // 配置验证
        for (GEContext geContext : taskContextList) {
            geContext.validate();
        }
        System.out.println("验证任务配置信息完成");

        System.out.println("执行代码生成任务...");
        List<Thread> threadList = new ArrayList<>();
        for (GEContext ctx : taskContextList) {
            Thread thread = new Thread(new GETask(ctx));
            thread.start();
            threadList.add(thread);
        }
        // 主线程等待子线程执行完毕
        for (Thread thread : threadList) {
            thread.join();
        }
    }
}
