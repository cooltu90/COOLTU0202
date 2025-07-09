package com.codingtu.cooltu.lib4j.file.write;

import com.codingtu.cooltu.lib4j.es.BaseEs;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.file.FileTool;
import com.codingtu.cooltu.lib4j.log.LibLogs;
import com.codingtu.cooltu.lib4j.tool.CountTool;
import com.codingtu.cooltu.lib4j.tool.StringTool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileWriter {

    private File file;
    private boolean isCover;
    private String charsetName;

    /**************************************************
     *
     * 创建方法
     *
     **************************************************/

    public static FileWriter to(File file) {
        FileWriter fileWriter = new FileWriter();
        fileWriter.file = file;
        return fileWriter;
    }

    public static FileWriter to(String path) {
        return to(new File(path));
    }

    /**************************************************
     *
     * 添加文件
     *
     **************************************************/

    public FileWriter cover() {
        this.isCover = true;
        return this;
    }

    public FileWriter charsetName(String charsetName) {
        this.charsetName = charsetName;
        return this;
    }

    /**************************************************
     *
     * 写入
     *
     **************************************************/

    public void write(Object line) {
        write(Es.es(line));
    }

    public void write(Object... lines) {
        write(Es.es(lines));
    }

    public void write(List lines) {
        write(Es.es(lines));
    }

    public void write(BaseEs lineEs) {
        if (!isCover) {
            if (this.file.exists()) {
                throw new RuntimeException("文件已经存在");
            }
        } else {
            if (this.file == null) {
                this.file = FileTool.getDefaultFile();
            }
        }
        BufferedWriter bw = null;
        try {
            FileTool.createParentDir(file);
            if (StringTool.isNotBlank(charsetName)) {
                bw = FileTool.getBufferedWriter(this.file, charsetName);
            } else {
                bw = FileTool.getBufferedWriter(this.file);
            }
            int count = CountTool.count(lineEs);
            for (int i = 0; i < count; i++) {
                bw.write(lineEs.getByIndex(i).toString());
                bw.newLine();
            }
        } catch (Exception e) {
            LibLogs.w(e);
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    LibLogs.w(e);
                }
                bw = null;
            }
        }
    }

}
