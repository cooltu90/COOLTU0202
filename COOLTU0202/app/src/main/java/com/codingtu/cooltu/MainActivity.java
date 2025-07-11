package com.codingtu.cooltu;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.codingtu.cooltu.lib4j.callback.FilePass;
import com.codingtu.cooltu.lib4j.callback.OnCallBack;
import com.codingtu.cooltu.lib4j.callback.PathDeal;
import com.codingtu.cooltu.lib4j.data.progress.Progress;
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

        Map<String, String> pathMap = new HashMap<>();

        ZipFile pathZipFile = new ZipFile("");

        Zip.map(pathMap).zipFile(pathZipFile)
                .progress(new OnCallBack.P1<Progress>() {
                    @Override
                    public void onCallBack(Progress progress) {

                    }
                })
                .error(new OnCallBack.P1<Throwable>() {
                    @Override
                    public void onCallBack(Throwable throwable) {

                    }
                })
                .finish(new OnCallBack.P1<File>() {
                    @Override
                    public void onCallBack(File file) {

                    }
                }).zip();

        Zip.src("").zipFile("")
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
                .progress(new OnCallBack.P1<Progress>() {
                    @Override
                    public void onCallBack(Progress progress) {

                    }
                }).start(new OnCallBack.P0() {
                    @Override
                    public void onCallBack() {

                    }
                })
                .finish(new OnCallBack.P1<File>() {
                    @Override
                    public void onCallBack(File file) {

                    }
                }).zip();


    }
}