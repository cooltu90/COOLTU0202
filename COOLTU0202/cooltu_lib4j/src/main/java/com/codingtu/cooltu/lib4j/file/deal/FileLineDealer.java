package com.codingtu.cooltu.lib4j.file.deal;

import com.codingtu.cooltu.lib4j.file.read.FileReader;
import com.codingtu.cooltu.lib4j.file.read.ReadLine;
import com.codingtu.cooltu.lib4j.file.write.FileWriter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileLineDealer {
    private File file;

    public static interface Dealer {
        public String deal(String line);

        default void before(String line, List<String> lines) {

        }

        default void after(String line, List<String> lines) {

        }

        default void dealLines(List<String> lines) {

        }

    }

    public static FileLineDealer create(File file) {
        FileLineDealer dealer = new FileLineDealer();
        dealer.file = file;
        return dealer;
    }

    public static FileLineDealer create(String path) {
        return create(new File(path));
    }

    public void deal(Dealer dealer) {

        if (!file.exists()) {
            return;
        }

        ArrayList<String> lines = new ArrayList<>();
        FileReader.from(file).readLine(new ReadLine<String>() {
            @Override
            public void readLine(String s) {

                try {
                    dealer.before(s, lines);
                } catch (Exception e) {

                }

                try {
                    s = dealer.deal(s);
                } catch (Exception e) {
                }
                lines.add(s);
                try {
                    dealer.after(s, lines);
                } catch (Exception e) {

                }
            }
        });

        dealer.dealLines(lines);

        FileWriter.to(file).cover().write(lines);
    }
}
