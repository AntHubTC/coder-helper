package com.tc.codehelper.core.ge;

import com.tc.codehelper.core.ge.model.GEOutputModel;
import com.tc.codehelper.core.ge.model.GETemplateModel;

/**
 * 带有代码头部注释说明的任务上下文
 */
public class GECodeContext extends GEContext {
    private static final String HEAD_COMMENT_KEY = "headComment";

    public GECodeContext() {
    }

    public GECodeContext(GETemplateModel templateModel, GEOutputModel outputModel) {
        super(templateModel, outputModel);
    }

    public GEHeadComment getHeadComment() {
        return (GEHeadComment) this.get(HEAD_COMMENT_KEY);
    }

    public void setHeadComment(GEHeadComment headComment) {
        this.put(HEAD_COMMENT_KEY, headComment);
    }
}
