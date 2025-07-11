package com.codingtu.cooltu.lib4j.zip;

import com.codingtu.cooltu.lib4j.callback.OnCallBack;
import com.codingtu.cooltu.lib4j.data.progress.Progress;
import com.codingtu.cooltu.lib4j.data.value.TValue;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.exception.ZipFailException;
import com.codingtu.cooltu.lib4j.file.FileTool;
import com.codingtu.cooltu.lib4j.log.LibLogs;
import com.codingtu.cooltu.lib4j.path.ZipFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipTool {

    private static void zip(ZipFile pathZipFile, long totalLength, Map<String, String> pathMap, OnCallBack.P1<Progress> onZipProgress) {

        FileTool.createParentDir(pathZipFile.rootFile());

        TValue<ZipOutputStream> out = TValue.obtain();
        try {
            out.value = new ZipOutputStream(new FileOutputStream(pathZipFile.rootFile()));

            TValue<Long> zipedLen = TValue.obtain(0l);
            TValue<Long> lastTime = TValue.obtain(System.currentTimeMillis());

            Es.maps(pathMap).ls(new Es.MapEach<String, String>() {
                @Override
                public boolean each(String oriPath, String zipPath) {
                    FileInputStream input = null;
                    try {
                        input = new FileInputStream(new File(oriPath));
                        out.value.putNextEntry(new ZipEntry(zipPath));

                        byte[] buff = new byte[1024 * 512];
                        int len = 0;

                        while ((len = input.read(buff)) != -1) {
                            out.value.write(buff, 0, len);
                            zipedLen.value += len;

                            long nowTime = System.currentTimeMillis();
                            if (nowTime - lastTime.value > 100) {
                                if (zipedLen.value < totalLength) {
                                    if (onZipProgress != null)
                                        onZipProgress.onCallBack(new Progress(totalLength, zipedLen.value));
                                }
                                lastTime.value = nowTime;
                            }

                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    } finally {
                        if (input != null) {
                            try {
                                input.close();
                            } catch (Exception e) {
                                LibLogs.e(e);
                            }
                        }
                        input = null;
                    }

                    return false;
                }
            });

            if (onZipProgress != null)
                onZipProgress.onCallBack(new Progress(totalLength, zipedLen.value));


        } catch (Exception e) {
            LibLogs.e(e);
            throw new ZipFailException("压缩打包失败:" + e.getMessage());
        } finally {
            if (out != null) {
                if (out.value != null) {
                    try {
                        out.value.close();
                    } catch (Exception e) {
                        LibLogs.e(e);
                    }
                    out.value = null;
                }
            }
        }
    }

}
