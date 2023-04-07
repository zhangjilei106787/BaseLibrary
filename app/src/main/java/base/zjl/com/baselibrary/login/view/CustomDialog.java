package base.zjl.com.baselibrary.login.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;

import base.zjl.com.baselibrary.R;


/**
 * 加载提醒对话框
 */
public class CustomDialog extends ProgressDialog {

    private boolean isdisMiss;
    private TextView textView;
    private String title;
    private boolean isShow;

    public CustomDialog(Context context, String title, boolean isDismiss) {
        super(context, R.style.CusDialog);
        this.title = title;
        this.isdisMiss = isDismiss;
    }

    public CustomDialog(Context context, String title) {
        super(context, R.style.CusDialog);
        this.title = title;
    }

    public CustomDialog(Context context) {
        super(context);
    }

    public CustomDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init(getContext());
    }

    private void init(Context context) {
        setCancelable(isdisMiss);
        setCanceledOnTouchOutside(isdisMiss);
        setContentView(R.layout.load_dialog);
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                isShow = false;
            }
        });
        textView = (TextView) findViewById(R.id.tv_load_dialog);
        textView.setText(title);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.CENTER;
        getWindow().setAttributes(params);
    }

    public void setTextView(String text) {
        if (textView != null) {
            textView.setText(text);
        }
    }

    public boolean isShow() {
        return isShow;
    }

    @Override
    public void show() {
        super.show();
        isShow = true;
    }
}