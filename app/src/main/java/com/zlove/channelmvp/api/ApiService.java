package com.zlove.channelmvp.api;

import com.zlove.channelmvp.bean.user.UserLoginBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ZLOVE on 2016/10/28.
 */
public interface ApiService {

    @POST("user/login")
    @FormUrlEncoded
    Observable<UserLoginBean> login(@Field("username") String username, @Field("password") String password);

}
