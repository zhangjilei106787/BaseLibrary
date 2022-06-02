package base.zjl.com.baselibrary.login.login;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.annotation.NonNull;

import java.util.List;

import base.zjl.com.baselibrary.login.bean.StudentEntry;

/**
 * 可以继承viewmodel   viewmodel 生命周期比activtiy 长 不能持有任何view
 * 如果需要用到context  继承androidViewModel  构造携带application
 */
public class UserViewModel extends AndroidViewModel {
    private  Application application;
    private UserRepository mUserRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        this.application=application;
        init();
    }

    public void init() {
        if (mUserRepository == null) {
            mUserRepository = new UserRepository( application);
        }
    }
    public LiveData<List<User>> getUsers() {
        return mUserRepository.getUsers();
    }

    public MutableLiveData<List<StudentEntry>> getStudentData() {

        return mUserRepository.getStudentData();
    }


    public LiveData<List<Person>> getPsersons() {
        return mUserRepository.getPsersons();
    }

    public MutableLiveData<Person> getMpsersonLive() {
        return mUserRepository.getMpsersonLive();
    }

    public void setUseLives() {
        mUserRepository.setUseLives();
    }

    public void loadUsers() {

        mUserRepository.loadUsers();
    }

    public void addStudent() {
        mUserRepository.addStudent();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}