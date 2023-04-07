package base.zjl.com.baselibrary.login.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import base.zjl.com.baselibrary.databinding.FragmentHomeBinding;
import base.zjl.com.baselibrary.databinding.FragmentNotifyBinding;
import base.zjl.com.baselibrary.login.bean.MessageEvent;
import base.zjl.com.baselibrary.login.mvvm.BaseFragment;

public class WaitFragment extends BaseFragment {


    private FragmentNotifyBinding binding;

    @Override
    public View bingView(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentNotifyBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void handBusMessage(MessageEvent event) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
