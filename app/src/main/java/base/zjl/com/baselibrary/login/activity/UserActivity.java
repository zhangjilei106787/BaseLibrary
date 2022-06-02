package base.zjl.com.baselibrary.login.activity;

import androidx.lifecycle.Observer;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import android.os.Message;
import android.util.Log;
import android.view.View;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import base.zjl.com.baselibrary.databinding.LayoutUseractivityBinding;
import base.zjl.com.baselibrary.login.bean.MessageEvent;
import base.zjl.com.baselibrary.login.bean.StudentEntry;
import base.zjl.com.baselibrary.login.mvvm.BaseActivity;
import base.zjl.com.baselibrary.login.login.Person;
import base.zjl.com.baselibrary.login.login.User;
import base.zjl.com.baselibrary.login.login.UserViewModel;

/**
 * @author: zjl on 2018-7-30.
 * Class:
 */
public class UserActivity extends BaseActivity {

    private UserViewModel mUserViewModel;
    private LayoutUseractivityBinding binding;

    @Override
    public void handBusMessage(MessageEvent event) {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public void layoutViewBinding() {
        binding = LayoutUseractivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    public void initView() {
        binding.customToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mUserViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        mUserViewModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                int size = users.size();
            }
        });
        mUserViewModel.getStudentData().observe(this, new Observer<List<StudentEntry>>() {
            @Override
            public void onChanged(@Nullable List<StudentEntry> studentEntries) {
                Log.e("tag", "studentEntries" + studentEntries);
            }
        });
        binding.tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mUserViewModel.loadUsers();
                mUserViewModel.addStudent();
            }
        });

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
    public void viewOnClick(View view) {

    }

    @Override
    public void handleMsg(Message msg) {

    }
}
