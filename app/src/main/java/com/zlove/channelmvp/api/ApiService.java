package com.zlove.channelmvp.api;

import com.zlove.channelmvp.bean.CommonBean;
import com.zlove.channelmvp.bean.project.ProjectItemBean;
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

    //----Find Password----
    @POST("user/forgetPassword")
    @FormUrlEncoded
    Observable<UserVerifyCodeBean> getFindPwdVerCode(@Field("phone") String phone);

    @POST("user/checkPhoneCode")
    @FormUrlEncoded
    Observable<CommonBean> checkPhoneCode(@Field("phone") String phone, @Field("code") String code);

    @POST("user/updateNewPassword")
    @FormUrlEncoded
    Observable<CommonBean> updateNewPassword(@Field("phone") String phone, @Field("new_password") String new_password);

    @POST("house/getHouseList")
    @FormUrlEncoded
    Observable<ProjectItemBean> getProjectList(@Field("keyword") String keyword, @Field("city_id") String city_id,
                                               @Field("house_type") String house_type, @Field("property_type") String property_type,
                                               @Field("price_min") String price_min, @Field("price_max") String price_max,
                                               @Field("page_index") String page_index, @Field("page_size") String page_size,
                                               @Field("area_id") String area_id);

}
