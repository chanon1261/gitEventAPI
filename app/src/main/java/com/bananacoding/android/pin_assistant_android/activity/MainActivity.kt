package com.bananacoding.android.pin_assistant_android.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import com.bananacoding.android.pin_assistant_android.MainApp
import com.bananacoding.android.pin_assistant_android.R
import com.labdogstudio.tutorial.Gdx3DTutorialGame
import rx.Observable
import java.util.concurrent.TimeUnit

class MainActivity : AndroidApplication() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MainApp.graph.inject(this)

        /*val dummyProcessTime = 2L
        Observable.timer(dummyProcessTime, TimeUnit.SECONDS).subscribe {
            startActivity(DemoActivity.getStartIntent(this))
        }*/

        val config = AndroidApplicationConfiguration()
        initialize(Gdx3DTutorialGame(), config)

        //initializeForView()
    }
}
