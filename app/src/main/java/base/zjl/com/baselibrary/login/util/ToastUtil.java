package base.zjl.com.baselibrary.login.util;

import android.text.TextUtils;
import android.widget.Toast;

import base.zjl.com.baselibrary.login.MyApplication;


/**
 * Toast工具类
 */
public class ToastUtil {
    public static void MyApplicationst(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
    public static void MyApplicationst(int resourse) {
        if (resourse == 0) {
            return;
        }
        Toast.makeText(MyApplication.getContext(), resourse, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(String message) {
        Toast.makeText(MyApplication.getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
