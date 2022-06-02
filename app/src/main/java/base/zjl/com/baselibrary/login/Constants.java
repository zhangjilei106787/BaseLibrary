package base.zjl.com.baselibrary.login;


import base.zjl.com.baselibrary.BuildConfig;

public class Constants {
    public static String baseUrl = "http://demo.wukzk.com/limeng/pdzp/public/api/";
    static {
        if (BuildConfig.DEBUG_APK) {  /*如果是开发模式，则使用测试环境*/
            baseUrl = "http://demo.wukzk.com/limeng/pdzp/public/api/";
        } else {                        /*如果上线模式，则使用正式环境  */
            baseUrl = "http://demo.wukzk.com/limeng/pdzp/public/api/";
        }
    }

    // TODO: 2018/6/30 常用API
    public static final String URL_REGISTER = "User2/register";//POST
    public static final String URL_LOGIN = "User/login";//POST
    public static final String URL_userCate = "Other/userCate";//接口名称：获取用户种类
    public static final String URL_BANNER_LIST = "BannerInfo/index";//get

}