package base.zjl.com.baselibrary.login.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.tbruyelle.rxpermissions3.Permission;
import com.tbruyelle.rxpermissions3.RxPermissions;

import java.util.List;

import base.zjl.com.baselibrary.databinding.ActivityMainBinding;
import base.zjl.com.baselibrary.login.bean.MessageEvent;
import base.zjl.com.baselibrary.login.bean.UserCateBean;
import base.zjl.com.baselibrary.login.mvvm.BaseActivity;
import base.zjl.com.baselibrary.login.net.callback.IGsonRequestCallBack;
import base.zjl.com.baselibrary.login.util.HttpUtil;
import io.reactivex.rxjava3.functions.Consumer;


public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    private long exitTime;

    @Override
    public void initData() {

        HttpUtil.getInstance().getUserType2(new IGsonRequestCallBack<List<UserCateBean>>() {
            @Override
            public void onBeforeRequest() {

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

    }

    @Override
    public void initEvent() {

    }

    @Override
    public void viewOnClick(View view) {

    }

    @Override
    public void handleMsg(Message msg) {

    }

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
    public void layoutViewBinding() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    public void initView() {
        RxPermissions permissions = new RxPermissions(this);
        permissions.request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Throwable {
                if (aBoolean) {
                    Intent intent = new Intent(MainActivity.this, UserActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void handBusMessage(MessageEvent event) {

    }

    @Override
    public void onClick(View view) {

    }

}
