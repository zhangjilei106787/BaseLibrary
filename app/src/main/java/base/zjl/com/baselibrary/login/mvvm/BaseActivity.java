package base.zjl.com.baselibrary.login.mvvm;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;

import com.company.baselibrary.R;

import base.zjl.com.baselibrary.login.bean.MessageEvent;
import base.zjl.com.baselibrary.login.observer.ActivityConfigurationObserver;

import com.company.baselibrary.utils.common.ScreenUtils;
import com.company.baselibrary.views.toolbar.CustomToolbar;
import com.gyf.immersionbar.ImmersionBar;
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public abstract class BaseActivity extends RxAppCompatActivity implements View.OnClickListener {

    private ProgressDialog mProgressDialog;
    public ImmersionBar mMImmersionBar;
    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeContentView();
        mMImmersionBar = ImmersionBar.with(this);
        mMImmersionBar.fitsSystemWindows(true).statusBarDarkFont(true).statusBarColor(R.color.colorPrimary).init();
        layoutViewBinding();
        getLifecycle().addObserver(new ActivityConfigurationObserver(this));
        createHandler();
        initView();
        initEvent();
        initData();
    }

    private void createHandler() {
        handler = new Handler(getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                handleMsg(msg);
            }
        };
    }

    public abstract void handleMsg(Message msg);

    public abstract void initView();

    public abstract void initEvent();

    public abstract void initData();

    public abstract void layoutViewBinding();


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void EventbusMessage(MessageEvent event) {
        handBusMessage(event);
    }


    public abstract void handBusMessage(MessageEvent event);

    /**
     * 初始化沉浸式
     */
    public void initImmersionBar(int color) {
        mMImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).statusBarDarkFont(true).statusBarColor(color).init();
    }


    /**
     * 动态改变
     */
    protected void beforeContentView() {
        if (ScreenUtils.isPortrait()) {
            ScreenUtils.adaptScreen4VerticalSlide(this, 360);
        } else {
            ScreenUtils.adaptScreen4HorizontalSlide(this, 360);
        }
    }

    @Override
    public void onClick(View view) {
        viewOnClick(view);
    }

    public abstract void viewOnClick(View view);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mProgressDialog != null) {
            mProgressDialog.cancel();
            mProgressDialog = null;
        }
    }

    public void showLoadingDialog() {
        showLoadingDialog("", false);
    }

    public void showLoadingDialog(String message, boolean cancelable) {
        if (null == mProgressDialog) {
            mProgressDialog = new ProgressDialog(this);
        }
        mProgressDialog.setCancelable(cancelable);
        mProgressDialog.setMessage(TextUtils.isEmpty(message) ? "加载中..." : message);
        if (!mProgressDialog.isShowing() && !isFinishing()) {
            try {
                mProgressDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void dismissLoadingDialog() {
        if (null != mProgressDialog) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


}
