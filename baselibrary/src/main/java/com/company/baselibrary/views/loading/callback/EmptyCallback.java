package com.company.baselibrary.views.loading.callback;


import com.company.baselibrary.R;

/**
 * @Description: 通用的数据为空
 */
public class EmptyCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.layout_defult_common_empty;
    }

    @Override
    public boolean getSuccessVisible() {
        return super.getSuccessVisible();
    }

}
