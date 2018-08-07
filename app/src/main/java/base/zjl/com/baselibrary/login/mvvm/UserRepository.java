package base.zjl.com.baselibrary.login.mvvm;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import base.zjl.com.baselibrary.login.MyApplication;
import base.zjl.com.baselibrary.login.bean.StudentEntry;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class UserRepository {
    private Application appcation;
    private MutableLiveData<List<User>> mUseLives;
    private MutableLiveData<List<Person>> mpsersonLives;
    private MutableLiveData<Person> mpsersonLive;
    private MutableLiveData<List<StudentEntry>> mStudentEntryMutableLiveData;
    private List<User> mUsers;

    public UserRepository(Application application) {
        this.appcation = application;
    }


    public LiveData<List<User>> getUsers() {
        if (mUseLives == null) {
            mUseLives = new MutableLiveData<List<User>>();
//            loadUsers();
        }
        return mUseLives;
    }

    public MutableLiveData<List<StudentEntry>> getStudentData() {
        if (mStudentEntryMutableLiveData == null) {
            mStudentEntryMutableLiveData = new MutableLiveData<>();
        }

        return mStudentEntryMutableLiveData;
    }

    @SuppressLint("CheckResult")
    public void addStudent() {
        Observable.create(new ObservableOnSubscribe<LiveData<List<StudentEntry>>>() {
            @Override
            public void subscribe(ObservableEmitter<LiveData<List<StudentEntry>>> emitter) {
                try {
                    StudentEntry studentEntry = new StudentEntry();
                    studentEntry.setName("李四");
                    studentEntry.setSex(0);
                    studentEntry.setClass_id("11");
                    MyApplication.getDataBaseInstance().getStudentDao().insert(studentEntry);
                    LiveData<List<StudentEntry>> listLiveData = MyApplication.getDataBaseInstance().getStudentDao().getAll1();
                    if (listLiveData != null) {
                        emitter.onNext(listLiveData);
                    }
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LiveData<List<StudentEntry>>>() {
                    @Override
                    public void accept(LiveData<List<StudentEntry>> studentEntries) {
                        Log.e("TAG", "studentEntries" + studentEntries);
                        mStudentEntryMutableLiveData = (MutableLiveData<List<StudentEntry>>) studentEntries;
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    public LiveData<List<Person>> getPsersons() {
        if (mpsersonLives == null) {
            mpsersonLives = new MutableLiveData<>();
        }
        Person person = new Person();
        List<Person> list = new ArrayList<>();
        list.add(person);
        Person person1 = new Person();
        list.add(person1);
        mpsersonLives.postValue(list);
        return mpsersonLives;
    }

    public MutableLiveData<Person> getMpsersonLive() {
        mpsersonLive = new MutableLiveData<>();
        Person person = new Person();
        mpsersonLive.postValue(person);
        return mpsersonLive;
    }

    public void setUseLives() {
        mUsers.add(new User("aa"));
        mUsers.add(new User("bb"));
        mUseLives.postValue(mUsers);
    }

    public void loadUsers() {

        mUsers = new ArrayList<>();
        mUsers.add(new User("aa"));
        mUsers.add(new User("bb"));
        mUsers.add(new User("cc"));
        mUsers.add(new User("dd"));
        mUsers.add(new User("ee"));
        //execute in ui thread
        mUseLives.postValue(mUsers);
    }

}