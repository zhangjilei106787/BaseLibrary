package base.zjl.com.baselibrary.login.activity;

import android.content.Intent;
import android.view.View;

import com.company.baselibrary.base.mvp.BaseActivity;

import base.zjl.com.baselibrary.R;
import base.zjl.com.baselibrary.login.login.LoginContract;
import base.zjl.com.baselibrary.login.login.LoginPresenter;

/**
 * @author: zjl on 2018-7-29.
 * Class:
 */
public class LoginActivity extends BaseActivity implements LoginContract.IloginView{
    @Override
    protected void initDate() {
        ((LoginPresenter)mpresenter).requestLogin("张三","123");
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected LoginPresenter initPresenter() {
        return  new LoginPresenter();
    }

    @Override
    public boolean useToolBar() {
        return false;
    }

    @Override
    public int initLoadResId() {
        return R.layout.layout_loginactivity;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onClick(View view) {

    }


    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void sussessed() {
        Intent intent=new Intent(this,UserActivity.class);
        startActivity(intent);
    }

    @Override
    public void failed() {

    }
}
