package com.codingtu.cooltu.lib4a.image;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;

import com.codingtu.cooltu.lib4a.CoreRequestCode;
import com.codingtu.cooltu.lib4a.config.CoreConfigs;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4a.permission.PermissionBack;
import com.codingtu.cooltu.lib4a.permission.PermissionTool;
import com.codingtu.cooltu.lib4a.tool.ToastTool;
import com.codingtu.cooltu.lib4a.uicore.CoreActInterface;
import com.codingtu.cooltu.lib4a.uicore.OnActBack;

import java.io.File;

public final class ImageGetter implements PermissionBack, OnActBack {

    private ImageGetterBack back;
    private CoreActInterface act;
    private File outputImage;

    public static interface ImageGetterBack {
        public void imageBack(String image);
    }

    private ImageGetter() {
    }

    public static void getPicFromCamera(CoreActInterface act, ImageGetterBack back) {
        new ImageGetter().getPicByCamera(act, back);
    }

    public static void getPicFromGallery(CoreActInterface act, ImageGetterBack back) {
        new ImageGetter().getPicByGallery(act, back);
    }

    public static void getVideoFromCamera(CoreActInterface act, ImageGetterBack back) {
        new ImageGetter().getVideoByCamera(act, back);
    }

    public static void getVideoFromGallery(CoreActInterface act, ImageGetterBack back) {
        new ImageGetter().getVideoByGallery(act, back);
    }

    private void getVideoByCamera(CoreActInterface act, ImageGetterBack back) {
        from(act, back, CoreRequestCode.GET_VIDEO_BY_CAMERA);
    }

    private void getVideoByGallery(CoreActInterface act, ImageGetterBack back) {
        from(act, back, CoreRequestCode.GET_VIDEO_BY_GALLERY);
    }

    private void getPicByCamera(CoreActInterface act, ImageGetterBack back) {
        from(act, back, CoreRequestCode.GET_PIC_BY_CAMERA);
    }

    private void getPicByGallery(CoreActInterface act, ImageGetterBack back) {
        from(act, back, CoreRequestCode.GET_PIC_BY_GALLERY);
    }

    private void from(CoreActInterface act, ImageGetterBack back, int code) {
        this.act = act;
        this.back = back;
        act.getBase().addPermissionBack(this);
        act.getBase().addOnActBack(this);
        switch (code) {
            case CoreRequestCode.GET_PIC_BY_CAMERA:
                PermissionTool.check(act.getAct(), code, Manifest.permission.CAMERA);
                break;
            case CoreRequestCode.GET_VIDEO_BY_CAMERA:
                PermissionTool.check(act.getAct(), code, Manifest.permission.CAMERA);
                break;
            case CoreRequestCode.GET_PIC_BY_GALLERY:
                PermissionTool.check(act.getAct(), code, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                break;
            case CoreRequestCode.GET_VIDEO_BY_GALLERY:
                PermissionTool.check(act.getAct(), code, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                break;
        }

    }

    @Override
    public void permissionBack(int requestCode, String[] permissions, int[] grantResults) {
        if (PermissionTool.allow(grantResults)) {
            if (requestCode == CoreRequestCode.GET_PIC_BY_CAMERA) {
                getPicByCamera();
            } else if (requestCode == CoreRequestCode.GET_PIC_BY_GALLERY) {
                getPicByGallery();
            } else if (requestCode == CoreRequestCode.GET_VIDEO_BY_CAMERA) {
                getVideoByCamera();
            } else if (requestCode == CoreRequestCode.GET_VIDEO_BY_GALLERY) {
                getVideoByGallery();
            } else {
                clear();
            }
        } else {
            if (requestCode == CoreRequestCode.GET_PIC_BY_CAMERA) {
                ToastTool.toast("您禁用了拍照的权限，请恢复权限后再尝试");
            } else if (requestCode == CoreRequestCode.GET_PIC_BY_GALLERY) {
                ToastTool.toast("您禁用了读取照片权限，请恢复权限后再尝试");
            }
            imageBack(null);
            clear();
        }
    }

    private void getPicByGallery() {
        getByGallery("image/*", CoreRequestCode.GET_PIC_BY_GALLERY);
    }

    private void getVideoByGallery() {
        getByGallery("video/*", CoreRequestCode.GET_VIDEO_BY_GALLERY);
    }

    private void getByGallery(String type, int code) {
        Intent intentToPickPic = new Intent(Intent.ACTION_PICK, null);
        intentToPickPic.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, type);
        act.getAct().startActivityForResult(intentToPickPic, code);
    }

    private void getPicByCamera() {
        getByCamera(MediaStore.ACTION_IMAGE_CAPTURE, ".jpg", CoreRequestCode.GET_PIC_BY_CAMERA);
    }

    private void getVideoByCamera() {
        getByCamera(MediaStore.ACTION_VIDEO_CAPTURE, ".mp4", CoreRequestCode.GET_VIDEO_BY_CAMERA);
    }

    private void getByCamera(String type, String suffix, int code) {
        try {
            File dir = new File(
                    Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + CoreConfigs.configs()
                            .getPicDirName());
            if (!dir.exists()) {
                dir.mkdirs();
            }
            outputImage = new File(dir, System.currentTimeMillis() + suffix);
            //            outputImage = new File(App.APP.getExternalCacheDir(), System.currentTimeMillis() + ".jpg");
            if (!outputImage.exists()) {
                outputImage.createNewFile();
            }
            Intent intent = new Intent(type);
            if (Build.VERSION.SDK_INT >= 24) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider
                        .getUriForFile(act.getAct(), CoreConfigs.configs().getImageGetterFileProvider(), outputImage));
            } else {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outputImage));
            }
            act.getAct().startActivityForResult(intent, code);
        } catch (Exception e) {
            act = null;
            imageBack(null);
            Logs.w(e);
        }
    }


    private void clear() {
//        new Handler().post(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    if (ImageGetter.this.act != null) {
//                        ((CoreUiInterface) act).getBase().removePermissionBack(ImageGetter.this);
//                        ((CoreUiInterface) act).getBase().removeOnActBack(ImageGetter.this);
//                    }
//                    ImageGetter.this.act = null;
//                    ImageGetter.this.back = null;
//                } catch (Exception e) {
//
//                }
//            }
//        });

        try {
            if (ImageGetter.this.act != null) {
                ((CoreActInterface) act).getBase().removePermissionBack(ImageGetter.this);
                ((CoreActInterface) act).getBase().removeOnActBack(ImageGetter.this);
            }
            ImageGetter.this.act = null;
            ImageGetter.this.back = null;
        } catch (Exception e) {

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {

            switch (requestCode) {
                case CoreRequestCode.GET_PIC_BY_CAMERA:
                case CoreRequestCode.GET_VIDEO_BY_CAMERA:
                    cameraBack();
                    break;
                case CoreRequestCode.GET_PIC_BY_GALLERY:
                case CoreRequestCode.GET_VIDEO_BY_GALLERY:
                    galleryBack(data);
                    break;
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            imageBack(null);
            clear();
        }
    }

    private void cameraBack() {
        if (outputImage != null) {
            ImageTools.updateAlbum(outputImage);
            String absolutePath = outputImage.getAbsolutePath();
            imageBack(absolutePath);
        } else {
            imageBack(null);
        }
        clear();
    }

    private void galleryBack(Intent data) {
        String pic = null;
        if (Build.VERSION.SDK_INT >= 19) {
            pic = handleImageOnKitKat(data);
        } else {
            pic = handleImageBeforeKitKat(data);
        }

        imageBack(pic);
        clear();
    }

    private void imageBack(String pic) {
        if (back != null)
            back.imageBack(pic);
    }

    /********************************
     *
     * 获取相册选择后的照片（4.4以上版本)
     *
     ********************************/
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private String handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(act.getAct(), uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris
                        .withAppendedId(Uri.parse("content://downloads/public_downloads"),
                                Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            imagePath = uri.getPath();
        }
        return imagePath;
    }


    /********************************
     *
     * 获取相册选择后的照片（4.4之前版本)
     *
     ********************************/
    private String handleImageBeforeKitKat(Intent data) {
        return getImagePath(data.getData(), null);
    }

    /********************************
     *
     * 从uri中获得图片地址
     *
     ********************************/
    @SuppressLint("Range")
    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = act.getAct().getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

}
