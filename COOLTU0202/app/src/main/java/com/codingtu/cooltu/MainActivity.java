package com.codingtu.cooltu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.codingtu.cooltu.config.Configs;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4j.config.LibConfigs;
import com.codingtu.cooltu.lib4j.tool.IntTool;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String format = IntTool.format(null, 2);


    }
}