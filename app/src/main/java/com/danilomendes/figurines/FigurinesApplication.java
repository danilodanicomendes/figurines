package com.danilomendes.figurines;

import android.app.Application;
import android.content.Context;

import com.danilomendes.figurines.data.manager.CompanyDataComponent;
import com.danilomendes.figurines.data.manager.CompanyDataModule;
import com.danilomendes.figurines.di.AppComponent;
import com.danilomendes.figurines.di.AppModule;
import com.danilomendes.figurines.di.DaggerAppComponent;
import com.danilomendes.figurines.di.DatabaseModule;
import com.danilomendes.figurines.di.NetworkModule;
import com.danilomendes.figurines.util.L;

/**
 * Created by danilo on 12-10-2017.
 */
public class FigurinesApplication extends Application {

    private AppComponent mAppComponent;
    private CompanyDataComponent mCompanyComponent;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        L.log("attachBaseContext");
        // TODO Use attachBaseContext instead of onCreate to inject?
        /*mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule(BuildConfig.BASE_URL))
                .databaseModule(new DatabaseModule()) // This line could be excluded.
                .build();*/
    }

    @Override
    public void onCreate() {
        super.onCreate();

        L.log("onCreate");
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule(BuildConfig.BASE_URL))
                .databaseModule(new DatabaseModule()) // This line could be excluded.
                .build();
    }

    public CompanyDataComponent getCompanyComponent() {
        if (mCompanyComponent == null) {
            mCompanyComponent = mAppComponent.getCompanyComponent(new CompanyDataModule());
        }

        return mCompanyComponent;
    }

    public void clearCompanyComponent() {
        mCompanyComponent = null;
    }
}
