package base.zjl.com.baselibrary.login.mvvm;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle4.components.support.RxFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import base.zjl.com.baselibrary.login.bean.MessageEvent;


public abstract class BaseFragment extends RxFragment {
    public Activity activity;
    private ProgressDialog mProgressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = bingView(inflater, container);
        getLifecycle().addObserver(new FragmentconfigurationObserver(getActivity(),this));
        return view;
    }

    public abstract View bingView(LayoutInflater inflater, ViewGroup container);

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void EventbusMessage(MessageEvent event) {
        handBusMessage(event);
    }


    public abstract void handBusMessage(MessageEvent event);

    public void initData() { }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
    public void showLoadingDialog() {
        showLoadingDialog("",false);
    }
    public void showLoadingDialog(String message, boolean cancelable) {
        if (null == mProgressDialog) {
            mProgressDialog = new ProgressDialog(activity);
        }
        mProgressDialog.setCancelable(cancelable);
        mProgressDialog.setMessage(TextUtils.isEmpty(message) ? "加载中..." : message);
        if (!mProgressDialog.isShowing() && !activity.isFinishing()) {
            try {
                mProgressDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 隐藏loading框
     */
    public void dismissLoadingDialog() {
        if (null != mProgressDialog) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //MobclickAgent.onPageStart(this.getClass().getSimpleName());
    }

    @Override
    public void onPause() {
        super.onPause();
      //  MobclickAgent.onPageEnd(this.getClass().getSimpleName());
    }
}
