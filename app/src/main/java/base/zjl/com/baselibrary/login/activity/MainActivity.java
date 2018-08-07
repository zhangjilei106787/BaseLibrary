package base.zjl.com.baselibrary.login.activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;

import java.util.List;

import base.zjl.com.baselibrary.R;
import base.zjl.com.baselibrary.login.bean.UserCateBean;
import base.zjl.com.baselibrary.login.mvvm.BaseActivity;
import base.zjl.com.baselibrary.login.net.callback.IGsonRequestCallBack;
import base.zjl.com.baselibrary.login.util.HttpUtil;


public class MainActivity extends BaseActivity {
    @Override
    protected void initDate() {

        HttpUtil.getInstance().getUserType2(new IGsonRequestCallBack<List<UserCateBean>>() {
            @Override
            public void onStartLoading() {

            }

            @Override
            public void onSuccess(List<UserCateBean> result) {
                if (result != null && !result.isEmpty()) {
                    //   Log.e("tag==",result.toString());
                }
            }

            @Override
            public void onError(String error) {

            }
        });
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);
    }

    @Override
    protected void initEvent() {

    }


    @Override
    public boolean useToolBar() {
        return true;
    }

    @Override
    public int initLoadResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initImmersionBar(R.color.colorPrimary);
        setToolBarTitle("normal");
    }


    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
