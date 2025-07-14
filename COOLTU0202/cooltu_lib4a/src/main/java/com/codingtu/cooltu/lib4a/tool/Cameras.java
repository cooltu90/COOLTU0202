package com.codingtu.cooltu.lib4a.tool;

import android.annotation.SuppressLint;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.TextureView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Cameras {

    private static final SparseIntArray ORIENTATION = new SparseIntArray();

    static {
        ORIENTATION.append(Surface.ROTATION_0, 90);
        ORIENTATION.append(Surface.ROTATION_90, 0);
        ORIENTATION.append(Surface.ROTATION_180, 270);
        ORIENTATION.append(Surface.ROTATION_270, 180);
    }

    private HandlerThread mCameraThread;
    private Handler mCameraHandler;
    private TextureView mTextureView;
    private Size mPreviewSize;
    private String mCameraId;
    private CameraDevice mCameraDevice;
    private CameraCaptureSession mCameraCaptureSession;
    private boolean isFirstResume = true;
    private ImageReader mImageReader;
    private CaptureBack captureBack;
    private CaptureRequest.Builder mCaptureRequestBuilder;
    private CaptureRequest mCaptureRequest;
    private InitComplete initComplete;

    public static Cameras obtain() {
        return new Cameras();
    }

    public Cameras() {
        startThread();
    }

    /**********************************************
     *
     * 初始化设置
     *
     **********************************************/
    //开启相机线程
    private void startThread() {
        mCameraThread = new HandlerThread("CameraThread");
        mCameraThread.start();
        mCameraHandler = new Handler(mCameraThread.getLooper());
    }

    //设置预览界面
    public void setTextureView(TextureView mTextureView) {
        this.mTextureView = mTextureView;
        this.mTextureView.setSurfaceTextureListener(mTextureListener);
    }

    public static interface InitComplete {
        public void initComplete(int w, int h);
    }

    public void onInitComplete(InitComplete initComplete) {
        this.initComplete = initComplete;
    }

    //同步activity的onResume方法
    public void onResume() {
        if (isFirstResume) {
            //第一次resume，等待TextureView初始化
            isFirstResume = false;
        } else {
            if (mCameraDevice == null) {
                openCamera();
            }
        }
    }

    //同步activity的onPause方法
    public void onPause() {
        close();
    }

    public void onDestroy() {
        mTextureView = null;
        captureBack = null;
        initComplete = null;
        if (mImageReader != null) {
            mImageReader.close();
            mImageReader = null;
        }
        mCameraThread.quit();
    }

    //TextureView可用后的回调
    private TextureView.SurfaceTextureListener mTextureListener = new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            //初始化相机参数
            initCamera(width, height);
            //打开相机
            openCamera();
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surface) {

        }
    };

    //打开相机，此处不做权限判断，在需要的activity等做权限判断。
    @SuppressLint("MissingPermission")
    private void openCamera() {
        CameraManager manager = SystemTool.getCameraManager();
        try {
            manager.openCamera(mCameraId, mStateCallback, mCameraHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    //相机状态回调
    private CameraDevice.StateCallback mStateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(CameraDevice camera) {
            mCameraDevice = camera;
            //开启预览
            startPreview();
        }

        @Override
        public void onDisconnected(CameraDevice camera) {
            close();
        }

        @Override
        public void onError(CameraDevice camera, int error) {
            close();
        }
    };

    //关闭相机等操作
    private void close() {
        if (mCameraCaptureSession != null) {
            mCameraCaptureSession.close();
            mCameraCaptureSession = null;
        }

        if (mCameraDevice != null) {
            mCameraDevice.close();
            mCameraDevice = null;
        }

    }

    //初始化相机参数
    private void initCamera(int width, int height) {
        CameraManager manager = SystemTool.getCameraManager();
        try {
            //遍历所有摄像头
            for (String cameraId : manager.getCameraIdList()) {
                CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraId);
                Integer facing = characteristics.get(CameraCharacteristics.LENS_FACING);
                if (facing != null && facing == CameraCharacteristics.LENS_FACING_FRONT) continue;
                StreamConfigurationMap map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
                assert map != null;
                mPreviewSize = getOptimalSize(map.getOutputSizes(SurfaceTexture.class), width, height);
                setupImageReader();
                mCameraId = cameraId;
                break;
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    //找到最合适的预览尺寸
    private Size getOptimalSize(Size[] sizeMap, int width, int height) {
        List<Size> sizeList = new ArrayList<>();
        for (Size option : sizeMap) {
            if (width > height) {
                if (option.getWidth() > width && option.getHeight() > height) {
                    sizeList.add(option);
                }
            } else {
                if (option.getWidth() > height && option.getHeight() > width) {
                    sizeList.add(option);
                }
            }
        }
        if (sizeList.size() > 0) {
            return Collections.min(sizeList, new Comparator<Size>() {
                @Override
                public int compare(Size lhs, Size rhs) {
                    return Long.signum(lhs.getWidth() * lhs.getHeight() - rhs.getWidth() * rhs.getHeight());
                }
            });
        }
        return sizeMap[0];
    }

    /**********************************************
     *
     * 开启预览
     *
     **********************************************/
    private void startPreview() {
        //获取TextrueView的Surface
        SurfaceTexture mSurfaceTexture = mTextureView.getSurfaceTexture();
        mSurfaceTexture.setDefaultBufferSize(mPreviewSize.getWidth(), mPreviewSize.getHeight());
        Surface previewSurface = new Surface(mSurfaceTexture);
        try {
            mCaptureRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            mCaptureRequestBuilder.addTarget(previewSurface);
            mCaptureRequestBuilder.addTarget(mImageReader.getSurface());
            mCameraDevice.createCaptureSession(Arrays.asList(previewSurface, mImageReader.getSurface()), new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(@NonNull CameraCaptureSession session) {
                    try {
                        mCaptureRequest = mCaptureRequestBuilder.build();
                        mCameraCaptureSession = session;
                        mCameraCaptureSession.setRepeatingRequest(mCaptureRequest, null, mCameraHandler);

                        if (initComplete != null)
                            initComplete.initComplete(mPreviewSize.getWidth(), mPreviewSize.getHeight());
                    } catch (Exception e) {

                    }
                }

                @Override
                public void onConfigureFailed(@NonNull CameraCaptureSession session) {

                }
            }, mCameraHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    /**********************************************
     *
     * 设置捕捉
     *
     **********************************************/
    private void setupImageReader() {
        mImageReader = ImageReader.newInstance(mPreviewSize.getWidth(), mPreviewSize.getHeight(), ImageFormat.YUV_420_888, 2);
        mImageReader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() {
            @Override
            public void onImageAvailable(ImageReader reader) {
                Image image = reader.acquireNextImage();
                if (captureBack != null)
                    captureBack.back(image);
                image.close();
            }
        }, mCameraHandler);
    }

    public static interface CaptureBack {
        public void back(Image image);
    }

    public void captureOnce(CaptureBack back) {
        this.captureBack = back;
//        lockFocus();
    }

    private void lockFocus() {
        try {
            mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, CameraMetadata.CONTROL_AF_TRIGGER_START);
            mCameraCaptureSession.capture(mCaptureRequestBuilder.build(), mCaptureCallback, mCameraHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private CameraCaptureSession.CaptureCallback mCaptureCallback = new CameraCaptureSession.CaptureCallback() {

        @Override
        public void onCaptureProgressed(CameraCaptureSession session, CaptureRequest
                request, CaptureResult partialResult) {
        }

        @Override
        public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest
                request, TotalCaptureResult result) {
            capture();
        }
    };

    private void capture() {
        try {
            final CaptureRequest.Builder mCaptureBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
            int rotation = SystemTool.getWindowManager().getDefaultDisplay().getRotation();
            mCaptureBuilder.addTarget(mImageReader.getSurface());
            mCaptureBuilder.set(CaptureRequest.JPEG_ORIENTATION, ORIENTATION.get(rotation));
            CameraCaptureSession.CaptureCallback CaptureCallback = new CameraCaptureSession.CaptureCallback() {
                @Override
                public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
                    unLockFocus();
                }
            };
            mCameraCaptureSession.stopRepeating();
            mCameraCaptureSession.capture(mCaptureBuilder.build(), CaptureCallback, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void unLockFocus() {
        try {
            mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, CameraMetadata.CONTROL_AF_TRIGGER_CANCEL);
            mCameraCaptureSession.setRepeatingRequest(mCaptureRequest, null, mCameraHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }


}
