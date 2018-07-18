package com.bananacoding.android.pin_assistant_android.dagger

import com.bananacoding.android.pin_assistant_android.MainApp
import com.bananacoding.android.pin_assistant_android.activity.DemoActivity
import com.bananacoding.android.pin_assistant_android.activity.MainActivity
import com.bananacoding.android.pin_assistant_android.service.GitHubService
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(mainApp: MainApp)
    fun inject(mainActivity: MainActivity)
    fun inject(demoActivity: DemoActivity)
    fun inject(gitHubService: GitHubService)
}