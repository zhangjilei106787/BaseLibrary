/*
 * Copyright 2020. Huawei Technologies Co., Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package base.zjl.com.baselibrary.login.scan;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.huawei.hms.hmsscankit.ScanUtil;
import com.huawei.hms.ml.scan.HmsScan;
import com.huawei.hms.ml.scan.HmsScanAnalyzerOptions;

import java.io.IOException;
import java.util.Objects;

import base.zjl.com.baselibrary.R;

import static base.zjl.com.baselibrary.login.scan.Contants.BITMAP_CODE;


public class CaptureCodeActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_PHOTO = 0X1113;
    private static final String TAG = "CommonActivity";
    private int defaultValue = BITMAP_CODE;
    private SurfaceHolder surfaceHolder;
    private CameraOperation cameraOperation;
    private SurfaceCallBack surfaceCallBack;
    private CommonHandler handler;
    private boolean isShow;
    private int mode;
    private ImageView backBtn;
    private ImageView imgBtn;
    private ImageView mscanArs;
    public static final String SCAN_RESULT = "scanResult";

    public ScanResultView scanResultView;
    private CheckBox cb_flash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        initStausBar();
        mode = getIntent().getIntExtra(Contants.DECODE_MODE, defaultValue);
        mscanArs = findViewById(R.id.scan_ars);
        cameraOperation = new CameraOperation();
        surfaceCallBack = new SurfaceCallBack();
        SurfaceView cameraPreview = findViewById(R.id.surfaceView);
        adjustSurface(cameraPreview);
        surfaceHolder = cameraPreview.getHolder();
        isShow = false;
        setBackOperation();
        scanResultView = findViewById(R.id.scan_result_view);
        cb_flash = (CheckBox) findViewById(R.id.cb_flash);
        cb_flash.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(TAG, "jeek zxing isChecked: " + isChecked);
                if (cameraOperation == null) {
                    return;
                }
                if (cameraOperation.getCamera() == null) {
                    cb_flash.setChecked(!isChecked);
                    return;
                }
                Camera.Parameters cameraParams = null;
                try {
                    cameraParams = cameraOperation.getCamera().getParameters();
                } catch (Exception e) {

                }
                if (cameraParams == null) {
                    return;
                }

                try {
                    if (isChecked) {
                        if (cameraOperation.getCamera() != null) {
                            cameraParams.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                            cameraOperation.getCamera().setParameters(cameraParams);
                        }
                    } else {
                        if (cameraOperation.getCamera() != null) {

                            cameraParams.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                            cameraOperation.getCamera().setParameters(cameraParams);
                        }
                    }
                } catch (Exception e) {
                    try {
                        cameraParams.setPreviewSize(1920, 1080);
                        cameraOperation.getCamera().setParameters(cameraParams);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                        try {
                            cameraParams.setPictureSize(1920, 1080);
                            cameraOperation.getCamera().setParameters(cameraParams);
                        } catch (Exception ignored) {
                        }
                    }
                }
            }
        });

    }

    public boolean hasKitKat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    public boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    protected void initStausBar() {
        if (hasKitKat() && !hasLollipop()) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else if (hasLollipop()) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#00ffffff"));
        }

    }

    private void adjustSurface(SurfaceView cameraPreview) {
        FrameLayout.LayoutParams paramSurface = (FrameLayout.LayoutParams) cameraPreview.getLayoutParams();
        if (getSystemService(Context.WINDOW_SERVICE) != null) {
            WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
            Display defaultDisplay = windowManager.getDefaultDisplay();
            Point outPoint = new Point();
            defaultDisplay.getRealSize(outPoint);
            float sceenWidth = outPoint.x;
            float sceenHeight = outPoint.y;
            float rate;
            if (sceenWidth / (float) 1080 > sceenHeight / (float) 1920) {
                rate = sceenWidth / (float) 1080;
                int targetHeight = (int) (1920 * rate);
                paramSurface.width = FrameLayout.LayoutParams.MATCH_PARENT;
                paramSurface.height = targetHeight;
                int topMargin = (int) (-(targetHeight - sceenHeight) / 2);
                if (topMargin < 0) {
                    paramSurface.topMargin = topMargin;
                }
            } else {
                rate = sceenHeight / (float) 1920;
                int targetWidth = (int) (1080 * rate);
                paramSurface.width = targetWidth;
                paramSurface.height = FrameLayout.LayoutParams.MATCH_PARENT;
                int leftMargin = (int) (-(targetWidth - sceenWidth) / 2);
                if (leftMargin < 0) {
                    paramSurface.leftMargin = leftMargin;
                }
            }
        }
    }

    private void setBackOperation() {
        backBtn = findViewById(R.id.iv_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mode == Contants.MULTIPROCESSOR_ASYN_CODE || mode == Contants.MULTIPROCESSOR_SYN_CODE) {
                    setResult(RESULT_CANCELED);
                }
                CaptureCodeActivity.this.finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mode == Contants.MULTIPROCESSOR_ASYN_CODE || mode == Contants.MULTIPROCESSOR_SYN_CODE) {
            setResult(RESULT_CANCELED);
        }
        CaptureCodeActivity.this.finish();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (isShow) {
            initCamera();
        } else {
            surfaceHolder.addCallback(surfaceCallBack);
        }
    }

    @Override
    protected void onPause() {
        if (handler != null) {
            handler.quit();
            handler = null;
        }
        cameraOperation.close();
        if (!isShow) {
            surfaceHolder.removeCallback(surfaceCallBack);
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initCamera() {
        try {
            cameraOperation.open(surfaceHolder);
            if (handler == null) {
                handler = new CommonHandler(CaptureCodeActivity.this, cameraOperation, mode);
            }
        } catch (IOException e) {
            Log.w(TAG, e);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK || data == null || requestCode != REQUEST_CODE_PHOTO) {
            return;
        }
        try {
            // Image-based scanning mode
            if (mode == BITMAP_CODE) {
                decodeBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData()), HmsScan.ALL_SCAN_TYPE);
            }
        } catch (Exception e) {
            Log.e(TAG, Objects.requireNonNull(e.getMessage()));
        }
    }

    //    /**
//     * 返回页面添加
//     * @param requestCode
//     * @param resultCode
//     * @param data
//     */
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            Parcelable[] obj = data.getParcelableArrayExtra(CaptureCodeActivity.SCAN_RESULT);
//            if (obj != null && obj.length > 0) {
//                if (obj.length == 1) {
//                    if (obj[0] != null && !TextUtils.isEmpty(((HmsScan) obj[0]).getOriginalValue())) {
//                        String result = ((HmsScan) obj[0]).getOriginalValue();
//
//                    }
//                }
//            } else {
//                ToastUtils.showLong(getResources().getString(R.string.barcode_error));
//            }
//
//        }
//    }
    private void decodeBitmap(Bitmap bitmap, int scanType) {
        HmsScan[] hmsScans = ScanUtil.decodeWithBitmap(CaptureCodeActivity.this, bitmap, new HmsScanAnalyzerOptions.Creator().setHmsScanTypes(scanType).setPhotoMode(true).create());
        if (hmsScans != null && hmsScans.length > 0 && hmsScans[0] != null && !TextUtils.isEmpty(hmsScans[0].getOriginalValue())) {
            Intent intent = new Intent();
            intent.putExtra(SCAN_RESULT, hmsScans);
            setResult(RESULT_OK, intent);
            CaptureCodeActivity.this.finish();
        }
    }

    class SurfaceCallBack implements SurfaceHolder.Callback {

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            if (!isShow) {
                isShow = true;
                initCamera();
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            isShow = false;
        }
    }
}
