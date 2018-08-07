package com.company.baselibrary.observer;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

/**
 * @author: zjl on 2018-7-30.
 * Class:
 */
public class ActivityObserver implements LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate(){

    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
  public void onDestroy(){

  }

  public void  onAttach(){

  }
}
