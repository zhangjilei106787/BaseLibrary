package com.company.baselibrary.views.loading.callback;


import com.company.baselibrary.R;

/**
 * @Description: 通用的网络错误
 */
public class ErrorCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.layout_defult_common_error;
    }

    @Override
    public boolean getSuccessVisible() {
        return super.getSuccessVisible();
    }

}
