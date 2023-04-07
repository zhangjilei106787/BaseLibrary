package base.zjl.com.baselibrary.login.util;

import android.content.Context;

import base.zjl.com.baselibrary.login.view.CustomDialog;

public class CustomDialogUtils {


    private static CustomDialogUtils instance;
    private CustomDialog customDialog;

    private CustomDialogUtils() {
    }

    public static CustomDialogUtils getInstance() {
        if (null == instance) {
            instance = new CustomDialogUtils();
        }
        return instance;
    }

    public void showDialog(Context context, String text, boolean isDiss) {
        customDialog = new CustomDialog(context, text, isDiss);
        customDialog.show();
    }

    public void dismissCusDialog() {
        if (customDialog != null) {
            customDialog.dismiss();
        }
    }
}
