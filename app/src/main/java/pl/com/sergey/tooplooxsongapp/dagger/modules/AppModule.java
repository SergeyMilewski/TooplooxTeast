package pl.com.sergey.tooplooxsongapp.dagger.modules;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sergey on 27.11.17.
 */
@Module
public class AppModule {

    Application mApplication;

    public AppModule(Application mApplication) {
        this.mApplication = mApplication;
    }


    @Provides
    Application provideApplication(){
        return mApplication;
    }

}
