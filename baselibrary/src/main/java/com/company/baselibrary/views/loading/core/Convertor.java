package com.company.baselibrary.views.loading.core;


import com.company.baselibrary.views.loading.callback.Callback;

public interface Convertor<T> {
    Class<? extends Callback> map(T t);
}
