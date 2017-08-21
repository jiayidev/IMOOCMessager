package net.brian.italker.factory.data;

import android.support.annotation.StringRes;

/**
 * 数据源接口定义
 * Created by with_you on 2017/7/19.
 */

public interface DataSource {

    /**
     * 同时包括了成功与失败的回调接口
     *
     * @param <T> 任意类型
     */
    interface Callback<T> extends SuccessCallback<T>, FailedCallback {

    }

    /**
     * 只关注成功的接口
     *
     * @param <T> 任意类型
     */
    interface SuccessCallback<T> {
        //数据加载成功,网络请求成功
        void onDataLoaded(T user);
    }

    /**
     * 只关注失败的接口
     */
    interface FailedCallback {
        //数据加载失败,网络请求失败
        void onDataNotAvailable(@StringRes int strRes);
    }
}
