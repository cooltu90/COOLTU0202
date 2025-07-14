package com.codingtu.cooltu.lib4a.tool;

import android.graphics.Bitmap;
import android.os.Environment;

import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4j.file.FileTool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SDCardTool extends FileTool {
    public static String getSDCard() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    public static boolean bitMapToFile(Bitmap originalBitmap, String tagetPath, int pressPercent) {
        return bitMapToFile(originalBitmap, new File(tagetPath), pressPercent);
    }

    public static boolean bitMapToFile(Bitmap originalBitmap, File targetFile, int pressPercent) {
        OutputStream bitMapOutputStream = null;
        boolean isSuccess = false;
        try {
            createParentDir(targetFile);
            bitMapOutputStream = new FileOutputStream(targetFile);
            originalBitmap.compress(Bitmap.CompressFormat.JPEG, pressPercent, bitMapOutputStream);
            isSuccess = true;
        } catch (Exception e) {
            Logs.e(e);
        } finally {
            if (bitMapOutputStream != null) {
                try {
                    bitMapOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            bitMapOutputStream = null;
            return isSuccess;
        }
    }
}
