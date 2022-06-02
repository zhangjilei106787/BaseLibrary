package base.zjl.com.baselibrary.login.mvvm;

import android.app.Activity;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import org.greenrobot.eventbus.EventBus;

import base.zjl.com.baselibrary.login.sentor.MyOrientationListener;

/**
 * @author: zjl on 2018-8-6.
 * Class: 全局需要根据生命周期配置的对象
 */
public class FragmentconfigurationObserver implements DefaultLifecycleObserver {
    MyOrientationListener listener;
    private Activity context;
    private Fragment mFragment;
    public FragmentconfigurationObserver(Activity context, Fragment fragment) {
        this.context = context;
        this.mFragment=fragment;
    }

    //极光推送 等需要根据生命周期操作的都可以加入进来
    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        this.listener = new MyOrientationListener(context);
        EventBus.getDefault().register(mFragment);

    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {

    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        Lifecycle.State currentState = owner.getLifecycle().getCurrentState();
        if (!currentState.equals(Lifecycle.State.DESTROYED)) {
            listener.start();
        }
    }

    @Override
    public void onPause(@NonNull LifecycleOwner owner) {
        Lifecycle.State currentState = owner.getLifecycle().getCurrentState();
        listener.stop();
    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {

    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        EventBus.getDefault().unregister(mFragment);
    }
}
