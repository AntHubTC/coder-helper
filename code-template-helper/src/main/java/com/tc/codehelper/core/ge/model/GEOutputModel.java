package com.tc.codehelper.core.ge.model;

import java.io.Serializable;

public class GEOutputModel implements Serializable {
    // 输出路径
    private String outPath;
    // 输出文件名
    private String outFileName;

    public GEOutputModel() {
    }

    public void validate() throws Exception {
        if (null == outPath || "".equals(outPath.trim())) {
            throw new Exception("输出路径不能为空！");
        }
        if (null == outFileName || "".equals(outFileName.trim())) {
            throw new Exception("输出文件名称不能为空！");
        }
    }

    public GEOutputModel(String outPath, String outFileName) {
        this.outPath = outPath;
        this.outFileName = outFileName;
    }

    public String getOutPath() {
        return outPath;
    }

    public void setOutPath(String outPath) {
        this.outPath = outPath;
    }

    public String getOutFileName() {
        return outFileName;
    }

    public void setOutFileName(String outFileName) {
        this.outFileName = outFileName;
    }

    @Override
    public String toString() {
        return "GEOutputModel{" +
                "outPath='" + outPath + '\'' +
                ", outFileName='" + outFileName + '\'' +
                '}';
    }
}
