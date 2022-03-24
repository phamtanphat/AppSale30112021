package com.example.appsale30112021.di.modules;



import com.example.appsale30112021.data.remote.ApiService;
import com.example.appsale30112021.ultils.AppConstant;
import com.example.appsale30112021.ultils.AppPreference;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public  class NetWorkModule {

    @Singleton
    @Provides
    public static OkHttpClient provideOkHttpClient(AppPreference appPreference) {
        return new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        String token = "";
                        if (appPreference != null && !appPreference.getToken().isEmpty()){
                            token = appPreference.getToken();
                        }
                        Request newRequest  = chain.request().newBuilder()
                                .addHeader("Authorization", "Bearer " + token)
                                .build();

                        return chain.proceed(newRequest);
                    }
                })
                .protocols(Arrays.asList(Protocol.HTTP_1_1))
                .build();
    }

    @Singleton
    @Provides
    public static Gson provideGson() {
        return new GsonBuilder().setLenient().disableHtmlEscaping().create();
    }

    @Singleton
    @Provides
    public static Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient ) {
        return new Retrofit.Builder()
                .baseUrl(AppConstant.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Singleton
    @Provides
    public static ApiService provideApiService(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }



}


