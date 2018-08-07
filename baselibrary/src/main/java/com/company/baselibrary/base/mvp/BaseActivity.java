package com.company.baselibrary.base.mvp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.company.baselibrary.R;
import com.company.baselibrary.utils.metrics.MetricsUtil;
import com.company.baselibrary.views.toolbar.CustomToolbar;
import com.gyf.barlibrary.ImmersionBar;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.simple.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends IBasepresenter> extends RxAppCompatActivity implements View.OnClickListener {
    private long exitTime = 0;
    private Unbinder unbinder;
    private CustomToolbar customToolbar;
    private ProgressDialog mProgressDialog;
    public ImmersionBar mMImmersionBar;
    public P mpresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeContentView();
        mMImmersionBar = ImmersionBar.with(this);
        mMImmersionBar.init();
        setContentView(initLoadResId());
        MetricsUtil.setCustomDensity(this, getApplication());
        unbinder = ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mpresenter=initPresenter();
        if (mpresenter != null) {
            mpresenter.attachModelView((IBaseView) this);
        }
        if (useToolBar()) initBar();
        initView();
        initEvent();
        initDate();

    }

    protected abstract void initDate();

    protected abstract void initEvent();

    protected abstract P initPresenter();


    /**
     * 初始化沉浸式
     */
    public void initImmersionBar(int color) {
        mMImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).statusBarDarkFont(true).statusBarColor(color).init();
    }

    public void initBar() {
        customToolbar = findViewById(R.id.custom_toolbar);
        if (customToolbar == null)
            throw new NullPointerException("该activity必须包含toolbar自定义的布局");
        customToolbar.setLeftTitleClickListener(this);
    }

    public void setToolBarTitle(String resStr) {
        if (customToolbar != null) {
            customToolbar.setMainTitle(resStr);
        }
    }

    public void setRightTitleText(String resStr) {
        if (customToolbar != null) {
            customToolbar.setRightTitleText(resStr);
        }
    }

    public void setRightTitleClickListener(View.OnClickListener listener) {
        if (customToolbar != null) {
            customToolbar.setRightTitleClickListener(listener);
        }
    }

    public void setToolBarTitle(@StringRes int resStr) {
        if (customToolbar != null) {
            customToolbar.setMainTitle(getString(resStr));
        }
    }

    public void setImageRightClickListener(View.OnClickListener onClickListener) {
        if (customToolbar != null) {
            customToolbar.setImageRightClickListener(onClickListener);
        }
    }

    public void setRightTitleText(int res) {
        if (customToolbar != null) {
            customToolbar.setRightImageRes(res);
        }
    }

    public abstract boolean useToolBar();

    protected void beforeContentView() {
    }

    public abstract int initLoadResId();

    protected abstract void initView();

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
        mpresenter.onDettach();
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
