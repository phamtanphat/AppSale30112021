package com.example.appsale30112021.di.modules;

import com.example.appsale30112021.presentation.main.MainActivity;
import com.example.appsale30112021.presentation.sign_in.SignInActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector
    public abstract SignInActivity bindContributeSignInActivity();

    @ContributesAndroidInjector
    public abstract MainActivity bindContributeMainActivity();
}
