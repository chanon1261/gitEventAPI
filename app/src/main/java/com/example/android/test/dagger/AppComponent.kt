package com.example.android.test.dagger

import com.example.android.test.MainApp
import com.example.android.test.activity.MainActivity
import com.example.android.test.service.GitHubService
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(mainApp: MainApp)
    fun inject(mainActivity: MainActivity)
    fun inject(gitHubService: GitHubService)
}