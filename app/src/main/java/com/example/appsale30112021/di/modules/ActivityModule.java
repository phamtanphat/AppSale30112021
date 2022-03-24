package com.example.appsale30112021.di.modules;

import com.example.appsale30112021.presentation.features.main.MainActivity;
import com.example.appsale30112021.presentation.features.sign_in.SignInActivity;
import com.example.appsale30112021.presentation.features.sign_up.SignUpActivity;
import com.example.appsale30112021.presentation.features.splash.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector
    public abstract SignInActivity bindContributeSignInActivity();

    @ContributesAndroidInjector
    public abstract SignUpActivity bindContributeSignUpActivity();

    @ContributesAndroidInjector
    public abstract SplashActivity bindContributeSplashActivity();

    @ContributesAndroidInjector
    public abstract MainActivity bindContributeMainActivity();
}
