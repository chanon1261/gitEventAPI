package com.bananacoding.android.pin_assistant_android.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.bananacoding.android.pin_assistant_android.R
import com.bananacoding.android.pin_assistant_android.activity.MainActivity
import com.bananacoding.android.pin_assistant_android.adapter.DrawerAdapter
import com.bananacoding.android.pin_assistant_android.data.ScreenEnum
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_drawer.*

class DrawerFragment : Fragment() {

    private lateinit var adapter: DrawerAdapter

    private var drawerListener: FragmentDrawerListener? = null
    private var mDrawerLayout: DrawerLayout? = null
    private var containerView: View? = null
    private lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            drawerListener = context as FragmentDrawerListener
        } catch (e: RuntimeException) {
            e.printStackTrace()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_drawer, container, false)
    }

    private var mDisposable: Disposable? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        home.setOnClickListener {
            drawerListener?.onDrawerItemSelected(view, ScreenEnum.Home.screenCode)
            containerView?.let { it1 -> mDrawerLayout?.closeDrawer(it1) }
        }
        train.setOnClickListener {
            drawerListener?.onDrawerItemSelected(view, ScreenEnum.SmartHome.screenCode)
            containerView?.let { it1 -> mDrawerLayout?.closeDrawer(it1) }
        }
        test.setOnClickListener {
            drawerListener?.onDrawerItemSelected(view, ScreenEnum.Test.screenCode)
            containerView?.let { it1 -> mDrawerLayout?.closeDrawer(it1) }
        }
        report.setOnClickListener {
            drawerListener?.onDrawerItemSelected(view, ScreenEnum.Report.screenCode)
            containerView?.let { it1 -> mDrawerLayout?.closeDrawer(it1) }
        }
        profile.setOnClickListener {
            drawerListener?.onDrawerItemSelected(view, ScreenEnum.Profile.screenCode)
            containerView?.let { it1 -> mDrawerLayout?.closeDrawer(it1) }
        }
        sign_out.setOnClickListener {
            drawerListener?.onDrawerItemSelected(view, ScreenEnum.SingOut.screenCode)
            containerView?.let { it1 -> mDrawerLayout?.closeDrawer(it1) }
            mDisposable?.dispose()
        }
    }

    fun init(fragmentId: Int, drawerLayout: DrawerLayout, toolbar: Toolbar, mainActivity: MainActivity) {
        containerView = activity!!.findViewById(fragmentId)
        mDrawerLayout = drawerLayout
        val drawerToggle = object : ActionBarDrawerToggle(activity, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            override fun onDrawerOpened(drawerView: View) {
                drawerView?.let { super.onDrawerOpened(it) }
                activity!!.invalidateOptionsMenu()
            }

            override fun onDrawerClosed(drawerView: View) {
                drawerView?.let { super.onDrawerClosed(it) }
                activity!!.invalidateOptionsMenu()
            }

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                drawerView?.let { super.onDrawerSlide(it, slideOffset) }
                toolbar.alpha = 1 - slideOffset / 2
            }
        }

        mDrawerLayout?.addDrawerListener(drawerToggle)
        mDrawerLayout?.post { drawerToggle.syncState() }

        this.mainActivity = mainActivity
    }

    interface ClickListener {
        fun onClick(view: View, position: Int)
        fun onLongClick(view: View?, position: Int)
    }

    internal class RecyclerTouchListener(context: Context, recyclerView: RecyclerView, private val clickListener: ClickListener?) : RecyclerView.OnItemTouchListener {

        private val gestureDetector: GestureDetector

        init {
            gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    return true
                }

                override fun onLongPress(e: MotionEvent) {
                    val child = recyclerView.findChildViewUnder(e.x, e.y)
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child))
                    }
                }
            })
        }

        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {

            val child = rv.findChildViewUnder(e.x, e.y)
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildAdapterPosition(child))
            }
            return false
        }

        override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}

        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

        }
    }

    interface FragmentDrawerListener {
        fun onDrawerItemSelected(view: View, position: Int)
    }
}

