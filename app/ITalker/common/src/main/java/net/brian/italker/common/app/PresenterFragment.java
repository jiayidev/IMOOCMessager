package net.brian.italker.common.app;

import android.content.Context;
import android.support.annotation.StringRes;

import net.brian.italker.factory.presenter.BaseContract;

/**
 * Created by with_you on 2017/6/9.
 */

public abstract class PresenterFragment<Presenter extends BaseContract.Presenter> extends Fragment
        implements BaseContract.View<Presenter> {

    protected Presenter mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //在界面onAttach之后就触发初始化
        initPresenter();
    }

    /**
     * 初始化Presenter
     * @return Presenter
     */
    protected abstract Presenter initPresenter();

    @Override
    public void showError(int str) {
        //显示错误,优先使用占位布局
        if(mPlaceHolderView!=null){
            mPlaceHolderView.triggerError(str);
            return;
        }else{
            Application.showToast(str);
        }

    }

    @Override
    public void showLoading() {
        if(mPlaceHolderView!=null) {
            mPlaceHolderView.triggerLoading();
        }
    }

    @Override
    public void setPresenter(Presenter presenter) {
        //View中赋值Presenter
        mPresenter = presenter;
    }
}
