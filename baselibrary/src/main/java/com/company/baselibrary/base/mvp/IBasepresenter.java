package com.company.baselibrary.base.mvp;

import java.lang.ref.WeakReference;

/**
 * @author: zjl on 2018-6-10.
 * Class:
 */
public abstract class IBasepresenter<M extends IBaseModel,V extends IBaseView> {

    public  M mModel;
    public V mView;
    public WeakReference<V> mViewRef;


    public void attachModelView( V pView) {
        mViewRef = new WeakReference<V>(pView);
        this.mView = mViewRef.get();
        this.mModel = getModel();
    }

    protected abstract  M getModel();
    public V getView() {
        if (isAttach()) {
            return mViewRef.get();
        } else {
            return null;
        }
    }

    public boolean isAttach() {
        return null != mViewRef && null != mViewRef.get();
    }


    public void onDettach() {
        if (null != mViewRef) {
            mViewRef.clear();
            mViewRef = null;
        }
    }
}


