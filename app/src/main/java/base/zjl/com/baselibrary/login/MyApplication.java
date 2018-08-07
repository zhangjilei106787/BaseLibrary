package base.zjl.com.baselibrary.login;

import android.app.Activity;
import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

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
public class MyApplication extends Application {
    private static AppRoomDataBase mMyRoomDataBase;
    private static Context context;
    public static MyApplication application = null;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        application = this;
        CommonUtil.init(context);
        initLoadingLayout();
        AppManager.init(this);
        managerActivity();
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


    private void managerActivity() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                AppManager.getInstance().addActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                AppManager.getInstance().removeActivity(activity);
            }
        });
    }
    public static AppRoomDataBase getDataBaseInstance() {
        if (mMyRoomDataBase == null) {
            synchronized (MyApplication.class) {
                if (mMyRoomDataBase == null) {
                    mMyRoomDataBase = Room.databaseBuilder(getApplication(), AppRoomDataBase.class, "datasource.db").addCallback(new RoomDatabase.Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                        }

                        @Override
                        public void onOpen(@NonNull SupportSQLiteDatabase db) {
                            super.onOpen(db);
                        }
                    }).addMigrations()//迁移数据库使用
                            .allowMainThreadQueries()//允许在主线程查询数据
                            .fallbackToDestructiveMigration()//迁移数据库如果发生错误，将会重新创建数据库，而不是发生崩溃
                            .build();
                }

            }
        }
        return mMyRoomDataBase;
    }
}
