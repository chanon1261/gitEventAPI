package com.bananacoding.android.pin_assistant_android.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.bananacoding.android.pin_assistant_android.MainApp
import com.bananacoding.android.pin_assistant_android.R
import com.bananacoding.android.pin_assistant_android.data.ScreenEnum
import com.bananacoding.android.pin_assistant_android.extension.empty
import com.bananacoding.android.pin_assistant_android.fragment.BlankFragment
import com.bananacoding.android.pin_assistant_android.fragment.DrawerFragment
import com.bananacoding.android.pin_assistant_android.fragment.MainFragment
import com.bananacoding.android.pin_assistant_android.fragment.SmartHomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import rx.Observable
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), DrawerFragment.FragmentDrawerListener /*AndroidApplication()*/ {

    companion object {
        fun getStartIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    private lateinit var drawerFragment: DrawerFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MainApp.graph.inject(this)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        drawerFragment = supportFragmentManager.findFragmentById(R.id.fragment_navigation_drawer) as DrawerFragment
        drawerFragment.init(R.id.fragment_navigation_drawer, drawer_layout, toolbar, this)

        displayView(ScreenEnum.Home.screenCode)

        /*val dummyProcessTime = 2L
        Observable.timer(dummyProcessTime, TimeUnit.SECONDS).subscribe {
            startActivity(DemoActivity.getStartIntent(this))
        }*/

        /*val config = AndroidApplicationConfiguration()
        initialize(Gdx3DTutorialGame(), config)*/
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        //menuInflater.inflate(R.menu.main, menu)
        return false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item)
    }

    override fun onDrawerItemSelected(view: View, position: Int) {
        when(position) {
            ScreenEnum.SmartHome.screenCode ->{
                val dummyProcessTime = 500L
                Observable.timer(dummyProcessTime, TimeUnit.MILLISECONDS).subscribe {
                    displayView(position)
                }
            }
            else ->{
                displayView(position)
            }
        }

        view.hideKeyboard()
    }

    public fun displayView(position: Int) {
        var fragment: Fragment? = null
        var title = String.empty()
        when (position) {
            0 -> {
                fragment = MainFragment()
                title = getString(R.string.nav_item_home)
            }
            1 -> {
                //setSupportActionBar(toolbar)
                fragment = SmartHomeFragment.fragment(this)
                title = getString(R.string.nav_item_train)
            }
            2 -> {
                fragment = BlankFragment.fragment(this)
                title = getString(R.string.nav_item_test)
            }
            3 -> {
                fragment = BlankFragment()
                title = getString(R.string.nav_item_report)
            }
            4 -> {
                //enable()
                fragment = BlankFragment.fragment(this)
                title = getString(R.string.nav_item_profile)
            }
            5 -> {
                //Logout.
                finish()
            }
        }

        if (fragment != null) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.main_content, fragment)
            fragmentTransaction.commit()
            supportActionBar?.title = String.empty()
        }
    }

    fun View.hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}
