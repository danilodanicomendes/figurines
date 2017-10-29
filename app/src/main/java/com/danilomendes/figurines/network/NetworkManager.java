package com.danilomendes.figurines.network;

import android.app.Application;
import android.content.Context;

import com.danilomendes.figurines.BuildConfig;
import com.danilomendes.figurines.network.api.CompanyService;
import com.danilomendes.figurines.utils.L;
import com.danilomendes.figurines.utils.NetworkUtils;
import com.github.aurae.retrofit2.LoganSquareConverterFactory;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by danilo on 18-10-2017.
 */
public class NetworkManager {

    private final Retrofit mRetrofit;
    private final Context mContext;

    @Inject
    public NetworkManager(Application context, String baseUrl) {
        this.mContext = context;

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Logger appLayerLogger = L::log;
            HttpLoggingInterceptor appLogging = new HttpLoggingInterceptor(appLayerLogger);
            appLogging.setLevel(HttpLoggingInterceptor.Level.BODY);
            clientBuilder.addInterceptor(appLogging);
        }

        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(clientBuilder.build())
                .addConverterFactory(LoganSquareConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public boolean isNetworkAvailable() {
        return NetworkUtils.isNetworkAvailable(mContext);
    }

    public CompanyService getCompaniesService() {
        return mRetrofit.create(CompanyService.class);
    }
}
