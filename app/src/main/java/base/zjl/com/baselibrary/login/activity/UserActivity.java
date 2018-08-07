package base.zjl.com.baselibrary.login.activity;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import base.zjl.com.baselibrary.R;
import base.zjl.com.baselibrary.login.bean.StudentEntry;
import base.zjl.com.baselibrary.login.mvvm.BaseActivity;
import base.zjl.com.baselibrary.login.mvvm.Person;
import base.zjl.com.baselibrary.login.mvvm.User;
import base.zjl.com.baselibrary.login.mvvm.UserViewModel;
import butterknife.BindView;

/**
 * @author: zjl on 2018-7-30.
 * Class:
 */
public class UserActivity extends BaseActivity {

    @BindView(R.id.tv_add)
    TextView mTvAdd;
    private UserViewModel mUserViewModel;
    public final int code = 1;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        mUserViewModel.init();
        mUserViewModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                int size = users.size();
            }
        });
        mUserViewModel.getPsersons().observe(this, new Observer<List<Person>>() {
            @Override
            public void onChanged(@Nullable List<Person> people) {
                int size = people.size();
            }
        });
        mUserViewModel.getMpsersonLive().observe(this, new Observer<Person>() {
            @Override
            public void onChanged(@Nullable Person person) {
                Log.e("tag", person.toString());
            }
        });
        mUserViewModel.getStudentData().observe(this, new Observer<List<StudentEntry>>() {
            @Override
            public void onChanged(@Nullable List<StudentEntry> studentEntries) {
                Log.e("tag", "studentEntries" + studentEntries);
            }
        });
        mTvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mUserViewModel.loadUsers();
                mUserViewModel.addStudent();
            }
        });


    }

    @Override
    protected void initDate() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    public boolean useToolBar() {
        return false;
    }

    @Override
    public int initLoadResId() {
        return R.layout.layout_useractivity;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onClick(View view) {

    }
}
