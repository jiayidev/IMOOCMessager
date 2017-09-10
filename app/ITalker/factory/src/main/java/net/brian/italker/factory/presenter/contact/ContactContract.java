package net.brian.italker.factory.presenter.contact;

import net.brian.italker.common.widget.recycler.RecyclerAdapter;
import net.brian.italker.factory.model.db.User;
import net.brian.italker.factory.presenter.BaseContract;

import java.util.List;

/**
 * Created by with_you on 2017/8/30.
 */

public interface ContactContract {
    // 什么都不需要额外定义，开始就是调用即可
    interface Presenter extends BaseContract.Presenter {

    }
    // 都在基类完成了
    interface View extends BaseContract.RecyclerView<Presenter, User> {

    }
}
