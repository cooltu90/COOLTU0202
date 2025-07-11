package com.codingtu.cooltu.lib4j.path;

import com.codingtu.cooltu.lib4j.es.BaseEs;
import com.codingtu.cooltu.lib4j.file.read.FileReader;
import com.codingtu.cooltu.lib4j.file.write.FileWriter;
import com.codingtu.cooltu.lib4j.path.base.BaseFile;

import java.io.File;
import java.util.List;

public class TextFile extends BaseFile {
    public TextFile(String root) {
        super(root);
    }

    public TextFile(File rootFile) {
        super(rootFile);
    }

    public BaseEs<String> getTextLines() {
        return FileReader.from(rootFile()).readLine();
    }

    public String getText() {
        return FileReader.from(rootFile()).readToStr();
    }

    public void setText(Object text) {
        FileWriter.to(rootFile()).cover().write(text);
    }

    public void setTextLines(List lines) {
        FileWriter.to(rootFile()).cover().write(lines);
    }

    public void setTextLines(Object... lines) {
        FileWriter.to(rootFile()).cover().write(lines);
    }

    public void setTextLines(BaseEs lines) {
        FileWriter.to(rootFile()).cover().write(lines);
    }
}
