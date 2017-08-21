package net.brian.italker.factory.presenter.search;

import net.brian.italker.factory.presenter.BasePresenter;

/**
 *  搜索人的实现
 * Created by with_you on 2017/8/19.
 */

public class SearchUserPresenter extends BasePresenter<SearchContract.UserView>
            implements SearchContract.Presenter{
    public SearchUserPresenter(SearchContract.UserView view) {
        super(view);
    }

    @Override
    public void search(String content) {

    }
}
