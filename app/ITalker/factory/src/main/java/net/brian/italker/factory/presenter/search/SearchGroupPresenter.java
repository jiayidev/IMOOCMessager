package net.brian.italker.factory.presenter.search;

import net.brian.italker.factory.presenter.BasePresenter;

/**
 *  搜索群的逻辑实现
 * Created by with_you on 2017/8/19.
 */

public class SearchGroupPresenter extends BasePresenter<SearchContract.GroupView>
            implements SearchContract.Presenter{
    public SearchGroupPresenter(SearchContract.GroupView view) {
        super(view);
    }

    @Override
    public void search(String content) {

    }
}
