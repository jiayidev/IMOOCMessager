package net.brian.italker.factory.presenter.contact;

import net.brian.italker.factory.model.card.UserCard;
import net.brian.italker.factory.presenter.BaseContract;

/**
 * Created by with_you on 2017/8/28.
 * 关注的接口定义
 */

public interface FollowContract {
    // 任务调度者
    interface Presenter extends BaseContract.Presenter {
        // 关注一个人
        void follow(String id);
    }

    interface View extends BaseContract.View<Presenter> {
        // 成功的情况下返回一个用户的信息
        void onFollowSucceed(UserCard userCard);
    }
}
