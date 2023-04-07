package base.zjl.com.baselibrary.login.net.retrofit;


import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import base.zjl.com.baselibrary.login.Constants;
import base.zjl.com.baselibrary.login.net.gsonconvert.GsonConverterFactory;
import base.zjl.com.baselibrary.login.net.interceptor.CommonInterceptor;
import base.zjl.com.baselibrary.login.net.interceptor.HeaderInterceptor;
import base.zjl.com.baselibrary.login.net.interceptor.LogInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * 网络请求的工具类
 */
public class RetrofitUtil {
    private static RetrofitUtil instance;
    private Retrofit helper;

    private RetrofitUtil() {
        OkHttpClient client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .addInterceptor(new CommonInterceptor())
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(new LogInterceptor())
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .proxy(Proxy.NO_PROXY)
                .build();

        helper = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.baseUrl)
                .build();
    }

    public static RetrofitUtil getInstance() {
        if (null == instance) {
            synchronized (RetrofitUtil.class) {
                if (null == instance)
                    instance = new RetrofitUtil();
            }
        }
        return instance;
    }

    public <T> T createService(Class<T> clz) {
        T service = helper.create(clz);
        return service;
    }

}
