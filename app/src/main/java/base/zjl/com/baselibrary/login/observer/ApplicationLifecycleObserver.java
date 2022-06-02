package base.zjl.com.baselibrary.login.observer;

import android.util.Log;

import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;


/**
 * author:zhangjilei
 * date:2022/6/2
 * description:
 */
public class ApplicationLifecycleObserver implements DefaultLifecycleObserver {

    @Override
    public void onStart(LifecycleOwner owner) {
        Log.e("ceshi", "onAppForeground");
    }


    @Override
    public void onStop(LifecycleOwner owner) {
        Log.e("ceshi", "onAppBackground");
    }

    @Override
    public void onDestroy(LifecycleOwner owner) {

    }

    @Override
    public void onPause(LifecycleOwner owner) {

    }

    @Override
    public void onCreate(LifecycleOwner owner) {

    }

    @Override
    public void onResume(LifecycleOwner owner) {

    }
}
