package com.codingtu.cooltu.lib4j.zip;

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

public class Zip1 {


    public static class Zip11 implements OnDestroy {
        private Map<String, String> pathMap;
        private ZipFile zipFile;

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

        public Zip11(Map<String, String> pathMap) {
            this.pathMap = pathMap;
        }


        public Zip11 zipFile(ZipFile zipFile) {
            this.zipFile = zipFile;
            return this;
        }


        public Zip11 zipFile(String path) {
            this.zipFile = new ZipFile(path);
            return this;
        }


        public Zip11 zipFile(File path) {
            this.zipFile = new ZipFile(path);
            return this;
        }

        public Zip11 progress(OnCallBack.P1<Progress> onProgress) {
            this.onProgress = onProgress;
            return this;
        }

        public Zip11 cacheSize(int cacheSize) {
            this.cacheSize = cacheSize;
            return this;
        }

        public Zip11 error(OnCallBack.P1<Throwable> onError) {
            this.onError = onError;
            return this;
        }

        public Zip11 finish(OnCallBack.P1<File> onFinish) {
            this.onFinish = onFinish;
            return this;
        }

        public Zip11 start(OnCallBack.P0 onStart) {
            this.onStart = onStart;
            return this;
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

    public static class Zip22 implements OnDestroy {
        private File srcFile;
        private ZipFile zipFile;

        @Override
        public void onDestroy() {
            this.srcFile = null;
            this.zipFile = null;
        }

        public Zip22(File srcFile) {
            this.srcFile = srcFile;
        }

        public Zip22(String srcPath) {
            this(new File(srcPath));
        }

        public Zip22 dest(String destPath) {
            this.zipFile = new ZipFile(destPath);
            return this;
        }

        public Zip22 dest(File destFile) {
            this.zipFile = new ZipFile(destFile);
            return this;
        }

        public Zip22 dest(ZipFile zipFile) {
            this.zipFile = zipFile;
            return this;
        }

    }

    public static Zip11 map(Map<String, String> pathMap) {
        return new Zip11(pathMap);
    }


    public static Zip22 src(String srcPath) {
        return new Zip22(srcPath);
    }
}
