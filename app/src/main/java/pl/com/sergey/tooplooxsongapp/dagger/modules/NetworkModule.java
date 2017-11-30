package pl.com.sergey.tooplooxsongapp.dagger.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import pl.com.sergey.tooplooxsongapp.BuildConfig;
import pl.com.sergey.tooplooxsongapp.api.ItunsApi;
import pl.com.sergey.tooplooxsongapp.api.LocalApi;
import pl.com.sergey.tooplooxsongapp.api.LocalApiImpl;
import pl.com.sergey.tooplooxsongapp.facade.DataFacade;
import pl.com.sergey.tooplooxsongapp.facade.DataFacadeImpl;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sergey on 27.11.17.
 */
@Module
public class NetworkModule {


    private String baseUrl;

    public NetworkModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }


    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    Cache provideHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(Cache cache) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.cache(cache);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.addInterceptor(interceptor);
        }
        return client.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build();

    }

    @Provides
    @Singleton
    ItunsApi provideRemoteApi(Retrofit retrofit) {
        return retrofit.create(ItunsApi.class);
    }

    @Provides
    @Singleton
    DataFacade provideDataFacade(ItunsApi itunsApi, LocalApi localApi) {
        return new DataFacadeImpl(itunsApi, localApi);
    }

    @Provides
    @Singleton
    LocalApi provideLocalApi(Application application) {
        return new LocalApiImpl(application.getApplicationContext());
    }

}
