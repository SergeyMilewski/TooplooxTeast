package pl.com.sergey.tooplooxsongapp;

import android.app.Application;

import pl.com.sergey.tooplooxsongapp.dagger.component.AppComponent;
import pl.com.sergey.tooplooxsongapp.dagger.component.DaggerAppComponent;
import pl.com.sergey.tooplooxsongapp.dagger.modules.AppModule;
import pl.com.sergey.tooplooxsongapp.dagger.modules.NetworkModule;


/**
 * Created by sergey on 27.11.17.
 */

public class MyApplication extends Application {

    private AppComponent appComponent;


    @Override
    public void onCreate() {

        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule(""))
                .build();

    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}
