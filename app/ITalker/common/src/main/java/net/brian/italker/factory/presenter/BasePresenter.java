package net.brian.italker.factory.presenter;

/**
 * Created by with_you on 2017/6/9.
 */

public class BasePresenter<T extends BaseContract.View>
        implements BaseContract.Presenter {

    private T mView;

    public BasePresenter(T view) {
        setView(view);
    }

    /**
     * 设置一个View，子类可以重复
     *
     * @param view
     */
    protected void setView(T view) {
        this.mView = view;
        this.mView.setPresenter(this);
    }

    /**
     * 给子类使用的获取View的操作
     * 不允许复写
     *
     * @return
     */
    protected final T getView() {
        return mView;
    }

    @Override
    public void start() {
        //开始的时候进行Loading调用
        T view = mView;
        if (view != null) {
            view.showLoading();
            ;
        }
    }

    @Override
    public void destroy() {
        T view = mView;
        mView = null;
        if (view != null) {
            //把Presenter设置为null
            view.setPresenter(null);
        }
    }
}
