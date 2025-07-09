package com.codingtu.cooltu.lib4j.file.list;

import com.codingtu.cooltu.lib4j.es.BaseEs;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.tool.CountTool;

import java.io.File;
import java.util.LinkedList;

public class FileLister {
    private File dirFile;
    private boolean isContainsDir;
    private boolean isDirInFront;

    public static FileLister dir(String dir) {
        return dir(new File(dir));
    }

    public static FileLister dir(File dirFile) {
        FileLister fileLister = new FileLister();
        fileLister.dirFile = dirFile;
        return fileLister;
    }

    public FileLister containsDir() {
        this.isContainsDir = true;
        return this;
    }

    public FileLister dirInFront() {
        this.isDirInFront = true;
        return this;
    }


    public void listOnce(ListFile listFile) {
        Es.es(dirFile.listFiles()).ls(new Es.EachEs<File>() {
            public boolean each(int position, File file) {
                listFile.list(file);
                return false;
            }
        });
    }

    public void list(ListFile listFile) {
        listOnce(new ListFile() {
            @Override
            public void list(File file) {
                FileLister.this.list(file, listFile);
            }
        });
    }

    private void list(File file, ListFile listFile) {
        if (file.isDirectory()) {
            if (isContainsDir && isDirInFront)
                listFile.list(file);
            File[] files = file.listFiles();
            int count = CountTool.count(files);
            for (int i = 0; i < count; i++) {
                list(files[i], listFile);
            }
            if (isContainsDir && !isDirInFront)
                listFile.list(file);
        } else {
            listFile.list(file);
        }
    }

    public BaseEs<File> list() {
        BaseEs<File> fileEs = Es.es();
        list(new ListFile() {
            @Override
            public void list(File file) {
                fileEs.add(file);
            }
        });
        return fileEs;
    }

    public LinkedList<File> linkedList() {
        LinkedList<File> list = new LinkedList<>();
        list(new ListFile() {
            @Override
            public void list(File file) {
                list.addLast(file);
            }
        });
        return list;
    }

}
