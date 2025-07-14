package com.codingtu.cooltu.lib4j.zip.zip;

import com.codingtu.cooltu.lib4j.callback.OnCallBack;
import com.codingtu.cooltu.lib4j.data.progress.Progress;
import com.codingtu.cooltu.lib4j.data.value.TValue;
import com.codingtu.cooltu.lib4j.destory.OnDestroy;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.es.map.BaseMap;
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

public class ZipBase<THIS extends ZipBase> implements OnDestroy {

    protected Map<String, String> pathMap;
    protected ZipFile zipFile;
    private OnCallBack.P1<Progress> onProgress;
    private OnCallBack.P1<Throwable> onError;
    private OnCallBack.P1<File> onFinish;
    private OnCallBack.P0 onStart;
    private Integer cacheSize;
    private long totalLen;
    private long zipedLen;

    @Override
    public void onDestroy() {
        pathMap = null;
        zipFile = null;
        onProgress = null;
        onError = null;
        onFinish = null;
        onStart = null;
    }

    public THIS zipFile(ZipFile zipFile) {
        this.zipFile = zipFile;
        return (THIS) this;
    }


    public THIS zipFile(String path) {
        this.zipFile = new ZipFile(path);
        return (THIS) this;
    }

    public THIS zipFile(File zipFile) {
        this.zipFile = new ZipFile(zipFile);
        return (THIS) this;
    }

    public THIS progress(OnCallBack.P1<Progress> onProgress) {
        this.onProgress = onProgress;
        return (THIS) this;
    }

    public THIS error(OnCallBack.P1<Throwable> onError) {
        this.onError = onError;
        return (THIS) this;
    }

    public THIS finish(OnCallBack.P1<File> onFinish) {
        this.onFinish = onFinish;
        return (THIS) this;
    }

    public THIS start(OnCallBack.P0 onStart) {
        this.onStart = onStart;
        return (THIS) this;
    }

    public THIS cacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
        return (THIS) this;
    }

    public void zip() {
        if (cacheSize == null) {
            cacheSize = 1024 * 512;
        }

        //计算totalLen
        BaseMap<String, String> maps = Es.maps(pathMap);
        maps.ls(new Es.MapEach<String, String>() {
            @Override
            public boolean each(String oriPath, String zipPath) {
                totalLen += new File(oriPath).length();
                return false;
            }
        });

        if (zipFile == null) {
            throw new ZipFailException("未指定压缩包地址");
        }

        if (this.onStart != null) {
            this.onStart.onCallBack();
        }

        FileTool.createParentDir(zipFile.rootFile());

        TValue<ZipOutputStream> out = TValue.obtain();
        try {
            out.value = new ZipOutputStream(new FileOutputStream(zipFile.rootFile()));

            TValue<Long> lastTime = TValue.obtain(System.currentTimeMillis());

            maps.ls(new Es.MapEach<String, String>() {
                @Override
                public boolean each(String oriPath, String zipPath) {
                    FileInputStream input = null;
                    try {
                        input = new FileInputStream(new File(oriPath));
                        out.value.putNextEntry(new ZipEntry(zipPath));

                        byte[] buff = new byte[cacheSize];
                        int len = 0;

                        while ((len = input.read(buff)) != -1) {
                            out.value.write(buff, 0, len);
                            zipedLen += len;

                            long nowTime = System.currentTimeMillis();
                            if (nowTime - lastTime.value > 100) {
                                if (zipedLen < totalLen) {
                                    if (onProgress != null)
                                        onProgress.onCallBack(new Progress(totalLen, zipedLen));
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

            if (onProgress != null)
                onProgress.onCallBack(new Progress(totalLen, zipedLen));

            if (this.onFinish != null) {
                this.onFinish.onCallBack(zipFile.rootFile());
                onDestroy();
            }

        } catch (Exception e) {
            LibLogs.e(e);
            if (onError != null) {
                onError.onCallBack(e);
            }
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
