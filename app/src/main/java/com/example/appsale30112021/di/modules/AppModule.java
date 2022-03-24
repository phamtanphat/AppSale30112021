package com.example.appsale30112021.di.modules;

import android.app.Application;

import com.example.appsale30112021.ultils.AppPreference;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    public static AppPreference providerAppPreference(Application application){
        return new AppPreference(application);
    }
}
