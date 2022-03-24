package com.example.appsale30112021.di.components;

import android.app.Application;

import com.example.appsale30112021.MyApplication;
import com.example.appsale30112021.di.modules.ActivityModule;
import com.example.appsale30112021.di.modules.AppModule;
import com.example.appsale30112021.di.modules.NetWorkModule;
import com.example.appsale30112021.di.modules.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                NetWorkModule.class,
                ViewModelModule.class,
                ActivityModule.class,
                AppModule.class
        }
)
public interface AppComponent extends AndroidInjector<MyApplication> {

        @Component.Factory
        interface Factory {

            AppComponent create(@BindsInstance Application application);

        }

}
