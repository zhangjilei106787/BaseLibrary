package base.zjl.com.baselibrary.login.login;

/**
 * @author: zjl on 2018-6-10.
 * Class:
 */
public class LoginModel implements LoginContract.IloginModel {
    @Override
    public void login(String name, String password,IloginListener listener) {
           if(name.equals("张三")&&password.equals("123")){
               listener.success();
           }else{
               listener.failed();
           }
    }
}
