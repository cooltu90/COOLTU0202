package com.codingtu.cooltu.lib4a.image;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4a.config.CoreApp;
import com.codingtu.cooltu.lib4a.config.CoreConfigs;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4j.tool.StringTool;

import java.io.File;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ImageTools {

    private static int getDefaultId() {
        Integer defaultIcon = CoreConfigs.configs().getDefaultIcon();
        return defaultIcon == null ? R.mipmap.default_img : defaultIcon;
    }


    public static void updateAlbum(File file) {
        if (file == null)
            file = Environment.getExternalStorageDirectory();
        CoreApp.APP.sendBroadcast(
                new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
    }

    public static void setImage(ImageView iv, String url) {
        setImage(iv, url, getDefaultId());
    }

    public static void setImage(ImageView iv, String url, int defaultId) {
        File file = new File(url);
        if (file.exists()) {
            RequestOptions opts = new RequestOptions();
            opts.diskCacheStrategy(DiskCacheStrategy.NONE);
            opts.error(defaultId);
            Glide
                    .with(CoreApp.APP)
                    .load(file).apply(opts)
                    .into(iv);
        } else {
            Glide
                    .with(CoreApp.APP)
                    .load(url).apply(RequestOptions.errorOf(defaultId))
                    .into(iv);
        }
    }

    public static void getImage(String url, final ImageBitmapGetter getter) {

        if (StringTool.isBlank(url)) {
            getter.callBack(null);
            return;
        }

        Flowable
                .just(url)
                .map(new Function<String, Bitmap>() {
                    @SuppressLint("CheckResult")
                    @Override
                    public Bitmap apply(String s) throws Exception {
                        File file = new File(s);
                        if (file.exists()) {
                            RequestOptions opts = new RequestOptions();
                            opts.diskCacheStrategy(DiskCacheStrategy.NONE);
                            return Glide.with(CoreApp.APP).asBitmap().load(file).apply(opts).submit().get();
                        } else {
                            return Glide.with(CoreApp.APP).asBitmap().load(s).submit().get();
                        }
                    }
                })
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Bitmap>() {
                    @Override
                    public void accept(Bitmap bitmap) {
                        try {
                            getter.callBack(bitmap);
                        } catch (Exception e) {
                            Logs.w(e);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        Logs.w(throwable);
                        try {
                            getter.callBack(null);
                        } catch (Exception e) {
                            Logs.w(e);
                        }
                    }
                });
    }

    public static interface ImageBitmapGetter {
        public void callBack(Bitmap bitmap);
    }
}
