package base.zjl.com.baselibrary.login.mvvm;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.company.baselibrary.R;
import com.company.baselibrary.utils.common.ScreenUtils;
import com.company.baselibrary.views.toolbar.CustomToolbar;
import com.gyf.barlibrary.ImmersionBar;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends RxAppCompatActivity implements View.OnClickListener {
    private long exitTime = 0;
    private CustomToolbar customToolbar;
    private ProgressDialog mProgressDialog;
    public ImmersionBar mMImmersionBar;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeContentView();
        mMImmersionBar = ImmersionBar.with(this);
        mMImmersionBar.init();
        setContentView(initLoadResId());
        unbinder = ButterKnife.bind(this);
        if (useToolBar()) initBar();
        getLifecycle().addObserver(new ActivityconfigurationObserver(this));
        initView();
        initEvent();
        initDate();

    }


    protected abstract void initDate();

    protected abstract void initEvent();


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
