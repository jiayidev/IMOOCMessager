package net.brian.italker.factory.data.helper;

import net.brian.italker.factory.Factory;
import net.brian.italker.factory.R;
import net.brian.italker.factory.data.DataSource;
import net.brian.italker.factory.model.api.RspModel;
import net.brian.italker.factory.model.api.account.AccountRspModel;
import net.brian.italker.factory.model.api.user.UserUpdateModel;
import net.brian.italker.factory.model.card.UserCard;
import net.brian.italker.factory.model.db.User;
import net.brian.italker.factory.net.NetWork;
import net.brian.italker.factory.net.RemoteService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by with_you on 2017/7/27.
 */

public class UserHelper {
    //更新用户信息,异步的
    public static void update(UserUpdateModel model, final DataSource.Callback<UserCard> callback) {
        //调用Retrofit对我们的网络请求接口做代理
        RemoteService service = NetWork.remote();
        //得到一个Call
        Call<RspModel<UserCard>> call = service.userUpdate(model);
        //网络请求
        call.enqueue(new Callback<RspModel<UserCard>>() {
            @Override
            public void onResponse(Call<RspModel<UserCard>> call, Response<RspModel<UserCard>> response) {
                RspModel<UserCard> rspModel = response.body();
                if (rspModel.success()) {
                    UserCard userCard = rspModel.getResult();
                    //数据库的存储操作，需要把UserCard转换为User
                    //保存用户信息
                    User user = userCard.build();
                    user.save();
                    //返回成功
                    callback.onDataLoaded(userCard);
                } else {
                    //错误信息下进行错误分配
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<UserCard>> call, Throwable t) {
                callback.onDataNotAvailable(R.string.data_network_error);
            }
        });
    }
}
