package net.brian.italker.factory.presenter.contact;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.util.DiffUtil;

import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.CursorResult;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;

import net.brian.italker.factory.data.DataSource;
import net.brian.italker.factory.data.helper.UserHelper;
import net.brian.italker.factory.model.card.UserCard;
import net.brian.italker.factory.model.db.AppDatabase;
import net.brian.italker.factory.model.db.User;
import net.brian.italker.factory.model.db.User_Table;
import net.brian.italker.factory.persistence.Account;
import net.brian.italker.factory.presenter.BasePresenter;
import net.brian.italker.factory.utils.DiffUiDataCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * 联系人的Presenter实现
 * Created by with_you on 2017/8/30.
 */

public class ContactPresenter extends BasePresenter<ContactContract.View>
        implements ContactContract.Presenter {

    public ContactPresenter(ContactContract.View view) {
        super(view);
    }

    @Override
    public void start() {
        super.start();

        // 加载本地数据库数据
        SQLite.select()
                .from(User.class)
                .where(User_Table.isFollow.eq(true))
                .and(User_Table.id.notEq(Account.getUserId()))
                .orderBy(User_Table.name, true)
                .limit(100)
                .async()
                .queryListResultCallback(new QueryTransaction.QueryResultListCallback<User>() {
                    @Override
                    public void onListQueryResult(QueryTransaction transaction,
                                                  @NonNull List<User> tResult) {
                        getView().getRecyclerAdapter().replace(tResult);
                        getView().onAdapterDataChanged();

                    }
                })
                .execute();

        // 加载网络数据
        UserHelper.refreshContact(new DataSource.Callback<List<UserCard>>() {
            @Override
            public void onDataNotAvailable(@StringRes int strRes) {
                // 网络失败，因为本地是有数据的， 不管错误
            }

            @Override
            public void onDataLoaded(final List<UserCard> userCards) {
                // 转换为User
                final List<User> users = new ArrayList<>();
                for (UserCard userCard : userCards) {
                    users.add(userCard.build());
                }

                // 丢到事务中保存数据库
                DatabaseDefinition definition = FlowManager.getDatabase(AppDatabase.class);
                definition.beginTransactionAsync(new ITransaction() {
                    @Override
                    public void execute(DatabaseWrapper databaseWrapper) {
                        FlowManager.getModelAdapter(User.class)
                                .saveAll(users);
                    }
                }).build().execute();

                // 网路的护具往往是新的，我们需要直接刷新界面

                diff(getView().getRecyclerAdapter().getItems(), users);
            }
        });

        // TODO 问题:
        // 1.关注后虽然存储了数据库，但是没有刷新联系人
        // 2.如果刷新数据库，或者从网络刷新，最终刷新的时候是全局刷新
        // 3.本地刷新和网络刷新，在添加到界面的时候会有可能冲突;导致数据显示异常
        // 4.如果识别已经在数据库中有这样的数据了
    }

    private void diff(List<User> newList, List<User> oldList) {
        DiffUtil.Callback callback = new DiffUiDataCallback<>(oldList, newList);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        result.dispatchUpdatesTo(getView().getRecyclerAdapter());

        getView().onAdapterDataChanged();

    }
}
