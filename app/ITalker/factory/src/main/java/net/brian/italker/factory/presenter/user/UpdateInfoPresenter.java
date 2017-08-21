package net.brian.italker.factory.presenter.user;

import android.support.annotation.StringRes;
import android.text.TextUtils;

import net.brian.italker.factory.Factory;
import net.brian.italker.factory.R;
import net.brian.italker.factory.data.DataSource;
import net.brian.italker.factory.data.helper.UserHelper;
import net.brian.italker.factory.model.api.user.UserUpdateModel;
import net.brian.italker.factory.model.card.UserCard;
import net.brian.italker.factory.model.db.User;
import net.brian.italker.factory.net.UploadHelper;
import net.brian.italker.factory.presenter.BasePresenter;
import net.brian.italker.factory.presenter.account.LoginContract;
import net.qiujuer.genius.kit.handler.Run;
import net.qiujuer.genius.kit.handler.runable.Action;

/**
 * Created by with_you on 2017/7/27.
 */

public class UpdateInfoPresenter extends BasePresenter<UpdateInfoContract.View>
        implements UpdateInfoContract.Presenter, DataSource.Callback<UserCard> {
    public UpdateInfoPresenter(UpdateInfoContract.View view) {
        super(view);
    }

    @Override
    public void update(final String photoFilePath, final String desc, final boolean isMan) {
        start();

        final UpdateInfoContract.View view = getView();

        if (TextUtils.isEmpty(photoFilePath) || TextUtils.isEmpty(desc)) {
            view.showError(R.string.data_account_update_invalid_parameter);
        } else {
            //上传头像
            Factory.runObAsync(new Runnable() {
                @Override
                public void run() {
                    String url = UploadHelper.uploadPortrait(photoFilePath);
                    if (TextUtils.isEmpty(url)) {
                        //上传失败
                        view.showError(R.string.data_upload_error);
                    } else {
                        //构建model
                        UserUpdateModel model = new UserUpdateModel("", url, desc,
                                isMan ? User.SEX_MAX : User.SEX_WOMAN);
                        //进行网络请求上传
                        UserHelper.update(model, UpdateInfoPresenter.this);
                    }
                }
            });
        }
    }

    @Override
    public void onDataLoaded(UserCard user) {
        final UpdateInfoContract.View view = getView();
        if (view == null)
            return;
        //强制执行在主线程中
        Run.onUiAsync(new Action() {
            @Override
            public void call() {
                view.updateSucceed();
            }
        });
    }

    @Override
    public void onDataNotAvailable(final int strRes) {
        final UpdateInfoContract.View view = getView();
        if (view == null)
            return;
        //强制执行主线程中
        Run.onUiAsync(new Action() {
            @Override
            public void call() {
                view.showError(strRes);
            }
        });
    }
}
