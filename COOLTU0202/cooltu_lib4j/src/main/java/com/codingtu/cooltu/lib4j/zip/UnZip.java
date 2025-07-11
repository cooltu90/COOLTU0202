package com.codingtu.cooltu.lib4j.zip;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.lib4j.callback.OnCallBack;
import com.codingtu.cooltu.lib4j.callback.PathDeal;
import com.codingtu.cooltu.lib4j.data.progress.Progress;
import com.codingtu.cooltu.lib4j.destory.OnDestroy;
import com.codingtu.cooltu.lib4j.file.FileTool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class UnZip implements OnDestroy {

    private File src;
    private File destDir;
    private Integer cacheSize;
    private long totalLen;

    private OnCallBack.P1<Throwable> onError;
    private OnCallBack.P1<Long> onFinish;
    private OnCallBack.P1<Progress> onProgress;
    private OnCallBack.P0 onStart;

    private PathDeal zipedNameDeal;

    UnZip() {
    }

    @Override
    public void onDestroy() {
        onError = null;
        onFinish = null;
        onProgress = null;
        onStart = null;
        zipedNameDeal = null;
    }

    public static UnZip src(String srcPath) {
        return src(new File(srcPath));
    }

    public static UnZip src(File src) {
        UnZip unZip = new UnZip();
        unZip.src = src;
        return unZip;
    }

    public UnZip destDir(String destDirPath) {
        return destDir(new File(destDirPath));
    }

    public UnZip destDir(File destDir) {
        this.destDir = destDir;
        return this;
    }

    public UnZip error(OnCallBack.P1<Throwable> onError) {
        this.onError = onError;
        return this;
    }

    public UnZip finish(OnCallBack.P1<Long> onFinish) {
        this.onFinish = onFinish;
        return this;
    }

    public UnZip progress(OnCallBack.P1<Progress> onProgress) {
        this.onProgress = onProgress;
        return this;
    }

    public UnZip cacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
        return this;
    }


    public UnZip start(OnCallBack.P0 onStart) {
        this.onStart = onStart;
        return this;
    }


    public UnZip zipedNameDeal(PathDeal zipedNameDeal) {
        this.zipedNameDeal = zipedNameDeal;
        return this;
    }

    public void unzip() {
        if (!src.exists()) {
            onError(new RuntimeException("没有找到需要解压的文件"));
            return;
        }

        if (destDir == null) {
            destDir = new File(src.getParentFile().getAbsolutePath());
        }

        if (!destDir.exists()) {
            destDir.mkdirs();
        }

        if (cacheSize == null) {
            cacheSize = 1024 * 512;
        }

        if (onStart != null) {
            onStart.onCallBack();
        }

        InputStream in = null;
        OutputStream out = null;
        try {
            ZipFile zip = new ZipFile(src);

            for (Enumeration entries = zip.entries(); entries.hasMoreElements(); ) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                totalLen += entry.getSize();
            }

            String destDirPath = destDir.getAbsolutePath();
            onProgress(0);

            long currentSize = 0;
            long lastTime = System.currentTimeMillis();
            for (Enumeration entries = zip.entries(); entries.hasMoreElements(); ) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                if (entry.getSize() <= 0) {
                    continue;
                }
                in = zip.getInputStream(entry);
                String zipEntryName = entry.getName();
                if (zipedNameDeal != null) {
                    zipEntryName = zipedNameDeal.deal(zipEntryName);
                }
                //解决路径不兼容的问题
                String outPath = (destDirPath + FileTool.SEPARATOR + zipEntryName).replace('\\', '/');
                File outFile = new File(outPath);
                FileTool.createParentDir(outFile);
                out = new FileOutputStream(outFile);
                byte[] buf1 = new byte[cacheSize];
                int len;
                while ((len = in.read(buf1)) > 0) {
                    currentSize += len;
                    out.write(buf1, 0, len);
                    long nowTime = System.currentTimeMillis();
                    if (nowTime - lastTime > 100) {
                        if (currentSize < totalLen) {
                            onProgress(currentSize);
                        }
                        lastTime = nowTime;
                    }
                }
                in.close();
                out.close();
                in = null;
                out = null;
            }
        } catch (Exception e) {
            onError(e);
            return;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        onProgress(totalLen);
        if (onFinish != null) {
            onFinish.onCallBack(totalLen);
        }

    }

    private void onProgress(long currentLen) {
        if (onProgress != null) {
            onProgress.onCallBack(new Progress(totalLen, currentLen));
        }
    }

    private void onError(Throwable throwable) {
        if (onError != null) {
            onError.onCallBack(throwable);
        }
        onDestroy();
    }

}