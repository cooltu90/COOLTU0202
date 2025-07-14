package com.codingtu.cooltu;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.lib4a.callback.OnCallBackInUiThread;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4a.tool.SDCardTool;
import com.codingtu.cooltu.lib4j.callback.FilePass;
import com.codingtu.cooltu.lib4j.callback.OnCallBack;
import com.codingtu.cooltu.lib4j.callback.PathDeal;
import com.codingtu.cooltu.lib4j.data.progress.Progress;
import com.codingtu.cooltu.lib4j.file.FileTool;
import com.codingtu.cooltu.lib4j.path.ZipFile;
import com.codingtu.cooltu.lib4j.zip.zip.Zip;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        task();
    }

    private void task() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                taskReal();
            }
        }).start();
    }

    private void taskReal() {

        Zip.src(SDCardTool.getSDCard() + FileTool.addPrexSeparator("testvideo") + FileTool.addPrexSeparator("2"))
                //.zipFile(SDCardTool.getSDCard() + FileTool.SEPARATOR + "testvideo" + FileTool.SEPARATOR + "3.zip")
                .pathDeal(new PathDeal() {
                    @Override
                    public String deal(String path) {
                        return path;
                    }
                })
                .filePass(new FilePass() {
                    @Override
                    public boolean isPass(File file) {
                        return false;
                    }
                })
                .progress(new OnCallBackInUiThread.P1<Progress>() {
                    @Override
                    protected void callBack(Progress progress) {
                        Logs.i("thread:" + Thread.currentThread().getName());
                        Logs.i(progress.toJson());
                    }
                }).start(new OnCallBack.P0() {
                    @Override
                    public void onCallBack() {

                    }
                })
                .finish(new OnCallBack.P1<File>() {
                    @Override
                    public void onCallBack(File file) {
                        Logs.i("file:" + file.getAbsolutePath());
                    }
                }).zip();

    }
}