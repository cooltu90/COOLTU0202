package com.codingtu.cooltu.lib4j.file.read;

import com.codingtu.cooltu.lib4j.es.BaseEs;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.file.FileTool;
import com.codingtu.cooltu.lib4j.file.read.parse.Parse;
import com.codingtu.cooltu.lib4j.tool.StringTool;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class FileReader<T> {
    private File file;
    private Parse<T> parse;
    private String charsetName;

    private FileReader() {

    }

    /**************************************************
     *
     * 静态创建
     *
     **************************************************/
    private static <T> FileReader<T> create(Parse<T> parse) {
        FileReader<T> fileReader = new FileReader<>();
        fileReader.parse = parse;
        return fileReader;
    }

    public static FileReader<String> from(File file) {
        return from(file, new Parse<String>() {
            @Override
            public String parse(String line) {
                return line;
            }
        });
    }

    public static FileReader<String> from(String path) {
        return from(new File(path));
    }

    public static <T> FileReader<T> from(File file, Parse<T> parse) {
        FileReader<T> fileReader = create(parse);
        fileReader.file = file;
        return fileReader;
    }

    public static <T> FileReader<T> from(String path, Parse<T> parse) {
        return from(new File(path), parse);
    }

    /**************************************************
     *
     *
     *
     **************************************************/

    public FileReader<T> charsetName(String charsetName) {
        this.charsetName = charsetName;
        return this;
    }


    /**************************************************
     *
     * readline
     *
     **************************************************/
    public void readLine(ReadLine<T> readLine) {
        if (this.file == null) {
            this.file = FileTool.getDefaultFile();
        }
        if (!this.file.exists()) {
//            throw new RuntimeException("未指定文件");
            return;
        }

        if (parse == null) {
            throw new RuntimeException("未添加转换器");
        }

        BufferedReader br = null;
        try {
            if (StringTool.isNotBlank(charsetName)) {
                br = FileTool.getBufferedReader(file, charsetName);
            } else {
                br = FileTool.getBufferedReader(file);
            }
            String line = null;
            while ((line = br.readLine()) != null) {
                readLine.readLine(parse.parse(line));
            }
        } catch (Exception e) {
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                br = null;
            }
        }

    }

    public BaseEs<T> readLine() {
        BaseEs<T> es = Es.es();
        readLine(new ReadLine<T>() {
            @Override
            public void readLine(T t) {
                es.add(t);
            }
        });
        return es;
    }

    public String readToStr() {
        StringBuilder sb = new StringBuilder();
        readLine(new ReadLine<T>() {
            @Override
            public void readLine(T t) {
                if (t != null) {
                    sb.append(t);
                }
            }
        });
        return sb.toString();
    }

}

