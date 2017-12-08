package com.danilomendes.figurines

import android.app.Application
import android.content.Context
import com.danilomendes.figurines.data.manager.CompanyDataComponent
import com.danilomendes.figurines.data.manager.CompanyDataModule
import com.danilomendes.figurines.di.*
import com.danilomendes.figurines.util.L

/**
 * Created by danilo on 08-12-2017.
 */
class FigurinesApplication : Application() {

    lateinit var appComponent: AppComponent

    var companyComponent: CompanyDataComponent? = null
        get() {
            if (field == null) {
                field = appComponent.getCompanyComponent(CompanyDataModule())
            }

            return field
        }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        L.log("attachBaseContext")
        // TODO Use attachBaseContext instead of onCreate to inject?
        /* appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule(BuildConfig.BASE_URL))
                .databaseModule(new DatabaseModule()) // This line could be excluded.
                .build();*/
    }

    override fun onCreate() {
        super.onCreate()

        L.log("onCreate")
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule(BuildConfig.BASE_URL))
                .databaseModule(DatabaseModule()) // This line could be excluded.
                .build()
    }

    fun clearCompanyComponent() {
        companyComponent = null
    }
}