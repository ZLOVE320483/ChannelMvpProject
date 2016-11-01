package com.zlove.channelmvp.api;

import com.zlove.channelmvp.bean.CommonBean;
import com.zlove.channelmvp.bean.user.UserLoginBean;
import com.zlove.channelmvp.bean.user.UserVerifyCodeBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ZLOVE on 2016/10/28.
 */
public interface ApiService {

    //-----Login----
    @POST("user/login")
    @FormUrlEncoded
    Observable<UserLoginBean> login(@Field("username") String username, @Field("password") String password);

    //-----Register----
    @POST("user/registerBoundPhone")
    @FormUrlEncoded
    Observable<UserVerifyCodeBean> getVerifyCode(@Field("phone") String phone);

    @POST("user/register")
    @FormUrlEncoded
    Observable<CommonBean> userRegister(@Field("phone") String phone, @Field("password") String password, @Field("code") String code, @Field("realname") String realname);

}
