package base.zjl.com.baselibrary.login.login;

/**
 * @author: zjl on 2018-6-10.
 * Class:
 */
public class LoginPresenter extends LoginContract.IloginPresenter {

    @Override
    public void requestLogin(String name, String password) {
        mModel.login(name,password,new LoginListener());
    }

    @Override
    public LoginContract.IloginModel getModel() {
        return new LoginModel();
    }


    public class  LoginListener implements IloginListener {
        @Override
        public void success() {
            LoginContract.IloginView view = getView();
            if(view!=null){
                view.sussessed();
            }

        }
        @Override
        public void failed() {
            LoginContract.IloginView view = getView();
            if(view!=null){
                view.failed();
            }
        }
    }
}
