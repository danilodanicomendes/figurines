package com.danilomendes.figurines;

import android.app.Application;
import android.content.Context;

import com.danilomendes.figurines.di.AppModule;
import com.danilomendes.figurines.di.DaggerNetworkComponent;
import com.danilomendes.figurines.di.NetworkComponent;
import com.danilomendes.figurines.di.NetworkModule;
import com.danilomendes.figurines.utils.L;

/**
 * Created by danilo on 12-10-2017.
 */
public class FigurinesApplication extends Application {

    private NetworkComponent mNetworkComponent;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        L.log("attachBaseContext");
        // TODO Use attachBaseContext instead of onCreate to inject?
        /*mNetworkComponent = DaggerNetworkComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule(BuildConfig.BASE_URL))
                .build();*/
    }

    @Override
    public void onCreate() {
        super.onCreate();

        L.log("onCreate");
        mNetworkComponent = DaggerNetworkComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule(BuildConfig.BASE_URL))
                .build();
    }

    public NetworkComponent getNetworkComponent() {
        return mNetworkComponent;
    }
}
