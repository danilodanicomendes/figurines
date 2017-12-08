package com.danilomendes.figurines.data.remote

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.danilomendes.figurines.BuildConfig
import com.danilomendes.figurines.util.L
import com.github.aurae.retrofit2.LoganSquareConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import javax.inject.Inject

/**
 * Created by danilo on 07-12-2017.
 */
class NetworkManager @Inject
constructor(context: Application, baseUrl: String) {

    val retrofit: Retrofit
    private val context: Context

    init {
        this.context = context

        val clientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val appLayerLogger = HttpLoggingInterceptor.Logger { L.log(it) }
            val appLogging = HttpLoggingInterceptor(appLayerLogger)
            appLogging.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(appLogging)
        }

        retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(clientBuilder.build())
                .addConverterFactory(LoganSquareConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    fun isNetworkAvailable(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) ?: return false
        val activeNetworkInfo = (cm as ConnectivityManager).activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}