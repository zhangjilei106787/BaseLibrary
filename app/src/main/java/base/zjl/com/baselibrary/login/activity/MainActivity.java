package base.zjl.com.baselibrary.login.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tbruyelle.rxpermissions3.Permission;
import com.tbruyelle.rxpermissions3.RxPermissions;

import java.util.List;

import base.zjl.com.baselibrary.R;
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


        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_map, R.id.navigation_notifications,R.id.navigation_me)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
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
//                if (aBoolean) {
//                    Intent intent = new Intent(MainActivity.this, UserActivity.class);
//                    startActivity(intent);
//                }
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
