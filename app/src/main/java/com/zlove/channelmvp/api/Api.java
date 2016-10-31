package com.zlove.channelmvp.api;

import android.text.TextUtils;

import com.zlove.channelmvp.config.ChannelCookie;
import com.zlove.channelmvp.constant.ApiConstants;
import com.zlove.channelmvp.util.ApplicationUtil;
import com.zlove.channelmvp.util.NetWorkUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ZLOVE on 2016/10/28.
 */
public class Api {
    public static final int READ_TIME_OUT = 15;
    public static final int CONNECT_TIME_OUT = 15;
    /**
     * 设缓存有效期为两天
     */
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;

    private static Api channelApi;
    public Retrofit retrofit;
    public ApiService apiService;

    private Api() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        File cacheFile = new File(ApplicationUtil.getApplicationContext().getCacheDir(), "cache");
        final Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb

        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request build = chain.request().newBuilder()
                        .addHeader("appVersion", ApplicationUtil.getVerName(ApplicationUtil.getApplicationContext()))
                        .addHeader("sysType", "android")
                        .addHeader("sysVersion", ApplicationUtil.getMobilePhoneSysVersion())
                        .addHeader("sessionid", TextUtils.isEmpty(ChannelCookie.getInstance().getSessionId()) ? "" : ChannelCookie.getInstance().getSessionId())
                        .addHeader("device_id", TextUtils.isEmpty(ApplicationUtil.getDeviceId()) ? "" : ApplicationUtil.getDeviceId())
                        .build();
                return chain.proceed(build);
            }
        };

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(mRewriteCacheControlInterceptor)
                .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                .addInterceptor(headerInterceptor)
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public static ApiService getDefault() {
        if (channelApi == null) {
            channelApi = new Api();
        }
        return channelApi.apiService;
    }

    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    private final Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetWorkUtils.isNetConnected(ApplicationUtil.getApplicationContext())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response originalResponse = chain.proceed(request);
            if (NetWorkUtils.isNetConnected(ApplicationUtil.getApplicationContext())) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_SEC)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };

}
