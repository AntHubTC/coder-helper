package com.tc.codehelper.core.ge;

import com.tc.codehelper.core.ge.model.GEOutputModel;
import com.tc.codehelper.core.ge.model.GETemplateModel;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 代码生成任务
 */
public class GETask implements Runnable {

    private GEContext context;

    public GETask(GEContext context) {
        this.context = context;
    }

    @Override
    public void run() {
        /* 首先，初始化运行时引擎，使用默认的配置 */
        Velocity.init();

        // 生成日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        this.context.put("date", sdf.format(Calendar.getInstance().getTime()));

        /* 创建Context对象，然后把数据放进去 */
        VelocityContext velocityContext = new VelocityContext();
        // 所有目标的变量放ctx中
        velocityContext.put("ctx", this.context);

        /* 渲染模板 */
        GETemplateModel tm = this.context.getTemplateModel();
        GEOutputModel om = this.context.getOutputModel();
        String templatePath = tm.getTemplatePath() + "/" + tm.getTemplateName();

        FileWriter fw = null;
        try {
            File outputFile = new File(om.getOutPath(), om.getOutFileName());
            if (outputFile.exists()) {
                outputFile.delete();
            }
            if (!outputFile.getParentFile().exists()) {
                outputFile.getParentFile().mkdirs();
            }
            fw = new FileWriter(outputFile, false);
            Velocity.mergeTemplate(
                    templatePath,
                    tm.getCharset(),
                    velocityContext,
                    fw
            );
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
