package com.bananacoding.android.pin_assistant_android

import android.app.Application
import com.bananacoding.android.pin_assistant_android.dagger.AppComponent
import com.bananacoding.android.pin_assistant_android.dagger.AppModule
import com.bananacoding.android.pin_assistant_android.dagger.DaggerAppComponent
import com.labdogstudio.tutorial.Gdx3DTutorialGame
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