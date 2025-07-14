package com.codingtu.cooltu.lib4a.tool;

import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4j.callback.OnCallBack;
import com.codingtu.cooltu.lib4j.destory.OnDestroy;

import com.codingtu.cooltu.lib4j.tool.StringTool;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.PostRequest;

import java.io.File;

public class Upload implements OnDestroy {

    /**************************************************
     *
     *
     *
     **************************************************/

    private PostRequest<String> post;

    private File file;
    private String fileKey;

    private OnCallBack.P1<String> onFinish;
    private OnCallBack.P1<Throwable> onError;
    private OnCallBack.P1<com.codingtu.cooltu.lib4j.data.progress.Progress> onProgress;
    private OnCallBack.P0 onStart;
    private boolean sync;

    @Override
    public void onDestroy() {
        onFinish = null;
        onError = null;
        onProgress = null;
        onStart = null;
        file = null;
        post = null;
    }


    Upload(String url) {
        if (StringTool.isBlank(url)) {
            onError(new RuntimeException("链接地址不能未空"));
            return;
        }

        post = OkGo.<String>post(url);
        post.tag(this).isMultipart(true);
    }

    public static Upload url(String url) {
        return new Upload(url);
    }

    public Upload header(String key, String value) {
        post.headers(key, value);
        return this;
    }

    public Upload file(String key, File file) {
        this.fileKey = key;
        this.file = file;
        return this;
    }

    public Upload file(String key, String filePath) {
        return file(key, new File(filePath));
    }

    public Upload param(String key, String value) {
        post.params(key, value);
        return this;
    }

    public Upload error(OnCallBack.P1<Throwable> onError) {
        this.onError = onError;
        return this;
    }

    public Upload finish(OnCallBack.P1<String> onFinish) {
        this.onFinish = onFinish;
        return this;
    }

    public Upload progress(OnCallBack.P1<com.codingtu.cooltu.lib4j.data.progress.Progress> onProgress) {
        this.onProgress = onProgress;
        return this;
    }

    public Upload start(OnCallBack.P0 onStart) {
        this.onStart = onStart;
        return this;
    }

    public Upload sync() {
        this.sync = true;
        return this;
    }

    public void upload() {
        if (post == null) {
            return;
        }

        if (!file.exists()) {
            onError(new RuntimeException("上传文件不存在"));
            return;
        }

        if (onStart != null) {
            onStart.onCallBack();
        }

        post.params(fileKey, file);
        if (sync) {
            String responseResult = null;
            try {
                post.setCallback(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                    }

                    @Override
                    public void uploadProgress(Progress progress) {
                        super.uploadProgress(progress);
                        if (onProgress != null) {
                            onProgress.onCallBack(new com.codingtu.cooltu.lib4j.data.progress.Progress(progress.totalSize, progress.currentSize));
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Logs.i("post error");
                        Upload.this.onError(response.getException());
                    }
                });
                okhttp3.Response response = post.execute();
                responseResult = response.body().string();
                try {
                    response.close();
                } catch (Exception e) {
                }
            } catch (Exception e) {
                Logs.i("response error");
                onError(e);
            } finally {

            }
            if (onFinish != null) {
                onFinish.onCallBack(responseResult);
            }
            onDestroy();
            return;
        }

        post.execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if (onFinish != null) {
                    onFinish.onCallBack(response.body());
                }
                onDestroy();
            }

            @Override
            public void uploadProgress(Progress progress) {
                super.uploadProgress(progress);
                if (onProgress != null) {
                    onProgress.onCallBack(new com.codingtu.cooltu.lib4j.data.progress.Progress(progress.totalSize, progress.currentSize));
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Upload.this.onError(response.getException());
            }
        });
    }


    private void onError(Throwable throwable) {
        if (onError != null) {
            onError.onCallBack(throwable);
        }
        onDestroy();
    }

}
