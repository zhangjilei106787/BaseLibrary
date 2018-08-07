package base.zjl.com.baselibrary.login.login;

import com.company.baselibrary.base.mvp.IBaseModel;
import com.company.baselibrary.base.mvp.IBaseView;
import com.company.baselibrary.base.mvp.IBasepresenter;

/**
 * @author: zjl on 2018-6-10.
 * Class:  登录模块契约类
 */
public interface LoginContract {

    interface IloginView extends IBaseView {
        void showDialog();
        void hideDialog();
        void sussessed();
        void failed();
    }

    interface IloginModel extends IBaseModel {
          void login(String name, String password,IloginListener listener);
    }

    abstract class IloginPresenter extends IBasepresenter<IloginModel, IloginView> {

        public abstract void requestLogin(String name, String password);

    }
}
