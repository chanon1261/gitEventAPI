package com.bananacoding.android.pin_assistant_android.dagger

import com.bananacoding.android.pin_assistant_android.BuildConfig
import com.bananacoding.android.pin_assistant_android.MainApp
import com.bananacoding.android.pin_assistant_android.service.GitHubApiService
import com.bananacoding.android.pin_assistant_android.service.GitHubService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(private val mainApp: MainApp) {

    @Provides
    @Singleton
    fun provideMainApp() = mainApp

    @Provides
    @Singleton
    fun provideGitHubApiService(): GitHubApiService {
        val interceptor = HttpLoggingInterceptor();
        interceptor.setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE);
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build();

        return Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(GitHubApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideGitHubService() = GitHubService();
}