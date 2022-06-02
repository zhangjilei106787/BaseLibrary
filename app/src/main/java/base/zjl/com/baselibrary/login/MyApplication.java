package base.zjl.com.baselibrary.login;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.multidex.MultiDexApplication;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import base.zjl.com.baselibrary.login.observer.ActivityObserver;
import base.zjl.com.baselibrary.login.observer.ApplicationLifecycleObserver;

import com.company.baselibrary.utils.appmanager.AppManager;
import com.company.baselibrary.views.loading.LoadSir;
import com.company.baselibrary.views.loading.callback.EmptyCallback;
import com.company.baselibrary.views.loading.callback.ErrorCallback;
import com.company.baselibrary.views.loading.callback.LoadingCallback;

import base.zjl.com.baselibrary.login.dao.AppRoomDataBase;

import com.company.baselibrary.utils.util.CommonUtil;

/**
 * @author: zjl on 2018-8-4.
 * Class:
 */
public class MyApplication extends MultiDexApplication {
    private static Context context;
    public static MyApplication application = null;

    @Override
    public void onCreate() {
        super.onCreate();
        ProcessLifecycleOwner.get().getLifecycle().addObserver(new ApplicationLifecycleObserver());
        context = getApplicationContext();
        application = this;
        CommonUtil.init(context);
        initLoadingLayout();
        AppManager.init(this);
        registerActivityLifecycleCallbacks(new ActivityObserver());
        AppRoomDataBase.getDataBaseInstance();
    }

    public static Context getContext() {
        return context;
    }

    public static MyApplication getApplication() {
        return application;
    }


    private void initLoadingLayout() {
        LoadSir.beginBuilder()
                .addCallback(new EmptyCallback())
                .addCallback(new ErrorCallback())
                .addCallback(new LoadingCallback())
                .setDefaultCallback(LoadingCallback.class)
                .commit();
    }


}
