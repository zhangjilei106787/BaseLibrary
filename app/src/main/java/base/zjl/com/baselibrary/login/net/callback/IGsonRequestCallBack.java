package base.zjl.com.baselibrary.login.net.callback;

/**
 * @author: liliuhuan
 * @date：
 * @version:1.0.0
 * @description:
 */
public interface IGsonRequestCallBack<T> {

    /**
     * 请求前的回调
     */
    void onBeforeRequest();

    /**
     * 成功的回调
     * @param result
     */
    void onSuccess(T result);

    /**
     * 失败的回调
     * @param error
     */
    void onError(String error);

}
