package net.brian.italker.factory.presenter.search;

import net.brian.italker.factory.model.card.GroupCard;
import net.brian.italker.factory.model.card.UserCard;
import net.brian.italker.factory.presenter.BaseContract;

import java.util.List;

/**
 * Created by with_you on 2017/8/19.
 */

public interface SearchContract {
    interface Presenter extends BaseContract.Presenter {
        //搜索内容
        void search(String content);
    }

    //搜索人的界面
    interface UserView extends BaseContract.View<Presenter>{
        void onSearchDone(List<UserCard> userCards);
    }

    //搜索群的界面
    interface GroupView extends BaseContract.View<Presenter>{
        void onSearchDone(List<GroupCard> groupCards);
    }
}
