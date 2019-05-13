package com.example.android.test

import android.app.Application
import com.example.android.test.dagger.AppComponent
import com.example.android.test.dagger.AppModule
import com.example.android.test.dagger.DaggerAppComponent
import net.danlew.android.joda.JodaTimeAndroid
import timber.log.Timber

open class MainApp : Application() {

    companion object {
        @JvmStatic lateinit var graph: AppComponent
        lateinit var instance: MainApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        initializeGraph()
        instance = this

        Timber.plant(Timber.DebugTree())
        JodaTimeAndroid.init(this)
        initializeGraph()
    }

    open fun initializeGraph() {
        graph = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        graph.inject(this)
    }
}