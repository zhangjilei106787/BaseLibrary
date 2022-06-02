package base.zjl.com.baselibrary.login.observer;

import android.app.Activity;
import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;


import org.greenrobot.eventbus.EventBus;

import base.zjl.com.baselibrary.login.sentor.MyOrientationListener;


/**
 * @author: zjl on 2018-8-6.
 * Class: 全局需要根据生命周期配置的对象
 */
public class ActivityConfigurationObserver implements LifecycleObserver {
    MyOrientationListener listener;
    private Activity context;

    public ActivityConfigurationObserver(Activity context) {
        this.context = context;
    }

    //极光推送 等需要根据生命周期操作的都可以加入进来
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onStart() {
        listener.start();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        this.listener = new MyOrientationListener(context);
        EventBus.getDefault().register((Activity) context);
        Log.e("ceshi", "onCreate");

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResume() {
        Log.e("ceshi", "ON_RESUME");
    }

    //每个生命周期都会执行
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    void onAny() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause() {
        listener.stop();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onStop() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy() {
        EventBus.getDefault().unregister((Activity) context);
    }


}
