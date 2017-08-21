package net.brian.italker.factory.presenter.account;

import android.support.annotation.StringRes;
import android.text.TextUtils;

import net.brian.italker.common.Common;
import net.brian.italker.factory.R;
import net.brian.italker.factory.data.DataSource;
import net.brian.italker.factory.data.helper.AccountHelper;
import net.brian.italker.factory.model.api.account.RegisterModel;
import net.brian.italker.factory.model.db.User;
import net.brian.italker.factory.persistence.Account;
import net.brian.italker.factory.presenter.BasePresenter;
import net.qiujuer.genius.kit.handler.Run;
import net.qiujuer.genius.kit.handler.runable.Action;

import java.util.regex.Pattern;

/**
 * Created by with_you on 2017/6/9.
 */

public class RegisterPresenter extends BasePresenter<RegisterContract.View>
        implements RegisterContract.Presenter, DataSource.Callback<User> {
    public RegisterPresenter(RegisterContract.View view) {
        super(view);
    }

    @Override
    public void register(String phone, String name, String password) {
        //调用开始方法，在start中默认启动了Loading
        start();

        //得到View接口
        RegisterContract.View view = getView();

        //校验
        if (!checkMobile(phone)) {
            //提示
            view.showError(R.string.data_account_register_invalid_parameter_mobile);
        } else if (name.length() < 2) {
            //姓名需要大于两位
            view.showError(R.string.data_account_register_invalid_parameter_name);
        } else if (password.length() < 6) {
            //密码需要大于6位
            view.showError(R.string.data_account_register_invalid_parameter_password);
        } else {
            //进行网络请求

            //构造Model，进行请求调用
            RegisterModel model = new RegisterModel(phone, password, name, Account.getPushId());
            //进行网络请求，并设置回送接口为自己
            AccountHelper.register(model, this);
        }
    }

    /**
     * 检查手机号是否合法
     *
     * @param phone 手机号码
     * @return 合法的为true
     */
    @Override
    public boolean checkMobile(String phone) {
        //手机号不为空，并且满足格式
        return !TextUtils.isEmpty(phone)
                && Pattern.matches(Common.Constance.REGEX_MOBILE, phone);
    }

    @Override
    public void onDataLoaded(User user) {
        //当网络请求成功，注册好了，会送一个用户信息回来
        //告知通知界面，注册成功
        final RegisterContract.View view = getView();
        if (view == null)
            return;
        //此时是从网络回送回来的，并不保证处于主线程状态
        //强制执行在主线程中
        Run.onUiAsync(new Action() {
            @Override
            public void call() {
                //调用主界面注册成功
                view.registerSuccess();
            }
        });
    }

    @Override
    public void onDataNotAvailable(@StringRes final int strRes) {
        //网络请求告知注册失败
        final RegisterContract.View view = getView();
        if (view == null)
            return;
        //此时是从网络回送回来的，并不保证处于主线程状态
        //强制执行在主线程中
        Run.onUiAsync(new Action() {
            @Override
            public void call() {
                //调用主界面注册失败显示错误
                view.showError(strRes);
            }
        });
    }
}
